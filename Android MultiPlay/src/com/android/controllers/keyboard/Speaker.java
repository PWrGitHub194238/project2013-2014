package com.android.controllers.keyboard;

import java.io.IOException;
import java.util.ArrayList;

import com.android.application.MultiPlayApplication;
import com.android.application.N;
import com.android.application.N.Helper;

import com.android.multiplay.R;
import com.android.multiplay.R.id;
import com.android.multiplay.R.layout;
import com.android.multiplay.R.menu;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.view.KeyEvent;

public class Speaker extends Activity {
	private ImageButton btnSpeak;
	protected static final int RESULT_SPEECH = 1;
	int Punctuation = 0, Commands = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_speaker);
		try {
			MultiPlayApplication.runThread();
			// txtText = (TextView) findViewById(R.id.text2);
			btnSpeak = (ImageButton) findViewById(R.id.speak);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.speaker, menu);
		return true;
	}

	public void onClick(View arg0) {
		Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

		intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "pl-PL");

		try {
			startActivityForResult(intent, RESULT_SPEECH);
			// txtText.setText("");
		} catch (ActivityNotFoundException a) {
			Toast t = Toast.makeText(getApplicationContext(),
					"Opps! Your device doesn't support Speech to Text",
					Toast.LENGTH_SHORT);
			t.show();
		}
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		int e;
		if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
			Toast.makeText(getApplicationContext(), "Punctuation",
					Toast.LENGTH_SHORT).show();
			
			if (Punctuation == 0 && Commands == 0) {
				Punctuation = 1;
				Commands = 0;
				e = N.DeviceSignal.SPEAKER_PUNCTUATION;
				int signal = Helper.encodeSignal(N.Device.SPEAKER,
						N.DeviceDataCounter.SINGLE, e);
				MultiPlayApplication.add(signal);
				//-----------------------------------record commands--------------------------
				Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
				intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "pl-PL");
				try {
					startActivityForResult(intent, RESULT_SPEECH);
					// txtText.setText("");
				} catch (ActivityNotFoundException a) {
					Toast t = Toast.makeText(getApplicationContext(),"Punctuation",
							Toast.LENGTH_SHORT);
					t.show();
				}
				
			} 
			return true;
		} else if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
			Toast.makeText(getApplicationContext(), "Commands",
					Toast.LENGTH_SHORT).show();
			if (Commands == 0 && Punctuation == 0) {
				Commands = 1;
				Punctuation = 0;
				e = N.DeviceSignal.SPEAKER_COMMANDS;
				int signal = Helper.encodeSignal(N.Device.SPEAKER,
						N.DeviceDataCounter.SINGLE, e);
				MultiPlayApplication.add(signal);
				
				//-----------------------------------record commands--------------------------
				Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
				intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "pl-PL");
				try {
					startActivityForResult(intent, RESULT_SPEECH);
					// txtText.setText("");
				} catch (ActivityNotFoundException a) {
					Toast t = Toast.makeText(getApplicationContext(),"Commands",
							Toast.LENGTH_SHORT);
					t.show();
				}
				
				
			} 
			return true;
		}
		return true;

	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		int e;
		switch (requestCode) {
		case RESULT_SPEECH:
			if (resultCode == RESULT_OK && null != data) {
				ArrayList<String> text = data
						.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
				// txtText.setText(text.get(0));
				if (Punctuation == 0 && Commands == 0) {
					int i = 0;
					while (i < text.get(0).length()) {
						Toast t = Toast.makeText(getApplicationContext(), text
								.get(0).subSequence(i, i + 1),
								Toast.LENGTH_SHORT);
						t.show();
						e = N.DeviceSignal.KEYBOARD_KEY_TO_INT(text.get(0)
								.subSequence(i, i + 1).toString());
						int signal = Helper.encodeSignal(N.Device.KEYBOARD,
								N.DeviceDataCounter.SINGLE, e);
						MultiPlayApplication.add(signal);
						i += 1;
					}
				}else if(Commands == 1){
					if(text.get(0).equals("uruchom")){
						Commands = 0;
						//-----------------------------------record application--------------------------
						Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
						intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "pl-PL");
						try {
							startActivityForResult(intent, RESULT_SPEECH);
							// txtText.setText("");
						} catch (ActivityNotFoundException a) {
						
						}
					}
				}else if(Punctuation == 1){
					if(text.get(0).equals("œrednik")){
						e = N.DeviceSignal.SPEAKER_SEMICOLON;
						int signal = Helper.encodeSignal(N.Device.SPEAKER,
								N.DeviceDataCounter.SINGLE, e);
						MultiPlayApplication.add(signal);
					}else if(text.get(0).equals("przecinek")){
						e = N.DeviceSignal.SPEAKER_COMMA;
						int signal = Helper.encodeSignal(N.Device.SPEAKER,
								N.DeviceDataCounter.SINGLE, e);
						MultiPlayApplication.add(signal);
					}else if(text.get(0).equals("kropka")){
						e = N.DeviceSignal.SPEAKER_POINT;
						int signal = Helper.encodeSignal(N.Device.SPEAKER,
								N.DeviceDataCounter.SINGLE, e);
						MultiPlayApplication.add(signal);
					}
					Punctuation = 0;
				}
			}
			break;
		}
	}
}
