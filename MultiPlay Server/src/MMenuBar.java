import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MMenuBar extends JMenuBar {

	public MMenuBar() {
		super();
		JMenu mMenu = new JMenu("File");
		JMenu mMenu2 = new JMenu("Help");
		JMenu mbl = new JMenu("Bluetooth");
		JMenu mwf = new JMenu("Wifi");
		JMenu mctrl = new JMenu("Controller");
		JMenu musr = new JMenu("Users");

		this.add(mMenu);
		this.add(musr);
		this.add(mbl);
		this.add(mwf);
		this.add(mctrl);
		this.add(mMenu2);

		mMenu.add(new JMenuItem("exit"));
		musr.add(new JMenuItem("Add User"));
		musr.add(new JMenuItem("Delete User"));
		mbl.add(new JMenuItem("Connect"));
		mwf.add(new JMenuItem("Connect"));
		mMenu2.add(new JMenuItem("help"));
		mMenu2.add(new JMenuItem("About"));

	}
}
