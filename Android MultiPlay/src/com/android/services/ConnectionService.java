package com.android.services;

import com.android.service.receivers.ConnectionServiceResponseReceiver;

import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;
import android.text.format.DateFormat;
import android.util.Log;

public class ConnectionService extends IntentService {

//	public ConnectionService(String name) {
//		super(name);
//		// TODO Auto-generated constructor stub
//	}
//
//	@Override
//	protected void onHandleIntent(Intent intent) {
//		// TODO Auto-generated method stub
//		
//	}
	
	 public static final String PARAM_IN_MSG = "imsg";
	    public static final String PARAM_OUT_MSG = "omsg";
	    public ConnectionService() {
	        super("SimpleIntentService");
	    }
	    @Override
	    protected void onHandleIntent(Intent intent) {
	        String msg = intent.getStringExtra(PARAM_IN_MSG);
	        SystemClock.sleep(3000); // 3 seconds
	        String resultTxt = msg + " "
	            + DateFormat.format("MM/dd/yy h:mmaa", System.currentTimeMillis());
	        
	        
	        Intent broadcastIntent = new Intent();
	        broadcastIntent.setAction(ConnectionServiceResponseReceiver.ACTION_RESP);
	        broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
	        broadcastIntent.putExtra(PARAM_OUT_MSG, resultTxt);
	        sendBroadcast(broadcastIntent);
	        Log.i("OK", "send");
	    }
	    
	    

}