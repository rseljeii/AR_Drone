package com.example.rseljeii.ar_drone;

import android.os.AsyncTask;
import android.util.Log;

import com.codeminders.ardrone.CommandSender;

/**
 * Created by rseljeii on 7/29/2014.
 */
public class AsyncTaskCommand extends AsyncTask<CommandSender, Void, String>
{
    //AsyncCommandSender ACS = new AsyncCommandSender();

    @Override
    protected String doInBackground(CommandSender... params)
    {
        params[0].run();
        return "Executed";
    }

    @Override
    protected void onPostExecute(String result)
    {
        Log.i("AsyncTaskCommand", "Executed");
    }

    @Override
    protected void onPreExecute() {}

    @Override
    protected void onProgressUpdate(Void... values) {}
} // end of class
