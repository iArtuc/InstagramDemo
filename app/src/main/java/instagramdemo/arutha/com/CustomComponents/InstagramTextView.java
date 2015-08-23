package instagramdemo.arutha.com.CustomComponents;


import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by ilkinartuc on 26/05/15.
 */
public class InstagramTextView extends TextView {

    public InstagramTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub

        // TODO Auto-generated constructor stub
        setAntiAlliasSubPixel();
//        Typeface face=Typeface.createFromAsset(context.getAssets(), "assets/fonts/Roboto-Regular.ttf");
//        this.setTypeface(face);

    }

    public InstagramTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setAntiAlliasSubPixel();
    }


    public InstagramTextView(Context context) {
        super(context);
        setAntiAlliasSubPixel();
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

    public void setTypeface(int style) {
        if (!isInEditMode()) {
            // Your custom code that is not letting the Visual Editor draw
            // properly
            // i.e. thread spawning or other things in the constructor
            if (style == Typeface.BOLD) {
                super.setTypeface(Typeface.createFromAsset(getContext()
                        .getAssets(), "assets/fonts/Roboto-Medium.ttf"));

            } else if (style == Typeface.ITALIC) {
                super.setTypeface(Typeface.createFromAsset(getContext()
                        .getAssets(), "assets/fonts/Roboto-Light.ttf"));

            } else {
                super.setTypeface(Typeface.createFromAsset(getContext()
                        .getAssets(), "assets/fonts/Roboto-Regular.ttf"));
            }
        }
    }

    public void setAntiAlliasSubPixel() {
        this.getPaint().setAntiAlias(true);
        this.getPaint().setSubpixelText(true);
    }

}