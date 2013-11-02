import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

class Adder implements ActionListener {
	MJTabbedPane tabbedPane;

	public Adder(MJTabbedPane tabbedPane) {
		this.tabbedPane = tabbedPane;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		NFrame frame = new NFrame(tabbedPane);
		frame.setSize(300, 100);
		frame.setVisible(true);
	}
}

public class MButton extends JButton {
	public MButton(MJTabbedPane tabbedPane) {
		super();
		this.setText("ADD");
		this.addActionListener(new Adder(tabbedPane));
	}
}
