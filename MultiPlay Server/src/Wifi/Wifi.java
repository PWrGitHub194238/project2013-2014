package Wifi;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import Frame.MFrame;

public class Wifi implements Runnable {
	private static DataInputStream dis = null;
	private static DataOutputStream dos = null;

	public Wifi() {

	}

	@Override
	public void run() {
		byte data;
		Connect connect = new Connect();
		System.out.println("Wifi Thread");

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
