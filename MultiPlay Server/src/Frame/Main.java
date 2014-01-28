package Frame;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import Bluetooth.Bluetooth;
import Connect.ConnectWifi;
import Wifi.Wifi;
/**
 * Main Class
 * @author Piotr B¹czkiewicz
 *
 */
public class Main {
/**
 * 
 * @param args
 */
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
		Thread wifiThread = new Thread(new Wifi(listy));
		wifiThread.start();
		Thread bluetoothThread = new Thread(new Bluetooth(listy));
		bluetoothThread.start();
		// thread get mouse positions
		// Pos p = new Pos();
		// p.run();
	}
}
