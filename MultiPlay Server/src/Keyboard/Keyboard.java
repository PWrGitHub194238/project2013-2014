package Keyboard;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import CodeKey.N;

public class Keyboard {
	private Robot robot;

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
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT(":")) {
				robot.keyPress(KeyEvent.VK_COLON);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("\"")) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_QUOTE);
			} else if (key == N.DeviceSignal.KEYBOARD_KEY_TO_INT("(")) {
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
			} else if (key == N.DeviceSignal.KEYBOARD_CI) {
				robot.keyPress(KeyEvent.VK_ALT);
				robot.keyPress(KeyEvent.VK_C);
			} else if (key == N.DeviceSignal.KEYBOARD_EU) {
				robot.keyPress(KeyEvent.VK_ALT);
				robot.keyPress(KeyEvent.VK_E);
			} else if (key == N.DeviceSignal.KEYBOARD_LY) {
				robot.keyPress(KeyEvent.VK_ALT);
				robot.keyPress(KeyEvent.VK_L);
			} else if (key == N.DeviceSignal.KEYBOARD_NI) {
				robot.keyPress(KeyEvent.VK_ALT);
				robot.keyPress(KeyEvent.VK_N);
			} else if (key == N.DeviceSignal.KEYBOARD_O_KRESKA) {
				robot.keyPress(KeyEvent.VK_ALT);
				robot.keyPress(KeyEvent.VK_O);
			} else if (key == N.DeviceSignal.KEYBOARD_OU) {
				robot.keyPress(KeyEvent.VK_ALT);
				robot.keyPress(KeyEvent.VK_A);
			} else if (key == N.DeviceSignal.KEYBOARD_SI) {
				robot.keyPress(KeyEvent.VK_ALT);
				robot.keyPress(KeyEvent.VK_S);
			} else if (key == N.DeviceSignal.KEYBOARD_ZI) {
				robot.keyPress(KeyEvent.VK_ALT);
				robot.keyPress(KeyEvent.VK_X);
			} else if (key == N.DeviceSignal.KEYBOARD_ZY) {
				robot.keyPress(KeyEvent.VK_ALT);
				robot.keyPress(KeyEvent.VK_Z);
			} else if (key == N.DeviceSignal.KEYBOARD_NI) {
				robot.keyPress(KeyEvent.VK_ALT);
				robot.keyPress(KeyEvent.VK_N);
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
			} else if (key == N.DeviceSignal.KEYBOARD_BCI) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_ALT);
				robot.keyPress(KeyEvent.VK_C);
			} else if (key == N.DeviceSignal.KEYBOARD_BEU) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_ALT);
				robot.keyPress(KeyEvent.VK_E);
			} else if (key == N.DeviceSignal.KEYBOARD_BLY) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_ALT);
				robot.keyPress(KeyEvent.VK_L);
			} else if (key == N.DeviceSignal.KEYBOARD_BNI) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_ALT);
				robot.keyPress(KeyEvent.VK_N);
			} else if (key == N.DeviceSignal.KEYBOARD_BO_KRESKA) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_ALT);
				robot.keyPress(KeyEvent.VK_O);
			} else if (key == N.DeviceSignal.KEYBOARD_BOU) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_ALT);
				robot.keyPress(KeyEvent.VK_A);
			} else if (key == N.DeviceSignal.KEYBOARD_BSI) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_ALT);
				robot.keyPress(KeyEvent.VK_S);
			} else if (key == N.DeviceSignal.KEYBOARD_BZI) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_ALT);
				robot.keyPress(KeyEvent.VK_X);
			} else if (key == N.DeviceSignal.KEYBOARD_BZY) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_ALT);
				robot.keyPress(KeyEvent.VK_Z);
			} else if (key == N.DeviceSignal.KEYBOARD_BNI) {
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_ALT);
				robot.keyPress(KeyEvent.VK_N);
			}
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
