package instagramdemo.arutha.com.instagramdemo;

import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.RelativeLayout;

import instagramdemo.arutha.com.CustomAdapters.MyGridAdapter;
import instagramdemo.arutha.com.CustomAdapters.PhotosAdapter;
import instagramdemo.arutha.com.CustomAdapters.PopularSearchAdapter;
import instagramdemo.arutha.com.CustomComponents.InstagramCustomDialog;
import instagramdemo.arutha.com.CustomComponents.MarginDecoration;
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
    private PhotosAdapter photoAdapter;
    private MyGridAdapter myGridAdapter;

    private ZoomCustomImageView zoomView;

    private GridLayoutManager gridLayoutManager;


    private boolean ifSearchOpen = false;
    private boolean photoClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instagram_main_fragment);


        BService = ((InstagramApplication) getApplication()).getBusinessService();
        instagramMainPageFlow = instagramMainPageFlow.getInstance(getApplicationContext(), BService);
        String AccessTokenTmp = PreferenceConnector.readString(getApplicationContext(), ApplicationConstants.API_ACCESS_TOKEN, null);
        BService.setAccessToken(AccessTokenTmp);


        getPopularSearches();
        SearchListener();
        initLayout();
        ClickListeners();
        getPhotosWithTag(null);

    }

    private void initLayout() {

        zoomView = (ZoomCustomImageView) findViewById(R.id.zoomView);


        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewMainFragment);
        mRecyclerView.setHasFixedSize(true);


        mSwipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipe_container_MainFragment);
        mSwipeRefresh.setColorSchemeColors(getResources().getColor(R.color.Blue), getResources().getColor(R.color.Red), getResources().getColor(R.color.Orange));
        mSwipeRefresh.bringToFront();

    }

    private void getPhotosWithTag(String tag) {

        String photoTag;
        if (tag == null) {
            photoTag = "hipo";
        } else {
            photoTag = tag;
        }
        instagramMainPageFlow.getTagPhotosRequest(new InstagramRespinseStatusListener() {
            @Override
            public void Success() {
                Log.d("tagPhotos", "OK");
                setmRecyclerViewAdapter(mainPageEnum.InstagramMainPage);
            }

            @Override
            public void Fail() {

            }
        }, photoTag);
    }

    private void getPopularSearches() {
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

                }
            }
        });
    }

    private void setmRecyclerViewAdapter(mainPageEnum enumVal) {

        switch (enumVal) {
            case PopularSearchPage:
                ifSearchOpen = true;
                LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                mRecyclerView.setLayoutManager(mLayoutManager);
                mAdapter = new PopularSearchAdapter(getApplicationContext(), instagramMainPageFlow.getPopularSearches(), new AdapterClickListener() {
                    @Override
                    public void itemClick(String obj) {
                        Log.d("clicked Item", obj);
                        getPhotosWithTag(obj);
                    }
                });
                mRecyclerView.setAdapter(mAdapter);
                mSwipeRefresh.setRefreshing(false);
                break;
            case InstagramMainPage:
                ifSearchOpen = false;
                photoAdapter = new PhotosAdapter(getApplicationContext(), instagramMainPageFlow.getPhotosUrl(), new PhotosAdapterClickListener() {
                    @Override
                    public void itemClick(Bitmap obj) {
                        Log.d("imageClicked", "yolo");
                        zoomView.bringToFront();
                        zoomView.setImageBitmap(obj);
                        photoClicked = true;
                    }
                });


                myGridAdapter = new MyGridAdapter(getApplicationContext());
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


                mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                        visibleItemCount = gridLayoutManager.getChildCount();
                        totalItemCount = gridLayoutManager.getItemCount();
                        pastVisiblesItems = gridLayoutManager.findFirstVisibleItemPosition();

                        if (loading) {
                            if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                                loading = false;
                                Log.v("...", "Last Item Wow !");
                            }
                        }
                    }
                });
//                mAdapter = new PopularSearchAdapter(getApplicationContext(), BService.getPopularSearches());
//                mRecyclerView.setAdapter(mAdapter);
//                mSwipeRefresh.setRefreshing(false);
                break;
        }
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

            setmRecyclerViewAdapter(mainPageEnum.InstagramMainPage);
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
//        Intent setIntent = new Intent(Intent.ACTION_MAIN);
//        setIntent.addCategory(Intent.CATEGORY_HOME);
//        setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(setIntent);
        }

    }

}



