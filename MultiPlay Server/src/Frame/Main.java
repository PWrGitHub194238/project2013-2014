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
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import Bluetooth.Bluetooth;
import CodeKey.N;
import Connect.ConnectWifi;
import Mouse.Mouse;
import Mouse.Pos;
import VJoy.VJoyDriver;
import VJoy.VJoyDriver32;
import VJoy.VJoyDriver64;
import VJoy.VJoyDriver32.VJoy32;
import Wifi.Serverwifi;
import Wifi.Wifi;

public class Main {

	public static void main(String[] args) {
		JList list = new JList();
		DefaultListModel model = new DefaultListModel();
		List<String> listy = new ArrayList<String>();
		// System.getProperties() show all system info
		// System.out.println("System: " + System.getProperties());
		ConnectWifi connect = new ConnectWifi();
		MFrame window = new MFrame(connect, model, list, listy);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		window.setSize((int) d.getWidth(),
				(int) (d.getHeight() - d.getHeight() * 10 / 100));
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
