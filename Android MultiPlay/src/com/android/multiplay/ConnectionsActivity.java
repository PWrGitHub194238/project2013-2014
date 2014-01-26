package com.android.multiplay;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

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
import com.android.asyncs.CheckConnectionStatus;
import com.android.asyncs.GenerateConnectionList;
import com.android.asyncs.OnAsyncTaskFinished;
import com.android.dialogs.AddConnectionDialog;
import com.android.dialogs.AlertDialogs;
import com.android.dialogs.AsyncTaskDialog;
import com.android.dialogs.DialogButtonClickListener;
import com.android.dialogs.PreferencesDialog;
import com.android.dialogs.ScrollViewSwitchDialog;
import com.android.dialogs.elements.DialogListCore;
import com.android.extendedWidgets.ImageToggleButton;
import com.android.extendedWidgets.lists.ElementOfConnectionsList;
import com.android.extendedWidgets.lists.ListOfConections;
import com.android.extendedWidgets.lists.PreferencesDialogItem;
import com.android.extendedWidgets.lists.PreferencesDialogList;
import com.android.service.receivers.WiFiDirectBroadcastReceiver;
import com.android.services.ConnectionHelper;

public class ConnectionsActivity extends Activity implements OnItemClickListener, OnItemLongClickListener, OnClickListener, OnLongClickListener, DialogButtonClickListener, OnAsyncTaskFinished {

	
	
////////////////////Fields



	/** Layout under {@link #lv_connections_activity_device_list}.
	* 
	* {@link RelativeLayout} which is displayed in place of the list of devices 
	* when it is empty before refreshing by tapping {@link #b_connections_activity_refresh_connections_check} 
	* or the list of devices is in the process of generating after tapping button 
	* {@link #b_connections_activity_bluetooth_switcher} or {@link #b_connections_activity_wireless_switcher}.
	* It is also displayed when, after refreshing, there is no devices to display. If so, appropriate text is displayed.
	*/
	private RelativeLayout rl_connections_activity_device_list_background_layout = null;
	
	/** Progress bar that appears in the course of generating the list of devices.
	* 
	*  This progress bar is an element of {@link #rl_connections_activity_device_list_background_layout}.
	*  It is displayed during searching for connections with devices and generating their list 
	*  after tapping one of the buttons: 
	*  <ul>
	*  <li> {@link #b_connections_activity_bluetooth_switcher}, </li>
	*  <li> {@link #b_connections_activity_wireless_switcher}, </li>
	*  <li> {@link #b_connections_activity_refresh_connections_check}. </li>
	*  </ul>
	*/
	private ProgressBar pb_connections_activity_refreshing = null;
	
	/** List of generated connections with other devices.
	* 
	* Each generated element consists of fields defined in {@link ElementOfConnectionsList}. 
	* When empty or not initialized it is not displayed at all (see {@link #rl_connections_activity_device_list_background_layout}).
	* After generating it displays every element on {@link #listOfElements}.
	* 
	* @see listOfElements
	* @see selectedConfig
	* @see lv_connections_activity_device_list_visibly
	*/
	private ListView lv_connections_activity_device_list = null;
	
	/** Button that fires searching for available bluetooth connections.
	* 
	* <ul>
	* <li> When button is tapped short: 
	* It fires execution of {@link GenerateConnectionList} that adds to list bluetooth connections which have been found. </li>
	* <li> When button is tapped long: It replaces default type of connections to bluetooth type- 
	* when there are default connections stored in Android memory of both types, 
	* the priority one is a bluetooth type and after discovering such a connection it is set automatically.</li>
	* </ul>
	* 
	* @see MultiPlayApplication#setMainNetworkConfiguration(ConnectionsConfigurationClass)
	*/
	private ImageToggleButton b_connections_activity_bluetooth_switcher = null;
	
