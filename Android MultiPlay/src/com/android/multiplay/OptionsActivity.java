package com.android.multiplay;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

public class OptionsActivity extends Activity {

//	private EditText IP;
//	private Button nextButton, search;

	// TODO JAVADOC
	
////////////////////Fields



/** Menu button.
* 
* It leaves to next activity: {@link MultiplayExplorerActivity}.
* This button is not active if no wireless or bluetooth connections were found.
* On click it call method {@link #multiplay_explorer_OnClick(View)}.
*/
private ImageButton b_connections = null;

/** Menu button.
* 
* It leaves to next activity: {@link SystemControllerActivity}.
* This button is not active if no wireless or bluetooth connections were found.
* On click it call method {@link #system_controller_OnClick(View)}.
*/
private ImageButton b_controllers = null;

/** Menu button.
* 
* It leaves to next activity: {@link HelpActivity}.
* On click it call method {@link #help_OnClick(View)}.
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
		
		initB_goback(0);
		
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
		
	}
	
	public void connections_OnClick( View view ) {
			
		}
	
	public void resetdata_OnClick( View view ) {
		
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
	private void initB_connecions( int id) {
		this.b_connections = (ImageButton) super.findViewById(id);
		this.b_connections.setOnFocusChangeListener(
		new OptionsFocusChangeListener(this,id));
	}

	/** Initialize method that links {@link #b_system_controller} object with correct View by id.
	* 
	* Also creates a new listener for this button's events 
	* by passing this {@link Activity} and id parameter 
	* to {@link OptionsFocusChangeListener#MainActivityFocusChangeListener(Activity, int)} .
	* @param id ID of view in {@link com.android.multiplay.R}
	*/
	private void initB_controllers( int id ) {
		this.b_controllers = (ImageButton) super.findViewById(id);
		this.b_controllers.setOnFocusChangeListener(
		new OptionsFocusChangeListener(this,id));
	}

	/** Initialize method that links {@link #b_help} object with correct View by id.
	* 
	* Also creates a new listener for this button's events 
	* by passing this {@link Activity} and id parameter 
	* to {@link OptionsFocusChangeListener#MainActivityFocusChangeListener(Activity, int)} .
	* @param id ID of view in {@link com.android.multiplay.R}
	*/
	private void initB_resetdata( int id ) {
		this.b_resetdata = (ImageButton) super.findViewById(id);
		this.b_resetdata.setOnFocusChangeListener(
		new OptionsFocusChangeListener(this,id));
	}
	
	private void initB_goback( int id ) {
		this.b_goback = (ImageButton) super.findViewById(id);
		this.b_goback.setOnFocusChangeListener(
		new OptionsFocusChangeListener(this,id));
	}

	
//	public void onClick(View arg0) {
//		switch (arg0.getId()) {
//		case R.id.imageButton1:
//			if (IP.getText().toString().equals("")) {
//				Toast.makeText(this, "Brak IP ", Toast.LENGTH_LONG).show();
//			} else {
//				Intent intent = new Intent(this, FirstMenu.class);
//				intent.putExtra("ip", IP.getText().toString());
//				super.startActivity(intent);
//			}
//			break;
//		case R.id.search:
//			Intent intent = new Intent(this, FirstMenu.class);
//			
//			super.startActivity(intent);
//			break;
//		}
//	}

	

}