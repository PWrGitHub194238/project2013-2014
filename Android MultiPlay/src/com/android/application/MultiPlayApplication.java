package com.android.application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import android.app.Application;
import android.os.AsyncTask;
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
	private static ConnectionsConfigurationClass setMainConfiguration = null;
	private MultiPlayDataBase multiPlayDataBase = null;
	
	private static BlockingQueue<Byte> socketQueue = null;
	
	private static SocketMainWiFiSender socketMainThread = null;
	
	public static void runThread() throws IOException {
		Log.d("THREAD","Run thread...");
		socketQueue = new LinkedBlockingQueue<Byte>();
		socketMainThread = new SocketMainWiFiSender((WirelessConfigurationClass) setMainConfiguration);
		socketMainThread.execute(N.signal.need_to_connect);
	}
	
	public static void execute() {
	}
	
	public static void add(byte signal) {
		synchronized (socketMainThread) {
			socketMainThread.queue.add(signal);
			Log.d("THREAD","Added "+signal);
			Log.d("THREAD","Executing "+socketMainThread.queue.size()+" signals...");
		
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

	public static final ArrayList<WirelessConfigurationClass> getDiscoveredWirelessDevices() {
		return discoveredWirelessDevices;
	}


	public static ConnectionsConfigurationClass getSetMainConfiguration() {
		return setMainConfiguration;
	}

	public static void setSetMainConfiguration(ConnectionsConfigurationClass setMainConfiguration) {
		MultiPlayApplication.setMainConfiguration = setMainConfiguration;
		Log.d("ListView","Name: "+setMainConfiguration.getName());
		Log.d("ListView","ConStatus: "+setMainConfiguration.getConnectionStatus());
		Log.d("ListView","IP: "+((WirelessConfigurationClass) setMainConfiguration).getIP());
		Log.d("ListView","Port: "+((WirelessConfigurationClass) setMainConfiguration).getPort());
		Log.d("ListView","Stored index: "+setMainConfiguration.getStoredIndex());
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
