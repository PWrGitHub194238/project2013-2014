package com.android.application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import android.app.Application;
import android.database.Cursor;
import android.util.Log;

import com.android.asychs.CheckConnectionStatus;
import com.android.asychs.SocketMainWiFiSender;
import com.android.database.DBHelper;
import com.android.database.MultiPlayDataBase;
import com.android.database.tables.NetworkWiFiSettings;

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
	
	public static void closeThread() {
		Log.d("THREAD","Stoping thread...");
		if (socketMainThread != null ) {
			add(N.Exit.EXIT_NO_ERROR);
		}
	}
	
	public static void add(int signal) {
		if (socketMainThread != null) {
			synchronized (socketMainThread) {
				SocketMainWiFiSender.queue.add(signal);
				Log.d("THREAD","Added "+signal);
				Log.d("THREAD","Executing "+SocketMainWiFiSender.queue.size()+" signals...");
			
				MultiPlayApplication.getSocketMainThread().notify();
			}
			Log.d("THREAD","Finish.");
		}
	}
	@Override
	public void onCreate() {
		super.onCreate();
	    
		discoveredBluetoothDevices = new ArrayList<BluetoothConfigurationClass>();
		discoveredWirelessDevices = new ArrayList<WirelessConfigurationClass>();
		dbHelper = new DBHelper(this.getApplicationContext());
		try {
			loadConnectionListFromDB();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void loadConnectionListFromDB() throws InstantiationException, IllegalAccessException {
		Cursor cursor = dbHelper.sql_select_by_id(NetworkWiFiSettings.class,true);
		discoveredWirelessDevices.addAll(
				DBHelper.parseNetworkWiFiSettings(cursor));
		
		ConnectionsConfigurationClass[] connectionsArrayToExecute = new ConnectionsConfigurationClass[0];
		discoveredBluetoothDevices.toArray(connectionsArrayToExecute);
		
		checkConnections(connectionsArrayToExecute);
		
		connectionsArrayToExecute = new ConnectionsConfigurationClass[0];
		discoveredWirelessDevices.toArray(connectionsArrayToExecute);
		checkConnections(connectionsArrayToExecute);
		
	}


	private void checkConnections(ConnectionsConfigurationClass[] connectionsArrayToExecute) {
		new CheckConnectionStatus().execute(connectionsArrayToExecute);
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
