package com.android.dialogs;

import java.util.regex.Pattern;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.application.MultiPlayApplication;
import com.android.database.DBHelper;
import com.android.database.tables.General;
import com.android.extendedWidgets.ExtendedProgressBar;
import com.android.extendedWidgets.OnProgressChangedListener;
import com.android.multiplay.R;

/** Displays the alert dialog box that contains {@link ScrollView}. 
 * 
 * Dialog that extends AlertDialogs
 *
 */
public class WelcomeDialogCheckRequirements_1Step extends ScrollViewDialog implements OnProgressChangedListener, EditText.OnEditorActionListener {
	
	public static final String DEVICE_NAME = General.DBSchema.COLUMN_1;
	
	private static final Pattern PATTERN_DEVICE_NAME = Pattern.compile("^.+?$");
	
	private static final String PATTERN_DEVICE_NAME_ERROR = "Device name cannot be empty.";
	
	TextView tv_dialog_welcome_1 = null;
	TextView tv_dialog_welcome_2 = null;
	EditText et_dialog_welcome_device_name = null;
	TextView tv_dialog_welcome_3 = null;
	ExtendedProgressBar pb_dialog_welcome_requirements = null;
	
    protected static WelcomeDialogCheckRequirements_1Step newInstance( Integer titleIconID, Integer titleID, ScrollView view, Integer positiveButtonID, Integer neutralButtonID, Integer negativeButtonID ) {
    	WelcomeDialogCheckRequirements_1Step dialog = new WelcomeDialogCheckRequirements_1Step();
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
		super.getPositiveButton().setEnabled(false);
		super.setWindowFullHorizontal();
	}

	public static void showDialog(Activity activity, String dialogIDTag, Integer titleIconID, Integer titleID, ScrollView view, Integer positiveButtonID, Integer neutralButtonID, Integer negativeButtonID ) {
		WelcomeDialogCheckRequirements_1Step dialog = WelcomeDialogCheckRequirements_1Step.newInstance(
				titleIconID,titleID,view,positiveButtonID,neutralButtonID,negativeButtonID);
        dialog.show(activity.getFragmentManager(), dialogIDTag);
	}
    
	@Override
    public void dialogInnerViewLogic() {
		super.dialogInnerViewLogic();
			tv_dialog_welcome_1 = (TextView) dialogInnerView.findViewById(R.id.tv_dialog_welcome_1);
			tv_dialog_welcome_2 = (TextView) dialogInnerView.findViewById(R.id.tv_dialog_welcome_2);
			et_dialog_welcome_device_name = (EditText) dialogInnerView.findViewById(R.id.et_dialog_welcome_device_name);
			et_dialog_welcome_device_name.setTag(DEVICE_NAME);
			et_dialog_welcome_device_name.setOnEditorActionListener(this);
			tv_dialog_welcome_3 = (TextView) dialogInnerView.findViewById(R.id.tv_dialog_welcome_3);
			tv_dialog_welcome_3.setVisibility(TextView.GONE);
			
			pb_dialog_welcome_requirements = (ExtendedProgressBar) dialogInnerView.findViewById(R.id.pb_dialog_welcome_requirements);
			pb_dialog_welcome_requirements.setOnProgressChangedListener(this);
	}

	@Override
	public void makeAction(int progress) {
		if (progress == pb_dialog_welcome_requirements.getMax()) {
			super.getPositiveButton().setEnabled(true);
		}
		
	}

	@Override
	public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		switch (v.getId()) {
		case R.id.et_dialog_welcome_device_name:
			if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE || event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
				Log.d("APP","EDITED");
				if (super.validation(et_dialog_welcome_device_name, PATTERN_DEVICE_NAME, PATTERN_DEVICE_NAME_ERROR) == true) {
					tv_dialog_welcome_3.setVisibility(TextView.VISIBLE);
					Log.d("APP",super.getReturnedData().get(DEVICE_NAME));

					try {
						MultiPlayApplication.getDbHelper().updateMultiPlayRequirements(
								getActivity(), pb_dialog_welcome_requirements);
						
						MultiPlayApplication.setMultiPlayRequirements(DBHelper.parseMultiPlayRequirements(
								MultiPlayApplication.getDbHelper().sql_select_by_id(
										General.class, DBHelper.REOPEN_YES)));
					} catch (java.lang.InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		}
		return false;
	}
	
	
}