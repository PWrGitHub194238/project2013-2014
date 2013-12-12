package Frame;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;

import Wifi.Connect;
//Menu Frame
public class MFrame extends JFrame {

	public MFrame() {
		super();
		// -------------------------MenuBar---------------------
		this.setTitle("MultiPlay");
		MenuBar mb = new MenuBar();
		Menu file = new Menu("File");
		Menu help = new Menu("Help");
		Menu options = new Menu("Options");
		mb.add(file);
		mb.add(help);
		mb.add(options);
		MenuItem exit = new MenuItem("Exit");
		MenuItem about = new MenuItem("About");
		MenuItem helps = new MenuItem("Help");
		help.add(about);
		help.add(helps);

		//----------------------- settings controllers-------------------
		JPanel mainPanel = new JPanel(new GridLayout(2, 2, 0, 0));
		this.add(mainPanel);
		
		//Mouse
		JPanel panelmouse = new JPanel();
		mainPanel.add(panelmouse);
		JRadioButton left = new JRadioButton("Left");
		left.setSelected(true);
		JRadioButton right = new JRadioButton("Right");

		ButtonGroup group = new ButtonGroup();
		group.add(left);
		group.add(right);
		panelmouse.add(left);
		panelmouse.add(right);
		
		left.setVisible(true);
		//Keyboard
		JPanel panelkeyboard = new JPanel();
		mainPanel.add(panelkeyboard);
		JLabel keyboard = new JLabel("Keyboard: ");
		panelkeyboard.add(keyboard);
		
		
		left.setVisible(true);
		//Wheel
		JPanel panelwheel = new JPanel();
		//Pad
		JPanel panelpad = new JPanel();

		//----------------------------Help Frame----------------------
		helps.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				HFrame helpframe = new HFrame();
				helpframe.setSize(500, 500);
				helpframe.setVisible(true);
			}
		});
		
		//-----------------------About Frame-----------------------
		about.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				AFrame helpframe = new AFrame();
				helpframe.setSize(500, 500);
				helpframe.setVisible(true);
			}

		});
		file.add(exit);
		setMenuBar(mb);
	}
}
