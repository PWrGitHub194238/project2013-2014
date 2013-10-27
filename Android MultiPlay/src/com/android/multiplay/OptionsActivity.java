package com.android.multiplay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class OptionsActivity extends Activity {

	private EditText IP;
	private Button nextButton, search;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_options);
		
		search = (Button) super.findViewById(R.id.search);
		IP = (EditText) super.findViewById(R.id.IPeditText);
		nextButton = (Button) super.findViewById(R.id.imageButton1);
	}
	
	
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.imageButton1:
			if (IP.getText().toString().equals("")) {
				Toast.makeText(this, "Brak IP ", Toast.LENGTH_LONG).show();
			} else {
				Intent intent = new Intent(this, FirstMenu.class);
				intent.putExtra("ip", IP.getText().toString());
				super.startActivity(intent);
			}
			break;
		case R.id.search:
			Intent intent = new Intent(this, FirstMenu.class);
			super.startActivity(intent);
			break;
		}
	}

}