package Frame;

import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * 
 * @author Piotr B¹czkiewicz
 *
 */
public class PPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1481970208803953523L;

	/**
	 * 
	 * @param dev
	 * @param system
	 */
	public PPanel(String dev, String system) {
		super();
		JLabel txt = new JLabel();
		if (dev == "device")
			txt.setText("<html>Device driver must be installed.<br> You can download it from the: <br> http://headsoft.com.au/download/pc/VJoySetup_DriverOnly.exe</html>");
		else if (dev == "bluetooth")
			if (system == "Linux")
				txt.setText("<html>Do not have a bluetooth adapter or you need to install the library libbluetooth-dev package</html>");
			else
				txt.setText("<html>Do not have a bluetooth adapter </html>");

		this.add(txt);
	}
}
