package instagramdemo.arutha.com.CustomComponents;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import instagramdemo.arutha.com.InstagramEnums.dialogTypeEnum;
import instagramdemo.arutha.com.instagramdemo.R;
import instagramdemo.arutha.com.utils.ApplicationConstants;

/**
 * Created by ilkinartuc on 23/08/15.
 */
public class InstagramCustomDialog {

    public InstagramCustomDialog(final Activity act, dialogTypeEnum enumVal, String textVal, Bitmap bitmapObj) {


        // custom dialog
        final Dialog dialog = new Dialog(act);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        switch (enumVal) {
            case LogoutDialog:
                dialog.setContentView(R.layout.logout_custom_dialog);
                RelativeLayout OkButton = (RelativeLayout) dialog.findViewById(R.id.RelativeCustomLogoutDialogOk);
                OkButton.setBackgroundColor(act.getResources().getColor(R.color.Green));
                // if button is clicked, close the custom dialog
                OkButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        act.finish();
                    }
                });

                RelativeLayout cancelButton = (RelativeLayout) dialog.findViewById(R.id.RelativeCustomLogoutDialogCancel);
                // if button is clicked, close the custom dialog
                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                break;
            case FullSizePictureDialog:


                dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                        WindowManager.LayoutParams.MATCH_PARENT);
                dialog.setContentView(R.layout.zoom_picture_dialog);

                ZoomCustomImageView zoomView = (ZoomCustomImageView) dialog.findViewById(R.id.zoomView);

                if (ApplicationConstants.ScreenWidth == 0) {
                    zoomView.setLayoutParams(new LinearLayout.LayoutParams(600, 600));
                } else {
                    zoomView.setLayoutParams(new LinearLayout.LayoutParams(ApplicationConstants.ScreenWidth, ApplicationConstants.ScreenWidth));
                }
                zoomView.setImageBitmap(bitmapObj);


                break;

        }


//


        dialog.show();


    }
}
