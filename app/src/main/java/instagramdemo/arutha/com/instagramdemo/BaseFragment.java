package instagramdemo.arutha.com.instagramdemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;

import instagramdemo.arutha.com.CustomComponents.customProgressDialog;

/**
 * Created by ilkinartuc on 23/08/15.
 */

public class BaseFragment extends Fragment {
    public customProgressDialog dialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();


    }

    public void showAHEDialog() {

        if (dialog == null) {
            dialog = new customProgressDialog(getActivity());
        }
        dialog.setCancelable(false);
        dialog.show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                // Actions to do after 10 seconds
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        }, 15000);

    }


    public void dismissAheDialog() {
        if (dialog != null) {
            try {
                dialog.hide();
                dialog.dismiss();
                dialog = null;
            } catch (Exception e) {
            }
        }
    }
}
