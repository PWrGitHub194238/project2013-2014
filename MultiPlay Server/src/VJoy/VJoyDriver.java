package VJoy;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Structure;

public class VJoyDriver {
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
	
	//deklaracja potrzebnych zmiennych
	VJoy64.JOYSTICK_STATE.ByReference m_joyState;
	VJoy64 vDLL;
	//tablica zawieraj¹ca wartoœci numeryczne poszczególnych przycisków, 2^(7+i)
	short[] buttonID;
	//tablica przechowywuj¹ca informacje o tym które przyciski s¹ aktualnie wciœniête, true/false
	boolean[] buttonPressed;
	
	//osie dwóch galek analogowych
	short axisX1,axisY1,axisX2,axisY2;
	
	//klasa z w¹tkiem reinicjalizuj¹cym sterownik co 5 minut
	VJoyReinit reinit;
	
	public VJoyDriver(boolean autoInit)
	{
		//autoInit - jeœli true to automatycznie inicjalizujemy sterownik i uruchamiamy w¹tek reinicjalizuj¹cy
		
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
	
	public void VJoyStart()
	{
		VJoyInit();
		new Thread(reinit).start();
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
	
	public boolean buttonPress(int buttonNumber)
	{
		//wciskamy przycisk o podanym numerze
		buttonPressed[buttonNumber]=true;
		if(updateButtons())		
		return true;
		else return false;
	}
	
	public boolean buttonRelease(int buttonNumber)
	{
		//puszczamy przycisk o podanym numerze
		buttonPressed[buttonNumber]=false;
		if(updateButtons())		
		return true;
		else return false;
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
