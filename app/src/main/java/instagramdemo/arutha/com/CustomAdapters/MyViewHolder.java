package instagramdemo.arutha.com.CustomAdapters;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import instagramdemo.arutha.com.instagramdemo.R;

/**
 * Created by peter on 3/4/15.
 */
public class MyViewHolder extends ViewHolder {
    public DynamicHeightImageView imageView;
    public View rootViewHolder;

    public MyViewHolder(View itemView) {
        super(itemView);
        this.rootViewHolder = itemView;

    }
}
