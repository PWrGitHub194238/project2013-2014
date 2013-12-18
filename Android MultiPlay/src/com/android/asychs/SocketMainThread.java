package com.android.asychs;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.BlockingQueue;

import android.os.AsyncTask;
import android.util.Log;



public class SocketMainThread extends AsyncTask<String, String, String> {

	
	
	public SocketMainThread(String string, BlockingQueue<Byte> socketQueue) {
		// TODO Auto-generated constructor stub
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
	protected synchronized String doInBackground(String... arg0) {
		Log.d("THREAD","doInBackground "+arg0[0]);
		try {
			Socket socket = new Socket(InetAddress.getByName("192.168.1.104"),8000);
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			while(true) {
			dos.write(1);
			dos.flush();
			Log.d("THREAD","doInBackground "+arg0[0]);
			this.wait();
			}
		} catch (UnknownHostException e) {
			Log.d("ERROR","UnknownHostException");
			e.printStackTrace();
		} catch (IOException e) {
			Log.d("ERROR","IOException");
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}