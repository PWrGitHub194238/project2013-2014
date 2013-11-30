package com.android.application;

import java.net.Socket;

import com.android.database.DBHelper;
import com.android.database.MultiPlayDataBase;

import android.app.Application;
import android.bluetooth.BluetoothSocket;

public class MultiPlayApplication extends Application {

	private DBHelper dbHelper = null;
	
	@Override
	public void onCreate() {
		super.onCreate();
	    
		dbHelper = new DBHelper(this.getApplicationContext());
		dbHelper.openConnection();
		
	}
	
	public void onDestroy() {

		dbHelper.closeConnection();
		
	}
	
	public final DBHelper getDbHelper() {
		return dbHelper;
	}

	private Socket wirelessSocket = null;
	private  BluetoothSocket bluetoothSocket = null;
	private MultiPlayDataBase multiPlayDataBase = null;
	
	public Socket getWirelessSocket() {
		return wirelessSocket;
	}
	public void setWirelessSocket(Socket wirelessSocket) {
		this.wirelessSocket = wirelessSocket;
	}
	public BluetoothSocket getBluetoothSocket() {
		return bluetoothSocket;
	}
	public void setBluetoothSocket(BluetoothSocket bluetoothSocket) {
		this.bluetoothSocket = bluetoothSocket;
	}
	public MultiPlayDataBase getMultiPlayDataBase() {
		return multiPlayDataBase;
	}
	public void setMultiPlayDataBase(MultiPlayDataBase multiPlayDataBase) {
		this.multiPlayDataBase = multiPlayDataBase;
	}
}
