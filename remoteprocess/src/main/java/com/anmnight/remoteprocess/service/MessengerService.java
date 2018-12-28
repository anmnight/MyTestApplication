package com.anmnight.remoteprocess.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.anmnight.commlibrary.widget.FloatFrame;
import com.anmnight.remoteprocess.R;

public class MessengerService extends Service {
    public MessengerService() {
    }

    private String TAG = "MessengerService";

    private FloatFrame mFrame;

    private static View frameView;

    @Override
    public void onCreate() {
        super.onCreate();

        mFrame = new FloatFrame(this);

        LayoutInflater inflater = LayoutInflater.from(this);

        frameView = inflater.inflate(R.layout.service_chat_frame, null);

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


    private static class MessengerHandler extends Handler {

        private String TAG = "MessengerHandler";

        @Override
        public void handleMessage(Message msg) {
            String arg = msg.getData().getString("msg");
            log(arg);
        }

        private void log(String str) {
            TextView textView = frameView.findViewById(R.id.text_view);
            textView.setText(str);
        }

    }


    private Messenger messenger = new Messenger(new MessengerHandler());
}
