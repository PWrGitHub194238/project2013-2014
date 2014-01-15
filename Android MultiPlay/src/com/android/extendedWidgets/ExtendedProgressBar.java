package com.android.extendedWidgets;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ProgressBar;

public class ExtendedProgressBar extends ProgressBar {

	OnProgressChangedListener onProgressChangedListener = null;
	
	public ExtendedProgressBar(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public ExtendedProgressBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public ExtendedProgressBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setProgress(int progress) {
		super.setProgress(progress);
		if ( onProgressChangedListener != null ) {
			onProgressChangedListener.makeAction(progress);
		}
	}
	
	public final void setOnProgressChangedListener(
			OnProgressChangedListener onProgressChangedListener) {
		this.onProgressChangedListener = onProgressChangedListener;
	}
}
