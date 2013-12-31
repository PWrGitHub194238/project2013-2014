package com.android.services;

import java.util.HashMap;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;


public class FunctionalityCheck {

	static Context context;
	static PackageManager pm;
	static SensorManager sensorManager;
	
	FunctionalityCheck()
	{		
	}
	
	public static HashMap<String, Boolean> deviceCheck(Context cont)
	{
		context=cont;
		pm=context.getPackageManager();
		sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
		
		HashMap<String, Boolean> returnedData=new HashMap<String,Boolean>();
		
		returnedData.put("TYPE_ACCELEROMETER",Boolean.valueOf(checkAccelerometer()));
		returnedData.put("TYPE_GYROSCOPE",Boolean.valueOf(checkGyroscope()));
		returnedData.put("TYPE_GRAVITY",Boolean.valueOf(checkGravity()));
		returnedData.put("TYPE_ROTATION_VECTOR",Boolean.valueOf(checkVector()));
		returnedData.put("TYPE_LINEAR_ACCELERATION",Boolean.valueOf(checkAcceleration()));
		returnedData.put("FEATURE_BLUETOOTH",Boolean.valueOf(checkBluetooth()));
		returnedData.put("FEATURE_BLUETOOTH_LE",Boolean.valueOf(checkBluetoothLE()));
		returnedData.put("FEATURE_WIFI",Boolean.valueOf(checkWifi()));
		returnedData.put("FEATURE_WIFI_DIRECT",Boolean.valueOf(checkWifiDirect()));
		
		return returnedData;
	}
	
	public static boolean checkAccelerometer()
	{
		if(sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)==null)
			return false;
		else
			return true;
	}
	
	public static boolean checkGyroscope()
	{
		if(sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)==null)
			return false;
		else
			return true;
	}
	
	public static boolean checkGravity()
	{
		if(sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY)==null)
			return false;
		else
			return true;
	}
	
	public static boolean checkVector()
	{
		if(sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR)==null)
			return false;
		else
			return true;
	}
	
	public static boolean checkAcceleration()
	{
		if(sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION)==null)
			return false;
		else
			return true;
	}
	
	public static boolean checkBluetooth()
	{
		if(!pm.hasSystemFeature(PackageManager.FEATURE_BLUETOOTH))
			return false;
		else
			return true;
	}

	public static boolean checkBluetoothLE()
	{
		if(!pm.hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE))
			return false;
		else
			return true;
	}
	
	public static boolean checkWifi()
	{
		if(!pm.hasSystemFeature(PackageManager.FEATURE_WIFI))
			return false;
		else
			return true;
	}
	
	public static boolean checkWifiDirect()
	{
		if(!pm.hasSystemFeature(PackageManager.FEATURE_WIFI_DIRECT))
			return false;
		else
			return true;
	}
}
