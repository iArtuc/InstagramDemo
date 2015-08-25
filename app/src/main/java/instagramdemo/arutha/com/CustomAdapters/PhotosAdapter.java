package instagramdemo.arutha.com.CustomAdapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import instagramdemo.arutha.com.Interfaces.PhotosAdapterClickListener;
import instagramdemo.arutha.com.instagramdemo.R;

/**
 * Created by ilkinartuc on 25/08/15.
 */
public class PhotosAdapter extends RecyclerView.Adapter<PhotosViewHolder> {


    private ArrayList<String> feedItemList;

    private Context mContext;
    private PhotosAdapterClickListener photosAdapterClickListener;

    public PhotosAdapter(Context context, ArrayList<String> feedItemList, PhotosAdapterClickListener listener) {
        this.feedItemList = feedItemList;
        this.mContext = context;
        this.photosAdapterClickListener = listener;
    }

    @Override
    public PhotosViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.photos_custom_item, null);
        PhotosViewHolder mh = new PhotosViewHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(final PhotosViewHolder feedListRowHolder, int i) {
        String feedItem = feedItemList.get(i);


        Picasso.with(mContext).load(feedItem)
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .into(feedListRowHolder.thumbnail);


        feedListRowHolder.rootViewHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                photosAdapterClickListener.itemClick(((BitmapDrawable) feedListRowHolder.thumbnail.getDrawable()).getBitmap());
            }
        });

    }

    @Override
    public int getItemCount() {
        return (null != feedItemList ? feedItemList.size() : 0);
    }

}