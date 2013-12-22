package Mouse;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;

public class Pos {
	public Pos() {

	}

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
