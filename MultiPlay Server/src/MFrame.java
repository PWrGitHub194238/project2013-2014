import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class MFrame extends JFrame {
	int port = 8888;

	public MFrame() {
		super();
		this.setTitle("MultiPlay");
		this.setLayout(new BorderLayout());
		MMenuBar mbar = new MMenuBar();
		this.setJMenuBar(mbar);
		MJTabbedPane tabbedPane = new MJTabbedPane();
		getContentPane().add(tabbedPane);
		MButton add = new MButton(tabbedPane);
		this.add(add, BorderLayout.NORTH);
		this.add(tabbedPane, BorderLayout.CENTER);


	}
}
