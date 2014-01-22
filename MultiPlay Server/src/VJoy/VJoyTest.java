package VJoy;

import java.util.Scanner;

public class VJoyTest {
	
	public static void main(String[] args) {
			
		VJoyDriver driver=new VJoyDriver64(true);
			
        Scanner s = new Scanner(System.in);
        String input;       
     
        int i=0;

//        for(i=256;i<=300000;i++)
//        {        	
//    		driver.testButtons((short)i);
//    		System.out.println(i);
//    		
//        	try {
//				Thread.sleep(100);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//        }
               
        for(i=-127;i<=127;i++)
        {        	
    		driver.updateAxes(1,i,i*3);
    		driver.updateAxes(2,i,i*3);
    		
        	try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
		driver.updateAxes(1,0,0);
		driver.updateAxes(2,0,0);
        
        System.out.printf("\nTest osi zakoñczony.");
    	driver.buttonPress(28);
        for(i=1;i<=24;i++)
        {
        	driver.buttonPress(i);
        	try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    	driver.buttonPress(26);
        for(i=1;i<=24;i++)
        {
        	driver.buttonRelease(i);
        	try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
        System.out.printf("\nTest przyciskow zakoñczony.");   
        System.out.printf("\nOczekiwanie na dane wejsciowe:"); 
        
        while (true)
        {
        	input=s.next();
        	
        	if(input.equals("0"))
        	{
        		driver.buttonRelease(1);
        		driver.buttonRelease(2);
        		driver.buttonRelease(3);
        		driver.buttonRelease(4);
        	}
        	
        	if(input.equals("1"))
        	{
        		driver.buttonPress(1);        		
        	}
        	
        	if(input.equals("2"))
        	{
        		driver.buttonPress(2);
        	}
        	
        	if(input.equals("3"))
        	{
        		driver.buttonPress(3);
        	}
        	
        	if(input.equals("4"))
        	{
        		driver.buttonPress(4);
        	}
        	
        	if(input.equals("exit"))
        		break;      	
        }
        		
		s.close();
		driver.close();
	}

}
