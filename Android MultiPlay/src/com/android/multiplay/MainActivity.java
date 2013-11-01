package com.android.multiplay;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.dialogs.DialogButtonClickListener;
import com.android.dialogs.mainactivity.MainActivityDialogList;

public class MainActivity extends Activity implements DialogButtonClickListener {

	
	
////////////////////Fields
	
	
	
	/** Menu button.
	 * 
	 * It leaves to next activity: {@link MultiplayExplorerActivity}.
	 * This button is not active if no wireless or bluetooth connections were found.
	 * On click it call method {@link #multiplay_explorer_OnClick(View)}.
	 */
	private ImageButton b_multiplay_explorer = null;
	
	/** Menu button.
	 * 
	 * It leaves to next activity: {@link SystemControllerActivity}.
	 * This button is not active if no wireless or bluetooth connections were found.
	 * On click it call method {@link #system_controller_OnClick(View)}.
	 */
	private ImageButton b_system_controller = null;
	
	/** Menu button.
	 * 
	 * It leaves to next activity: {@link HelpActivity}.
	 * On click it call method {@link #help_OnClick(View)}.
	 */
	private ImageButton b_help = null;
	
	/** Menu button.
	 * 
	 * It leaves to next activity: {@link OptionsActivity}.
	 * On click it call method {@link #options_OnClick(View)}.
	 */
	private ImageButton b_options = null;

	
	
////////////////////Methods
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initB_multiplay_explorer(R.id.b_main_activity_multiplay_explorer_icon);
		initB_system_controller(R.id.b_main_activity_system_controller_icon);
		initB_help(R.id.b_main_activity_help_icon);
		initB_options(R.id.b_main_activity_options_icon);
		
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

	
	
	public void multiplay_explorer_OnClick( View view ) {
		Intent intent = new Intent(this, MultiplayExplorerActivity.class);
		super.startActivity(intent);
	}
	
	public void system_controller_OnClick( View view ) {
		Intent intent = new Intent(this, SystemControllerActivity.class);
		super.startActivity(intent);
	}
	
	public void help_OnClick( View view ) {
		Intent intent = new Intent(this, HelpActivity.class);
		super.startActivity(intent);
	}
	
	public void options_OnClick( View view ) {
		Intent intent = new Intent(this, OptionsActivity.class);
		super.startActivity(intent);
	}
	
	
	
////////////////////DialogButtonClickListener's methods for dialog interaction
	
	
		
	@Override
	public void onDialogPositiveClick(DialogFragment dialog) {
		String dialogTag = dialog.getTag();

		if ( dialogTag.equals(MainActivityDialogList.TAG_BLUETOOTH_CONNECTED)) {
			Toast.makeText(this, "OKKKKKKKKKKK",Toast.LENGTH_LONG).show();
			return;
		}
		
		if ( dialogTag.equals(MainActivityDialogList.TAG_BLUETOOTH_ENABLED)) {
			
			return;
		}
		
		if ( dialogTag.equals(MainActivityDialogList.TAG_WIFI_CONNECTED)) {
			
			return;
		}
		
		if ( dialogTag.equals(MainActivityDialogList.TAG_WIFI_ENABLED)) {
			
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

	
	
////////////////////Methods for initialize objects
	
	
	
	/** Default initial method that constrains all tasks that should be done either {{@link #onCreate(Bundle)} or {{@link #onRestart()}.
	 *  It requests back focus to unfocused any menu items.
	 */
	private void init() {
		super.findViewById(R.id.tv_title_of_selected_item).requestFocus();
	}
	
	/** Initialize method that links {@link #b_multiplay_explorer} object with correct View by id.
	 * 
	 * Also creates a new listener for this button's events 
	 * by passing this {@link Activity} and id parameter 
	 * to {@link OptionsFocusChangeListener#MainActivityFocusChangeListener(Activity, int)} .
	 * @param id ID of view in {@link com.android.multiplay.R}
	 */
	private void initB_multiplay_explorer( int id) {
		this.b_multiplay_explorer = (ImageButton) super.findViewById(id);
		this.b_multiplay_explorer.setOnFocusChangeListener(
				new OptionsFocusChangeListener(this,id));
	}
	
	/** Initialize method that links {@link #b_system_controller} object with correct View by id.
	 * 
	 * Also creates a new listener for this button's events 
	 * by passing this {@link Activity} and id parameter 
	 * to {@link OptionsFocusChangeListener#MainActivityFocusChangeListener(Activity, int)} .
	 * @param id ID of view in {@link com.android.multiplay.R}
	 */
	private void initB_system_controller( int id ) {
		this.b_system_controller = (ImageButton) super.findViewById(id);
		this.b_system_controller.setOnFocusChangeListener(
				new OptionsFocusChangeListener(this,id));
	}
	
	/** Initialize method that links {@link #b_help} object with correct View by id.
	 * 
	 * Also creates a new listener for this button's events 
	 * by passing this {@link Activity} and id parameter 
	 * to {@link OptionsFocusChangeListener#MainActivityFocusChangeListener(Activity, int)} .
	 * @param id ID of view in {@link com.android.multiplay.R}
	 */
	private void initB_help( int id ) {
		this.b_help = (ImageButton) super.findViewById(id);
		this.b_help.setOnFocusChangeListener(
				new OptionsFocusChangeListener(this,id));
	}
	
	/** Initialize method that links {@link #b_options} object with correct View by id.
	 * 
	 * Also creates a new listener for this button's events 
	 * by passing this {@link Activity} and id parameter 
	 * to {@link OptionsFocusChangeListener#MainActivityFocusChangeListener(Activity, int)} .
	 * @param id ID of view in {@link com.android.multiplay.R}
	 */
	private void initB_options( int id ) {
		this.b_options = (ImageButton) super.findViewById(id);
		this.b_options.setOnFocusChangeListener(
				new OptionsFocusChangeListener(this,id));
	}
}