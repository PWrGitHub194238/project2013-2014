package Wifi;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import Keyboard.Keyboard;
import Mouse.Mouse;
import VJoy.VJoyTest;

public class Serverwifi implements Runnable {
	int x = 3, y = 3;
	String pm, x1, y1,key;
	Socket socket;
	DataInputStream dis;
	DataOutputStream dos;

	public Serverwifi(Socket socket,DataInputStream dis,DataOutputStream dos) {
		this.socket = socket;
		this.dis=dis;
		this.dos=dos;
	}

	@Override
	public void run() {
		Mouse mouse = new Mouse();
		Keyboard keyboard= new Keyboard();
		int device=0 ;
		//while (true) {
			try {
				dis = new DataInputStream((socket.getInputStream()));
				device=dis.readByte();
				//if (device==1) {
					System.out.println("mysz");
				//}
				
				/*device = Integer.toString((data.read()));
				System.out.println("proba");
				if (device.equals("mouse")) {
					try {
						x1 = data.readUTF();
						y1 = data.readUTF();
						x += Integer.parseInt(x1);
						y += Integer.parseInt(y1);
						mouse.run(x, y);
					} catch (NumberFormatException e) {
						pm = x1;
						mouse.click(pm);
					}
				} else if (device.equals("VJoy")) {

				} else if (device.equals("keyboard")) {
					key = data.readUTF();
					keyboard.click(key);
				} else if (device.equals("wheel")) {

				} else if (device.equals("exit")) {
					break;
				}*/
			} catch (IOException e) {
				e.printStackTrace();
				return ;
			
			}

		//}

	}

}
