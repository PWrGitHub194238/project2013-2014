package com.android.controllers.mouse;

import com.android.application.MultiPlayApplication;
import com.android.application.N;
import com.android.application.N.Helper;

import com.android.dialogs.AlertDialogs;
import com.android.dialogs.elements.DialogListCore;
import com.android.dialogs.elements.MultiPlayExplorerActivityDialogList;
import com.android.multiplay.R;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

//myszka oparta na żyroskopie(beta).
public class Gyromouse extends Activity implements SensorEventListener,
		OnTouchListener {
	private int shiftflag = 0, altflag = 0;
	private SensorManager sm;
	private TextView tv;
	private Button button1, button2, button3, button4, button5, button6,
			button7, button8, button9, button10, button11, button12, button13,
			button14, button15, button16, button17, button18, button19,
			button20, button21, button22, button23, button24, button25,
			button26, button27, button28, button29, button30, button31,
			button32, button33, button34, button35, button36;

	private int stop;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gyromouse);
		// button = (Button) super.findViewById(R.id.stopbu);
		// bundle = super.getIntent().getExtras();
		// ip = bundle.getString("ip");
		tv = (TextView) findViewById(R.id.text);
		stop = 0;
		button1 = (Button) super.findViewById(R.id.leftb);
		button2 = (Button) super.findViewById(R.id.rightb);
		button3 = (Button) super.findViewById(R.id.upb);

		button4 = (Button) super.findViewById(R.id.downb);
		button5 = (Button) super.findViewById(R.id.enterb);
		button6 = (Button) super.findViewById(R.id.bshift);
		button7 = (Button) super.findViewById(R.id.bq);
		button8 = (Button) super.findViewById(R.id.bw);
		button9 = (Button) super.findViewById(R.id.be);
		button10 = (Button) super.findViewById(R.id.br);
		button11 = (Button) super.findViewById(R.id.bt);
		button12 = (Button) super.findViewById(R.id.by);
		button13 = (Button) super.findViewById(R.id.bu);
		button14 = (Button) super.findViewById(R.id.bi);
		button15 = (Button) super.findViewById(R.id.bo);
		button16 = (Button) super.findViewById(R.id.bp);
		button17 = (Button) super.findViewById(R.id.ba);
		button18 = (Button) super.findViewById(R.id.bs);
		button19 = (Button) super.findViewById(R.id.bd);
		button20 = (Button) super.findViewById(R.id.bf);
		button21 = (Button) super.findViewById(R.id.bg);
		button22 = (Button) super.findViewById(R.id.bh);
		button23 = (Button) super.findViewById(R.id.bj);
		button24 = (Button) super.findViewById(R.id.bk);
		button25 = (Button) super.findViewById(R.id.bl);
		button26 = (Button) super.findViewById(R.id.bz);
		button27 = (Button) super.findViewById(R.id.bx);
		button28 = (Button) super.findViewById(R.id.bc);
		button29 = (Button) super.findViewById(R.id.bv);
		button30 = (Button) super.findViewById(R.id.bb);
		button31 = (Button) super.findViewById(R.id.bn);
		button32 = (Button) super.findViewById(R.id.bm);
		button33 = (Button) super.findViewById(R.id.balt);
		button34 = (Button) super.findViewById(R.id.bspace);
		button35 = (Button) super.findViewById(R.id.besc);

		sm = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);

		sm.registerListener(this,
				sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_NORMAL);

	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSensorChanged(SensorEvent arg0) {
		float x = arg0.values[0];
		float y = arg0.values[1];
		if (stop == 0) {
			tv.setText(Integer.toString((int) x) + " "
					+ Integer.toString((int) y));
			int signal = Helper.encodeSignal(N.Device.MOUSE, N.DeviceDataCounter.DOUBLE, (int)x,(int)y);
			// Sender sender = new Sender();
			// sender.setip(ip);
			// sender.getxy((int) y, (int) x);
			// sender.execute("mouse");
		}
	}

	public boolean onKeyLongPress(int keyCode, KeyEvent event) {
		super.onKeyLongPress(keyCode, event);
		return false;
	}

	// volume and camera button

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		super.onKeyDown(keyCode, event);
		if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
			Toast.makeText(getApplicationContext(), "PPM", Toast.LENGTH_SHORT)
					.show();
			int signal = Helper.encodeSignal(N.Device.MOUSE,
					N.DeviceDataCounter.SINGLE, N.DeviceSignal.MOUSE_PPM);
			return true;
		} else if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
			Toast.makeText(getApplicationContext(), "LPM", Toast.LENGTH_SHORT)
					.show();
			int signal = Helper.encodeSignal(N.Device.MOUSE,
					N.DeviceDataCounter.SINGLE, N.DeviceSignal.MOUSE_LPM);
			return true;
		} else if (keyCode == KeyEvent.KEYCODE_CAMERA) {
			if (stop == 0) {
				Toast.makeText(getApplicationContext(), "STOP",
						Toast.LENGTH_SHORT).show();
				stop = 1;
			} else {
				Toast.makeText(getApplicationContext(), "START",
						Toast.LENGTH_SHORT).show();
				stop = 0;
			}
			return true;
		}
		return true;
	}

	public void onClick(View arg0) {
		int i;
		switch (arg0.getId()) {
		case R.id.besc:
			i = N.DeviceSignal.KEYBOARD_ESC;
			Toast.makeText(getApplicationContext(), Integer.toString(i),
					Toast.LENGTH_SHORT).show();
			break;
		case R.id.leftb:
			i = N.DeviceSignal.KEYBOARD_LEFT;
			
			break;
		case R.id.rightb:
			i = N.DeviceSignal.KEYBOARD_RIGHT;
			break;
		case R.id.upb:
			i = N.DeviceSignal.KEYBOARD_UP;
			break;
		case R.id.downb:
			i = N.DeviceSignal.KEYBOARD_DOWN;
			break;
		case R.id.enterb:
			 i = N.DeviceSignal.KEYBOARD_ENTER;
			break;
		case R.id.bshift:
			i = N.DeviceSignal.KEYBOARD_SHIFT;
			if (altflag == 0) {
				switch (shiftflag) {
				case 0:
					shiftflag = 1;
					// wyslij ze zostal nacisniety shift
					button7.setText("Q");
					button8.setText("W");
					button9.setText("E");
					button10.setText("R");
					button11.setText("T");
					button12.setText("Y");
					button13.setText("U");
					button14.setText("I");
					button15.setText("O");
					button16.setText("P");
					button17.setText("A");
					button18.setText("S");
					button19.setText("D");
					button20.setText("F");
					button21.setText("G");
					button22.setText("H");
					button23.setText("J");
					button24.setText("K");
					button25.setText("L");
					button26.setText("Z");
					button27.setText("X");
					button28.setText("C");
					button29.setText("V");
					button30.setText("B");
					button31.setText("N");
					button32.setText("M");
					break;
				case 1:
					shiftflag = 2;
					button7.setText("Q");
					button8.setText("W");
					button9.setText("E");
					button10.setText("R");
					button11.setText("T");
					button12.setText("Y");
					button13.setText("U");
					button14.setText("I");
					button15.setText("O");
					button16.setText("P");
					button17.setText("A");
					button18.setText("S");
					button19.setText("D");
					button20.setText("F");
					button21.setText("G");
					button22.setText("H");
					button23.setText("J");
					button24.setText("K");
					button25.setText("L");
					button26.setText("Z");
					button27.setText("X");
					button28.setText("C");
					button29.setText("V");
					button30.setText("B");
					button31.setText("N");
					button32.setText("M");
					break;
				case 2:
					// wyslij ze zostal odcisniety shift
					shiftflag = 0;
					button7.setText("q");
					button8.setText("w");
					button9.setText("e");
					button10.setText("r");
					button11.setText("t");
					button12.setText("y");
					button13.setText("u");
					button14.setText("i");
					button15.setText("o");
					button16.setText("p");
					button17.setText("a");
					button18.setText("s");
					button19.setText("d");
					button20.setText("f");
					button21.setText("g");
					button22.setText("h");
					button23.setText("j");
					button24.setText("k");
					button25.setText("l");
					button26.setText("z");
					button27.setText("x");
					button28.setText("c");
					button29.setText("v");
					button30.setText("b");
					button31.setText("n");
					button32.setText("m");
					break;
				}
			} else if (altflag == 1) {
				switch (shiftflag) {
				case 0:
					shiftflag = 1;
					// wyslij ze zostal nacisniety shift
					button7.setText("!");
					button8.setText("@");
					button9.setText("#");
					button10.setText("$");
					button11.setText("%");
					button12.setText("^");
					button13.setText("&");
					button14.setText("*");
					button15.setText("(");
					button16.setText(")");
					button17.setText("_");
					button18.setText("+");
					button19.setText("{");
					button20.setText("}");
					button21.setText("|");
					button22.setText("\\");
					button23.setText(":");
					button24.setText("\"");
					button25.setText("<");
					button26.setText(">");
					button27.setText("?");
					button28.setText("/");
					button29.setText("=");
					button30.setText("-");
					button31.setText("~");
					button32.setText("`");
					break;
				case 1:
					shiftflag = 2;
					button7.setText("1");
					button8.setText("2");
					button9.setText("3");
					button10.setText("4");
					button11.setText("5");
					button12.setText("6");
					button13.setText("7");
					button14.setText("8");
					button15.setText("9");
					button16.setText("0");
					button17.setText("ê");
					button18.setText("ó");
					button19.setText("¹");
					button20.setText("œ");
					button21.setText("³");
					button22.setText("¿");
					button23.setText("Ÿ");
					button24.setText("æ");
					button25.setText("ñ");
					button26.setText("[");
					button27.setText("]");
					button28.setText(";");
					button29.setText("'");
					button30.setText(",");
					button31.setText(".");
					button32.setText("?");
					break;
				case 2:
					// wyslij ze zostal odcisniety shift
					shiftflag = 0;
					button7.setText("q");
					button8.setText("w");
					button9.setText("e");
					button10.setText("r");
					button11.setText("t");
					button12.setText("y");
					button13.setText("u");
					button14.setText("i");
					button15.setText("o");
					button16.setText("p");
					button17.setText("a");
					button18.setText("s");
					button19.setText("d");
					button20.setText("f");
					button21.setText("g");
					button22.setText("h");
					button23.setText("j");
					button24.setText("k");
					button25.setText("l");
					button26.setText("z");
					button27.setText("x");
					button28.setText("c");
					button29.setText("v");
					button30.setText("b");
					button31.setText("n");
					button32.setText("m");
					break;
				}
			}
			break;

		case R.id.bspace:
			i = N.DeviceSignal.KEYBOARD_BACKSPACE;
			break;
		case R.id.bq:
			// MultiPlayApplication.add(N.dev_signal.keyboard);
			if (shiftflag == 1 && altflag != 1) {
				// nacisniecie "q"
				// odcisniecie shifta
				shiftflag = 0;
				smalkey key = new smalkey(button7, button8, button9, button10,
						button11, button12, button13, button14, button15,
						button16, button17, button18, button19, button20,
						button21, button22, button23, button24, button25,
						button26, button27, button28, button29, button30,
						button31, button33);
				 i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("q");
				shiftflag = 0;
			} else if (shiftflag == 2) {
				 i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("q");

			}

			break;
		case R.id.bw:
			// MultiPlayApplication.add(N.dev_signal.keyboard);
			if (shiftflag == 1 && altflag != 1) {
				// nacisniecie "q"
				// odcisniecie shifta
				shiftflag = 0;
				smalkey key = new smalkey(button7, button8, button9, button10,
						button11, button12, button13, button14, button15,
						button16, button17, button18, button19, button20,
						button21, button22, button23, button24, button25,
						button26, button27, button28, button29, button30,
						button31, button32);

				shiftflag = 0;
				 i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("w");
			} else if (shiftflag == 2) {
				 i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("w");

			}
			break;
		case R.id.be:
			int signal = Helper.encodeSignal(N.Device.KEYBOARD, N.DeviceDataCounter.SINGLE, N.DeviceSignal.KEYBOARD_KEY_TO_INT("e"));
			int[] ret = Helper.decodeSignal(signal);

			Toast.makeText(getApplicationContext(), Integer.toString(ret[2]),
					Toast.LENGTH_SHORT).show();
			// MultiPlayApplication.add(N.dev_signal.keyboard);
			if (shiftflag == 1 && altflag != 1) {
				// nacisniecie "q"
				// odcisniecie shifta
				shiftflag = 0;
				smalkey key = new smalkey(button7, button8, button9, button10,
						button11, button12, button13, button14, button15,
						button16, button17, button18, button19, button20,
						button21, button22, button23, button24, button25,
						button26, button27, button28, button29, button30,
						button31, button32);
				shiftflag = 0;
				 i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("e");
			} else if (shiftflag == 2) {
				 i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("e");
			}
			break;
		case R.id.br:
			// MultiPlayApplication.add(N.dev_signal.keyboard);
			if (shiftflag == 1 && altflag != 1) {
				// nacisniecie "q"
				// odcisniecie shifta
				shiftflag = 0;
				smalkey key = new smalkey(button7, button8, button9, button10,
						button11, button12, button13, button14, button15,
						button16, button17, button18, button19, button20,
						button21, button22, button23, button24, button25,
						button26, button27, button28, button29, button30,
						button31, button32);
				shiftflag = 0;
				 i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("r");
			} else if (shiftflag == 2) {
				 i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("r");
			}
			break;
		case R.id.bt:
			// MultiPlayApplication.add(N.dev_signal.keyboard);
			if (shiftflag == 1 && altflag != 1) {
				// nacisniecie "q"
				// odcisniecie shifta
				shiftflag = 0;
				smalkey key = new smalkey(button7, button8, button9, button10,
						button11, button12, button13, button14, button15,
						button16, button17, button18, button19, button20,
						button21, button22, button23, button24, button25,
						button26, button27, button28, button29, button30,
						button31, button32);
				shiftflag = 0;
				 i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("t");
			} else if (shiftflag == 2) {
				 i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("t");
			}
			break;
		case R.id.by:
			// MultiPlayApplication.add(N.dev_signal.keyboard);
			if (shiftflag == 1 && altflag != 1) {
				// nacisniecie "q"
				// odcisniecie shifta
				shiftflag = 0;
				smalkey key = new smalkey(button7, button8, button9, button10,
						button11, button12, button13, button14, button15,
						button16, button17, button18, button19, button20,
						button21, button22, button23, button24, button25,
						button26, button27, button28, button29, button30,
						button31, button32);
				 i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("y");
				shiftflag = 0;
			} else if (shiftflag == 2) {
				 i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("y");
			}
			break;
		case R.id.bu:
			// MultiPlayApplication.add(N.dev_signal.keyboard);
			if (shiftflag == 1 && altflag != 1) {
				// nacisniecie "q"
				// odcisniecie shifta
				shiftflag = 0;
				smalkey key = new smalkey(button7, button8, button9, button10,
						button11, button12, button13, button14, button15,
						button16, button17, button18, button19, button20,
						button21, button22, button23, button24, button25,
						button26, button27, button28, button29, button30,
						button31, button32);
				shiftflag = 0;
				 i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("u");

			} else if (shiftflag == 2) {
				 i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("u");

			}
			break;
		case R.id.bi:
			// MultiPlayApplication.add(N.dev_signal.keyboard);
			if (shiftflag == 1 && altflag != 1) {
				// nacisniecie "q"
				// odcisniecie shifta
				shiftflag = 0;
				smalkey key = new smalkey(button7, button8, button9, button10,
						button11, button12, button13, button14, button15,
						button16, button17, button18, button19, button20,
						button21, button22, button23, button24, button25,
						button26, button27, button28, button29, button30,
						button31, button32);
				 i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("i");

				shiftflag = 0;
			} else if (shiftflag == 2) {
				 i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("i");

			}
			break;
		case R.id.bo:
			// MultiPlayApplication.add(N.dev_signal.keyboard);
			if (shiftflag == 1 && altflag != 1) {
				// nacisniecie "q"
				// odcisniecie shifta
				shiftflag = 0;
				smalkey key = new smalkey(button7, button8, button9, button10,
						button11, button12, button13, button14, button15,
						button16, button17, button18, button19, button20,
						button21, button22, button23, button24, button25,
						button26, button27, button28, button29, button30,
						button31, button32);
				 i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("o");

				shiftflag = 0;
			} else if (shiftflag == 2) {
				 i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("o");
			}
			break;
		case R.id.bp:
			// MultiPlayApplication.add(N.dev_signal.keyboard);
			if (shiftflag == 1 && altflag != 1) {
				// nacisniecie "q"
				// odcisniecie shifta
				shiftflag = 0;
				smalkey key = new smalkey(button7, button8, button9, button10,
						button11, button12, button13, button14, button15,
						button16, button17, button18, button19, button20,
						button21, button22, button23, button24, button25,
						button26, button27, button28, button29, button30,
						button31, button32);
				shiftflag = 0;
				i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("p");

			} else if (shiftflag == 2) {
				 i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("p");
			}
			break;
		case R.id.ba:
			// MultiPlayApplication.add(N.dev_signal.keyboard);
			if (shiftflag == 1 && altflag != 1) {
				// nacisniecie "q"
				// odcisniecie shifta
				shiftflag = 0;
				smalkey key = new smalkey(button7, button8, button9, button10,
						button11, button12, button13, button14, button15,
						button16, button17, button18, button19, button20,
						button21, button22, button23, button24, button25,
						button26, button27, button28, button29, button30,
						button31, button32);
				shiftflag = 0;
				 i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("a");

			} else if (shiftflag == 2) {
				 i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("a");

			}
			break;
		case R.id.bs:
			// MultiPlayApplication.add(N.dev_signal.keyboard);
			if (shiftflag == 1 && altflag != 1) {
				// nacisniecie "q"
				// odcisniecie shifta
				shiftflag = 0;
				smalkey key = new smalkey(button7, button8, button9, button10,
						button11, button12, button13, button14, button15,
						button16, button17, button18, button19, button20,
						button21, button22, button23, button24, button25,
						button26, button27, button28, button29, button30,
						button31, button32);
				shiftflag = 0;
				 i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("s");

			} else if (shiftflag == 2) {
				 i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("s");

			}
			break;
		case R.id.bd:
			// MultiPlayApplication.add(N.dev_signal.keyboard);
			if (shiftflag == 1 && altflag != 1) {
				// nacisniecie "q"
				// odcisniecie shifta
				shiftflag = 0;
				smalkey key = new smalkey(button7, button8, button9, button10,
						button11, button12, button13, button14, button15,
						button16, button17, button18, button19, button20,
						button21, button22, button23, button24, button25,
						button26, button27, button28, button29, button30,
						button31, button32);
				shiftflag = 0;
			 i=N.DeviceSignal.KEYBOARD_KEY_TO_INT("d");

			} else if (shiftflag == 2) {
				 i=N.DeviceSignal.KEYBOARD_KEY_TO_INT("d");

			}
			break;
		case R.id.bf:
			// MultiPlayApplication.add(N.dev_signal.keyboard);
			if (shiftflag == 1 && altflag != 1) {
				// nacisniecie "q"
				// odcisniecie shifta
				shiftflag = 0;
				smalkey key = new smalkey(button7, button8, button9, button10,
						button11, button12, button13, button14, button15,
						button16, button17, button18, button19, button20,
						button21, button22, button23, button24, button25,
						button26, button27, button28, button29, button30,
						button31, button32);
				shiftflag = 0;
				i=N.DeviceSignal.KEYBOARD_KEY_TO_INT("f");

			} else if (shiftflag == 2) {
				i=N.DeviceSignal.KEYBOARD_KEY_TO_INT("f");
			}
			break;
		case R.id.bg:
			// MultiPlayApplication.add(N.dev_signal.keyboard);
			if (shiftflag == 1 && altflag != 1) {
				// nacisniecie "q"
				// odcisniecie shifta
				shiftflag = 0;
				smalkey key = new smalkey(button7, button8, button9, button10,
						button11, button12, button13, button14, button15,
						button16, button17, button18, button19, button20,
						button21, button22, button23, button24, button25,
						button26, button27, button28, button29, button30,
						button31, button32);
				shiftflag = 0;
				 i=N.DeviceSignal.KEYBOARD_KEY_TO_INT("g");

			} else if (shiftflag == 2) {
				 i=N.DeviceSignal.KEYBOARD_KEY_TO_INT("g");
			}
			break;
		case R.id.bh:
			// MultiPlayApplication.add(N.dev_signal.keyboard);
			if (shiftflag == 1 && altflag != 1) {
				// nacisniecie "q"
				// odcisniecie shifta
				shiftflag = 0;
				smalkey key = new smalkey(button7, button8, button9, button10,
						button11, button12, button13, button14, button15,
						button16, button17, button18, button19, button20,
						button21, button22, button23, button24, button25,
						button26, button27, button28, button29, button30,
						button31, button32);
				shiftflag = 0;
				 i=N.DeviceSignal.KEYBOARD_KEY_TO_INT("h");

			} else if (shiftflag == 2) {
				 i=N.DeviceSignal.KEYBOARD_KEY_TO_INT("h");
			}
			break;
		case R.id.bj:
			// MultiPlayApplication.add(N.dev_signal.keyboard);
			if (shiftflag == 1 && altflag != 1) {
				// nacisniecie "q"
				// odcisniecie shifta
				shiftflag = 0;
				smalkey key = new smalkey(button7, button8, button9, button10,
						button11, button12, button13, button14, button15,
						button16, button17, button18, button19, button20,
						button21, button22, button23, button24, button25,
						button26, button27, button28, button29, button30,
						button31, button32);
				shiftflag = 0;
				 i=N.DeviceSignal.KEYBOARD_KEY_TO_INT("j");

			} else if (shiftflag == 2) {
				 i=N.DeviceSignal.KEYBOARD_KEY_TO_INT("j");
			}
			break;
		case R.id.bk:
			// MultiPlayApplication.add(N.dev_signal.keyboard);
			if (shiftflag == 1 && altflag != 1) {
				// nacisniecie "q"
				// odcisniecie shifta
				shiftflag = 0;
				smalkey key = new smalkey(button7, button8, button9, button10,
						button11, button12, button13, button14, button15,
						button16, button17, button18, button19, button20,
						button21, button22, button23, button24, button25,
						button26, button27, button28, button29, button30,
						button31, button32);
				shiftflag = 0;
				 i=N.DeviceSignal.KEYBOARD_KEY_TO_INT("k");

			} else if (shiftflag == 2) {
				i=N.DeviceSignal.KEYBOARD_KEY_TO_INT("k");
			}
			break;
		case R.id.bl:
			// MultiPlayApplication.add(N.dev_signal.keyboard);
			if (shiftflag == 1 && altflag != 1) {
				// nacisniecie "q"
				// odcisniecie shifta
				shiftflag = 0;
				smalkey key = new smalkey(button7, button8, button9, button10,
						button11, button12, button13, button14, button15,
						button16, button17, button18, button19, button20,
						button21, button22, button23, button24, button25,
						button26, button27, button28, button29, button30,
						button31, button32);
				shiftflag = 0;
				 i=N.DeviceSignal.KEYBOARD_KEY_TO_INT("l");

			} else if (shiftflag == 2) {
				 i=N.DeviceSignal.KEYBOARD_KEY_TO_INT("l");
			}
			break;
		case R.id.bz:
			// MultiPlayApplication.add(N.dev_signal.keyboard);
			if (shiftflag == 1 && altflag != 1) {
				// nacisniecie "q"
				// odcisniecie shifta
				shiftflag = 0;
				smalkey key = new smalkey(button7, button8, button9, button10,
						button11, button12, button13, button14, button15,
						button16, button17, button18, button19, button20,
						button21, button22, button23, button24, button25,
						button26, button27, button28, button29, button30,
						button31, button32);
				shiftflag = 0;
				 i=N.DeviceSignal.KEYBOARD_KEY_TO_INT("z");

			} else if (shiftflag == 2) {
				 i=N.DeviceSignal.KEYBOARD_KEY_TO_INT("z");
			}
			break;
		case R.id.bx:
			// MultiPlayApplication.add(N.dev_signal.keyboard);
			if (shiftflag == 1 && altflag != 1) {
				// nacisniecie "q"
				// odcisniecie shifta
				shiftflag = 0;
				smalkey key = new smalkey(button7, button8, button9, button10,
						button11, button12, button13, button14, button15,
						button16, button17, button18, button19, button20,
						button21, button22, button23, button24, button25,
						button26, button27, button28, button29, button30,
						button31, button32);
				shiftflag = 0;
				 i=N.DeviceSignal.KEYBOARD_KEY_TO_INT("x");

			} else if (shiftflag == 2) {
				 i=N.DeviceSignal.KEYBOARD_KEY_TO_INT("x");
			}
			break;
		case R.id.bc:
			// MultiPlayApplication.add(N.dev_signal.keyboard);
			if (shiftflag == 1 && altflag != 1) {
				// nacisniecie "q"
				// odcisniecie shifta
				shiftflag = 0;
				smalkey key = new smalkey(button7, button8, button9, button10,
						button11, button12, button13, button14, button15,
						button16, button17, button18, button19, button20,
						button21, button22, button23, button24, button25,
						button26, button27, button28, button29, button30,
						button31, button32);
				shiftflag = 0;
				 i=N.DeviceSignal.KEYBOARD_KEY_TO_INT("c");

			} else if (shiftflag == 2) {
				 i=N.DeviceSignal.KEYBOARD_KEY_TO_INT("c");
			}
			break;
		case R.id.bv:
			// MultiPlayApplication.add(N.dev_signal.keyboard);
			if (shiftflag == 1 && altflag != 1) {
				// nacisniecie "q"
				// odcisniecie shifta
				shiftflag = 0;
				smalkey key = new smalkey(button7, button8, button9, button10,
						button11, button12, button13, button14, button15,
						button16, button17, button18, button19, button20,
						button21, button22, button23, button24, button25,
						button26, button27, button28, button29, button30,
						button31, button32);
				shiftflag = 0;
				 i=N.DeviceSignal.KEYBOARD_KEY_TO_INT("v");

			} else if (shiftflag == 2) {
				 i=N.DeviceSignal.KEYBOARD_KEY_TO_INT("v");
			}
			break;
		case R.id.bb:
			// MultiPlayApplication.add(N.dev_signal.keyboard);
			if (shiftflag == 1 && altflag != 1) {
				// nacisniecie "q"
				// odcisniecie shifta
				shiftflag = 0;
				smalkey key = new smalkey(button7, button8, button9, button10,
						button11, button12, button13, button14, button15,
						button16, button17, button18, button19, button20,
						button21, button22, button23, button24, button25,
						button26, button27, button28, button29, button30,
						button31, button32);
				shiftflag = 0;
				 i=N.DeviceSignal.KEYBOARD_KEY_TO_INT("b");

			} else if (shiftflag == 2) {
				 i=N.DeviceSignal.KEYBOARD_KEY_TO_INT("b");
			}
			break;
		case R.id.bn:
			// MultiPlayApplication.add(N.dev_signal.keyboard);
			if (shiftflag == 1 && altflag != 1) {
				// nacisniecie "q"
				// odcisniecie shifta
				shiftflag = 0;
				smalkey key = new smalkey(button7, button8, button9, button10,
						button11, button12, button13, button14, button15,
						button16, button17, button18, button19, button20,
						button21, button22, button23, button24, button25,
						button26, button27, button28, button29, button30,
						button31, button32);
				shiftflag = 0;
				 i=N.DeviceSignal.KEYBOARD_KEY_TO_INT("n");

			} else if (shiftflag == 2) {
				 i=N.DeviceSignal.KEYBOARD_KEY_TO_INT("n");
			}
			break;
		case R.id.bm:
			// MultiPlayApplication.add(N.dev_signal.keyboard);
			if (shiftflag == 1 && altflag != 1) {
				// nacisniecie "q"
				// odcisniecie shifta
				shiftflag = 0;
				smalkey key = new smalkey(button7, button8, button9, button10,
						button11, button12, button13, button14, button15,
						button16, button17, button18, button19, button20,
						button21, button22, button23, button24, button25,
						button26, button27, button28, button29, button30,
						button31, button32);
				shiftflag = 0;
				 i=N.DeviceSignal.KEYBOARD_KEY_TO_INT("m");

			} else if (shiftflag == 2) {
				 i=N.DeviceSignal.KEYBOARD_KEY_TO_INT("m");
			}
			break;
		case R.id.balt:
			// MultiPlayApplication.add(N.dev_signal.keyboard);
			if (shiftflag == 1 || shiftflag == 2) {
			} else {
				switch (altflag) {
				case 1:
					altflag = 0;
					// wyslij ze zostal nacisniety shift
					button7.setText("1");
					button8.setText("2");
					button9.setText("3");
					button10.setText("4");
					button11.setText("5");
					button12.setText("6");
					button13.setText("7");
					button14.setText("8");
					button15.setText("9");
					button16.setText("0");
					button17.setText("�");
					button18.setText("�");
					button19.setText("�");
					button20.setText("�");
					button21.setText("�");
					button26.setText("�");
					button27.setText("�");
					button28.setText("�");
					button29.setText("�");
					button26.setText("[");
					button27.setText("]");
					button28.setText(";");
					button29.setText("'");
					button30.setText(",");
					button31.setText(".");
					button32.setText("?");
					shiftflag = 0;
					break;
				case 0:
					altflag = 1;
					button7.setText("q");
					button8.setText("w");
					button9.setText("e");
					button10.setText("r");
					button11.setText("t");
					button12.setText("y");
					button13.setText("u");
					button14.setText("i");
					button15.setText("o");
					button16.setText("p");
					button17.setText("a");
					button18.setText("s");
					button19.setText("d");
					button20.setText("f");
					button21.setText("g");
					button22.setText("h");
					button23.setText("j");
					button24.setText("k");
					button25.setText("l");
					button26.setText("z");
					button27.setText("x");
					button28.setText("c");
					button29.setText("v");
					button30.setText("b");
					button31.setText("n");
					button32.setText("m");
					shiftflag = 0;
					break;
				}
			}
			break;
		}
	}

	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {
		// TODO Auto-generated method stub
		return false;
	}

}
