import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class NFrame extends JFrame {

	public NFrame(MJTabbedPane tabbedPane){
		super();
		JLabel label= new JLabel("Podaj imie:"); 
		JTextField txt= new JTextField();
	    BoxLayout boxLayout = new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS); 
	    this.setLayout(boxLayout);
		this.add(label);
		this.add(txt);
		NButton button= new NButton("OK",tabbedPane,txt);
		this.add(button);
	}

}
