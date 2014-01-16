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

import CodeKey.N;
import Keyboard.Keyboard;
import Mouse.Mouse;
import Speaker.Speaker;
import VJoy.VJoyDriver;
import VJoy.VJoyDriver32;
import VJoy.VJoyDriver64;

public class Serverwifi implements Runnable {
	private int x = 3, y = 3;
	private Socket socket;
	private DataInputStream dis;
	private DataOutputStream dos;
	private ServerSocket serversocket;
	private int port;
	private int i = 0;

	public Serverwifi(int port) {
		this.port = port;
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
				serversocket = new ServerSocket(port);
				// System.err.println(port);
				Socket socket;
				try {
					socket = serversocket.accept();
					dis = new DataInputStream(socket.getInputStream());
					dos = new DataOutputStream(socket.getOutputStream());
					this.socket = socket;
					this.dis = dis;
					this.dos = dos;
					this.serversocket = serversocket;
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
								// przetwarzanie sygna³u dla lewej ga³ki
							} else if (ret[0] == N.Device.VJOYJOYSTICKRIGHT) {
								// ret[2]-x
								// ret[3]-y
								// przetwarzanie sygna³u dla prawej ga³ki
							} else if (ret[0] == N.Device.VJOYBUTTONS) {
								if (ret[3] == N.DeviceSignal.PRESS) {
									if (ret[2] == N.DeviceSignal.VJOY_CIRCLE_PRESS) {
										// wciœniecie kó³ka chyba przycisk 14
									} else if (ret[2] == N.DeviceSignal.VJOY_SHARP_PRESS) {
										// wciœniecie krzy¿yka chyba przycisk 15
									} else if (ret[2] == N.DeviceSignal.VJOY_SQUARE_PRESS) {
										// wciœniecie kwadratu chyba przycisk 16
									} else if (ret[2] == N.DeviceSignal.VJOY_TRIANGLE_PRESS) {
										// wciœniecie trójkata chyba przycisk 13
									}else if (ret[2] == N.DeviceSignal.KEYBOARD_DOWN) {
										// wciœniecie Dó³ chyba przycisk 7
									}else if (ret[2] == N.DeviceSignal.KEYBOARD_LEFT) {
										// wciœniecie Dó³ chyba przycisk 8
									}else if (ret[2] == N.DeviceSignal.KEYBOARD_RIGHT) {
										// wciœniecie Dó³ chyba przycisk 6
									}else if (ret[2] == N.DeviceSignal.KEYBOARD_UP) {
										// wciœniecie Dó³ chyba przycisk 5
									}else if (ret[2] == N.DeviceSignal.VJOY_START_PRESS) {
										// wciœniecie start chyba przycisk 4
									}
								} else if (ret[3] == N.DeviceSignal.RELEASE) {
									if (ret[2] == N.DeviceSignal.VJOY_CIRCLE_PRESS) {
										// zwolnienie kó³ka chyba przycisk 14
									} else if (ret[2] == N.DeviceSignal.VJOY_SHARP_PRESS) {
										// zwolnienie krzy¿yka chyba przycisk 15
									} else if (ret[2] == N.DeviceSignal.VJOY_SQUARE_PRESS) {
										// zwolnienie kwadratu chyba przycisk 16
									} else if (ret[2] == N.DeviceSignal.VJOY_TRIANGLE_PRESS) {
										// zwolnienie trójkata chyba przycisk 13
									}else if (ret[2] == N.DeviceSignal.KEYBOARD_DOWN) {
										// zwolnienie Dó³ chyba przycisk 7
									}else if (ret[2] == N.DeviceSignal.KEYBOARD_LEFT) {
										// zwolnienie lewo chyba przycisk 8
									}else if (ret[2] == N.DeviceSignal.KEYBOARD_RIGHT) {
										// zwolnienie prawo chyba przycisk 6
									}else if (ret[2] == N.DeviceSignal.KEYBOARD_UP) {
										// zwolnienie gora chyba przycisk 5
									}else if (ret[2] == N.DeviceSignal.VJOY_START_PRESS) {
										// zwolnienie start chyba przycisk 4
									}
								}
							} else if (ret[0] == N.Device.EXIT) {
								dis.close();
								dos.close();
								socket.close();
								serversocket.close();
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
