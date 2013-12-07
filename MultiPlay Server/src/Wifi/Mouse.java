package Wifi;

import java.awt.AWTException;
import java.awt.Robot;

public class Mouse {

	public void run( int x, int y){
		try {
			Robot robot = new Robot();
			robot.mouseMove(x, y);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
