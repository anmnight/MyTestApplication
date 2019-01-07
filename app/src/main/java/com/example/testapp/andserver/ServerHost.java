package com.example.testapp.andserver;

import android.util.Log;

import com.example.testapp.unit.HttpUnit;
import com.yanzhenjie.andserver.AndServer;
import com.yanzhenjie.andserver.Server;

import java.net.InetAddress;
import java.util.concurrent.TimeUnit;

public class ServerHost {

    private Server mServer;
    private String TAG = "ServerHost";


    public ServerHost(InetAddress inetAddress) {

        mServer = AndServer.serverBuilder()
                .inetAddress(inetAddress)
                .port(8088)
                .timeout(10, TimeUnit.SECONDS)
                .listener(new ServerListener())
                .build();
    }

    public void startServer() {
        if (!mServer.isRunning()) {
            mServer.startup();
        }
    }

    public void stopServer() {
        if (mServer.isRunning()) {
            mServer.shutdown();
        } else {
            Log.w("AndServer", "The server has not started yet.");
        }
    }


    private class ServerListener implements Server.ServerListener {

        @Override
        public void onStarted() {
            Log.i(TAG, "onStarted");

            InetAddress address = HttpUnit.getLocalIPAddress();
            Log.i(TAG, mServer.getInetAddress().getHostAddress());
            Log.i(TAG, "Host : " + address.getHostAddress());
        }

        @Override
        public void onStopped() {
            Log.i(TAG, "onStopped");
        }

        @Override
        public void onException(Exception e) {
            e.printStackTrace();
        }
    }

}
