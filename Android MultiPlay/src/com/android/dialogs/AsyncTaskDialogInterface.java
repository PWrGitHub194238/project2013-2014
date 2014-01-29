package com.android.dialogs;

/** Enables communication with the main thread in the sense of returning 
 * its results with optional possibility of artificial blocking activity (but NOT main thread).
 * 
 * @author tomasz
 *
 */
public interface AsyncTaskDialogInterface {

	public void updateDialogLogStatus( String log);
}
