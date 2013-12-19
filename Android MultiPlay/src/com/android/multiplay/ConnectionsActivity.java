package com.android.multiplay;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.UUID;

import android.app.Activity;
import android.app.DialogFragment;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.WifiP2pManager.Channel;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.application.BluetoothConfigurationClass;
import com.android.application.ConnectionsConfigurationClass;
import com.android.application.MultiPlayApplication;
import com.android.application.WirelessConfigurationClass;
import com.android.asychs.GenerateConnectionList;
import com.android.dialogs.AddConnectionDialog;
import com.android.dialogs.AlertDialogs;
import com.android.dialogs.DialogButtonClickListener;
import com.android.dialogs.elements.DialogListCore;
import com.android.dialogs.elements.OptionsActivityDialogList;
import com.android.extendedWidgets.ImageToggleButton;
import com.android.extendedWidgets.lists.ElementOfConnectionsList;
import com.android.extendedWidgets.lists.ListOfConections;
import com.android.service.receivers.WiFiDirectBroadcastReceiver;
import com.android.services.ConnectionHelper;

public class ConnectionsActivity extends Activity implements OnItemClickListener, OnItemLongClickListener, OnClickListener, OnLongClickListener, DialogButtonClickListener {

	RelativeLayout rl_connections_activity_device_list_background_layout = null;
	
	ProgressBar pb_connections_activity_refreshing = null;
	
	ListView lv_connections_activity_device_list = null;
	
	ImageToggleButton b_connections_activity_bluetooth_switcher = null;
			
	ImageToggleButton b_connections_activity_wireless_switcher = null;
	
	ImageButton b_connections_activity_add_new_connection = null;
	
	ImageButton b_connections_activity_refresh_connections_check = null;
	
	ConnectionsConfigurationClass selectedConfig = null;
	
	Collection<ElementOfConnectionsList> listOfElements = null;
	boolean lv_connections_activity_device_list_visibly = false;
	
	WifiP2pManager mManager = null;
	Channel mChannel = null;
	BroadcastReceiver mReceiver = null;
	
	BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
	private static int REQUEST_ENABLE_BT = 1;

	
	IntentFilter mIntentFilter = null;
	
