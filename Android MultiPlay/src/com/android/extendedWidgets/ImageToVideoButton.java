package com.android.extendedWidgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;

public class ImageToVideoButton extends ImageButton {

	private int videoID;
	
	public ImageToVideoButton(Context context) {
		super(context);
	}
	
	public ImageToVideoButton(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public ImageToVideoButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	public ImageToVideoButton(Context context, int videoID) {
		super(context);
		this.setVideoID(videoID);
	}

	/**
	 * @return the videoID
	 */
	public int getVideoID() {
		return videoID;
	}

	/**
	 * @param videoID the videoID to set
	 */
	public void setVideoID(int videoID) {
		this.videoID = videoID;
	}
	
}
