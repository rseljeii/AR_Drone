package com.example.rseljeii.ar_drone;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.example.rseljeii.ar_drone.R;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.shigeodayo.ardrone.manager.*;
import com.shigeodayo.ardrone.navdata.*;
import com.shigeodayo.ardrone.utils.*;
import com.shigeodayo.ardrone.processing.*;
import com.shigeodayo.ardrone.command.*;
import com.shigeodayo.ardrone.*;

public class FlightMode extends Activity
{
    Boolean liftoff = false;

    // http://drones.johnback.us/
    ARDroneForP5 ardrone, ardrone2;

    void setup()
    {
        //size(320, 240);
        Log.i("FlightMode", "YOU ARE IN setup()");

        //ardrone = new ARDroneForP5("192.168.1.1");
        ardrone = new ARDroneForP5("192.168.1.1", ARDroneVersion.ARDRONE2);
        Log.i("FlightMode setup()", "CONNECT");
        // Connect to AR.Drone
        ardrone.connect();
        Log.i("FlightMode setup()", "CONNECT NAV");

        // For sensor data streaming
        ardrone.connectNav();

        // For video data streaming
        //ardrone.connectVideo();
        Log.i("FlightMode setup()", "START");
        // Start controlling AR.Drone and getting sensor/video data
        ardrone.start();
        Log.i("FlightMode setup()", "Setup Finished");
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
            // Code to liftoff AR Drone
            ardrone.takeOff();
            liftoff = true;
        }

        else
        {
            // Code to land AR Drone
            ardrone.landing(); // IS CORRECT??
            liftoff = false;
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
                Toast.makeText(FlightMode.this, "Takeoff and Land", Toast.LENGTH_LONG).show();
                liftoff_land(v);
            } // end of onClick()
        } // end of View.OnClickListener()
        );
    } // end of Take_Land_Button

    private void Back_Button()
    {
        // Get Reference to the button
        Button button = (Button)findViewById(R.id.Back_Button);

        // Set the click listener to the button
        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Toast.makeText(FlightMode.this, "Back", Toast.LENGTH_LONG).show();
                ardrone.backward();
            } // end of onClick()
        } // end of View.OnClickListener()
        );
    } // end of Back_Button

    private void Down_Button()
    {
        // Get Reference to the button
        Button button = (Button)findViewById(R.id.Down_Button);

        // Set the click listener to the button
        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Toast.makeText(FlightMode.this, "Down", Toast.LENGTH_LONG).show();
                ardrone.down();
            } // end of onClick()
        } // end of View.OnClickListener()
        );
    } // end of Down_Button

    private void Forward_Button()
    {
        // Get Reference to the button
        Button button = (Button)findViewById(R.id.Forward_Button);

        // Set the click listener to the button
        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Toast.makeText(FlightMode.this, "Forward", Toast.LENGTH_LONG).show();
                ardrone.forward();
            } // end of onClick()
        } // end of View.OnClickListener()
        );
    } // end of Forward_Button

    private void Up_Button()
    {
        // Get Reference to the button
        Button button = (Button)findViewById(R.id.Up_Button);

        // Set the click listener to the button
        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Toast.makeText(FlightMode.this, "Up", Toast.LENGTH_LONG).show();
                ardrone.up();
            } // end of onClick()
        } // end of View.OnClickListener()
        );
    } // end of Up_Button

    private void Left_Button()
    {
        // Get Reference to the button
        Button button = (Button)findViewById(R.id.Left_Button);

        // Set the click listener to the button
        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Toast.makeText(FlightMode.this, "Left", Toast.LENGTH_LONG).show();
                ardrone.spinLeft();
            } // end of onClick()
        } // end of View.OnClickListener()
        );
    } // end of Up_Button

    private void Right_Button()
    {
        // Get Reference to the button
        Button button = (Button)findViewById(R.id.Right_Button);

        // Set the click listener to the button
        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Log.i("FlightMode", "YOU CLICK ON THE RIGHT BUTTON");
                Toast.makeText(FlightMode.this, "Right", Toast.LENGTH_LONG).show();
                ardrone.spinRight();
            } // end of onClick()
        } // end of View.OnClickListener()
        );
    } // end of Right_Button
} // end of class