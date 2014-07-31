import com.codeminders.ardrone.ARDrone;

public class Driver
{

    private static final long CONNECT_TIMEOUT = 3000;

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        ARDrone drone;
        try
        {
            // Create ARDrone object,
            // connect to drone and initialize it.
        	System.out.println("Constructor");
        	drone = new ARDrone(); 	
        	System.out.println("Connect");
        	drone.connect();	
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
            Thread.sleep(2000);

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
