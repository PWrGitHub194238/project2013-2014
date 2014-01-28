package Frame;

import javax.swing.JFrame;
import javax.swing.JLabel;
/**
 * 
 * @author Piotr B¹czkiewicz
 *
 */
public class AlertFrame extends JFrame {
/**
 * 
 * @param name
 */
	public AlertFrame(String name){
		super(name);
		this.setSize(500, 100);
		JLabel authors = new JLabel(
				"<html>The driver was not initialized.<br> More information can be found in the \"Help\"</html>");
		this.add(authors);
		this.setLocation(500, 300);
		this.setVisible(true);
	}

}
