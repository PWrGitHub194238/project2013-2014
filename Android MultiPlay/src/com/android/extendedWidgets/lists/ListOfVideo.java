package com.android.extendedWidgets.lists;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.extendedWidgets.ImageToggleButton;
import com.android.multiplay.R;
 
/** Adapter for list of elements defined in {@link VideoListItem}.
 * 
 * @author tomasz
 *
 */
public class ListOfVideo extends BaseAdapter implements OnClickListener {
 
    /**
     * 
     */
    private Context ctx;
    /**
     * 
     */
    private VideoListItem[] videoList = null;
 
    /**
     * @param ctx
     * @param videoList
     */
    public ListOfVideo(Context ctx, VideoListItem[] videoList) {
    	this.videoList = videoList;
	    this.ctx = ctx;
    }
 
    /* (non-Javadoc)
     * @see android.widget.Adapter#getCount()
     */
    public int getCount() {
    	return videoList.length;
    }
 
    /* (non-Javadoc)
     * @see android.widget.Adapter#getItem(int)
     */
    public VideoListItem getItem(int position) {
    	return videoList[position];
    }
 
    /* (non-Javadoc)
     * @see android.widget.Adapter#getItemId(int)
     */
    public long getItemId(int position) {
    	return 0;
    }
 
    /** Pattern for list item that holds view configuration to prevent it to load each time new item occurred.
     * 
     * @author tomasz
     *
     */
    private class ViewHolderPattern {
    	private ImageView video_icon = null;
    	private TextView video_title = null;
    	private TextView video_duration = null;
    	private TextView video_description = null;

    	
		public void setElement(VideoListItem videoListItem) {
			video_icon.setBackgroundResource(videoListItem.getVideo_iconID());
			video_title.setText(videoListItem.getVideo_title());
			video_duration.setText(caclulateDuration(videoListItem.getVideo_duration()));
			video_description.setText(videoListItem.getVideo_description());
		}


		private String caclulateDuration(int video_duration) {
			// TODO Auto-generated method stub
			return "5";
		}
    }
 
    /* (non-Javadoc)
     * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
     */
    public View getView(int position, View convertView, ViewGroup parent) {
 
    ViewHolderPattern view_holder;
 
    if (convertView == null) {
        LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.list_video_item, parent, false);
 
        view_holder = new ViewHolderPattern();
        view_holder.video_icon = (ImageView) convertView.findViewById(R.id.list_video_item_icon);
        view_holder.video_title = (TextView) convertView.findViewById(R.id.list_video_item_title);
        view_holder.video_duration = (TextView) convertView.findViewById(R.id.list_video_item_duration);
        view_holder.video_description = (TextView) convertView.findViewById(R.id.list_video_item_description);

        convertView.setTag(view_holder);
    } else {
        view_holder = (ViewHolderPattern) convertView.getTag();
    }
 
    view_holder.setElement(videoList[position]);
 
    return convertView;
    }

	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		((ImageToggleButton) v).toggleButton();
		if (((ImageToggleButton) v).isToggle()) {
			Log.d("APP","OK");
		}
	}
}