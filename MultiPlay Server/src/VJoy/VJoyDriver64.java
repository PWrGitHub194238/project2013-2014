package VJoy;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Structure;

public class VJoyDriver64 extends VJoyDriver {
	public interface VJoy64 extends Library {
	    VJoy64 INSTANCE = (VJoy64)Native.loadLibrary("VJoy64", VJoy64.class);
	    boolean VJoy_Initialize(String name,String serial);
	    void VJoy_Shutdown();
	    boolean VJoy_UpdateJoyState(int id, JOYSTICK_STATE.ByReference pJoyState);
	    
		public static class JOYSTICK_STATE extends Structure {
			public static class ByReference extends JOYSTICK_STATE implements Structure.ByReference {}
	    	public byte ReportId; //unsigned
	    	public short XAxis;
	    	public short YAxis;
	    	public short ZAxis;
	    	public short XRotation;
	    	public short YRotation;
	    	public short ZRotation;
	    	public short Slider;
	    	public short Dial;
	    	public short Wheel;
	    	public short POV; //unsigned
	    	public int Buttons; //unsigned
	    	
	        @Override
	        protected List<String> getFieldOrder() {
	            return Arrays.asList(new String[] {
	                    "ReportId", "XAxis", "YAxis", "ZAxis", "XRotation", "YRotation", "ZRotation", "Slider", "Dial", "Wheel", "POV", "Buttons"  });
	        }
		}
					
	}
	
	VJoy64.JOYSTICK_STATE.ByReference m_joyState;
	VJoy64 vDLL;
	
	public VJoyDriver64(boolean autoInit) {
		super(autoInit);
	}
	
	public void VJoyInit()
	{
		String myLibraryPath = System.getProperty("user.dir")+"\\dll\\";
		System.setProperty("jna.library.path", myLibraryPath);
		
		System.out.printf(myLibraryPath+"\n");
				
		vDLL=VJoy64.INSTANCE;
		
		boolean result=vDLL.VJoy_Initialize("","");
		if(result==true)		
		System.out.printf("Inicjalizacja zakonczona powodzeniem");
		else
		System.out.printf("Inicjalizacja nieudana");
		
		m_joyState= new VJoy64.JOYSTICK_STATE.ByReference();
		
		vDLL.VJoy_UpdateJoyState(0, m_joyState);
	}
	
	public boolean updateButtons()
	{
		//aktualizujemy stan przycisków
		short valueWheel=0;
		short valuePOV=0;
		
		for(int i=1;i<=8;i++)
		{
			if(buttonPressed[i])
				valueWheel+=buttonID[i];
		}
		
		for(int i=9;i<=24;i++)
		{
			if(buttonPressed[i])
				valuePOV+=(short)Math.pow(2,i-9);
		}
		
		m_joyState.Wheel=valueWheel;
		m_joyState.POV=valuePOV;
		
		if(vDLL.VJoy_UpdateJoyState(0,m_joyState))
		return true;
		else return false;
	}
	
	public boolean updateAxes(int axisNr,int cordX,int cordY)
	{
		//aktualizujemy stan ga³ki axisNr (1 lub 2) i ustawiamy j¹ na [cordX,cordY], ka¿da oœ w zakresie [-127,127]
		if(axisNr==1)
		{
			m_joyState.XAxis=(short)cordX;
			m_joyState.YAxis=(short)cordY;
		}
		else
		{
			m_joyState.XRotation=(short)cordX;
			m_joyState.YRotation=(short)cordY;
		}
			
		if(vDLL.VJoy_UpdateJoyState(0,m_joyState))
		return true;
		else return false;
	}
	
	public void close()
	{
		vDLL.VJoy_Shutdown();
	}
}
