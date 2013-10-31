package com.android.multiplay;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.android.extendedWidgets.ImageToggleButton;
import com.android.service.receivers.ConnectionServiceResponseReceiver;
import com.android.services.ConnectionService;

public class MainActivity extends Activity {

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
	
	
	
	/** Switch button that enables or disables wireless network Android service.
	 * 
	 * On click it call method {@link #toggleWirelessNetwork_onClick(View)}.
	 */
	private ImageToggleButton b_wireless_network_switch = null;
	
	/** Switch button that enables or disables wireless network Android service.
	 * 
	 * On click it call method {@link #toggleBluetooth_onClick(View)}.
	 */
	private ImageToggleButton b_bluetooth_switch = null;
	
	
	
	/** Receiver for {@link ConnectionService}.
	 * 
	 * Object receives data from {@link ConnectionService} that invokes some actions on this activity.
	 * In this case receiver will call pop-ups on connection established.
	 * 
	 * @See {@link #b_wireless_network_switch}
	 * @See {{@link #b_bluetooth_switch}
	 */
	private ConnectionServiceResponseReceiver connectionServiceResponseReceiver = null;
	
	/** Filter for {{@link #connectionServiceResponseReceiver}.
	 * 
	 * Filter that prevents this activity receiver for receiving any other 
	 * {@link IntentService}'s data, sending back on {@link IntentService#sendBroadcast(Intent)} 
	 * except {@link Intent} passes that flirt.
	 */
	private IntentFilter intentFilter = null;
	
	
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

		initConnectionCheck();
	}


	@Override
	protected void onRestart() {
		super.onRestart();
		init();
	}

	@Override 
	protected void onDestroy() {
		super.onDestroy();
		super.unregisterReceiver(connectionServiceResponseReceiver);
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
	
	/** Enable or disable bluetooth connection service on Android device.
	 * 
	 * Method runs {@link ConnectionService} that will handle all requested work in background.
	 * It will try to find previously used bluetooth connection (if any has been saved) and connect to id in background
	 * or just returns prompt that will inform user about no default wireless network, asking user
	 * what application should do next.
	 * 
	 * @param view Default parameter.
	 */
	public void toggleBluetooth_onClick( View view ) {
		b_bluetooth_switch.setBackgroundResource(R.drawable.main_activity_button_pending);
		b_bluetooth_switch.toggleButton();
		sendToggleBluetoothIntentToService(b_bluetooth_switch.isToggle());
		Log.i("OK", "click");
	}
	
	/** Prepares {@link Intent} and passes it to new service triggered by {@link #startService(Intent)} method.
	 * 
	 * Newly created {@link Intent} has two extras: <br>
	 * <ol>
	 * 	<li> {@link ConnectionService#INPUT_DATA_CALLED_REASON} - 
	 * 		Integer value that defines a behavior of {@link ConnectionService} that will be called.</li>
	 * 	<li> {@link ConnectionService#INPUT_DATA_CONNECTION_SWITH} - 
	 * 		boolean value which is true when user switches button from OFF to ON, false otherwise.</li>
	 * </ol>
	 * @param toggle ON or OFF boolean status of {@link #b_bluetooth_switch} button after tapped. 
	 * 		Gives a value for {@link ConnectionService#INPUT_DATA_CONNECTION_SWITH} extra.
	 */
	private void sendToggleBluetoothIntentToService( boolean toggle ) {
		Intent outputIntent = new Intent(this, ConnectionService.class);
		outputIntent.putExtra(ConnectionService.INPUT_DATA_CALLED_REASON, ConnectionService.BLUETOOTH);
		outputIntent.putExtra(ConnectionService.INPUT_DATA_CONNECTION_SWITH, toggle);
		outputIntent.addCategory(Intent.CATEGORY_DEFAULT);
		super.startService(outputIntent);
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
	public void toggleWirelessNetwork_onClick( View view ) {
		b_wireless_network_switch.setBackgroundResource(R.drawable.main_activity_button_pending);
		b_wireless_network_switch.toggleButton();
		sendToggleWirelessNetworkSwitchIntentToService(b_wireless_network_switch.isToggle());
		Log.i("OK", "start");
	}
	
	/** Prepares {@link Intent} and passes it to new service triggered by {@link #startService(Intent)} method.
	 * 
	 * Newly created {@link Intent} has two extras: <br>
	 * <ol>
	 * 	<li> {@link ConnectionService#INPUT_DATA_CONNECTION_SELECT} - 
	 * 		boolean value which is false when this method was triggered by taping
	 * 		{@link #b_wireless_network_switch} to enable or disable wireless network service
	 * 		or false when button {@link #b_wireless_network_switch} was tapped 
	 * 		and {@link #toggleWirelessNetwork_onClick(View)} was called.</li>
	 * 	<li> {@link ConnectionService#INPUT_DATA_CONNECTION_SWITH} - 
	 * 		boolean value which is true when user switches button from OFF to ON, false otherwise.</li>
	 * </ol>
	 * @param toggle ON or OFF boolean status of {@link #b_wireless_network_switch} button after tapped. 
	 * 		Gives a value for {@link ConnectionService#INPUT_DATA_CONNECTION_SWITH} extra.
	 */
	private void sendToggleWirelessNetworkSwitchIntentToService( boolean toggle ) {
		Intent outputIntent = new Intent(this, ConnectionService.class);
		outputIntent.putExtra(ConnectionService.INPUT_DATA_CALLED_REASON, ConnectionService.WIFI);
		outputIntent.putExtra(ConnectionService.INPUT_DATA_CONNECTION_SWITH, toggle);
		outputIntent.addCategory(Intent.CATEGORY_DEFAULT);
		super.startService(outputIntent);
	}
	
	
	
////////////////////Methods for initialize objects
	
	
	
	/** Default initial method that constrains all tasks that should be done either {{@link #onCreate(Bundle)} or {{@link #onRestart()}.
	 *  It requests back focus to unfocused any menu items.
	 */
	private void init() {
		super.findViewById(R.id.tv_title_of_selected_item).requestFocus();
	}
	
	private void initConnectionCheck() {
		intentFilter = new IntentFilter(ConnectionServiceResponseReceiver.ACTION_RESP);
		intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
	    connectionServiceResponseReceiver = new ConnectionServiceResponseReceiver(this);
	    super.registerReceiver(connectionServiceResponseReceiver, intentFilter);
	    
	    Intent outputIntent = new Intent(this, ConnectionService.class);
		outputIntent.addCategory(Intent.CATEGORY_DEFAULT);
		outputIntent.putExtra(ConnectionService.INPUT_DATA_CALLED_REASON, ConnectionService.INIT);
		super.startService(outputIntent);
	}
	
	/** Initialize method that links {@link #b_multiplay_explorer} object with correct View by id.
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
	
	/** Initialize method that links {@link #b_system_controller} object with correct View by id.
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
	
	/** Initialize method that links {@link #b_help} object with correct View by id.
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
	
	/** Initialize method that links {@link #b_options} object with correct View by id.
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
	
	/** Initialize method that links {@link #b_wireless_network_switch} object with correct View by id.
	 * @param id ID of view in {@link com.android.multiplay.R}
	 */
	private void initB_wireless_network_switch( int id ) {
		this.b_wireless_network_switch = (ImageToggleButton) super.findViewById(id);
	}

	/** Initialize method that links {@link #b_bluetooth_switch} object with correct View by id.
	 * @param id ID of view in {@link com.android.multiplay.R}
	 */
	private void initB_bluetooth_switch( int id ) {
		this.b_bluetooth_switch = (ImageToggleButton) super.findViewById(id);
	}
}