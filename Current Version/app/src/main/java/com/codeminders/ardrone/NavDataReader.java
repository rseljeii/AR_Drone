
package com.codeminders.ardrone;
import android.os.AsyncTask;
import android.util.Log;

import com.example.rseljeii.ar_drone.AsyncChannel;
import com.example.rseljeii.ar_drone.AsyncTaskCommand;
import com.example.rseljeii.ar_drone.AsyncTaskNav;
import com.example.rseljeii.ar_drone.ChannelThread;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class NavDataReader extends AsyncTask<NavDataReader, Void, String> implements Runnable
{
    private static final int BUFSIZE = 4096;

    private DatagramChannel  channel;
    private ARDrone          drone;
    private Selector         selector;
    private boolean          done;
    private InetSocketAddress socket;
    private AsyncChannel channel_thread = new AsyncChannel();

    @Override
    protected String doInBackground(NavDataReader... params)
    {
        Log.i("NavDataManager", "DOING SOMETHING");

        params[0].run();
        return "Executed";
    }

    public NavDataReader(ARDrone drone, InetAddress drone_addr, int navdata_port) throws IOException
    {
        this.drone = drone;
        BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<Runnable>(50);

        Log.i("NavDataManager", "TEST1");
        ChannelThread channel = new ChannelThread();
        Log.i("NavDataManager", "TEST2");
        channel.connect(drone, drone_addr, navdata_port);
        new Thread(channel).start();
        Log.i("NavDataManager", "TEST3");

        /*
        //channel = DatagramChannel.open();
        //channel.configureBlocking(false);
        //channel.socket().bind(new InetSocketAddress(navdata_port));
        socket = new InetSocketAddress(drone_addr, navdata_port);Log.i("NavDataManager", "TEST4");
        //channel.connect(socket);   Log.i("NavDataManager", "TEST5");
        Log.i("NavDataManager", "PRE Execute");
        channel_thread.execute(socket);
        Log.i("NavDataManager", "POST Execute");
        //channel_thread.run(socket);
        //selector = Selector.open(); Log.i("NavDataManager", "TEST5");
        //channel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE); Log.i("NavDataManager", "TEST6");
        */

    }

    private void disconnect()
    {
        try
        {
            selector.close();
        } catch(IOException iox)
        {
            // Ignore
        }

        try
        {
            channel.disconnect();
        } catch(IOException iox)
        {
            // Ignore
        }
    }

    @Override
    public void run()
    {
        try
        {
            ByteBuffer inbuf = ByteBuffer.allocate(BUFSIZE);
            done = false;
            while(!done)
            {
                selector.select();
                if(done)
                {
                    disconnect();
                    break;
                }
                Set readyKeys = selector.selectedKeys();
                Iterator iterator = readyKeys.iterator();
                while(iterator.hasNext())
                {
                    SelectionKey key = (SelectionKey) iterator.next();
                    iterator.remove();
                    if(key.isWritable())
                    {
                        byte[] trigger_bytes = {0x01, 0x00, 0x00, 0x00};
                        ByteBuffer trigger_buf = ByteBuffer.allocate(trigger_bytes.length);
                        trigger_buf.put(trigger_bytes);
                        trigger_buf.flip();

                        channel_thread.write(trigger_buf);
                        //channel.write(trigger_buf);
                        //channel.register(selector, SelectionKey.OP_READ);
                    } else if(key.isReadable())
                    {
                        inbuf.clear();
                        int len = channel.read(inbuf);
                        byte[] packet = new byte[len];
                        inbuf.flip();
                        inbuf.get(packet, 0, len);

                        NavData nd = NavData.createFromData(packet);

                        drone.navDataReceived(nd);
                    }
                }
            }
        } catch(Exception e)
        {
            drone.changeToErrorState(e);
        }

    }

    public void stop()
    {
        done = true;
        selector.wakeup();
    }

}