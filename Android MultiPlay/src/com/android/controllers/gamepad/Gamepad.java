package com.android.controllers.gamepad;

import com.android.application.MultiPlayApplication;
import com.android.application.N;
import com.android.application.N.Helper;
import com.android.multiplay.R;
import com.android.multiplay.R.layout;
import com.android.multiplay.R.menu;

import android.os.Bundle;
import android.os.PowerManager;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class Gamepad extends Activity {
	private ImageButton bup, bdown, bleft, bright, bcircle, bsharp, btrinagle,
			bsquere, bstart;
	private ImageView left, right;
	private TextView lefttxt, righttxt;
	private double lefty_center;
	private double leftx_center, rightx_center, righty_center;
	 private PowerManager.WakeLock wl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gamepad);
		PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wl = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK, "DoNjfdhotDimScreen");
		bup = (ImageButton) super.findViewById(R.id.arrup);
		bdown = (ImageButton) super.findViewById(R.id.arrdown);
		bleft = (ImageButton) super.findViewById(R.id.arrleft);
		bright = (ImageButton) super.findViewById(R.id.arrright);
		bcircle = (ImageButton) super.findViewById(R.id.circle);
		bsharp = (ImageButton) super.findViewById(R.id.sharp);
		btrinagle = (ImageButton) super.findViewById(R.id.tringle);
		bstart = (ImageButton) super.findViewById(R.id.start);
		bsquere = (ImageButton) super.findViewById(R.id.square);
		left = (ImageView) super.findViewById(R.id.leftjoy);
		right = (ImageView) super.findViewById(R.id.rightjoy);
		int[] img_coordinates = new int[2];
		left.getLocationOnScreen(img_coordinates);
		leftx_center = (double) img_coordinates[0] + left.getWidth() / 2.0;
		lefty_center = (double) img_coordinates[1] + left.getHeight() / 2.0;
		right.getLocationOnScreen(img_coordinates);
		rightx_center = (double) img_coordinates[0] + right.getWidth() / 2.0;
		righty_center = (double) img_coordinates[1] + right.getHeight() / 2.0;
		lefttxt = (TextView) super.findViewById(R.id.lefttext);
		righttxt = (TextView) super.findViewById(R.id.righttxt);
		bstart.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {

					int signal = Helper.encodeSignal(N.Device.VJOYBUTTONS,
							N.DeviceDataCounter.DOUBLE,
							N.DeviceSignal.VJOY_START_PRESS,
							N.DeviceSignal.PRESS);
					MultiPlayApplication.add(signal);
				} else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
					int signal = Helper.encodeSignal(N.Device.VJOYBUTTONS,
							N.DeviceDataCounter.DOUBLE,
							N.DeviceSignal.VJOY_START_PRESS,
							N.DeviceSignal.RELEASE);
					MultiPlayApplication.add(signal);
				}
				return true;
			}
		});
		left.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {
					String x = Integer
							.toString((int) (((leftx_center - (int) event
									.getX()) * (-1)) / 3));
					String y = Integer
							.toString((int) (((lefty_center - (int) event
									.getY()) * (-1)) / 3));
					lefttxt.setText(x + " " + y);
					int signal = Helper.encodeSignal(N.Device.VJOYJOYSTICKLEFT,
							N.DeviceDataCounter.DOUBLE, Integer.parseInt(x),
							Integer.parseInt(y));
					MultiPlayApplication.add(signal);
				} else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
					String x = Integer.toString(0);
					String y = Integer.toString(0);
					lefttxt.setText(x + " " + y);
					int signal = Helper.encodeSignal(N.Device.VJOYJOYSTICKLEFT,
							N.DeviceDataCounter.DOUBLE, Integer.parseInt(x),
							Integer.parseInt(y));
					MultiPlayApplication.add(signal);
				}
				return true;
			}
		});
		right = (ImageView) super.findViewById(R.id.rightjoy);
		right.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {
					String x = Integer
							.toString((int) (((rightx_center - (int) event
									.getX()) * (-1)) / 3));
					String y = Integer
							.toString((int) (((righty_center - (int) event
									.getY()) * (-1)) / 3));
					righttxt.setText(x + " " + y);
					int signal = Helper.encodeSignal(
							N.Device.VJOYJOYSTICKRIGHT,
							N.DeviceDataCounter.DOUBLE, Integer.parseInt(x),
							Integer.parseInt(y));
					MultiPlayApplication.add(signal);
				} else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
					String x = Integer.toString(0);
					String y = Integer.toString(0);
					righttxt.setText(x + " " + y);
					int signal = Helper.encodeSignal(
							N.Device.VJOYJOYSTICKRIGHT,
							N.DeviceDataCounter.DOUBLE, Integer.parseInt(x),
							Integer.parseInt(y));
					MultiPlayApplication.add(signal);
				}
				return true;
			}
		});
		bup.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {

					int signal = Helper.encodeSignal(N.Device.VJOYBUTTONS,
							N.DeviceDataCounter.DOUBLE,
							N.DeviceSignal.KEYBOARD_UP, N.DeviceSignal.PRESS);
					MultiPlayApplication.add(signal);
				} else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {

					int signal = Helper.encodeSignal(N.Device.VJOYBUTTONS,
							N.DeviceDataCounter.DOUBLE,
							N.DeviceSignal.KEYBOARD_UP, N.DeviceSignal.RELEASE);
					MultiPlayApplication.add(signal);
				}
				return true;
			}
		});
		bdown.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {

					int signal = Helper.encodeSignal(N.Device.VJOYBUTTONS,
							N.DeviceDataCounter.DOUBLE,
							N.DeviceSignal.KEYBOARD_DOWN, N.DeviceSignal.PRESS);
					MultiPlayApplication.add(signal);
				} else if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {

					int signal = Helper.encodeSignal(N.Device.VJOYBUTTONS,
							N.DeviceDataCounter.DOUBLE,
							N.DeviceSignal.KEYBOARD_UP, N.DeviceSignal.RELEASE);
					MultiPlayApplication.add(signal);
				}
				return true;
			}
		});
		bleft.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {

					int signal = Helper.encodeSignal(N.Device.VJOYBUTTONS,
							N.DeviceDataCounter.DOUBLE,
							N.DeviceSignal.KEYBOARD_LEFT, N.DeviceSignal.PRESS);
					MultiPlayApplication.add(signal);
				} else if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {

					int signal = Helper.encodeSignal(N.Device.VJOYBUTTONS,
							N.DeviceDataCounter.DOUBLE,
							N.DeviceSignal.KEYBOARD_LEFT,
							N.DeviceSignal.RELEASE);
					MultiPlayApplication.add(signal);
				}
				return true;
			}
		});
		bright.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {

					int signal = Helper
							.encodeSignal(N.Device.VJOYBUTTONS,
									N.DeviceDataCounter.DOUBLE,
									N.DeviceSignal.KEYBOARD_RIGHT,
									N.DeviceSignal.PRESS);
					MultiPlayApplication.add(signal);
				} else if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {

					int signal = Helper.encodeSignal(N.Device.VJOYBUTTONS,
							N.DeviceDataCounter.DOUBLE,
							N.DeviceSignal.KEYBOARD_RIGHT,
							N.DeviceSignal.RELEASE);
					MultiPlayApplication.add(signal);
				}
				return true;
			}
		});
		bcircle.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {

					int signal = Helper.encodeSignal(N.Device.VJOYBUTTONS,
							N.DeviceDataCounter.DOUBLE,
							N.DeviceSignal.VJOY_CIRCLE_PRESS,
							N.DeviceSignal.PRESS);
					MultiPlayApplication.add(signal);
				} else if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {

					int signal = Helper.encodeSignal(N.Device.VJOYBUTTONS,
							N.DeviceDataCounter.DOUBLE,
							N.DeviceSignal.VJOY_CIRCLE_PRESS,
							N.DeviceSignal.RELEASE);
					MultiPlayApplication.add(signal);
				}
				return true;
			}
		});
		bsquere.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {

					int signal = Helper.encodeSignal(N.Device.VJOYBUTTONS,
							N.DeviceDataCounter.DOUBLE,
							N.DeviceSignal.VJOY_SQUARE_PRESS,
							N.DeviceSignal.PRESS);
					MultiPlayApplication.add(signal);
				} else if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {

					int signal = Helper.encodeSignal(N.Device.VJOYBUTTONS,
							N.DeviceDataCounter.DOUBLE,
							N.DeviceSignal.VJOY_SQUARE_PRESS,
							N.DeviceSignal.RELEASE);
					MultiPlayApplication.add(signal);
				}
				return true;
			}
		});

		bsharp.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {

					int signal = Helper.encodeSignal(N.Device.VJOYBUTTONS,
							N.DeviceDataCounter.DOUBLE,
							N.DeviceSignal.VJOY_SHARP_PRESS,
							N.DeviceSignal.PRESS);
					MultiPlayApplication.add(signal);
				} else if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {

					int signal = Helper.encodeSignal(N.Device.VJOYBUTTONS,
							N.DeviceDataCounter.DOUBLE,
							N.DeviceSignal.VJOY_SHARP_PRESS,
							N.DeviceSignal.RELEASE);
					MultiPlayApplication.add(signal);
				}
				return true;
			}
		});
		btrinagle.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {

					int signal = Helper.encodeSignal(N.Device.VJOYBUTTONS,
							N.DeviceDataCounter.DOUBLE,
							N.DeviceSignal.VJOY_TRIANGLE_PRESS,
							N.DeviceSignal.PRESS);
					MultiPlayApplication.add(signal);
				} else if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {

					int signal = Helper.encodeSignal(N.Device.VJOYBUTTONS,
							N.DeviceDataCounter.DOUBLE,
							N.DeviceSignal.VJOY_TRIANGLE_PRESS,
							N.DeviceSignal.RELEASE);
					MultiPlayApplication.add(signal);
				}
				return true;
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.gamepad, menu);
		return true;
	}
	protected void onPause() {
        super.onPause();
        wl.release();

    }
	protected void onResume() {
        super.onResume();
        wl.acquire();

    }

}
