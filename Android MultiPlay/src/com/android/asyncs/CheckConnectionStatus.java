package com.android.asyncs;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.UUID;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.AsyncTask;
import android.util.Log;

import com.android.application.BluetoothConfigurationClass;
import com.android.application.ConnectionsConfigurationClass;
import com.android.application.N;
import com.android.application.WirelessConfigurationClass;
import com.android.dialogs.AsyncTaskDialog;
import com.android.services.ConnectionHelper;


/** Topic check the status of the selected call to the server. 
 * 
 * In case of failure the result is returned after a set time from the start of the connection attempt.
 * 
 * @author tomasz
 *
 */
public class CheckConnectionStatus extends AsyncTask<ConnectionsConfigurationClass, String, Integer> {

	/**
	 * 
	 */
	public static int TIMEOUT = 3000;
	
	/**
	 * 
	 */
	private Socket socket = null;
	/**
	 * 
	 */
	private InetSocketAddress socketAddress = null;
	/**
	 * 
	 */
	private WirelessConfigurationClass wirelessConfiguration = null;
	
	/**
	 * 
	 */
	private BluetoothSocket bluetoothSocket = null;
	/**
	 * 
	 */
	private BluetoothAdapter bluetoothAdapter = null;
	/**
	 * 
	 */
	private BluetoothDevice bluetoothDevice = null;
	/**
	 * 
	 */
	private UUID uuid = null;
	/**
	 * 
	 */
	private BluetoothConfigurationClass bluetoothConfiguration = null;
	
	/**
	 * 
	 */
	private DataInputStream dis = null;
	/**
	 * 
	 */
	private DataOutputStream dos = null;
	/**
	 * 
	 */
	byte recivedAuthorizationConfirmation = 0;
	
	/**
	 * 
	 */
	private ConnectionsConfigurationClass networkConfiguration = null;
	
	/**
	 * 
	 */
	private AsyncTaskDialog dialog = null;
	/**
	 * 
	 */
	private OnAsyncTaskFinished activity = null;
	/**
	 * 
	 */
	private Integer asyncCallReason = null;
	/**
	 * 
	 */
	private Integer asyncCallOnError = null;

	
	/**
	 * 
	 */
	public CheckConnectionStatus() {
		this.dialog = null;
		this.activity = null;
		this.asyncCallReason = OnAsyncTaskFinished.TAG.ConnectionActivity_Empty;
		this.asyncCallOnError = OnAsyncTaskFinished.TAG.ConnectionActivity_Empty;
	};

	/**
	 * @param activity
	 * @param dialog
	 */
	public CheckConnectionStatus(OnAsyncTaskFinished activity, AsyncTaskDialog dialog) {
		this.activity = activity;
		this.dialog = dialog;
		this.asyncCallReason = OnAsyncTaskFinished.TAG.ConnectionActivity_Empty;
		this.asyncCallOnError = OnAsyncTaskFinished.TAG.ConnectionActivity_Empty;
	};
	
	/**
	 * @param activity
	 * @param dialog
	 * @param asyncCallReason
	 */
	public CheckConnectionStatus(OnAsyncTaskFinished activity, AsyncTaskDialog dialog, Integer asyncCallReason) {
		this.activity = activity;
		this.dialog = dialog;
		this.asyncCallReason = asyncCallReason;
		this.asyncCallOnError = OnAsyncTaskFinished.TAG.ConnectionActivity_Empty;
	};
	
