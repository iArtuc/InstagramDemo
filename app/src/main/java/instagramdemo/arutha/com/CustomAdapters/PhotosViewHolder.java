package instagramdemo.arutha.com.CustomAdapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import instagramdemo.arutha.com.instagramdemo.R;

/**
 * Created by ilkinartuc on 25/08/15.
 */
public class PhotosViewHolder extends RecyclerView.ViewHolder {
    protected ImageView thumbnail;
    protected View rootViewHolder;

    public PhotosViewHolder(View view) {
        super(view);
        this.thumbnail = (ImageView) view.findViewById(R.id.imageViewPhotoThumbnail);
        this.rootViewHolder = view;
    }

}
