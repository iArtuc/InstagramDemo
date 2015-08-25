package instagramdemo.arutha.com.CustomAdapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import instagramdemo.arutha.com.Interfaces.AdapterClickListener;
import instagramdemo.arutha.com.instagramdemo.R;

/**
 * Created by ilkinartuc on 25/08/15.
 */
public class PopularSearchAdapter extends RecyclerView.Adapter<CustomViewHolder> {

    private List<String> feedItemList;
    private Context mContext;
    private AdapterClickListener ClickListener;

    public PopularSearchAdapter(Context context, ArrayList<String> feedItemList, AdapterClickListener listener) {
        this.feedItemList = feedItemList;
        this.mContext = context;
        this.ClickListener = listener;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.popular_search_custom_item, null);

        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, final int i) {
        String popularSearchName = feedItemList.get(i);


        //Setting text view title
        customViewHolder.textView.setText(popularSearchName);
        customViewHolder.RootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickListener.itemClick(feedItemList.get(i));
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != feedItemList ? feedItemList.size() : 0);
    }
}




