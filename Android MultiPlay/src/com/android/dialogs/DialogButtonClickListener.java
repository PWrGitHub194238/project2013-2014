package com.android.dialogs;

import android.app.DialogFragment;

/** Interface for receiving a signal from the dialogs used in the application.
 * 
 * @author tomasz
 *
 */
public interface DialogButtonClickListener {
	/**
	 * @param dialog
	 */
	public void onDialogPositiveClick(DialogFragment dialog);
	/**
	 * @param dialog
	 */
	public void onDialogNeutralClick(DialogFragment dialog);
	/**
	 * @param dialog
	 */
	public void onDialogNegativeClick(DialogFragment dialog);
}
