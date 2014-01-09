package Wifi;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import CodeKey.N;
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
				serversocket = new ServerSocket(connect.getportdefault());
				try {
					Socket socket = serversocket.accept();
					dis = new DataInputStream(socket.getInputStream());
					dos = new DataOutputStream(socket.getOutputStream());
					System.out.println("RUN");
					System.out.println("Read...");
					data = dis.readByte();
					if (data == N.Signal.NEED_AUTHORIZATION) {// czeka na
																// autoryzacjê
																// polaczenia
																// przy
																// defaultowym
																// porcie
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
									N.Signal.NEED_AUTHORIZATION, N.System.BSD)); // odsyla
																					// kod
																					// autoryzacji
																					// z
																					// ifo
																					// o
																					// systemie

						}
						int port = connect.getport(); // losuje jakiœ
														// piêciocyfrowy port,
														// nie powtarzalny
						dos.writeInt(port); // wysy³a ten port
						dis.close();
						dos.close(); // zamyka stary socket
						socket.close();
						serversocket.close();
						System.out.println("Over");

						// (data == N.Signal.NEED_CONNECTION) {
						// System.out.println("Send back connect code...");
						// dos.writeByte(N.Signal.NEED_CONNECTION);
						Serverwifi wifi = new Serverwifi(port); // uruchamia
																// w¹tek dla
																// danego
																// jednego
																// po³aczenia i
																// wraca do
																// oczekiwania
																// na po³aczenie
																// nowe przy
																// defaultowym
																// portcie
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
