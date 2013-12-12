package Frame;
//Help Frame
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class HFrame extends JFrame {

	public HFrame() {
		super();
		
		JTabbedPane tab= new JTabbedPane();
		getContentPane().add(tab);
		JPanel panel1=new JPanel();
		tab.addTab("Mouse            ",panel1);
		JPanel panel2=new JPanel();
		tab.addTab("Keyboard           ",panel2);
		JPanel panel3=new JPanel();
		tab.addTab("Wheel           ",panel3);
		JPanel panel4=new JPanel();
		tab.addTab("Pad              ",panel4);
		JPanel panel5=new JPanel();
		tab.addTab("Joystick             ",panel5);
		this.add(tab);
		tab.setVisible(true);
		this.setResizable(false);
	}

}
