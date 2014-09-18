package com.example.rseljeii.ar_drone;

import android.os.AsyncTask;
import android.util.Log;

import com.codeminders.ardrone.ARDrone;
import com.codeminders.ardrone.NavDataReader;

import java.nio.channels.DatagramChannel;
import java.nio.channels.Selector;

/**
 * Created by rseljeii on 8/17/2014.
 */
public class AsyncCommandSender extends AsyncTask<NavDataReader, Void, String>
{
    @Override
    protected String doInBackground(NavDataReader... params)
    {
        Log.i("AsyncCommandSender", "DOING SOMETHING");



        return "Executed";
    }

    @Override
    protected void onPostExecute(String result) {
        //TextView txt = (TextView) findViewById(R.id.output);
        //txt.setText("Executed"); // txt.setText(result);

        Log.i("AsyncCommandSender", "Executed");
    }

    @Override
    protected void onPreExecute() {}

    @Override
    protected void onProgressUpdate(Void... values) {}

} // end of AsyncCommandSender class