	String tag = "Connections";
	int count = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_connections);
		
		initRl_connections_activity_device_list_background_layout(R.id.rl_connections_activity_device_list_background_layout);
		initLv_connections_activity_device_list(R.id.lv_connections_activity_device_list);
		initB_connections_activity_bluetooth_switcher(R.id.b_connections_activity_bluetooth_switcher);
		initB_connections_activity_wireless_switcher(R.id.b_connections_activity_wireless_switcher);
		initB_connections_activity_add_new_connection(R.id.b_connections_activity_add_new_connection);
		initB_connections_activity_refresh_connections_check(R.id.b_connections_activity_refresh_connections_check);
		
		init();	
	}

	/* register the broadcast receiver with the intent values to be matched */
	@Override
	protected void onResume() {
	    super.onResume();
	    registerReceiver(mReceiver, mIntentFilter);
	}
	
	/* unregister the broadcast receiver */
	@Override
	protected void onPause() {
	    super.onPause();
	    unregisterReceiver(mReceiver);
	}
	
	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int id,
			long arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int id, long arg3) {
		Log.d("ListView","Click index: "+id);
		selectedConfig = MultiPlayApplication.getDiscoveredWirelessDevices().get(id);
		AlertDialogs.showDialog(this,
				OptionsActivityDialogList.TAG_CONNECT_CONFIRMATION,
				OptionsActivityDialogList.IT_TITLE_ICON_WIFI,
				OptionsActivityDialogList.ID_TITLE_CONNECT_CONFIRMATION,
				DialogListCore.ID_MESSAGE_NO_MESSAGE,
				DialogListCore.ID_BUTTON_CONNECT,
				null,
				DialogListCore.ID_BUTTON_CANCEL);
	}

	@Override
	public boolean onLongClick(View v) {
		switch(v.getId()) {
			case R.id.b_connections_activity_bluetooth_switcher:
				if (mBluetoothAdapter == null) {
				    // Device does not support Bluetooth
				} else {

				}
				break;
			case R.id.b_connections_activity_wireless_switcher:
		        // Do Button Two Action
		        break;
		    case R.id.b_connections_activity_add_new_connection:
		        // Do Button Two Action
		        break;
		    case R.id.b_connections_activity_refresh_connections_check:
		        // Do Button Two Action
		        break;
		}
		return false;
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.b_connections_activity_bluetooth_switcher:
			if (mBluetoothAdapter == null) {
			    // Device does not support Bluetooth
			} else {
				bluetooth_switcher_onClick(v);
				toogleBluetoothSwither();
			}
			break;
		case R.id.b_connections_activity_wireless_switcher:
			wireless_switcher_onClick(v);
			toogleWirelessSwither();
	        // Do Button Two Action
	        break;
	    case R.id.b_connections_activity_add_new_connection:
	    	new_connection_onClick();
	        break;
	    case R.id.b_connections_activity_refresh_connections_check:
	        refresh_connections_onClick(v);
	        break;
	}
	return;
}

	private void new_connection_onClick() {
		AddConnectionDialog.showDialog(
				  this,
				  OptionsActivityDialogList.TAG_ADD_CONNECTION,
				  OptionsActivityDialogList.IT_TITLE_ICON_CONNECTION_CREATOR,
				  OptionsActivityDialogList.ID_TITLE_ADD_CONNECTION,
				  AddConnectionDialog.getViewFromResource(this,R.layout.dialog_add_new_connection),
				  DialogListCore.ID_BUTTON_ADD,
				  DialogListCore.ID_BUTTON_EMPTY,
				  DialogListCore.ID_BUTTON_CANCEL);
		
	}



	private void bluetooth_switcher_onClick(View v) {
		Log.d("connections","BT_CLICKED");
		if (!mBluetoothAdapter.isEnabled()) {
			Log.d("connections","BT IS NOT ENABLED");
		    startActivityForResult(
		    		new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE), 
		    		REQUEST_ENABLE_BT);
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == REQUEST_ENABLE_BT) {
			if (resultCode ==  RESULT_OK) {
				Log.d("connections","BT IS OK");
				new GenerateConnectionList(listOfElements,pb_connections_activity_refreshing).execute();
			} else {
				
			}
		}
	}



	private void wireless_switcher_onClick(View v) {
		mManager.discoverPeers(mChannel, new WifiP2pManager.ActionListener() {
		    @Override
		    public void onSuccess() {
		    	Log.d("Connections", "OK");
		    }

		    @Override
		    public void onFailure(int reason) {
                count++;

                String err=new String();
                if(reason==WifiP2pManager.BUSY) err="BUSY";
                if(reason==WifiP2pManager.ERROR)err="ERROR";
                if(reason==WifiP2pManager.P2P_UNSUPPORTED) err="P2P_UNSUPPORTED";
                Log.d(tag,"FAIL - couldnt start to discover peers code: "+err+" ("+count+")");
                if(count>=20)return;
                mManager.discoverPeers(mChannel, this);
            }
		});
	}
	
	private void refresh_connections_onClick(View v) {
		if (lv_connections_activity_device_list_visibly == false) {
			int index = 0;
			int childrenCounter = rl_connections_activity_device_list_background_layout.getChildCount();
			for ( index = 0; index < childrenCounter; index += 1 ) {
				rl_connections_activity_device_list_background_layout.getChildAt(index).setVisibility(RelativeLayout.INVISIBLE);
			}
			pb_connections_activity_refreshing.setVisibility(ProgressBar.VISIBLE);
			
			refreshList(true, false);
			 
			ListOfConections adapter_listy = new ListOfConections(this, listOfElements);
			lv_connections_activity_device_list.setAdapter(adapter_listy);
			rl_connections_activity_device_list_background_layout.setVisibility(RelativeLayout.INVISIBLE);
			lv_connections_activity_device_list.setVisibility(ListView.VISIBLE);
			
		}
	}
	
	
	private void toogleBluetoothSwither() {
		b_connections_activity_bluetooth_switcher.toggleButton();
		b_connections_activity_bluetooth_switcher.setBackgroundResource(
				b_connections_activity_bluetooth_switcher.getDrawableId());
	}

	private void toogleWirelessSwither() {
		b_connections_activity_wireless_switcher.toggleButton();
		b_connections_activity_wireless_switcher.setBackgroundResource(
				b_connections_activity_wireless_switcher.getDrawableId());
	}
	
