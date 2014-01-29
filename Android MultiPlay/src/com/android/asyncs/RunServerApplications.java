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
import com.android.application.MultiPlayApplication;
import com.android.application.N;
import com.android.application.WirelessConfigurationClass;
import com.android.dialogs.AsyncTaskDialog;
import com.android.extendedWidgets.lists.ExplorerApplicationItem;


/** Sends a request to the server launch the selected application and waits for the result.
 * 
 * @author tomasz
 *
 */
public class RunServerApplications extends AsyncTask<ExplorerApplicationItem, String, Integer> {

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
	private int recivedRunApplicationConfirmation = 0;
	
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
	public RunServerApplications() {
		this.dialog = null;
		this.activity = null;
		this.asyncCallReason = OnAsyncTaskFinished.TAG.ConnectionActivity_Empty;
		this.asyncCallOnError = OnAsyncTaskFinished.TAG.ConnectionActivity_Empty;
	};

	/**
	 * @param activity
	 * @param dialog
	 */
	public RunServerApplications(OnAsyncTaskFinished activity, AsyncTaskDialog dialog) {
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
	public RunServerApplications(OnAsyncTaskFinished activity, AsyncTaskDialog dialog, Integer asyncCallReason) {
		this.activity = activity;
		this.dialog = dialog;
		this.asyncCallReason = asyncCallReason;
		this.asyncCallOnError = OnAsyncTaskFinished.TAG.ConnectionActivity_Empty;
	};
	
	/**
	 * 
	 * @param activity
	 * @param dialog
	 * @param asyncCallReason
	 * @param asyncCallOnerror
	 * @param selectedApplication
	 */
	public RunServerApplications(OnAsyncTaskFinished activity, AsyncTaskDialog dialog, 
			Integer asyncCallReason, Integer asyncCallOnerror) {
		this.activity = activity;
		this.dialog = dialog;
		this.asyncCallReason = asyncCallReason;
		this.asyncCallOnError = asyncCallOnerror;
	};
	

	/* (non-Javadoc)
	 * @see android.os.AsyncTask#doInBackground(Params[])
	 */
	@Override
	protected synchronized Integer doInBackground(ExplorerApplicationItem... params) {

		ExplorerApplicationItem application = params[0];
		networkConfiguration = MultiPlayApplication.getMainNetworkConfiguration();
			
		if ( networkConfiguration != null ) {
			publishProgress("Establishing connection...");
				
			if ( networkConfiguration instanceof WirelessConfigurationClass ) {
				
				wirelessConfiguration = (WirelessConfigurationClass) networkConfiguration;
	
				Log.d("THREAD","Socket... Run application by WiFi connection:"+wirelessConfiguration.getName());
	
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
							N.Signal.RUN_APPLICATION);
					
					recivedRunApplicationConfirmation = dis.readByte();
						
					Log.d("THREAD","RECIVED: "+recivedRunApplicationConfirmation);
						
					if ( isAuthorizationSuccess(recivedRunApplicationConfirmation) == true ) {
	
						dos.writeUTF(application.getApplicationID() + "_" + application.getApplicationName());
						
					} else {
						Log.d("THREAD","RECIVED: ERROR");
						publishProgress("Authorization error.");
	
						try {
							dis.close();
							dos.close();
							socket.close();
						} catch (IOException e) {
							e.printStackTrace();
							Log.d("THREAD",String.valueOf(e.getStackTrace()[0].getLineNumber())+
									" "+e.getStackTrace()[0].getMethodName());
						}
						return asyncCallOnError;
					}
					
					
					try {
						dis.close();
						dos.close();
						socket.close();
					} catch (IOException e) {
						e.printStackTrace();
						Log.d("THREAD",String.valueOf(e.getStackTrace()[0].getLineNumber())+
								" "+e.getStackTrace()[0].getMethodName());
					}
					publishProgress("Check out complete.");
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
				
				Log.d("THREAD","Socket...  Run application by BT connection:"+bluetoothConfiguration.getName());
					
	
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
							N.Signal.RUN_APPLICATION);
					
					recivedRunApplicationConfirmation = dis.readByte();
					if ( isAuthorizationSuccess(recivedRunApplicationConfirmation) == true ) {
	
						dos.writeUTF(application.getApplicationID() + "_" + application.getApplicationName());
						
						recivedRunApplicationConfirmation = dis.readByte();
						
						if ( recivedRunApplicationConfirmation == N.Signal.APPLICATION_RUNNING) {
							try {
								dis.close();
								dos.close();
								bluetoothSocket.close();
							} catch (IOException e) {
								e.printStackTrace();
								Log.d("THREAD",String.valueOf(e.getStackTrace()[0].getLineNumber())+
										" "+e.getStackTrace()[0].getMethodName());
							}
							publishProgress("Connected.");
							return asyncCallReason;
						} else {
							try {
								dis.close();
								dos.close();
								bluetoothSocket.close();
							} catch (IOException e) {
								e.printStackTrace();
								Log.d("THREAD",String.valueOf(e.getStackTrace()[0].getLineNumber())+
										" "+e.getStackTrace()[0].getMethodName());
							}
							return asyncCallOnError;
						}
						
					} else {
						Log.d("THREAD","RECIVED: ERROR");
						publishProgress("Authorization error.");
		
						try {
							dis.close();
							dos.close();
							bluetoothSocket.close();
						} catch (IOException e) {
							e.printStackTrace();
							Log.d("THREAD",String.valueOf(e.getStackTrace()[0].getLineNumber())+
									" "+e.getStackTrace()[0].getMethodName());
						}
						return asyncCallOnError;
					}
					
				} catch (IOException e) {
					
					e.printStackTrace();
					Log.d("THREAD",String.valueOf(e.getStackTrace()[0].getLineNumber())+
							" "+e.getStackTrace()[0].getMethodName());
					publishProgress("Connection error.");
					this.cancel(true);
					
				}
			}
		}
		return asyncCallOnError;
	}
	
	/**
	 * @param recivedRunApplicationConfirmation
	 * @return
	 */
	private boolean isAuthorizationSuccess(int recivedRunApplicationConfirmation) {
		return recivedRunApplicationConfirmation == N.Signal.RUN_APPLICATION;
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