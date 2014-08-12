package com.example.rseljeii.ar_drone;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.example.rseljeii.ar_drone.R;

import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.codeminders.ardrone.ARDrone;

public class FlightMode extends Activity
{
    Boolean liftoff = false;

    // http://drones.johnback.us/
    ARDrone ardrone, ardrone2;

    void setup()
    {
        try
        {
            ardrone = new ARDrone();

            Log.i("FlightMode setup()", "CONNECT");
            ardrone.connect();

            ardrone.clearEmergencySignal();

            Log.i("FlightMode setup()", "Trim");
            ardrone.trim();
        }
        catch(Throwable e)
        {
            e.printStackTrace();
        }
        //ardrone = new ARDrone("192.168.1.1");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_mode);

        setup();

        Log.i("FlightMode onCreate()", ("Finished Setup"));

        // Buttons
        Take_Land_Button();Log.i("FlightMode onCreate()", ("Take_Land_Button"));
        Back_Button();Log.i("FlightMode onCreate()", ("Back_Button"));
        Down_Button();Log.i("FlightMode onCreate()", ("Down_Button"));
        Forward_Button();Log.i("FlightMode onCreate()", ("Forward_Button"));
        Up_Button();Log.i("FlightMode onCreate()", ("Up_Button"));
        Left_Button();Log.i("FlightMode onCreate()", ("Left_Button"));
        Right_Button();Log.i("FlightMode onCreate()", ("Right_Button"));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.flight_mode, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // This button will liftoff or land the AR Drone
    public void liftoff_land(View v)
    {
        // AR Drone has not lifted off and is currently parked
        if(liftoff == false)
        {
            liftoff = true;
            try
            {
                Log.i("FlightMode setup()", "Taking off");
                ardrone.takeOff();
                ardrone.hover();
            }
            catch(Throwable e)
            {
                e.printStackTrace();
            }
        }

        else
        {
            // Code to land AR Drone
            liftoff = false;
            try
            {
                Log.i("FlightMode setup()", "Landing");
                ardrone.land();
                Thread.sleep(5000);
                //ardrone.disconnect();
                //Log.i("FlightMode setup()", "Disconnect");
            }
            catch(Throwable e)
            {
                e.printStackTrace();
            }
        }
    } // end of liftoff_land()

    // Buttons Listener
    private void Take_Land_Button()
    {
        // Get Reference to the button
        Button TakeLandButton = (Button)findViewById(R.id.Take_Land_Button);

        // Set the click listener to the button
        TakeLandButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                //Toast.makeText(FlightMode.this, "Takeoff and Land", Toast.LENGTH_LONG).show();
                liftoff_land(v);
            } // end of onClick()
        } // end of View.OnClickListener()
        );
    } // end of Take_Land_Button

    private void Back_Button()
    {
        /*
            front_back_tilt The front-back tilt (aka. "drone pitch" or theta angle)

            A negative value makes the drone lower its nose, thus flying frontward.
            A positive value makes the drone raise its nose, thus flying backward.

            The drone translation speed in the horizontal plane depends on the environment and cannot be determined.
            With roll or pitch values set to 0, the drone will stay horizontal but continue sliding in the air because of its inertia.
            Only the air resistance will then make it stop.
         */

        // Get Reference to the button
        final Button button = (Button)findViewById(R.id.Back_Button);

        // Set the click listener to the button
        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                //Toast.makeText(FlightMode.this, "Back", Toast.LENGTH_LONG).show();

                try
                {
                    while(button.isPressed())
                    {
                        ardrone.move(0, 09f, 0, 0);
                        Log.i("FlightMode", "Move Back");
                    }

                    //Toast.makeText(FlightMode.this, "Back", Toast.LENGTH_LONG).show();
                }
                catch(Throwable e)
                {
                    e.printStackTrace();
                }
            } // end of onClick()

         } // end of View.OnClickListener()
        );
    } // end of Back_Button

    private void Down_Button()
    {
        /*
            @param vertical_speed The vertical speed (aka. "gaz") argument is a percentage of the maximum vertical speed as defined here.

            A positive value makes the drone rise in the air.
            A negative value makes it go down.
         */
        // Get Reference to the button
        final Button button = (Button)findViewById(R.id.Down_Button);

        // Set the click listener to the button
        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                //Toast.makeText(FlightMode.this, "Down", Toast.LENGTH_LONG).show();

                try
                {
                    while(button.isPressed())
                    {
                        ardrone.move(0, 0, -0.9f, 0);
                        Log.i("FlightMode", "Move Down");
                    }
                }
                catch(Throwable e)
                {
                    e.printStackTrace();
                }
            } // end of onClick()
        } // end of View.OnClickListener()
        );
    } // end of Down_Button

    private void Forward_Button()
    {
        /*
            front_back_tilt The front-back tilt (aka. "drone pitch" or theta angle)

            A negative value makes the drone lower its nose, thus flying frontward.
            A positive value makes the drone raise its nose, thus flying backward.

            The drone translation speed in the horizontal plane depends on the environment and cannot be determined.
            With roll or pitch values set to 0, the drone will stay horizontal but continue sliding in the air because of its inertia.
            Only the air resistance will then make it stop.
         */

        // Get Reference to the button
        final Button button = (Button)findViewById(R.id.Forward_Button);

        // Set the click listener to the button
        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                //Toast.makeText(FlightMode.this, "Forward", Toast.LENGTH_LONG).show();

                try
                {
                    while(button.isPressed())
                    {
                        ardrone.move(0, -0.9f, 0, 0);
                        Log.i("FlightMode", "Move Forward");
                    }

                    //Toast.makeText(FlightMode.this, "Forward", Toast.LENGTH_LONG).show();
                }
                catch(Throwable e)
                {
                    e.printStackTrace();
                }
            } // end of onClick()
        } // end of View.OnClickListener()
        );
    } // end of Forward_Button

    private void Up_Button()
    {
        /*
            @param vertical_speed The vertical speed (aka. "gaz") argument is a percentage of the maximum vertical speed as defined here.

            A positive value makes the drone rise in the air.
            A negative value makes it go down.
         */

        // Get Reference to the button
        final Button button = (Button)findViewById(R.id.Up_Button);

        // Set the click listener to the button
        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                //Toast.makeText(FlightMode.this, "Up", Toast.LENGTH_LONG).show();

                try
                {
                    while(button.isPressed())
                    {
                        ardrone.move(0, 0, 0.9f, 0);
                        Log.i("FlightMode", "Move Up");
                    }
                }
                catch(Throwable e)
                {
                    e.printStackTrace();
                }
            } // end of onClick()
        } // end of View.OnClickListener()
        );
    } // end of Up_Button

    private void Left_Button()
    {
        /*
            @param angular_speed The angular speed argument is a percentage of the
            maximum angular speed as defined here.

            A positive value makes the drone spin right.
            A negative value makes it spin left.
        */

        // Get Reference to the button
        final Button button = (Button)findViewById(R.id.Left_Button);

        // Set the click listener to the button
        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                //Toast.makeText(FlightMode.this, "Left", Toast.LENGTH_LONG).show();

                try
                {
                    while(button.isPressed())
                    {
                        ardrone.move(0, 0, 0, -10);
                        Log.i("FlightMode", "Move Left");
                    }
                }
                catch(Throwable e)
                {
                    e.printStackTrace();
                }
            } // end of onClick()
        } // end of View.OnClickListener()
        );
    } // end of Up_Button

    private void Right_Button()
    {
        /*
            @param angular_speed The angular speed argument is a percentage of the
            maximum angular speed as defined here.

            A positive value makes the drone spin right.
            A negative value makes it spin left.
        */

        // Get Reference to the button
        final Button button = (Button)findViewById(R.id.Right_Button);

        // Set the click listener to the button
        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Log.i("FlightMode", "YOU CLICK ON THE RIGHT BUTTON");
                //Toast.makeText(FlightMode.this, "Right", Toast.LENGTH_LONG).show();

                try
                {
                    while(button.isPressed())
                    {
                        ardrone.move(0, 0, 0, 0.9f);
                        Log.i("FlightMode", "Move Right");
                    }
                }
                catch(Throwable e)
                {
                    e.printStackTrace();
                }
            } // end of onClick()
        } // end of View.OnClickListener()
        );
    } // end of Right_Button
} // end of class