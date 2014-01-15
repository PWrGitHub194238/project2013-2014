package com.android.asychs;

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



public class SocketMainBluetoothSender extends AsyncTask<Byte, String, String> {

	private BluetoothSocket bluetoothSocket = null;
	private DataOutputStream dos = null;
	private DataInputStream dis = null;
	private BluetoothConfigurationClass mainConfiguration = null;
	
	public static LinkedBlockingQueue<Integer> queue = null;
	public static Boolean isRuning = false;
	
	
	
	
	public SocketMainBluetoothSender(BluetoothConfigurationClass mainNetworkConfiguration) throws IOException {
		super();
		this.mainConfiguration = mainNetworkConfiguration;
		queue = new LinkedBlockingQueue<Integer>();
		Log.d("THREAD","sender starts...");
	
	}

	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		Log.d("THREAD","sender stoped.");
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		Log.d("THREAD","onPreExecute");
		isRuning = true;

	}

	@Override
	protected void onProgressUpdate(String... values) {
		// TODO Auto-generated method stub
		super.onProgressUpdate(values);
	}

	@Override
	protected synchronized String doInBackground(Byte... Params) {
		BluetoothDevice bluetoothDevice = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(
				mainConfiguration.getAdress());
		UUID uuid = mainConfiguration.getUuid();
		try {
			Log.d("THREAD",bluetoothDevice.getName() + "\n" + bluetoothDevice.getAddress());

			Log.d("THREAD",uuid.toString());
		    bluetoothSocket = bluetoothDevice.createRfcommSocketToServiceRecord(uuid);
			this.dos = new DataOutputStream(bluetoothSocket.getOutputStream());
			dos.writeByte(Params[0]);
			this.dis = new DataInputStream(bluetoothSocket.getInputStream());
			//int port = dis.readInt();
			//Log.d("THREAD","Port: "+port);
//			dis.close();
//			dos.close();
//			bluetoothSocket.close();
//			this.bluetoothSocket = new Socket(
//					InetAddress.getByName(mainConfiguration.getIP()),
//					port);
//			this.dos = new DataOutputStream(socket.getOutputStream());
			
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
