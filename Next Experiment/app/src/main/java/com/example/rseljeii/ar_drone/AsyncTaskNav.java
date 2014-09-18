package com.example.rseljeii.ar_drone;

import android.os.AsyncTask;
import android.util.Log;

import com.codeminders.ardrone.NavDataReader;

/**
 * Created by rseljeii on 7/29/2014.
 */
public class AsyncTaskNav extends AsyncTask<NavDataReader, Void, String>
{
    @Override
    protected String doInBackground(NavDataReader... params)
    {
        Log.i("NavDataManager", "DOING SOMETHING");

        params[0].run();
        return "Executed";
    }

    @Override
    protected void onPostExecute(String result) {
        //TextView txt = (TextView) findViewById(R.id.output);
        //txt.setText("Executed"); // txt.setText(result);

        Log.i("AsyncTask", "Executed");
    }

    @Override
    protected void onPreExecute() {}

    @Override
    protected void onProgressUpdate(Void... values) {}

} // end of class
