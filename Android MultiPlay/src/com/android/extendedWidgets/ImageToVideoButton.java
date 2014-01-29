package com.android.extendedWidgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;

/** Extended {@link ImageButton} that in addition has a field contains id 
 * for video which can be used for redirecting user to appropriate video activity.
 * 
 * @author tomasz
 *
 */
public class ImageToVideoButton extends ImageButton {

	private int videoID;
	/**
	 * 
	 * @param context
	 */
	public ImageToVideoButton(Context context) {
		super(context);
	}
	/**
	 * 
	 * @param context
	 * @param attrs
	 */
	public ImageToVideoButton(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	/**
	 * 
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public ImageToVideoButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	/**
	 * 
	 * @param context
	 * @param videoID
	 */
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
