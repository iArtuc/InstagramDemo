package instagramdemo.arutha.com.CustomComponents;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.content.Context;

import instagramdemo.arutha.com.instagramdemo.R;


/**
 * Created by ilkinartuc on 23/08/15.
 */

public class customProgressDialog extends ProgressDialog {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_dialog);

    }

    public customProgressDialog(Context context) {
        super(context);
    }

    public customProgressDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }
}
