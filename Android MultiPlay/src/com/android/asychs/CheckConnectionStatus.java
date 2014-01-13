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
	
	private Handler mHandler = new Handler() {

	    @Override
	    public void handleMessage(Message msg) {
	    	Log.d("THREAD","Timeout...");
	    	CheckConnectionStatus.this.cancel(true);
	    }
	};
	
	@Override
	protected synchronized String doInBackground(ConnectionsConfigurationClass... params) {
		byte recivedAuthorizationConfirmation = 0;
		int recivedScreenDimensions = 0;
		networkConfiguration = params[0];
			
		if ( networkConfiguration instanceof WirelessConfigurationClass ) {
			WirelessConfigurationClass wirelessConfiguration = (WirelessConfigurationClass) networkConfiguration;
			try {
				Log.d("THREAD","Socket... Checking WiFi connection:"+wirelessConfiguration.getName());
				
				mHandler.sendEmptyMessageDelayed(0, 3000);
				socket = new Socket(
						InetAddress.getByName(wirelessConfiguration.getIP()),
						wirelessConfiguration.getPort());
				Log.d("THREAD","dis...");
				dis = new DataInputStream(socket.getInputStream());
				Log.d("THREAD","dos...");
				dos = new DataOutputStream(socket.getOutputStream());
				
				Log.d("THREAD","Read...");
				dos.writeByte(N.Signal.NEED_AUTHORIZATION);
				recivedAuthorizationConfirmation = dis.readByte();
				
				Log.d("THREAD","RECIVED: "+recivedAuthorizationConfirmation);
				Log.d("THREAD","Decoded: S: "+N.Signal.decodeSystem(recivedAuthorizationConfirmation)+" I: "+N.Signal.decodeSignal(recivedAuthorizationConfirmation));
				
				if ( N.Signal.NEED_AUTHORIZATION == N.Signal.decodeSignal(recivedAuthorizationConfirmation) ) {
					networkConfiguration.setConnectionStatus(ConnectionHelper.STATUS_ON);
					networkConfiguration.setSystem(N.Signal.decodeSystem(recivedAuthorizationConfirmation));
				} else {
					Log.d("THREAD","RECIVED: ERROR");

					dis.close();
					dos.close();
					socket.close();
				}
				
				recivedScreenDimensions = dis.readInt();
				int[] data = N.Helper.decodeSignal(recivedScreenDimensions);
				Log.d("THREAD","RECIVED addidtional data: X: "+data[2] + " Y: "+data[3]+"\n");
				networkConfiguration.setSystemDimmensionX(data[2]);
				networkConfiguration.setSystemDimmensionY(data[3]);

				dis.close();
				dos.close();
				socket.close();
				
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else if ( networkConfiguration instanceof BluetoothConfigurationClass ) {
			
		}
		return null;
	}

	@Override
	protected void onCancelled() {
		// TODO Auto-generated method stub
		super.onCancelled();
		Log.d("THREAD","Interupted.");
	}

	
}
