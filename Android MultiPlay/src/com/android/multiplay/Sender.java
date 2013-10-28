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

public class Sender extends AsyncTask<Object, Object, Object> {
	EditText IP, Port;
	DataOutputStream dataOutputStream = null;
	DataInputStream dataInputStream = null;
	int x, y;
	String ip=null;

	public Sender() {
	}
	public void setip(String ip) {
	this.ip=ip;
	}
	public void getxy(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	protected Object doInBackground(Object... arg0) {
		try {
			Socket socket = new Socket(InetAddress.getByName(ip),
					8888);
			try {
				dataOutputStream = new DataOutputStream(
						socket.getOutputStream());
				dataOutputStream.writeUTF(Integer.toString(x));
				dataOutputStream.writeUTF(Integer.toString(y));
				dataOutputStream.flush();
				dataOutputStream.close();
				socket.close();
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
