package Frame;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.net.ServerSocket;
import java.net.Socket;

import Mouse.Mouse;
import Mouse.Pos;
import VJoy.VJoyDriver32;
import VJoy.VJoyDriver64;
import Wifi.Connect;
import Wifi.N;
import Wifi.Serverwifi;

public class Main {
	static DataInputStream dis = null;
	static DataOutputStream dos = null;

	public static void main(String[] args) {
		// System.getProperties() show all system info
		// System.out.println("System: " + System.getProperties());
		Connect connect = new Connect();
		MFrame window = new MFrame(connect);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		window.setSize((int) d.getWidth(), 100);
		window.setVisible(true);
		window.setResizable(false);
		byte data;
		// thread get mouse positions
		// Pos p = new Pos();
		// p.run();
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
