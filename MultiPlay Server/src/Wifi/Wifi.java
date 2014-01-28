package Wifi;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import XML.appParser;

import CodeKey.N;
import CodeKey.N.Helper;
import Connect.ConnectWifi;
/**
 * 
 * @author Piotr B¹czkiewicz
 *
 */
public class Wifi implements Runnable {
	private static DataInputStream dis = null;
	private static DataOutputStream dos = null;
	private List<String> listy;
	private int i = 0;
	appParser xml;
/**
 * 
 * @param listy
 */
	public Wifi(List<String> listy) {
		this.listy = listy;
	}
/**
 * @see java.lang.Runnable#run()
 */
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
						System.out.println("NEED_APPS");

						int size = listy.size();
						i = 0;
						dos.writeInt(Helper.encodeSignal(
								0, N.DeviceDataCounter.DOUBLE, data,size));

						while (i < size) {
							dos.writeUTF(String.valueOf(i) + "_" + listy.get(i));
							i++;
						}
						dis.close();
						dos.close();
						socket.close();
						serversocket.close();
					} else if (data == N.Signal.RUN_APPLICATION) {
						dos.writeByte(data);
						// Windows
						if (System.getProperty("os.name").startsWith("Win")) {
							String id = dis.readUTF();
							// --------miejsce na odczyt z xml danych
							String[] name = id.split("_",2);
							int index = Integer.parseInt(name[0]);
							String nazwa = name[1];
							xml = new appParser();
							if (appParser.getName(index).equals(nazwa)) {
								nazwa = nazwa.toLowerCase();
								ProcessBuilder pb = new ProcessBuilder("cmd", "/c",
										appParser.getPath(index).replace("\\", "\\\\"));
								pb.start();
								dos.writeByte(N.Signal.APPLICATION_RUNNING);
							}

						} else if (System.getProperty("os.name").contains("Linux")) {
							String id = dis.readUTF();
							// --------miejsce na odczyt z xml danych
							String[] name = id.split("_",2);
							int index = Integer.parseInt(name[0]);
							String nazwa = name[1];

							xml = new appParser();
							if (appParser.getName(index) == nazwa) {
								nazwa = nazwa.toLowerCase();
								String[] cmd = new String[] { "/bin/bash", "-c",
										nazwa };
								Runtime.getRuntime().exec(cmd);
								dos.writeByte(N.Signal.APPLICATION_RUNNING);
							}
						}
						dis.close();
						dos.close();
						socket.close();
						serversocket.close();
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
