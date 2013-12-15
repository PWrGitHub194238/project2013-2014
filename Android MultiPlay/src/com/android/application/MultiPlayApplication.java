package com.android.application;

import java.util.ArrayList;
import java.util.Collection;

import android.app.Application;

import com.android.asychs.SocketMainThread;
import com.android.database.DBHelper;
import com.android.database.MultiPlayDataBase;

public class MultiPlayApplication extends Application {

	public final static boolean BLUETOOTH = true;
	public final static boolean WIRELESS = false;
	
	private static DBHelper dbHelper = null;
	private static Collection<BluetoothConfigurationClass> discoveredBluetoothDevices = null;
	private static Collection<WirelessConfigurationClass> discoveredWirelessDevices = null;
	private MultiPlayDataBase multiPlayDataBase = null;
	
	private SocketMainThread socketMainThread = null;
	
	@Override
	public void onCreate() {
		super.onCreate();
	    
		discoveredBluetoothDevices = new ArrayList<BluetoothConfigurationClass>();
		discoveredWirelessDevices = new ArrayList<WirelessConfigurationClass>();
		dbHelper = new DBHelper(this.getApplicationContext());
		dbHelper.openConnection();
		
	}
	
	public void onDestroy() {
		dbHelper.closeConnection();	
	}

	public static final DBHelper getDbHelper() {
		return dbHelper;
	}

	public final void setDbHelper(DBHelper dbHelper) {
		this.dbHelper = dbHelper;
	}

	public static final Collection<BluetoothConfigurationClass> getDiscoveredBluetoothDevices() {
		return discoveredBluetoothDevices;
	}

	public static final Collection<WirelessConfigurationClass> getDiscoveredWirelessDevices() {
		return discoveredWirelessDevices;
	}


	public final MultiPlayDataBase getMultiPlayDataBase() {
		return multiPlayDataBase;
	}

	public final void setMultiPlayDataBase(MultiPlayDataBase multiPlayDataBase) {
		this.multiPlayDataBase = multiPlayDataBase;
	}

	public final SocketMainThread getSocketMainThread() {
		return socketMainThread;
	}

	public final void setSocketMainThread(SocketMainThread socketMainThread) {
		this.socketMainThread = socketMainThread;
	}
}
