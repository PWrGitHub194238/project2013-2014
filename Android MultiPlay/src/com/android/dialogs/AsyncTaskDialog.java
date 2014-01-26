package com.android.dialogs;

import android.content.Context;
import android.widget.TextView;

import com.android.multiplay.R;

public class AsyncTaskDialog extends FullScreenDialog implements AsyncTaskDialogInterface {
	
	private TextView tv_dialog_asynchtask_working = null;

	public AsyncTaskDialog(Context context) {
		super(context,R.layout.dialog_asynchtask);
	}

	@Override
	protected void dialogInnerViewLogic() {
		super.dialogInnerViewLogic();
		tv_dialog_asynchtask_working = (TextView) super.getLayout().findViewById(R.id.tv_dialog_asynchtask_working);
	}
	
	@Override
	public void updateDialogLogStatus(String log) {
		if (tv_dialog_asynchtask_working == null) {
			dialogInnerViewLogic();
		}
		tv_dialog_asynchtask_working.setText(log);
	
	}
}
