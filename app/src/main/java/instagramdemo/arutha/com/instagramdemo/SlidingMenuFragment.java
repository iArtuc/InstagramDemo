package instagramdemo.arutha.com.instagramdemo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import instagramdemo.arutha.com.InstagramAuthorize.InstagramApp;
import instagramdemo.arutha.com.InstagramAuthorize.InstagramAutho;
import instagramdemo.arutha.com.flows.BusinessService;
import instagramdemo.arutha.com.flows.InstagramApplication;
import instagramdemo.arutha.com.flows.SlidingMenuFlow;


@SuppressLint("ValidFragment")
public class SlidingMenuFragment extends Fragment {
    private View view;
    private Context context;
    private BusinessService BService;


    private ViewGroup SlidingContainer;
    private LayoutInflater SlidingInflater;


    private SlidingMenuFlow slidingFlow;

    private RelativeLayout logoutButton;


    @SuppressWarnings("deprecation")
    @SuppressLint("CutPasteId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = this.getActivity();
        view = inflater.inflate(R.layout.slidingmenu_fragment_layout, container, false);
        setSlidingInflater(inflater);
        setSlidingContainer(container);
        BService = ((InstagramApplication) this.getActivity().getApplication()).getBusinessService();
        slidingFlow = new SlidingMenuFlow(context, BService);
        initLayout();

        setLayout();


        return view;
    }


    private void setLayout() {

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slidingFlow.clearUserData();
                Intent i = new Intent(context, InstagramAutho.class);
                // i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                ((Activity) context).finish();
            }
        });


    }


    private void initLayout() {
        // TODO Auto-generated method stub

        logoutButton = (RelativeLayout) view.findViewById(R.id.RelativeLogutButton);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        // new GetirKullaniciBilgileriRequest(SlidingMenuFragment.this);

    }

    public ViewGroup getSlidingContainer() {
        return SlidingContainer;
    }

    public void setSlidingContainer(ViewGroup slidingContainer) {
        SlidingContainer = slidingContainer;
    }

    public LayoutInflater getSlidingInflater() {
        return SlidingInflater;
    }

    public void setSlidingInflater(LayoutInflater slidingInflater) {
        SlidingInflater = slidingInflater;
    }


}
