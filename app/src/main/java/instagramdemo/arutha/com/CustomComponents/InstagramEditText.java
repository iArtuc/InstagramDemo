package instagramdemo.arutha.com.CustomComponents;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by ilkinartuc on 26/05/15.
 */
public class InstagramEditText extends EditText {

    public InstagramEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub

        // TODO Auto-generated constructor stub
        setAntiAlliasSubPixel();
    }

    public void setTypeface(int style) {
        if (!isInEditMode()) {
            // Your custom code that is not letting the Visual Editor draw
            // properly
            // i.e. thread spawning or other things in the constructor

            if (style == Typeface.BOLD) {
                super.setTypeface(Typeface.createFromAsset(getContext()
                        .getAssets(), "fonts/OpenSans-Regular.ttf"));

            } else {
                super.setTypeface(Typeface.createFromAsset(getContext()
                        .getAssets(), "fonts/OpenSans-Regular.ttf"));
            }
        }
    }

    public void setTypeface(Typeface tf, int style) {

        if (!isInEditMode()) {

            if (style == Typeface.BOLD) {
                super.setTypeface(Typeface.createFromAsset(getContext()
                        .getAssets(), "fonts/Roboto-Medium.ttf"));

            } else if (style == Typeface.ITALIC) {
                super.setTypeface(Typeface.createFromAsset(getContext()
                        .getAssets(), "fonts/Roboto-Light.ttf"));

            } else {
                super.setTypeface(Typeface.createFromAsset(getContext()
                        .getAssets(), "fonts/Roboto-Regular.ttf"));
            }
        }
    }

    public void setAntiAlliasSubPixel() {
        this.getPaint().setAntiAlias(true);
        this.getPaint().setSubpixelText(true);
    }

    public boolean isEmpty() {
        if (this.getText().toString().length() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isCharCountMinSix() {
        if (this.getText().toString().length() > 5) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isCharCountEqualsEleven() {
        if (this.getText().toString().length() == 11) {
            return true;
        } else {
            return false;
        }
    }

}