package com.anmnight.remoteprocess;

import android.app.Application;
import com.anmnight.remoteprocess.database.RemoteDatabase;
import com.facebook.stetho.Stetho;

import androidx.room.Room;

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/12/27 13:29
 * anmnight@qq.com
 */
public class RemoteApplication extends Application {


    public static RemoteDatabase mDatabase;



    @Override
    public void onCreate() {
        super.onCreate();

        if (mDatabase == null) {
            mDatabase = Room.databaseBuilder(this, RemoteDatabase.class, "remote_database").build();
        }


        Stetho.initializeWithDefaults(this);


    }
}
