package Frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Toolkit;
import java.awt.dnd.DropTarget;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.LocalDevice;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;

import XML.appParser;

import Connect.ConnectWifi;

//Menu Frame
public class MFrame extends JFrame {
	private JList list;
	private DefaultListModel model;
	List<String> listy;
	appParser xml;

	public MFrame(ConnectWifi connect, final DefaultListModel model,
			final JList list, final List<String> listy) {
		super();
		this.list = list;
		this.model = model;
		xml = new appParser();

		int y = xml.getSize();
		int u = 0;
		String[] l = new String[y];
		while (u < y) {
			l[u] = xml.getName(u);
			listy.add(u, xml.getName(u));
			u++;
		}
		list.setListData(l);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		// ------------------------------Elements on MFRAME--------------

		list.setBounds(0, 100, (int) (d.getWidth() - d.getWidth() * 20 / 100),
				(int) d.getHeight() - 200);
		add(list);
		JButton bdelete = new JButton("Delete");
		this.add(bdelete);
		bdelete.setBounds((int) (d.getWidth() - d.getWidth() * 20 / 100) + 3,
				100, (int) (d.getWidth()
						- (d.getWidth() - d.getWidth() * 20 / 100) + 3) - 3,
				(int) d.getHeight() - 200);
		bdelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int index = list.getSelectedIndex();
				xml.deleteApp(index);
				listy.remove(index);
				String[] l = new String[listy.size()];
				int i = 0;
				while (i < listy.size()) {
					l[i] = listy.get(i);
					i++;
				}
				list.setListData(l);
			}
		});

		// -----------------------------Drag and Drop-------------
		DragnDropListener myDragDropListener = new DragnDropListener(this,
				list, listy);
		new DropTarget(this, myDragDropListener);

		// -------------------------MenuBar---------------------
		this.setTitle("MultiPlay");

		MenuBar mb = new MenuBar();
		Menu file = new Menu("File");
		Menu help = new Menu("Help");
		Menu options = new Menu("Options");
		mb.add(file);
		mb.add(help);
		mb.add(options);
		MenuItem exit = new MenuItem("Exit");
		MenuItem about = new MenuItem("About");
		MenuItem helps = new MenuItem("Help");
		MenuItem controllers = new MenuItem("Controllers");
		MenuItem connects = new MenuItem("Connect");
		help.add(about);
		help.add(helps);
		options.add(controllers);
		options.add(connects);

		// ----------------------- settings controllers-------------------
		JPanel mainPanel = new JPanel();
		this.add(mainPanel);

		// Connect info
		JPanel kon = new JPanel();
		mainPanel.add(kon);
		JLabel wificon = new JLabel("Your IP: " + connect.getIP() + "    "
				+ "Port: " + connect.getportdefault());
		try {
			String m = LocalDevice.getLocalDevice().getBluetoothAddress();
			int i = 0;
			String mac = "";
			while (i < m.length()) {
				mac += m.charAt(i);
				if (i % 2 != 0) {
					mac += ':';
				}
				i++;
			}
			char tmp[] = mac.toCharArray();
			tmp[tmp.length - 1] = ' ';
			mac = String.valueOf(tmp);

			JLabel bluecon = new JLabel("Your MAC: " + mac);
			JLabel sys = new JLabel("                    System: "
					+ ManagementFactory.getOperatingSystemMXBean().getName()
					+ "\n" + "   User: " + System.getProperty("user.name")
					+ "\n" + "   Arch: "
					+ ManagementFactory.getOperatingSystemMXBean().getArch());
			kon.add(wificon);
			kon.add(bluecon);

			kon.add(sys);
		} catch (BluetoothStateException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// ----------------------------Help Frame----------------------
		helps.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				HFrame helpframe = new HFrame();
				helpframe.setSize(500, 500);
				helpframe.setVisible(true);
			}
		});

		// -----------------------About Frame-----------------------
		about.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				AFrame helpframe = new AFrame();
				helpframe.setSize(500, 500);
				helpframe.setVisible(true);
			}

		});
		file.add(exit);
		setMenuBar(mb);
		// -----------------------EXIT Frame-----------------------

		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				JDialog.setDefaultLookAndFeelDecorated(true);
				int response = JOptionPane
						.showConfirmDialog(null, "Do you want to exit?",
								"Confirm", JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE);
				if (response == JOptionPane.NO_OPTION) {
				} else if (response == JOptionPane.YES_OPTION) {
					System.exit(0);
				} else if (response == JOptionPane.CLOSED_OPTION) {
					System.out.println("JOptionPane closed");
				}
			}

		});
		// X Button
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				JDialog.setDefaultLookAndFeelDecorated(true);
				int response = JOptionPane
						.showConfirmDialog(null, "Do you want to exit?",
								"Confirm", JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE);
				if (response == JOptionPane.NO_OPTION) {
				} else if (response == JOptionPane.YES_OPTION) {
					System.exit(0);
				} else if (response == JOptionPane.CLOSED_OPTION) {
					System.out.println("JOptionPane closed");
				}
			}
		});

	}
}
