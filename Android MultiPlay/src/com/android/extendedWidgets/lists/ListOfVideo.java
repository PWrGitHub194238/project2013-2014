package com.android.extendedWidgets.lists;

import java.util.Collection;

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
 
public class ListOfVideo extends BaseAdapter implements OnClickListener {
 
    private Context ctx;
    private VideoListItem[] data = null;
 
    public ListOfVideo(Context ctx, Collection<VideoListItem> listElements) {
    	data = new VideoListItem[listElements.size()];
    	listElements.toArray(data);
	    this.ctx = ctx;
    }
 
    public int getCount() {
    	return data.length;
    }
 
    public VideoListItem getItem(int position) {
    	return data[position];
    }
 
    public long getItemId(int position) {
    	return 0;
    }
 
    private class ViewHolderPattern {
    	private ImageView video_icon = null;
    	private TextView video_title = null;
    	private TextView video_duration = null;
    	private ImageToggleButton toggleDescription = null;
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
 
    public View getView(int position, View convertView, ViewGroup parent) {
 
    ViewHolderPattern view_holder;
 
    if (convertView == null) {
        LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.list_video_item, parent, false);
 
        view_holder = new ViewHolderPattern();
        view_holder.video_icon = (ImageView) convertView.findViewById(R.id.list_video_item_icon);
        view_holder.video_title = (TextView) convertView.findViewById(R.id.list_video_item_title);
        view_holder.video_duration = (TextView) convertView.findViewById(R.id.list_video_item_duration);
        view_holder.toggleDescription = (ImageToggleButton) convertView.findViewById(R.id.list_video_item_toggle_description);
        view_holder.toggleDescription.setOnClickListener(this);
        view_holder.video_description = (TextView) convertView.findViewById(R.id.list_video_item_description);

        convertView.setTag(view_holder);
    } else {
        view_holder = (ViewHolderPattern) convertView.getTag();
    }
 
    view_holder.setElement(data[position]);
 
    return convertView;
    }

	@Override
	public void onClick(View v) {
		((ImageToggleButton) v).toggleButton();
		if (((ImageToggleButton) v).isToggle()) {
			Log.d("APP","OK");
		}
	}
}