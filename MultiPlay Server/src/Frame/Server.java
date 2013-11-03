package Frame;
import javax.swing.JPanel;

import Bluetooth.Serverbluetooth;
import Wifi.Serverwifi;


public class Server {
	MJTabbedPane tabbedPane;

	public Server(MJTabbedPane tabbedPane, String name){
		this.tabbedPane = tabbedPane;
		JPanel panel = new JPanel();
		tabbedPane.addTab(name, panel);
		Serverbluetooth serverbl = new Serverbluetooth();
		Serverwifi serverwifi = new Serverwifi();
	}
}
