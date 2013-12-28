package com.android.application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import android.app.Application;
import android.util.Log;

import com.android.asychs.SocketMainWiFiSender;
import com.android.database.DBHelper;
import com.android.database.MultiPlayDataBase;

public class MultiPlayApplication extends Application {

	public final static boolean BLUETOOTH = true;
	public final static boolean WIRELESS = false;
	
	private static DBHelper dbHelper = null;
	private static Collection<BluetoothConfigurationClass> discoveredBluetoothDevices = null;
	private static ArrayList<WirelessConfigurationClass> discoveredWirelessDevices = null;
	private static ConnectionsConfigurationClass mainNetworkConfiguration = null;
	private MultiPlayDataBase multiPlayDataBase = null;
	
	private static SocketMainWiFiSender socketMainThread = null;
	
	public static void runThread() throws IOException {
		Log.d("THREAD","Run thread...");
		socketMainThread = new SocketMainWiFiSender((WirelessConfigurationClass) mainNetworkConfiguration);
		socketMainThread.execute(N.Signal.NEED_CONNECTION);
	}
	
	public static void execute() {
	}
	
	public static void add(int signal) {
		synchronized (socketMainThread) {
			SocketMainWiFiSender.queue.add(signal);
			Log.d("THREAD","Added "+signal);
			Log.d("THREAD","Executing "+SocketMainWiFiSender.queue.size()+" signals...");
		
			MultiPlayApplication.getSocketMainThread().notify();
		}
		Log.d("THREAD","Finish.");
	}
	@Override
	public void onCreate() {
		super.onCreate();
	    
		discoveredBluetoothDevices = new ArrayList<BluetoothConfigurationClass>();
		discoveredWirelessDevices = new ArrayList<WirelessConfigurationClass>();
		dbHelper = new DBHelper(this.getApplicationContext());
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

	public static final ArrayList<WirelessConfigurationClass> getDiscoveredWirelessDevices() {
		return discoveredWirelessDevices;
	}

	public static ConnectionsConfigurationClass getMainNetworkConfiguration() {
		return mainNetworkConfiguration;
	}

	public static final void setMainNetworkConfiguration(
			ConnectionsConfigurationClass mainNetworkConfiguration) {
		MultiPlayApplication.mainNetworkConfiguration = mainNetworkConfiguration;
		Log.d("ListView","Name: "+mainNetworkConfiguration.getName());
		Log.d("ListView","ConStatus: "+mainNetworkConfiguration.getConnectionStatus());
		Log.d("ListView","IP: "+((WirelessConfigurationClass) mainNetworkConfiguration).getIP());
		Log.d("ListView","Port: "+((WirelessConfigurationClass) mainNetworkConfiguration).getPort());
		Log.d("ListView","Stored index: "+mainNetworkConfiguration.getStoredIndex());
	}

	public final MultiPlayDataBase getMultiPlayDataBase() {
		return multiPlayDataBase;
	}

	public final void setMultiPlayDataBase(MultiPlayDataBase multiPlayDataBase) {
		this.multiPlayDataBase = multiPlayDataBase;
	}

	public final static SocketMainWiFiSender getSocketMainThread() {
		return socketMainThread;
	}

	public final void setSocketMainThread(SocketMainWiFiSender socketMainThread) {
		this.socketMainThread = socketMainThread;
	}
}
