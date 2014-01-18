package com.android.controllers.mouse;

import java.io.IOException;

import com.android.application.MultiPlayApplication;
import com.android.application.N;
import com.android.application.N.Helper;
import com.android.multiplay.R;
import com.android.multiplay.R.layout;
import com.android.multiplay.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.widget.TextView;
import android.widget.Toast;

public class TouchpadActivity extends Activity {
	private double oldx = 245.0, oldy = 175.0;
	private TextView txv;
	private int multi=1;		//mno¿nik do ustawieñ szybkoœci ruchu

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_touch_pad);
		txv = (TextView) super.findViewById(R.id.txe);
		try {
			MultiPlayApplication.runThread();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.touch_pad, menu);
		return true;
	}

	public boolean onTouchEvent(MotionEvent event) {
		double oldoldx = event.getX(), oldoldy = event.getY();

		int maskedAction = event.getActionMasked();
		switch (maskedAction) {
		case MotionEvent.ACTION_MOVE:
			if (oldoldx - oldx < (double) 0.0 && oldoldy - oldy < (double) 0.0) {
				txv.setText("X=-1, Y=-1");
				int signal = Helper.encodeSignal(N.Device.MOUSE,
						N.DeviceDataCounter.DOUBLE, -5*multi, -5*multi);
				MultiPlayApplication.add(signal);
			} else if (oldoldx - oldx > (double) 0.0
					&& oldoldy - oldy > (double) 0.0) {
				int signal = Helper.encodeSignal(N.Device.MOUSE,
						N.DeviceDataCounter.DOUBLE, 5*multi, 5*multi);
				MultiPlayApplication.add(signal);
				txv.setText("X=1, Y=1");
			} else if (oldoldx - oldx > (double) 0.0
					&& oldoldy - oldy == (double) 0.0) {
				int signal = Helper.encodeSignal(N.Device.MOUSE,
						N.DeviceDataCounter.DOUBLE, 5*multi, 0);
				MultiPlayApplication.add(signal);
				txv.setText("X=1, Y=0");
			} else if (oldoldx - oldx == (double) 0.0
					&& oldoldy - oldy > (double) 0.0) {
				int signal = Helper.encodeSignal(N.Device.MOUSE,
						N.DeviceDataCounter.DOUBLE, 0, 5*multi);
				MultiPlayApplication.add(signal);
				txv.setText("X=0, Y=1");
			} else if (oldoldx - oldx < (double) 0.0
					&& oldoldy - oldy > (double) 0.0) {
				int signal = Helper.encodeSignal(N.Device.MOUSE,
						N.DeviceDataCounter.DOUBLE, -5*multi, 5*multi);
				MultiPlayApplication.add(signal);
				txv.setText("X=-1, Y=1");
			} else if (oldoldx - oldx < (double) 0.0
					&& oldoldy - oldy == (double) 0.0) {
				int signal = Helper.encodeSignal(N.Device.MOUSE,
						N.DeviceDataCounter.DOUBLE, -5*multi, 0);
				MultiPlayApplication.add(signal);
				txv.setText("X=-1, Y=0");

			} else if (oldoldx - oldx > (double) 0.0
					&& oldoldy - oldy < (double) 0.0) {
				int signal = Helper.encodeSignal(N.Device.MOUSE,
						N.DeviceDataCounter.DOUBLE, 5*multi, -5*multi);
				MultiPlayApplication.add(signal);
				txv.setText("X=1, Y=-1");

			} else if (oldoldx - oldx == (double) 0.0
					&& oldoldy - oldy < (double) 0.0) {
				int signal = Helper.encodeSignal(N.Device.MOUSE,
						N.DeviceDataCounter.DOUBLE, 0, -5*multi);
				MultiPlayApplication.add(signal);
				txv.setText("X=0, Y=-1");
			}
			break;
		case MotionEvent.ACTION_UP:
			txv.setText("X=0, Y=0");
			break;
		}
		oldx = event.getX();
		oldy = event.getY();
		return true;
	}

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
		}
		return true;
	}
}