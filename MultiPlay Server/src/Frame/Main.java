package Frame;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import Mouse.Mouse;
import Mouse.Pos;
import Wifi.Connect;
import Wifi.N;
import Wifi.Serverwifi;

public class Main {
	static DataInputStream dis = null;
	static DataOutputStream dos = null;

	public static void main(String[] args) {
		Connect connect = new Connect();
		MFrame window = new MFrame(connect);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		window.setSize((int) d.getWidth(), 100);
		window.setVisible(true);
		window.setResizable(false);
		byte data;
		Pos p = new Pos();
		p.run();
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
					if (data == N.signal.need_authorization) {
						System.out.println("Send back authorization code...");
						dos.writeByte(N.signal.need_authorization);
						System.out.println("Over");
						dis.close();
						dos.close();
						socket.close();
					} else if (data == N.signal.need_to_connect) {
						System.out.println("Send back connect code...");
						dos.writeByte(N.signal.need_to_connect);
						Serverwifi wifi = new Serverwifi(socket, dis, dos);
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
