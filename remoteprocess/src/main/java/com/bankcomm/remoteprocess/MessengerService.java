package com.bankcomm.remoteprocess;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

public class MessengerService extends Service {
    public MessengerService() {
    }

    private String TAG = "MessengerService";

    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }


    private static class MessengerHandler extends Handler {

        private String TAG = "MessengerHandler";
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
}
