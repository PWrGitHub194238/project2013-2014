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
public class Gyromouse extends Activity  implements SensorEventListener {
	 SensorManager sm;
	 TextView tv; 
		private String ip;
		Bundle bundle;
		private Button button;
		int stop;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gyromouse);
		button = (Button) super.findViewById(R.id.stop);
		bundle = super.getIntent().getExtras();
		ip = bundle.getString("ip");
        tv=(TextView)findViewById(R.id.pochyl); 
        stop=0;
        sm=(SensorManager)this.getSystemService(Context.SENSOR_SERVICE); 

        sm.registerListener(this,sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
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
		 float x=arg0.values[0]; 
	        float y=arg0.values[1]; 
	       if(stop==0){
	        tv.setText("X: "+x+" Y: "+y);
	        Sender sender = new Sender();
			sender.setip(ip);
			sender.getxy((int)y, (int)x);
			sender.execute("mouse");
	       }
	      

	}
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.stop:
			if(stop==0){
				stop=1;
			}
			else{
				stop=0;
			}
			break;
		}
	}

}
