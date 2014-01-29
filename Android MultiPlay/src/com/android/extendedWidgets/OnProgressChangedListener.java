package com.android.extendedWidgets;

/** Interface that handle executing some actions on progress bar value change.
 * 
 * @author tomasz
 *
 */
public interface OnProgressChangedListener {
/**
 * 
 * @param progress
 */
	public void makeAction(int progress);
}
