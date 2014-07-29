package com.example.rseljeii.ar_drone;

import android.os.AsyncTask;
import android.util.Log;

import com.shigeodayo.ardrone.navdata.NavDataManager;

/**
 * Created by rseljeii on 7/29/2014.
 */
public class AsyncTaskNav extends AsyncTask<NavDataManager, Void, String>
{
    @Override
    protected String doInBackground(NavDataManager... params) {
        Log.i("NavDataManager", "DOING SOMETHING");

        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.interrupted();
            }
        }
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
