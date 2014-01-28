package Mouse;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;

import CodeKey.N;
/**
 * method of simulating mouse 
 * @author Piotr B¹czkiewicz
 *
 */
public class Mouse {
	/**
	 * method of simulating mouse offset
	 * @param x
	 * @param y
	 */
	public void run(int x, int y) {
		try {
			Robot robot = new Robot();
			PointerInfo a = MouseInfo.getPointerInfo();
			Point b = a.getLocation();
			int x1 = (int) b.getX();
			int y1 = (int) b.getY();
			Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
			//if (!(x1 + x > d.getWidth()))
				robot.mouseMove(x1 + x, y1);
				System.out.println("X");
		//	if (!(y1 + y > d.getHeight()) || !(x1 + x > d.getWidth()))
				robot.mouseMove(x1 + x, y1+y);

		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
/**
 * method of simulating mouse click
 * @param pm
 */
	public void click(int pm) {
		try {
			Robot robot = new Robot();
			if(pm==N.DeviceSignal.MOUSE_LPM) {
				robot.mousePress(InputEvent.BUTTON1_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_MASK);
			}
			else if(pm==N.DeviceSignal.MOUSE_PPM){
				robot.mousePress(InputEvent.BUTTON3_MASK);
				robot.mouseRelease(InputEvent.BUTTON3_MASK);
			}
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
