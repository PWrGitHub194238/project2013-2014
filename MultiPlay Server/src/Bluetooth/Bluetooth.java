package Bluetooth;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.UUID;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;

import CodeKey.N;
import Wifi.Connect;
import Wifi.Serverwifi;

public class Bluetooth implements Runnable {
	private LocalDevice local = null;
	private StreamConnectionNotifier notifier = null;
	private StreamConnection connection = null;

	@Override
	public void run() {
		waitForConnection();
	}

	private void waitForConnection() {

		byte data;
		Connect connect = new Connect();
		System.out.println("Bluetooth Thread");

		while (true) {
			data = 0;
			try {
				local = LocalDevice.getLocalDevice();
				local.setDiscoverable(DiscoveryAgent.GIAC);

				UUID uuid = new UUID("04c6093b00001000800000805f9b34fb", false);
				System.out.println(uuid.toString());

				String url = "btspp://localhost:" + uuid.toString()
						+ ";name=RemoteBluetooth";
				try {
					notifier = (StreamConnectionNotifier) Connector.open(url);
					System.out.println("waiting for connection...");
					connection = notifier.acceptAndOpen();
					DataInputStream dis = new DataInputStream(
							connection.openDataInputStream());
					DataOutputStream dos = new DataOutputStream(
							connection.openDataOutputStream());
					data = dis.readByte();
					System.out.println("RUN");
					System.out.println("Read...");
					if (data == N.Signal.NEED_AUTHORIZATION) {
						System.out.println("Send back authorization code...");
						if (System.getProperty("os.name").startsWith("Win")) {
							dos.writeByte(N.Signal.encodeSignal(
									N.Signal.NEED_AUTHORIZATION,
									N.System.WINDOWS));
						} else if (System.getProperty("os.name").contains(
								"Linux")) {
							dos.writeByte(N.Signal
									.encodeSignal(N.Signal.NEED_AUTHORIZATION,
											N.System.LINUX));

						} else if (System.getProperty("os.name")
								.contains("BSD")) {
							dos.writeByte(N.Signal.encodeSignal(
									N.Signal.NEED_AUTHORIZATION, N.System.BSD));
						}
						Toolkit zestaw = Toolkit.getDefaultToolkit();
						Dimension wymiary = zestaw.getScreenSize();
						int wysokosc = wymiary.height;
						int szerokosc = wymiary.width;
						dos.writeInt(N.Helper
								.encodeSignal(N.Signal.DIMENSION,
										N.DeviceDataCounter.DOUBLE, wysokosc,
										szerokosc));
						dis.close();
						dos.close();
						connection.close();
						notifier.close();
						System.out.println("Over");
					} else if (data == N.Signal.NEED_CONNECTION) {
						int port = connect.getport();
						dos.writeInt(port);
						dis.close();
						dos.close();
						connection.close();
						notifier.close();
						System.out.println("Over");
						Serverbluetooth wifi = new Serverbluetooth(uuid, url);
						wifi.run();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} catch (BluetoothStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		/*
		 * // retrieve the local Bluetooth device object
		 * System.out.println("Bluetooth Thread"); // setup the server to listen
		 * for connection try { local = LocalDevice.getLocalDevice();
		 * local.setDiscoverable(DiscoveryAgent.GIAC);
		 * 
		 * UUID uuid = new UUID("04c6093b00001000800000805f9b34fb", false);
		 * System.out.println(uuid.toString());
		 * 
		 * String url = "btspp://localhost:" + uuid.toString() +
		 * ";name=RemoteBluetooth"; notifier = (StreamConnectionNotifier)
		 * Connector.open(url); } catch (BluetoothStateException e) {
		 * System.out.println("Bluetooth is not turned on.");
		 * e.printStackTrace(); return; } catch (IOException e) {
		 * e.printStackTrace(); return; }
		 * 
		 * // waiting for connection while (true) { byte data; while (true) {
		 * data = 0; try { System.out.println("waiting for connection...");
		 * connection = notifier.acceptAndOpen(); DataInputStream dis = new
		 * DataInputStream( connection.openDataInputStream()); DataOutputStream
		 * dos = new DataOutputStream( connection.openDataOutputStream()); data
		 * = dis.readByte(); System.out.println("RUN");
		 * System.out.println("Read..."); if (data ==
		 * N.Signal.NEED_AUTHORIZATION) {
		 * System.out.println("Send back authorization code...");
		 * dos.writeByte(N.Signal.NEED_AUTHORIZATION); if
		 * (System.getProperty("os.name").startsWith("Win")) {
		 * 
		 * dos.writeInt(N.System.WINDOWS); } else if
		 * (System.getProperty("os.name").contains( "Linux")) {
		 * dos.writeInt(N.System.LINUX);
		 * 
		 * } else if (System.getProperty("os.name") .contains("BSD")) {
		 * dos.writeInt(N.System.BSD); } System.out.println("Over");
		 * dis.close(); dos.close(); connection.close(); notifier.close(); }
		 * else if (data == N.Signal.NEED_CONNECTION) {
		 * System.out.println("Send back connect code...");
		 * dos.writeByte(N.Signal.NEED_CONNECTION); Serverbluetooth bluetooth =
		 * new Serverbluetooth( connection, dis, dos, notifier);
		 * bluetooth.run(); } } catch (Exception e) { e.printStackTrace();
		 * return; } } }
		 */
	}

}
