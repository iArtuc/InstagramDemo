package instagramdemo.arutha.com.flows;

import android.app.Application;

/**
 * Created by ilkinartuc on 23/08/15.
 */
public class InstagramApplication extends Application {

    private BusinessService businessService;

    private static InstagramApplication mcbpApplication;


    public static InstagramApplication getAHEApplication() {
        return mcbpApplication;
    }

    private static void setAHEPApplication(InstagramApplication application) {
        mcbpApplication = application;
    }


    @Override
    public void onCreate() {
        super.onCreate();


        setAHEPApplication(this);

        businessService = new BusinessService();


    }


    public BusinessService getBusinessService() {
        return businessService;
    }


}
