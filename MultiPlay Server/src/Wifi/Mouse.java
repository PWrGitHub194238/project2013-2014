package Wifi;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

public class Mouse {

	public void run(int x, int y) {
		try {
			Robot robot = new Robot();
			robot.mouseMove(x, y);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void click(String pm) {
		try {
			Robot robot = new Robot();
			if (pm.equals("l")) {
				robot.mousePress(InputEvent.BUTTON1_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_MASK);
			} else if (pm.equals("m")) {
				robot.mousePress(InputEvent.BUTTON2_MASK);
				robot.mouseRelease(InputEvent.BUTTON2_MASK);
			} else if (pm.equals("r")) {
				robot.mousePress(InputEvent.BUTTON3_MASK);
				robot.mouseRelease(InputEvent.BUTTON3_MASK);
			}
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
