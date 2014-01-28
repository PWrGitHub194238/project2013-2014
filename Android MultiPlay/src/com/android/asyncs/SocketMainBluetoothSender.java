package com.android.asyncs;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.AsyncTask;
import android.util.Log;

import com.android.application.BluetoothConfigurationClass;
import com.android.application.N;
import com.android.application.N.Helper;



/**
 * @author tomasz
 *
 */
public class SocketMainBluetoothSender extends AsyncTask<Byte, String, String> {

	/**
	 * 
	 */
	private BluetoothSocket bluetoothSocket = null;
	/**
	 * 
	 */
	private DataOutputStream dos = null;
	/**
	 * 
	 */
	private DataInputStream dis = null;
	/**
	 * 
	 */
	private BluetoothConfigurationClass mainConfiguration = null;
	
	/**
	 * 
	 */
	public static LinkedBlockingQueue<Integer> queue = null;
	/**
	 * 
	 */
	public static Boolean isRuning = false;
	
	
	
	
	/**
	 * @param mainNetworkConfiguration
	 * @throws IOException
	 */
	public SocketMainBluetoothSender(BluetoothConfigurationClass mainNetworkConfiguration) throws IOException {
		super();
		this.mainConfiguration = mainNetworkConfiguration;
		queue = new LinkedBlockingQueue<Integer>();
		Log.d("THREAD","sender starts...");
	
	}

	/* (non-Javadoc)
	 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
	 */
	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		Log.d("THREAD","sender stoped.");
	}

	/* (non-Javadoc)
	 * @see android.os.AsyncTask#onPreExecute()
	 */
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		Log.d("THREAD","onPreExecute");
		isRuning = true;

	}

	/* (non-Javadoc)
	 * @see android.os.AsyncTask#onProgressUpdate(Progress[])
	 */
	@Override
	protected void onProgressUpdate(String... values) {
		// TODO Auto-generated method stub
		super.onProgressUpdate(values);
	}

	/* (non-Javadoc)
	 * @see android.os.AsyncTask#doInBackground(Params[])
	 */
	@Override
	protected synchronized String doInBackground(Byte... Params) {
		BluetoothDevice bluetoothDevice = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(
				mainConfiguration.getAdress());
		UUID uuid = mainConfiguration.getUuid();
		try {
			Log.d("THREAD",bluetoothDevice.getName() + "\n" + bluetoothDevice.getAddress());

			Log.d("THREAD",uuid.toString());
		    bluetoothSocket = bluetoothDevice.createRfcommSocketToServiceRecord(uuid);
		    
		    bluetoothSocket.connect();
			dos = new DataOutputStream(bluetoothSocket.getOutputStream());
			if (dos == null) {
				Log.d("THREAD","NUUUUUUUUUUUUUL");

			} else {
				Log.d("THREAD","OKKKKKKKKKKKK");

			}
			dos.writeByte(
					N.Signal.NEED_CONNECTION);
			dis = new DataInputStream(bluetoothSocket.getInputStream());
			int port = dis.readInt();
			Log.d("THREAD","Port: "+port);
			//dis.close();
			Log.d("THREAD","BT 1");
			//dos.close();
			Log.d("THREAD","BT 2");
			//bluetoothSocket.close();
			Log.d("THREAD","BT 3");
			
			Thread.sleep(5000);
			uuid = BluetoothConfigurationClass.generateUUIDfromMAC(BluetoothConfigurationClass.Profiles.inne, bluetoothDevice.getAddress());
			Log.d("THREAD","BT 4");
			bluetoothSocket = bluetoothDevice.createRfcommSocketToServiceRecord(uuid);
		    
Log.d("THREAD","BT CONNECT");

		    bluetoothSocket.connect();
			Log.d("THREAD","BT 5");

		    this.dos = new DataOutputStream(bluetoothSocket.getOutputStream());
			Log.d("THREAD","BT 6");

			
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Log.d("THREAD","Sender created.");
		int data = 0;
		int i = 0;
		while(isRuning) {
			try {
				Log.d("THREAD","isEmpty "+queue.isEmpty());
				while(queue.isEmpty() == false ) {
						data = queue.take();
						Log.d("THREAD","doInBackground "+data);
						int[] out = Helper.decodeSignal(data);
						Log.d("THREAD"," > Encoded: DEV: "+out[0]+" C: "+out[1]+" X: "+out[2]+" Y: "+out[3]);
						dos.writeInt(data);
					//	i += 1;
					//	Log.d("THREAD","doInBackground "+data);
						//Log.d("THREAD",String.valueOf(i));
				}
				if ( data != N.Exit.EXIT_NO_ERROR ) {
					this.wait();
				} else {
					isRuning = false;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Log.d("THREAD","sender stoped.");
		return null;
	}
}
