package instagramdemo.arutha.com.flows;

import android.content.Context;


/**
 * Created by ilkinartuc on 09/06/15.
 */
public class SlidingMenuFlow {

    private Context context;
    private BusinessService BService;


    public SlidingMenuFlow(Context con, BusinessService bs) {
        setContext(con);
        setBService(bs);

    }

    public void clearUserData() {
        SharedPrefManager tmpManager = new SharedPrefManager(getContext());
        tmpManager.resetAccessToken();
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
