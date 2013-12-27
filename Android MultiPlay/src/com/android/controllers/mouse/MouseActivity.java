package com.android.controllers.mouse;

import com.android.application.N;
import com.android.application.N.Helper;
import com.android.multiplay.R;
import com.android.multiplay.Sender;
import com.android.multiplay.R.id;
import com.android.multiplay.R.layout;
import com.android.multiplay.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MouseActivity extends Activity {
	Bundle bundle;
	private String tx;
	TextView txv;
	int hotx = 245, hoty = 176;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mouse);
		// bundle = super.getIntent().getExtras();
		// ip = bundle.getString("ip");
		txv = (TextView) super.findViewById(R.id.texty);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mouse, menu);
		return true;
	}

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

		}
		return true;
	}
	public boolean onTouchEvent(MotionEvent event) {
		int maskedAction = event.getActionMasked();
		String x = Integer.toString((hotx - (int) event.getX()) * (-1));
		String y = Integer.toString((hoty - (int) event.getY()) * (-1));
		switch (maskedAction) {
		//case MotionEvent.ACTION_DOWN:
		//	txv.setText("X: " + x + "Y: " + y);
		//	break;
		case MotionEvent.ACTION_MOVE:
			txv.setText("X: " + x + "Y: " + y);
			int signal = Helper.encodeSignal(N.Device.MOUSE, N.DeviceDataCounter.DOUBLE, Integer.parseInt(x),Integer.parseInt(y));
			break;
		case MotionEvent.ACTION_UP:

			txv.setText("X: 0" + "Y: 0");
			break;
		}

		return true;
		// RETURN SUPER.ONTOUCHEVENT(EVENT);
	}

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
