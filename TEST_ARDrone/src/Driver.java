//import com.codeminders.ardrone.ARDrone;
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
    	ARDrone drone;
    	
    	
        try
        {
            // Create ARDrone object,
            // connect to drone and initialize it.
        	System.out.println("Constructor");
        	drone = new ARDrone(); 	
        	System.out.println("Connect");
        	//drone.connect();	
            //drone.clearEmergencySignal();

            // Wait until drone is ready
            //drone.waitForReady(CONNECT_TIMEOUT);

            // do TRIM operation
            System.out.println("Trim");
            drone.trim();	

            // Take off
            System.out.println("Taking off");
            //System.err.println("Taking off");
            drone.takeOff();

            // Fly a little :)
            //Thread.sleep(10000);
            
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
            drone.land();

            // Give it some time to land
            Thread.sleep(2000);
            
            // Disconnect from the done
            drone.disconnect();

        } catch(Throwable e)
        {
            e.printStackTrace();
        }
                
    }
}
