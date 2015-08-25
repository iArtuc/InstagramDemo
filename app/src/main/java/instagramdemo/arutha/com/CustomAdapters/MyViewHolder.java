package instagramdemo.arutha.com.CustomAdapters;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.TextView;

/**
 * Created by peter on 3/4/15.
 */
public class MyViewHolder extends ViewHolder {
	public DynamicHeightImageView imageView;
	public TextView positionTextView;
	public MyViewHolder(View itemView) {
		super(itemView);
	}
}
