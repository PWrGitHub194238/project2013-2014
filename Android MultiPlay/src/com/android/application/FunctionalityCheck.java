package com.android.application;

import java.util.HashMap;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.util.Log;

import com.android.database.tables.General;


public class FunctionalityCheck {

	static PackageManager packageManager = null;
	static SensorManager sensorManager = null;
	
	public final static int[] BTTestetDevices = {
		android.os.Build.VERSION_CODES.KITKAT
	};
	
	public FunctionalityCheck(Context context)
	{		
		packageManager = context.getPackageManager();
		sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
	}
	
	public HashMap<String, Integer> deviceCheck()
	{
		HashMap<String, Integer> returnedData=new HashMap<String,Integer>();
		
		returnedData.put(General.DBSchema.COLUMN_5,checkBluetooth());
		returnedData.put(General.DBSchema.COLUMN_6,checkBluetoothLE());
		returnedData.put(General.DBSchema.COLUMN_7,checkWifi());
		returnedData.put(General.DBSchema.COLUMN_8,checkWifiDirect());
		returnedData.put(General.DBSchema.COLUMN_9,checkAccelerometer());
		returnedData.put(General.DBSchema.COLUMN_10,checkGyroscope());
		returnedData.put(General.DBSchema.COLUMN_11,checkGravity());
		returnedData.put(General.DBSchema.COLUMN_12,checkVector());
		returnedData.put(General.DBSchema.COLUMN_13,checkAcceleration());
		returnedData.put(General.DBSchema.COLUMN_14,checkMicrophone());
		returnedData.put(General.DBSchema.COLUMN_15,checkMultitouch());

		
		return returnedData;
	}

	public int checkBluetooth() {
		if(!packageManager.hasSystemFeature(PackageManager.FEATURE_BLUETOOTH)) {
			Log.d("REQ","X FEATURE_BLUETOOTH");
			return 0;
		} else if (!isTestedSDK(android.os.Build.VERSION.SDK_INT)) {
			return 1;
		} else {
			return 2;
		}
	}
	
	public int checkBluetoothLE() {
		if(!packageManager.hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
			Log.d("REQ","X FEATURE_BLUETOOTH_LE");
			return 0;
		} else {
			return 2;
		}
	}
	
	public int checkWifi() {
		if(!packageManager.hasSystemFeature(PackageManager.FEATURE_WIFI)) {
			Log.d("REQ","X FEATURE_WIFI");
			return 0;
		} else {
			return 2;
		}
	}
	
	public int checkWifiDirect() {
		if(!packageManager.hasSystemFeature(PackageManager.FEATURE_WIFI_DIRECT)) {
			Log.d("REQ","X FEATURE_WIFI_DIRECT");
			return 0;
		} else {
			return 2;
		}
	}
	
	public int checkAccelerometer() {
		if(sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) == null) {
			Log.d("REQ","X TYPE_ACCELEROMETER");
			return 0;
		} else {
			return 2;
		}
	}
	
	public int checkGyroscope() {
		if(sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE) == null) {
			Log.d("REQ","X TYPE_GYROSCOPE");
			return 0;
		} else {
			return 2;
		}
	}
	
	public int checkGravity() {
		if(sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY) == null) {
			Log.d("REQ","X TYPE_GRAVITY");
			return 0;
		} else {
			return 2;
		}
	}
	
	public int checkVector() {
		if(sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR) == null) {
			Log.d("REQ","X TYPE_ROTATION_VECTOR");
			return 0;
		} else {
			return 2;
		}
	}
	
	public int checkAcceleration() {
		if(sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION) == null) {
			Log.d("REQ","X TYPE_LINEAR_ACCELERATION");
			return 0;
		} else {
			return 2;
		}
	}
	
	private boolean isTestedSDK(int sdkInt) {
		int versionsCount = BTTestetDevices.length;
		int i;
		for (i = 0; i < versionsCount; i += 1) {
			if (sdkInt == BTTestetDevices[i]) {
				return true;
			}
		}
		return false;
	}

	public int checkMicrophone() {
		if(!packageManager.hasSystemFeature(PackageManager.FEATURE_MICROPHONE)) {
			Log.d("REQ","X FEATURE_MICROPHONE");
			return 0;
		} else {
			return 2;
		}
	}

	public int checkMultitouch() {
		if(packageManager.hasSystemFeature(PackageManager.FEATURE_TOUCHSCREEN_MULTITOUCH)) {
			if(packageManager.hasSystemFeature(PackageManager.FEATURE_TOUCHSCREEN_MULTITOUCH_DISTINCT) || packageManager.hasSystemFeature(PackageManager.FEATURE_TOUCHSCREEN_MULTITOUCH_JAZZHAND)) {
				return 2;
			} else {
				return 1;
			}
		} else {
			Log.d("REQ","X FEATURE_TOUCHSCREEN_MULTITOUCH");
			return 0;
		}
	}
}
