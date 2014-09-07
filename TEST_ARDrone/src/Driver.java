//import com.codeminders.ardrone.ARDrone;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

import com.codeminders.ardrone.ARDrone;

public class Driver
{

    private static final long CONNECT_TIMEOUT = 3000;

    /**
     * @param args
     */
    public static void main(String[] args)
    {
    	Scanner scan = new Scanner (System.in);
    	int a;
    	
    	ARDrone drone1 = null;
    	ARDrone drone2 = null;
    	Boolean test = true;
    	
    	
    	byte[] DRONE_IP_1  = { (byte) 192, (byte) 168, (byte) 1, (byte) 200 };
    	byte[] DRONE_IP_2  = { (byte) 192, (byte) 168, (byte) 1, (byte) 202 };
    	
    	InetAddress address1 = null;
    	InetAddress address2 = null;
    	
		try 
		{
			address1 = InetAddress.getByAddress(DRONE_IP_1);
			if(test) address2 = InetAddress.getByAddress(DRONE_IP_2);
		} 
		catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
    	
        try
        {
            System.out.println("Constructor");
        	drone1 = new ARDrone(address1);
            if(test) drone2 = new ARDrone(address2);
        	System.out.println("Connect");
        	Thread.sleep(1000);
        	drone1.connect();
        	Thread.sleep(3000);
        	if(test) drone2.connect();
            drone1.clearEmergencySignal();
            drone2.clearEmergencySignal();
            
            // Wait until drone is ready
            //drone.waitForReady(CONNECT_TIMEOUT);

            System.out.println("Trim");
            drone1.trim();
            if(test) drone2.trim();

            // Take off
            System.out.println("Taking off");
            drone1.takeOff();
            Thread.sleep(3000);
            if(test) drone2.takeOff();

            // Fly a little :)
            Thread.sleep(10000);
            
            /*
            //Move Forward            
            System.out.println("Forward");
            //for(int i = 0; i < 100000; i++)
            drone.move(0, -0.9f, 0, 0);
            Thread.sleep(3000);
            
			//Turn Left
            System.out.println("Left");
            //for(int i = 0; i < 50000; i++)
            drone.move(0, 0, 0, -0.9f);
            Thread.sleep(3000);
            
            // Turn Right
            System.out.println("Right");
            //for(int i = 0; i < 50000; i++)
            	drone.move(0, 0, 0, 0.9f);
            Thread.sleep(3000);
           
 			//Move Backward
            System.out.println("Backward");
            //for(int i = 0; i < 100000; i++)
            drone.move(0, 0.5f, 0, 0);
            Thread.sleep(3000);

          
           

            /*
 
            System.out.println("Command");
            a = scan.nextInt();
            int i = 0;
            Boolean temp = true;
            
            while(temp)
            {
            	System.out.print("\nEnter Command " + i++ + ": ");
            	if(a == 5)
            		drone.move(0, 0, 0, -0.9f);
            	if(a != 5)
            		temp = false;
            }


			*/





			// Land
            System.out.println("Landing");
            //System.err.println("Landing");
            drone1.land();
            Thread.sleep(3000);
            if(test) drone2.land();

            // Give it some time to land
            Thread.sleep(10000);
            
            // Disconnect from the done
            //drone1.disconnect();
            //if(test) drone2.disconnect();

        } catch(Throwable e)
        {
            e.printStackTrace();
        }
                
    }
}
