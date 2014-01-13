package com.android.application;

import java.util.HashMap;

import com.android.database.tables.General;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;


public class FunctionalityCheck {

	static PackageManager packageManager = null;
	static SensorManager sensorManager = null;
	
	public FunctionalityCheck(Context context)
	{		
		packageManager = context.getPackageManager();
		sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
	}
	
	public HashMap<String, Boolean> deviceCheck()
	{
		HashMap<String, Boolean> returnedData=new HashMap<String,Boolean>();
		
		returnedData.put(General.DBSchema.COLUMN_5,Boolean.valueOf(checkBluetooth()));
		returnedData.put(General.DBSchema.COLUMN_6,Boolean.valueOf(checkBluetoothLE()));
		returnedData.put(General.DBSchema.COLUMN_7,Boolean.valueOf(checkWifi()));
		returnedData.put(General.DBSchema.COLUMN_8,Boolean.valueOf(checkWifiDirect()));
		returnedData.put(General.DBSchema.COLUMN_9,Boolean.valueOf(checkAccelerometer()));
		returnedData.put(General.DBSchema.COLUMN_10,Boolean.valueOf(checkGyroscope()));
		returnedData.put(General.DBSchema.COLUMN_11,Boolean.valueOf(checkGravity()));
		returnedData.put(General.DBSchema.COLUMN_12,Boolean.valueOf(checkVector()));
		returnedData.put(General.DBSchema.COLUMN_13,Boolean.valueOf(checkAcceleration()));
		
		return returnedData;
	}
	
	public boolean checkAccelerometer()
	{
		if(sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)==null)
			return false;
		else
			return true;
	}
	
	public boolean checkGyroscope()
	{
		if(sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)==null)
			return false;
		else
			return true;
	}
	
	public boolean checkGravity()
	{
		if(sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY)==null)
			return false;
		else
			return true;
	}
	
	public boolean checkVector()
	{
		if(sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR)==null)
			return false;
		else
			return true;
	}
	
	public boolean checkAcceleration()
	{
		if(sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION)==null)
			return false;
		else
			return true;
	}
	
	public boolean checkBluetooth()
	{
		if(!packageManager.hasSystemFeature(PackageManager.FEATURE_BLUETOOTH))
			return false;
		else
			return true;
	}

	public boolean checkBluetoothLE()
	{
		if(!packageManager.hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE))
			return false;
		else
			return true;
	}
	
	public boolean checkWifi()
	{
		if(!packageManager.hasSystemFeature(PackageManager.FEATURE_WIFI))
			return false;
		else
			return true;
	}
	
	public boolean checkWifiDirect()
	{
		if(!packageManager.hasSystemFeature(PackageManager.FEATURE_WIFI_DIRECT))
			return false;
		else
			return true;
	}
}
