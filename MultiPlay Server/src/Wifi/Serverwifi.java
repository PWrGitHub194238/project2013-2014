package Wifi;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import CodeKey.N;
import Connect.ConnectWifi;
import Keyboard.Keyboard;
import Mouse.Mouse;
import VJoy.VJoyDriver;
import VJoy.VJoyDriver32;
import VJoy.VJoyDriver64;
/**
 * 
 * @author Piotr B¹czkiewicz
 *
 */
public class Serverwifi implements Runnable {
	private DataInputStream dis;
	private DataOutputStream dos;
	private int port;
	private ConnectWifi connect = null;
/**
 * 
 * @param port
 * @param connect
 */
	public Serverwifi(int port, ConnectWifi connect) {
		this.port = port;
		this.connect = connect;
	}
	/**
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		System.out.println("WIFI Server");
		VJoyDriver vjoy = null;
		Mouse mouse = new Mouse();
		Keyboard keyboard = new Keyboard();
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
					this.dis = dis;
					this.dos = dos;
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
									mouse.run(ret[2], ret[3]);
							} else if (ret[0] == N.Device.KEYBOARD) {
								if (ret[1] == N.DeviceDataCounter.DOUBLE)
									if (ret[3] == N.DeviceSignal.PRESS) {
										keyboard.click(ret[2]);
									} else if (ret[3] == N.DeviceSignal.RELEASE) {
										keyboard.release(ret[2]);
									}
							} else if (ret[0] == N.Device.WHEEL) {
								if (ret[1] == N.DeviceDataCounter.SINGLE) {
									vjoy.updateAxes(1, ret[2], 0);
								}
							} else if (ret[0] == N.Device.WHEELBUTTONS) {
								if (ret[1] == N.DeviceDataCounter.DOUBLE) {
									if (ret[3] == N.DeviceSignal.PRESS) {
										if (ret[2] == N.DeviceSignal.KEYBOARD_UP)
											vjoy.buttonPress(3);
										else if (ret[2] == N.DeviceSignal.KEYBOARD_DOWN)
											vjoy.buttonPress(4);
										else if (ret[2] == N.DeviceSignal.VJOY_CIRCLE_PRESS)
											vjoy.buttonPress(2);
									} else if (ret[3] == N.DeviceSignal.RELEASE) {
										if (ret[2] == N.DeviceSignal.KEYBOARD_UP)
											vjoy.buttonRelease(3);
										else if (ret[2] == N.DeviceSignal.KEYBOARD_DOWN)
											vjoy.buttonRelease(4);
										else if (ret[2] == N.DeviceSignal.VJOY_CIRCLE_PRESS)
											vjoy.buttonPress(2);
									}
								}
							} else if (ret[0] == N.Device.SPEAKER) {
								if (ret[1] == N.DeviceDataCounter.SINGLE) {
									keyboard.clickandrelease(ret[2]);
								}
							} else if (ret[0] == N.Device.VJOYJOYSTICKLEFT) {
								// ret[2]-x
								// ret[3]-y
								// przetwarzanie sygna³u dla lewej ga³ki
								if (ret[1] == N.DeviceDataCounter.DOUBLE) {
									vjoy.updateAxes(1, ret[2], ret[3]);
								}
							} else if (ret[0] == N.Device.VJOYJOYSTICKRIGHT) {
								// ret[2]-x
								// ret[3]-y
								// przetwarzanie sygna³u dla prawej ga³ki
								if (ret[1] == N.DeviceDataCounter.DOUBLE) {
									vjoy.updateAxes(2, ret[2], ret[3]);
								}
							} else if (ret[0] == N.Device.VJOYBUTTONS) {
								if (ret[1] == N.DeviceDataCounter.DOUBLE) {
									if (ret[3] == N.DeviceSignal.PRESS) {
										if (ret[2] == N.DeviceSignal.VJOY_CIRCLE_PRESS) {
											vjoy.buttonPress(2);
										} else if (ret[2] == N.DeviceSignal.VJOY_SHARP_PRESS) {
											vjoy.buttonPress(3);
										} else if (ret[2] == N.DeviceSignal.VJOY_SQUARE_PRESS) {
											vjoy.buttonPress(4);
										} else if (ret[2] == N.DeviceSignal.VJOY_TRIANGLE_PRESS) {
											vjoy.buttonPress(1);
										} else if (ret[2] == N.DeviceSignal.KEYBOARD_DOWN) {
											vjoy.buttonPress(13);
										} else if (ret[2] == N.DeviceSignal.KEYBOARD_LEFT) {
											vjoy.buttonPress(14);
										} else if (ret[2] == N.DeviceSignal.KEYBOARD_RIGHT) {
											vjoy.buttonPress(15);
										} else if (ret[2] == N.DeviceSignal.KEYBOARD_UP) {
											vjoy.buttonPress(12);
										} else if (ret[2] == N.DeviceSignal.VJOY_START_PRESS) {
											vjoy.buttonPress(9);
										}
									} else if (ret[3] == N.DeviceSignal.RELEASE) {
										if (ret[2] == N.DeviceSignal.VJOY_CIRCLE_PRESS) {
											vjoy.buttonRelease(2);
										} else if (ret[2] == N.DeviceSignal.VJOY_SHARP_PRESS) {
											vjoy.buttonRelease(3);
										} else if (ret[2] == N.DeviceSignal.VJOY_SQUARE_PRESS) {
											vjoy.buttonRelease(4);
										} else if (ret[2] == N.DeviceSignal.VJOY_TRIANGLE_PRESS) {
											vjoy.buttonRelease(1);
										} else if (ret[2] == N.DeviceSignal.KEYBOARD_DOWN) {
											vjoy.buttonRelease(13);
										} else if (ret[2] == N.DeviceSignal.KEYBOARD_LEFT) {
											vjoy.buttonRelease(14);
										} else if (ret[2] == N.DeviceSignal.KEYBOARD_RIGHT) {
											vjoy.buttonRelease(15);
										} else if (ret[2] == N.DeviceSignal.KEYBOARD_UP) {
											vjoy.buttonRelease(12);
										} else if (ret[2] == N.DeviceSignal.VJOY_START_PRESS) {
											vjoy.buttonRelease(9);
										}
									}
								}
							} else if (ret[0] == N.Device.CUSTOM_TOUTHCIRCLE_BUTTON) {
								if (ret[1] == N.DeviceDataCounter.DOUBLE) {
									if (ret[3] == N.DeviceSignal.PRESS) {
										vjoy.buttonPress(ret[2]);
									} else if (ret[3] == N.DeviceSignal.RELEASE) {
										vjoy.buttonRelease(ret[2]);
									}
								}

							} else if (ret[0] == N.Device.EXIT) {
								dis.close();
								dos.close();
								socket.close();
								serversocket.close();
								connect.delport(port);
								break;
							}
						} catch (IOException e) {
							e.printStackTrace();
							return;
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (AWTException e1) {
			e1.printStackTrace();
		}

	}
}
