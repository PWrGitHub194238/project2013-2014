package com.android.extendedWidgets;

import java.io.IOException;
import java.util.ArrayList;

import com.android.application.MultiPlayApplication;
import com.android.application.N;
import com.android.application.N.Helper;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;

public class TouchCircle extends View implements OnLongClickListener, OnTouchListener {
	 
	int signal;
	int signalToRelease = -1;
	
	float maxRadius;
	float currentOuterRadius;
	
	float vjoyScale;
	float lifeCycleScale = 1.0f;
	
	float deadLifeCycleRatio = 0.1f;
	float deadRadius;
	float lifeOuterCycleRatio = 0.9f;
	float lifeRadius;
	
	double touchRadius;
	double multitouchRadius;
	
	double multitouchAngle;

	float left,top,right,bottom;
	float hotPointX, hotPointY;
	
	Paint paint = null;
	
	RectF outerCycleRectangledSize = null;
	RectF lifeCycleRectangledSize = null;
	RectF deadCycleRectangledSize = null;
	
	int lifeCycleX;
	int lifeCycleY;
	
	int pointerCounter = 1;
	int historicalPointerCounter = 1;
	
	ArrayList<TouchCircleButton> touchCircleButtons = null;


	// CONSTRUCTOR
	public TouchCircle(Context context, float left, float top, float radius, Point screenSize ) {
		super(context);
		setFocusable(true);

			try {
				MultiPlayApplication.runThread();
				initSizes(left,top,radius,screenSize);
				
				initButtons();
				
				initPaint();
				
				this.setOnTouchListener(this);
				this.setOnLongClickListener(this);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	private void initButtons() {
		touchCircleButtons = new ArrayList<TouchCircleButton>();
		touchCircleButtons.add(new TouchCircleButton(0, 90, 9, Color.BLUE));
		touchCircleButtons.add(new TouchCircleButton(90, 90, 10, Color.RED));
		touchCircleButtons.add(new TouchCircleButton(180, 45, 11, Color.GREEN));
		touchCircleButtons.add(new TouchCircleButton(225, 45, 12, Color.YELLOW));
		touchCircleButtons.add(new TouchCircleButton(270, 45, 13, Color.CYAN));
		touchCircleButtons.add(new TouchCircleButton(315, 45, 14, Color.MAGENTA));
	}

	private void initSizes(float left, float top, float radius, Point screenSize) {
		this.left = left;
		this.top = top;
		
		maxRadius = Math.abs(Math.min(screenSize.x - left, screenSize.y - top))/2;
		currentOuterRadius = maxRadius-top;

		this.right = 2*currentOuterRadius+left;
		this.bottom = 2*currentOuterRadius+top;
		
		outerCycleRectangledSize = new RectF(left, top, right, bottom);
		lifeCycleRectangledSize = getScaledButtonRectangledSize(lifeOuterCycleRatio,outerCycleRectangledSize);
		deadCycleRectangledSize = getScaledButtonRectangledSize(deadLifeCycleRatio, lifeCycleRectangledSize);
		
		hotPointX = currentOuterRadius + outerCycleRectangledSize.left;
		hotPointY = currentOuterRadius + outerCycleRectangledSize.top;
		
		vjoyScale = 126.0f/currentOuterRadius;
		
		Log.d("APP","MR: "+maxRadius);

		Log.d("APP","R: "+currentOuterRadius);
		Log.d("APP","L: "+outerCycleRectangledSize.left+" R: "+outerCycleRectangledSize.right+" T: "+outerCycleRectangledSize.top+" B: "+outerCycleRectangledSize.bottom);
		Log.d("APP","HX: "+hotPointX+" HY: "+hotPointY);
		
	}

	private RectF getScaledButtonRectangledSize(float lifeOuterCycleRatio, RectF buttonRectangledSize) {
		float widthDiff = buttonRectangledSize.width()*(1-lifeOuterCycleRatio)/2;
		float heightDiff = buttonRectangledSize.height()*(1-lifeOuterCycleRatio)/2;
		return new RectF(buttonRectangledSize.left+widthDiff,
				buttonRectangledSize.top+heightDiff,
				buttonRectangledSize.right-widthDiff,
				buttonRectangledSize.bottom-heightDiff);
	}

	private void initPaint() {
		paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.BLACK);
		paint.setStyle(Paint.Style.STROKE); 
	}

	
	private float getParametricAngle(float a, float b, float x, float y) {
		float partialDiff1 = x - a;
		float partialDiff2 = y - b;
		multitouchRadius = Math.sqrt(Math.pow(partialDiff1,2) + Math.pow(partialDiff2,2));
		multitouchAngle = Math.acos((partialDiff1)/multitouchRadius);
		if (partialDiff2 < 0) {
			multitouchAngle = 2*Math.PI - multitouchAngle;
		}
		return (float) multitouchAngle;
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		
		
		// opacity
		//p.setAlpha(0x80); //
		canvas.drawOval(outerCycleRectangledSize, paint);
		
		paint.setStyle(Paint.Style.FILL_AND_STROKE); 
		for ( TouchCircleButton button : touchCircleButtons ) {
			paint.setColor(button.color);
			canvas.drawArc(outerCycleRectangledSize, button.angleStart, button.angleCount, true, paint);
		}
		
		//canvas.drawLine(hotPointX, hotPointY, getParametricX(hotPointX,currentRadius,135), getParametricY(hotPointY,currentRadius,135), paint);
		paint.setColor(Color.BLACK);
		paint.setStyle(Paint.Style.STROKE); 
		canvas.drawOval(lifeCycleRectangledSize, paint);
		
		paint.setColor(Color.WHITE);
		paint.setStyle(Paint.Style.FILL); 
		canvas.drawOval(lifeCycleRectangledSize, paint);
		
		paint.setColor(Color.GREEN);
		canvas.drawOval(deadCycleRectangledSize, paint);
		//canvas.drawArc(buttonRectangledSize, 90, 180, true, paint);
		//canvas.drawLine(hotPointX, hotPointY, getParametricX(hotPointX,currentRadius,90), getParametricY(hotPointY,currentRadius,90), paint);

		paint.setColor(Color.WHITE);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		touchRadius = Math.sqrt(Math.pow(event.getX(0)-hotPointX,2) + Math.pow(event.getY(0)-hotPointY,2));
		if ( touchRadius <= currentOuterRadius) {
			pointerCounter = event.getPointerCount();
			if ( pointerCounter ==2) {
				if (pointerCounter == 1) {
//					Log.d("RAD1", "NORMAL");
				} else if (pointerCounter == 2) {
					getParametricAngle(event.getY(0),event.getX(0),event.getY(1),event.getX(1));
//					Log.d("RAD1", "P: "+String.valueOf(pointerCounter) + " A: "+Math.toDegrees(multitouchAngle));
					
					for ( TouchCircleButton button : touchCircleButtons ) {
						if (Math.toDegrees(multitouchAngle) > button.angleStart && Math.toDegrees(multitouchAngle) < button.angleStart + button.angleCount) {
//							Log.d("RAD2", "EXTRA SIGNAL: "+button.signal);
							switch(event.getAction() & MotionEvent.ACTION_MASK) {
							case MotionEvent.ACTION_POINTER_DOWN:
								if ( signalToRelease == -1 ) {
//									Log.d("RAD2", "EXTRA SIGNAL DOWN: "+button.signal);
									if ( event.getActionIndex() == 1) {
										Log.d("RAD2", "EXTRA SIGNAL 1 DOWN: "+button.signal);
										signalToRelease = button.signal;
										 signal = N.Helper.encodeSignal(N.Device.CUSTOM_TOUTHCIRCLE_BUTTON, 
												 N.DeviceDataCounter.DOUBLE,
												 signalToRelease,
												 N.DeviceSignal.PRESS);
										MultiPlayApplication.add(signal);
									}
								}
								break;
							case MotionEvent.ACTION_POINTER_UP:
								if ( signalToRelease != -1 ) {
//									Log.d("RAD2", "EXTRA SIGNAL UP: "+signalToRelease);
									if ( event.getActionIndex() == 1) {
										Log.d("RAD2", "EXTRA SIGNAL 1 UP: "+signalToRelease);
										signal = N.Helper.encodeSignal(N.Device.CUSTOM_TOUTHCIRCLE_BUTTON, 
												 N.DeviceDataCounter.DOUBLE,
												 signalToRelease,
												 N.DeviceSignal.RELEASE);
										signalToRelease = -1;
										MultiPlayApplication.add(signal);
									}
								}
								break;
							}
						}
					}
				}


			}
			historicalPointerCounter = pointerCounter;
			switch (event.getAction()) {
				case MotionEvent.ACTION_MOVE:
//					Log.d("APP", "R: "+touchRadius+" X: "+String.valueOf(hotPointY-event.getY(0))+" Y: "+String.valueOf(event.getX(0)-hotPointX));
					lifeCycleX = (int) (vjoyScale * (hotPointY-event.getY(0)));
					lifeCycleY = (int) (vjoyScale * (event.getX(0)-hotPointX));
//					Log.d("APP", "R: "+touchRadius+" X: "+String.valueOf(lifeCycleX)+" Y: "+String.valueOf(lifeCycleY));

					signal = N.Helper.encodeSignal(N.Device.VJOYJOYSTICKLEFT, 
							 N.DeviceDataCounter.DOUBLE,
							 lifeCycleX,
							 lifeCycleY);
					MultiPlayApplication.add(signal);
					break;
				case MotionEvent.ACTION_UP:
					Log.d("RAD2", "zero");
					signal = N.Helper.encodeSignal(N.Device.VJOYJOYSTICKLEFT, 
							 N.DeviceDataCounter.DOUBLE,
							 0,0);
					MultiPlayApplication.add(signal);
					break;
			}
		}
		return true;
	}

	@Override
	public boolean onLongClick(View v) {
		// TODO Auto-generated method stub
		return false;
	}

}