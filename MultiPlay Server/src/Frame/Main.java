package Frame;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import Wifi.Serverwifi;

public class Main {
	public static void main(String[] args) {
		
		MFrame window = new MFrame();
		window.setSize(500, 500);
		window.setVisible(true);
		int port=1234;
		try {
			ServerSocket serversocket= new ServerSocket(port);
			Socket socket= serversocket.accept();
			Serverwifi wifi= new Serverwifi(socket);
			wifi.run();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}
