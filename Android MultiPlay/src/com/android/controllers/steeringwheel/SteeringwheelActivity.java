package com.android.controllers.steeringwheel;

//ewentualna kierownica jeśli sie uda zrobić sterowniki po stronie komputera

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
import android.widget.TextView;

public class SteeringwheelActivity extends Activity implements
		SensorEventListener {
	private SensorManager sm;
	private TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_steeringwheel);
		// bundle = super.getIntent().getExtras();
		// ip = bundle.getString("ip");
		tv = (TextView) findViewById(R.id.stopv);
		sm = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);
		sm.registerListener(this,
				sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_NORMAL);
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
	}
}
