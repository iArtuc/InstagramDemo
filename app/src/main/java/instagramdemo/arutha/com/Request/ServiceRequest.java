package instagramdemo.arutha.com.Request;


import instagramdemo.arutha.com.InstagramEnums.RequestMethodEnum;
import instagramdemo.arutha.com.Interfaces.AsyncListener;
import instagramdemo.arutha.com.instagramdemo.Instagram;

public class ServiceRequest extends
        android.os.AsyncTask<Void, Void, Object> {
    private AsyncListener asc;
    private RequestMethodEnum Method;
    private String Access_Token;
    private String Search_Tag;
    private String Max_Tag_id;

    private Instagram instagram;

    public ServiceRequest(AsyncListener asc, RequestMethodEnum method,
                          String AccessToken, String searchTag, String max_id) {
        super();
        this.asc = asc;
        this.Access_Token = AccessToken;
        this.Method = method;
        this.Search_Tag = searchTag;
        this.Max_Tag_id = max_id;
        instagram = new Instagram(Access_Token);


        execute();
    }

    @Override
    protected Object doInBackground(Void... params) {
        // TODO Auto-generated method stub
        try {

            switch (Method) {
                case getTag:
                    return instagram.getTagsEndpoint().getRecent(Search_Tag, null, null, Max_Tag_id);
                case getPopular:
                    return instagram.getMediaEndpoint().getPopular();
            }


        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object result) {
        // TODO Auto-generated method stub
        try {

            asc.asyncOperationSucceded(result);

        } catch (Exception e) {
            //e.printStackTrace();

            asc.asyncOperationFailed(null);

            // TODO: handle exception
        }
        super.onPostExecute(result);
    }

}