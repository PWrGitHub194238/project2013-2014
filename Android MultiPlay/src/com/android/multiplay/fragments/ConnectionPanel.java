package com.android.multiplay.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.IntentService;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.android.extendedWidgets.ImageToggleButton;
import com.android.multiplay.R;
import com.android.service.receivers.ConnectionServiceResponseReceiver;
import com.android.services.ConnectionService;

/**
 * 
 * @author tomasz
 *
 */
public class ConnectionPanel extends Fragment implements OnClickListener {

	
	
////////////////////Fields



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
	
	/** Root activity for this fragment.
	 * 
	 */
	private Activity activity = null;

	
	
////////////////////Methods



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    	
    	Log.i("Fragment", "CREATE");
		
        return inflater.inflate(R.layout.connection_panel, container, false);
    }
    
    @Override
    public void onResume() {
    	super.onResume();
    	Log.i("Fragment", "ONRESUME");
    	initContext();
    	
		initB_bluetooth_switch(R.id.b_bluetooth_switch);
		initB_wireless_network_switch(R.id.b_wireless_network_switch);
		initConnectionCheck();
    }
    
    @Override
	public void onPause() {
    	super.onPause();
    	activity.unregisterReceiver(connectionServiceResponseReceiver);
    }

    @Override
    public void onStop() {
    	super.onStop();
    }

	@Override
	public void onDestroy() {
		super.onDestroy();
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
		Log.i("Fragment", "B ON CLICK");
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
		Intent outputIntent = new Intent(activity, ConnectionService.class);
		outputIntent.putExtra(ConnectionService.INPUT_DATA_CALLED_REASON, ConnectionService.BLUETOOTH);
		outputIntent.putExtra(ConnectionService.INPUT_DATA_CONNECTION_SWITH, toggle);
		outputIntent.addCategory(Intent.CATEGORY_DEFAULT);
		activity.startService(outputIntent);
		Log.i("Fragment", "B START INTENT");

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
		Intent outputIntent = new Intent(activity, ConnectionService.class);
		outputIntent.putExtra(ConnectionService.INPUT_DATA_CALLED_REASON, ConnectionService.WIFI);
		outputIntent.putExtra(ConnectionService.INPUT_DATA_CONNECTION_SWITH, toggle);
		outputIntent.addCategory(Intent.CATEGORY_DEFAULT);
		activity.startService(outputIntent);
	}
	
	
	private void initConnectionCheck() {
		intentFilter = new IntentFilter(ConnectionServiceResponseReceiver.ACTION_RESP);
		intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
	    connectionServiceResponseReceiver = new ConnectionServiceResponseReceiver(activity);
	    activity.registerReceiver(connectionServiceResponseReceiver, intentFilter);
	    
	    Intent outputIntent = new Intent(activity, ConnectionService.class);
		outputIntent.addCategory(Intent.CATEGORY_DEFAULT);
		outputIntent.putExtra(ConnectionService.INPUT_DATA_CALLED_REASON, ConnectionService.INIT);
		Log.i("Fragment", "START SERVICE");
		activity.startService(outputIntent);
	}
	
	
	/** Initialize method that links {@link #b_wireless_network_switch} object with correct View by id.
	 * 
	 * Also sets to it {@link OnClickListener} instead of XML property android:onClick
	 * to avoid application for looking onClick method in fragment's root activity.
	 * 
	 * @param id ID of view in {@link com.android.multiplay.R}
	 */
	private void initB_wireless_network_switch( int id ) {
		this.b_wireless_network_switch = (ImageToggleButton) activity.findViewById(id);
		this.b_wireless_network_switch.setOnClickListener(this);
	}

	/** Initialize method that links {@link #b_bluetooth_switch} object with correct View by id.
	 *
	 * Also sets to it {@link OnClickListener} instead of XML property android:onClick
	 * to avoid application for looking onClick method in fragment's root activity.
	 * 
	 * @param id ID of view in {@link com.android.multiplay.R}
	 */
	private void initB_bluetooth_switch( int id ) {
		this.b_bluetooth_switch = (ImageToggleButton) activity.findViewById(id);
		this.b_bluetooth_switch.setOnClickListener(this);
	}
	
	private void initContext() {
		activity = this.getActivity();
	}

	@Override
	public void onClick(View v) {
	switch (v.getId()) {
	case R.id.b_bluetooth_switch:
		toggleBluetooth_onClick(v);
		break;
	case R.id.b_wireless_network_switch:
		toggleWirelessNetwork_onClick(v);
		break;
	}
		
	}
	
}