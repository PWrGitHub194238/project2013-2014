package Mouse;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
/*
 * @author Piotr Baczkiewicz
 * @see Pos
 */
public class Pos {
	public Pos() {

	}
	/*
	 * @see run
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
