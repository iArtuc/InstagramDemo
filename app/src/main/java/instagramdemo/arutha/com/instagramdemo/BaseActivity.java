package instagramdemo.arutha.com.instagramdemo;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu.OnMenuItemClickListener;

import instagramdemo.arutha.com.CustomComponents.customProgressDialog;

/**
 * Created by ilkinartuc on 23/08/15.
 */
public class BaseActivity extends FragmentActivity implements
        OnMenuItemClickListener {

    public customProgressDialog dialog;

    public static ImageView ActionBarSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.base_fragment);
        //setActionBar();
        setActionBar();

    }

    public void showAHEDialog() {

        if (dialog == null) {
            dialog = new customProgressDialog(this);
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

    public void setActionBar() {
        // TODO Auto-generated method stub
        //getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        ActionBar mActionBar = getActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        //mActionBar.setElevation(0);
        LayoutInflater mInflater = LayoutInflater.from(this);


//                View mCustomView = mInflater.inflate(R.layout.actionbar_layout, null);
//                ActionBarNameTextView = (AheTextView) mCustomView.findViewById(R.id.textViewActionBarName);
//                ActionBarSettings = (ImageView) mCustomView.findViewById(R.id.imageViewAheSettings);
//
//                ActionBarSettings.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent intent = new Intent(getApplicationContext(), SettingsPage.class);
//                        startActivity(intent);
//                    }
//                });
//                mActionBar.setCustomView(mCustomView);
//                break;


        mActionBar.setDisplayShowCustomEnabled(true);
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {

            default:
                return false;
        }
    }


}