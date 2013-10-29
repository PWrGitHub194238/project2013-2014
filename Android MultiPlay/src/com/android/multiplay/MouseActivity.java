package com.android.multiplay;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

public class MouseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mouse);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mouse, menu);
		return true;
	}

	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.button1:
			Sender sender = new Sender();
			sender.setip(ip);
			sender.getxy(-10, 0);
			sender.execute();
			break;
		case R.id.mouseb:
			Sender sender2 = new Sender();
			sender2.setip(ip);
			sender2.getxy(0, -10);
			sender2.execute();
			break;
		case R.id.button3:
			Sender sender3 = new Sender();
			sender3.setip(ip);
			sender3.getxy(10, 0);
			sender3.execute();
			break;
		case R.id.keyboardib:
			Sender sender4 = new Sender();
			sender4.setip(ip);
			sender4.getxy(0, 10);
			sender4.execute();
			break;
		}
	}

}