	/**
	 * @param activity
	 * @param dialog
	 * @param asyncCallReason
	 * @param asyncCallOnerror
	 */
	public CheckConnectionStatus(OnAsyncTaskFinished activity, AsyncTaskDialog dialog, Integer asyncCallReason, Integer asyncCallOnerror) {
		this.activity = activity;
		this.dialog = dialog;
		this.asyncCallReason = asyncCallReason;
		this.asyncCallOnError = asyncCallOnerror;
	};
	
	
	/* (non-Javadoc)
	 * @see android.os.AsyncTask#doInBackground(Params[])
	 */
	@Override
	protected synchronized Integer doInBackground(ConnectionsConfigurationClass... params) {

		networkConfiguration = params[0];
		networkConfiguration.setConnectionStatus(ConnectionHelper.STATUS_NOT_IN_RANGE);
		publishProgress("Establishing connection...");
			
		if ( networkConfiguration instanceof WirelessConfigurationClass ) {
			
			wirelessConfiguration = (WirelessConfigurationClass) networkConfiguration;

			Log.d("THREAD","Socket... Checking WiFi connection:"+wirelessConfiguration.getName());

			try {
				socketAddress = new InetSocketAddress(
						InetAddress.getByName(wirelessConfiguration.getIP()),
						wirelessConfiguration.getPort());
				
				socket = new Socket();
				socket.connect(socketAddress, TIMEOUT);
				
				Log.d("THREAD","dis...");
				dis = new DataInputStream(
						socket.getInputStream());
				
				Log.d("THREAD","dos...");
				dos = new DataOutputStream(
						socket.getOutputStream());
					
				publishProgress("Authorization...");

				Log.d("THREAD","Read...");
				dos.writeByte(
						N.Signal.NEED_AUTHORIZATION);
				
				recivedAuthorizationConfirmation = dis.readByte();
					
				Log.d("THREAD","RECIVED: "+recivedAuthorizationConfirmation);
				Log.d("THREAD","Decoded: S: "+N.Signal.decodeSystem(recivedAuthorizationConfirmation)+
						" I: "+N.Signal.decodeSignal(recivedAuthorizationConfirmation));
					
				if ( isAuthorizationSuccess(recivedAuthorizationConfirmation) == true ) {
					networkConfiguration.setConnectionStatus(ConnectionHelper.STATUS_ON);
					networkConfiguration.setSystem(N.Signal.decodeSystem(recivedAuthorizationConfirmation));
				} else {
					Log.d("THREAD","RECIVED: ERROR");
					publishProgress("Authorization error.");

					dis.close();
					dos.close();
					socket.close();
					return asyncCallOnError;
				}
				
				getServerScreenDimension(
						dis.readInt());
				
				dis.close();
				dos.close();
				socket.close();
				publishProgress("Connected.");
				return asyncCallReason;
				
			} catch (IOException e) {
				
				e.printStackTrace();
				Log.d("THREAD",String.valueOf(e.getStackTrace()[0].getLineNumber())+
						" "+e.getStackTrace()[0].getMethodName());
				publishProgress("Connection error.");
				this.cancel(true);
				
			}

		} else if ( networkConfiguration instanceof BluetoothConfigurationClass ) {
			
			bluetoothConfiguration = (BluetoothConfigurationClass) networkConfiguration;
			
			bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
			
			if (bluetoothAdapter == null) {
				return null;
			}
			
			bluetoothDevice = bluetoothAdapter.getRemoteDevice(
					bluetoothConfiguration.getAdress());
			
			uuid = bluetoothConfiguration.getUuid();
			
			Log.d("THREAD","Socket... Checking BT connection:"+bluetoothConfiguration.getName());
				

			Log.d("THREAD",bluetoothDevice.getName() + "\n" + bluetoothDevice.getAddress());

			Log.d("THREAD",uuid.toString());
			
			try {
				bluetoothSocket = bluetoothDevice.createRfcommSocketToServiceRecord(uuid);
				
				Log.d("THREAD","6");
		
				// Cancel discovery because it will slow down the connection
				bluetoothAdapter.cancelDiscovery();
				
				Log.d("THREAD","7");
	
				bluetoothSocket.connect();
	
				Log.d("THREAD","dis...");
				
				dis = new DataInputStream(
						bluetoothSocket.getInputStream());
				
				Log.d("THREAD","dos...");
				
				dos = new DataOutputStream(
						bluetoothSocket.getOutputStream());
							
				publishProgress("Authorization...");

				Log.d("THREAD","Read...");
				
				dos.writeByte(
						N.Signal.NEED_AUTHORIZATION);
				
				recivedAuthorizationConfirmation = dis.readByte();
				
				Log.d("THREAD","RECIVED: "+recivedAuthorizationConfirmation);
				Log.d("THREAD","Decoded: S: "+N.Signal.decodeSystem(recivedAuthorizationConfirmation)+
						" I: "+N.Signal.decodeSignal(recivedAuthorizationConfirmation));
					
				if ( isAuthorizationSuccess(recivedAuthorizationConfirmation) == true ) {
					networkConfiguration.setConnectionStatus(ConnectionHelper.STATUS_ON);
					networkConfiguration.setSystem(N.Signal.decodeSystem(recivedAuthorizationConfirmation));
					
				} else {
					Log.d("THREAD","RECIVED: ERROR");
					publishProgress("Authorization error.");
	
					dis.close();
					dos.close();
					bluetoothSocket.close();
					return asyncCallOnError;
				}
				
				getServerScreenDimension(
						dis.readInt());

				dis.close();
				dos.close();
				bluetoothSocket.close();
				publishProgress("Connected.");
				return asyncCallReason;
				
			} catch (IOException e) {
				
				e.printStackTrace();
				Log.d("THREAD",String.valueOf(e.getStackTrace()[0].getLineNumber())+
						" "+e.getStackTrace()[0].getMethodName());
				publishProgress("Connection error.");
				this.cancel(true);
				
			}
		}
		return asyncCallOnError;
	}
	
