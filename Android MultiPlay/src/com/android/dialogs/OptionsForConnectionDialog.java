package com.android.dialogs;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.ScrollView;

/** Displays the alert dialog box that contains {@link ScrollView}. 
 * 
 * Dialog that extends AlertDialogs
 *
 */
public class OptionsForConnectionDialog extends ScrollViewDialog {
	
    protected static OptionsForConnectionDialog newInstance(ScrollView view ) {
    	OptionsForConnectionDialog dialog = new OptionsForConnectionDialog();
        Bundle args = ScrollViewDialog.newInstance(null,null,view,null,null,null).getArguments();
        
        dialog.setArguments(args);
        return dialog;
    }

	@Override
	public void buildDialogContent(Builder builder) {
		super.buildDialogContent(builder);
		if ( ScrollViewDialog.dialogInnerView != null ) {
			dialogInnerViewLogic();
		}
	}
	
	@Override
	public void onShow(DialogInterface dialog) {
		super.onShow(dialog);
		super.getPositiveButton().setEnabled(false);
	}

	public static void showDialog(Activity activity, String dialogIDTag, ScrollView view) {
		OptionsForConnectionDialog dialog = OptionsForConnectionDialog.newInstance(view);
        dialog.show(activity.getFragmentManager(), dialogIDTag);
	}
    
	@Override
    public void dialogInnerViewLogic() {
		super.dialogInnerViewLogic();
	}


}