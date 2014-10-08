package com.example.rseljeii.ar_drone;

import android.util.Log;

import com.codeminders.ardrone.ARDrone;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Random;
import java.lang.Runnable;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by rseljeii on 9/24/2014.
 */
public class ChannelThread implements Runnable {

    private DatagramChannel channel;
    private ARDrone drone;
    private Selector selector;
    private boolean done;
    private InetSocketAddress socket;
    private InetAddress drone_addr;
    private int navdata_port;

    InetSocketAddress temp = null;

    public void connect(ARDrone drone, InetAddress drone_addr, int navdata_port)
    {
        Log.i("ChannelThread", "INSIDE CONNECT");
        Random rand = new Random(System.currentTimeMillis());

        try {
            temp = new InetSocketAddress(rand.nextInt((2500) + 1) + 1025);

            channel = DatagramChannel.open();
            channel.configureBlocking(false);
            //channel.socket().bind(new InetSocketAddress(rand.nextInt((2500) + 1) + 1025));
            channel.socket().bind(temp);
            this.drone_addr = drone_addr;
            this.navdata_port = navdata_port;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void run ( )
    {
        try {
            socket = new InetSocketAddress(drone_addr, navdata_port);
            Log.i("NavDataManager", "PRE Execute " + drone_addr + " port: " + temp.getPort() + " new port: " + socket.getPort() + " nav_port: " + navdata_port);
            channel.connect(socket);
            Log.i("NavDataManager", "POST Execute " + drone_addr + " port: " + temp.getPort() + " new port: " + socket.getPort() + " nav_port: " + navdata_port);
            selector = Selector.open();
            channel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}