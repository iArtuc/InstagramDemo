package instagramdemo.arutha.com.instagramdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import instagramdemo.arutha.com.InstagramAuthorize.InstagramAutho;
import instagramdemo.arutha.com.utils.ApplicationConstants;
import instagramdemo.arutha.com.utils.PreferenceConnector;

/**
 * Created by ilkinartuc on 23/08/15.
 */
public class SplashScreen extends Activity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash_screen);

        new Handler().postDelayed(new Runnable() {

			/*
             * Showing splash screen with a timer. This will be useful when you
			 * want to show case your app logo / company
			 */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity

                String token = PreferenceConnector.readString(getApplicationContext(), ApplicationConstants.API_ACCESS_TOKEN, null);
                if (token != null) {
                    Intent i = new Intent(SplashScreen.this, InstagramDemoMainFragment.class);
                    // Intent i = new Intent(SplashScreen.this, graph.class);
                    startActivity(i);
                } else {
                    Intent i = new Intent(SplashScreen.this, InstagramAutho.class);
                    // Intent i = new Intent(SplashScreen.this, graph.class);
                    startActivity(i);
                }
                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

}
