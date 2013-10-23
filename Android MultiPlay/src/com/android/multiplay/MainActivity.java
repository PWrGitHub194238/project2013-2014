package com.android.multiplay;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	private EditText IP;
	private Button nextButton, search;
	private Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		context = super.getApplicationContext();
		search = (Button) super.findViewById(R.id.search);
		search.setOnClickListener(this);
		IP = (EditText) super.findViewById(R.id.IPeditText);
		nextButton = (Button) super.findViewById(R.id.imageButton1);
		nextButton.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.imageButton1:
			if (IP.getText().toString().equals("")) {
				Toast.makeText(context, "Brak IP ", Toast.LENGTH_LONG).show();
			} else {
				Intent intent = new Intent(context, FirstMenu.class);
				intent.putExtra("ip", IP.getText().toString());
				super.startActivity(intent);
			}
			break;
		case R.id.search:
			Intent intent = new Intent(context, FirstMenu.class);
			super.startActivity(intent);
			break;
		}
	}
}
