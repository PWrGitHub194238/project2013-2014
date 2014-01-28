package Connect;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Random;

/**
 * 
 * @author Piotr B�czkiewicz
 *
 */
public class ConnectWifi {
	private int portdefault = 1234;
	private int port;

	private String IP = null;
	private ArrayList<Integer> arrlist = new ArrayList<Integer>();

	/**
	 * 
	 * @return ip
	 */
	public String getIP() {
		String ip = null;
		if (System.getProperty("os.name").startsWith("Win")) {
			try {
				ip = InetAddress.getLocalHost().toString();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				Enumeration<NetworkInterface> interfaces = NetworkInterface
						.getNetworkInterfaces();
				while (interfaces.hasMoreElements()) {
					NetworkInterface iface = interfaces.nextElement();
					if (iface.isLoopback() || !iface.isUp())
						continue;
					Enumeration<InetAddress> addresses = iface
							.getInetAddresses();
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

	/**
	 * 
	 * @return default port 1234
	 */
	public int getportdefault() {
		return portdefault;
	}

	/**
	 * 
	 * @return get unique random port
	 */
	public int getport() {
		Random r = new Random();
		int port, flag;
		do {
			port = r.nextInt() % 55000 + 8000;
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
	/**
	 * delete unused port from list
	 * @param port
	 */
	public void delport(int port) {
		int i = arrlist.indexOf(port);
		arrlist.remove(i);
	}
}
