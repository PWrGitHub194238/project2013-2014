package com.android.controllers.steeringwheel;
//ewentualna kierownica jeśli sie uda zrobić sterowniki po stronie komputera

import com.android.multiplay.R;
import com.android.multiplay.R.layout;
import com.android.multiplay.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class SteeringwheelActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_steeringwheel);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.steeringwheel, menu);
		return true;
	}

}
