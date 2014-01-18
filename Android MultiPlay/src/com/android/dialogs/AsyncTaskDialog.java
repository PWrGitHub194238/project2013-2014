package com.android.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.multiplay.R;

public class AsyncTaskDialog extends Dialog implements AsyncTaskDialogInterface {
	
	public static String dialogIDTag = "AsynchTaskDialog";
	
	
	private TextView tv_dialog_asynchtask_working = null;
	
	
	public AsyncTaskDialog(Context context) {
		super(context, R.style.AsynchTaskProcessingDialog);
		
    	LayoutInflater inflater = LayoutInflater.from(context);
    	View layout = inflater.inflate(R.layout.dialog_asynchtask, null);
    	super.addContentView(layout, new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT));
    	
    	tv_dialog_asynchtask_working = (TextView) layout.findViewById(R.id.tv_dialog_asynchtask_working);
	}


	@Override
	public void updateDialogLogStatus(String log) {
		tv_dialog_asynchtask_working.setText(log);
		
	}
}
