package instagramdemo.arutha.com.flows;

import android.content.Context;

import instagramdemo.arutha.com.utils.ApplicationConstants;
import instagramdemo.arutha.com.utils.PreferenceConnector;

/**
 * Created by ilkinartuc on 23/08/15.
 */
public class SharedPrefManager {


    private Context context;

    public SharedPrefManager(Context cntx) {
        setContext(cntx);


    }

    public void storeAccessToken(String accessToken, String id, String username, String name) {

        PreferenceConnector.writeString(getContext(), ApplicationConstants.API_ID, id);
        PreferenceConnector.writeString(getContext(), ApplicationConstants.API_NAME, name);
        PreferenceConnector.writeString(getContext(), ApplicationConstants.API_ACCESS_TOKEN, accessToken);
        PreferenceConnector.writeString(getContext(), ApplicationConstants.API_USERNAME, username);

    }

    public void storeAccessToken(String accessToken) {

        PreferenceConnector.writeString(getContext(), ApplicationConstants.API_ACCESS_TOKEN, accessToken);
    }

    /**
     * Reset access token and user name
     */
    public void resetAccessToken() {

        PreferenceConnector.writeString(getContext(), ApplicationConstants.API_ID, null);
        PreferenceConnector.writeString(getContext(), ApplicationConstants.API_NAME, null);
        PreferenceConnector.writeString(getContext(), ApplicationConstants.API_ACCESS_TOKEN, null);
        PreferenceConnector.writeString(getContext(), ApplicationConstants.API_USERNAME, null);

    }


    public String getUsername() {
        return PreferenceConnector.readString(getContext(), ApplicationConstants.API_USERNAME, null);
    }


    public String getId() {
        return PreferenceConnector.readString(getContext(), ApplicationConstants.API_ID, null);
    }


    public String getName() {
        return PreferenceConnector.readString(getContext(), ApplicationConstants.API_NAME, null);
    }


    public String getAccessToken() {
        return PreferenceConnector.readString(getContext(), ApplicationConstants.API_ACCESS_TOKEN, null);
    }

    private Context getContext() {
        return context;
    }

    private void setContext(Context context) {
        this.context = context;
    }

    public boolean hasAccessToken() {
        return (getAccessToken() == null) ? false : true;
    }
}
