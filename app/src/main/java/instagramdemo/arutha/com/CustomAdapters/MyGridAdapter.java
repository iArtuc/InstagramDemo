package instagramdemo.arutha.com.CustomAdapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.LruCache;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Picasso.Builder;

import java.util.ArrayList;
import java.util.List;

import instagramdemo.arutha.com.instagramdemo.R;

/**
 * Created by peter on 3/4/15.
 */
public class MyGridAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<String> imageUrls = new ArrayList<>();
    private List<Float> ratios = new ArrayList<>();

    Picasso p;

    public MyGridAdapter(Context context) {
        mContext = context;
        try {
            Picasso p = new Builder(mContext)
                    .memoryCache(new LruCache(24000))
                    .build();
            p.setIndicatorsEnabled(true);
            p.setLoggingEnabled(true);
            Picasso.setSingletonInstance(p);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.resizable_grid_item, null);
        MyViewHolder holder = new MyViewHolder(itemView);
        holder.imageView = (DynamicHeightImageView) itemView.findViewById(R.id.dynamic_height_image_view);
        holder.positionTextView = (TextView) itemView.findViewById(R.id.item_position_view);
        itemView.setTag(holder);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        MyViewHolder vh = (MyViewHolder) viewHolder;
        vh.positionTextView.setText("pos: " + position);
        vh.imageView.setRatio(ratios.get(position));
        Picasso.with(mContext).load(imageUrls.get(position)).placeholder(PlaceHolderDrawableHelper.getBackgroundDrawable(position)).into(vh.imageView);
    }

    @Override
    public int getItemCount() {
        return ratios.size();
    }

    public void clearAdapter() {
        imageUrls.clear();
        ratios.clear();
    }

    public void addDrawable(String imageUrl, int width, int height) {
        Log.d("POH", imageUrl);
        imageUrls.add(imageUrl);
        float ratio = (float) height / (float) width;
        ratios.add(ratio);
    }



}
