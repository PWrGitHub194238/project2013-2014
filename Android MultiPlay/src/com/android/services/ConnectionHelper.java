package com.android.services;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnectionHelper {

	public static boolean isWirelessNetworkConnected(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager)
		        context.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo networkInfo = null;
	    if (connectivityManager != null) {
	        networkInfo =
	            connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
	    }
	    return networkInfo == null ? false : networkInfo.isConnected();
	}
	
	public static boolean isBluetoothConnected(Context context) {
	    ConnectivityManager connectivityManager = (ConnectivityManager)
	        context.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo networkInfo = null;
	    if (connectivityManager != null) {
	        networkInfo =
	            connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_BLUETOOTH);
	    }
	    return networkInfo == null ? false : networkInfo.isConnected();
	}
}
