package com.android.controllers.mouse;

import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.application.MultiPlayApplication;
import com.android.application.N;
import com.android.application.N.Helper;
import com.android.multiplay.R;
/**
 * Activity simulate a trackpoint
 * @author Piotr B�czkiewicz
 *
 */
public class MouseActivity extends Activity {
	private String tx;
	private TextView txv;
	private int hotx = 245, hoty = 176;
	private int multi=1;		//mno�nik do ustawie� szybko�ci ruchu
/**
 * @param savedInstanceState
 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mouse);
		try {
			MultiPlayApplication.runThread();
			txv = (TextView) super.findViewById(R.id.texty);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

			
		
	
	}

/**
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
		}
		return true;
	}
/**
 * @param event
 * @return true
 */
	public boolean onTouchEvent(MotionEvent event) {
		int maskedAction = event.getActionMasked();
		String x = Integer.toString((int)(((hotx - (int) event.getX()) * (-1))/multi*10));
		String y = Integer.toString((int)(((hoty - (int) event.getY()) * (-1))/multi*10));
		switch (maskedAction) {
		// case MotionEvent.ACTION_DOWN:
		// txv.setText("X: " + x + "Y: " + y);
		// break;
		case MotionEvent.ACTION_MOVE:
			txv.setText("X: " + x + "Y: " + y);
			int signal = Helper.encodeSignal(N.Device.MOUSE,
					N.DeviceDataCounter.DOUBLE, Integer.parseInt(x),
					Integer.parseInt(y));
			MultiPlayApplication.add(signal);
			break;
		case MotionEvent.ACTION_UP:

			txv.setText("X: 0" + "Y: 0");
			break;
		}

		return true;
		// RETURN SUPER.ONTOUCHEVENT(EVENT);
	}
/**
 * 
 * @param arg0
 */
	public void onClick(View arg0) {
		/*
		 * switch (arg0.getId()) { case R.id.left: Sender sender = new Sender();
		 * sender.setip(ip); sender.getxy(-10, 0); sender.execute("mouse");
		 * break; case R.id.right: Sender sender2 = new Sender();
		 * sender2.setip(ip); sender2.getxy(0, -10); sender2.execute("mouse");
		 * break; case R.id.up: Sender sender3 = new Sender();
		 * sender3.setip(ip); sender3.getxy(10, 0); sender3.execute("mouse");
		 * break; case R.id.down: Sender sender4 = new Sender();
		 * sender4.setip(ip); sender4.getxy(0, 10); sender4.execute("mouse");
		 * break; }
		 */
	}

}
