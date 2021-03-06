package com.android.controllers.mouse;

import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.application.MultiPlayApplication;
import com.android.application.N;
import com.android.application.N.Helper;
import com.android.multiplay.R;

/**The mouse using an accelerometer. Activity includes the mouse and the keyboard
 * @author Piotr Baczkiewicz
 * 
 */
public class GyromouseActivity extends Activity implements SensorEventListener,
		OnTouchListener {
	private int shiftflag = 0, altflag = 0;
	private SensorManager sm;
	private int multi = 3; // mno�nik do ustawie� szybko�ci ruchu
	private TextView tv;
	private Button button1, button2, button3, button4, button5, button6,
			button7, button8, button9, button10, button11, button12, button13,
			button14, button15, button16, button17, button18, button19,
			button20, button21, button22, button23, button24, button25,
			button26, button27, button28, button29, button30, button31,
			button32, button33, button34, button35, button36;
	int e, signal;
	private int stop = 0;
/**

 * @see android.app.Activity#onCreate(android.os.Bundle)
 * @param savedInstanceState
 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gyromouse);
		try {
			
			MultiPlayApplication.runThread();
			tv= (TextView) findViewById(R.id.textgyro);
			sm = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);
			sm.registerListener(this,
					sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
					SensorManager.SENSOR_DELAY_FASTEST);
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
			button34 = (Button) super.findViewById(R.id.bbackspace);
			button35=(Button) super.findViewById(R.id.bspace);
			button34.setOnTouchListener(new View.OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {
						signal = Helper.encodeSignal(N.Device.KEYBOARD,
								N.DeviceDataCounter.DOUBLE,
								N.DeviceSignal.KEYBOARD_BACKSPACE,
								N.DeviceSignal.PRESS);
						MultiPlayApplication.add(signal);
					} else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
						signal = Helper.encodeSignal(N.Device.KEYBOARD,
								N.DeviceDataCounter.DOUBLE,
								N.DeviceSignal.KEYBOARD_BACKSPACE,
								N.DeviceSignal.RELEASE);
						MultiPlayApplication.add(signal);
					}
					return true;
				}
			});

			button35.setOnTouchListener(new View.OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {
						 signal = Helper.encodeSignal(N.Device.KEYBOARD,
								N.DeviceDataCounter.DOUBLE,
								N.DeviceSignal.KEYBOARD_SPACE,
								N.DeviceSignal.PRESS);
						MultiPlayApplication.add(signal);
					} else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
						 signal = Helper.encodeSignal(N.Device.KEYBOARD,
								N.DeviceDataCounter.DOUBLE,
								N.DeviceSignal.KEYBOARD_SPACE,
								N.DeviceSignal.RELEASE);
						MultiPlayApplication.add(signal);
					}
					return true;
				}
			});

			button1.setOnTouchListener(new View.OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {
						 signal = Helper.encodeSignal(N.Device.KEYBOARD,
								N.DeviceDataCounter.DOUBLE,
								N.DeviceSignal.KEYBOARD_LEFT,
								N.DeviceSignal.PRESS);
						MultiPlayApplication.add(signal);
					} else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
						 signal = Helper.encodeSignal(N.Device.KEYBOARD,
								N.DeviceDataCounter.DOUBLE,
								N.DeviceSignal.KEYBOARD_LEFT,
								N.DeviceSignal.RELEASE);
						MultiPlayApplication.add(signal);
					}
					return true;
				}
			});
			button2.setOnTouchListener(new View.OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {
						 signal = Helper.encodeSignal(N.Device.KEYBOARD,
								N.DeviceDataCounter.DOUBLE,
								N.DeviceSignal.KEYBOARD_RIGHT,
								N.DeviceSignal.PRESS);
						MultiPlayApplication.add(signal);
					} else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
						 signal = Helper.encodeSignal(N.Device.KEYBOARD,
								N.DeviceDataCounter.DOUBLE,
								N.DeviceSignal.KEYBOARD_RIGHT,
								N.DeviceSignal.RELEASE);
						MultiPlayApplication.add(signal);
					}
					return true;
				}
			});
			button3.setOnTouchListener(new View.OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {
						 signal = Helper.encodeSignal(N.Device.KEYBOARD,
								N.DeviceDataCounter.DOUBLE,
								N.DeviceSignal.KEYBOARD_UP,
								N.DeviceSignal.PRESS);
						MultiPlayApplication.add(signal);
					} else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
						 signal = Helper.encodeSignal(N.Device.KEYBOARD,
								N.DeviceDataCounter.DOUBLE,
								N.DeviceSignal.KEYBOARD_UP,
								N.DeviceSignal.RELEASE);
						MultiPlayApplication.add(signal);
					}
					return true;
				}
			});
			button4.setOnTouchListener(new View.OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {
						 signal = Helper.encodeSignal(N.Device.KEYBOARD,
								N.DeviceDataCounter.DOUBLE,
								N.DeviceSignal.KEYBOARD_DOWN,
								N.DeviceSignal.PRESS);
						MultiPlayApplication.add(signal);
					} else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
						 signal = Helper.encodeSignal(N.Device.KEYBOARD,
								N.DeviceDataCounter.DOUBLE,
								N.DeviceSignal.KEYBOARD_DOWN,
								N.DeviceSignal.RELEASE);
						MultiPlayApplication.add(signal);
					}
					return true;
				}
			});
			button5.setOnTouchListener(new View.OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {
						 signal = Helper.encodeSignal(N.Device.KEYBOARD,
								N.DeviceDataCounter.DOUBLE,
								N.DeviceSignal.KEYBOARD_ENTER,
								N.DeviceSignal.PRESS);
						MultiPlayApplication.add(signal);
					} else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
						 signal = Helper.encodeSignal(N.Device.KEYBOARD,
								N.DeviceDataCounter.DOUBLE,
								N.DeviceSignal.KEYBOARD_ENTER,
								N.DeviceSignal.RELEASE);
						MultiPlayApplication.add(signal);
					}
					return true;
				}
			});
			button7.setOnTouchListener(new View.OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					int signal, i;
					if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {

						if (shiftflag == 1 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("Q");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);

							MultiPlayApplication.add(signal);
							
						} else if (shiftflag == 2 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("Q");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("q");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);

						} else if (shiftflag == 0 && altflag == 1) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("!");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 2) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("1");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						}

					} else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
						if (shiftflag == 1 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("Q");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							smalkey key = new smalkey(button7, button8,
									button9, button10, button11, button12,
									button13, button14, button15, button16,
									button17, button18, button19, button20,
									button21, button22, button23, button24,
									button25, button26, button27, button28,
									button29, button30, button31, button33);
							MultiPlayApplication.add(signal);
							shiftflag = 0;

							shiftflag = 0;
						} else if (shiftflag == 2 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("Q");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("q");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);

						} else if (shiftflag == 0 && altflag == 1) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("!");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 2) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("1");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						}
					}
					return true;
				}
			});
			button8.setOnTouchListener(new View.OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					int signal, i;
					if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {

						if (shiftflag == 1 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("W");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);

							MultiPlayApplication.add(signal);
							
						} else if (shiftflag == 2 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("W");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("w");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);

						} else if (shiftflag == 0 && altflag == 1) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("@");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 2) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("2");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						}

					} else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
						if (shiftflag == 1 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("W");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);

							MultiPlayApplication.add(signal);
							shiftflag = 0;
							smalkey key = new smalkey(button7, button8,
									button9, button10, button11, button12,
									button13, button14, button15, button16,
									button17, button18, button19, button20,
									button21, button22, button23, button24,
									button25, button26, button27, button28,
									button29, button30, button31, button33);
							shiftflag = 0;
						} else if (shiftflag == 2 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("W");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("w");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);

						} else if (shiftflag == 0 && altflag == 1) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("@");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 2) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("2");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						}
					}
					return true;
				}
			});
			button9.setOnTouchListener(new View.OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					int signal, i;
					if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {

						if (shiftflag == 1 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("E");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);

							MultiPlayApplication.add(signal);
							
						} else if (shiftflag == 2 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("E");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("e");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);

						} else if (shiftflag == 0 && altflag == 1) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("#");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 2) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("3");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						}

					} else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
						if (shiftflag == 1 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("E");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							shiftflag = 0;
							smalkey key = new smalkey(button7, button8,
									button9, button10, button11, button12,
									button13, button14, button15, button16,
									button17, button18, button19, button20,
									button21, button22, button23, button24,
									button25, button26, button27, button28,
									button29, button30, button31, button33);
							shiftflag = 0;
							MultiPlayApplication.add(signal);
							shiftflag = 0;

							shiftflag = 0;
						} else if (shiftflag == 2 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("E");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("e");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);

						} else if (shiftflag == 0 && altflag == 1) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("#");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 2) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("3");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						}
					}
					return true;
				}
			});
			button10.setOnTouchListener(new View.OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					int signal, i;
					if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {

						if (shiftflag == 1 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("R");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);

							MultiPlayApplication.add(signal);
							
						} else if (shiftflag == 2 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("R");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("r");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);

						} else if (shiftflag == 0 && altflag == 1) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("$");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 2) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("4");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						}

					} else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
						if (shiftflag == 1 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("R");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							shiftflag = 0;
							smalkey key = new smalkey(button7, button8,
									button9, button10, button11, button12,
									button13, button14, button15, button16,
									button17, button18, button19, button20,
									button21, button22, button23, button24,
									button25, button26, button27, button28,
									button29, button30, button31, button33);
							shiftflag = 0;
							MultiPlayApplication.add(signal);
							shiftflag = 0;

							shiftflag = 0;
						} else if (shiftflag == 2 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("R");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("r");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);

						} else if (shiftflag == 0 && altflag == 1) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("$");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 2) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("4");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						}
					}
					return true;
				}
			});
			button11.setOnTouchListener(new View.OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					int signal, i;
					if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {

						if (shiftflag == 1 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("T");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);

							MultiPlayApplication.add(signal);
							
						} else if (shiftflag == 2 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("T");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("t");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);

						} else if (shiftflag == 0 && altflag == 1) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("%");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 2) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("5");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						}

					} else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
						if (shiftflag == 1 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("T");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							shiftflag = 0;
							smalkey key = new smalkey(button7, button8,
									button9, button10, button11, button12,
									button13, button14, button15, button16,
									button17, button18, button19, button20,
									button21, button22, button23, button24,
									button25, button26, button27, button28,
									button29, button30, button31, button33);
							shiftflag = 0;
							MultiPlayApplication.add(signal);
							shiftflag = 0;

							shiftflag = 0;
						} else if (shiftflag == 2 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("T");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("t");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);

						} else if (shiftflag == 0 && altflag == 1) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("%");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 2) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("5");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						}
					}
					return true;
				}
			});
			button12.setOnTouchListener(new View.OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					int signal, i;
					if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {

						if (shiftflag == 1 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("Y");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);

							MultiPlayApplication.add(signal);
							
						} else if (shiftflag == 2 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("Y");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("y");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);

						} else if (shiftflag == 0 && altflag == 1) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("^");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 2) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("6");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						}

					} else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
						if (shiftflag == 1 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("Y");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);

							MultiPlayApplication.add(signal);
							shiftflag = 0;
							smalkey key = new smalkey(button7, button8,
									button9, button10, button11, button12,
									button13, button14, button15, button16,
									button17, button18, button19, button20,
									button21, button22, button23, button24,
									button25, button26, button27, button28,
									button29, button30, button31, button33);
							shiftflag = 0;
						} else if (shiftflag == 2 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("Y");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("y");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);

						} else if (shiftflag == 0 && altflag == 1) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("^");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 2) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("6");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						}
					}
					return true;
				}
			});
			button13.setOnTouchListener(new View.OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					int signal, i;
					if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {

						if (shiftflag == 1 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("U");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);

							MultiPlayApplication.add(signal);
							
						} else if (shiftflag == 2 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("U");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("u");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);

						} else if (shiftflag == 0 && altflag == 1) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("&");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 2) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("7");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						}

					} else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
						if (shiftflag == 1 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("U");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							shiftflag = 0;
							smalkey key = new smalkey(button7, button8,
									button9, button10, button11, button12,
									button13, button14, button15, button16,
									button17, button18, button19, button20,
									button21, button22, button23, button24,
									button25, button26, button27, button28,
									button29, button30, button31, button33);
							shiftflag = 0;
							MultiPlayApplication.add(signal);
							shiftflag = 0;

							shiftflag = 0;
						} else if (shiftflag == 2 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("U");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("u");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);

						} else if (shiftflag == 0 && altflag == 1) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("&");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 2) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("7");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						}
					}
					return true;
				}
			});
			button14.setOnTouchListener(new View.OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					int signal, i;
					if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {

						if (shiftflag == 1 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("I");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);

							MultiPlayApplication.add(signal);
							
						} else if (shiftflag == 2 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("I");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("i");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);

						} else if (shiftflag == 0 && altflag == 1) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("*");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 2) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("8");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						}

					} else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
						if (shiftflag == 1 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("I");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);

							MultiPlayApplication.add(signal);
							shiftflag = 0;
							smalkey key = new smalkey(button7, button8,
									button9, button10, button11, button12,
									button13, button14, button15, button16,
									button17, button18, button19, button20,
									button21, button22, button23, button24,
									button25, button26, button27, button28,
									button29, button30, button31, button33);
							shiftflag = 0;
						} else if (shiftflag == 2 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("I");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("i");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);

						} else if (shiftflag == 0 && altflag == 1) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("*");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 2) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("8");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						}
					}
					return true;
				}
			});
			button15.setOnTouchListener(new View.OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					int signal, i;
					if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {

						if (shiftflag == 1 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("O");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);

							MultiPlayApplication.add(signal);
							
						} else if (shiftflag == 2 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("O");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("o");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);

						} else if (shiftflag == 0 && altflag == 1) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("(");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 2) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("9");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						}

					} else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
						if (shiftflag == 1 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("O");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);

							MultiPlayApplication.add(signal);
							shiftflag = 0;
							smalkey key = new smalkey(button7, button8,
									button9, button10, button11, button12,
									button13, button14, button15, button16,
									button17, button18, button19, button20,
									button21, button22, button23, button24,
									button25, button26, button27, button28,
									button29, button30, button31, button33);
							shiftflag = 0;
						} else if (shiftflag == 2 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("O");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("o");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);

						} else if (shiftflag == 0 && altflag == 1) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("(");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 2) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("9");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						}
					}
					return true;
				}
			});
			button16.setOnTouchListener(new View.OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					int signal, i;
					if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {

						if (shiftflag == 1 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("P");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);

							MultiPlayApplication.add(signal);
							
						} else if (shiftflag == 2 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("P");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("p");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);

						} else if (shiftflag == 0 && altflag == 1) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT(")");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 2) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("0");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						}

					} else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
						if (shiftflag == 1 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("P");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);

							MultiPlayApplication.add(signal);
							shiftflag = 0;
							smalkey key = new smalkey(button7, button8,
									button9, button10, button11, button12,
									button13, button14, button15, button16,
									button17, button18, button19, button20,
									button21, button22, button23, button24,
									button25, button26, button27, button28,
									button29, button30, button31, button33);
							shiftflag = 0;
						} else if (shiftflag == 2 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("P");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("p");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);

						} else if (shiftflag == 0 && altflag == 1) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT(")");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 2) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("0");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						}
					}
					return true;
				}
			});
			button17.setOnTouchListener(new View.OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					int signal, i;
					if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {

						if (shiftflag == 1 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("A");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);

							MultiPlayApplication.add(signal);
							
						} else if (shiftflag == 2 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("A");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("a");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);

						} else if (shiftflag == 0 && altflag == 1) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("[");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 2) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("<");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						}

					} else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
						if (shiftflag == 1 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("A");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);

							MultiPlayApplication.add(signal);
							shiftflag = 0;
							smalkey key = new smalkey(button7, button8,
									button9, button10, button11, button12,
									button13, button14, button15, button16,
									button17, button18, button19, button20,
									button21, button22, button23, button24,
									button25, button26, button27, button28,
									button29, button30, button31, button33);
							shiftflag = 0;
						} else if (shiftflag == 2 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("A");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("a");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);

						} else if (shiftflag == 0 && altflag == 1) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("[");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 2) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("<");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						}
					}
					return true;
				}
			});
			button18.setOnTouchListener(new View.OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					int signal, i;
					if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {

						if (shiftflag == 1 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("S");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);

							MultiPlayApplication.add(signal);
							
						} else if (shiftflag == 2 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("S");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("s");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);

						} else if (shiftflag == 0 && altflag == 1) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("]");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 2) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT(">");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						}

					} else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
						if (shiftflag == 1 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("S");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);

							MultiPlayApplication.add(signal);
							shiftflag = 0;
							smalkey key = new smalkey(button7, button8,
									button9, button10, button11, button12,
									button13, button14, button15, button16,
									button17, button18, button19, button20,
									button21, button22, button23, button24,
									button25, button26, button27, button28,
									button29, button30, button31, button33);
							shiftflag = 0;
						} else if (shiftflag == 2 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("S");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("s");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);

						} else if (shiftflag == 0 && altflag == 1) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("]");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 2) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT(">");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						}
					}
					return true;
				}
			});
			button19.setOnTouchListener(new View.OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					int signal, i;
					if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {

						if (shiftflag == 1 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("D");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);

							MultiPlayApplication.add(signal);
							
						} else if (shiftflag == 2 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("D");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("d");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);

						} else if (shiftflag == 0 && altflag == 1) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT(";");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 2) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("|");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						}

					} else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
						if (shiftflag == 1 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("D");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);

							MultiPlayApplication.add(signal);
							shiftflag = 0;
							smalkey key = new smalkey(button7, button8,
									button9, button10, button11, button12,
									button13, button14, button15, button16,
									button17, button18, button19, button20,
									button21, button22, button23, button24,
									button25, button26, button27, button28,
									button29, button30, button31, button33);
							shiftflag = 0;
						} else if (shiftflag == 2 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("D");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("d");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);

						} else if (shiftflag == 0 && altflag == 1) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT(";");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 2) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("|");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						}
					}
					return true;
				}
			});
			button20.setOnTouchListener(new View.OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					int signal, i;
					if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {

						if (shiftflag == 1 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("F");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);

							MultiPlayApplication.add(signal);
						
						} else if (shiftflag == 2 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("F");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("f");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);

						} else if (shiftflag == 0 && altflag == 1) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT(":");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 2) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("~");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						}

					} else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
						if (shiftflag == 1 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("F");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);

							MultiPlayApplication.add(signal);
							shiftflag = 0;
							smalkey key = new smalkey(button7, button8,
									button9, button10, button11, button12,
									button13, button14, button15, button16,
									button17, button18, button19, button20,
									button21, button22, button23, button24,
									button25, button26, button27, button28,
									button29, button30, button31, button33);
							shiftflag = 0;
						} else if (shiftflag == 2 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("F");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("f");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);

						} else if (shiftflag == 0 && altflag == 1) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT(":");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 2) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("~");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						}
					}
					return true;
				}
			});
			button21.setOnTouchListener(new View.OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					int signal, i;
					if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {

						if (shiftflag == 1 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("G");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);

							MultiPlayApplication.add(signal);
							
						} else if (shiftflag == 2 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("G");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("g");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);

						} else if (shiftflag == 0 && altflag == 1) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("\"");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 2) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("`");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						}

					} else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
						if (shiftflag == 1 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("G");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);

							MultiPlayApplication.add(signal);
							shiftflag = 0;
							smalkey key = new smalkey(button7, button8,
									button9, button10, button11, button12,
									button13, button14, button15, button16,
									button17, button18, button19, button20,
									button21, button22, button23, button24,
									button25, button26, button27, button28,
									button29, button30, button31, button33);
							shiftflag = 0;
						} else if (shiftflag == 2 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("G");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("g");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);

						} else if (shiftflag == 0 && altflag == 1) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("\"");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 2) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("`");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						}
					}
					return true;
				}
			});
			button22.setOnTouchListener(new View.OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					int signal, i;
					if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {

						if (shiftflag == 1 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("H");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);

							MultiPlayApplication.add(signal);
						
						} else if (shiftflag == 2 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("H");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("h");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);

						} else if (shiftflag == 0 && altflag == 1) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT(",");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 2) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT(",");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						}

					} else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
						if (shiftflag == 1 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("H");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);

							MultiPlayApplication.add(signal);
							shiftflag = 0;
							smalkey key = new smalkey(button7, button8,
									button9, button10, button11, button12,
									button13, button14, button15, button16,
									button17, button18, button19, button20,
									button21, button22, button23, button24,
									button25, button26, button27, button28,
									button29, button30, button31, button33);
							shiftflag = 0;
						} else if (shiftflag == 2 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("H");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("h");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);

						} else if (shiftflag == 0 && altflag == 1) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT(",");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 2) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT(",");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						}
					}
					return true;
				}
			});
			button23.setOnTouchListener(new View.OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					int signal, i;
					if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {

						if (shiftflag == 1 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("J");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);

							MultiPlayApplication.add(signal);
							
						} else if (shiftflag == 2 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("J");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("j");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);

						} else if (shiftflag == 0 && altflag == 1) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT(".");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 2) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT(".");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						}

					} else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
						if (shiftflag == 1 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("J");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);

							MultiPlayApplication.add(signal);
							shiftflag = 0;
							smalkey key = new smalkey(button7, button8,
									button9, button10, button11, button12,
									button13, button14, button15, button16,
									button17, button18, button19, button20,
									button21, button22, button23, button24,
									button25, button26, button27, button28,
									button29, button30, button31, button33);
							shiftflag = 0;
						} else if (shiftflag == 2 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("J");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("j");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);

						} else if (shiftflag == 0 && altflag == 1) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT(".");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 2) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT(".");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						}
					}
					return true;
				}
			});

			button24.setOnTouchListener(new View.OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					int signal, i;
					if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {

						if (shiftflag == 1 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("K");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);

							MultiPlayApplication.add(signal);
							
						} else if (shiftflag == 2 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("K");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("k");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);

						} else if (shiftflag == 0 && altflag == 1) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("/");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 2) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("/");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						}

					} else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
						if (shiftflag == 1 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("K");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);

							MultiPlayApplication.add(signal);
							shiftflag = 0;
							smalkey key = new smalkey(button7, button8,
									button9, button10, button11, button12,
									button13, button14, button15, button16,
									button17, button18, button19, button20,
									button21, button22, button23, button24,
									button25, button26, button27, button28,
									button29, button30, button31, button33);
							shiftflag = 0;
						} else if (shiftflag == 2 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("K");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("k");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);

						} else if (shiftflag == 0 && altflag == 1) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("/");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 2) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("/");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						}
					}
					return true;
				}
			});
			button25.setOnTouchListener(new View.OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					int signal, i;
					if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {

						if (shiftflag == 1 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("L");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);

							MultiPlayApplication.add(signal);
							
						} else if (shiftflag == 2 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("L");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("l");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);

						} else if (shiftflag == 0 && altflag == 1) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("-");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 2) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("-");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						}

					} else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
						if (shiftflag == 1 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("L");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);

							MultiPlayApplication.add(signal);
							shiftflag = 0;
							smalkey key = new smalkey(button7, button8,
									button9, button10, button11, button12,
									button13, button14, button15, button16,
									button17, button18, button19, button20,
									button21, button22, button23, button24,
									button25, button26, button27, button28,
									button29, button30, button31, button33);
							shiftflag = 0;
						} else if (shiftflag == 2 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("L");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("l");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);

						} else if (shiftflag == 0 && altflag == 1) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("-");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 2) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("-");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						}
					}
					return true;
				}
			});
			button26.setOnTouchListener(new View.OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					int signal, i;
					if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {

						if (shiftflag == 1 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("Z");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);

							MultiPlayApplication.add(signal);
							
						} else if (shiftflag == 2 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("Z");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("z");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);

						} else if (shiftflag == 0 && altflag == 1) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("+");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 2) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("+");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						}

					} else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
						if (shiftflag == 1 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("Z");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);

							MultiPlayApplication.add(signal);
							shiftflag = 0;
							smalkey key = new smalkey(button7, button8,
									button9, button10, button11, button12,
									button13, button14, button15, button16,
									button17, button18, button19, button20,
									button21, button22, button23, button24,
									button25, button26, button27, button28,
									button29, button30, button31, button33);
							shiftflag = 0;
						} else if (shiftflag == 2 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("Z");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("z");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);

						} else if (shiftflag == 0 && altflag == 1) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("+");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 2) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("+");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						}
					}
					return true;
				}
			});
			button27.setOnTouchListener(new View.OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					int signal, i;
					if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {

						if (shiftflag == 1 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("X");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);

							MultiPlayApplication.add(signal);
							
						} else if (shiftflag == 2 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("X");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("x");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);

						} else if (shiftflag == 0 && altflag == 1) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("=");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 2) {
							i = N.DeviceSignal.KEYBOARD_HOME;
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						}

					} else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
						if (shiftflag == 1 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("X");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);

							MultiPlayApplication.add(signal);
							shiftflag = 0;
							smalkey key = new smalkey(button7, button8,
									button9, button10, button11, button12,
									button13, button14, button15, button16,
									button17, button18, button19, button20,
									button21, button22, button23, button24,
									button25, button26, button27, button28,
									button29, button30, button31, button33);
							shiftflag = 0;
						} else if (shiftflag == 2 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("X");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("x");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);

						} else if (shiftflag == 0 && altflag == 1) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("=");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 2) {
							i = N.DeviceSignal.KEYBOARD_HOME;
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						}
					}
					return true;
				}
			});
			button28.setOnTouchListener(new View.OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					int signal, i;
					if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {

						if (shiftflag == 1 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("C");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);

							MultiPlayApplication.add(signal);
							
						} else if (shiftflag == 2 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("C");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("c");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);

						} else if (shiftflag == 0 && altflag == 1) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("{");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 2) {
							i = N.DeviceSignal.KEYBOARD_END;
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						}

					} else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
						if (shiftflag == 1 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("C");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);

							MultiPlayApplication.add(signal);
							shiftflag = 0;
							smalkey key = new smalkey(button7, button8,
									button9, button10, button11, button12,
									button13, button14, button15, button16,
									button17, button18, button19, button20,
									button21, button22, button23, button24,
									button25, button26, button27, button28,
									button29, button30, button31, button33);
							shiftflag = 0;
						} else if (shiftflag == 2 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("C");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("c");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);

						} else if (shiftflag == 0 && altflag == 1) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("{");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 2) {
							i = N.DeviceSignal.KEYBOARD_END;
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						}
					}
					return true;
				}
			});
			button29.setOnTouchListener(new View.OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					int signal, i;
					if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {

						if (shiftflag == 1 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("V");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);

							MultiPlayApplication.add(signal);
							
						} else if (shiftflag == 2 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("V");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("v");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);

						} else if (shiftflag == 0 && altflag == 1) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("}");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 2) {
							i = N.DeviceSignal.KEYBOARD_DELETE;
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						}

					} else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
						if (shiftflag == 1 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("V");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);

							MultiPlayApplication.add(signal);
							shiftflag = 0;
							smalkey key = new smalkey(button7, button8,
									button9, button10, button11, button12,
									button13, button14, button15, button16,
									button17, button18, button19, button20,
									button21, button22, button23, button24,
									button25, button26, button27, button28,
									button29, button30, button31, button33);
							shiftflag = 0;
						} else if (shiftflag == 2 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("V");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("v");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);

						} else if (shiftflag == 0 && altflag == 1) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("}");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 2) {
							i = N.DeviceSignal.KEYBOARD_DELETE;
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						}
					}
					return true;
				}
			});
			button30.setOnTouchListener(new View.OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					int signal, i;
					if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {

						if (shiftflag == 1 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("B");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);

							MultiPlayApplication.add(signal);
							
						} else if (shiftflag == 2 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("B");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("b");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);

						} else if (shiftflag == 0 && altflag == 1) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("'");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 2) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("'");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						}

					} else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
						if (shiftflag == 1 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("B");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);

							MultiPlayApplication.add(signal);
							shiftflag = 0;
							smalkey key = new smalkey(button7, button8,
									button9, button10, button11, button12,
									button13, button14, button15, button16,
									button17, button18, button19, button20,
									button21, button22, button23, button24,
									button25, button26, button27, button28,
									button29, button30, button31, button33);
							shiftflag = 0;
						} else if (shiftflag == 2 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("B");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("b");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);

						} else if (shiftflag == 0 && altflag == 1) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("'");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 2) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("'");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						}
					}
					return true;
				}
			});
			button31.setOnTouchListener(new View.OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					int signal, i;
					if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {

						if (shiftflag == 1 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("N");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);

							MultiPlayApplication.add(signal);
							
						} else if (shiftflag == 2 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("N");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("n");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);

						} else if (shiftflag == 0 && altflag == 1) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("\\");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 2) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("\\");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						}

					} else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
						if (shiftflag == 1 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("N");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);

							MultiPlayApplication.add(signal);
							shiftflag = 0;
							smalkey key = new smalkey(button7, button8,
									button9, button10, button11, button12,
									button13, button14, button15, button16,
									button17, button18, button19, button20,
									button21, button22, button23, button24,
									button25, button26, button27, button28,
									button29, button30, button31, button33);
							shiftflag = 0;
						} else if (shiftflag == 2 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("n");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("n");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);

						} else if (shiftflag == 0 && altflag == 1) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("\\");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 2) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("\\");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						}
					}
					return true;
				}
			});
			button32.setOnTouchListener(new View.OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					int signal, i;
					if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {

						if (shiftflag == 1 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("M");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);

							MultiPlayApplication.add(signal);
							
						} else if (shiftflag == 2 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("M");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("m");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);

						} else if (shiftflag == 0 && altflag == 1) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("?");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 2) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("?");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.PRESS);
							MultiPlayApplication.add(signal);
						}

					} else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
						if (shiftflag == 1 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("M");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);

							MultiPlayApplication.add(signal);
							shiftflag = 0;
							smalkey key = new smalkey(button7, button8,
									button9, button10, button11, button12,
									button13, button14, button15, button16,
									button17, button18, button19, button20,
									button21, button22, button23, button24,
									button25, button26, button27, button28,
									button29, button30, button31, button33);
							shiftflag = 0;
						} else if (shiftflag == 2 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("M");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 0) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("m");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);

						} else if (shiftflag == 0 && altflag == 1) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("?");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						} else if (shiftflag == 0 && altflag == 2) {
							i = N.DeviceSignal.KEYBOARD_KEY_TO_INT("?");
							signal = Helper.encodeSignal(N.Device.KEYBOARD,
									N.DeviceDataCounter.DOUBLE, i,
									N.DeviceSignal.RELEASE);
							MultiPlayApplication.add(signal);
						}
					}
					return true;
				}
			});

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

	}
/**

 * @see android.hardware.SensorEventListener#onAccuracyChanged(android.hardware.Sensor, int)
 * @param arg0
 * @param arg1
 */
	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub

	}
