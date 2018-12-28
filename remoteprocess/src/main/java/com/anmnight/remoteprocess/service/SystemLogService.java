package com.anmnight.remoteprocess.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.anmnight.remoteprocess.ISystemLogInterface;
import com.anmnight.remoteprocess.RemoteApplication;
import com.anmnight.remoteprocess.database.dao.SystemLogDao;
import com.anmnight.remoteprocess.pojo.SystemLog;
import com.anmnight.remoteprocess.works.SystemLogWorker;

import java.util.concurrent.TimeUnit;

import androidx.work.Constraints;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

public class SystemLogService extends Service {
    public SystemLogService() {
    }

    private final String TAG = "SystemLogService";
    private final String WorkerTag = "system_log_worker";
    private PeriodicWorkRequest mPeriodicWorkRequest;

    private SystemLogDao dao = RemoteApplication.mDatabase.systemLogDao();

    @Override
    public void onCreate() {
        super.onCreate();

        Constraints constraints = new Constraints.Builder()
                .setRequiresCharging(true)
                .build();


    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {

        mPeriodicWorkRequest = new PeriodicWorkRequest.Builder(SystemLogWorker.class, 1, TimeUnit.SECONDS)
                .build();


        return new LogBinder();
    }


    private class LogBinder extends ISystemLogInterface.Stub {

        @Override
        public void startSystemLog() throws RemoteException {
            WorkManager.getInstance().enqueue(mPeriodicWorkRequest);
        }

        @Override
        public void stopSystemLog() throws RemoteException {
            WorkManager.getInstance().cancelAllWorkByTag(WorkerTag);
        }

        @Override
        public SystemLog loadSystemLogNow() throws RemoteException {

            return dao.queryNow();
        }

        @Override
        public void loadSpaceSystemLog(int start, int end) throws RemoteException {

        }
    }


}
