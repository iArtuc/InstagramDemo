package instagramdemo.arutha.com.instagramdemo;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import instagramdemo.arutha.com.CustomAdapters.MyGridAdapter;
import instagramdemo.arutha.com.CustomAdapters.PopularSearchAdapter;
import instagramdemo.arutha.com.CustomComponents.InstagramCustomDialog;
import instagramdemo.arutha.com.CustomComponents.InstagramEditText;
import instagramdemo.arutha.com.CustomComponents.ZoomCustomImageView;
import instagramdemo.arutha.com.InstagramEnums.dialogTypeEnum;
import instagramdemo.arutha.com.InstagramEnums.mainPageEnum;
import instagramdemo.arutha.com.Interfaces.AdapterClickListener;
import instagramdemo.arutha.com.Interfaces.InstagramRespinseStatusListener;
import instagramdemo.arutha.com.Interfaces.PhotosAdapterClickListener;
import instagramdemo.arutha.com.flows.BusinessService;
import instagramdemo.arutha.com.flows.InstagramApplication;
import instagramdemo.arutha.com.flows.InstagramMainPageFlow;
import instagramdemo.arutha.com.utils.ApplicationConstants;
import instagramdemo.arutha.com.utils.PreferenceConnector;

/**
 * Created by ilkinartuc on 23/08/15.
 */
public class InstagramDemoMainFragment extends BaseActivity {


    private boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;


    private BusinessService BService;
    private InstagramMainPageFlow instagramMainPageFlow;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefresh;
    private PopularSearchAdapter mAdapter;
    private MyGridAdapter myGridAdapter;

    private ZoomCustomImageView zoomView;

    private GridLayoutManager gridLayoutManager;
    private LinearLayoutManager mLayoutManager;

    private boolean ifSearchOpen = false;
    private boolean photoClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instagram_main_fragment);


        BService = ((InstagramApplication) getApplication()).getBusinessService();
        instagramMainPageFlow = InstagramMainPageFlow.getInstance(getApplicationContext(), BService);
        String AccessTokenTmp = PreferenceConnector.readString(getApplicationContext(), ApplicationConstants.API_ACCESS_TOKEN, null);
        BService.setAccessToken(AccessTokenTmp);


        getPopularSearches();
        SearchListener();
        initLayout();
        ClickListeners();
        getPhotosWithTag(null);


    }

    private void initLayout() {

        myGridAdapter = new MyGridAdapter(getApplicationContext(), new PhotosAdapterClickListener() {
            @Override
            public void itemClick(Bitmap obj) {
                Log.d("imageClicked", "yolo");
                if (obj != null) {
                    zoomView.bringToFront();
                    zoomView.setImageBitmap(obj);
                    photoClicked = true;
                }
            }
        });

        zoomView = (ZoomCustomImageView) findViewById(R.id.zoomView);


        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewMainFragment);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);


        mSwipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipe_container_MainFragment);

        mSwipeRefresh.setColorSchemeColors(getResources().getColor(R.color.Blue), getResources().getColor(R.color.Red), getResources().getColor(R.color.Orange));
        mSwipeRefresh.bringToFront();

