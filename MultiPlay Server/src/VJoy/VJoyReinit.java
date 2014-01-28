package VJoy;
/**
 * The reinitialization thread for VJoy driver.
 * Due to nature of limitations in the driver, it requires reinitialization every 5 minutes. This thread deals with that.
 * 
 * @author Lucjan Koperkiewicz
 *
 */
public class VJoyReinit implements Runnable {
	
	VJoyDriver driver;
	/**
	 * Creates an instance of VJoyReinit class. Usually called by @see VJoy.VJoyDriver#VJoyStart()
	 * 
	 * @param driver
	 */
	VJoyReinit(VJoyDriver driver)
	{
		this.driver=driver;
	}
	/**
	 * @see java.lang.Runnable#run()
	 */
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
