package Frame;

//Help Frame
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
/**
 * 
 * @author Piotr B¹czkiewicz
 *
 */
public class HFrame extends JFrame {

	private static final long serialVersionUID = 1L;
/**
 * 
 */
	public HFrame() {
		super();

		JTabbedPane tab = new JTabbedPane();
		getContentPane().add(tab);
		if (System.getProperty("os.name").contains("Linux")) {
			PPanel panel1 = new PPanel("bluetooth", "linux");
			tab.addTab("Bluetooth", panel1);
		} else if (System.getProperty("os.name").contains("Windows")) {
			PPanel panel1 = new PPanel("bluetooth", "Windows");
			tab.addTab("Bluetooth", panel1);
		}
		JPanel panel2 = new JPanel();
		tab.addTab("Keyboard           ", panel2);
		PPanel panel3 = new PPanel("device", "null");
		tab.addTab("Wheel           ", panel3);
		PPanel panel4 = new PPanel("device", "null");
		tab.addTab("Pad              ", panel4);
		JPanel panel5 = new JPanel();
		tab.addTab("Joystick             ", panel5);
		this.add(tab);
		tab.setVisible(true);
		this.setResizable(false);
	}

}