//        textWatcher(ActionBarSearchEditText);

    }

    private void getPhotosWithPaging() {

//        showInstagramDialog();
        instagramMainPageFlow.getTagPhotosRequest(new InstagramRespinseStatusListener() {
            @Override
            public void Success() {
//                dismissInstagramDialog();
                Log.d("tagPhotos", "OK");
                setmRecyclerViewAdapter(mainPageEnum.getPhotosWithTagPaging);
            }

            @Override
            public void Fail() {

            }
        }, instagramMainPageFlow.getTagForSearch());
    }

    private void getPhotosWithTag(String tag) {

        showInstagramDialog();
        if (tag == null) {
            instagramMainPageFlow.setTagForSearch("hipo");
        } else {
            instagramMainPageFlow.setTagForSearch(tag);
        }
        instagramMainPageFlow.getTagPhotosRequest(new InstagramRespinseStatusListener() {
            @Override
            public void Success() {
                dismissInstagramDialog();
                Log.d("tagPhotos", "OK");
                setmRecyclerViewAdapter(mainPageEnum.getPhotosWithTag);
            }

            @Override
            public void Fail() {
                dismissInstagramDialog();
            }
        }, instagramMainPageFlow.getTagForSearch());
    }

    private void getPopularSearches() {
        showInstagramDialog();
        instagramMainPageFlow.getPopularTags(new InstagramRespinseStatusListener() {
            @Override
            public void Success() {
                Log.d("popularTagRequest", "OK");
                if (ifSearchOpen) {
                    setmRecyclerViewAdapter(mainPageEnum.PopularSearchPage);
                }
            }

            @Override
            public void Fail() {
                dismissInstagramDialog();
                Log.d("popularTagRequest", "Fail");
            }
        });
    }

    private void ClickListeners() {
        ActionBackImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchTab(false);
            }
        });


        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (ifSearchOpen) {
                    getPopularSearches();
                } else {
                    getPhotosWithTag(instagramMainPageFlow.getTagForSearch());
                }
            }
        });


        ActionBarSearchEditText.setOnEditorActionListener(
                new EditText.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (actionId == EditorInfo.IME_ACTION_DONE) {
                            instagramMainPageFlow.setTagForSearch(ActionBarSearchEditText.getText().toString().trim());
                            getPhotosWithTag(instagramMainPageFlow.getTagForSearch());
                            closeSearchTab();

                            return false;

                        }
                        return false;
                    }

                });
    }

    public void textWatcher(InstagramEditText aheEdit) {
        final InstagramEditText tmpEditText = aheEdit;
        tmpEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                instagramMainPageFlow.setTagForSearch(s.toString());


            }
        });
    }

    private void setmRecyclerViewAdapter(mainPageEnum enumVal) {

        switch (enumVal) {
            case PopularSearchPage:
                ifSearchOpen = true;
                instagramMainPageFlow.setNextURL(null);
                mLayoutManager = new LinearLayoutManager(getApplicationContext());
                mRecyclerView.setLayoutManager(mLayoutManager);
                mAdapter = new PopularSearchAdapter(getApplicationContext(), instagramMainPageFlow.getPopularSearches(), new AdapterClickListener() {
                    @Override
                    public void itemClick(String obj) {
                        Log.d("clicked Item", obj);
                        instagramMainPageFlow.setTagForSearch(obj);
                        getPhotosWithTag(instagramMainPageFlow.getTagForSearch());
                        instagramMainPageFlow.setNextURL(null);

                        closeSearchTab();

                    }
                });
                mRecyclerView.setAdapter(mAdapter);
                mSwipeRefresh.setRefreshing(false);
                break;
            case getPhotosWithTag:
                ifSearchOpen = false;
//                photoAdapter = new PhotosAdapter(getApplicationContext(), instagramMainPageFlow.getPhotosUrl(), new PhotosAdapterClickListener() {
//                    @Override
//                    public void itemClick(Bitmap obj) {
//                        Log.d("imageClicked", "yolo");
//                        zoomView.bringToFront();
//                        zoomView.setImageBitmap(obj);
//                        photoClicked = true;
//                    }
//                });


                myGridAdapter.clearAdapter();

                for (int i = 0; i < instagramMainPageFlow.getPhotosUrl().size(); i++) {
                    myGridAdapter.addDrawable(instagramMainPageFlow.getPhotosUrl().get(i), 300, 300);
                }
                gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
                mRecyclerView.setLayoutManager(gridLayoutManager);
//                mRecyclerView.addItemDecoration(new MarginDecoration(this));
//                mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(2, 50, false));
//                mRecyclerView.setAdapter(photoAdapter);

                mRecyclerView.setAdapter(myGridAdapter);

                mSwipeRefresh.setRefreshing(false);


                mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                        visibleItemCount = gridLayoutManager.getChildCount();
                        totalItemCount = gridLayoutManager.getItemCount();
                        pastVisiblesItems = gridLayoutManager.findFirstVisibleItemPosition();

                        if (loading) {
                            if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                                loading = false;
                                Log.d("...", "Last Item Wow !");
                                getPhotosWithPaging();
                            }
                        }
                    }
                });
