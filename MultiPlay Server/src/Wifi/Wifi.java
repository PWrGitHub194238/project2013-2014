package Wifi;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import CodeKey.N;
import Connect.ConnectWifi;
import Frame.MFrame;

public class Wifi implements Runnable {
	private static DataInputStream dis = null;
	private static DataOutputStream dos = null;
	
	public Wifi() {

	}

	@Override
	public void run() {
		byte data;
		ConnectWifi connect = new ConnectWifi();
		System.out.println("Wifi Thread");

		while (true) {
			data = 0;
			ServerSocket serversocket;
			try {
				serversocket = new ServerSocket(connect.getportdefault());
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
						socket.close();
						serversocket.close();
						System.out.println("Over");
					} else if (data == N.Signal.NEED_CONNECTION) {
						int port = connect.getport();
						dos.writeInt(port);
						dis.close();
						dos.close();
						socket.close();
						serversocket.close();
						System.out.println("Over");
						Serverwifi wifi = new Serverwifi(port, connect);
						wifi.run();
					} else if (data == N.Signal.NEED_APPLICATIONS) {

					} else if (data == N.Signal.RUN_APPLICATION) {
						// Windows
						if (System.getProperty("os.name").startsWith("Win")) {
							// String name= dis.readUTF();
							// --------miejsce na odczyt z xml danych

							// ProcessBuilder pb = new ProcessBuilder("cmd",
							// "/c",file);
						}
						//Linux and BSD
						else if (System.getProperty("os.name").contains(
								"Linux")) {
							// String name= dis.readUTF();
							// miejsce na odczyt z xml danych
							// String[] cmd = new String[] {"/bin/bash", "-c",
							// fileName};
							// Runtime.getRuntime().exec(cmd);
						}
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