	/** Button that fires searching for available wireless connections.
	* 
	* <ul>
	* <li> When button is tapped short: 
	* It fires execution of {@link GenerateConnectionList} that adds to list wireless connections which have been found. </li>
	* <li> When button is tapped long: It replaces default type of connections to wireless type- 
	* when there are default connections stored in Android memory of both types, 
	* the priority one is a wireless type and after discovering such a connection it is set automatically.</li>
	* </ul>
	* 
	* @see MultiPlayApplication#setMainNetworkConfiguration(ConnectionsConfigurationClass)
	*/
	private ImageToggleButton b_connections_activity_wireless_switcher = null;
	
	/** Button that fires {@link AddConnectionDialog}.
	 * 
	 * @see #new_connection_onClick()
	 * 
	 */
	private ImageButton b_connections_activity_add_new_connection = null;
	
	/** Button refreshes the list of found connections.
	 * 
	 * It executes {@link CheckConnectionStatus} for each element on {@link #listOfElements}
	 * and refreshes the list of available connections along with their statuses. 
	 * 
	 */
	private ImageButton b_connections_activity_refresh_connections_check = null;
	
	/**
	* 
	*/
	private ConnectionsConfigurationClass selectedConfig = null;
	
	/**
	* 
	*/
	private Collection<ElementOfConnectionsList> listOfElements = null;
	
	private ListOfConections listOfElementsAdpater = null;
	
	/**
	* 
	*/
	private boolean lv_connections_activity_device_list_visibly = false;
	
	private boolean networkAvaliable = true;

	
	
	
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
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Log.d("ListView","Click index: "+position);
		
		ElementOfConnectionsList selectedItem = (ElementOfConnectionsList) parent.getItemAtPosition(position);
		Log.d("ListView","Click btorwifi: "+selectedItem.isBTorWiFi());