//                mAdapter = new PopularSearchAdapter(getApplicationContext(), BService.getPopularSearches());
//                mRecyclerView.setAdapter(mAdapter);
//                mSwipeRefresh.setRefreshing(false);
                break;
            case getPhotosWithTagPaging:
                int start = myGridAdapter.getItemCount();
                for (int i = 0; i < instagramMainPageFlow.getPhotosUrl().size(); i++) {
                    myGridAdapter.addDrawable(instagramMainPageFlow.getPhotosUrl().get(i), 300, 300);
                }
                myGridAdapter.notifyItemRangeInserted(start, instagramMainPageFlow.getPhotosUrl().size());
                loading = true;
                break;
        }
    }


    private void closeSearchTab() {
        final float scale = getApplicationContext().getResources().getDisplayMetrics().density;
        int pixels = (int) (30 * scale + 0.5f);
        RelativeLayout.LayoutParams addQuestion_Params = new RelativeLayout.LayoutParams(pixels, ViewGroup.LayoutParams.WRAP_CONTENT);
        addQuestion_Params.addRule(RelativeLayout.CENTER_VERTICAL);
        addQuestion_Params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        ActionBarSearchEditText.setLayoutParams(addQuestion_Params);
        ActionBarSearchEditText.setText("");
        ObjectAnimator anim = ObjectAnimator.ofFloat(ActionBarIcon, "translationX", -500, 0);
        anim.setDuration(250);                  // Duration in milliseconds
        anim.setInterpolator(new LinearInterpolator());  // E.g. Linear, Accelerate, Decelerate
        anim.start();
        ActionBackImage.setVisibility(View.GONE);
        ActionBarSearchEditText.clearFocus();

        hideKeyboard();
        hideSoftKeyboard(InstagramDemoMainFragment.this);
    }

    private void SearchListener() {
        ActionBarSearchEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            public void onFocusChange(View v, boolean hasFocus) {
                Log.d("hasFocus", hasFocus + "");
                if (!hasFocus) {
                    // code to execute when EditText loses focus

                } else {
                    if (ActionBarSearchEditText.getText().toString().length() == 0) {

                        searchTab(true);

                    }
                }
            }
        });

    }

    private void searchTab(boolean isOpen) {
        if (isOpen) {
            setmRecyclerViewAdapter(mainPageEnum.PopularSearchPage);
            RelativeLayout.LayoutParams addQuestion_Params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            addQuestion_Params.addRule(RelativeLayout.CENTER_VERTICAL);
            addQuestion_Params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            addQuestion_Params.addRule(RelativeLayout.RIGHT_OF, ActionBackImage.getId());
            ActionBarSearchEditText.setLayoutParams(addQuestion_Params);
            ObjectAnimator oa = ObjectAnimator.ofFloat(ActionBarIcon, "xFraction", 0, 0.25f);
            ObjectAnimator anim = ObjectAnimator.ofFloat(ActionBarIcon, "translationX", 0f, -500);
            anim.setDuration(250);                  // Duration in milliseconds
            anim.setInterpolator(new LinearInterpolator());  // E.g. Linear, Accelerate, Decelerate
            anim.start();
            ActionBackImage.setVisibility(View.VISIBLE);
        } else {

            setmRecyclerViewAdapter(mainPageEnum.getPhotosWithTag);
            closeSearchTab();
        }
    }


    private void hideSoftKeyboard(Activity activity) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void hideKeyboard() {
        // Check if no view has focus:
        View view = this.getCurrentFocus();
        if (view != null) {
            try {
                InputMethodManager inputManager = (InputMethodManager) this
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(view.getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (photoClicked) {
            mSwipeRefresh.bringToFront();
            photoClicked = false;
        }

        if (ActionBarSearchEditText.hasFocus()) {
            ActionBarSearchEditText.clearFocus();

        } else if (!photoClicked) {
            if (ApplicationConstants.LOG) {
                Log.d("CDA", "onBackPressed Called");
            }
            new InstagramCustomDialog(this, dialogTypeEnum.LogoutDialog, null);

        }

    }


}



