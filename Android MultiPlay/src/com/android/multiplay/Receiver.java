package com.android.multiplay;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import android.os.AsyncTask;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.EditText;
import android.util.Log;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Receiver extends AsyncTask<Object, Object, Object> {
	EditText IP, Port;
	Socket socket;
	DataInputStream dataInputStream = null;

	@Override
	protected Object doInBackground(Object... arg0) {
		try {
			socket = new Socket(InetAddress.getByName("192.168.0.22"), 8888);
			try {
				dataInputStream = new DataInputStream(socket.getInputStream());
				String napis = dataInputStream.readUTF();
				dataInputStream.close();
				socket.close();
				return napis;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}