package com.android.carousel;

import android.app.Activity;

public class CarouselDataItem {
	private String title = null;
	private int iconId = -1;
	private Class<? extends Activity> controllerActivity = null;
	private Class<? extends Activity> optionsActivity = null;
	private Class<? extends Activity> helperActivity = null;
	/**
	 * 
	 * @param title
	 * @param iconId
	 * @param controllerActivity
	 * @param optionsActivity
	 * @param helperActivity
	 */
	public CarouselDataItem(String title, int iconId, 
			Class<? extends Activity> controllerActivity, 
			Class<? extends Activity> optionsActivity,
			Class<? extends Activity> helperActivity) {
		super();
		this.title = title;
		this.iconId = iconId;
		this.controllerActivity = controllerActivity;
		this.optionsActivity = optionsActivity;
		this.helperActivity = helperActivity;
	}

	/**
	 * @return the title
	 */
	public final String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public final void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the iconId
	 */
	public final int getIconId() {
		return iconId;
	}

	/**
	 * @param iconId the iconId to set
	 */
	public final void setIconId(int iconId) {
		this.iconId = iconId;
	}

	/**
	 * @return the controllerActivity
	 */
	public final Class<? extends Activity> getControllerActivity() {
		return controllerActivity;
	}

	/**
	 * @return the optionsActivity
	 */
	public final Class<? extends Activity> getOptionsActivity() {
		return optionsActivity;
	}

	/**
	 * @return the helperActivity
	 */
	public final Class<? extends Activity> getHelperActivity() {
		return helperActivity;
	}

	
}
