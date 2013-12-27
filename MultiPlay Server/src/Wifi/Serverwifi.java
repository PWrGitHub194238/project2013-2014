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
import Speaker.Speaker;
import VJoy.VJoyTest;

public class Serverwifi implements Runnable {
	private int x = 3, y = 3;
	private String pm, x1, y1, key;
	private Socket socket;
	private DataInputStream dis;
	private DataOutputStream dos;

	public Serverwifi(Socket socket, DataInputStream dis, DataOutputStream dos) {
		this.socket = socket;
		this.dis = dis;
		this.dos = dos;
	}

	@Override
	public void run() {
		Mouse mouse = new Mouse();
		Keyboard keyboard = new Keyboard();
		Speaker speak = new Speaker();
		int signals = 0;
		System.out.println("watek");
		while (true) {
			try {
				signals = dis.readInt();
				int[] ret = Wifi.N.Helper.decodeSignal(signals);
				if (ret[0] == N.Device.MOUSE) {
					if (ret[1] == N.DeviceDataCounter.SINGLE)
						mouse.click(ret[2]);
					else if (ret[1] == N.DeviceDataCounter.DOUBLE)
						mouse.run(ret[2], ret[3]);
				} else if (ret[0] == N.Device.KEYBOARD)
					keyboard.click(ret[2]);
				else if (ret[0] == N.Device.WHEEL) {

				} else if (ret[0] == N.Device.SPEAKER) {

				} else if (ret[0] == N.Device.EXIT) {
					return;
				}
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}

		}

	}

}