		if (selectedItem.isBTorWiFi() == true) {
			
			Log.d("ListView","Click bt name: "+selectedItem.getDeviceName());
			Log.d("ListView","Click bt name: "+MultiPlayApplication.getDiscoveredBluetoothDevices().get(selectedItem.getIndex()).getName());

			selectedConfig = MultiPlayApplication.getDiscoveredBluetoothDevices().get(selectedItem.getIndex());
			
			Log.d("ListView","Click wifi state: "+selectedConfig.getConnectionStatus());
			
			Log.d("THREAD","EXEC 223");
			runLogAsyncThread(
					OnAsyncTaskFinished.TAG.ConnectionActivity_onItemClick_BT,
					OnAsyncTaskFinished.TAG.ConnectionActivity_onItemClick_BT);
			
		} else {
			Log.d("ListView","Click wifi name: "+selectedItem.getDeviceName());
			Log.d("ListView","Click wifi  name: "+MultiPlayApplication.getDiscoveredWirelessDevices().get(selectedItem.getIndex()).getName());

			selectedConfig = MultiPlayApplication.getDiscoveredWirelessDevices().get(selectedItem.getIndex());
			
			Log.d("ListView","Click wifi state: "+selectedConfig.getConnectionStatus());

			
			Log.d("THREAD","EXEC 237 <---");
			runLogAsyncThread(
					OnAsyncTaskFinished.TAG.ConnectionActivity_onItemClick_WIFI,
					OnAsyncTaskFinished.TAG.ConnectionActivity_onItemClick_WIFI);
		}
	}

	@Override
	public boolean onLongClick(View v) {
		switch(v.getId()) {
			case R.id.b_connections_activity_bluetooth_switcher:
				if (mBluetoothAdapter == null) {
				    // Device does not support Bluetooth
				} else {
					Collection<PreferencesDialogItem> listElements = new ArrayList<PreferencesDialogItem>();
					listElements.add(
							new PreferencesDialogItem(
									PreferencesDialogItem.ID.ListOfConnections_edit,
									PreferencesDialogItem.ICON.icon_1,
									PreferencesDialogItem.NAME.dialog_optionID_EDIT_CONNECTION));
					listElements.add(
							new PreferencesDialogItem(
									PreferencesDialogItem.ID.ListOfConnections_forget,
									PreferencesDialogItem.ICON.icon_1,
									PreferencesDialogItem.NAME.dialog_optionID_EDIT_CONNECTION));
					listElements.add(
							new PreferencesDialogItem(
									PreferencesDialogItem.ID.returnTAG,
									PreferencesDialogItem.ICON.icon_1,
									PreferencesDialogItem.NAME.dialog_optionID_RETURN));
					
					PreferencesDialogList listAdapter = new PreferencesDialogList(getApplicationContext(), listElements );
					PreferencesDialog.showDialog(this, 
							ConnectionsActivity.DialogList.TAG_CONNECTION_OPTIONS, 
							listAdapter );
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

	/** OcClick method displays {@link AddConnectionDialog} in order to add a new connection.
	 * 
	 * @see #b_connections_activity_add_new_connection
	 * 
	 */
	private void new_connection_onClick() {		
		AddConnectionDialog.showDialog(this,
				ConnectionsActivity.DialogList.TAG_ADD_CONNECTION,
				ConnectionsActivity.DialogList.ID_TITLE_ICON_CONNECTION_CREATOR,
				ConnectionsActivity.DialogList.ID_TITLE_CONNECT_CONFIRMATION,
				ScrollViewSwitchDialog.getViewFromResource(this,R.layout.dialog_add_new_connection),
				R.id.s_connections_activity_connection_type_switch,
				R.id.rl_connections_activity_wifi_configurator_layout,
				R.id.rl_connections_activity_bt_configurator_layout,
				DialogListCore.ID_BUTTON_CONNECT,
				null,
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
		refresh();
	}
	
	public void refresh() {
		if (lv_connections_activity_device_list_visibly == false) {
			int index = 0;
			int childrenCounter = rl_connections_activity_device_list_background_layout.getChildCount();
			for ( index = 0; index < childrenCounter; index += 1 ) {
				rl_connections_activity_device_list_background_layout.getChildAt(index).setVisibility(RelativeLayout.INVISIBLE);
			}
			pb_connections_activity_refreshing.setVisibility(ProgressBar.VISIBLE);
			
			refreshConnectionList();
			 
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
		
		if (MultiPlayApplication.isWirelessEnable() == false ) {
			networkAvaliable = networkAvaliable || false;
		}
		
		
		
		mManager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
	    mChannel = mManager.initialize(this, getMainLooper(), null);
	    mReceiver = new WiFiDirectBroadcastReceiver(mManager, mChannel, this);
	    
	    mIntentFilter = new IntentFilter();
	    mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
	    mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
	    mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
	    mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);
	    
	    refreshConnectionList();
	}
	
	private void initRl_connections_activity_device_list_background_layout(int id) {
		this.rl_connections_activity_device_list_background_layout = (RelativeLayout) super.findViewById(id);
		this.pb_connections_activity_refreshing = (ProgressBar) rl_connections_activity_device_list_background_layout.findViewById(R.id.pb_connections_activity_refreshing);
		this.pb_connections_activity_refreshing.setVisibility(ProgressBar.INVISIBLE);
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
	
	
	
////////////////////Methods for operations on configurations list.



	public void refreshConnectionList() {
		listOfElements.clear();
		if (true ) {
			Log.d("Connections","Refresh wifi list");
			refreshWirelessList();

		}
		if ( true ) {
			Log.d("Connections","Refresh BT list");
			refreshBluetoothList();
		}
		
		listOfElementsAdpater = new ListOfConections(this, listOfElements);
		lv_connections_activity_device_list.setAdapter(listOfElementsAdpater);
	}
	
	public void refreshWirelessList() {
		ArrayList<WirelessConfigurationClass> wirelessConfigArray = MultiPlayApplication.getDiscoveredWirelessDevices();
		int wirelessConfigArraySize = wirelessConfigArray.size();
		WirelessConfigurationClass configuration = null;
		int index = 0;
		
		for ( index = 0; index < wirelessConfigArraySize; index += 1) {
			configuration = wirelessConfigArray.get(index);
			if (configuration.getStoredIndex() != -1) {
				configuration.setStored(
						ElementOfConnectionsList.STORED_YES);
			} else {
				configuration.setStored(
						ElementOfConnectionsList.STORED_NO);
			}

			listOfElements.add(new ElementOfConnectionsList(index,configuration));
		}
	}
	
	public void refreshBluetoothList() {
		ArrayList<BluetoothConfigurationClass> bluetoothConfigArray = MultiPlayApplication.getDiscoveredBluetoothDevices();
		int bluetoothConfigArraySize = bluetoothConfigArray.size();
		BluetoothConfigurationClass configuration = null;
		int index = 0;
		
		for ( index = 0; index < bluetoothConfigArraySize; index += 1) {
			configuration = bluetoothConfigArray.get(index);
			if (configuration.getStoredIndex() != -1) {
				configuration.setStored(
						ElementOfConnectionsList.STORED_YES);
			} else {
				configuration.setStored(
						ElementOfConnectionsList.STORED_NO);
			}
			
			listOfElements.add(new ElementOfConnectionsList(index,configuration));
		}
	}
	
	
	
////////////////////DialogButtonClickListener's methods for dialog interaction

	
	
	public final static class DialogList {

		private static final String PACKAGE = "com.android.multiplay.connectionsactivity";
		
		public static final String TAG_ADD_CONNECTION = PACKAGE + "ADD_CONNECTION";
		public static final String TAG_CONNECT_CONFIRMATION = PACKAGE + "CONNECT_CONFIRMATION";
		public static final String TAG_CONNECTION_OPTIONS = PACKAGE + "CONNECTION_OPTIONS";
		public static final String TAG_CONNECT_DENY_BT = PACKAGE + "CONNECT_DENY_BT";
		public static final String TAG_CONNECT_DENY_WIFI = PACKAGE + "CONNECT_DENY_WIFI";
		
		public static final int ID_TITLE_ADD_CONNECTION =
				R.string.dialog_ID_TITLE_ADD_CONNECTION;
		public static final int ID_TITLE_CONNECT_CONFIRMATION =
				R.string.dialog_ID_TITLE_CONNECT_CONFIRMATION;
		public static final Integer ID_TITLE_CONNECT_DENY = 
				R.string.dialog_ID_TITLE_CONNECT_DENY;
		
		public static final int ID_TITLE_ICON_CONNECTION_CREATOR =
				R.drawable.connections_activity_icon_creator;
		public static final int ID_TITLE_ICON_WIFI_SUCCESS =
				R.drawable.connections_activity_icon_wifi_success;
		public static final int ID_TITLE_ICON_BT_SUCCESS =
				R.drawable.connections_activity_icon_bt_success;
		public static final int ID_TITLE_ICON_WIFI_WARNING =
				R.drawable.connections_activity_icon_wifi_warning;
		public static final int ID_TITLE_ICON_BT_WARNING =
				R.drawable.connections_activity_icon_bt_warning;
		
		public static final Integer ID_MESSAGE_CONNECT_CONFIRMATION = 
				R.string.dialog_ID_MESSAGE_CONNECT_CONFIRMATIONN;
		public static final Integer ID_MESSAGE_CONNECT_DENY =
				R.string.dialog_ID_MESSAGE_CONNECT_DENY;
	}
	
	@Override
	public void onDialogPositiveClick(DialogFragment dialog) {
		String dialogTag = dialog.getTag();
	
		if ( dialogTag.equals(ConnectionsActivity.DialogList.TAG_ADD_CONNECTION)) {

			insertConnectionToMultiPlay(
					((AddConnectionDialog) dialog).getReturnedData(),
					((AddConnectionDialog) dialog).isViewSwitcherState());

		} else if (dialogTag.equals(ConnectionsActivity.DialogList.TAG_CONNECT_CONFIRMATION)) {
			Toast.makeText(this, "Make connection",Toast.LENGTH_LONG).show();
			MultiPlayApplication.setMainNetworkConfiguration(selectedConfig);

		} else if (dialogTag.equals(ConnectionsActivity.DialogList.TAG_CONNECT_DENY_BT)) {
		
			Log.d("THREAD","EXEC 595");
			runLogAsyncThread(
					OnAsyncTaskFinished.TAG.ConnectionActivity_onItemClick_BT,
					OnAsyncTaskFinished.TAG.ConnectionActivity_onItemClick_BT);
			
		} else if (dialogTag.equals(ConnectionsActivity.DialogList.TAG_CONNECT_DENY_WIFI)) {
			
			Log.d("THREAD","EXEC 613");
			runLogAsyncThread(
					OnAsyncTaskFinished.TAG.ConnectionActivity_onItemClick_WIFI,
					OnAsyncTaskFinished.TAG.ConnectionActivity_onItemClick_WIFI);
		}

		refreshConnectionList();
	}


	private void insertConnectionToMultiPlay(Map<String, String> returnedData, boolean isBTorWIFI) {
		
		if ( isBTorWIFI == MultiPlayApplication.CONNECTION_TYPE_WIFI ) {
			
			Log.d("Connections","Name-> "+returnedData.get(AddConnectionDialog.DEVICE_NAME));
			Log.d("Connections","IP-> "+returnedData.get(AddConnectionDialog.DEVICE_IP));
			Log.d("Connections","Port-> "+returnedData.get(AddConnectionDialog.DEVICE_PORT));
			
			insertWirelessConnectionToMultiPlay(
					returnedData.get(AddConnectionDialog.DEVICE_IP),
					returnedData.get(AddConnectionDialog.DEVICE_PORT),
					returnedData.get(AddConnectionDialog.DEVICE_NAME),
					returnedData.get(AddConnectionDialog.DEVICE_IS_STORED));	
			
		} else if ( isBTorWIFI == MultiPlayApplication.CONNECTION_TYPE_BT ) {
			
			Log.d("Connections","UUID-> "+BluetoothConfigurationClass.generateUUIDfromMAC(
					BluetoothConfigurationClass.Profiles.inne,
					returnedData.get(AddConnectionDialog.DEVICE_MAC)));
			Log.d("Connections","MAC-> "+returnedData.get(AddConnectionDialog.DEVICE_MAC));
			
			insertBluetoothConnectionToMultiPlay(
					returnedData.get(AddConnectionDialog.DEVICE_MAC),
					returnedData.get(AddConnectionDialog.DEVICE_NAME),
					returnedData.get(AddConnectionDialog.DEVICE_IS_STORED));

		}
		
		refreshConnectionList();
	}

	private void insertWirelessConnectionToMultiPlay(String deviceIP,
			String devicePort, String deviceName, String isStored) {
		
		WirelessConfigurationClass config = new WirelessConfigurationClass(
				deviceIP,Integer.valueOf(devicePort));
		
		config.setConnectionStatus(
				ConnectionHelper.STATUS_NOT_IN_RANGE);
		
		config.setName(deviceName);
		
		config.setStored(
				isStored == Boolean.toString(true));

		ConnectionHelper.insertNewConnectionToList(
				MultiPlayApplication.CONNECTION_TYPE_WIFI, config);
	}

	private void insertBluetoothConnectionToMultiPlay(String deviceMAC,
			String deviceName, String isStored) {
		
		BluetoothConfigurationClass config = new BluetoothConfigurationClass(
				BluetoothConfigurationClass.generateUUIDfromMAC(
						BluetoothConfigurationClass.Profiles.SSP,deviceMAC),
				deviceMAC);
		
		config.setConnectionStatus(
				ConnectionHelper.STATUS_NOT_IN_RANGE);
		
		config.setName(deviceName);
		
		config.setStored(
				isStored == Boolean.toString(true));
		
		ConnectionHelper.insertNewConnectionToList(
				MultiPlayApplication.CONNECTION_TYPE_BT, config);
	}

	
	@Override
	public void onDialogNeutralClick(DialogFragment dialog) {
	// TODO Auto-generated method stub
	
		refreshConnectionList();
		
	}
	
	
	
	@Override
	public void onDialogNegativeClick(DialogFragment dialog) {
	// TODO Auto-generated method stub
		
		refreshConnectionList();

}
	

	
////////////////////Methods for AsynchTask.
	
	

	private void runLogAsyncThread() {
		AsyncTaskDialog asyncTaskDialog = new AsyncTaskDialog(this);
		synchronized (asyncTaskDialog ) {
			asyncTaskDialog.show();
			new CheckConnectionStatus(this,asyncTaskDialog).execute(selectedConfig);
		}
	}
	
	private void runLogAsyncThread(int asyncCallReason) {
		AsyncTaskDialog asyncTaskDialog = new AsyncTaskDialog(this);
		synchronized (asyncTaskDialog ) {
			asyncTaskDialog.show();
			new CheckConnectionStatus(this,asyncTaskDialog,asyncCallReason).execute(selectedConfig);
		}
	}
	
	private void runLogAsyncThread(int asyncCallReason, int asyncCallOnError) {
		AsyncTaskDialog asyncTaskDialog = new AsyncTaskDialog(this);
		synchronized (asyncTaskDialog ) {
			asyncTaskDialog.show();
			new CheckConnectionStatus(this,asyncTaskDialog,asyncCallReason,asyncCallOnError).execute(selectedConfig);
		}
	}

	@Override
	public void onBackgroundFinished(int activityTAG) {
		switch (activityTAG) {
		case OnAsyncTaskFinished.TAG.ConnectionActivity_onItemClick_BT:
			afterRecheckElementOnclick(MultiPlayApplication.CONNECTION_TYPE_BT);
			break;
			
		case OnAsyncTaskFinished.TAG.ConnectionActivity_onItemClick_WIFI:
			afterRecheckElementOnclick(MultiPlayApplication.CONNECTION_TYPE_WIFI);
			break;
			
		case OnAsyncTaskFinished.TAG.ConnectionActivity_onDialogPositiveClick:
			refreshConnectionList();
		}
	}

	private void afterRecheckElementOnclick(boolean isBTorWIFI) {
		int dialogTitleIconID = 0;
		String dialogTagID = null;
		
		Log.d("ListView","Click afterstate: "+selectedConfig.getConnectionStatus());
		
		switch (selectedConfig.getConnectionStatus()) {
		case ConnectionHelper.STATUS_ON:
			if ( isBTorWIFI == MultiPlayApplication.CONNECTION_TYPE_WIFI ) {
				dialogTitleIconID = ConnectionsActivity.DialogList.ID_TITLE_ICON_WIFI_SUCCESS;
			} else {
				dialogTitleIconID = ConnectionsActivity.DialogList.ID_TITLE_ICON_BT_SUCCESS;
			}
			AlertDialogs.showDialog(this,
					ConnectionsActivity.DialogList.TAG_CONNECT_CONFIRMATION,
					dialogTitleIconID,
					ConnectionsActivity.DialogList.ID_TITLE_CONNECT_CONFIRMATION,
					ConnectionsActivity.DialogList.ID_MESSAGE_CONNECT_CONFIRMATION,
					DialogListCore.ID_BUTTON_CONNECT,
					null,
					DialogListCore.ID_BUTTON_CANCEL);
			break;
		case ConnectionHelper.STATUS_NOT_IN_RANGE:
			if ( isBTorWIFI == MultiPlayApplication.CONNECTION_TYPE_WIFI ) {
				dialogTitleIconID = ConnectionsActivity.DialogList.ID_TITLE_ICON_WIFI_WARNING;
				dialogTagID = ConnectionsActivity.DialogList.TAG_CONNECT_DENY_WIFI;
			} else {
				dialogTitleIconID = ConnectionsActivity.DialogList.ID_TITLE_ICON_BT_WARNING;
				dialogTagID = ConnectionsActivity.DialogList.TAG_CONNECT_DENY_BT;
			}
			AlertDialogs.showDialog(this,
					dialogTagID,
					dialogTitleIconID,
					ConnectionsActivity.DialogList.ID_TITLE_CONNECT_DENY,
					ConnectionsActivity.DialogList.ID_MESSAGE_CONNECT_DENY,
					DialogListCore.ID_BUTTON_RECHECK,
					null,
					DialogListCore.ID_BUTTON_CANCEL);
			break;
		}
	}
}
