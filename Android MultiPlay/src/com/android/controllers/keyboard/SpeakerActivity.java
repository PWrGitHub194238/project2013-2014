package com.android.controllers.keyboard;

import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.application.MultiPlayApplication;
import com.android.application.N;
import com.android.application.N.Helper;
import com.android.multiplay.R;

/**
 * Class of speech to text processing
 * 
 * @author Piotr Baczkiewicz
 * 
 */
public class SpeakerActivity extends Activity {
	private ImageButton btnSpeak;
	protected static final int RESULT_SPEECH = 1;
	private int Punctuation = 0, Commands = 0;

	/**
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 * @param savedInstanceState
	 */
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

	/**
	 * @see onClick
	 * @param arg0
	 */
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

	/**
	 * @see android.app.Activity#onKeyDown(int, android.view.KeyEvent)
	 * @param keyCode
	 * @param event
	 * @return true
	 */
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		int e;
		if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
			Toast.makeText(getApplicationContext(), "Punctuation",
					Toast.LENGTH_SHORT).show();

			if (Punctuation == 0 && Commands == 0) {
				Punctuation = 1;
				Commands = 0;

				Intent intent = new Intent(
						RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
				intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");
				try {
					startActivityForResult(intent, RESULT_SPEECH);
					// txtText.setText("");
				} catch (ActivityNotFoundException a) {
					Toast t = Toast.makeText(getApplicationContext(),
							"Punctuation", Toast.LENGTH_SHORT);
					t.show();
				}

			}
			return true;
			/*
			 * } else if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
			 * Toast.makeText(getApplicationContext(), "Commands",
			 * Toast.LENGTH_SHORT).show(); if (Commands == 0 && Punctuation ==
			 * 0) { Commands = 1; Punctuation = 0; e =
			 * N.DeviceSignal.SPEAKER_COMMANDS; int signal =
			 * Helper.encodeSignal(N.Device.SPEAKER, N.DeviceDataCounter.SINGLE,
			 * e); MultiPlayApplication.add(signal);
			 * 
			 * //-----------------------------------record
			 * commands-------------------------- Intent intent = new
			 * Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
			 * intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "pl-PL");
			 * try { startActivityForResult(intent, RESULT_SPEECH); //
			 * txtText.setText(""); } catch (ActivityNotFoundException a) {
			 * Toast t = Toast.makeText(getApplicationContext(),"Commands",
			 * Toast.LENGTH_SHORT); t.show(); }
			 * 
			 * 
			 * } return true;
			 */
		}
		return true;

	}

	/**
	 * 
	 * @see android.app.Activity#onActivityResult(int, int,
	 *      android.content.Intent)
	 * @param requestCode
	 * @param resultCode
	 * @param data
	 */
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
						int signal = Helper.encodeSignal(N.Device.SPEAKER,
								N.DeviceDataCounter.SINGLE, e);
						MultiPlayApplication.add(signal);
						i += 1;
					}
				}/*
				 * else if(Commands == 1){ if(text.get(0).equals("uruchom")){
				 * Commands = 0; //-----------------------------------record
				 * application-------------------------- Intent intent = new
				 * Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
				 * intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
				 * "pl-PL"); try { startActivityForResult(intent,
				 * RESULT_SPEECH); // txtText.setText(""); } catch
				 * (ActivityNotFoundException a) {
				 * 
				 * } } }
				 */else if (Punctuation == 1) {
					if (text.get(0).equals("œrednik")) {
						e = N.DeviceSignal.KEYBOARD_KEY_TO_INT(";");
						int signal = Helper.encodeSignal(N.Device.SPEAKER,
								N.DeviceDataCounter.SINGLE, e);
						MultiPlayApplication.add(signal);
						Toast t = Toast.makeText(getApplicationContext(),
								text.get(0), Toast.LENGTH_SHORT);
						t.show();
					} else if (text.get(0).equals("przecinek")) {
						e = N.DeviceSignal.KEYBOARD_KEY_TO_INT(",");
						int signal = Helper.encodeSignal(N.Device.SPEAKER,
								N.DeviceDataCounter.SINGLE, e);
						MultiPlayApplication.add(signal);
						Toast t = Toast.makeText(getApplicationContext(),
								text.get(0), Toast.LENGTH_SHORT);
						t.show();
					} else if (text.get(0).equals("kropka")) {
						e = N.DeviceSignal.KEYBOARD_KEY_TO_INT(".");
						int signal = Helper.encodeSignal(N.Device.SPEAKER,
								N.DeviceDataCounter.SINGLE, e);
						MultiPlayApplication.add(signal);
						Toast t = Toast.makeText(getApplicationContext(),
								text.get(0), Toast.LENGTH_SHORT);
						t.show();
					} else if (text.get(0).equals("wykrzyknik")) {
						e = N.DeviceSignal.KEYBOARD_KEY_TO_INT("!");
						int signal = Helper.encodeSignal(N.Device.SPEAKER,
								N.DeviceDataCounter.SINGLE, e);
						MultiPlayApplication.add(signal);
						Toast t = Toast.makeText(getApplicationContext(),
								text.get(0), Toast.LENGTH_SHORT);
						t.show();
					} else if (text.get(0).equals("znak zapytania")) {
						e = N.DeviceSignal.KEYBOARD_KEY_TO_INT("?");
						int signal = Helper.encodeSignal(N.Device.SPEAKER,
								N.DeviceDataCounter.SINGLE, e);
						MultiPlayApplication.add(signal);
						Toast t = Toast.makeText(getApplicationContext(),
								text.get(0), Toast.LENGTH_SHORT);
						t.show();
					} else if (text.get(0).equals("dwukropek")) {
						e = N.DeviceSignal.KEYBOARD_KEY_TO_INT(":");
						int signal = Helper.encodeSignal(N.Device.SPEAKER,
								N.DeviceDataCounter.SINGLE, e);
						MultiPlayApplication.add(signal);
						Toast t = Toast.makeText(getApplicationContext(),
								text.get(0), Toast.LENGTH_SHORT);
						t.show();
					} else if (text.get(0).equals("cudzys³ów")) {
						e = N.DeviceSignal.KEYBOARD_KEY_TO_INT("\"");
						int signal = Helper.encodeSignal(N.Device.SPEAKER,
								N.DeviceDataCounter.SINGLE, e);
						MultiPlayApplication.add(signal);
						Toast t = Toast.makeText(getApplicationContext(),
								text.get(0), Toast.LENGTH_SHORT);
						t.show();
					} else if (text.get(0).equals("nawias")) {
						e = N.DeviceSignal.KEYBOARD_KEY_TO_INT("(");
						int signal = Helper.encodeSignal(N.Device.SPEAKER,
								N.DeviceDataCounter.SINGLE, e);
						MultiPlayApplication.add(signal);
						Toast t = Toast.makeText(getApplicationContext(),
								text.get(0), Toast.LENGTH_SHORT);
						t.show();
					} else if (text.get(0).equals("zamknij nawias")) {
						e = N.DeviceSignal.KEYBOARD_KEY_TO_INT(")");
						int signal = Helper.encodeSignal(N.Device.SPEAKER,
								N.DeviceDataCounter.SINGLE, e);
						MultiPlayApplication.add(signal);
						Toast t = Toast.makeText(getApplicationContext(),
								text.get(0), Toast.LENGTH_SHORT);
						t.show();
					} else if (text.get(0).equals("równe")
							|| text.get(0).equals("równa siê")) {
						e = N.DeviceSignal.KEYBOARD_KEY_TO_INT("=");
						int signal = Helper.encodeSignal(N.Device.SPEAKER,
								N.DeviceDataCounter.SINGLE, e);
						MultiPlayApplication.add(signal);
						Toast t = Toast.makeText(getApplicationContext(),
								text.get(0), Toast.LENGTH_SHORT);
						t.show();
					} else if (text.get(0).equals("plus")) {
						e = N.DeviceSignal.KEYBOARD_KEY_TO_INT("+");
						int signal = Helper.encodeSignal(N.Device.SPEAKER,
								N.DeviceDataCounter.SINGLE, e);
						MultiPlayApplication.add(signal);
						Toast t = Toast.makeText(getApplicationContext(),
								text.get(0), Toast.LENGTH_SHORT);
						t.show();
					} else if (text.get(0).equals("ma³pa")
							|| text.get(0).equals("at")) {
						e = N.DeviceSignal.KEYBOARD_KEY_TO_INT("@");
						int signal = Helper.encodeSignal(N.Device.SPEAKER,
								N.DeviceDataCounter.SINGLE, e);
						MultiPlayApplication.add(signal);
						Toast t = Toast.makeText(getApplicationContext(),
								text.get(0), Toast.LENGTH_SHORT);
						t.show();
					} else if (text.get(0).equals("ampersant")) {
						e = N.DeviceSignal.KEYBOARD_KEY_TO_INT("&");
						int signal = Helper.encodeSignal(N.Device.SPEAKER,
								N.DeviceDataCounter.SINGLE, e);
						MultiPlayApplication.add(signal);
						Toast t = Toast.makeText(getApplicationContext(),
								text.get(0), Toast.LENGTH_SHORT);
						t.show();
					} else if (text.get(0).equals("procent")) {
						e = N.DeviceSignal.KEYBOARD_KEY_TO_INT("%");
						int signal = Helper.encodeSignal(N.Device.SPEAKER,
								N.DeviceDataCounter.SINGLE, e);
						MultiPlayApplication.add(signal);
						Toast t = Toast.makeText(getApplicationContext(),
								text.get(0), Toast.LENGTH_SHORT);
						t.show();
					} else if (text.get(0).equals("dolar")) {
						e = N.DeviceSignal.KEYBOARD_KEY_TO_INT("$");
						int signal = Helper.encodeSignal(N.Device.SPEAKER,
								N.DeviceDataCounter.SINGLE, e);
						MultiPlayApplication.add(signal);
						Toast t = Toast.makeText(getApplicationContext(),
								text.get(0), Toast.LENGTH_SHORT);
						t.show();
					}
					Punctuation = 0;
				}
			}
			break;
		}
	}
}
