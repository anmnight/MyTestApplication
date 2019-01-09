package com.example.testapp.andserver;

import android.os.Environment;

import com.example.testapp.TestHomeApplication;
import com.yanzhenjie.andserver.util.IOUtils;

import java.io.File;

public class PathManager {

    private static PathManager sInstance;

    public static PathManager getInstance() {
        if (sInstance == null) {
            synchronized (PathManager.class) {
                if (sInstance == null) {
                    sInstance = new PathManager();
                }
            }
        }
        return sInstance;
    }

    private File mRootDir;

    private PathManager() {
        if (storageAvailable()) {
            mRootDir = TestHomeApplication.getInstance().getExternalFilesDir("/");
        } else {
            mRootDir = TestHomeApplication.getInstance().getFilesDir();
        }
        mRootDir = new File(mRootDir, "/");
        IOUtils.createFolder(mRootDir);
    }

    public String getRootDir() {
        return mRootDir.getAbsolutePath();
    }

    public String getWebDir() {
        return new File(mRootDir, "website").getAbsolutePath();
    }


    private static boolean storageAvailable() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File sd = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
            return sd.canWrite();
        } else {
            return false;
        }
    }
}