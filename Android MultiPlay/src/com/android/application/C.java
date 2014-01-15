package com.android.application;

import com.android.controllers.keyboard.KeyboardActivity;
import com.android.controllers.keyboard.KeyboardOptionActivity;
import com.android.controllers.keyboard.SpeakerActivity;
import com.android.controllers.keyboard.SpeakerOptionActivity;
import com.android.controllers.mouse.GyromouseActivity;
import com.android.controllers.mouse.GyromouseOptionActivity;
import com.android.controllers.mouse.MouseActivity;
import com.android.controllers.mouse.MouseOptionActivity;
import com.android.controllers.mouse.TouchpadActivity;
import com.android.controllers.mouse.TouchpadOptionActivity;
import com.android.controllers.steeringwheel.SteeringwheelActivity;
import com.android.controllers.steeringwheel.SteeringwheelOptionActivity;
import com.android.multiplay.R;

public final class C {
	
	public static final int SIGNAL_MOUSE = 					Integer.parseInt("00000", 2);
	private static final ControllerDefinition CONTROLLER_MOUSE = new ControllerDefinition(
			C.SIGNAL_MOUSE, "Mouse", R.drawable.carousel_controller_icon_mouse, 
			ControllerDefinition.STAND_ALONE,MouseActivity.class, MouseOptionActivity.class,
			Requirements.OS_EVERY, new int[] {
				
			});
	
	private static final ControllerDefinition CONTROLLER_GYROMOUSE = new ControllerDefinition(
			C.SIGNAL_MOUSE, "GyroMouse", R.drawable.carousel_controller_icon_gyromouse, 
			ControllerDefinition.STAND_ALONE, GyromouseActivity.class, GyromouseOptionActivity.class,
			Requirements.OS_EVERY, new int[] {
				Requirements.SENSOR_GYROSCOPE
			});
	
	private static final ControllerDefinition CONTROLLER_TOUCHPAD = new ControllerDefinition(
			C.SIGNAL_MOUSE, "Touchpad", R.drawable.carousel_controller_icon_touchpad, 
			ControllerDefinition.STAND_ALONE,  TouchpadActivity.class, TouchpadOptionActivity.class,
			Requirements.OS_EVERY, new int[] {
				
			});
	
	public static final int SIGNAL_KEYBOARD = 					Integer.parseInt("00001", 2);
	private static final ControllerDefinition CONTROLLER_KEYBOARD = new ControllerDefinition(
			C.SIGNAL_KEYBOARD, "Keyboard", R.drawable.carousel_controller_icon_keyboard,
			ControllerDefinition.STAND_ALONE, KeyboardActivity.class, KeyboardOptionActivity.class,
			Requirements.OS_EVERY, new int[] {
				
			});
	
	public static final int SIGNAL_WHEEL = 					Integer.parseInt("00010", 2);
	private static final ControllerDefinition CONTROLLER_WHEEL = new ControllerDefinition(
			C.SIGNAL_WHEEL, "Steering wheel", R.drawable.carousel_controller_icon_wheel, 
			ControllerDefinition.STAND_ALONE, SteeringwheelActivity.class, SteeringwheelOptionActivity.class,
			Requirements.OS_WINDOWS, new int[] {
				Requirements.SENSOR_ACCELEROMETER
			});
	
	public static final int SIGNAL_SPEAKER = 					Integer.parseInt("00011", 2);
	private static final ControllerDefinition CONTROLLER_SPEAKER = new ControllerDefinition(
			C.SIGNAL_SPEAKER, "Speaker", R.drawable.carousel_controller_icon_speaker, 
			ControllerDefinition.NOT_STAND_ALONE, SpeakerActivity.class, SpeakerOptionActivity.class,
			Requirements.OS_EVERY, new int[] {
				
			});
	
	public static final ControllerDefinition[] CONTROLLER_LIST = {
		CONTROLLER_MOUSE,
		CONTROLLER_GYROMOUSE,
		CONTROLLER_TOUCHPAD,
		CONTROLLER_KEYBOARD,
		CONTROLLER_WHEEL,
		CONTROLLER_SPEAKER
	};
	
	
	public static final class Requirements {
		public static final int SENSOR_ACCELEROMETER = 0;
		public static final int SENSOR_GRAVITY = 1;
		public static final int SENSOR_GYROSCOPE = 2;
		public static final int SENSOR_LINEAR_ACCELERATION = 3;
		public static final int SENSOR_ROTATION_VECTOR = 4;
		
		public static final int OS_EVERY = 0;
		public static final int OS_LINUX = 1;
		public static final int OS_WINDOWS = 2;
		public static final int OS_BSD = 3;
	}
}