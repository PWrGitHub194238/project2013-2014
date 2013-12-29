package com.android.carousel;

import android.app.Activity;

public class CarouselDataItem {
	String title = null;
	int iconId = -1;
	Class<? extends Activity> nextActivity = null;
	
	public CarouselDataItem(String title, int iconId, Class<? extends Activity> nextActivity) {
		super();
		this.title = title;
		this.iconId = iconId;
		this.nextActivity = nextActivity;
	}

	public final String getTitle() {
		return title;
	}

	public final void setTitle(String title) {
		this.title = title;
	}

	public final int getIconId() {
		return iconId;
	}

	public final void setIconId(int iconId) {
		this.iconId = iconId;
	}

	public final Class<? extends Activity> getNextActivity() {
		return nextActivity;
	}

	public final void setNextActivity(Class<? extends Activity> nextActivity) {
		this.nextActivity = nextActivity;
	}

	
}
