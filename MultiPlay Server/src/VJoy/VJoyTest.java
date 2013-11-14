package VJoy;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Structure;

public class VJoyTest {

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
	public static void main(String[] args) {
		
		String myLibraryPath = System.getProperty("user.dir")+"\\dll\\";
		System.setProperty("jna.library.path", myLibraryPath);
		
		System.out.printf(myLibraryPath+"\n");
		
		VJoy64 vDLL=VJoy64.INSTANCE;
		
		boolean result=vDLL.VJoy_Initialize("","");
		if(result==true)		
		System.out.printf("Inicjalizacja zakonczona powodzeniem");
		else
		System.out.printf("Inicjalizacja nieudana");
		
		VJoy64.JOYSTICK_STATE.ByReference m_joyState= new VJoy64.JOYSTICK_STATE.ByReference(); 
		
		//m_joyState.XAxis = 32767; 
		//m_joyState.YAxis = 15767;
		//m_joyState.ZAxis = 0;
		//m_joyState.ZRotation = 2000;
		//m_joyState.Wheel=(short)Math.pow(2,12);
		//m_joyState.POV=3;
		vDLL.VJoy_UpdateJoyState(0, m_joyState);
			
        Scanner s = new Scanner(System.in);
        String input;
        while (true)
        {
        	input=s.next();
        	
        	if(input.equals("0"))
        	{
        		m_joyState.Wheel=0;
        		vDLL.VJoy_UpdateJoyState(0, m_joyState);
        	}
        	
        	if(input.equals("1"))
        	{
        		m_joyState.Wheel=(short)Math.pow(2,8);
        		vDLL.VJoy_UpdateJoyState(0, m_joyState);
        	}
        	
        	if(input.equals("2"))
        	{
        		m_joyState.Wheel=(short)Math.pow(2,9);
        		vDLL.VJoy_UpdateJoyState(0, m_joyState);
        	}
        	
        	if(input.equals("3"))
        	{
        		m_joyState.Wheel=(short)Math.pow(2,10);
        		vDLL.VJoy_UpdateJoyState(0, m_joyState);
        	}
        	
        	if(input.equals("4"))
        	{
        		m_joyState.Wheel=(short)Math.pow(2,11);
        		vDLL.VJoy_UpdateJoyState(0, m_joyState);
        	}
        	
        	if(input.equals("exit"))
        		break;      	
        }
        		
		s.close();
		vDLL.VJoy_Shutdown();
	}

}
