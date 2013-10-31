package com.android.multiplay;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Application;

public class MyApplication extends Application {
	Socket socket=null;
	DataOutputStream dataOutputStream = null;
	DataInputStream dataInputStream = null;
	
	public void makeSocket(String ip){
		 try {
			socket = new Socket(InetAddress.getByName(ip), 8888);
			dataOutputStream = new DataOutputStream(
					socket.getOutputStream());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public Socket getSocket(){
		return socket;
	}
	public void send(String massage , int x, int y){
		try {
			dataOutputStream.writeUTF("mouse");
			dataOutputStream.writeUTF(Integer.toString(x));
			dataOutputStream.writeUTF(Integer.toString(y));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
