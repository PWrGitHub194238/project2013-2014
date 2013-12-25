package com.android.multiplay;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class Your extends Activity implements SensorEventListener,
		OnTouchListener {
	private static final int GYROMOUSE = Menu.FIRST;
	private static final int KEYBOARD = Menu.FIRST + 1;
	private static final int MOUSE = Menu.FIRST + 2;
	int hotx = 245, hoty = 176, flagmouse = 0, gyroflag = 0;
	TextView txv;
	private SensorManager sm;
	ScrollView sv;
	LinearLayout ll;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_your);
		sv = new ScrollView(this);

		ll = new LinearLayout(this);
		sv.addView(ll);

		txv = (TextView) super.findViewById(R.id.texty);
		sm = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);

		sm.registerListener(this,
				sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, GYROMOUSE, 0, "GYROMOUSE");
		menu.add(0, KEYBOARD, 0, "KEYBOARD");
		menu.add(0, MOUSE, 0, "MOUSE");
		return true;
	}

	/* Handles item selections */
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case KEYBOARD:
			newKey();
			return true;
		case GYROMOUSE:
			GyroMouse();
			return true;
		case MOUSE:
			Mouse();
			return true;
		}

		return false;
	}

	public void newKey() {
		Button b = new Button(this);
		b.setText("Hurra");
		ll.addView(b);
		this.setContentView(sv);

	}

	public void Mouse() {
		if (flagmouse == 0 && gyroflag == 0) {
			flagmouse = 1;
		} else
			flagmouse = 0;
	}

	public void GyroMouse() {
		if (gyroflag == 0 && flagmouse == 0) {
			gyroflag = 1;
		} else
			gyroflag = 0;

	}

	public boolean onTouchEvent(MotionEvent event) {
		if (flagmouse == 1) {
			int maskedAction = event.getActionMasked();
			String x = Integer.toString((hotx - (int) event.getX()) * (-1));
			String y = Integer.toString((hoty - (int) event.getY()) * (-1));
			switch (maskedAction) {
			// case MotionEvent.ACTION_DOWN:
			// txv.setText("X: " + x + "Y: " + y);
			// break;
			case MotionEvent.ACTION_MOVE:
				txv.setText("X: " + x + "Y: " + y);
				break;
			case MotionEvent.ACTION_UP:

				txv.setText("X: 0" + "Y: 0");
				break;
			}
		}
		return true;
		// RETURN SUPER.ONTOUCHEVENT(EVENT);
	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSensorChanged(SensorEvent arg0) {
		if (gyroflag == 1) {
			float x = arg0.values[0];
			float y = arg0.values[1];
			// if (stop == 0) {
			txv.setText(Integer.toString((int) x) + " "
					+ Integer.toString((int) y));
			// Sender sender = new Sender();
			// sender.setip(ip);
			// sender.getxy((int) y, (int) x);
			// sender.execute("mouse");
		}

	}

	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {
		// TODO Auto-generated method stub
		return false;
	}

}