/**
 * @see android.hardware.SensorEventListener#onSensorChanged(android.hardware.SensorEvent)
 * @param arg0
 */
	@Override
	public void onSensorChanged(SensorEvent arg0) {
		if (stop == 0) {
			MultiPlayApplication.add(Helper.encodeSignal(N.Device.MOUSE,N.DeviceDataCounter.DOUBLE,
					(int) (arg0.values[1] * multi), (int) (arg0.values[0] * multi)));
		}
	}
/**

 * @see android.app.Activity#onKeyLongPress(int, android.view.KeyEvent)
 * @param keyCode
 * @param event
 * @return false
 */
	public boolean onKeyLongPress(int keyCode, KeyEvent event) {
		super.onKeyLongPress(keyCode, event);
		return false;
	}

	/**
	 *  volume and camera button
	 * @see android.app.Activity#onKeyDown(int, android.view.KeyEvent)
	 * @param keyCode
	 * @param event
	 * @return true
	 */

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		super.onKeyDown(keyCode, event);
		if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
			Toast.makeText(getApplicationContext(), "PPM", Toast.LENGTH_SHORT)
					.show();
			int signal = Helper.encodeSignal(N.Device.MOUSE,
					N.DeviceDataCounter.SINGLE, N.DeviceSignal.MOUSE_PPM);
			MultiPlayApplication.add(signal);
			return true;
		} else if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
			Toast.makeText(getApplicationContext(), "LPM", Toast.LENGTH_SHORT)
					.show();
			int signal = Helper.encodeSignal(N.Device.MOUSE,
					N.DeviceDataCounter.SINGLE, N.DeviceSignal.MOUSE_LPM);
			MultiPlayApplication.add(signal);
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
/**
 * @see onClick
 * @param arg0
 */
	public void onClick(View arg0) {
		int i;
		int signal;
		switch (arg0.getId()) {

		case R.id.bshift:
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
			}

			break;

		case R.id.balt:
			// MultiPlayApplication.add(N.dev_signal.keyboard);
			if (shiftflag == 0) {
				switch (altflag) {
				case 0:
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
					button17.setText("[");
					button18.setText("]");
					button19.setText(";");
					button20.setText(":");
					button21.setText("\"");
					button22.setText(",");
					button23.setText(".");
					button24.setText("/");
					button25.setText("-");
					button26.setText("+");
					button27.setText("=");
					button28.setText("{");
					button29.setText("}");
					button30.setText("'");
					button31.setText("\\");
					button32.setText("?");
					altflag = 1;
					break;
				case 1:
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
					button17.setText("<");
					button18.setText(">");
					button19.setText("|");
					button20.setText("~");
					button21.setText("`");
					button22.setText(",");
					button23.setText(".");
					button24.setText("/");
					button25.setText("-");
					button26.setText("+");
					button27.setText("hom");
					button28.setText("end");
					button29.setText("del");
					button30.setText("'");
					button31.setText("\\");
					button32.setText("?");
					altflag = 2;
					break;

				case 2:
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
					altflag = 0;
					break;
				}
			}
			break;
		}
		
	}
/**
 
 * @see android.view.View.OnTouchListener#onTouch(android.view.View, android.view.MotionEvent)
 * @param arg0
 * @param arg1
 * @return true
 */
	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {
		// TODO Auto-generated method stub
		return false;
	}
/**
 * @see onPause
 */
	protected void onPause() {
		super.onPause();
		stop = 1;
	}
/**
 * @see onResume
 */
	protected void onResume() {
		super.onResume();
		stop = 0;
	}

}
