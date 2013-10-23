package com.android.multiplay;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class FirstMenu extends Activity implements OnClickListener {
	Bundle bundle;
	private Button button1, button2, button3, button4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first_menu);
		bundle = super.getIntent().getExtras();
		button1 = (Button) super.findViewById(R.id.button1);
		button2 = (Button) super.findViewById(R.id.button2);
		button3 = (Button) super.findViewById(R.id.button3);
		button4 = (Button) super.findViewById(R.id.button4);
		button1.setOnClickListener(this);
		button2.setOnClickListener(this);
		button3.setOnClickListener(this);
		button4.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.first_menu, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.button1:
			Sender sender = new Sender();

			sender.getxy(-10, 0);
			sender.execute();
			break;
		case R.id.button2:
			Sender sender2 = new Sender();
			sender2.getxy(0, -10);
			sender2.execute();
			break;
		case R.id.button3:
			Sender sender3 = new Sender();
			sender3.getxy(10, 0);
			sender3.execute();
			break;
		case R.id.button4:
			Sender sender4 = new Sender();
			sender4.getxy(0, 10);
			sender4.execute();
			break;
		}
	}
}
