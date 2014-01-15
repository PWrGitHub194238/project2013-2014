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
public class WelcomeDialogCheckRequirements_2Step extends ScrollViewDialog {
	

	
    protected static WelcomeDialogCheckRequirements_2Step newInstance( Integer titleIconID, Integer titleID, ScrollView view, Integer positiveButtonID, Integer neutralButtonID, Integer negativeButtonID ) {
    	WelcomeDialogCheckRequirements_2Step dialog = new WelcomeDialogCheckRequirements_2Step();
        Bundle args = ScrollViewDialog.newInstance(titleIconID, titleID, view, positiveButtonID, neutralButtonID, negativeButtonID).getArguments();
        
        dialog.setArguments(args);
        return dialog;
    }

	@Override
	public void buildDialogContent(Builder builder) {
		super.buildDialogContent(builder);
		this.setCancelable(false);
		if ( ScrollViewDialog.dialogInnerView != null ) {
			dialogInnerViewLogic();
		}
	}
	
	@Override
	public void onShow(DialogInterface dialog) {
		super.onShow(dialog);
	}

	public static void showDialog(Activity activity, String dialogIDTag, Integer titleIconID, Integer titleID, ScrollView view, Integer positiveButtonID, Integer neutralButtonID, Integer negativeButtonID ) {
		WelcomeDialogCheckRequirements_2Step dialog = WelcomeDialogCheckRequirements_2Step.newInstance(
				titleIconID,titleID,view,positiveButtonID,neutralButtonID,negativeButtonID);
        dialog.show(activity.getFragmentManager(), dialogIDTag);
	}
    
	@Override
    public void dialogInnerViewLogic() {
		super.dialogInnerViewLogic();

	}

	
}