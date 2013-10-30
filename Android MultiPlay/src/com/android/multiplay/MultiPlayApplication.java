package com.android.multiplay;

import java.net.Socket;

import android.app.Application;
import android.bluetooth.BluetoothSocket;

public class MultiPlayApplication extends Application {

	public MultiPlayApplication() {
		super();
	}
	
	private Socket wirelessSocket = null;
	private  BluetoothSocket bluetoothSocket = null;
	
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
}
