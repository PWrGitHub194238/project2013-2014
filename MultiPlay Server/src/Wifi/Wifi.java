package Wifi;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.UUID;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;
import Frame.MFrame;

public class Wifi implements Runnable {
	static DataInputStream dis = null;
	static DataOutputStream dos = null;

	public Wifi() {

	}

	@Override
	public void run() {
		byte data;
		Connect connect = new Connect();

		while (true) {
			data = 0;
			ServerSocket serversocket;
			try {
				serversocket = new ServerSocket(connect.getport());
				try {
					Socket socket = serversocket.accept();
					dis = new DataInputStream(socket.getInputStream());
					dos = new DataOutputStream(socket.getOutputStream());
					System.out.println("RUN");
					System.out.println("Read...");
					data = dis.readByte();
					if (data == N.Signal.NEED_AUTHORIZATION) {
						System.out.println("Send back authorization code...");
						dos.writeByte(N.Signal.NEED_AUTHORIZATION);
						if (System.getProperty("os.name").startsWith("Win")) {

							dos.writeInt(N.System.WINDOWS);
						} else if (System.getProperty("os.name").contains(
								"Linux")) {
							dos.writeInt(N.System.LINUX);

						} else if (System.getProperty("os.name")
								.contains("BSD")) {
							dos.writeInt(N.System.BSD);
						}
						System.out.println("Over");
						dis.close();
						dos.close();
						socket.close();
						serversocket.close();
					} else if (data == N.Signal.NEED_CONNECTION) {
						System.out.println("Send back connect code...");
						dos.writeByte(N.Signal.NEED_CONNECTION);
						Serverwifi wifi = new Serverwifi(socket, dis, dos,
								serversocket);
						wifi.run();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

}
