package Wifi;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Random;

import VJoy.VJoyDriver32;
import VJoy.VJoyDriver64;

public class Connect {
	private int portdefault = 1234;
	private int port;
	private String IP = null;
	private ArrayList<Integer> arrlist = new ArrayList<Integer>();

	public String getIP() {
		String ip = null;
		if (System.getProperty("os.name").startsWith("Win")) {
			try {
				ip= InetAddress.getLocalHost().toString();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			try {
				Enumeration<NetworkInterface> interfaces = NetworkInterface
						.getNetworkInterfaces();
				while (interfaces.hasMoreElements()) {
					NetworkInterface iface = interfaces.nextElement();
					if (iface.isLoopback() || !iface.isUp())
						continue;
					Enumeration<InetAddress> addresses = iface.getInetAddresses();
					while (addresses.hasMoreElements()) {
						InetAddress addr = addresses.nextElement();
						ip = addr.getHostAddress();
					}
				}
				
				
			} catch (SocketException e) {
				throw new RuntimeException(e);
			}
		}
		return ip;
	}

	public int getportdefault() {
		return portdefault;
	}

	public int getport() {
		Random r = new Random();
		int port, flag;
		do {
			port = r.nextInt() % 100000;
			flag = 0;
			for (int i = 0; i < arrlist.size(); i++) {
				if (port == arrlist.get(i)) {
					flag = 1;
				}
			}
		} while (port < 10000 || flag == 1);
		arrlist.add(port);
		return arrlist.get(arrlist.size() - 1);
	}
}
