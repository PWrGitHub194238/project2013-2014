package Keyboard;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import CodeKey.N;
/**
 * 
 * @author Piotr B¹czkiewicz
 *
 */
public class Keyboard {
	private Robot robot;
/**
 * Method of simulating a click on the keyboard
 * @param key
 */
	public void click(int key) {
		try {
			robot = new Robot();
			if (key == N.DeviceSignal.KEYBOARD_LEFT) {
				robot.keyPress(KeyEvent.VK_LEFT);
			} else if (key == N.DeviceSignal.KEYBOARD_RIGHT) {
				robot.keyPress(KeyEvent.VK_RIGHT);
			} else if (key == N.DeviceSignal.KEYBOARD_ENTER) {
				robot.keyPress(KeyEvent.VK_ENTER);
			} else if (key == N.DeviceSignal.KEYBOARD_ESC) {
				robot.keyPress(KeyEvent.VK_ESCAPE);
			} else if (key == N.DeviceSignal.KEYBOARD_DOWN) {
				robot.keyPress(KeyEvent.VK_DOWN);
			} else if (key == N.DeviceSignal.KEYBOARD_UP) {
				robot.keyPress(KeyEvent.VK_UP);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("q")) {
				robot.keyPress(KeyEvent.VK_Q);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("w")) {
				robot.keyPress(KeyEvent.VK_W);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("e")) {
				robot.keyPress(KeyEvent.VK_E);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("r")) {
				robot.keyPress(KeyEvent.VK_R);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("t")) {
				robot.keyPress(KeyEvent.VK_T);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("y")) {
				robot.keyPress(KeyEvent.VK_Y);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("u")) {
				robot.keyPress(KeyEvent.VK_U);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("i")) {
				robot.keyPress(KeyEvent.VK_I);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("o")) {
				robot.keyPress(KeyEvent.VK_O);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("p")) {
				robot.keyPress(KeyEvent.VK_P);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("a")) {
				robot.keyPress(KeyEvent.VK_A);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("s")) {
				robot.keyPress(KeyEvent.VK_S);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("d")) {
				robot.keyPress(KeyEvent.VK_D);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("f")) {
				robot.keyPress(KeyEvent.VK_F);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("g")) {
				robot.keyPress(KeyEvent.VK_G);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("h")) {
				robot.keyPress(KeyEvent.VK_H);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("j")) {
				robot.keyPress(KeyEvent.VK_J);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("k")) {
				robot.keyPress(KeyEvent.VK_K);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("l")) {
				robot.keyPress(KeyEvent.VK_L);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("z")) {
				robot.keyPress(KeyEvent.VK_Z);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("x")) {
				robot.keyPress(KeyEvent.VK_X);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("c")) {
				robot.keyPress(KeyEvent.VK_C);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("v")) {
				robot.keyPress(KeyEvent.VK_V);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("b")) {
				robot.keyPress(KeyEvent.VK_B);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("n")) {
				robot.keyPress(KeyEvent.VK_N);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("m")) {
				robot.keyPress(KeyEvent.VK_M);
			} else if (key == N.DeviceSignal.KEYBOARD_BACKSPACE) {
				robot.keyPress(KeyEvent.VK_BACK_SPACE);
			} else if (key == N.DeviceSignal.KEYBOARD_SPACE) {
				robot.keyPress(KeyEvent.VK_SPACE);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT(".")) {
				robot.keyPress(KeyEvent.VK_PERIOD);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT(",")) {
				robot.keyPress(KeyEvent.VK_COMMA);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT(";")) {
				robot.keyPress(KeyEvent.VK_SEMICOLON);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("!")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_1);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("?")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_SLASH);	
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("\\")) {
				robot.keyPress(KeyEvent.VK_BACK_SLASH);
					} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("|")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_BACK_SLASH);	
			}
			else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT(":")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_SEMICOLON);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("\"")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_QUOTE);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("#")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_3);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("$")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_4);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("^")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_6);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("&")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_7);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("*")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_8);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("%")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_5);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("[")) {
				robot.keyPress(KeyEvent.VK_OPEN_BRACKET);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("]")) {
				robot.keyPress(KeyEvent.VK_CLOSE_BRACKET);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("{")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_OPEN_BRACKET);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("}")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_CLOSE_BRACKET);
			}
			if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("/")) {
				robot.keyPress(KeyEvent.VK_SLASH);
			}
			else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("(")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_9);			
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT(")")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_0);			
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("=")) {
				robot.keyPress(KeyEvent.VK_EQUALS);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("+")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_EQUALS);		
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("@")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_2);		
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("0")) {
				robot.keyPress(KeyEvent.VK_0);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("1")) {
				robot.keyPress(KeyEvent.VK_1);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("2")) {
				robot.keyPress(KeyEvent.VK_2);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("3")) {
				robot.keyPress(KeyEvent.VK_3);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("4")) {
				robot.keyPress(KeyEvent.VK_4);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("5")) {
				robot.keyPress(KeyEvent.VK_5);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("6")) {
				robot.keyPress(KeyEvent.VK_6);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("7")) {
				robot.keyPress(KeyEvent.VK_7);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("8")) {
				robot.keyPress(KeyEvent.VK_8);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("9")) {
				robot.keyPress(KeyEvent.VK_9);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("-")) {
				robot.keyPress(KeyEvent.VK_MINUS);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("Q")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_Q);	
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("W")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_W);		
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("E")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_E);		
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("R")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_R);		
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("T")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_T);			
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("Y")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_Y);		
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("U")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_U);
				
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("I")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_I);
				
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("O")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_O);
				
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("P")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_P);
				
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("A")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_A);
				
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("S")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_S);
				
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("D")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_D);
				
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("F")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_F);
				
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("G")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_G);
			
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("<")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_PERIOD);
				
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT(">")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_COMMA);
				
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("H")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_H);
				
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("J")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_J);
				
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("K")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_K);
			
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("L")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_L);
				
			
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("Z")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_Z);
			
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("X")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_X);
				
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("C")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_C);
			
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("V")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_V);
				
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("B")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_B);
				
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("N")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_N);
				
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("M")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_M);
				
			} else if (key == N.DeviceSignal.KEYBOARD_BACKSPACE) {
				robot.keyPress(KeyEvent.VK_BACK_SPACE);

			} else if (key == N.DeviceSignal.KEYBOARD_HOME) {
				robot.keyPress(KeyEvent.VK_HOME);
				
			}  else if (key == N.DeviceSignal.KEYBOARD_END) {
				robot.keyPress(KeyEvent.VK_END);
			}
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
/**
 * method of simulating a fast click and release on the keyboard
 * @param key
 */
	public void clickandrelease(int key) {
		try {
			robot = new Robot();
			if (key == N.DeviceSignal.KEYBOARD_LEFT) {
				robot.keyPress(KeyEvent.VK_LEFT);
				robot.keyRelease(KeyEvent.VK_LEFT);
			} else if (key == N.DeviceSignal.KEYBOARD_RIGHT) {
				robot.keyPress(KeyEvent.VK_RIGHT);
				robot.keyRelease(KeyEvent.VK_RIGHT);
			} else if (key == N.DeviceSignal.KEYBOARD_ENTER) {
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
			} else if (key == N.DeviceSignal.KEYBOARD_ESC) {
				robot.keyPress(KeyEvent.VK_ESCAPE);
				robot.keyRelease(KeyEvent.VK_ESCAPE);
			} else if (key == N.DeviceSignal.KEYBOARD_DOWN) {
				robot.keyPress(KeyEvent.VK_DOWN);
				robot.keyRelease(KeyEvent.VK_DOWN);
			} else if (key == N.DeviceSignal.KEYBOARD_UP) {
				robot.keyPress(KeyEvent.VK_UP);
				robot.keyRelease(KeyEvent.VK_UP);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("q")) {
				robot.keyPress(KeyEvent.VK_Q);
				robot.keyRelease(KeyEvent.VK_Q);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("w")) {
				robot.keyPress(KeyEvent.VK_W);
				robot.keyRelease(KeyEvent.VK_W);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("e")) {
				robot.keyPress(KeyEvent.VK_E);
				robot.keyRelease(KeyEvent.VK_E);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("r")) {
				robot.keyPress(KeyEvent.VK_R);
				robot.keyRelease(KeyEvent.VK_R);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("t")) {
				robot.keyPress(KeyEvent.VK_T);
				robot.keyRelease(KeyEvent.VK_T);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("y")) {
				robot.keyPress(KeyEvent.VK_Y);
				robot.keyRelease(KeyEvent.VK_Y);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("u")) {
				robot.keyPress(KeyEvent.VK_U);
				robot.keyRelease(KeyEvent.VK_U);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("i")) {
				robot.keyPress(KeyEvent.VK_I);
				robot.keyRelease(KeyEvent.VK_I);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("o")) {
				robot.keyPress(KeyEvent.VK_O);
				robot.keyRelease(KeyEvent.VK_O);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("p")) {
				robot.keyPress(KeyEvent.VK_P);
				robot.keyRelease(KeyEvent.VK_P);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("a")) {
				robot.keyPress(KeyEvent.VK_A);
				robot.keyRelease(KeyEvent.VK_A);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("s")) {
				robot.keyPress(KeyEvent.VK_S);
				robot.keyRelease(KeyEvent.VK_S);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("d")) {
				robot.keyPress(KeyEvent.VK_D);
				robot.keyRelease(KeyEvent.VK_D);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("f")) {
				robot.keyPress(KeyEvent.VK_F);
				robot.keyRelease(KeyEvent.VK_F);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("g")) {
				robot.keyPress(KeyEvent.VK_G);
				robot.keyRelease(KeyEvent.VK_G);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("h")) {
				robot.keyPress(KeyEvent.VK_H);
				robot.keyRelease(KeyEvent.VK_H);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("j")) {
				robot.keyPress(KeyEvent.VK_J);
				robot.keyRelease(KeyEvent.VK_J);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("k")) {
				robot.keyPress(KeyEvent.VK_K);
				robot.keyRelease(KeyEvent.VK_K);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("l")) {
				robot.keyPress(KeyEvent.VK_L);
				robot.keyRelease(KeyEvent.VK_L);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("z")) {
				robot.keyPress(KeyEvent.VK_Z);
				robot.keyRelease(KeyEvent.VK_Z);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("x")) {
				robot.keyPress(KeyEvent.VK_X);
				robot.keyRelease(KeyEvent.VK_X);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("c")) {
				robot.keyPress(KeyEvent.VK_C);
				robot.keyRelease(KeyEvent.VK_C);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("v")) {
				robot.keyPress(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_V);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("b")) {
				robot.keyPress(KeyEvent.VK_B);
				robot.keyRelease(KeyEvent.VK_B);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("n")) {
				robot.keyPress(KeyEvent.VK_N);
				robot.keyRelease(KeyEvent.VK_N);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("m")) {
				robot.keyPress(KeyEvent.VK_M);
				robot.keyRelease(KeyEvent.VK_M);
			} else if (key == N.DeviceSignal.KEYBOARD_BACKSPACE) {
				robot.keyPress(KeyEvent.VK_BACK_SPACE);
				robot.keyRelease(KeyEvent.VK_BACK_SPACE);
			} else if (key == N.DeviceSignal.KEYBOARD_SPACE) {
				robot.keyPress(KeyEvent.VK_SPACE);
				robot.keyRelease(KeyEvent.VK_SPACE);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT(".")) {
				robot.keyPress(KeyEvent.VK_PERIOD);
				robot.keyRelease(KeyEvent.VK_PERIOD);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT(",")) {
				robot.keyPress(KeyEvent.VK_COMMA);
				robot.keyRelease(KeyEvent.VK_COMMA);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT(";")) {
				robot.keyPress(KeyEvent.VK_SEMICOLON);
				robot.keyRelease(KeyEvent.VK_SEMICOLON);

			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("!")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_1);
				robot.keyRelease(KeyEvent.VK_1);
				robot.keyRelease(KeyEvent.VK_SHIFT);

			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("?")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_SLASH);
				robot.keyRelease(KeyEvent.VK_SLASH);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("\\")) {
				robot.keyPress(KeyEvent.VK_BACK_SLASH);
				robot.keyRelease(KeyEvent.VK_BACK_SLASH);
					} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("|")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_BACK_SLASH);
				robot.keyRelease(KeyEvent.VK_BACK_SLASH);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			}

			else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT(":")) {
				robot.keyPress(KeyEvent.VK_SHIFT);

				robot.keyPress(KeyEvent.VK_SEMICOLON);
				robot.keyRelease(KeyEvent.VK_SEMICOLON);
				robot.keyRelease(KeyEvent.VK_SHIFT);

			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("\"")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_QUOTE);
				robot.keyRelease(KeyEvent.VK_QUOTE);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("#")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_3);
				robot.keyRelease(KeyEvent.VK_3);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("$")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_4);
				robot.keyRelease(KeyEvent.VK_4);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("^")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_6);
				robot.keyRelease(KeyEvent.VK_6);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("&")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_7);
				robot.keyRelease(KeyEvent.VK_7);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("*")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_8);
				robot.keyRelease(KeyEvent.VK_8);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("%")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_5);
				robot.keyRelease(KeyEvent.VK_5);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("[")) {
				robot.keyPress(KeyEvent.VK_OPEN_BRACKET);
				robot.keyRelease(KeyEvent.VK_OPEN_BRACKET);

			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("]")) {
				robot.keyPress(KeyEvent.VK_CLOSE_BRACKET);
				robot.keyRelease(KeyEvent.VK_CLOSE_BRACKET);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("{")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_OPEN_BRACKET);
				robot.keyRelease(KeyEvent.VK_OPEN_BRACKET);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("}")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_CLOSE_BRACKET);
				robot.keyRelease(KeyEvent.VK_CLOSE_BRACKET);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			}
			if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("/")) {

				robot.keyPress(KeyEvent.VK_SLASH);
				robot.keyRelease(KeyEvent.VK_SLASH);

			}

			else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("(")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_9);
				robot.keyRelease(KeyEvent.VK_9);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT(")")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_0);
				robot.keyRelease(KeyEvent.VK_0);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("=")) {
				robot.keyPress(KeyEvent.VK_EQUALS);
				robot.keyRelease(KeyEvent.VK_EQUALS);

			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("+")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_EQUALS);
				robot.keyRelease(KeyEvent.VK_EQUALS);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("@")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_2);
				robot.keyRelease(KeyEvent.VK_2);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("0")) {
				robot.keyPress(KeyEvent.VK_0);
				robot.keyRelease(KeyEvent.VK_0);

			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("1")) {
				robot.keyPress(KeyEvent.VK_1);
				robot.keyRelease(KeyEvent.VK_1);

			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("2")) {
				robot.keyPress(KeyEvent.VK_2);
				robot.keyRelease(KeyEvent.VK_2);

			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("3")) {
				robot.keyPress(KeyEvent.VK_3);
				robot.keyRelease(KeyEvent.VK_3);

			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("4")) {
				robot.keyPress(KeyEvent.VK_4);
				robot.keyRelease(KeyEvent.VK_4);

			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("5")) {
				robot.keyPress(KeyEvent.VK_5);
				robot.keyRelease(KeyEvent.VK_5);

			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("6")) {
				robot.keyPress(KeyEvent.VK_6);
				robot.keyRelease(KeyEvent.VK_6);

			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("7")) {
				robot.keyPress(KeyEvent.VK_7);
				robot.keyRelease(KeyEvent.VK_7);

			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("8")) {
				robot.keyPress(KeyEvent.VK_8);
				robot.keyRelease(KeyEvent.VK_8);

			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("9")) {
				robot.keyPress(KeyEvent.VK_9);
				robot.keyRelease(KeyEvent.VK_9);

			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("-")) {
				robot.keyPress(KeyEvent.VK_MINUS);
				robot.keyRelease(KeyEvent.VK_MINUS);

			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("Q")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_Q);
				robot.keyRelease(KeyEvent.VK_Q);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("W")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_W);
				robot.keyRelease(KeyEvent.VK_W);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("E")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_E);
				robot.keyRelease(KeyEvent.VK_E);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("R")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_R);
				robot.keyRelease(KeyEvent.VK_R);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("T")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_T);
				robot.keyRelease(KeyEvent.VK_T);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("Y")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_Y);
				robot.keyRelease(KeyEvent.VK_Y);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("U")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_U);
				robot.keyRelease(KeyEvent.VK_U);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("I")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_I);
				robot.keyRelease(KeyEvent.VK_I);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("O")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_O);
				robot.keyRelease(KeyEvent.VK_O);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("P")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_P);
				robot.keyRelease(KeyEvent.VK_P);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("A")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_A);
				robot.keyRelease(KeyEvent.VK_A);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("S")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_S);
				robot.keyRelease(KeyEvent.VK_S);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("D")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_D);
				robot.keyRelease(KeyEvent.VK_D);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("F")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_F);
				robot.keyRelease(KeyEvent.VK_F);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("G")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_G);
				robot.keyRelease(KeyEvent.VK_G);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("<")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_PERIOD);
				robot.keyRelease(KeyEvent.VK_PERIOD);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT(">")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_COMMA);
				robot.keyRelease(KeyEvent.VK_COMMA);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("H")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_H);
				robot.keyRelease(KeyEvent.VK_H);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("J")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_J);
				robot.keyRelease(KeyEvent.VK_J);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("K")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_K);
				robot.keyRelease(KeyEvent.VK_K);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("L")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_L);
				robot.keyRelease(KeyEvent.VK_L);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("Z")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_Z);
				robot.keyRelease(KeyEvent.VK_Z);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("X")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_X);
				robot.keyRelease(KeyEvent.VK_X);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("C")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_C);
				robot.keyRelease(KeyEvent.VK_C);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("V")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("B")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_B);
				robot.keyRelease(KeyEvent.VK_R);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("N")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_N);
				robot.keyRelease(KeyEvent.VK_N);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("M")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_M);
				robot.keyRelease(KeyEvent.VK_M);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_BACKSPACE) {
				robot.keyPress(KeyEvent.VK_BACK_SPACE);
				robot.keyRelease(KeyEvent.VK_BACK_SPACE);

			} else if (key == N.DeviceSignal.KEYBOARD_HOME) {
				robot.keyPress(KeyEvent.VK_HOME);
				robot.keyRelease(KeyEvent.VK_HOME);
				
			}  else if (key == N.DeviceSignal.KEYBOARD_END) {
				robot.keyPress(KeyEvent.VK_END);
				robot.keyRelease(KeyEvent.VK_END);
			}
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * method of simulating a release on the keyboard
	 * @param key
	 */
	public void release(int key) {
		try {
			robot = new Robot();
			if (key == N.DeviceSignal.KEYBOARD_LEFT) {
				robot.keyRelease(KeyEvent.VK_LEFT);
			} else if (key == N.DeviceSignal.KEYBOARD_RIGHT) {
				robot.keyRelease(KeyEvent.VK_RIGHT);
			} else if (key == N.DeviceSignal.KEYBOARD_ENTER) {
				robot.keyRelease(KeyEvent.VK_ENTER);
			} else if (key == N.DeviceSignal.KEYBOARD_ESC) {
				robot.keyRelease(KeyEvent.VK_ESCAPE);
			} else if (key == N.DeviceSignal.KEYBOARD_DOWN) {
				robot.keyRelease(KeyEvent.VK_DOWN);
			} else if (key == N.DeviceSignal.KEYBOARD_UP) {
				robot.keyRelease(KeyEvent.VK_UP);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("q")) {
				robot.keyRelease(KeyEvent.VK_Q);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("w")) {
				robot.keyRelease(KeyEvent.VK_W);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("e")) {
				robot.keyRelease(KeyEvent.VK_E);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("r")) {
				robot.keyRelease(KeyEvent.VK_R);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("t")) {
				robot.keyRelease(KeyEvent.VK_T);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("y")) {
				robot.keyRelease(KeyEvent.VK_Y);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("u")) {
				robot.keyRelease(KeyEvent.VK_U);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("i")) {
				robot.keyRelease(KeyEvent.VK_I);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("o")) {
				robot.keyRelease(KeyEvent.VK_O);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("p")) {
				robot.keyRelease(KeyEvent.VK_P);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("a")) {
				robot.keyRelease(KeyEvent.VK_A);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("s")) {
				robot.keyRelease(KeyEvent.VK_S);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("d")) {
				robot.keyRelease(KeyEvent.VK_D);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("f")) {
				robot.keyRelease(KeyEvent.VK_F);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("g")) {
				robot.keyRelease(KeyEvent.VK_G);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("h")) {
				robot.keyRelease(KeyEvent.VK_H);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("j")) {
				robot.keyRelease(KeyEvent.VK_J);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("k")) {
				robot.keyRelease(KeyEvent.VK_K);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("l")) {
				robot.keyRelease(KeyEvent.VK_L);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("z")) {
				robot.keyRelease(KeyEvent.VK_Z);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("x")) {
				robot.keyRelease(KeyEvent.VK_X);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("c")) {
				robot.keyRelease(KeyEvent.VK_C);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("v")) {
				robot.keyRelease(KeyEvent.VK_V);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("b")) {
				robot.keyRelease(KeyEvent.VK_B);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("n")) {
				robot.keyRelease(KeyEvent.VK_N);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("m")) {
				robot.keyRelease(KeyEvent.VK_M);
			} else if (key == N.DeviceSignal.KEYBOARD_BACKSPACE) {
				robot.keyRelease(KeyEvent.VK_BACK_SPACE);
			} else if (key == N.DeviceSignal.KEYBOARD_SPACE) {
				robot.keyRelease(KeyEvent.VK_SPACE);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT(".")) {
				robot.keyRelease(KeyEvent.VK_PERIOD);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT(",")) {
				robot.keyRelease(KeyEvent.VK_COMMA);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT(";")) {
				robot.keyRelease(KeyEvent.VK_SEMICOLON);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("!")) {
		
				robot.keyRelease(KeyEvent.VK_1);
				robot.keyRelease(KeyEvent.VK_SHIFT);

			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("?")) {
			
				robot.keyRelease(KeyEvent.VK_SLASH);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("\\")) {
				robot.keyRelease(KeyEvent.VK_BACK_SLASH);
					} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("|")) {
				
				robot.keyRelease(KeyEvent.VK_BACK_SLASH);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			}

			else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT(":")) {
			
				robot.keyRelease(KeyEvent.VK_SEMICOLON);
				robot.keyRelease(KeyEvent.VK_SHIFT);

			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("\"")) {
				
				robot.keyRelease(KeyEvent.VK_QUOTE);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("#")) {
				
				robot.keyRelease(KeyEvent.VK_3);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("$")) {
				
				robot.keyRelease(KeyEvent.VK_4);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("^")) {
				
				robot.keyRelease(KeyEvent.VK_6);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("&")) {
				
				robot.keyRelease(KeyEvent.VK_7);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("*")) {
			
				robot.keyRelease(KeyEvent.VK_8);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("%")) {
				
				robot.keyRelease(KeyEvent.VK_5);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("[")) {
				robot.keyRelease(KeyEvent.VK_OPEN_BRACKET);

			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("]")) {
				robot.keyRelease(KeyEvent.VK_CLOSE_BRACKET);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("{")) {
				
				robot.keyRelease(KeyEvent.VK_OPEN_BRACKET);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("}")) {
				
				robot.keyRelease(KeyEvent.VK_CLOSE_BRACKET);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			}
			if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("/")) {

				robot.keyRelease(KeyEvent.VK_SLASH);

			}

			else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("(")) {
			
				robot.keyRelease(KeyEvent.VK_9);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT(")")) {
				
				robot.keyRelease(KeyEvent.VK_0);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("=")) {
				robot.keyRelease(KeyEvent.VK_EQUALS);

			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("+")) {
				
				robot.keyRelease(KeyEvent.VK_EQUALS);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("@")) {
				
				robot.keyRelease(KeyEvent.VK_2);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("0")) {
				robot.keyRelease(KeyEvent.VK_0);

			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("1")) {
				
				robot.keyRelease(KeyEvent.VK_1);

			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("2")) {
			
				robot.keyRelease(KeyEvent.VK_2);

			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("3")) {
				
				robot.keyRelease(KeyEvent.VK_3);

			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("4")) {
				
				robot.keyRelease(KeyEvent.VK_4);

			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("5")) {
				
				robot.keyRelease(KeyEvent.VK_5);

			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("6")) {
			
				robot.keyRelease(KeyEvent.VK_6);

			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("7")) {
				
				robot.keyRelease(KeyEvent.VK_7);

			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("8")) {
				
				robot.keyRelease(KeyEvent.VK_8);

			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("9")) {
				
				robot.keyRelease(KeyEvent.VK_9);

			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("-")) {
			
				robot.keyRelease(KeyEvent.VK_MINUS);

			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("Q")) {
				
				robot.keyRelease(KeyEvent.VK_Q);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("W")) {
				
				robot.keyRelease(KeyEvent.VK_W);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("E")) {
				
				robot.keyRelease(KeyEvent.VK_E);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("R")) {
				
				robot.keyRelease(KeyEvent.VK_R);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("T")) {
				
				robot.keyRelease(KeyEvent.VK_T);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("Y")) {
				
				robot.keyRelease(KeyEvent.VK_Y);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("U")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_U);
				robot.keyRelease(KeyEvent.VK_U);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("I")) {
				
				robot.keyRelease(KeyEvent.VK_I);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("O")) {
				
				robot.keyRelease(KeyEvent.VK_O);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("P")) {
				
				robot.keyRelease(KeyEvent.VK_P);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("A")) {
				
				robot.keyRelease(KeyEvent.VK_A);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("S")) {
								robot.keyRelease(KeyEvent.VK_S);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("D")) {
				
				robot.keyRelease(KeyEvent.VK_D);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("F")) {
				
				robot.keyRelease(KeyEvent.VK_F);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("G")) {
				
				robot.keyRelease(KeyEvent.VK_G);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("<")) {
				
				robot.keyRelease(KeyEvent.VK_PERIOD);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT(">")) {
				
				robot.keyRelease(KeyEvent.VK_COMMA);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("H")) {
				
				robot.keyRelease(KeyEvent.VK_H);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("J")) {
				
				robot.keyRelease(KeyEvent.VK_J);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("K")) {
				
				robot.keyRelease(KeyEvent.VK_K);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("L")) {
				
				robot.keyRelease(KeyEvent.VK_L);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("Z")) {
				
				robot.keyRelease(KeyEvent.VK_Z);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("X")) {
				
				robot.keyRelease(KeyEvent.VK_X);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("C")) {
				
				robot.keyRelease(KeyEvent.VK_C);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("V")) {
			
				robot.keyRelease(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("B")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_B);
				robot.keyRelease(KeyEvent.VK_R);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("N")) {
				
				robot.keyRelease(KeyEvent.VK_N);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("M")) {
				
				robot.keyRelease(KeyEvent.VK_M);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (key == N.DeviceSignal.KEYBOARD_BACKSPACE) {
				robot.keyRelease(KeyEvent.VK_BACK_SPACE);

			} else if (key == N.DeviceSignal.KEYBOARD_HOME) {
				robot.keyRelease(KeyEvent.VK_HOME);
				
			}  else if (key == N.DeviceSignal.KEYBOARD_END) {
				robot.keyRelease(KeyEvent.VK_END);
			}
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}    
