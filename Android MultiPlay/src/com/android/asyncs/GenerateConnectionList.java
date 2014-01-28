package com.android.asyncs;

import java.util.Collection;
import java.util.Set;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;

import com.android.extendedWidgets.lists.ConnectionsListItem;

/** Topic searches for available connections to servers based on the ability to detect devices through a mobile device. 
 * 
 * Adds found a connection to the appropriate list.
 * 
 * @author tomasz
 *
 */

public class GenerateConnectionList extends AsyncTask<String, String, String> {
		 
	/**
	 * 
	 */
	private Collection<ConnectionsListItem> listOfElements = null;
	/**
	 * 
	 */
	private ProgressBar progressBar = null;
			
	/**
	 * @param listOfElements
	 */
	public GenerateConnectionList(Collection<ConnectionsListItem> listOfElements) {
		super();
		this.listOfElements = listOfElements;
		this.progressBar = progressBar;
		Log.d("connections","BT IS OK 1");
	}

	/* (non-Javadoc)
	 * @see android.os.AsyncTask#onProgressUpdate(Progress[])
	 */
	@Override
	protected void onProgressUpdate(String... values) {
		super.onProgressUpdate(values);
		}
	 
	/* (non-Javadoc)
	 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
	 */
	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		progressBar.setVisibility(ProgressBar.INVISIBLE);
	}
	 
	/* (non-Javadoc)
	 * @see android.os.AsyncTask#onPreExecute()
	 */
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}
	 
	/* (non-Javadoc)
	 * @see android.os.AsyncTask#doInBackground(Params[])
	 */
	@Override
	protected String doInBackground(String... params) {
		Log.d("connections","BT IS OK 2");
		BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
		// If there are paired devices
		if (pairedDevices.size() > 0) {
		    // Loop through paired devices
		    for (BluetoothDevice device : pairedDevices) {
		        // Add the name and address to an array adapter to show in a ListView
//		        listOfElements.add(new ElementOfConnectionsList(0,
//		        		"AAA"+device.getName(), device.getAddress(), ConnectionHelper.STATUS_NOT_IN_RANGE,
//						ElementOfConnectionsList.STORED_NO,
//						N.System.WINDOWS,
//						-1,
//						-1,
//						ConnectionHelper.CONNECTION_TYPE_BT));
		    }
		}
		Log.d("connections","BT IS OK 3");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "Wykonano wszystkie działania";
	}
}
