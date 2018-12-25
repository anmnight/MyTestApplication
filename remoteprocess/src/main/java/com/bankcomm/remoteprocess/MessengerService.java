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
import android.view.LayoutInflater;
import android.view.View;

import com.bankcomm.commlibrary.widget.FloatFrame;

public class MessengerService extends Service {
    public MessengerService() {
    }

    private String TAG = "MessengerService";

    private FloatFrame mFrame;

    private View mView;


    @Override
    public void onCreate() {
        super.onCreate();

        LayoutInflater inflater = LayoutInflater.from(this);

        mView = inflater.inflate(R.layout.ui_float_view, null);

        mFrame = new FloatFrame(this);

    }

    @Override
    public IBinder onBind(Intent intent) {

        mFrame.show(mView);

        Log.i(TAG, "onBind");

        return messenger.getBinder();
    }


    @Override
    public boolean onUnbind(Intent intent) {

        Log.d(TAG, "onUnbind");

        return super.onUnbind(intent);
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
