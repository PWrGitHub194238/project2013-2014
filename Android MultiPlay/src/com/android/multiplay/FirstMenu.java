package com.android.multiplay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.android.controllers.keyboard.KeyboardActivity;
import com.android.controllers.mouse.Gyromouse;
import com.android.controllers.mouse.MouseActivity;

public class FirstMenu extends Activity {
	Bundle bundle;
	private Button button1, button2,button3;
	private String ip;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first_menu);
		//bundle = super.getIntent().getExtras();
		//ip = bundle.getString("ip");
		button1 = (Button) super.findViewById(R.id.keyboardb);
		button2 = (Button) super.findViewById(R.id.mouseb);
		button3 = (Button) super.findViewById(R.id.gyromouse);
		ip="192.168.1.100";
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
		case R.id.gyromouse:
			intent = new Intent(this, Gyromouse.class);
			intent.putExtra("ip", ip);
			super.startActivity(intent);
		}
	}
}
