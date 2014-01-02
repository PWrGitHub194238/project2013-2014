package Bluetooth;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;

import CodeKey.N;
import Keyboard.Keyboard;
import Mouse.Mouse;
import Speaker.Speaker;
import VJoy.VJoyDriver;
import VJoy.VJoyDriver32;
import VJoy.VJoyDriver64;

public class Serverbluetooth implements Runnable {
	private StreamConnection connection = null;
	private StreamConnectionNotifier notifier = null;
	private DataOutputStream dos = null;
	private DataInputStream dis = null;
	private int i = 0;
	private int x = 3, y = 3;
	private String pm, x1, y1, key;

	public Serverbluetooth(StreamConnection connection, DataInputStream dis,
			DataOutputStream dos, StreamConnectionNotifier notifier) {
		this.connection = connection;
		this.notifier = notifier;
		this.dos = dos;
		this.dis = dis;
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
			System.out.println("Bluetooth SERVER!!");
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
						if (ret[1] == N.DeviceDataCounter.SINGLE) {
							if (ret[2] == N.DeviceSignal.SPEAKER_PUNCTUATION) {
								signals = dis.readInt();
								ret = N.Helper.decodeSignal(signals);
								keyboard.click(ret[2]);

							} else if (ret[2] == N.DeviceSignal.SPEAKER_COMMANDS) {
							}
						}

					} else if (ret[0] == N.Device.VJOY) {

					} else if (ret[0] == N.Device.EXIT) {
						dis.close();
						dos.close();
						connection.close();
						notifier.close();
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
