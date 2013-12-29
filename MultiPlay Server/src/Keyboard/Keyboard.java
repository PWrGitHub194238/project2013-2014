package Keyboard;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import Wifi.N;

public class Keyboard {

	public void click(int key) {
		Robot robot;
		
		try {
			robot = new Robot();
			if(key==N.DeviceSignal.KEYBOARD_LEFT){
				robot.keyPress(KeyEvent.VK_LEFT);
			}
			else if(key==N.DeviceSignal.KEYBOARD_RIGHT){
				robot.keyPress(KeyEvent.VK_RIGHT);
				
			}
			else if(key==N.DeviceSignal.KEYBOARD_RIGHT){
				robot.keyPress(KeyEvent.VK_DOWN);
			}
			else if(key==N.DeviceSignal.KEYBOARD_UP){

				robot.keyPress(KeyEvent.VK_UP);
			}
			else if(key==N.DeviceSignal.KEYBOARD_KEY_TO_INT("q")){

				robot.keyPress(KeyEvent.VK_Q);
			}
			else if(key==N.DeviceSignal.KEYBOARD_KEY_TO_INT("w")){

				robot.keyPress(KeyEvent.VK_W);
			
			}
			else if(key==N.DeviceSignal.KEYBOARD_KEY_TO_INT("e")){

				robot.keyPress(KeyEvent.VK_E);
			}
			else if(key==N.DeviceSignal.KEYBOARD_KEY_TO_INT("r")){
				robot.keyPress(KeyEvent.VK_R);
			}
			else if(key==N.DeviceSignal.KEYBOARD_KEY_TO_INT("t")){

				robot.keyPress(KeyEvent.VK_T);
			}
			else if(key==N.DeviceSignal.KEYBOARD_KEY_TO_INT("y")){

				robot.keyPress(KeyEvent.VK_Y);
			}
			else if(key==N.DeviceSignal.KEYBOARD_KEY_TO_INT("u")){

				robot.keyPress(KeyEvent.VK_U);
			}
			else if(key==N.DeviceSignal.KEYBOARD_KEY_TO_INT("i")){

				robot.keyPress(KeyEvent.VK_I);
			}
			else if(key==N.DeviceSignal.KEYBOARD_KEY_TO_INT("o")){

				robot.keyPress(KeyEvent.VK_O);
			}
			else if(key==N.DeviceSignal.KEYBOARD_KEY_TO_INT("p")){

				robot.keyPress(KeyEvent.VK_P);
			}	
			else if(key==N.DeviceSignal.KEYBOARD_KEY_TO_INT("a")){

				robot.keyPress(KeyEvent.VK_A);
			}
			else if(key==N.DeviceSignal.KEYBOARD_KEY_TO_INT("s")){

				robot.keyPress(KeyEvent.VK_S);
			}
			else if(key==N.DeviceSignal.KEYBOARD_KEY_TO_INT("d")){

				robot.keyPress(KeyEvent.VK_D);
			}
			else if(key==N.DeviceSignal.KEYBOARD_KEY_TO_INT("f")){

				robot.keyPress(KeyEvent.VK_F);
			}
			else if(key==N.DeviceSignal.KEYBOARD_KEY_TO_INT("g")){

				robot.keyPress(KeyEvent.VK_G);
			}
			else if(key==N.DeviceSignal.KEYBOARD_KEY_TO_INT("h")){

				robot.keyPress(KeyEvent.VK_H);
			}
			else if(key==N.DeviceSignal.KEYBOARD_KEY_TO_INT("j")){

				robot.keyPress(KeyEvent.VK_J);
			}
			else if(key==N.DeviceSignal.KEYBOARD_KEY_TO_INT("k")){

				robot.keyPress(KeyEvent.VK_K);
			}
			else if(key==N.DeviceSignal.KEYBOARD_KEY_TO_INT("l")){

				robot.keyPress(KeyEvent.VK_L);
			}
			else if(key==N.DeviceSignal.KEYBOARD_KEY_TO_INT("z")){

				robot.keyPress(KeyEvent.VK_Z);
			}
			else if(key==N.DeviceSignal.KEYBOARD_KEY_TO_INT("x")){

				robot.keyPress(KeyEvent.VK_X);
			}
			else if(key==N.DeviceSignal.KEYBOARD_KEY_TO_INT("c")){

				robot.keyPress(KeyEvent.VK_C);
			}
			else if(key==N.DeviceSignal.KEYBOARD_KEY_TO_INT("v")){

				robot.keyPress(KeyEvent.VK_V);
			}
			else if(key==N.DeviceSignal.KEYBOARD_KEY_TO_INT("b")){

				robot.keyPress(KeyEvent.VK_B);
			}
			else if(key==N.DeviceSignal.KEYBOARD_KEY_TO_INT("n")){

				robot.keyPress(KeyEvent.VK_N);
			}
			else if(key==N.DeviceSignal.KEYBOARD_KEY_TO_INT("m")){

				robot.keyPress(KeyEvent.VK_M);
			}
			else if(key==N.DeviceSignal.KEYBOARD_BACKSPACE){

				robot.keyPress(KeyEvent.VK_BACK_SPACE);
			}
			else if(key==N.DeviceSignal.KEYBOARD_SPACE){
				
				robot.keyPress(KeyEvent.VK_SPACE);
			}
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		/*try {
			Robot robot = new Robot();
			if (key.equals("esc")) {
				robot.keyPress(KeyEvent.VK_ESCAPE);
			} else if (key.equals("f1")) {
				robot.keyPress(KeyEvent.VK_F1);
			} else if (key.equals("f2")) {
				robot.keyPress(KeyEvent.VK_F2);
			} else if (key.equals("f3")) {
				robot.keyPress(KeyEvent.VK_F3);
			} else if (key.equals("f4")) {
				robot.keyPress(KeyEvent.VK_F4);
			} else if (key.equals("f5")) {
				robot.keyPress(KeyEvent.VK_F5);
			} else if (key.equals("f6")) {
				robot.keyPress(KeyEvent.VK_F6);
			} else if (key.equals("f7")) {
				robot.keyPress(KeyEvent.VK_F7);
			} else if (key.equals("f8")) {
				robot.keyPress(KeyEvent.VK_F8);
			} else if (key.equals("f9")) {
				robot.keyPress(KeyEvent.VK_F9);
			} else if (key.equals("f10")) {
				robot.keyPress(KeyEvent.VK_F10);
			} else if (key.equals("f11")) {
				robot.keyPress(KeyEvent.VK_F11);
			} else if (key.equals("f12")) {
				robot.keyPress(KeyEvent.VK_F12);
			} else if (key.equals("del")) {
				robot.keyPress(KeyEvent.VK_DELETE);
			} else if (key.equals("print")) {
				robot.keyPress(KeyEvent.VK_PRINTSCREEN);
			} else if (key.equals("`")) {
				// ???????????
			} else if (key.equals("1")) {
				robot.keyPress(KeyEvent.VK_1);
			} else if (key.equals("2")) {
				robot.keyPress(KeyEvent.VK_2);
			} else if (key.equals("3")) {
				robot.keyPress(KeyEvent.VK_3);
			} else if (key.equals("4")) {
				robot.keyPress(KeyEvent.VK_4);
			} else if (key.equals("5")) {
				robot.keyPress(KeyEvent.VK_5);
			} else if (key.equals("6")) {
				robot.keyPress(KeyEvent.VK_6);
			} else if (key.equals("7")) {
				robot.keyPress(KeyEvent.VK_7);
			} else if (key.equals("8")) {
				robot.keyPress(KeyEvent.VK_8);
			} else if (key.equals("9")) {
				robot.keyPress(KeyEvent.VK_9);
			} else if (key.equals("0")) {
				robot.keyPress(KeyEvent.VK_0);
			} else if (key.equals("-")) {
				robot.keyPress(KeyEvent.VK_MINUS);
			} else if (key.equals("+")) {
				robot.keyPress(KeyEvent.VK_PLUS);
			} else if (key.equals("backspace")) {
				robot.keyPress(KeyEvent.VK_BACK_SPACE);
			} else if (key.equals("tab")) {
				robot.keyPress(KeyEvent.VK_TAB);
			} else if (key.equals("q")) {
				robot.keyPress(KeyEvent.VK_Q);
			} else if (key.equals("w")) {
				robot.keyPress(KeyEvent.VK_W);
			} else if (key.equals("e")) {
				robot.keyPress(KeyEvent.VK_E);
			} else if (key.equals("r")) {
				robot.keyPress(KeyEvent.VK_R);
			} else if (key.equals("t")) {
				robot.keyPress(KeyEvent.VK_T);
			} else if (key.equals("y")) {
				robot.keyPress(KeyEvent.VK_Y);
			} else if (key.equals("u")) {
				robot.keyPress(KeyEvent.VK_U);
			} else if (key.equals("i")) {
				robot.keyPress(KeyEvent.VK_I);
			} else if (key.equals("o")) {
				robot.keyPress(KeyEvent.VK_O);
			} else if (key.equals("p")) {
				robot.keyPress(KeyEvent.VK_P);
			} else if (key.equals("[")) {
				// ??????
			} else if (key.equals("]")) {
				// ????????????????
			} else if (key.equals("\\")) {
				// ????????????????????
			} else if (key.equals("caps")) {
				robot.keyPress(KeyEvent.VK_CAPS_LOCK);
			} else if (key.equals("a")) {
				robot.keyPress(KeyEvent.VK_A);
			} else if (key.equals("s")) {
				robot.keyPress(KeyEvent.VK_S);
			} else if (key.equals("d")) {
				robot.keyPress(KeyEvent.VK_D);
			} else if (key.equals("f")) {
				robot.keyPress(KeyEvent.VK_F);
			} else if (key.equals("g")) {
				robot.keyPress(KeyEvent.VK_G);
			} else if (key.equals("h")) {
				robot.keyPress(KeyEvent.VK_H);
			} else if (key.equals("j")) {
				robot.keyPress(KeyEvent.VK_J);
			} else if (key.equals("k")) {
				robot.keyPress(KeyEvent.VK_K);
			} else if (key.equals("l")) {
				robot.keyPress(KeyEvent.VK_L);
			} else if (key.equals(";")) {
				// ???????????????????
			} else if (key.equals("'")) {
				robot.keyPress(KeyEvent.VK_QUOTE);
			} else if (key.equals("lshift")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
			} else if (key.equals("z")) {
				robot.keyPress(KeyEvent.VK_Z);
			} else if (key.equals("x")) {
				robot.keyPress(KeyEvent.VK_X);
			} else if (key.equals("c")) {
				robot.keyPress(KeyEvent.VK_C);
			} else if (key.equals("v")) {
				robot.keyPress(KeyEvent.VK_V);
			} else if (key.equals("b")) {
				robot.keyPress(KeyEvent.VK_B);
			} else if (key.equals("n")) {
				robot.keyPress(KeyEvent.VK_N);
			} else if (key.equals("m")) {
				robot.keyPress(KeyEvent.VK_M);
			} else if (key.equals(",")) {
				// ????????????????
			} else if (key.equals(".")) {
				// ???????????????????
			} else if (key.equals("/")) {
				// ??????????????????????
			} else if (key.equals("rshift")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
			} else if (key.equals("lctrl")) {
				robot.keyPress(KeyEvent.VK_CONTROL);
			} else if (key.equals("win")) {
				robot.keyPress(KeyEvent.VK_WINDOWS);
			} else if (key.equals("lalt")) {
				robot.keyPress(KeyEvent.VK_ALT);
			} else if (key.equals("space")) {
				robot.keyPress(KeyEvent.VK_SPACE);
			} else if (key.equals("ralt")) {
				robot.keyPress(KeyEvent.VK_ALT);
			} else if (key.equals("rctrl")) {
				robot.keyPress(KeyEvent.VK_CONTROL);
			} else if (key.equals("left")) {
				robot.keyPress(KeyEvent.VK_LEFT);
			} else if (key.equals("up")) {
				robot.keyPress(KeyEvent.VK_UP);
			} else if (key.equals("right")) {
				robot.keyPress(KeyEvent.VK_RIGHT);
			} else if (key.equals("down")) {
				robot.keyPress(KeyEvent.VK_DOWN);
			} else if (key.equals("n1")) {
				robot.keyPress(KeyEvent.VK_NUMPAD1);
			} else if (key.equals("n2")) {
				robot.keyPress(KeyEvent.VK_NUMPAD2);
			} else if (key.equals("n3")) {
				robot.keyPress(KeyEvent.VK_NUMPAD3);
			} else if (key.equals("n4")) {
				robot.keyPress(KeyEvent.VK_NUMPAD4);
			} else if (key.equals("n5")) {
				robot.keyPress(KeyEvent.VK_NUMPAD5);
			} else if (key.equals("n6")) {
				robot.keyPress(KeyEvent.VK_NUMPAD6);
			} else if (key.equals("n7")) {
				robot.keyPress(KeyEvent.VK_NUMPAD7);
			} else if (key.equals("n8")) {
				robot.keyPress(KeyEvent.VK_NUMPAD8);
			} else if (key.equals("n9")) {
				robot.keyPress(KeyEvent.VK_NUMPAD9);
			} else if (key.equals("n0")) {
				robot.keyPress(KeyEvent.VK_NUMPAD0);
			} else if (key.equals("end")) {
				robot.keyPress(KeyEvent.VK_END);
			} else if (key.equals("home")) {
				robot.keyPress(KeyEvent.VK_HOME);
			} else if (key.equals("enter")) {
				robot.keyPress(KeyEvent.VK_ENTER);
			}
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
}
