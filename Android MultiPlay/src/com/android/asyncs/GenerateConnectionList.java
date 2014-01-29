package com.android.asyncs;

import java.util.Set;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.AsyncTask;
import android.util.Log;

import com.android.application.BluetoothConfigurationClass;
import com.android.application.MultiPlayApplication;
import com.android.application.N;
import com.android.dialogs.AsyncTaskDialog;
import com.android.services.ConnectionHelper;

/** Thread searches for available connections to servers based on the ability to detect devices through a mobile device. 
 * 
 * Adds found a connection to the appropriate list.
 * 
 * @author tomasz
 *
 */

public class GenerateConnectionList extends AsyncTask<String, String, Integer> {
			
	/**
	 * 
	 */
	private AsyncTaskDialog dialog = null;
	/**
	 * 
	 */
	private OnAsyncTaskFinished activity = null;
	/**
	 * 
	 */
	private Integer asyncCallReason = null;
	/**
	 * 
	 */
	private Integer asyncCallOnError = null;
	
	/**
	 * @param activity
	 * @param dialog
	 * @param asyncCallReason
	 * @param asyncCallOnerror
	 */
	public GenerateConnectionList(OnAsyncTaskFinished activity, AsyncTaskDialog dialog, Integer asyncCallReason, Integer asyncCallOnerror) {
		this.activity = activity;
		this.dialog = dialog;
		this.asyncCallReason = asyncCallReason;
		this.asyncCallOnError = asyncCallOnerror;
	};

	@Override
	protected Integer doInBackground(String... params) {
		Log.d("connections","BT IS OK 2");
		publishProgress("Establishing connection...");
		
		BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
		
		// If there are paired devices
		if (pairedDevices.size() > 0) {
		    // Loop through paired devices
		    for (BluetoothDevice device : pairedDevices) {
		        BluetoothConfigurationClass btConfiguration = new BluetoothConfigurationClass(
		        		BluetoothConfigurationClass.generateUUIDfromMAC(
		    					BluetoothConfigurationClass.Profiles.SSP,
		    					device.getAddress()), device.getAddress());
				Log.d("Connections","> UUID: " + btConfiguration.getUuid());
				Log.d("Connections","> Address: " + btConfiguration.getAdress());
	
				btConfiguration.setName(device.getName());
				
				btConfiguration.setStored(false);
				
				btConfiguration.setConnectionStatus(ConnectionHelper.STATUS_WARNING);
				btConfiguration.setSystem(N.System.UNKNOW);
				
				new CheckConnectionStatus().execute(btConfiguration);

				MultiPlayApplication.getDiscoveredBluetoothDevices().add(btConfiguration);

		    }
		}
		Log.d("connections","BT IS OK 3");
		
		return asyncCallReason;
	}
	
	/* (non-Javadoc)
	 * @see android.os.AsyncTask#onProgressUpdate(Progress[])
	 */
	/* (non-Javadoc)
	 * @see android.os.AsyncTask#onProgressUpdate(Progress[])
	 */
	@Override
	protected void onProgressUpdate(String... values) {
		super.onProgressUpdate(values);
		updateDialogLogStatus(values[0]);
	}

	/* (non-Javadoc)
	 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
	 */
	/* (non-Javadoc)
	 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
	 */
	@Override
	protected void onPostExecute(Integer result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		Log.d("THREAD","Thread closing.");
		returnToActivity(result);
		dismissDialog();
	}

	/* (non-Javadoc)
	 * @see android.os.AsyncTask#onCancelled(java.lang.Object)
	 */
	/* (non-Javadoc)
	 * @see android.os.AsyncTask#onCancelled(java.lang.Object)
	 */
	@Override
	protected void onCancelled(Integer result) {
		// TODO Auto-generated method stub
		super.onCancelled(result);
		Log.d("THREAD","Interupted by: " + asyncCallOnError);
		returnToActivity(asyncCallOnError);
		dismissDialog();
	}

	/**
	 * @param asyncCallReason
	 */
	private void returnToActivity(int asyncCallReason) {
		if ( activity != null ) {
			activity.onBackgroundFinished(
					asyncCallReason);
		}
	}
	
	/**
	 * @param message
	 */
	private void updateDialogLogStatus(String message) {
		if (dialog != null ) {
			dialog.updateDialogLogStatus(message);
		}
	}
	
	/**
	 * 
	 */
	private void dismissDialog() {
		if (dialog != null ) {
			dialog.dismiss();
		}
	}

}