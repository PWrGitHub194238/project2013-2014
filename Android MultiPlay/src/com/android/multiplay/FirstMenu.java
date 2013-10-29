package com.android.multiplay;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class FirstMenu extends Activity {
	Bundle bundle;
	private Button button1, button2;
	private String ip;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first_menu);
		bundle = super.getIntent().getExtras();
		ip = bundle.getString("ip");
		button1 = (Button) super.findViewById(R.id.keyboardb);
		button2 = (Button) super.findViewById(R.id.mouseb);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.first_menu, menu);
		return true;
	}

	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.keyboardb:
			Intent intent = new Intent(this, KeyboardActivity.class);
			intent.putExtra("ip", ip);
			super.startActivity(intent);
			break;
		case R.id.mouseb:
			intent = new Intent(this, MouseActivity.class);
			intent.putExtra("ip", ip);
			super.startActivity(intent);
			break;

		}
	}
}
