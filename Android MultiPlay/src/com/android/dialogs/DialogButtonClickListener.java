package com.android.dialogs;

import android.app.DialogFragment;

public interface DialogButtonClickListener {
	public void onDialogPositiveClick(DialogFragment dialog);
	public void onDialogNeutralClick(DialogFragment dialog);
	public void onDialogNegativeClick(DialogFragment dialog);
}
