package com.android.extendedWidgets.lists;

import com.android.multiplay.R;


public class VideoListItem {
	
    public final static class VIDEO_MINIATURE {
    	public final static int FIRST = R.drawable.list_video_item_icon_1;
    	public final static int SECOND = R.drawable.list_video_item_icon_2;
    	public final static int THIRST = R.drawable.list_video_item_icon_3;
    };
    
	public VideoListItem(int index, String video_url, int video_iconID, 
			String video_title, int video_duration, String video_description) {
		this.index = index;
		this.video_url = video_url;
		this.video_iconID = video_iconID;
		this.video_title = video_title;
		this.video_duration = video_duration;
		this.video_description = video_description;
	}
	
	private int index = 0;
	private String video_url = null;
	private int video_iconID = 0;
	private String video_title = null;
	private int video_duration = 0;
	private String video_description = null;

	/**
	 * @return the index
	 */
	public final int getIndex() {
		return index;
	}
	/**
	 * @param index the index to set
	 */
	public final void setIndex(int index) {
		this.index = index;
	}
	/**
	 * @return the video_url
	 */
	public final String getVideo_url() {
		return video_url;
	}
	/**
	 * @param video_url the video_url to set
	 */
	public final void setVideo_url(String video_url) {
		this.video_url = video_url;
	}
	/**
	 * @return the video_iconID
	 */
	public final int getVideo_iconID() {
		return video_iconID;
	}
	/**
	 * @param video_iconID the video_iconID to set
	 */
	public final void setVideo_iconID(int video_iconID) {
		this.video_iconID = video_iconID;
	}
	/**
	 * @return the video_title
	 */
	public final String getVideo_title() {
		return video_title;
	}
	/**
	 * @param video_title the video_title to set
	 */
	public final void setVideo_title(String video_title) {
		this.video_title = video_title;
	}
	/**
	 * @return the video_duration
	 */
	public final int getVideo_duration() {
		return video_duration;
	}
	/**
	 * @param video_duration the video_duration to set
	 */
	public final void setVideo_duration(int video_duration) {
		this.video_duration = video_duration;
	}
	/**
	 * @return the video_description
	 */
	public final String getVideo_description() {
		return video_description;
	}
	/**
	 * @param video_description the video_description to set
	 */
	public final void setVideo_description(String video_description) {
		this.video_description = video_description;
	}

	
	
}