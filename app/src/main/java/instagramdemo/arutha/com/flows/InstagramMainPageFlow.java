package instagramdemo.arutha.com.flows;

import android.content.Context;

import com.google.gson.Gson;

/**
 * Created by ilkinartuc on 23/08/15.
 */
public class InstagramMainPageFlow {

    private Gson gsonObject;
    private Context context;
    private BusinessService BService;

    private static InstagramMainPageFlow mInstance = null;


    private InstagramMainPageFlow(Context con, BusinessService bs) {
        gsonObject = new Gson();
        setContext(con);
        setBService(bs);
    }

    public static InstagramMainPageFlow getInstance(Context con, BusinessService bs) {
        if (mInstance == null) {
            mInstance = new InstagramMainPageFlow(con, bs);
        }
        return mInstance;
    }

    public boolean hasToken() {

        return true;
    }


    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public BusinessService getBService() {
        return BService;
    }

    public void setBService(BusinessService BService) {
        this.BService = BService;
    }

}
