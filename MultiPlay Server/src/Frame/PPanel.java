package Frame;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PPanel extends JPanel {
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
