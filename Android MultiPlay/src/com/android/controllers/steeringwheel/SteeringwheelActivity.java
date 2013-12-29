package com.android.controllers.steeringwheel;

//ewentualna kierownica jeśli sie uda zrobić sterowniki po stronie komputera

import java.io.IOException;

import com.android.application.MultiPlayApplication;
import com.android.application.N;
import com.android.application.N.Helper;
import com.android.multiplay.R;
import com.android.multiplay.R.layout;
import com.android.multiplay.R.menu;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.TextView;

public class SteeringwheelActivity extends Activity implements
		SensorEventListener {
	private SensorManager sm;
	private TextView tv;
	int up = 0,down=0;
	private Button b1, b2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_steeringwheel);
		try {
			MultiPlayApplication.runThread();
			tv = (TextView) findViewById(R.id.stopv);
			sm = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);
			b1 = (Button) super.findViewById(R.id.brea);
			b2 = (Button) super.findViewById(R.id.gaz);
			b1.setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					int signal;
					if (event.getAction() == MotionEvent.ACTION_DOWN) {
						up = 1;
					}
					if (event.getAction() == MotionEvent.ACTION_UP) {
						up = 0;
					}
					return true;
				}
			});
			b2.setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					int signal;
					if (event.getAction() == MotionEvent.ACTION_DOWN) {
						down = 1;
					}
					if (event.getAction() == MotionEvent.ACTION_UP) {
						down = 0;
					}
					return true;
				}
			});
			sm.registerListener(this,
					sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
					SensorManager.SENSOR_DELAY_NORMAL);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.steeringwheel, menu);
		return true;
	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSensorChanged(SensorEvent arg0) {
		float y = arg0.values[1];
		tv.setText(Integer.toString((int) y));
		if(up==0 && down==0){
			int signal = Helper.encodeSignal(N.Device.WHEEL,
					N.DeviceDataCounter.DOUBLE, (int) y,0);
			MultiPlayApplication.add(signal);
		}
		else if (up==1 && down==0){
			int signal = Helper.encodeSignal(N.Device.WHEEL,
					N.DeviceDataCounter.DOUBLE, (int) y,N.DeviceSignal.KEYBOARD_UP);
			MultiPlayApplication.add(signal);
		}else if (up==0 && down==1){
			int signal = Helper.encodeSignal(N.Device.WHEEL,
					N.DeviceDataCounter.DOUBLE, (int) y,N.DeviceSignal.KEYBOARD_SPACE);
			MultiPlayApplication.add(signal);
		}
	}

}
