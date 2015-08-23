package instagramdemo.arutha.com.instagramdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import instagramdemo.arutha.com.CustomComponents.InstagramCustomDialog;
import instagramdemo.arutha.com.InstagramAuthorize.InstagramAutho;
import instagramdemo.arutha.com.InstagramEnums.dialogTypeEnum;
import instagramdemo.arutha.com.InstagramEnums.mainPageEnum;
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

    private void changeFragment(mainPageEnum enumItem) {

        Fragment fr;
        FragmentTransaction fragmentTransaction;
        FragmentManager fm;
        Bundle bundle;
        switch (enumItem) {
            case GetToken:
                fr = new InstagramAutho();
                fm = getSupportFragmentManager();
//                bundle = new Bundle();
//                bundle.putLong("PoliceNo", police);
//                fr.setArguments(bundle);
                fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_place, fr);
                fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

                // fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
            case InstagramMainPage:

                fr = new InstagramMainPage();
                fm = getSupportFragmentManager();
//                bundle = new Bundle();
//                bundle.putLong("PoliceNo", police);
//                bundle.putLong("GrupNo", grup);
//                fr.setArguments(bundle);
                fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_place, fr);
                fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

                // fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

                break;

        }


    }


    private void setPageToView() {

        if (instagramMainPageFlow.hasToken()) {
            changeFragment(mainPageEnum.InstagramMainPage);
        } else {
            changeFragment(mainPageEnum.GetToken);
        }


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



