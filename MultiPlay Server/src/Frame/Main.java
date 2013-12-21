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
		int port=1234;
		try {
			ServerSocket serversocket= new ServerSocket(port);
			Socket socket= serversocket.accept();
			dis = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());
			byte data = 0;
			data = dis.readByte();
			if ( data == N.signal.need_authorization ) {
				System.out.println("Send back authorization code...");
				dos.writeByte(N.signal.need_authorization);
				System.out.println("Over");
				dis.close();
				dos.close();
				socket.close();
			} else if ( data == N.signal.need_to_connect ) {
					System.out.println("Send back connect code...");
					dos.writeByte(N.signal.need_to_connect);
			System.out.println("fdf");
			Serverwifi wifi= new Serverwifi(socket);
			wifi.run();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}
