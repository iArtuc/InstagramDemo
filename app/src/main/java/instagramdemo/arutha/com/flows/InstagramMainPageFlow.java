package instagramdemo.arutha.com.flows;

import android.content.Context;

import com.google.gson.Gson;

import java.util.ArrayList;

import instagramdemo.arutha.com.InstagramEnums.RequestMethodEnum;
import instagramdemo.arutha.com.Interfaces.AsyncListener;
import instagramdemo.arutha.com.Interfaces.InstagramRespinseStatusListener;
import instagramdemo.arutha.com.Request.ServiceRequest;
import instagramdemo.arutha.com.instagrammodels.Popular;
import instagramdemo.arutha.com.instagrammodels.RecentByTag;

/**
 * Created by ilkinartuc on 23/08/15.
 */
public class InstagramMainPageFlow {

    private Gson gsonObject;
    private Context context;
    private BusinessService BService;


    private ArrayList<String> popularSearches;
    private ArrayList<String> photosUrl;


    private String nextURL;


    private static InstagramMainPageFlow mInstance = null;


    private InstagramMainPageFlow(Context con, BusinessService bs) {
        gsonObject = new Gson();
        setContext(con);
        setBService(bs);
    }

    public static InstagramMainPageFlow getInstance(Context con, BusinessService bs) {
        if (mInstance == null) {
            mInstance = new InstagramMainPageFlow(con, bs);
        } else {
            mInstance.setContext(con);
            mInstance.setBService(bs);
        }

        return mInstance;
    }

    public boolean hasToken() {

        return true;
    }


    private void setTagPhotosObjects(RecentByTag obj) {
        if (obj != null && obj.getPagination() != null) {
            setNextURL(obj.getPagination().getNextUrl());
        }

        if (obj != null && obj.getMediaList() != null) {
            ArrayList<String> tmpURL = new ArrayList<String>();
            for (int i = 0; i < obj.getMediaList().size(); i++) {
                tmpURL.add(obj.getMediaList().get(i).getImages().getStandardResolution().getUrl());
            }
            setPhotosUrl(tmpURL);
        }


    }

    public void getTagPhotosRequest(final InstagramRespinseStatusListener listener, String tag) {


        new ServiceRequest(new AsyncListener() {
            @Override
            public void asyncOperationSucceded(Object obj) {


                RecentByTag photosObj = (RecentByTag) obj;
                setTagPhotosObjects(photosObj);
//                Log.d("with tags", tag.getPagination().getNextUrl());

                listener.Success();
            }

            @Override
            public void asyncOperationFailed(Object obj) {

                listener.Fail();
            }
        }, RequestMethodEnum.getTag, BService.getAccessToken(), tag, null);

//        new myasyncclass().execute();

//        Log.d("user", recent.getPagination().getNextUrl());


//        String tag, String AccessToken,String max_tag_id


    }


    private void setPopularSearchesArrayList(Popular tag) {
        try {
            if (tag != null && tag.getMediaList() != null) {
                ArrayList<String> tmpPopularTags = new ArrayList<String>();
                for (int i = 0; i < tag.getMediaList().size(); i++) {
                    if (tag.getMediaList().get(i).getTags() != null) {
                        for (int j = 0; j < tag.getMediaList().get(i).getTags().size(); j++) {
                            tmpPopularTags.add(tag.getMediaList().get(i).getTags().get(j));
                        }
                    }
                }
                setPopularSearches(tmpPopularTags);
            }
        } catch (Exception e) {

        }

    }

    public void getPopularTags(final InstagramRespinseStatusListener listener) {


        new ServiceRequest(new AsyncListener() {
            @Override
            public void asyncOperationSucceded(Object obj) {

                Popular tag = (Popular) obj;
//                Log.d("popular", tag.getMediaList().get(0).getTags().get(0));
                setPopularSearchesArrayList(tag);


                listener.Success();
            }

            @Override
            public void asyncOperationFailed(Object obj) {

                listener.Fail();
            }
        }, RequestMethodEnum.getPopular, BService.getAccessToken(), null, null);

//        new myasyncclass().execute();

//        Log.d("user", recent.getPagination().getNextUrl());


//        String tag, String AccessToken,String max_tag_id


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

    public ArrayList<String> getPopularSearches() {
        return popularSearches;
    }

    public void setPopularSearches(ArrayList<String> popularSearches) {
        this.popularSearches = popularSearches;
    }

    public ArrayList<String> getPhotosUrl() {
        return photosUrl;
    }

    public void setPhotosUrl(ArrayList<String> photosUrl) {
        this.photosUrl = photosUrl;
    }

    public String getNextURL() {
        return nextURL;
    }

    public void setNextURL(String nextURL) {
        this.nextURL = nextURL;
    }
}
