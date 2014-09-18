package com.example.rseljeii.ar_drone;

import android.os.AsyncTask;
import android.util.Log;

import com.codeminders.ardrone.ARDrone;
import com.codeminders.ardrone.NavDataReader;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;

/**
 * Created by rseljeii on 9/17/2014.
 */
public class AsyncChannel extends AsyncTask<String, Void, DatagramChannel>
{
    private DatagramChannel channel;
    private ARDrone drone;
    private Selector selector;
    private boolean          done;
    InetAddress drone_addr;
    int navdata_port;

    @Override
    protected DatagramChannel doInBackground(String... params)
    {
        try {
            drone_addr = InetAddress.getByAddress(params[0].getBytes());
            navdata_port = Integer.parseInt(params[1]);


            Log.i("AsyncChannel", "IP = " + drone_addr.getAddress());
            channel = DatagramChannel.open();
            Log.i("NavDataManager", "TEST1");
            channel.configureBlocking(false);
            Log.i("NavDataManager", "TEST2");
            channel.socket().bind(new InetSocketAddress(navdata_port));
            Log.i("NavDataManager", "TEST3");
            channel.connect(new InetSocketAddress(drone_addr, navdata_port));
            Log.i("NavDataManager", "TEST4");

            selector = Selector.open();
            Log.i("NavDataManager", "TEST5");
            channel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
            Log.i("NavDataManager", "TEST6");

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return channel;
    }

    protected void onPostExecute(String result) {
        //TextView txt = (TextView) findViewById(R.id.output);
        //txt.setText("Executed"); // txt.setText(result);

        Log.i("AsyncCommandSender", "Executed");
    }

    @Override
    protected void onPreExecute() {}

    @Override
    protected void onProgressUpdate(Void... values) {}
} // end of class
