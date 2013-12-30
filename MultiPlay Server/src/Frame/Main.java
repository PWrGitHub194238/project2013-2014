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

import Bluetooth.Bluetooth;
import Mouse.Mouse;
import Mouse.Pos;
import VJoy.VJoyDriver32;
import VJoy.VJoyDriver64;
import Wifi.Connect;
import Wifi.N;
import Wifi.Serverwifi;
import Wifi.Wifi;

public class Main {
	

	public static void main(String[] args) {
		// System.getProperties() show all system info
		// System.out.println("System: " + System.getProperties());
		Connect connect = new Connect();
		MFrame window = new MFrame(connect);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		window.setSize((int) d.getWidth(), 100);
		window.setVisible(true);
		window.setResizable(false);
		  Thread wifiThread = new Thread(new Wifi());
		  wifiThread.start();
		  Thread bluetoothThread = new Thread(new Bluetooth());
		  bluetoothThread.start();
		// thread get mouse positions
		// Pos p = new Pos();
		// p.run();
		
	}

}
