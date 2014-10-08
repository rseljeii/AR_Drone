package com.example.rseljeii.ar_drone;

import android.os.AsyncTask;
import android.util.Log;

import com.codeminders.ardrone.ARDrone;
import com.codeminders.ardrone.NavDataReader;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;

/**
 * Created by rseljeii on 9/17/2014.
 */
public class AsyncChannel extends AsyncTask<InetSocketAddress, Void, DatagramChannel>
{
    private DatagramChannel channel;
    private ARDrone drone;
    private Selector selector;
    private boolean done;

    @Override
    protected DatagramChannel doInBackground(InetSocketAddress... params)
    {
        Log.i("NavDataReader", "Test1");

        try
        {
            channel = DatagramChannel.open();
            channel.configureBlocking(false);
            channel.socket().bind(new InetSocketAddress(5554));
            channel.connect(params[0]);
            Log.i("AsyncChannel", "CONNECTED");
            selector = Selector.open();
            channel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return channel;
    }

    public DatagramChannel run(InetSocketAddress... params)
    {
        Log.i("NavDataReader", "Test1 IP Address: " + params[0].getAddress() + "     port: " + params[0].getPort());

        try
        {
            channel = DatagramChannel.open();
            channel.configureBlocking(false);
            channel.socket().bind(new InetSocketAddress(5554));
            channel.connect(params[0]);

            selector = Selector.open();
            channel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return channel;
    }

    protected void onPostExecute(String result)
    {
        Log.i("AsyncCommandSender", "Executed");
    }

    @Override
    protected void onPreExecute() {}

    @Override
    protected void onProgressUpdate(Void... values) {}

    public void write(ByteBuffer input)
    {
        try
        {
            channel.write(input);
            channel.register(selector, SelectionKey.OP_READ);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    } // end of write method
} // end of class
