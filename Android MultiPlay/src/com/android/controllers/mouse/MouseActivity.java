package com.android.controllers.mouse;

import java.io.IOException;

import com.android.application.MultiPlayApplication;
import com.android.application.N;
import com.android.application.N.Helper;
import com.android.multiplay.R;
import com.android.multiplay.Sender;
import com.android.multiplay.R.id;
import com.android.multiplay.R.layout;
import com.android.multiplay.R.menu;

import android.os.Bundle;
import android.os.PowerManager;
import android.app.Activity;
import android.content.Context;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MouseActivity extends Activity {
	private String tx;
	private TextView txv;
	private int hotx = 245, hoty = 176;
	private int multi=1;		//mno¿nik do ustawieñ szybkoœci ruchu
	 private PowerManager.WakeLock wl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mouse);
		try {
			MultiPlayApplication.runThread(MultiPlayApplication.isConnectedTo());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

			txv = (TextView) super.findViewById(R.id.texty);
			PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
	        wl = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK, "DoNjfdhotDimScreen");
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
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

	public boolean onTouchEvent(MotionEvent event) {
		int maskedAction = event.getActionMasked();
		String x = Integer.toString((int)(((hotx - (int) event.getX()) * (-1))/multi*8));
		String y = Integer.toString((int)(((hoty - (int) event.getY()) * (-1))/multi*8));
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

	protected void onPause() {
        super.onPause();
        wl.release();

    }
	protected void onResume() {
        super.onResume();
        wl.acquire();

    }

}
