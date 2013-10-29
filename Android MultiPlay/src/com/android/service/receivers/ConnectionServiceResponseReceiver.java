package com.android.service.receivers;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.services.ConnectionService;

public class ConnectionServiceResponseReceiver extends BroadcastReceiver {

	   public static final String ACTION_RESP = "com.android.serviece.ConnectionService";
	
	   Activity activity = null;
	   
	   public ConnectionServiceResponseReceiver( Activity activity ) {
		   this.activity = activity;
	   }
	   
	   @Override
	    public void onReceive(Context context, Intent intent) {
		   Log.i("OK", "recive");
	       String text = intent.getStringExtra(ConnectionService.OUTPUT_DATA);
	       Toast.makeText(activity, text, Toast.LENGTH_SHORT).show();
	    }
	}