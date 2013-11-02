
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class Serwer {

	/**
	 * @param args
	 * @throws AWTException
	 */
	public static void main(String[] args) throws AWTException {
		try {
			ServerSocket server = new ServerSocket(8888);
			Socket socket;
			Robot robot = new Robot();
			int x = 300, y = 300;
			while (true) {
				System.out.println("jestem1");
				socket = server.accept();
				System.out.println("jestem1");
				InputStream in = socket.getInputStream();
				DataInputStream datain = new DataInputStream(in);
				String napis = datain.readUTF();
				System.out.println(napis);
				if (napis.equals("mouse")) {
					String napis2 = datain.readUTF();
					String napis3 = datain.readUTF();
					datain.close();
					x = x + Integer.parseInt(napis2);
					y += Integer.parseInt(napis3);
					robot.mouseMove(x, y);
				}
				if (napis.equals("keyboard")) {
					String napis2 = datain.readUTF();
					datain.close();
					if (napis2.equals("enter")) {
						robot.keyPress(KeyEvent.VK_ENTER);
						robot.keyRelease(KeyEvent.VK_ENTER);
					}
					if (napis2.equals("up")) {
						robot.keyPress(KeyEvent.VK_UP);
						robot.keyRelease(KeyEvent.VK_UP);
					}
					if (napis2.equals("left")) {
						robot.keyPress(KeyEvent.VK_LEFT);
						robot.keyRelease(KeyEvent.VK_LEFT);
					}
					if (napis2.equals("right")) {
						robot.keyPress(KeyEvent.VK_RIGHT);
						robot.keyRelease(KeyEvent.VK_RIGHT);
					}
					if (napis2.equals("down")) {
						robot.keyPress(KeyEvent.VK_DOWN);
						robot.keyRelease(KeyEvent.VK_DOWN);
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
