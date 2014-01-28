package Mouse;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
/**
 * Return actually mouse position
 * @author Piotr B¹czkiewicz
 *
 */
public class Pos {
	/**
	 * Constructor
	 */
	public Pos() {

	}
	/**
	 * method get a actually mouse position, x and y
	 */
	public void run() {
		while (true) {
			PointerInfo a = MouseInfo.getPointerInfo();
			Point b = a.getLocation();
			int x = (int) b.getX();
			int y = (int) b.getY();
			System.out.print(y+"\n" + x+"\n");
		}
	}
}
