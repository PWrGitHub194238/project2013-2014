package VJoy;

public class VJoyReinit implements Runnable {
	
	VJoyDriver driver;
	
	VJoyReinit(VJoyDriver driver)
	{
		this.driver=driver;
	}
	
	public void run()
	{
		while(true)
		{	
		try {
			Thread.sleep(300000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.close();
		driver.VJoyInit();	
		driver.updateButtons();
		}
		
	}
}
