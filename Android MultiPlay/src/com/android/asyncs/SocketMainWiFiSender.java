package com.android.asyncs;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.LinkedBlockingQueue;

import android.os.AsyncTask;
import android.util.Log;

import com.android.application.N;
import com.android.application.WirelessConfigurationClass;

/** The main thread to send data via Wi-fi from your mobile device to the server on a desktop computer. 
 * A thread is put to sleep, when completed all tasks, sends matches any signals. 
 * At the moment there is a new signal, thread is awakened.
 * 
 * @author tomasz
 *
 */
public class SocketMainWiFiSender extends AsyncTask<Byte, String, String> {

	/**
	 * 
	 */
	private Socket socket = null;
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
	private WirelessConfigurationClass mainConfiguration = null;
	
	/**
	 * 
	 */
	public static LinkedBlockingQueue<Integer> queue = null;
	/**
	 * 
	 */
	public static Boolean isRuning = false;
	
	
	
	
	/**
	 * @param mainConfiguration
	 * @throws IOException
	 */
	public SocketMainWiFiSender(WirelessConfigurationClass mainConfiguration) throws IOException {
		super();
		this.mainConfiguration = mainConfiguration;
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
		try {
			this.socket = new Socket(
					InetAddress.getByName(mainConfiguration.getIP()),
					mainConfiguration.getPort());
			this.dos = new DataOutputStream(socket.getOutputStream());
			dos.writeByte(Params[0]);
			this.dis = new DataInputStream(socket.getInputStream());
			int port = dis.readInt();
			Log.d("THREAD","Port: "+port);
			dis.close();
			dos.close();
			socket.close();
			this.socket = new Socket(
					InetAddress.getByName(mainConfiguration.getIP()),
					port);
			this.dos = new DataOutputStream(socket.getOutputStream());
			
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Log.d("THREAD","Sender created.");
		int data = 0;
		while(isRuning) {
			try {
			//	Log.d("THREAD","isEmpty "+queue.isEmpty());
				while(queue.isEmpty() == false ) {
						data = queue.take();
						//Log.d("THREAD","doInBackground "+data);
					//	int[] out = Helper.decodeSignal(data);
					//	Log.d("THREAD"," > Encoded: DEV: "+out[0]+" C: "+out[1]+" X: "+out[2]+" Y: "+out[3]);
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
