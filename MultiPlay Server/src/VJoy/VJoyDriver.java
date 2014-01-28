package VJoy;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Structure;
/**
 * 
 * Class handling the interaction with VJoy driver and, subsequently, with virtual gamepads created by it. It uses JNA to create interface
 * that maps functionality of VJoy dll and allows for its use in Java.
 * NOTE: You should use either 32 or 64-bit version of this class, depending on the OS.
 * 
 * @author Lucjan Koperkiewicz
 *
 */
public class VJoyDriver {

	public interface VJoy32 extends Library {
	    VJoy32 INSTANCE = (VJoy32)Native.loadLibrary("VJoy32", VJoy32.class);
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
	
	//deklaracja potrzebnych zmiennych
	VJoy32.JOYSTICK_STATE.ByReference m_joyState;
	VJoy32 vDLL;
	
	//tablica zawieraj¹ca wartoœci numeryczne poszczególnych przycisków, 2^(7+i)
	short[] buttonID;
	//tablica przechowywuj¹ca informacje o tym które przyciski s¹ aktualnie wciœniête, true/false
	boolean[] buttonPressed;
	
	//osie dwóch galek analogowych
	short axisX1,axisY1,axisX2,axisY2;
	
	//klasa z w¹tkiem reinicjalizuj¹cym sterownik co 5 minut
	VJoyReinit reinit;
	/**
	 * Creates new VJoyDriver containing necessary interfaces required for handling virtual gamepad and optionally immediately initializes it (recommended).
	 * NOTE: You should create either 32 or 64-bit version of this class, depending on the OS.
	 * 
	 * @param autoInit if true it will automatically initialize driver and start reinitialization thread. Otherwise it will require manual startup
	 * at a later time - see VJoyStart
	 */
	public VJoyDriver(boolean autoInit)
	{
		//autoInit - jeœli true to automatycznie inicjalizujemy sterownik i uruchamiamy w¹tek reinicjalizuj¹cy
		//ver - 32 albo 64, której wersji sterownika u¿yæ
		
		//NA TEMAT ZMIENNYCH BO SA MA£O LOGICZNE:
		//Wheel zawiera przyciski 1-8 ze wzorem 2^(7+i) oraz prawdopodobnie pomiêdzy nimi kombinacje dla dŸwigni POV (z regu³y to d-pad)
		//POV zawiera przyciski 9-24
		//Dial obs³uguje Tarczê, czymkolwiek ona jest, oraz zielony POV
		//Slider = suwak
		//Buttons nie mam bladego pojêcia co zawiera bo nic nie robi
		//Axis i Rotation dzia³aj¹ zgodnie z przewidywaniami, poza ZAxis - z jakiegoœ powodu nie widze jej wp³ywu na oœ Z, sprawdzi sie
		//zakresy osi w SDK podane s¹ od -32768 do 32767 ale nie mam pojêcia czemu a¿ taka skoro i tak siê zapêtla. Dlatego
		//przyjmujemy dok³adnoœæ -127 do 127.
		
		//dla standardowego pada w zupe³noœci powinno nam starczeæ 10-12 przycisków, standardowe osie X, Y, Z oraz POV 
		buttonID=new short[33];
		buttonPressed=new boolean[33];

		for(int i=0;i<=32;i++)
		{
			buttonID[i]=(short)Math.pow(2,7+i);
			buttonPressed[i]=false;
		}
		
		reinit=new VJoyReinit(this);
		
		if(autoInit)
		{
			VJoyStart();			
		}
		
	}
	
	/**
	 * Initializes the driver and starts reinitialization thread.
	 */
	public void VJoyStart()
	{
		VJoyInit();
		new Thread(reinit).start();
	}
	
	/**
	 * Initializes the driver from appropiate dll in \dll\ directory. 
	 */
	public void VJoyInit()
	{
		String myLibraryPath = System.getProperty("user.dir")+"\\dll\\";
		System.setProperty("jna.library.path", myLibraryPath);
		
		System.out.printf(myLibraryPath+"\n");
		
		
		vDLL=VJoy32.INSTANCE;
		
		boolean result=vDLL.VJoy_Initialize("","");
		if(result==true)		
		System.out.printf("Inicjalizacja zakonczona powodzeniem");
		else
		System.out.printf("Inicjalizacja nieudana");
		
		m_joyState= new VJoy32.JOYSTICK_STATE.ByReference();
		
		vDLL.VJoy_UpdateJoyState(0, m_joyState);
	}
	/**
	 * Simulates press of the button with given number on virtual gamepad. Supports buttons from 1 to 24. The pressed button will remain pressed
	 * until its release - see buttonRelease
	 * 
	 * @param buttonNumber number of button to press
	 * @return true if button was pressed successfully, false if not
	 */
	public boolean buttonPress(int buttonNumber)
	{
		//wciskamy przycisk o podanym numerze
		buttonPressed[buttonNumber]=true;
		if(updateButtons())		
		return true;
		else return false;
	}
	/**
	 * Releases previously pressed button.
	 * <p>
	 * If button was not pressed it does nothing.
	 * 
	 * @param buttonNumber number of button to release
	 * @return true if button was released successfully, false if not
	 */
	public boolean buttonRelease(int buttonNumber)
	{
		//puszczamy przycisk o podanym numerze
		buttonPressed[buttonNumber]=false;
		if(updateButtons())		
		return true;
		else return false;
	}
	/**
	 * Updates the current state of buttons, reflecting it in virtual gamepad.
	 * It is called automatically by functions responsible for changing buttons' state, but if such a need arises, it can be called to fix
	 * potential stuck buttons.
	 * 
	 * @return true if driver updated successfully, false if not
	 */
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
		
		for(int i=25;i<=28;i++)
		{
			if(buttonPressed[i])
			{
				valueWheel+=16*(i-25);
				break;
			}
		}
		
		m_joyState.Wheel=valueWheel;
		m_joyState.POV=valuePOV;
		
		if(vDLL.VJoy_UpdateJoyState(0,m_joyState))
		return true;
		else return false;
	}
	/**
	 * Changes the state of chosen analog stick axisNr to coordinates of cordX, cordY.
	 * The stick will remain in such state until it's changed again, so it is important to remember that returning it to neutral position
	 * requires calling this function with (cordX,cordY)=(0,0).
	 * 
	 * @param axisNr the number of analog stick updated. It is 1 for the left stick and 2 for right stick.
	 * @param cordX the new X position of stick. It is contained in range from -127 to -127, with 0 being central neutral position 
	 * @param cordY the new Y position of stick. It is contained in range from -127 to -127, with 0 being central neutral position
	 * @return true if analog stick updated successfully, false if not
	 */
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
			m_joyState.ZAxis=(short)cordX;
			m_joyState.ZRotation=(short)cordY;
		}
			
		if(vDLL.VJoy_UpdateJoyState(0,m_joyState))
		return true;
		else return false;
	}

	
	/**
	 * Closes the driver and its actions.
	 */
	public void close()
	{
		vDLL.VJoy_Shutdown();
	}

}
