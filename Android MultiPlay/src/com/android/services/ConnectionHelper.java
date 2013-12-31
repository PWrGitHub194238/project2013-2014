package com.android.services;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.android.application.BluetoothConfigurationClass;
import com.android.application.ConnectionsConfigurationClass;
import com.android.application.MultiPlayApplication;
import com.android.application.WirelessConfigurationClass;
import com.android.asychs.CheckConnectionStatus;
import com.android.asychs.SocketMainWiFiSender;
import com.android.database.DBHelper;
import com.android.database.tables.NetworkWiFiSettings;
import com.android.multiplay.R;

public class ConnectionHelper {

	public static final boolean CONNECTION_TYPE_WIFI = false;
	public static final boolean CONNECTION_TYPE_BT = true;
	
	public static final int STATUS_ON = R.drawable.activity_button_on;
	public static final int STATUS_WARNING = R.drawable.activity_button_warning;
	public static final int STATUS_NOT_IN_RANGE = R.drawable.activity_button_not_in_range;
	
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
	
	public static int insertNewConnectionToList(boolean connectionType, ConnectionsConfigurationClass configuration) {
		if (connectionType == CONNECTION_TYPE_WIFI) {
			WirelessConfigurationClass wifiConfiguration = (WirelessConfigurationClass) configuration;
			Log.d("Connections","> Name: " + configuration.getName());
			Log.d("Connections","> IP: " + wifiConfiguration.getIP());
			Log.d("Connections","> Port: " + wifiConfiguration.getPort());
			
			
			 Map<String,String> newValues = new HashMap<String,String>();
			 newValues.put(NetworkWiFiSettings.DBSchema.COLUMN_1, configuration.getName());
			 newValues.put(NetworkWiFiSettings.DBSchema.COLUMN_2, wifiConfiguration.getIP());
			 newValues.put(NetworkWiFiSettings.DBSchema.COLUMN_3, wifiConfiguration.getPort().toString());
			
			 try {
			 		if (configuration.isStored() == true) {
			 			
						MultiPlayApplication.getDbHelper().sql_insert_row(NetworkWiFiSettings.class, newValues,DBHelper.CLEAR_YES,DBHelper.REOPEN_YES);
						int i = DBHelper.sql_generate_not_exitsting_minID(NetworkWiFiSettings.class,DBHelper.REOPEN_YES) - 1;
						wifiConfiguration.setStoredIndex(i);
					}
					wifiConfiguration.setConnectionStatus(STATUS_NOT_IN_RANGE);
					Cursor cursor = null;
					cursor = MultiPlayApplication.getDbHelper().sql_select_by_id(NetworkWiFiSettings.class,DBHelper.REOPEN_YES);
					
					Log.d("DB",DatabaseUtils.dumpCursorToString(cursor));
					Log.d("APP","Checking connection...");
					new CheckConnectionStatus().execute(wifiConfiguration);
					MultiPlayApplication.getDiscoveredWirelessDevices().add(wifiConfiguration);
					Log.d("APP",MultiPlayApplication.getDiscoveredWirelessDevices().iterator().next().toString());
					
			 	} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//			try {
//			((MultiPlayApplication) getApplication()).getDbHelper().sql_delete_row(General.class);
//			 Map<String,String> newValues = new HashMap<String,String>();
//			 newValues.put(General.DBSchema.COLUMN_1, "Name A");
//			 newValues.put(General.DBSchema.COLUMN_2, DBHelper.FALSE);
//			((MultiPlayApplication) getApplication()).getDbHelper().sql_insert_row(General.class, newValues,DBHelper.CLEAR_YES);
//			newValues.put(General.DBSchema.COLUMN_1, "Name B");
//			 newValues.put(General.DBSchema.COLUMN_2, DBHelper.FALSE);
//			((MultiPlayApplication) getApplication()).getDbHelper().sql_insert_row(General.class, newValues,DBHelper.CLEAR_YES);
//			newValues.put(General.DBSchema.COLUMN_1, "Name B");
//			 newValues.put(General.DBSchema.COLUMN_2, DBHelper.FALSE);
//			((MultiPlayApplication) getApplication()).getDbHelper().sql_insert_row(General.class, newValues,DBHelper.CLEAR_YES);
//		} catch (InstantiationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
			
		} else {
			BluetoothConfigurationClass btConfiguration = (BluetoothConfigurationClass) configuration;
			Log.d("Connections","> UUID: " + btConfiguration.getUuid());
			Log.d("Connections","> Address: " + btConfiguration.getAdress());
		}
		Log.d("Connections","> Status: " + configuration.getConnectionStatus());
		Log.d("Connections","> MAC: " + configuration.getMACAdress());
		Log.d("Connections","> Index: " + configuration.getStoredIndex());

		return 1;
	}
}
