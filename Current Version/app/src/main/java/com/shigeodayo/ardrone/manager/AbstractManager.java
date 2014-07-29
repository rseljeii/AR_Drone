/**
   ARDroneForP5
   https://github.com/shigeodayo/ARDroneForP5
   Copyright (C) 2013, Shigeo YOSHIDA.

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
*/
package com.shigeodayo.ardrone.manager;

import android.util.Log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public abstract class AbstractManager implements Runnable {

	protected InetAddress inetaddr = null;
	protected DatagramSocket socket = null;

	public boolean connect(int port) {
        Log.i("AbstractManager", "The port is: " + port);
		try {
			socket = new DatagramSocket(port);
            Log.i("AbstractManager", "NEW SOCKET: " + socket.getPort());
            Log.i("AbstractManager", "INTERNET ADDRESS: " + socket.getInetAddress());
            Log.i("AbstractManager", "LOCAL PORT: " + socket.getLocalPort());
            Log.i("AbstractManager", "CONNECTED: " + socket.isConnected());
			socket.setSoTimeout(10000);
			//socket.setSoTimeout(3000);
		} catch (SocketException e) {
			e.printStackTrace();
			return false;
		}
        Log.i("AbstractManager", "END OF CONNECT");
		return true;
	}

	public void close() {
		socket.close();
	}

	protected void ticklePort(int port) {
		byte[] buf = { 0x01, 0x00, 0x00, 0x00 };
		DatagramPacket packet = new DatagramPacket(buf, buf.length, inetaddr,
				port);
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
