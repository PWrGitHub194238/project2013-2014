package Wifi;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import VJoy.VJoyTest;

public class Serverwifi implements Runnable {
	int x=3,y=3;
	Socket socket;
	DataInputStream data;

	public Serverwifi(Socket socket) {
		this.socket=socket;
	}

	@Override
	public void run() {
		Mouse mouse =  new Mouse();
		String device =  new String();
			while (true) {
				try {
					data= new DataInputStream((socket.getInputStream()));
					device=data.readUTF();
					if(device.equals("mouse")){
						 x+= Integer.parseInt(data.readUTF());
						 y+= Integer.parseInt(data.readUTF());
						mouse.run(x,y);
					}
					else if(device.equals("VJoy")){
						VJoyTest joy = new VJoyTest();
					}
					else if(device.equals("exit")){
						break;
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		

	}

}
