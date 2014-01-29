package com.android.multiplay;


import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.android.application.MultiPlayApplication;
import com.android.controllers.help.movie.Movie_Activity_Help;
import com.android.database.MultiPlayDataBase;
import com.android.dialogs.AlertDialogs;
import com.android.dialogs.DialogButtonClickListener;
import com.android.dialogs.elements.DialogListCore;

public class HelpActivity extends Activity implements DialogButtonClickListener {

////////////////////Fields



/** Menu button.
* 
* It leaves to next activity: {@link MultiplayExplorerHelpActivity}.
* This button is not active if no wireless or bluetooth connections were found.
* On click it call method {@link #connections_OnClick(View)}.
*/
private ImageButton b_explorer = null;

/** Menu button.
* 
* It leaves to next activity: {@link SystemControllerHelpActivity}.
* This button is not active if no wireless or bluetooth connections were found.
* On click it call method {@link #connections_OnClick(View)}.
*/
private ImageButton b_controllers = null;

/** Menu button.
* 
* It leaves to next activity: {@link OptionsHelpActivity}.
* This button is not active if no wireless or bluetooth connections were found.
* On click it call method {@link #connections_OnClick(View)}.
*/
private ImageButton b_options = null;

private ImageButton b_goback = null;

////////////////////Methods


/**
 * @see android.app.Activity#onCreate(android.os.Bundle)
 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_help);
		
		initB_goback(R.id.b_activity_icon_back);
		
		initB_explorer(R.id.b_help_activity_explorer_icon);
		initB_controllers(R.id.b_help_activity_controllers_icon);
		initB_options(R.id.b_help_activity_options_icon);
		
		init();
	}
/**
 * @see android.app.Activity#onResume()
 */
	@Override
	protected void onResume() {
		super.onResume();
		Log.i("Main", "Resume");
		init();
	}
	/**
	 * @see android.app.Activity#onRestart()
	 */
	@Override
	protected void onRestart() {
		super.onRestart();
		init();
	}
	
/**
 * @see android.app.Activity#onDestroy()
 */
	@Override 
	protected void onDestroy() {
		super.onDestroy();
	}
	
	
	
//////////////////// Methods for onClick events

	
	/**
	 * 
	 * @param view
	 */
	public void explorer_help_OnClick( View view ) {
		Intent intent = new Intent(this, Movie_Activity_Help.class);
		super.startActivity(intent);
	}
	
	/**
	 * 
	 * @param view
	 */
	public void controllers_help_OnClick( View view ) {
		Intent intent = new Intent(this, SystemControllerHelpActivity.class);
		super.startActivity(intent);
	}
	/**
	 * 
	 * @param view
	 */
	public void options_help_OnClick( View view ) {
		Intent intent = new Intent(this, OptionsHelpActivity.class);
		super.startActivity(intent);
	}
	/**
	 * 
	 * @param view
	 */
	public void go_back_OnClick( View view ) {
		super.finish();
	}

	
	
////////////////////Methods for initialize objects



	/** Default initial method that constrains all tasks that should be done either {{@link #onCreate(Bundle)} or {{@link #onRestart()}.
	*  It requests back focus to unfocused any menu items.
	*/
	private void init() {
		b_goback.requestFocus();
	}

	/** Initialize method that links {@link #b_explorer} object with correct View by id.
	* 
	* Also creates a new listener for this button's events 
	* by passing this {@link Activity} and id parameter 
	* to {@link ButtonsFocusChangeListener#ButtonsFocusChangeListener(Activity, int)} .
	* @param id ID of view in {@link com.android.multiplay.R}
	*/
	private void initB_explorer( int id) {
		this.b_explorer = (ImageButton) super.findViewById(id);
		this.b_explorer.setOnFocusChangeListener(
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

	/** Initialize method that links {@link #b_options} object with correct View by id.
	* 
	* Also creates a new listener for this button's events 
	* by passing this {@link Activity} and id parameter 
	* to {@link ButtonsFocusChangeListener#ButtonsFocusChangeListener(Activity, int)} .
	* @param id ID of view in {@link com.android.multiplay.R}
	*/
	private void initB_options( int id ) {
		this.b_options = (ImageButton) super.findViewById(id);
		this.b_options.setOnFocusChangeListener(
		new ButtonsFocusChangeListener(this,id));
	}
	
	private void initB_goback( int id ) {
		this.b_goback = (ImageButton) super.findViewById(id);
		this.b_goback.setOnFocusChangeListener(
		new ButtonsFocusChangeListener(this,id));
	}

	
	
////////////////////DialogButtonClickListener's methods for dialog interaction
	
	
	
	public final static class DialogList {
	
		private static final String PACKAGE = "com.android.multiplay.helpactivity";
		
	}
	
	@Override
	public void onDialogPositiveClick(DialogFragment dialog) {
		// TODO Auto-generated method stub

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
