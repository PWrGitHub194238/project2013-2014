package com.android.carousel;

import android.app.Activity;

public class CarouselDataItem {
	String title = null;
	int iconId = -1;
	Class<? extends Activity> controllerActivity = null;
	Class<? extends Activity> optionsActivity = null;
	
	public CarouselDataItem(String title, int iconId, 
			Class<? extends Activity> controllerActivity, Class<? extends Activity> optionsActivity) {
		super();
		this.title = title;
		this.iconId = iconId;
		this.controllerActivity = controllerActivity;
		this.optionsActivity = optionsActivity;
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


	
}
