package com.android.extendedWidgets;


public class TouchCircleButton {

	float angleStart;
	float angleCount;
	int signal;
	int color;
	
	TouchCircleButton(float angleMin, float angleCount, int signal, int color) {
		this.angleStart = angleMin;
		this.angleCount = angleCount;
		this.signal = signal;
		this.color = color;
	}
}
