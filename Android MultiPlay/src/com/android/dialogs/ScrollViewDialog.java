package com.android.dialogs;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ScrollView;

/** Displays the alert dialog box that contains {@link ScrollView}. 
 * 
 * Dialog that extends AlertDialogs
 *
 */
public class ScrollViewDialog extends AlertDialogs {

	protected static final String VIEW_ID = "viewID";
	protected static ScrollView dialogInnerView = null;
	
	private Map<String,String> returnedData = null;

	protected static ScrollViewDialog newInstance( Integer titleIconID, Integer titleID, ScrollView view, Integer positiveButtonID, Integer neutralButtonID, Integer negativeButtonID ) {
    	ScrollViewDialog dialog = new ScrollViewDialog();

        Bundle args = AlertDialogs.newInstance(titleIconID, titleID, null, positiveButtonID, neutralButtonID, negativeButtonID).getArguments();

        args.putInt(ScrollViewDialog.VIEW_ID, ( view != null ) ? view.getId() : 0);

        dialog.setArguments(args);
        return dialog;
    }

	@Override
	public void buildDialogContent(Builder builder) {
		Log.d("Dialogs","override 3");
		Integer argsID = super.getArguments().getInt(ScrollViewDialog.VIEW_ID);
		if ( argsID !=  0 ) {
			builder.setView(dialogInnerView);
		}
	}

	public static void showDialog(Activity activity, String dialogIDTag, Integer titleIconID, Integer titleID, ScrollView view, Integer positiveButtonID, Integer neutralButtonID, Integer negativeButtonID ) {
		ScrollViewDialog dialog = ScrollViewDialog.newInstance(titleIconID,titleID,view,positiveButtonID,neutralButtonID,negativeButtonID);
        dialog.show(activity.getFragmentManager(), dialogIDTag);
	}
	
    public static ScrollView getViewFromResource(Activity activity, int id) {
    	dialogInnerView = (ScrollView) activity.getLayoutInflater().inflate(id, null).getRootView();
		return dialogInnerView;
    }
    
    public void dialogInnerViewLogic() {
    	Log.d("Dialogs","logic 1");

    	returnedData = new HashMap<String,String>();
    }

	public final Map<String, String> getReturnedData() {
		return returnedData;
	}

	public final void setReturnedData(Map<String, String> returnedData) {
		this.returnedData = returnedData;
	}

	public final ScrollView getDialogInnerView() {
		return dialogInnerView;
	}
	
	protected boolean validation(EditText toValid, Pattern regexp, String raiseError) {
		String text = toValid.getText().toString();
		String tag = toValid.getTag().toString();
		
		Log.d("Dialogs",tag+" validatation");
		
		if (regexp.matcher(text).matches() == true) {
			returnedData.put(tag, text);
			Log.d("Dialogs","validatation: OK ("+text+")");
			return true;
		} else {
			toValid.setError(raiseError);
			Log.d("Dialogs",tag+" validatation: FAIL");
			return false;
		}
	}
}