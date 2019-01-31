package com.anmnight.commlibrary.unit;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.util.List;

public class PathManager {

    private static PathManager sInstance;

    public static PathManager getInstance(Context app) {
        if (sInstance == null) {
            synchronized (PathManager.class) {
                if (sInstance == null) {
                    sInstance = new PathManager(app);
                }
            }
        }
        return sInstance;
    }

    private File mRootDir;

    private PathManager(Context app) {
        if (storageAvailable()) {
            mRootDir = app.getExternalFilesDir("/");
        } else {
            mRootDir = app.getFilesDir();
        }
        mRootDir = new File(mRootDir, "/");
        createFolder(mRootDir);
    }

    public String getRootDir() {
        return mRootDir.getAbsolutePath();
    }

    public String getWebDir() {
        return new File(mRootDir, "website").getAbsolutePath();
    }

    public String getImageDir() {
        return new File(mRootDir, "imgs").getAbsolutePath();
    }

    public File[] loadFilesAtDir(String dir) {
        return new File(dir).listFiles();
    }

    private boolean storageAvailable() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File sd = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
            return sd.canWrite();
        } else {
            return false;
        }
    }

    private boolean createFolder(File targetFolder) {
        if (targetFolder.exists()) {
            if (targetFolder.isDirectory()) return true;
            //noinspection ResultOfMethodCallIgnored
            targetFolder.delete();
        }
        return targetFolder.mkdirs();
    }
}