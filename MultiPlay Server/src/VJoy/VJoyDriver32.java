package VJoy;

import java.util.Arrays;
import java.util.List;

import Frame.AlertFrame;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Structure;

public class VJoyDriver32 extends VJoyDriver {
	public interface VJoy32 extends Library {
		VJoy32 INSTANCE = (VJoy32) Native.loadLibrary("VJoy32", VJoy32.class);

		boolean VJoy_Initialize(String name, String serial);

		void VJoy_Shutdown();

		boolean VJoy_UpdateJoyState(int id, JOYSTICK_STATE.ByReference pJoyState);

		public static class JOYSTICK_STATE extends Structure {
			public static class ByReference extends JOYSTICK_STATE implements
					Structure.ByReference {
			}

			public byte ReportId; // unsigned
			public short XAxis;
			public short YAxis;
			public short ZAxis;
			public short XRotation;
			public short YRotation;
			public short ZRotation;
			public short Slider;
			public short Dial;
			public short Wheel;
			public short POV; // unsigned
			public int Buttons; // unsigned

			@Override
			protected List<String> getFieldOrder() {
				return Arrays.asList(new String[] { "ReportId", "XAxis",
						"YAxis", "ZAxis", "XRotation", "YRotation",
						"ZRotation", "Slider", "Dial", "Wheel", "POV",
						"Buttons" });
			}
		}

	}

	VJoy32.JOYSTICK_STATE.ByReference m_joyState;
	VJoy32 vDLL;

	public VJoyDriver32(boolean autoInit) {
		super(autoInit);
	}

	public void VJoyInit() {
		String myLibraryPath = System.getProperty("user.dir") + "\\dll\\";
		System.setProperty("jna.library.path", myLibraryPath);

		System.out.printf(myLibraryPath + "\n");

		vDLL = VJoy32.INSTANCE;

		boolean result = vDLL.VJoy_Initialize("", "");
		if (result == true)
			System.out.printf("Inicjalizacja zakonczona powodzeniem");
		else {
			System.out.printf("Inicjalizacja nieudana");
			AlertFrame window = new AlertFrame("Not found Driver");
		}

		m_joyState = new VJoy32.JOYSTICK_STATE.ByReference();

		vDLL.VJoy_UpdateJoyState(0, m_joyState);
	}

	public boolean updateButtons() {
		// aktualizujemy stan przycisków
		short valueWheel = 0;
		short valuePOV = 0;

		for (int i = 1; i <= 8; i++) {
			if (buttonPressed[i])
				valueWheel += buttonID[i];
		}

		for (int i = 9; i <= 24; i++) {
			if (buttonPressed[i])
				valuePOV += (short) Math.pow(2, i - 9);
		}
		
		for(int i=25;i<=28;i++)
		{
			if(buttonPressed[i])
			{
				valueWheel+=16*(i-25);
				break;
			}
		}

		m_joyState.Wheel = valueWheel;
		m_joyState.POV = valuePOV;

		if (vDLL.VJoy_UpdateJoyState(0, m_joyState))
			return true;
		else
			return false;
	}

	public boolean updateAxes(int axisNr, int cordX, int cordY) {
		// aktualizujemy stan ga³ki axisNr (1 lub 2) i ustawiamy j¹ na
		// [cordX,cordY], ka¿da oœ w zakresie [-127,127]
		if (axisNr == 1) {
			m_joyState.XAxis = (short) cordX;
			m_joyState.YAxis = (short) cordY;
		} else {
			m_joyState.XRotation = (short) cordX;
			m_joyState.YRotation = (short) cordY;
		}

		if (vDLL.VJoy_UpdateJoyState(0, m_joyState))
			return true;
		else
			return false;
	}

	public void close() {
		vDLL.VJoy_Shutdown();
	}
}
