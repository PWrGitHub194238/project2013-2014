package com.android.multiplay;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class Your extends Activity implements SensorEventListener,
		OnTouchListener {
	private static final int GYROMOUSE = Menu.FIRST;
	private static final int KEYBOARD = Menu.FIRST + 1;
	private static final int MOUSE = Menu.FIRST + 2;
	private static final String IMAGEVIEW_TAG = "Android Logo";

	Button b;
	int hotx = 245, hoty = 176, flagmouse = 0, gyroflag = 0;
	TextView txv;
	private SensorManager sm;
	ScrollView sv;
	GridLayout ll;
	private android.widget.GridLayout.LayoutParams layoutParams;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_your);
		sv = new ScrollView(this);

		ll = new GridLayout(this);
		sv.addView(ll);

		txv = (TextView) super.findViewById(R.id.texty);
		sm = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);

		sm.registerListener(this,
				sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, GYROMOUSE, 0, "GYROMOUSE");
		menu.add(0, KEYBOARD, 0, "KEYBOARD");
		menu.add(0, MOUSE, 0, "MOUSE");
		return true;
	}

	/* Handles item selections */
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case KEYBOARD:
			newKey();
			return true;
		case GYROMOUSE:
			GyroMouse();
			return true;
		case MOUSE:
			Mouse();
			return true;
		}

		return false;
	}

	public void newKey() {
		final Button b = new Button(this);
		b.setText("Hurra");
		this.b = b;
		this.b.setTag(IMAGEVIEW_TAG);
		this.b.setOnLongClickListener(new View.OnLongClickListener() {

			@Override
			public boolean onLongClick(View v) {
				ClipData.Item item = new ClipData.Item((CharSequence) v
						.getTag());
				String[] mimeTypes = { ClipDescription.MIMETYPE_TEXT_PLAIN };
				ClipData dragData = new ClipData(v.getTag().toString(),
						mimeTypes, item);
				View.DragShadowBuilder myShadow = new DragShadowBuilder(b);

				// Starts the drag
				v.startDrag(dragData, // the data to be dragged
						myShadow, // the drag shadow builder
						null, // no need to use local data
						0); // flags (not currently used, set to 0)
				return false;
			}

		});
		b.setOnDragListener(new OnDragListener() {
			@Override
			public boolean onDrag(View v, DragEvent event) {
				switch (event.getAction()) {
				case DragEvent.ACTION_DRAG_STARTED:
					layoutParams = (GridLayout.LayoutParams) v
							.getLayoutParams();

					// Do nothing
					break;
				case DragEvent.ACTION_DRAG_ENTERED:

					int x_cord = (int) event.getX();
					int y_cord = (int) event.getY();
					break;
				case DragEvent.ACTION_DRAG_EXITED:
					x_cord = (int) event.getX();
					y_cord = (int) event.getY();
					layoutParams.leftMargin = x_cord;
					layoutParams.topMargin = y_cord;
					v.setLayoutParams(layoutParams);
					break;
				case DragEvent.ACTION_DRAG_LOCATION:
					x_cord = (int) event.getX();
					y_cord = (int) event.getY();
					break;
				case DragEvent.ACTION_DRAG_ENDED:
					x_cord = (int)0;
					y_cord = (int) 0;					break;
				case DragEvent.ACTION_DROP:
					
					break;
				default:
					break;
				}
				return true;
			}
		});
		ll.addView(b);
		this.setContentView(sv);

	}

	public void Mouse() {
		if (flagmouse == 0 && gyroflag == 0) {
			flagmouse = 1;
		} else
			flagmouse = 0;
	}

	public void GyroMouse() {
		if (gyroflag == 0 && flagmouse == 0) {
			gyroflag = 1;
		} else
			gyroflag = 0;

	}

	public boolean onTouchEvent(MotionEvent event) {
		if (flagmouse == 1) {
			int maskedAction = event.getActionMasked();
			String x = Integer.toString((hotx - (int) event.getX()) * (-1));
			String y = Integer.toString((hoty - (int) event.getY()) * (-1));
			switch (maskedAction) {
			// case MotionEvent.ACTION_DOWN:
			// txv.setText("X: " + x + "Y: " + y);
			// break;
			case MotionEvent.ACTION_MOVE:
				txv.setText("X: " + x + "Y: " + y);
				break;
			case MotionEvent.ACTION_UP:

				txv.setText("X: 0" + "Y: 0");
				break;
			}
		}
		return true;
		// RETURN SUPER.ONTOUCHEVENT(EVENT);
	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSensorChanged(SensorEvent arg0) {
		if (gyroflag == 1) {
			float x = arg0.values[0];
			float y = arg0.values[1];
			// if (stop == 0) {
			txv.setText(Integer.toString((int) x) + " "
					+ Integer.toString((int) y));
			// Sender sender = new Sender();
			// sender.setip(ip);
			// sender.getxy((int) y, (int) x);
			// sender.execute("mouse");
		}

	}

	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {
		// TODO Auto-generated method stub
		return false;
	}

}
