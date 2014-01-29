package com.android.services;

import android.app.IntentService;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.util.Log;

import com.android.multiplay.fragments.ConnectionPanel;
import com.android.service.receivers.ConnectionServiceResponseReceiver;

/** Service that handle in background events that should be fired 
 * depending on the phone's network settings (if Wifi is enabled / disabled, 
 * whether Bluetooth is enabled or disabled).
 * 
 * @author tomasz
 *
 */
public class ConnectionService extends IntentService {

	public static final int INIT = 1;
	public static final int BLUETOOTH = 2;
	public static final int WIFI = 3;
	
	private static final String CLASS = "com.android.services.ConnectionService";
	
	/** Intents extra string that defines a default behavior for {@link #onHandleIntent(Intent)}.
	 * 
	 * {@link Intent#getIntExtra(String, int)} can receive values:
	 * <ul>
	 * 	<li> {@link ConnectionService#INIT} - it says that {@link #onHandleIntent(Intent)} 
	 * 		should process {{@link #initService()} method as service assumed that it is it's first call.</li>
	 * 	<li> {@link ConnectionService#BLUETOOTH} - it says that {@link #onHandleIntent(Intent)} 
	 * 		should process {{@link #bluetoothService()} method as service assumed 
	 * 		that it was called by {@link ConnectionPanel#toggleBluetooth_onClick(android.view.View)}</li>
	 * 	<li> {@link ConnectionService#WIFI} - it says that {@link #onHandleIntent(Intent)} 
	 * 		should process {{@link #wifiService()} method as service assumed 
	 * 		that it was called by {@link ConnectionPanel#toggleWirelessNetwork_onClick(android.view.View)}</li>
	 * </ul>
	 * 
	 */
	public static final String INPUT_DATA_CALLED_REASON = CLASS + "CALLED_REASON";

	public static final String INPUT_DATA_CONNECTION_SWITH = CLASS + "CONNECTION_SWITH";
    
	public static final String OUTPUT_DATA_IS_BT_ENABLE = CLASS + "IS_BT_ENABLE";
    public static final String OUTPUT_DATA_BT_DEFAULT_CONF_AVAILABLE = CLASS + "BT_DEFAULT_CONF_AVAILABLE";
    
    public static final String OUTPUT_DATA_IS_WIFI_ENABLE = CLASS + "IS_WIFI_ENABLE";
    public static final String OUTPUT_DATA_WIFI_DEFAULT_CONF_AVAILABLE = CLASS + "WIFI_DEFAULT_CONF_AVAILABLE";
    
    private int called_reason_id = 0;
    private boolean switchOnOff = false;
	
	private static BluetoothAdapter bluetoothAdapter = null;
	private static WifiManager wifiManager = null;
   
    private Intent broadcastIntent = null;
    
    public ConnectionService() {
		super("ConnectionService");
		Log.i("Fragment", "Default contructor");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Log.i("Fragment", "onHandle");
		bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		called_reason_id = intent.getIntExtra(INPUT_DATA_CALLED_REASON, 0);
		if ( called_reason_id != 0 ) {
			broadcastIntent = new Intent(ConnectionServiceResponseReceiver.ACTION_RESP);
			broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
			broadcastIntent.putExtra(INPUT_DATA_CALLED_REASON, called_reason_id);
			
			if ( called_reason_id == ConnectionService.INIT ) {
				Log.i("Fragment", "INIT");
				initService();
			} else {
				switchOnOff = intent.getBooleanExtra(INPUT_DATA_CONNECTION_SWITH, true);
				broadcastIntent.putExtra(INPUT_DATA_CONNECTION_SWITH, switchOnOff);
				if ( called_reason_id == ConnectionService.BLUETOOTH ) {
					bluetoothService();
				} else if ( called_reason_id == ConnectionService.WIFI ) {
					wifiService();
				}
			}
		}
		
		sendBroadcast(broadcastIntent);
		Log.i("ConnectionService", "sent");
	}

