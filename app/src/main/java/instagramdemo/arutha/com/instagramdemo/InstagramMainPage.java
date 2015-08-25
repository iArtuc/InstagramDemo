package instagramdemo.arutha.com.instagramdemo;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import instagramdemo.arutha.com.flows.BusinessService;
import instagramdemo.arutha.com.flows.InstagramApplication;
import instagramdemo.arutha.com.flows.InstagramMainPageFlow;
import instagramdemo.arutha.com.instagrammodels.RecentByTag;
import instagramdemo.arutha.com.utils.ApplicationConstants;
import instagramdemo.arutha.com.utils.PreferenceConnector;


/**
 * Created by ilkinartuc on 23/08/15.
 */
public class InstagramMainPage extends BaseFragment {

    private RecyclerView gridView;
    private InstagramMainPageFlow instagramMainPageFlow;
    private BusinessService BService;

    private Context context;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        context = getActivity().getApplicationContext();
        view = inflater.inflate(R.layout.instagram_image_page, container, false);
        BService = ((InstagramApplication) getActivity().getApplication()).getBusinessService();
        instagramMainPageFlow = instagramMainPageFlow.getInstance(context, BService);

        initLayout();

        makeRequest();


        return view;
    }


    private void makeRequest() {

//        instagramMainPageFlow.getRecentPhotosRequest(new InstagramRespinseStatusListener() {
//            @Override
//            public void Success() {
//
//            }
//
//            @Override
//            public void Fail() {
//
//            }
//        });


//        new myasyncclass().execute();

//        Log.d("user", recent.getPagination().getNextUrl());
    }

    private void initLayout() {

        gridView = (RecyclerView) view.findViewById(R.id.grid_view);
//        GridLayoutManager lms = new GridLayoutManager(getActivity(), 2);
//        gridView.setLayoutManager(lms);


    }

    public class myasyncclass extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            // Create a very simple REST adapter which points the GitHub API endpoint.


            final Instagram instagram = new Instagram(PreferenceConnector.readString(getActivity().getApplicationContext(), ApplicationConstants.API_ACCESS_TOKEN, null));


            final RecentByTag tag = instagram.getTagsEndpoint().getRecent("hipo");


            Log.d("user", "yolo");


//            taskService.getTasks(new Callback<List<Task>>() {
//                @Override
//                public void success(List<Task> tasks, Response response) {
//                    // here you do stuff with returned tasks
//                }
//
//                @Override
//                public void failure(RetrofitError error) {
//                    // you should handle errors, too
//                }
//            });

//
            return null;
        }
    }

}
