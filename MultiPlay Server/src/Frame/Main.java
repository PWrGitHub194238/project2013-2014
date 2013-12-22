package Frame;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import Wifi.N;
import Wifi.Serverwifi;

public class Main {
	static DataInputStream dis = null;
	static DataOutputStream dos = null;

	public static void main(String[] args) {

		MFrame window = new MFrame();
		window.setSize(500, 500);
		window.setVisible(true);
		int port = 1234;
		try {
			ServerSocket serversocket = new ServerSocket(port);
			Socket socket = serversocket.accept();
			dis = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());
			byte data = 0;
			data = dis.readByte();
			if (data == N.signal.need_authorization) {
				dos.writeByte(N.signal.need_authorization);
				System.out.println("autoryzowane");
				Serverwifi wifi = new Serverwifi(socket,dis,dos);
				wifi.run();
				
			} else if (data == N.signal.need_to_connect) {
				dos.writeByte(N.signal.need_to_connect);
				

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
