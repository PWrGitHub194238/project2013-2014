package com.android.services;

import com.android.service.receivers.ConnectionServiceResponseReceiver;

import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;
import android.text.format.DateFormat;
import android.util.Log;

public class ConnectionService extends IntentService {

	public static final String INPUT_DATA_CONNECTION_SELECT = "com.android.multiplay.MainActivity.CONNECTION_SELECT";
	public static final String INPUT_DATA_CONNECTION_SWITH = "com.android.multiplay.MainActivity.CONNECTION_SWITH";
    public static final String OUTPUT_DATA = "com.android.service.recivers.ConnectionServiece";

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