package com.example.testapp.android.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/12/21 09:49
 * anmnight@qq.com
 */
public class BinderService extends Service {

    private static class MessengerHandler extends Handler {

        private String TAG = "BinderService";
        private Messenger messenger;

        @Override
        public void handleMessage(Message msg) {
            String arg = msg.getData().getString("msg");
            Log.i(TAG, "Client : " + arg);
            messenger = msg.replyTo;
            try {
                send();
            } catch (RemoteException e) {
                e.printStackTrace();
            }


        }


        private void send() throws RemoteException {
            Message msg = Message.obtain();
            Bundle bundle = new Bundle();
            bundle.putString("msg", "message from server");
            msg.setData(bundle);
            messenger.send(msg);
        }
    }


    private Messenger messenger = new Messenger(new MessengerHandler());

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }


}
