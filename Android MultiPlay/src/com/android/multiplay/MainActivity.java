package com.android.multiplay;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	Button button;
	Context context;
	EditText IP, Port;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		context = getApplicationContext();
		button = (Button) findViewById(R.id.buttonactivity1);
		IP = (EditText) findViewById(R.id.IPeditText);
		Port = (EditText) findViewById(R.id.PorteditText);
		button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				//Sprawdzanie czy pola są puste
				if ((IP.getText().toString().matches(""))
						|| (Port.getText().toString().matches(""))) {
					Toast.makeText(
							context,
							"You must enter the IP and Port for your computer !",
							Toast.LENGTH_LONG).show();
				} else {
					Toast.makeText(context, "Life is beautiful",
							Toast.LENGTH_LONG).show();
				}
				
				/*miejsce na laczenie sie z serwerem na komputerze i wywoływanie nowego activity, niedlugo dopisze. */
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
