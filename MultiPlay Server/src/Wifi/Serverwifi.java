package Wifi;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
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
import VJoy.VJoyDriver;
import VJoy.VJoyDriver32;
import VJoy.VJoyDriver64;

public class Serverwifi implements Runnable {
	private int x = 3, y = 3;
	private String pm, x1, y1, key;
	private Socket socket;
	private DataInputStream dis;
	private DataOutputStream dos;
	ServerSocket serversocket;
	int i = 0;

	public Serverwifi(Socket socket, DataInputStream dis, DataOutputStream dos,ServerSocket serversocket) {
		this.socket = socket;
		this.dis = dis;
		this.dos = dos;
		this.serversocket=serversocket;
	}

	@Override
	public void run() {

		VJoyDriver vjoy = null;
		Mouse mouse = new Mouse();
		Keyboard keyboard = new Keyboard();
		Speaker speak = new Speaker();
		Robot robot;
		try {
			robot = new Robot();
			if (System.getProperty("os.name").startsWith("Win")) {
				if (System.getProperty("os.arch").contains("64"))
					vjoy = new VJoyDriver64(true);
				else
					vjoy = new VJoyDriver32(true);
			}

			int signals = 0;
			System.out.println("watek");
			while (true) {
				try {
					signals = dis.readInt();
					int[] ret = N.Helper.decodeSignal(signals);

					if (ret[0] == N.Device.MOUSE) {
						if (ret[1] == N.DeviceDataCounter.SINGLE)
							mouse.click(ret[2]);
						else if (ret[1] == N.DeviceDataCounter.DOUBLE)
							if (i == 0) {
								mouse.run(ret[2], ret[3]);
								i = 1;
							} else
								i = 0;
					} else if (ret[0] == N.Device.KEYBOARD) {
						if (ret[1] == N.DeviceDataCounter.SINGLE)
							keyboard.click(ret[2]);
					} else if (ret[0] == N.Device.WHEEL) {
						if (ret[1] == N.DeviceDataCounter.DOUBLE) {
							if (ret[2] == -10)
								ret[2] = -9;
							if (ret[3] == N.DeviceSignal.KEYBOARD_UP)
								vjoy.updateAxes(1, (int) (ret[2] * 14.1),
										(int) -(9 * 14.1));
							else if (ret[3] == N.DeviceSignal.KEYBOARD_SPACE)
								vjoy.updateAxes(1, (int) (ret[2] * 14.1),
										(int) (9 * 14.1));
							else
								vjoy.updateAxes(1, (int) (ret[2] * 14.1), 0);

						}
					} else if (ret[0] == N.Device.SPEAKER) {

					} else if (ret[0] == N.Device.VJOY) {

					} else if (ret[0] == N.Device.EXIT) {
						dis.close();
						dos.close();
						socket.close();
						serversocket.close();
						return;
					}
				} catch (IOException e) {
					e.printStackTrace();
					return;
				}

			}

		} catch (AWTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
