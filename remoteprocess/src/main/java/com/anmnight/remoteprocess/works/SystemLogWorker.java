package com.anmnight.remoteprocess.works;


import android.content.Context;
import android.util.Log;

import com.anmnight.remoteprocess.RemoteApplication;
import com.anmnight.remoteprocess.database.dao.SystemLogDao;
import com.anmnight.remoteprocess.pojo.SystemLog;
import com.anmnight.remoteprocess.unit.SystemStatusUnit;

import java.text.SimpleDateFormat;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/12/27 14:24
 * anmnight@qq.com
 */
public class SystemLogWorker extends Worker {

    private String TAG = "SystemLogWorker";

    private SystemLogDao dao = RemoteApplication.mDatabase.systemLogDao();
    private Context context;

    public SystemLogWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.context = context;
    }

    @NonNull
    @Override
    public Result doWork() {

        try {
            SystemLog log = new SystemLog();
            long seed = System.currentTimeMillis();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHms", Locale.CHINA);
            String time = dateFormat.format(seed);
            log.timeSeed = seed;
            log.timeString = time;
            log.processCpu = SystemStatusUnit.INSTANCE.getCurProcessCpuRate();
            log.totalCpu = SystemStatusUnit.INSTANCE.getTotalCpuRate();
            log.availableMemory = SystemStatusUnit.INSTANCE.getAvailableMemory(context);
            dao.insert(log);
            return Result.success();
        } catch (Exception e) {
            return Result.failure();
        }
    }
}
