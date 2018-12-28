package com.anmnight.remoteprocess.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.RemoteException;

import com.anmnight.remoteprocess.ISystemLogInterface;
import com.anmnight.remoteprocess.RemoteApplication;
import com.anmnight.remoteprocess.database.dao.SystemLogDao;
import com.anmnight.remoteprocess.pojo.SystemLog;
import com.anmnight.remoteprocess.works.SystemLogWorker;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import androidx.work.Constraints;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

public class SystemLogService extends Service {
    public SystemLogService() {
    }

    private PeriodicWorkRequest mPeriodicWorkRequest;

    private String WORKER_PREFERENCES = "preferences.worker";
    private String SYSTEM_LOG = "worker.system.log";

    private SystemLogDao dao = RemoteApplication.mDatabase.systemLogDao();

    private Constraints constraints = new Constraints.Builder()
            .setRequiresCharging(true)
            .build();

    private SharedPreferences mSharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
        mSharedPreferences = getSharedPreferences(WORKER_PREFERENCES, Context.MODE_PRIVATE);
    }

    @Override
    public IBinder onBind(Intent intent) {
        mPeriodicWorkRequest = new PeriodicWorkRequest.Builder(SystemLogWorker.class, 1, TimeUnit.SECONDS)
                .setConstraints(constraints)
                .build();

        return new LogBinder();
    }


    private class LogBinder extends ISystemLogInterface.Stub {

        @Override
        public void startSystemLog() throws RemoteException {
            WorkManager.getInstance().enqueue(mPeriodicWorkRequest);
            addSystemLogWorker(mPeriodicWorkRequest.getId());
        }

        @Override
        public void stopSystemLog() throws RemoteException {
            WorkManager.getInstance().cancelWorkById(findSystemLogWorkerUUID());
        }

        @Override
        public SystemLog loadSystemLogNow() throws RemoteException {
            return dao.queryNow();
        }

        @Override
        public void loadSpaceSystemLog(int start, int end) throws RemoteException {

        }
    }


    private void addSystemLogWorker(UUID uuid) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(SYSTEM_LOG, uuid.toString());
        editor.apply();
    }

    private UUID findSystemLogWorkerUUID() {
        return UUID.fromString(mSharedPreferences.getString(SYSTEM_LOG, ""));
    }


}
