package com.example.rseljeii.ar_drone;

import android.os.AsyncTask;
import android.util.Log;

import com.codeminders.ardrone.ARDrone;
import com.codeminders.ardrone.CommandSender;
import com.codeminders.ardrone.NavDataReader;

import java.io.IOException;
import java.net.DatagramSocket;

/**
 * Created by rseljeii on 8/21/2014.
 */
public class AsyncConnect extends AsyncTask<ARDrone, Void, String>
{
   @Override
    protected String doInBackground(ARDrone... params)
    {
        Log.i("AsyncCommandSender", "CONNECTNG");



        try
        {
            params[0].connect();
        }
        catch(IOException ex)
        {

        }


        return "Executed";
    }

    @Override
    protected void onPostExecute(String result)
    {
        Log.i("AsyncCommandSender", "Executed");
    }

    @Override
    protected void onPreExecute() {}

    @Override
    protected void onProgressUpdate(Void... values) {}

} // end of AsyncConnect class
