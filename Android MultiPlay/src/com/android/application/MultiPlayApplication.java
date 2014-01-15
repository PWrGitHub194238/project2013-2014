package com.android.application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import android.app.Application;
import android.database.Cursor;
import android.util.Log;

import com.android.asychs.CheckConnectionStatus;
import com.android.asychs.SocketMainBluetoothSender;
import com.android.asychs.SocketMainWiFiSender;
import com.android.database.DBHelper;
import com.android.database.MultiPlayDataBase;
import com.android.database.tables.General;
import com.android.database.tables.NetworkBTSettings;
import com.android.database.tables.NetworkWiFiSettings;

public class MultiPlayApplication extends Application {

	public final static boolean BLUETOOTH = true;
	public final static boolean WIRELESS = false;
	
	private static DBHelper dbHelper = null;
	
	private static Map<String,String> multiPlayRequirements = null;
	
	/** Flag responsible for giving adequate priority to the available ways 
	 * to connect with other devices. With the flag set to:
	 * <ul>
	 * 	<li>true - with both bluetooth and wireless service available the first one will be set
	 * as default (@see {@link #BLUETOOTH}),</li>
	 * 	<li>false - with both bluetooth and wireless service available the second one will be set
	 * as default (@see {@link #WIRELESS}),</li>
	 * </ul>
	 * 
	 */
	private static boolean networkPriority = WIRELESS;
	private static boolean connectedTo = WIRELESS;
	
	private static ArrayList<BluetoothConfigurationClass> discoveredBluetoothDevices = null;
	private static boolean bluetoothEnable = false;
	private static ArrayList<WirelessConfigurationClass> discoveredWirelessDevices = null;
	private static boolean wirelessEnable = false;
	
	private static ConnectionsConfigurationClass mainNetworkConfiguration = null;
	
	private MultiPlayDataBase multiPlayDataBase = null;
	
	private static SocketMainWiFiSender socketMainWifiThread = null;
	private static SocketMainBluetoothSender socketMainBluetoothThread = null;


	public static void runThread(boolean WiFiorBT ) throws IOException {
		if ( WiFiorBT = WIRELESS ) {
			Log.d("THREAD","Run wifi thread...");
			socketMainWifiThread = new SocketMainWiFiSender((WirelessConfigurationClass) mainNetworkConfiguration);
			socketMainWifiThread.execute(N.Signal.NEED_CONNECTION);
		} else {
			Log.d("THREAD","Run BT thread...");
			socketMainBluetoothThread = new SocketMainBluetoothSender((BluetoothConfigurationClass) mainNetworkConfiguration);
			socketMainBluetoothThread.execute(N.Signal.NEED_CONNECTION);
		}
		connectedTo = WiFiorBT;
	}

	public static void closeThread() {
		Log.d("THREAD","Stoping thread...");
		if (socketMainWifiThread != null  || socketMainBluetoothThread != null ) {
			add(N.Exit.EXIT_NO_ERROR);
		}
	}

