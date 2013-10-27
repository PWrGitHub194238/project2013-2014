package com.android.extendedWidgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;

public class ImageToogleButton extends ImageButton {

	private boolean isToogle = false;
	
	public ImageToogleButton(Context context) {
		super(context);
	}
	
	public ImageToogleButton(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public ImageToogleButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public boolean isToogle() {
		return isToogle;
	}

	public void setToogle(boolean isToogle) {
		this.isToogle = isToogle;
	}

	public void toogleButton() {
		this.isToogle = !this.isToogle;
	}
}
