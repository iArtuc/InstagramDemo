package instagramdemo.arutha.com.instagramdemo;

import android.os.Bundle;
import android.util.Log;

import instagramdemo.arutha.com.CustomComponents.InstagramCustomDialog;
import instagramdemo.arutha.com.InstagramEnums.dialogTypeEnum;
import instagramdemo.arutha.com.flows.BusinessService;
import instagramdemo.arutha.com.flows.InstagramApplication;
import instagramdemo.arutha.com.flows.InstagramMainPageFlow;
import instagramdemo.arutha.com.utils.ApplicationConstants;

/**
 * Created by ilkinartuc on 23/08/15.
 */
public class InstagramDemoMainFragment extends BaseActivity {

    private BusinessService BService;
    private InstagramMainPageFlow instagramMainPageFlow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instagram_main_fragment);

        BService = ((InstagramApplication) getApplication()).getBusinessService();
        instagramMainPageFlow = instagramMainPageFlow.getInstance(getApplicationContext(), BService);

//        ActionBarNameTextView.setText(aheMainPageFlow.getMusteriNameLastName());
//        ActionBarNameTextView.setVisibility(View.GONE);

//        BService.getSlidingItemListener(new SlidingItemListener() {
//            @Override
//            public void SlidingItem(final long policeNo, final SlidingItemTypeEnum enumItem, final long grupNo) {
//
//                if (ApplicationConstants.LOG) {
//                    Log.d("Sliding Listener", policeNo + "");
//                }
//                BaseActivity.slidingMenu.toggle();
//
//                switch (enumItem) {
//                    case BesItem:
//                        singleBesRequest(policeNo);
//                        break;
//                    case BesTotal:
//                        changeFragment(enumItem);
//                        break;
//                    case PGOBes:
//                        break;
//                    case BirikimliHayat:
//                        break;
//                    case BirikimsizHayat:
//                        break;
//                    case GelirSigortasiHayat:
//                        break;
//                }
//
//            }
//
//        });


        setPageToView();

    }


    private void setPageToView() {

        instagramMainPageFlow.hasToken();


    }

    @Override
    public void onBackPressed() {
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



