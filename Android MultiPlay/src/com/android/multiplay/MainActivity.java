package com.android.multiplay;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.android.extendedWidgets.ImageToogleButton;
import com.android.service.receivers.ConnectionServiceResponseReceiver;
import com.android.services.ConnectionService;

public class MainActivity extends Activity {

	private ImageButton b_multiplay_explorer = null;
	private ImageButton b_system_controller = null;
	private ImageButton b_help = null;
	private ImageButton b_options = null;
	
	private ImageToogleButton b_wireless_network_switch = null;
	private ImageToogleButton b_bluetooth_switch = null;
	
	private ConnectionServiceResponseReceiver connectionServiceResponseReceiver = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initB_multiplay_explorer(R.id.b_multiplay_explorer_icon);
		initB_system_controller(R.id.b_system_controller_icon);
		initB_help(R.id.b_help_icon);
		initB_options(R.id.b_options_icon);
		
		initB_bluetooth_switch(R.id.b_bluetooth_switch);
		initB_wireless_network_switch(R.id.b_wireless_network_switch);
		
		init();
		
		 IntentFilter filter = new IntentFilter(ConnectionServiceResponseReceiver.ACTION_RESP);
	        filter.addCategory(Intent.CATEGORY_DEFAULT);
	        connectionServiceResponseReceiver = new ConnectionServiceResponseReceiver(this);
	        registerReceiver(connectionServiceResponseReceiver, filter);
	        Log.i("OK", "reg");
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		init();
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
	
	public void toogleBluetooth_onClick( View view ) {
		b_bluetooth_switch.setBackgroundResource(R.drawable.main_activity_button_pending);
		b_bluetooth_switch.toogleButton();
	}
	
	/** Enable or disable wireless connection service on Android device.
	 * 
	 * Method runs {@link ConnectionService} that will handle all requested work in background.
	 * It will try to find previously used network (if any has been saved) and connect to id in background
	 * or just returns prompt that will inform user about no default wireless network, asking user
	 * what application should do next.
	 * 
	 * @param view Default parameter.
	 */
	public void toogleWirelessNetwork_onClick( View view ) {
		b_wireless_network_switch.setBackgroundResource(R.drawable.main_activity_button_pending);
		b_wireless_network_switch.toogleButton();
		Intent msgIntent = new Intent(this, ConnectionService.class);
		msgIntent.putExtra(ConnectionService.PARAM_IN_MSG, "TEST");
		
		
	        
		super.startService(msgIntent);
		Log.i("OK", "start");
	}
	
	
	
////////////////////Methods for init objects
	
	
	
	private void init() {
		super.findViewById(R.id.tv_title_of_selected_item).requestFocus();
	}
	
	/** Init method that links {@link #b_multiplay_explorer} object with correct View by id.
	 * 
	 * Also creates a new listener for this button's events 
	 * by passing this {@link Activity} and id parameter 
	 * to {@link MainActivityFocusChangeListener#MainActivityFocusChangeListener(Activity, int)} .
	 * @param id ID of view in {@link com.android.multiplay.R}
	 */
	private void initB_multiplay_explorer( int id) {
		this.b_multiplay_explorer = (ImageButton) super.findViewById(id);
		this.b_multiplay_explorer.setOnFocusChangeListener(
				new MainActivityFocusChangeListener(this,id));
	}
	
	/** Init method that links {@link #b_system_controller} object with correct View by id.
	 * 
	 * Also creates a new listener for this button's events 
	 * by passing this {@link Activity} and id parameter 
	 * to {@link MainActivityFocusChangeListener#MainActivityFocusChangeListener(Activity, int)} .
	 * @param id ID of view in {@link com.android.multiplay.R}
	 */
	private void initB_system_controller( int id ) {
		this.b_system_controller = (ImageButton) super.findViewById(id);
		this.b_system_controller.setOnFocusChangeListener(
				new MainActivityFocusChangeListener(this,id));
	}
	
	/** Init method that links {@link #b_help} object with correct View by id.
	 * 
	 * Also creates a new listener for this button's events 
	 * by passing this {@link Activity} and id parameter 
	 * to {@link MainActivityFocusChangeListener#MainActivityFocusChangeListener(Activity, int)} .
	 * @param id ID of view in {@link com.android.multiplay.R}
	 */
	private void initB_help( int id ) {
		this.b_help = (ImageButton) super.findViewById(id);
		this.b_help.setOnFocusChangeListener(
				new MainActivityFocusChangeListener(this,id));
	}
	
	/** Init method that links {@link #b_options} object with correct View by id.
	 * 
	 * Also creates a new listener for this button's events 
	 * by passing this {@link Activity} and id parameter 
	 * to {@link MainActivityFocusChangeListener#MainActivityFocusChangeListener(Activity, int)} .
	 * @param id ID of view in {@link com.android.multiplay.R}
	 */
	private void initB_options( int id ) {
		this.b_options = (ImageButton) super.findViewById(id);
		this.b_options.setOnFocusChangeListener(
				new MainActivityFocusChangeListener(this,id));
	}
	
	private void initB_wireless_network_switch( int id ) {
		this.b_wireless_network_switch = (ImageToogleButton) super.findViewById(id);
	}

	private void initB_bluetooth_switch( int id ) {
		this.b_bluetooth_switch = (ImageToogleButton) super.findViewById(id);
	}
}