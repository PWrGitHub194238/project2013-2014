import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

class Namelistener implements ActionListener {
	MJTabbedPane tabbedPane;
	String name;

	public Namelistener(MJTabbedPane tabbedPane, String name) {
		this.tabbedPane = tabbedPane;
		this.name = name;

	}

	public void actionPerformed(ActionEvent e) {
		ServerThread thread = new ServerThread(tabbedPane, name);

	}

}

public class NButton extends JButton {

	public NButton(String napis, MJTabbedPane tabbedPane, JTextField txt) {
		super();
		this.setText(napis);
		String name = new String();
		name = txt.getText();
		this.addActionListener(new Namelistener(tabbedPane, name));
	}
}
