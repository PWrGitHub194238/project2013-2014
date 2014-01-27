package Bluetooth;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;

import javax.bluetooth.LocalDevice;
import javax.bluetooth.UUID;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;

import CodeKey.N;
import Connect.BluetoothConfigurationClass;
import Keyboard.Keyboard;
import Mouse.Mouse;
import VJoy.VJoyDriver;
import VJoy.VJoyDriver32;
import VJoy.VJoyDriver64;

/*
 * @author Piotr Baczkiewicz
 */
public class Serverbluetooth implements Runnable {
	private StreamConnection connection = null;
	private StreamConnectionNotifier notifier = null;
	private DataOutputStream dos = null;
	private DataInputStream dis = null;
	String url = null;

	/*
	 * @param uuid uuid to bluetooth connection
	 * 
	 * @param url url to bluetooth connection
	 */
	public Serverbluetooth(UUID uuid, String url) {
		this.url = url;
	}

	/*
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		System.out.println("BT Server");
		VJoyDriver vjoy = null;
		Mouse mouse = new Mouse();
		Keyboard keyboard = new Keyboard();
		if (System.getProperty("os.name").startsWith("Win")) {
			if (System.getProperty("os.arch").contains("64"))
				vjoy = new VJoyDriver64(true);
			else
				vjoy = new VJoyDriver32(true);
		}
		int signals = 0;
		byte data;
		System.out.println("watek\n");
		ServerSocket serversocket;
		try {
			UUID uuid = new UUID(
					BluetoothConfigurationClass.Profiles.inne.replace("-", "")
							+ LocalDevice.getLocalDevice()
									.getBluetoothAddress(), false);
			String url = "btspp://localhost:" + uuid.toString()
					+ ";name=RemoteBluetooth";
			notifier = (StreamConnectionNotifier) Connector.open(url);
			System.out.println("Opening");
			try {
				connection = notifier.acceptAndOpen();
				dis = new DataInputStream(connection.openDataInputStream());
				dos = new DataOutputStream(connection.openDataOutputStream());
				System.out.println("Opened");
				while (true) {
					try {
						signals = dis.readInt();
						int[] ret = N.Helper.decodeSignal(signals);
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

							if (ret[1] == N.DeviceDataCounter.DOUBLE) {
								vjoy.updateAxes(1, ret[2], ret[3]);
							}
						} else if (ret[0] == N.Device.VJOYJOYSTICKRIGHT) {

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
	}
}
