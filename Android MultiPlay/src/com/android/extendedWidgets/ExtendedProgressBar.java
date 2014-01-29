package com.android.extendedWidgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;

/** Extended {@link ProgressBar} that can handle events where the value of progress bar has changed.
 * 
 * @author tomasz
 *
 */
public class ExtendedProgressBar extends ProgressBar {

	OnProgressChangedListener onProgressChangedListener = null;

	/**
	 * 
	 * @param context
	 */
	public ExtendedProgressBar(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param context
	 * @param attrs
	 */
	public ExtendedProgressBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public ExtendedProgressBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see android.widget.ProgressBar#setProgress(int)
	 */
	@Override
	public void setProgress(int progress) {
		super.setProgress(progress);
		if (onProgressChangedListener != null) {
			onProgressChangedListener.makeAction(progress);
		}
	}

	/**
	 * 
	 * @param onProgressChangedListener
	 */
	public final void setOnProgressChangedListener(
			OnProgressChangedListener onProgressChangedListener) {
		this.onProgressChangedListener = onProgressChangedListener;
	}
}
