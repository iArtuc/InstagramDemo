package instagramdemo.arutha.com.InstagramAuthorize;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import instagramdemo.arutha.com.instagramdemo.BaseFragment;
import instagramdemo.arutha.com.instagramdemo.R;
import instagramdemo.arutha.com.utils.ApplicationConstants;


public class InstagramAutho extends BaseFragment {

    private InstagramApp mApp;
    private Button btnConnect;
    private TextView tvSummary;

    private ViewGroup overallContainer;
    private LayoutInflater overallInflater;

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setOverallInflater(inflater);
        setOverallContainer(container);
        view = inflater
                .inflate(R.layout.main, container, false);
//        setContentView(R.layout.main);

        mApp = new InstagramApp(getActivity(), ApplicationConstants.CLIENT_ID,
                ApplicationConstants.CLIENT_SECRET, ApplicationConstants.CALLBACK_URL);
        mApp.setListener(listener);

        tvSummary = (TextView) view.findViewById(R.id.tvSummary);

        btnConnect = (Button) view.findViewById(R.id.btnConnect);
        btnConnect.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                if (mApp.hasAccessToken()) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(
                            getActivity());
                    builder.setMessage("Disconnect from Instagram?")
                            .setCancelable(false)
                            .setPositiveButton("Yes",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(
                                                DialogInterface dialog, int id) {
                                            mApp.resetAccessToken();
                                            btnConnect.setText("Connect");
                                            tvSummary.setText("Not connected");
                                        }
                                    })
                            .setNegativeButton("No",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(
                                                DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    });
                    final AlertDialog alert = builder.create();
                    alert.show();
                } else {
                    mApp.authorize();
                }
            }
        });

        if (mApp.hasAccessToken()) {
            tvSummary.setText("Connected as " + mApp.getUserName());
            btnConnect.setText("Disconnect");
        }

        return view;
    }

    InstagramApp.OAuthAuthenticationListener listener = new InstagramApp.OAuthAuthenticationListener() {

        @Override
        public void onSuccess() {
            tvSummary.setText("Connected as " + mApp.getUserName());
            btnConnect.setText("Disconnect");
        }

        @Override
        public void onFail(String error) {
            Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
        }
    };


    public ViewGroup getOverallContainer() {
        return overallContainer;
    }

    public void setOverallContainer(ViewGroup conta) {
        overallContainer = conta;
    }

    public LayoutInflater getOverallInflater() {
        return overallInflater;
    }

    public void setOverallInflater(LayoutInflater inflat) {
        overallInflater = inflat;
    }
}