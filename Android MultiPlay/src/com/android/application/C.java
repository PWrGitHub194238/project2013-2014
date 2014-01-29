package com.android.application;

import com.android.controllers.custom.CustomControllerActivity;
import com.android.controllers.gamepad.GamepadActivity;
import com.android.controllers.gamepad.GamepadOptionActivity;
import com.android.controllers.help.gamepad.GamepadHelp;
import com.android.controllers.help.gyromouse.GyromouseHelp;
import com.android.controllers.help.keyboard.KeyboardHelp;
import com.android.controllers.help.mouse.MouseHelp;
import com.android.controllers.help.speaker.SpeakerHelp;
import com.android.controllers.help.steeringwheel.SteeringwheelHelp;
import com.android.controllers.help.touchpad.TouchPadHelp;
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
import com.android.database.tables.General;
import com.android.multiplay.CustomControllerHelp;
import com.android.multiplay.R;

/** Stores structured information about each controller, by default available in the application, as well as the types of items available for building your own.
 * 
 * It also contains a class that defines a list of all the possible requirements that may be needed to run some of the defined controllers.
 * 
 * @author tomasz
 *
 */
public final class C {
	
	public static final int SIGNAL_MOUSE = 					Integer.parseInt("00000", 2);
	private static final ControllerDefinition CONTROLLER_MOUSE = new ControllerDefinition(
			C.SIGNAL_MOUSE, "Mouse", R.drawable.carousel_controller_icon_mouse, 
			ControllerDefinition.STAND_ALONE,MouseActivity.class, MouseOptionActivity.class, MouseHelp.class,
			Requirements.OS_EVERY, new String[] {
				
			});
	
	private static final ControllerDefinition CONTROLLER_GYROMOUSE = new ControllerDefinition(
			C.SIGNAL_MOUSE, "GyroMouse", R.drawable.carousel_controller_icon_gyromouse, 
			ControllerDefinition.STAND_ALONE, GyromouseActivity.class, GyromouseOptionActivity.class, GyromouseHelp.class,
			Requirements.OS_EVERY, new String[] {
				Requirements.SENSOR_ACCEL
			});
	
	private static final ControllerDefinition CONTROLLER_TOUCHPAD = new ControllerDefinition(
			C.SIGNAL_MOUSE, "Touchpad", R.drawable.carousel_controller_icon_touchpad, 
			ControllerDefinition.STAND_ALONE,  TouchpadActivity.class, TouchpadOptionActivity.class, TouchPadHelp.class,
			Requirements.OS_EVERY, new String[] {
				
			});
	
	public static final int SIGNAL_KEYBOARD = 					Integer.parseInt("00001", 2);
	private static final ControllerDefinition CONTROLLER_KEYBOARD = new ControllerDefinition(
			C.SIGNAL_KEYBOARD, "Keyboard", R.drawable.carousel_controller_icon_keyboard,
			ControllerDefinition.STAND_ALONE, KeyboardActivity.class, KeyboardOptionActivity.class, KeyboardHelp.class,
			Requirements.OS_EVERY, new String[] {
				
			});
	
	public static final int SIGNAL_WHEEL = 					Integer.parseInt("00010", 2);
	private static final ControllerDefinition CONTROLLER_WHEEL = new ControllerDefinition(
			C.SIGNAL_WHEEL, "Steering wheel", R.drawable.carousel_controller_icon_wheel, 
			ControllerDefinition.STAND_ALONE, SteeringwheelActivity.class, SteeringwheelOptionActivity.class, SteeringwheelHelp.class,
			Requirements.OS_WINDOWS, new String[] {
				Requirements.SENSOR_ACCEL
			});
	
	public static final int SIGNAL_SPEAKER = 					Integer.parseInt("00011", 2);
	private static final ControllerDefinition CONTROLLER_SPEAKER = new ControllerDefinition(
			C.SIGNAL_SPEAKER, "Speaker", R.drawable.carousel_controller_icon_speaker, 
			ControllerDefinition.STAND_ALONE, SpeakerActivity.class, SpeakerOptionActivity.class, SpeakerHelp.class,
			Requirements.OS_EVERY, new String[] {
				
			});
	
	public static final int SIGNAL_GAMEPAD = 					Integer.parseInt("00100", 2);
	private static final ControllerDefinition CONTROLLER_GAMEPAD = new ControllerDefinition(
			C.SIGNAL_GAMEPAD, "Gamepad", R.drawable.carousel_controller_icon_gamepad, 
			ControllerDefinition.STAND_ALONE, GamepadActivity.class, GamepadOptionActivity.class, GamepadHelp.class,
			Requirements.OS_WINDOWS, new String[] {
				
			});
	
	public static final int SIGNAL_CUSTOM = 					Integer.parseInt("01001", 2);
	private static final ControllerDefinition CONTROLLER_CUSTOM = new ControllerDefinition(
			C.SIGNAL_CUSTOM, "TouchCircle", R.drawable.carousel_controller_icon_touchcircle, 
			ControllerDefinition.STAND_ALONE, CustomControllerActivity.class, null, CustomControllerHelp.class,
			Requirements.OS_WINDOWS, new String[] {
				
			});
	
	public static final ControllerDefinition[] CONTROLLER_LIST = {
		CONTROLLER_MOUSE,
		CONTROLLER_GYROMOUSE,
		CONTROLLER_TOUCHPAD,
		CONTROLLER_KEYBOARD,
		CONTROLLER_WHEEL,
		CONTROLLER_SPEAKER,
		CONTROLLER_GAMEPAD,
	};
	
	public static final ControllerDefinition[] CUSTOM_CONTROLLER_LIST = {
		CONTROLLER_CUSTOM
	};
	
	/** defines a list of all the possible requirements that may be needed to run some of the defined controllers.
	 * 
	 * @author tomasz
	 *
	 */
	
	public static final class Requirements {

		public static final String BLUETOOTH = General.DBSchema.COLUMN_5;
        public static final String BLE = General.DBSchema.COLUMN_6;
        public static final String WIFI = General.DBSchema.COLUMN_7;
        public static final String WIFI_P2P = General.DBSchema.COLUMN_8;
        public static final String SENSOR_ACCEL = General.DBSchema.COLUMN_9;
        public static final String SENSOR_GYROSCOPE = General.DBSchema.COLUMN_10;
        public static final String SENSOR_GRAVITY = General.DBSchema.COLUMN_11;
        public static final String SENSOR_ROT_VECTOR = General.DBSchema.COLUMN_12;
        public static final String SENSOR_LIN_ACCEL = General.DBSchema.COLUMN_13;
        public static final String MICROPHONE = General.DBSchema.COLUMN_14;
        public static final String MULTITOUCH = General.DBSchema.COLUMN_15;
        
		public static final int OS_EVERY = 0;
		public static final int OS_LINUX = 1;
		public static final int OS_WINDOWS = 2;
		public static final int OS_BSD = 3;
	}
}