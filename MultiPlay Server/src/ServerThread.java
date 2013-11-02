import java.awt.TextField;

import javax.swing.JPanel;

public class ServerThread implements Runnable {
	MJTabbedPane tabbedPane;

	public ServerThread(MJTabbedPane tabbedPane, String name) {
		this.tabbedPane = tabbedPane;
		JPanel panel = new JPanel();

		tabbedPane.addTab(name, panel);

	}

	@Override
	public void run() {

	}

}
