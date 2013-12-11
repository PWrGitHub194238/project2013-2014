package com.android.controllers.mouse;

import com.android.multiplay.R;
import com.android.multiplay.Sender;
import com.android.multiplay.R.id;
import com.android.multiplay.R.layout;
import com.android.multiplay.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MouseActivity extends Activity {
	Bundle bundle;
	private String ip;
	private Button button1, button2, button3, button4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mouse);
		//bundle = super.getIntent().getExtras();
		//ip = bundle.getString("ip");
		button1 = (Button) super.findViewById(R.id.left);
		button2 = (Button) super.findViewById(R.id.right);
		button3 = (Button) super.findViewById(R.id.up);
		button4 = (Button) super.findViewById(R.id.down);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mouse, menu);
		return true;
	}

	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.left:
			Sender sender = new Sender();
			sender.setip(ip);
			sender.getxy(-10, 0);
			sender.execute("mouse");
			break;
		case R.id.right:
			Sender sender2 = new Sender();
			sender2.setip(ip);
			sender2.getxy(0, -10);
			sender2.execute("mouse");
			break;
		case R.id.up:
			Sender sender3 = new Sender();
			sender3.setip(ip);
			sender3.getxy(10, 0);
			sender3.execute("mouse");
			break;
		case R.id.down:
			Sender sender4 = new Sender();
			sender4.setip(ip);
			sender4.getxy(0, 10);
			sender4.execute("mouse");
			break;
		}
	}

}
