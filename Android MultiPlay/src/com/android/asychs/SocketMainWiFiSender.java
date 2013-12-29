package com.android.asychs;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.LinkedBlockingQueue;

import android.os.AsyncTask;
import android.util.Log;

import com.android.application.N;
import com.android.application.N.Helper;
import com.android.application.WirelessConfigurationClass;



public class SocketMainWiFiSender extends AsyncTask<Byte, String, String> {

	private Socket socket = null;
	private DataOutputStream dos = null;
	private WirelessConfigurationClass mainConfiguration = null;
	
	public static LinkedBlockingQueue<Integer> queue = null;
	public static Boolean isRuning = false;
	
	
	
	
	public SocketMainWiFiSender(WirelessConfigurationClass mainConfiguration) throws IOException {
		super();
		this.mainConfiguration = mainConfiguration;
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
		try {
			this.socket = new Socket(
					InetAddress.getByName(mainConfiguration.getIP()),
					mainConfiguration.getPort());
			this.dos = new DataOutputStream(socket.getOutputStream());
			dos.writeByte(N.Signal.NEED_CONNECTION);
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
				Log.d("THREAD","isEmpty "+queue.isEmpty());
				while(queue.isEmpty() == false ) {
						data = queue.take();
						Log.d("THREAD","doInBackground "+data);
						int[] out = Helper.decodeSignal(data);
						Log.d("THREAD","Encoded: DEV: "+out[0]+" C: "+out[1]+" X: "+out[2]+" Y: "+out[3]);
						dos.writeInt(data);
						Log.d("THREAD","doInBackground "+data);
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
