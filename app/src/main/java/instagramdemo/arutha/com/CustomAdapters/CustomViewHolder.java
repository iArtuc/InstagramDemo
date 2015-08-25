package instagramdemo.arutha.com.CustomAdapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import instagramdemo.arutha.com.CustomComponents.InstagramTextView;
import instagramdemo.arutha.com.instagramdemo.R;

/**
 * Created by ilkinartuc on 25/08/15.
 */
public class CustomViewHolder extends RecyclerView.ViewHolder {
    protected InstagramTextView textView;
    protected View RootView;

    public CustomViewHolder(View view) {
        super(view);
        this.textView = (InstagramTextView) view.findViewById(R.id.textViewPopularSearches);
        RootView = view;

    }
}
