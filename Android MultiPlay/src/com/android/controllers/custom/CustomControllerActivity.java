package com.android.controllers.custom;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;

import com.android.extendedWidgets.TouchCircle;

/** Creates a Touchcircle custom view.
 * 
 * @author tomasz
 *
 */
public class CustomControllerActivity extends Activity {

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		Point point = new Point();
		display.getSize(point);
		
		TouchCircle view = new TouchCircle(this,200,10,10,point);
		setContentView(view);
	}

}
