package com.example.rseljeii.ar_drone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MyActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        setupButton();
    }

    private void setupButton()
    {
        // Get Reference to the button
        Button messageButton = (Button)findViewById(R.id.Start_Button);

        // Set the click listener to the button
        messageButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                //Log.i("ButtonExample2App", "You Clicked the button");
                //Toast.makeText(MyActivity.this, "You Click It Fucker", Toast.LENGTH_LONG).show();

                //Go to next Activity
                startActivity(new Intent(MyActivity.this, FlightMode.class));
            } // end of onClick()
        } // end of View.OnClickListener()
        );
    } // end of setupMessageButton

    public void buttonOnClick(View v)
    {
        Button button = (Button) v;
        startActivity(new Intent(getApplicationContext(), FlightMode.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
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


}
