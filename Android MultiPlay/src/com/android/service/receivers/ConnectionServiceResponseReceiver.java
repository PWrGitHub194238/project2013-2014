package com.android.service.receivers;

import com.android.services.ConnectionService;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class ConnectionServiceResponseReceiver extends BroadcastReceiver {

	   public static final String ACTION_RESP =
	      "com.mamlambo.intent.action.MESSAGE_PROCESSED";
	   
	   Activity activity = null;
	   
	   public ConnectionServiceResponseReceiver( Activity activity ) {
		   this.activity = activity;
	   }
	   @Override
	    public void onReceive(Context context, Intent intent) {
	       String text = intent.getStringExtra(ConnectionService.PARAM_OUT_MSG);
	       Toast.makeText(activity, text, Toast.LENGTH_SHORT).show();
	       Log.i("OK", "recive");
	    }
	}