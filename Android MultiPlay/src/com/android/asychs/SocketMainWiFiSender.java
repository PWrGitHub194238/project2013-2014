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
import com.android.application.WirelessConfigurationClass;



public class SocketMainWiFiSender extends AsyncTask<Byte, String, String> {

	private Socket socket = null;
	private DataOutputStream dos = null;
	private WirelessConfigurationClass mainConfiguration = null;
	
	public static LinkedBlockingQueue<Integer> queue = null;
	
	
	
	
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
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		Log.d("THREAD","onPreExecute");

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
		
		while(true) {
			try {
				Log.d("THREAD","isEmpty "+queue.isEmpty());
				while(queue.isEmpty() == false ) {
						int data = queue.take();
						Log.d("THREAD","doInBackground "+data);
						dos.writeInt(data);
				}
				this.wait();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}