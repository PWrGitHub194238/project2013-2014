package com.android.services;

import android.app.IntentService;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.SystemClock;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.Toast;

import com.android.service.receivers.ConnectionServiceResponseReceiver;

public class ConnectionService extends IntentService {

	public static final String INPUT_DATA_CONNECTION_INIT = "com.android.multiplay.MainActivity.CONNECTION_INIT";
	public static final String INPUT_DATA_CONNECTION_SELECT = "com.android.multiplay.MainActivity.CONNECTION_SELECT";
	public static final String INPUT_DATA_CONNECTION_SWITH = "com.android.multiplay.MainActivity.CONNECTION_SWITH";
    public static final String OUTPUT_DATA = "com.android.service.recivers.ConnectionServiece";

    private boolean isFirstCall = false;
    private boolean isBluetoothSelected = false;
    private boolean isWirelessNetworkSelected = false;
    private boolean switchOnOff = false;
   
    private Intent broadcastIntent = null;
    
    public static final String PARAM_IN_MSG = "imsg";
    public static final String PARAM_OUT_MSG = "omsg";
    
    public ConnectionService() {
		super("ConnectionService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		isFirstCall = intent.getBooleanExtra(INPUT_DATA_CONNECTION_INIT, false);
		if ( isFirstCall == true ) {
			Toast.makeText(super.getApplicationContext(), "INIT"+((isEnabled()) ? " WIFI: YES" : " WIFI: NO")+((isEnabledBT()) ? " BT: YES" : " BT: NO"), Toast.LENGTH_SHORT).show();
		} else {
			isBluetoothSelected = intent.getBooleanExtra(INPUT_DATA_CONNECTION_SELECT, isBluetoothSelected);
			isWirelessNetworkSelected = !isBluetoothSelected;
			switchOnOff = intent.getBooleanExtra(INPUT_DATA_CONNECTION_SWITH, switchOnOff);
			
			SystemClock.sleep(3000); // 3 seconds
		    String resultTxt = ((isBluetoothSelected) ? "Bluetooth " : "Wireless ") + ((switchOnOff) ? "Swith ON " : "Swith OFF ") +DateFormat.format("MM/dd/yy h:mmaa", System.currentTimeMillis()).toString();
		    
		    broadcastIntent = new Intent();
		    broadcastIntent.setAction(ConnectionServiceResponseReceiver.ACTION_RESP);
		    broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
		    broadcastIntent.putExtra(OUTPUT_DATA, resultTxt);
		    sendBroadcast(broadcastIntent);
		    Log.i("OK", "send");
		}
	}
	
	
	
	private static boolean isConnected(Context context) {
	    ConnectivityManager connectivityManager = (ConnectivityManager)
	        context.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo networkInfo = null;
	    if (connectivityManager != null) {
	        networkInfo =
	            connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
	    }
	    return networkInfo == null ? false : networkInfo.isConnected();
	}
	
	private static boolean isConnectedBT(Context context) {
	    ConnectivityManager connectivityManager = (ConnectivityManager)
	        context.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo networkInfo = null;
	    if (connectivityManager != null) {
	        networkInfo =
	            connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_BLUETOOTH);
	    }
	    return networkInfo == null ? false : networkInfo.isConnected();
	}
	
	private boolean isEnabled() {
		WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		return wifi.isWifiEnabled();
	}
	
	private boolean isEnabledBT() {
//		BluetoothManager bt = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
//		return bt.getAdapter().isEnabled();
		BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		return mBluetoothAdapter.isEnabled();
	}
}