	/**
	 * @param recivedAuthorizationConfirmation
	 * @return
	 */
	private boolean isAuthorizationSuccess(byte recivedAuthorizationConfirmation) {
		return N.Signal.NEED_AUTHORIZATION == N.Signal.decodeSignal(recivedAuthorizationConfirmation);
	}

	/**
	 * @param data
	 */
	private void getServerScreenDimension(int data) {
		int[] serverSideScreenDimensions = new int[5];
		serverSideScreenDimensions = N.Helper.decodeSignal(data);
		
		publishProgress("Reading data from server...");
		if (isDimensionRecived(serverSideScreenDimensions) == true ) {
			Log.d("THREAD","RECIVED addidtional data: X: "+
				serverSideScreenDimensions[2] + " Y: "+serverSideScreenDimensions[3]+"\n");
			networkConfiguration.setSystemDimmensionX(
					serverSideScreenDimensions[2]);
			networkConfiguration.setSystemDimmensionY(
					serverSideScreenDimensions[3]);
		}
		
	}

	/**
	 * @param serverSideScreenDimensions
	 * @return
	 */
	private boolean isDimensionRecived(int[] serverSideScreenDimensions) {
		return serverSideScreenDimensions[0] == N.Signal.DIMENSION;
	}

	
	
	/* (non-Javadoc)
	 * @see android.os.AsyncTask#onProgressUpdate(Progress[])
	 */
	/* (non-Javadoc)
	 * @see android.os.AsyncTask#onProgressUpdate(Progress[])
	 */
	@Override
	protected void onProgressUpdate(String... values) {
		super.onProgressUpdate(values);
		updateDialogLogStatus(values[0]);
	}

	/* (non-Javadoc)
	 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
	 */
	/* (non-Javadoc)
	 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
	 */
	@Override
	protected void onPostExecute(Integer result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		Log.d("THREAD","Thread closing.");
		returnToActivity(result);
		dismissDialog();
	}

	/* (non-Javadoc)
	 * @see android.os.AsyncTask#onCancelled(java.lang.Object)
	 */
	/* (non-Javadoc)
	 * @see android.os.AsyncTask#onCancelled(java.lang.Object)
	 */
	@Override
	protected void onCancelled(Integer result) {
		// TODO Auto-generated method stub
		super.onCancelled(result);
		Log.d("THREAD","Interupted by: " + asyncCallOnError);
		returnToActivity(asyncCallOnError);
		dismissDialog();
	}

	/**
	 * @param asyncCallReason
	 */
	private void returnToActivity(int asyncCallReason) {
		if ( activity != null ) {
			activity.onBackgroundFinished(
					asyncCallReason);
		}
	}
	
	/**
	 * @param message
	 */
	private void updateDialogLogStatus(String message) {
		if (dialog != null ) {
			dialog.updateDialogLogStatus(message);
		}
	}
	
	/**
	 * 
	 */
	private void dismissDialog() {
		if (dialog != null ) {
			dialog.dismiss();
		}
	}

}