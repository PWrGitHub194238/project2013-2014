package com.android.application;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import android.os.AsyncTask;

public class SenderWF extends AsyncTask<Object, Object, Object> {
	DataOutputStream dataOutputStream = null;
	Socket socket = null;
	OutputStream out = null;
	int x, y;

	public void getxy(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void setsocket(Socket socket) {
		this.socket = socket;
	}

	public void makeStream() {
		try {
			out = socket.getOutputStream();
			dataOutputStream = new DataOutputStream(out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected Object doInBackground(Object... arg0) {
		if (((String) arg0[0]).equals("mouse")) {
			try {
				dataOutputStream.writeUTF("mouse");
				dataOutputStream.writeUTF(Integer.toString(x));
				dataOutputStream.writeUTF(Integer.toString(y));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (((String) arg0[0]).equals("keyboard")) {
			try {
				dataOutputStream.writeUTF("keyboard");
				if (((String) arg0[1]).equals("up")) {
					dataOutputStream.writeUTF("up");
				}
				if (((String) arg0[1]).equals("down")) {
					dataOutputStream.writeUTF("down");
				}
				if (((String) arg0[1]).equals("left")) {
					dataOutputStream.writeUTF("left");
				}
				if (((String) arg0[1]).equals("right")) {
					dataOutputStream.writeUTF("right");

				}
				if (((String) arg0[1]).equals("enter")) {
					dataOutputStream.writeUTF("enter");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

}
