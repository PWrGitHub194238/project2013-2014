package com.android.extendedWidgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;

public class ImageToggleButton extends ImageButton {

	private boolean isToggle = false;
	
	public ImageToggleButton(Context context) {
		super(context);
	}
	
	public ImageToggleButton(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public ImageToggleButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public boolean isToggle() {
		return isToggle;
	}

	public void setToggle(boolean isToggle) {
		this.isToggle = isToggle;
	}

	public void toggleButton() {
		this.isToggle = !this.isToggle;
	}
}