////////////////////Methods for initialize objects



	/**
	 * 
	 */
	private void init() {
		mManager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
	    mChannel = mManager.initialize(this, getMainLooper(), null);
	    mReceiver = new WiFiDirectBroadcastReceiver(mManager, mChannel, this);
	    
	    mIntentFilter = new IntentFilter();
	    mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
	    mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
	    mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
	    mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);
	}
	
	private void initRl_connections_activity_device_list_background_layout(int id) {
		this.rl_connections_activity_device_list_background_layout = (RelativeLayout) super.findViewById(id);
		this.pb_connections_activity_refreshing = (ProgressBar) rl_connections_activity_device_list_background_layout.findViewById(R.id.pb_connections_activity_refreshing);
	}
	
	private void initLv_connections_activity_device_list(int id) {
		Log.d("ListView","OK");
		this.lv_connections_activity_device_list = (ListView) super.findViewById(id);
		this.lv_connections_activity_device_list.setVisibility(
				ListView.INVISIBLE);
		this.lv_connections_activity_device_list.setOnItemClickListener(this);
		this.lv_connections_activity_device_list.setOnItemLongClickListener(this);
		this.listOfElements = new ArrayList<ElementOfConnectionsList>();
		Log.d("ListView","OK");
	}

	private void initB_connections_activity_bluetooth_switcher(int id) {
		this.b_connections_activity_bluetooth_switcher = (ImageToggleButton) super.findViewById(id);
		this.b_connections_activity_bluetooth_switcher.setOnClickListener(this);
		this.b_connections_activity_bluetooth_switcher.setOnLongClickListener(this);
		if (mBluetoothAdapter == null) {

		} else {
			this.b_connections_activity_bluetooth_switcher.setToggled_on_drawable_id(
					R.drawable.connections_activity_button_bt_on);
			this.b_connections_activity_bluetooth_switcher.setToggled_off_drawable_id(
					R.drawable.connections_activity_button_bt_off);
		}
	}

	private void initB_connections_activity_wireless_switcher(int id) {
		this.b_connections_activity_wireless_switcher = (ImageToggleButton) super.findViewById(id);
		this.b_connections_activity_wireless_switcher.setOnClickListener(this);
		this.b_connections_activity_wireless_switcher.setOnLongClickListener(this);
	}

	private void initB_connections_activity_add_new_connection(int id) {
		this.b_connections_activity_add_new_connection = (ImageButton) super.findViewById(id);
		this.b_connections_activity_add_new_connection.setOnClickListener(this);
		this.b_connections_activity_add_new_connection.setOnLongClickListener(this);
	}

	private void initB_connections_activity_refresh_connections_check(int id) {
		this.b_connections_activity_refresh_connections_check = (ImageButton) super.findViewById(id);
		this.b_connections_activity_refresh_connections_check.setOnClickListener(this);
		this.b_connections_activity_refresh_connections_check.setOnLongClickListener(this);
	}



	@Override
	public void onDialogPositiveClick(DialogFragment dialog) {
		String dialogTag = dialog.getTag();

		if ( dialogTag.equals(OptionsActivityDialogList.TAG_ADD_CONNECTION)) {
			Toast.makeText(this, "OKKKKKKKKKKK",Toast.LENGTH_LONG).show();

			AddConnectionDialog d = (AddConnectionDialog) dialog;
			Log.d("Connections","Name-> "+d.getReturnedData().get(AddConnectionDialog.DEVICE_NAME));

			if (d.isS_connections_activity_connection_type_switch_state() == ConnectionHelper.CONNECTION_TYPE_WIFI ) {
				
				Log.d("Connections","IP-> "+d.getReturnedData().get(AddConnectionDialog.DEVICE_IP));
				Log.d("Connections","Port-> "+d.getReturnedData().get(AddConnectionDialog.DEVICE_PORT));
				ConnectionsConfigurationClass config = new WirelessConfigurationClass(
						d.getReturnedData().get(AddConnectionDialog.DEVICE_IP),
						Integer.valueOf(d.getReturnedData().get(AddConnectionDialog.DEVICE_PORT)));
				
				config.setConnectionStatus(ConnectionHelper.STATUS_NOT_IN_RANGE);
				config.setName(d.getReturnedData().get(AddConnectionDialog.DEVICE_NAME));
				config.setStored(
						(d.getReturnedData().get(AddConnectionDialog.DEVICE_IS_STORED).contentEquals("TRUE"))?true:false);
				
				ConnectionHelper.insertNewConnectionToList(ConnectionHelper.CONNECTION_TYPE_WIFI, config);
			} else {
				
				Log.d("Connections","UUID-> "+d.getReturnedData().get(AddConnectionDialog.DEVICE_UUID));
				Log.d("Connections","MAC-> "+d.getReturnedData().get(AddConnectionDialog.DEVICE_MAC));
				ConnectionsConfigurationClass config = new BluetoothConfigurationClass(
						UUID.fromString(d.getReturnedData().get(AddConnectionDialog.DEVICE_UUID)),
						d.getReturnedData().get(AddConnectionDialog.DEVICE_MAC));
				
				config.setName(d.getReturnedData().get(AddConnectionDialog.DEVICE_NAME));
				config.setStored(
						(d.getReturnedData().get(AddConnectionDialog.DEVICE_IS_STORED).contentEquals("TRUE"))?true:false);
				if (AddConnectionDialog.DEVICE_IS_STORED.contentEquals("TRUE")) {
					config.setStoredIndex(1);
				}
				
				ConnectionHelper.insertNewConnectionToList(ConnectionHelper.CONNECTION_TYPE_BT, config);
				
				
			}
			return;
		} else if (dialogTag.equals(OptionsActivityDialogList.TAG_CONNECT_CONFIRMATION)) {
			Toast.makeText(this, "Make connection",Toast.LENGTH_LONG).show();
			MultiPlayApplication.setSetMainConfiguration(selectedConfig);
			//TODO
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
	
	public void refreshList(boolean isWirelessOn, boolean isBluetoothOn) {
		listOfElements.clear();
		if ( isWirelessOn == true ) {
			Log.d("Connections","Refresh list");
			refreshWirelessList();
		}
		if ( isBluetoothOn == true ) {

		}
	}
	
	public void refreshWirelessList() {
		Iterator<WirelessConfigurationClass> wirelessConfigInterator = MultiPlayApplication.getDiscoveredWirelessDevices().iterator();
		WirelessConfigurationClass configuration = null;
		boolean isConfigurationStored = false;
		while (wirelessConfigInterator.hasNext()) {
			configuration = wirelessConfigInterator.next();
			if (configuration.getStoredIndex() != -1) {
				isConfigurationStored = ElementOfConnectionsList.STORED_YES;
			} else {
				isConfigurationStored = ElementOfConnectionsList.STORED_NO;
			}
			isConfigurationStored = 
			listOfElements.add(new ElementOfConnectionsList(
					configuration.getName(), configuration.getIP(),
					configuration.getConnectionStatus(),
					isConfigurationStored,
					ConnectionHelper.CONNECTION_TYPE_WIFI));
		}
	}
	
	public void refreshBluetoothList() {
		Iterator<BluetoothConfigurationClass> bluetoothConfigInterator = MultiPlayApplication.getDiscoveredBluetoothDevices().iterator();
		BluetoothConfigurationClass configuration = null;
		boolean isConfigurationStored = false;
		while (bluetoothConfigInterator.hasNext()) {
			configuration = bluetoothConfigInterator.next();
			if (configuration.getStoredIndex() != -1) {
				isConfigurationStored = ElementOfConnectionsList.STORED_YES;
			} else {
				isConfigurationStored = ElementOfConnectionsList.STORED_NO;
			}
			isConfigurationStored = 
			listOfElements.add(new ElementOfConnectionsList(
					configuration.getName(), configuration.getAdress(),
					configuration.getConnectionStatus(),
					isConfigurationStored,
					ConnectionHelper.CONNECTION_TYPE_BT));
		}
	}
}
