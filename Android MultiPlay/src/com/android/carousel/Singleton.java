package com.android.carousel;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

public class Singleton extends Application {
	private static Singleton m_Instance;

	// Appscreen metrics
	float m_fFrameS = 0;

	public int m_nFrameW = 0, m_nFrameH = 0, m_nTotalW = 0, m_nTotalH = 0,
			m_nPaddingX = 0, m_nPaddingY = 0;

	// Application constants
	public static final String PRJNAME = "Carousel",

	LOG_TAG = Singleton.PRJNAME + "::Singleton";

	public Activity m_GUI = null;

	/*---------------------------------------------------------------------------------------------
	 * Singleton Init instance
	 *--------------------------------------------------------------------------------------------*/
	public Singleton() {
		super();
		Debug(LOG_TAG, "Singleton created.");
		m_Instance = this;
		//
	}

	// Double-checked singleton fetching
	public static Singleton getInstance() {
		// init instance
		if (m_Instance == null) {
			synchronized (Singleton.class) {
				if (m_Instance == null)
					new Singleton();
			}
		}
		return m_Instance;
	}

	@Override
	public void onCreate() {
		Debug(LOG_TAG, "onCreate()");
		super.onCreate();

	}

	@Override
	public void onTerminate() {
		Debug(LOG_TAG, "onTerminate()");
		super.onTerminate();
	}

	/*---------------------------------------------------------------------------------------------
	 * Debug tools
	 *--------------------------------------------------------------------------------------------*/
	/**
	 * 
	 * @param Level
	 * @param tag
	 * @param message
	 */
	public void Debug(int Level, String tag, String message) {
		switch (Level) {
		case 0:
			Log.e(tag, message);
			break;
		case 1:
			Log.w(tag, message);
			break;
		case 2:
			Log.i(tag, message);
			break;
		case 3:
			Log.d(tag, message);
			break;
		case 4:
			Log.v(tag, message);
			break;
		default:
			Log.d(tag, message);
			break;
		}
	}

	/**
	 * 
	 * @param tag
	 * @param message
	 */
	public void Debug(String tag, String message) {
		Debug(3, tag, message);
	}

	/**
	 * 
	 * @param message
	 */
	public void Debug(String message) {
		Debug(3, LOG_TAG, message);
	}

	/*---------------------------------------------------------------------------------------------
	 * GUI Metrics                                                                                
	 *--------------------------------------------------------------------------------------------*/
	/**
	 * 
	 * @param context
	 * @param scale
	 */
	public void InitGUIFrame(Activity context, float scale) {
		m_GUI = context;
		DisplayMetrics dm = new DisplayMetrics();
		context.getWindowManager().getDefaultDisplay().getMetrics(dm);

		WindowManager wm = (WindowManager) m_GUI
				.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int width = size.x;
		int height = size.y;

		m_nTotalW = dm.widthPixels;
		m_nTotalH = dm.heightPixels;
		// scale factor
		m_fFrameS = (float) m_nTotalH / height;// 640.0f;
		m_fFrameS *= scale;
		// compute our frame
		m_nFrameW = (int) (width * m_fFrameS);// (960.0f * m_fFrameS);
		m_nFrameH = m_nTotalH;
		// compute padding for our frame inside the total screen size

		m_nPaddingY = 0;
		m_nPaddingX = (m_nTotalW - m_nFrameW) / 2;

		Debug(LOG_TAG, "InitGUIFrame: frame:" + m_nFrameW + "x" + m_nFrameH
				+ " Scale:" + m_fFrameS);

	}

	/**
	 * 
	 * @param v
	 * @return
	 */
	public int Scale(int v) {
		float s = (float) v * m_fFrameS;
		int rs = 0;

		if (s - (int) s >= 0.5)
			rs = ((int) s) + 1;
		else
			rs = (int) s;
		// Log.d("Scale", ""+s+":"+rs);
		return rs;
	}

	/**
	 * 
	 * @param context
	 * @param scalex
	 * @param scaley
	 * @param id
	 * @return
	 */
	public Bitmap getScaledBitmap(Context context, float scalex, float scaley,
			int id) {
		Bitmap bitmap = BitmapFactory
				.decodeResource(context.getResources(), id);
		Matrix matrix = new Matrix();
		matrix.postScale(scalex, scaley);
		matrix.postRotate(0);
		return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
				bitmap.getHeight(), matrix, true);
	}

	/**
	 * 
	 * @param context
	 * @param scalex
	 * @param scaley
	 * @param id
	 * @return
	 */
	public Drawable getScaledDrawable(Context context, float scalex,
			float scaley, int id) {
		return new BitmapDrawable(context.getResources(), getScaledBitmap(
				context, scalex, scaley, id));
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	Drawable ScaleDrawable(int id) {
		return getScaledDrawable(m_GUI, m_fFrameS, m_fFrameS, id);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	Bitmap ScaleBitmap(int id) {
		return getScaledBitmap(m_GUI, m_fFrameS, m_fFrameS, id);
	}

}
