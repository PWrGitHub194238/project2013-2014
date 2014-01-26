package com.android.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.android.multiplay.R;

public abstract class FullScreenDialog extends Dialog implements AsyncTaskDialogInterface {
	
	public static String dialogIDTag = "FullScreenDialog";
	
	private View layout = null;
	private boolean isCancelable = false;

	public FullScreenDialog(Context context, Integer layoutID) {
		super(context, R.style.FullScreenDialog);

    	layout = LayoutInflater.from(context).inflate(layoutID, null);
    	super.addContentView(layout, new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT));
    	
    	dialogInnerViewLogic();
	}
	
	public FullScreenDialog(Context context, Integer layoutID, boolean isCancelable) {
		super(context, R.style.FullScreenDialog);
		
		this.isCancelable = isCancelable;
		layout = LayoutInflater.from(context).inflate(layoutID, null);
    	super.addContentView(layout, new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT));
    	
    	dialogInnerViewLogic();
	}

	protected void dialogInnerViewLogic() {
		this.setCancelable(isCancelable);
	}

	/**
	 * @return the layout
	 */
	public final View getLayout() {
		return layout;
	}
	
	
}
