package Bluetooth;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.bluetooth.UUID;
import javax.microedition.io.Connector;
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
	private UUID uuid = null;
	String url = null;
	private int i = 0;
	private int x = 3, y = 3;
	private String pm, x1, y1, key;

	public Serverbluetooth(UUID uuid, String url) {
		this.uuid = uuid;
		this.url = url;
	}

	@Override
	public void run() {
		System.out.println("WIFI Server");

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
			byte data;
			System.out.println("watek");
			ServerSocket serversocket;
			try {
				notifier = (StreamConnectionNotifier) Connector.open(url);
				// System.err.println(port);

				try {
					connection = notifier.acceptAndOpen();
					DataInputStream dis = new DataInputStream(
							connection.openDataInputStream());
					DataOutputStream dos = new DataOutputStream(
							connection.openDataOutputStream());

					// data = dis.readByte();

					int i = 0;
					while (true) {

						try {
							signals = dis.readInt();
							// i += 1;
							// System.err.println(signals);
							int[] ret = N.Helper.decodeSignal(signals);
							// System.out.println("D: "+ret[0] + "S: " + ret[1]
							// +" "+ret[2]+ " "+ N.Device.WHEEL);
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
								// System.out.println(ret[2]);
								if (ret[1] == N.DeviceDataCounter.DOUBLE) {
									// if (ret[2] == -10)
									// ret[2] = -9;
									if (ret[3] == N.DeviceSignal.KEYBOARD_UP)
										// vjoy.updateAxes(1,
										// (int) (ret[2] * 14.1),
										// (int) -(9 * 14.1));
										vjoy.updateAxes(1, ret[2], -126);
									else if (ret[3] == N.DeviceSignal.KEYBOARD_SPACE)
										// vjoy.updateAxes(1,
										// (int) (ret[2] * 14.1),
										// (int) (9 * 14.1));
										vjoy.updateAxes(1, ret[2], 126);
									else
										// vjoy.updateAxes(1,
										// (int) (ret[2] * 14.1), 0);
										vjoy.updateAxes(1, ret[2], 0);
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
							} else if (ret[0] == N.Device.VJOYJOYSTICKLEFT) {
								// ret[2]-x
								// ret[3]-y
								vjoy.updateAxes(1,ret[2],ret[3]);
							} else if (ret[0] == N.Device.VJOYJOYSTICKRIGHT) {
								// ret[2]-x
								// ret[3]-y
								vjoy.updateAxes(2,ret[2],ret[3]);
							} else if (ret[0] == N.Device.VJOYBUTTONS) {
								if (ret[3] == N.DeviceSignal.PRESS) {
									if (ret[2] == N.DeviceSignal.VJOY_CIRCLE_PRESS) {
										// wciœniecie kó³ka chyba przycisk 14
										vjoy.buttonPress(2);
									} else if (ret[2] == N.DeviceSignal.VJOY_SHARP_PRESS) {
										// wciœniecie krzy¿yka chyba przycisk 15
										vjoy.buttonPress(3);
									} else if (ret[2] == N.DeviceSignal.VJOY_SQUARE_PRESS) {
										// wciœniecie kwadratu chyba przycisk 16
										vjoy.buttonPress(4);
									} else if (ret[2] == N.DeviceSignal.VJOY_TRIANGLE_PRESS) {
										// wciœniecie trójkata chyba przycisk 13
										vjoy.buttonPress(1);
									}else if (ret[2] == N.DeviceSignal.KEYBOARD_DOWN) {
										vjoy.buttonPress(13);
									}else if (ret[2] == N.DeviceSignal.KEYBOARD_LEFT) {
										vjoy.buttonPress(14);
									}else if (ret[2] == N.DeviceSignal.KEYBOARD_RIGHT) {
										vjoy.buttonPress(15);
									}else if (ret[2] == N.DeviceSignal.KEYBOARD_UP) {
										vjoy.buttonPress(12);
									}else if (ret[2] == N.DeviceSignal.VJOY_START_PRESS) {
										// wciœniecie start chyba przycisk 4
										vjoy.buttonPress(9);
									}
								} else if (ret[3] == N.DeviceSignal.RELEASE) {
									if (ret[2] == N.DeviceSignal.VJOY_CIRCLE_PRESS) {
										// zwolnienie kó³ka chyba przycisk 14
										vjoy.buttonRelease(2);
									} else if (ret[2] == N.DeviceSignal.VJOY_SHARP_PRESS) {
										// zwolnienie krzy¿yka chyba przycisk 15
										vjoy.buttonRelease(3);
									} else if (ret[2] == N.DeviceSignal.VJOY_SQUARE_PRESS) {
										// zwolnienie kwadratu chyba przycisk 16
										vjoy.buttonRelease(4);
									} else if (ret[2] == N.DeviceSignal.VJOY_TRIANGLE_PRESS) {
										// zwolnienie trójkata chyba przycisk 13
										vjoy.buttonRelease(1);
									}else if (ret[2] == N.DeviceSignal.KEYBOARD_DOWN) {
										vjoy.buttonRelease(13);
									}else if (ret[2] == N.DeviceSignal.KEYBOARD_LEFT) {
										vjoy.buttonRelease(14);
									}else if (ret[2] == N.DeviceSignal.KEYBOARD_RIGHT) {
										vjoy.buttonRelease(15);
									}else if (ret[2] == N.DeviceSignal.KEYBOARD_UP) {
										vjoy.buttonRelease(12);
									}else if (ret[2] == N.DeviceSignal.VJOY_START_PRESS) {
										// zwolnienie start chyba przycisk 4
										vjoy.buttonRelease(9);
									}
								}
							}  else if (ret[0] == N.Device.EXIT) {
								dis.close();
								dos.close();
								connection.close();
								notifier.close();
								break;
							}
						} catch (IOException e) {
							e.printStackTrace();
							return;
						}
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} catch (AWTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
