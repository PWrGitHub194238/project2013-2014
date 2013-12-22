package com.android.asychs;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.android.application.BluetoothConfigurationClass;
import com.android.application.ConnectionsConfigurationClass;
import com.android.application.N;
import com.android.application.WirelessConfigurationClass;
import com.android.services.ConnectionHelper;

public class CheckConnectionStatus extends AsyncTask<ConnectionsConfigurationClass, String, String> {

	private Socket socket = null;
	private DataInputStream dis = null;
	private DataOutputStream dos = null;
	private ConnectionsConfigurationClass networkConfiguration = null;
	
	private android.os.Handler mHandler = new android.os.Handler() {

	    @Override
	    public void handleMessage(Message msg) {
	    	Log.d("THREAD","Timeout...");
	    	CheckConnectionStatus.this.cancel(true);
	    }
	};
	
	@Override
	protected synchronized String doInBackground(ConnectionsConfigurationClass... params) {
		networkConfiguration = params[0];
		if ( networkConfiguration instanceof WirelessConfigurationClass ) {
			WirelessConfigurationClass wirelessConfiguration = (WirelessConfigurationClass) networkConfiguration;
			try {
				Log.d("THREAD","Socket...");
				
				mHandler.sendEmptyMessageDelayed(0, 2000);
				
				socket = new Socket(
						InetAddress.getByName(wirelessConfiguration.getIP()),
						wirelessConfiguration.getPort());
				Log.d("THREAD","dis...");
				dis = new DataInputStream(socket.getInputStream());
				Log.d("THREAD","dos...");
				dos = new DataOutputStream(socket.getOutputStream());
				Log.d("THREAD","Read...");
				dos.write(N.signal.need_authorization);
				byte recived = dis.readByte();
				Log.d("THREAD","RECIVED: "+recived);
				dis.close();
				dos.close();
				//socket.close();
				if ( N.signal.need_authorization == recived ) {
					networkConfiguration.setConnectionStatus(ConnectionHelper.STATUS_ON);
				}
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if ( networkConfiguration instanceof BluetoothConfigurationClass ) {
			
		}
//		SocketMainThread th = MultiPlayApplication.getSocketMainThread();
//		synchronized (th) {
//		MultiPlayApplication.getSocketMainThread().notify();
//		}
		return null;
	}

	@Override
	protected void onCancelled() {
		// TODO Auto-generated method stub
		super.onCancelled();
		Log.d("THREAD","Interupted.");
	}

	
}
