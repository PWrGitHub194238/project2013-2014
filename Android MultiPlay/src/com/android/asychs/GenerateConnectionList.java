package com.android.asychs;

import java.util.Collection;
import java.util.Set;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;

import com.android.extendedWidgets.lists.ElementOfConnectionsList;
import com.android.services.ConnectionHelper;

public class GenerateConnectionList extends AsyncTask<String, String, String> {
		 
	private Collection<ElementOfConnectionsList> listOfElements = null;
	private ProgressBar progressBar = null;
			
	public GenerateConnectionList(Collection<ElementOfConnectionsList> listOfElements, ProgressBar progressBar) {
		super();
		this.listOfElements = listOfElements;
		this.progressBar = progressBar;
		Log.d("connections","BT IS OK 1");
	}

	@Override
	protected void onProgressUpdate(String... values) {
		super.onProgressUpdate(values);
// aktualizacja stanu podczas dzia³ania, tutaj zmiana tresci textview na inny
// w values bêdziemy przesy³aæ tylko jedna wartosc
		}
	 
	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
// dzia³ania po wykonaniu zdania w doInBackground
// ta metoda dzia³a w UI, wiêc mo¿esz zmieniaæ ró¿ne elementy!
		progressBar.setVisibility(ProgressBar.INVISIBLE);
	}
	 
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
// dzia³ania przed wykonaniem zadañ
// ta metoda dzia³a w UI, wiêc mo¿esz zmieniaæ ró¿ne elementy!
	}
	 
	@Override
	protected String doInBackground(String... params) {
// zadanie do wykonania
// tutaj nie mo¿esz nic zmieniaæ w UI, tylko wykonywaæ obliczenia i zadania
 
// Dla wydajnoœci zapisujemy wielkosc tablicy przed pêtl¹
		Log.d("connections","BT IS OK 2");
		BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
		// If there are paired devices
		if (pairedDevices.size() > 0) {
		    // Loop through paired devices
		    for (BluetoothDevice device : pairedDevices) {
		        // Add the name and address to an array adapter to show in a ListView
		        listOfElements.add(new ElementOfConnectionsList(
		        		"AAA"+device.getName(), device.getAddress(), ConnectionHelper.STATUS_NOT_IN_RANGE,
						ElementOfConnectionsList.STORED_NO,
						ConnectionHelper.CONNECTION_TYPE_BT));
		    }
		}
		Log.d("connections","BT IS OK 3");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "Wykonano wszystkie dzia³ania, ³¹cznie ";
	}
}
