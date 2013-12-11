package com.android.controllers.mouse;

import com.android.multiplay.R;
import com.android.multiplay.Sender;
import com.android.multiplay.R.id;
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
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//myszka oparta na żyroskopie(beta).
public class Gyromouse extends Activity implements SensorEventListener {
	SensorManager sm;
	TextView tv;
	private String ip;
	Bundle bundle;
	private Button button, button1, button2, button3, button4, button5;

	int stop;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gyromouse);
		button = (Button) super.findViewById(R.id.stopbu);
		//bundle = super.getIntent().getExtras();
		//ip = bundle.getString("ip");
		tv = (TextView) findViewById(R.id.stopv);
		stop = 0;
		button1 = (Button) super.findViewById(R.id.leftbu); // przyciski
															// przykładowe do
															// klawiatury które
		button2 = (Button) super.findViewById(R.id.rightbu); // należy dodać w
																// przyszłości
		button3 = (Button) super.findViewById(R.id.upbu); // do wysyłania jest
															// ju prawie
															// zainplementowane
		button4 = (Button) super.findViewById(R.id.downbu); // wystarczy dodać
															// kilka if'ow
		button5 = (Button) super.findViewById(R.id.enterbu);
		sm = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);

		sm.registerListener(this,
				sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_NORMAL);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.gyromouse, menu);
		return true;
	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSensorChanged(SensorEvent arg0) {
		float x = arg0.values[0];
		float y = arg0.values[1];
		if (tv.getText().toString().equals("start")) {
		
			Sender sender = new Sender();
			sender.setip(ip);
			sender.getxy((int) y, (int) x);
			sender.execute("mouse");
		}

	}

	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.stopbu:
			if (tv.getText().toString().equals("stop")) {
				tv.setText("start");
			} else {
				tv.setText("stop");
			}
			break;
		case R.id.leftbu:
			Sender sender = new Sender();
			sender.setip(ip);
			sender.execute("keyboard", "left");
			break;
		case R.id.rightbu:
			Sender sender2 = new Sender();
			sender2.setip(ip);
			sender2.execute("keyboard", "right");
			break;
		case R.id.upbu:
			Sender sender3 = new Sender();
			sender3.setip(ip); // wywyla odpowiednie klawisze poprzez klase
								// Sender
			sender3.execute("keyboard", "up");
			break;
		case R.id.downbu:
			Sender sender4 = new Sender();
			sender4.setip(ip);
			sender4.execute("keyboard", "down");
			break;
		case R.id.enterbu:
			Sender sender5 = new Sender();
			sender5.setip(ip);
			sender5.execute("keyboard", "enter");
			break;
		}
	}

}
