package com.android.dialogs;

import android.content.Context;
import android.widget.TextView;

import com.android.multiplay.R;

/** Displays the text dialog box that contains one log-like text field.
 * 
 * Dialog that extends {@link FullScreenDialog}
 *
 * @author tomasz
 */
public class AsyncTaskDialog extends FullScreenDialog implements AsyncTaskDialogInterface {
	
	/**
	 * 
	 */
	private TextView tv_dialog_asynchtask_working = null;

	/**
	 * @param context
	 */
	public AsyncTaskDialog(Context context) {
		super(context,R.layout.dialog_asynchtask);
	}

	/* (non-Javadoc)
	 * @see com.android.dialogs.FullScreenDialog#dialogInnerViewLogic()
	 */
	@Override
	protected void dialogInnerViewLogic() {
		super.dialogInnerViewLogic();
		tv_dialog_asynchtask_working = (TextView) super.getLayout().findViewById(R.id.tv_dialog_asynchtask_working);
	}
	
	/* (non-Javadoc)
	 * @see com.android.dialogs.AsyncTaskDialogInterface#updateDialogLogStatus(java.lang.String)
	 */
	@Override
	public void updateDialogLogStatus(String log) {
		if (tv_dialog_asynchtask_working == null) {
			dialogInnerViewLogic();
		}
		tv_dialog_asynchtask_working.setText(log);
	
	}
}
