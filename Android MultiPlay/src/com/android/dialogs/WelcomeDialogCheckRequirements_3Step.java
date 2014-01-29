package com.android.dialogs;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.ScrollView;

/** Displays the alert dialog box that contains {@link ScrollView}. It shows 3ed welcome screen to new user.
 * 
 * @author tomasz
 *
 */
public class WelcomeDialogCheckRequirements_3Step extends ScrollViewDialog {
	

	
    /**
     * @param titleIconID
     * @param titleID
     * @param view
     * @param positiveButtonID
     * @param neutralButtonID
     * @param negativeButtonID
     * @return
     */
    protected static WelcomeDialogCheckRequirements_3Step newInstance( Integer titleIconID, Integer titleID, ScrollView view, Integer positiveButtonID, Integer neutralButtonID, Integer negativeButtonID ) {
    	WelcomeDialogCheckRequirements_3Step dialog = new WelcomeDialogCheckRequirements_3Step();
        Bundle args = ScrollViewDialog.newInstance(titleIconID, titleID, view, positiveButtonID, neutralButtonID, negativeButtonID).getArguments();
        
        dialog.setArguments(args);
        return dialog;
    }

	/* (non-Javadoc)
	 * @see com.android.dialogs.ScrollViewDialog#buildDialogContent(android.app.AlertDialog.Builder)
	 */
	@Override
	public void buildDialogContent(Builder builder) {
		super.buildDialogContent(builder);
		this.setCancelable(false);
		if ( ScrollViewDialog.dialogInnerView != null ) {
			dialogInnerViewLogic();
		}
	}
	
	/* (non-Javadoc)
	 * @see com.android.dialogs.AlertDialogs#onShow(android.content.DialogInterface)
	 */
	@Override
	public void onShow(DialogInterface dialog) {
		super.onShow(dialog);
		super.setWindowFullHorizontal();
	}

	/**
	 * @param activity
	 * @param dialogIDTag
	 * @param titleIconID
	 * @param titleID
	 * @param view
	 * @param positiveButtonID
	 * @param neutralButtonID
	 * @param negativeButtonID
	 */
	public static void showDialog(Activity activity, String dialogIDTag, Integer titleIconID, Integer titleID, ScrollView view, Integer positiveButtonID, Integer neutralButtonID, Integer negativeButtonID ) {
		WelcomeDialogCheckRequirements_3Step dialog = WelcomeDialogCheckRequirements_3Step.newInstance(
				titleIconID,titleID,view,positiveButtonID,neutralButtonID,negativeButtonID);
        dialog.show(activity.getFragmentManager(), dialogIDTag);
	}
    
	/* (non-Javadoc)
	 * @see com.android.dialogs.ScrollViewDialog#dialogInnerViewLogic()
	 */
	@Override
    public void dialogInnerViewLogic() {
		super.dialogInnerViewLogic();

	}

	
}