	private void initService() {
		searchForEnableServices();
	}
	
	private void searchForEnableServices() {
		Log.i("Fragment", "search");
		searchForEnableBluetoothService();
		searchForEnableWifiService();
	}
	
	private void searchForEnableBluetoothService() {
		if ( bluetoothAdapter != null ) {
			if ( isBluetoothServiceEnabled() ) {
				Log.i("Fragment", "BT enabled");

				if ( isBluetoothSavedConfAvailable() ) {
					setBluetoothDefaultConnectionFromHistory();
				}
			}
		} else {
			//TODO handle exeption - bt is not supproted
		}
	}

	private boolean isBluetoothServiceEnabled() {
		boolean isEnabled = bluetoothAdapter.isEnabled();
		broadcastIntent.putExtra(ConnectionService.OUTPUT_DATA_IS_BT_ENABLE,isEnabled);
		return isEnabled;
	}
	
	private boolean isBluetoothSavedConfAvailable() {
		boolean isBluetoothDefaultConfAvaliable = findBluetoothSavedConf();
		broadcastIntent.putExtra(ConnectionService.OUTPUT_DATA_BT_DEFAULT_CONF_AVAILABLE,isBluetoothDefaultConfAvaliable);
		return isBluetoothDefaultConfAvaliable;
	}
	
	public boolean findBluetoothSavedConf() {
		// TODO Auto-generated method stub
		return false;
	}

	//Jakiś argument z sqlite.
	public void setBluetoothDefaultConnectionFromHistory() {
		// TODO Auto-generated method stub
		
	}
	
	private void searchForEnableWifiService() {
		if ( wifiManager != null ) {
			if ( isWirelessNetworkServiceEnabled() ) {
				Log.i("Fragment", "WiFI enabled");

				if ( isWirelessNetworkSavedConfAvailable() ) {
					setWirelessNetworkDefaultConnectionFromHistory();
				}
			}
		} else {
			//TODO handle exeption - bt is not supproted
		}
	}

	private boolean isWirelessNetworkServiceEnabled() {
		boolean isEnabled = wifiManager.isWifiEnabled();
		broadcastIntent.putExtra(ConnectionService.OUTPUT_DATA_IS_WIFI_ENABLE,isEnabled);
		return isEnabled;
	}
	
	private boolean isWirelessNetworkSavedConfAvailable() {
		boolean isWirelessNetworkDefaultConfAvaliable = findWirelessNetworkSavedConf();
		broadcastIntent.putExtra(ConnectionService.OUTPUT_DATA_WIFI_DEFAULT_CONF_AVAILABLE,isWirelessNetworkDefaultConfAvaliable);
		return isWirelessNetworkDefaultConfAvaliable;
	}
	
	public boolean findWirelessNetworkSavedConf() {
		// TODO Auto-generated method stub
		return false;
	}

	public void setWirelessNetworkDefaultConnectionFromHistory() {
		// TODO Auto-generated method stub
		
	}
	
	private void bluetoothService() {
		// TODO Auto-generated method stub
		Log.i("Fragment", "BT CLICK");
		if ( switchOnOff ) {
			enableBlurtoothService();
		} else {
			disableBlurtoothService();
		}
		initService();
	}
	
	private void enableBlurtoothService() {
		Log.i("Fragment", "BT enabling BT:"+switchOnOff);
	}
	
	private void disableBlurtoothService() {
		Log.i("Fragment", "BT disabling BT:"+switchOnOff);
	}

	private void wifiService() {
		if ( switchOnOff ) {
			enableWirelessNetworkService();
		} else {
			disableWirelessNetworkService();
		}
		initService();
	}

	private void enableWirelessNetworkService() {
		Log.i("Fragment", "WIFI enabling WIFI:"+switchOnOff);
	}
	
	private void disableWirelessNetworkService() {
		Log.i("Fragment", "WIFI disabling WIFI:"+switchOnOff);
	}
}