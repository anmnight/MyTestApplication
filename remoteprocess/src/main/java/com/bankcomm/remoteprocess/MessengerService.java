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
import android.widget.Button;

import com.bankcomm.commlibrary.widget.FloatFrame;

import org.jetbrains.annotations.NotNull;

public class MessengerService extends Service implements FrameViewEvent.Callback {
    public MessengerService() {
    }

    private String TAG = "MessengerService";

    private FloatFrame mFrame;

    private View frameView;

    private FrameViewEvent frameViewEvent;

    @Override
    public void onCreate() {
        super.onCreate();

        mFrame = new FloatFrame(this);

        LayoutInflater inflater = LayoutInflater.from(this);

        frameView = inflater.inflate(R.layout.service_chat_frame, null);

        frameViewEvent = new FrameViewEvent(frameView, this);

    }

    @Override
    public IBinder onBind(Intent intent) {

        mFrame.show(frameView);

        return messenger.getBinder();
    }


    @Override
    public boolean onUnbind(Intent intent) {

        mFrame.destroy();

        return super.onUnbind(intent);
    }

    @Override
    public void send(@NotNull String message) {

        try {
            Message msg = Message.obtain();
            Bundle bundle = new Bundle();
            bundle.putString("msg", message);
            msg.setData(bundle);
            repMessenger.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private static Messenger repMessenger;
    private static class MessengerHandler extends Handler {

        private String TAG = "MessengerHandler";


        @Override
        public void handleMessage(Message msg) {
            String arg = msg.getData().getString("msg");
            Log.i(TAG, "Client : " + arg);
            repMessenger = msg.replyTo;

        }

    }


    private Messenger messenger = new Messenger(new MessengerHandler());
}