	public static void add(int signal) {
		if (socketMainWifiThread != null) {
			synchronized (socketMainWifiThread) {
				SocketMainWiFiSender.queue.add(signal);
				Log.d("THREAD","Added "+signal);
				Log.d("THREAD","Executing "+SocketMainWiFiSender.queue.size()+" signals...");
			
				MultiPlayApplication.getSocketMainWifiThread().notify();
			}
			Log.d("THREAD","Finish.");
		} else {
			synchronized (socketMainBluetoothThread) {
				SocketMainBluetoothSender.queue.add(signal);
				Log.d("THREAD","Added "+signal);
				Log.d("THREAD","Executing "+SocketMainBluetoothSender.queue.size()+" signals...");
			
				MultiPlayApplication.getSocketMainBluetoothThread().notify();
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
			getMultiPlayRequirementsFromDB();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void loadConnectionListFromDB() throws InstantiationException, IllegalAccessException {
		loadWirelessConnectionListFromDB();
		loadBluetoothConnectionListFromDB();
	}

	private void loadWirelessConnectionListFromDB () throws InstantiationException, IllegalAccessException {
		Cursor cursor = dbHelper.sql_select_by_id(NetworkWiFiSettings.class,true);
		int deviceIndex = 0;
		int deviceCount = 0;
		discoveredWirelessDevices.addAll(
				DBHelper.parseNetworkWiFiSettings(cursor));
		deviceCount = discoveredWirelessDevices.size();
		
		for ( deviceIndex = 0; deviceIndex < deviceCount; deviceIndex += 1 ) {
			new CheckConnectionStatus().execute(discoveredWirelessDevices.get(deviceIndex));
		}
	}
	
	private void loadBluetoothConnectionListFromDB () throws InstantiationException, IllegalAccessException {
		Cursor cursor = dbHelper.sql_select_by_id(NetworkBTSettings.class,true);
		int deviceIndex = 0;
		int deviceCount = 0;
		discoveredBluetoothDevices.addAll(
				DBHelper.parseNetworkBTSettings(cursor));
		deviceCount = discoveredBluetoothDevices.size();
		
		for ( deviceIndex = 0; deviceIndex < deviceCount; deviceIndex += 1 ) {
			new CheckConnectionStatus().execute(discoveredBluetoothDevices.get(deviceIndex));
		}
	}
	
	private void getMultiPlayRequirementsFromDB() throws InstantiationException, IllegalAccessException {
		if(isFirstStart() == true) {
			Cursor cursor = dbHelper.sql_select_by_id(General.class, DBHelper.REOPEN_YES);
			multiPlayRequirements = DBHelper.parseMultiPlayRequirements(cursor);
		}
	}

	public final static boolean isFirstStart() {
		try {
			return !MultiPlayApplication.getDbHelper().sql_select_by_id(General.class, true).moveToFirst();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
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
////////////////////////////////////////////
	public static final void setMainNetworkConfiguration(
			ConnectionsConfigurationClass mainNetworkConfiguration) {
		
		MultiPlayApplication.mainNetworkConfiguration = mainNetworkConfiguration;
		if (mainNetworkConfiguration instanceof WirelessConfigurationClass) {
			Log.d("ListView","Name: "+mainNetworkConfiguration.getName());
			Log.d("ListView","ConStatus: "+mainNetworkConfiguration.getConnectionStatus());
			Log.d("ListView","IP: "+((WirelessConfigurationClass) mainNetworkConfiguration).getIP());
			Log.d("ListView","Port: "+((WirelessConfigurationClass) mainNetworkConfiguration).getPort());
			Log.d("ListView","Stored index: "+mainNetworkConfiguration.getStoredIndex());
		} else {
			Log.d("ListView","Name: "+mainNetworkConfiguration.getName());
			Log.d("ListView","ConStatus: "+mainNetworkConfiguration.getConnectionStatus());
			Log.d("ListView","UUID: "+((BluetoothConfigurationClass) mainNetworkConfiguration).getUuid().toString());
			Log.d("ListView","MAC: "+((BluetoothConfigurationClass) mainNetworkConfiguration).getAdress());
			Log.d("ListView","Stored index: "+mainNetworkConfiguration.getStoredIndex());
		}
	}

	public final MultiPlayDataBase getMultiPlayDataBase() {
		return multiPlayDataBase;
	}

	public final void setMultiPlayDataBase(MultiPlayDataBase multiPlayDataBase) {
		this.multiPlayDataBase = multiPlayDataBase;
	}

	
	/** WIFI - false, BT - true
	 * @return the connectedTo
	 */
	public static final boolean isConnectedTo() {
		return connectedTo;
	}

	/**
	 * @return the socketMainWifiThread
	 */
	public static final SocketMainWiFiSender getSocketMainWifiThread() {
		return socketMainWifiThread;
	}
	/**
	 * @param socketMainWifiThread the socketMainWifiThread to set
	 */
	public static final void setSocketMainWifiThread(
			SocketMainWiFiSender socketMainWifiThread) {
		MultiPlayApplication.socketMainWifiThread = socketMainWifiThread;
	}
	/**
	 * @return the socketMainBluetoothThread
	 */
	public static final SocketMainBluetoothSender getSocketMainBluetoothThread() {
		return socketMainBluetoothThread;
	}
	/**
	 * @param socketMainBluetoothThread the socketMainBluetoothThread to set
	 */
	public static final void setSocketMainBluetoothThread(
			SocketMainBluetoothSender socketMainBluetoothThread) {
		MultiPlayApplication.socketMainBluetoothThread = socketMainBluetoothThread;
	}
	/**
	 * @return the networkPriority
	 */
	public static final boolean isNetworkPriority() {
		return networkPriority;
	}

	/**
	 * @param networkPriority the networkPriority to set
	 */
	public static final void setNetworkPriority(boolean networkPriority) {
		MultiPlayApplication.networkPriority = networkPriority;
	}

	/**
	 * @return the bluetoothEnable
	 */
	public static final boolean isBluetoothEnable() {
		return bluetoothEnable;
	}

	/**
	 * @param bluetoothEnable the bluetoothEnable to set
	 */
	public static final void setBluetoothEnable(boolean bluetoothEnable) {
		MultiPlayApplication.bluetoothEnable = bluetoothEnable;
	}

	/**
	 * @return the wirelessEnable
	 */
	public static final boolean isWirelessEnable() {
		return wirelessEnable;
	}

	/**
	 * @param wirelessEnable the wirelessEnable to set
	 */
	public static final void setWirelessEnable(boolean wirelessEnable) {
		MultiPlayApplication.wirelessEnable = wirelessEnable;
	}
	
	
}
