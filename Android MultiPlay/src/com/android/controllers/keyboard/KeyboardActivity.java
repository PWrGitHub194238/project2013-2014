package com.android.controllers.keyboard;

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

public class KeyboardActivity extends Activity {
	Bundle bundle;
	private String ip;
	private Button button1, button2, button3, button4, button5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_keyboard);
		bundle = super.getIntent().getExtras();
		ip = bundle.getString("ip");
		button1 = (Button) super.findViewById(R.id.left);		//przyciski przykładowe do klawiatury które 
		button2 = (Button) super.findViewById(R.id.right);		//należy dodać w przyszłości
		button3 = (Button) super.findViewById(R.id.up);			//do wysyłania jest ju prawie zainplementowane
		button4 = (Button) super.findViewById(R.id.down);		//wystarczy dodać kilka if'ow
		button5 = (Button) super.findViewById(R.id.enterb);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.keyboard, menu);
		return true;
	}

	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.left:
			Sender sender = new Sender();
			sender.setip(ip);
			sender.execute("keyboard", "left");
			break;
		case R.id.right:
			Sender sender2 = new Sender();
			sender2.setip(ip);
			sender2.execute("keyboard", "right");
			break;
		case R.id.up:
			Sender sender3 = new Sender();
			sender3.setip(ip);						//wywyla odpowiednie klawisze poprzez klase Sender
			sender3.execute("keyboard", "up");
			break;
		case R.id.down:
			Sender sender4 = new Sender();
			sender4.setip(ip);
			sender4.execute("keyboard", "down");
			break;
		case R.id.enterb:
			Sender sender5 = new Sender();
			sender5.setip(ip);
			sender5.execute("keyboard", "enter");
			break;
		}
	}
}
