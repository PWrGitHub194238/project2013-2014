package com.android.multiplay;


import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.android.application.MultiPlayApplication;
import com.android.database.MultiPlayDataBase;
import com.android.dialogs.AlertDialogs;
import com.android.dialogs.DialogButtonClickListener;
import com.android.dialogs.elements.DialogListCore;

public class OptionsActivity extends Activity implements DialogButtonClickListener {

////////////////////Fields



/** Menu button.
* 
* It leaves to next activity: {@link MultiplayExplorerActivity}.
* This button is not active if no wireless or bluetooth connections were found.
* On click it call method {@link #connections_OnClick(View)}.
*/
private ImageButton b_connections = null;

/** Menu button.
* 
* It leaves to next activity: {@link SystemControllerActivity}.
* This button is not active if no wireless or bluetooth connections were found.
* On click it call method {@link #connections_OnClick(View)}.
*/
private ImageButton b_controllers = null;

/** Menu button.
* 
* It leaves to next activity: {@link HelpActivity}.
* On click it call method {@link #resetdata_OnClick(View)}.
*/
private ImageButton b_resetdata = null;

private ImageButton b_goback = null;

////////////////////Methods



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_options);

//		search = (Button) super.findViewById(R.id.search);
//		IP = (EditText) super.findViewById(R.id.IPeditText);
//		nextButton = (Button) super.findViewById(R.id.imageButton1);
		
		initB_connecions(R.id.b_options_activity_connecions_icon);
		initB_controllers(R.id.b_options_activity_controllers_icon);
		initB_resetdata(R.id.b_options_activity_resetdata_icon);
		
		initB_goback(R.id.b_go_back);
		
		init();
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.i("Main", "Resume");
		init();
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		init();
	}
	

	@Override 
	protected void onDestroy() {
		super.onDestroy();
	}
	
	
	
//////////////////// Methods for onClick events

	
	
	public void controllers_OnClick( View view ) {
		Intent intent = new Intent(this, OptionsControllerActivity.class);
		super.startActivity(intent);
	}
	
	public void connections_OnClick( View view ) {
		Intent intent = new Intent(this, ConnectionsActivity.class);
		super.startActivity(intent);
	}
	
	public void resetdata_OnClick( View view ) {
		AlertDialogs.showDialog(this,
				OptionsActivity.DialogList.TAG_RESET_DB,
				DialogListCore.IT_TITLE_ICON_WARNING,
				OptionsActivity.DialogList.ID_TITLE_RESET_DB,
				OptionsActivity.DialogList.ID_MESSAGE_RESET_DB,
				DialogListCore.ID_BUTTON_OK,
				null,
				DialogListCore.ID_BUTTON_CANCEL);
	}
	
	public void go_back_OnClick( View view ) {
		super.finish();
	}

	
	
////////////////////Methods for initialize objects



	/** Default initial method that constrains all tasks that should be done either {{@link #onCreate(Bundle)} or {{@link #onRestart()}.
	*  It requests back focus to unfocused any menu items.
	*/
	private void init() {
		super.findViewById(R.id.tv_title_of_selected_item).requestFocus();
	}

	/** Initialize method that links {@link #b_connections} object with correct View by id.
	* 
	* Also creates a new listener for this button's events 
	* by passing this {@link Activity} and id parameter 
	* to {@link ButtonsFocusChangeListener#ButtonsFocusChangeListener(Activity, int)} .
	* @param id ID of view in {@link com.android.multiplay.R}
	*/
	private void initB_connecions( int id) {
		this.b_connections = (ImageButton) super.findViewById(id);
		this.b_connections.setOnFocusChangeListener(
		new ButtonsFocusChangeListener(this,id));
	}

	/** Initialize method that links {@link #b_controllers} object with correct View by id.
	* 
	* Also creates a new listener for this button's events 
	* by passing this {@link Activity} and id parameter 
	* to {@link ButtonsFocusChangeListener#ButtonsFocusChangeListener(Activity, int)} .
	* @param id ID of view in {@link com.android.multiplay.R}
	*/
	private void initB_controllers( int id ) {
		this.b_controllers = (ImageButton) super.findViewById(id);
		this.b_controllers.setOnFocusChangeListener(
		new ButtonsFocusChangeListener(this,id));
	}

	/** Initialize method that links {@link #b_resetdata} object with correct View by id.
	* 
	* Also creates a new listener for this button's events 
	* by passing this {@link Activity} and id parameter 
	* to {@link ButtonsFocusChangeListener#ButtonsFocusChangeListener(Activity, int)} .
	* @param id ID of view in {@link com.android.multiplay.R}
	*/
	private void initB_resetdata( int id ) {
		this.b_resetdata = (ImageButton) super.findViewById(id);
		this.b_resetdata.setOnFocusChangeListener(
		new ButtonsFocusChangeListener(this,id));
	}
	
	private void initB_goback( int id ) {
		this.b_goback = (ImageButton) super.findViewById(id);
		this.b_goback.setOnFocusChangeListener(
		new ButtonsFocusChangeListener(this,id));
	}

	
	
////////////////////DialogButtonClickListener's methods for dialog interaction
	
	
	
	public final static class DialogList {
	
		private static final String PACKAGE = "com.android.multiplay.optionsactivity";
		
		public static final String TAG_RESET_DB = PACKAGE + "RESET_DB";
		
		public static final int ID_TITLE_RESET_DB =
		R.string.dialog_ID_TITLE_RESET_DB;
		
		
		public static final int ID_MESSAGE_RESET_DB =
		R.string.dialog_ID_MESSAGE_RESET_DB;
	
	}
	
	@Override
	public void onDialogPositiveClick(DialogFragment dialog) {
		String dialogTag = dialog.getTag();

		if ( dialogTag.equals(OptionsActivity.DialogList.TAG_RESET_DB)) {
			MultiPlayApplication.getDbHelper().recreateDataBase();
			return;
		}
	}

	@Override
	public void onDialogNeutralClick(DialogFragment dialog) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDialogNegativeClick(DialogFragment dialog) {
		// TODO Auto-generated method stub
		
	}
}