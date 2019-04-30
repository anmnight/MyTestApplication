package com.example.testapp.android.classloader;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;

import dalvik.system.DexFile;

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/9/10 10:18
 * anmnight@qq.com
 */
public class DexHelperUnit {

    private static final String EXTRACTED_NAME_EXT = ".classes";
    private static final String PREFS_FILE = "multidex.version";
    private static final String KEY_DEX_NUMBER = "dex.number";
    private static final String CODE_CACHE_SECONDARY_DIRECTORY = "code_cache/secondary-dexes";
    private static final String EXTRACTED_SUFFIX = ".zip";
    private static final String COMPONENT_PACKAGE_NAME = "com.example.testapp.enteries";

    private String tag = "DexHelperUnit";

    public List<Class> enterActivities(Context context) {
        List<String> paths = getDexFilePaths(context);
        List<Class> classes = new ArrayList<>();

        for (String path : paths) {

            DexFile dexfile = null;
            try {
                if (path.endsWith(EXTRACTED_SUFFIX)) {
                    dexfile = DexFile.loadDex(path, path + ".tmp", 0);
                } else {
                    dexfile = new DexFile(path);
                }

                Enumeration<String> dexEntries = dexfile.entries();

                while (dexEntries.hasMoreElements()) {

                    String className = dexEntries.nextElement();

                    if (className.startsWith(COMPONENT_PACKAGE_NAME)) {
                        classes.add(Class.forName(className));
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    Objects.requireNonNull(dexfile).close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return classes;
    }


    private List<String> getDexFilePaths(Context context) {

        ApplicationInfo applicationInfo = context.getApplicationInfo();
        File sourceApk = new File(applicationInfo.sourceDir);

        List<String> sourcePaths = new ArrayList<>();
        sourcePaths.add(applicationInfo.sourceDir);

        String extractedFilePrefix = sourceApk.getName() + EXTRACTED_NAME_EXT;
        int totalDexNumber = getMultiDexPreferences(context).getInt(KEY_DEX_NUMBER, 1);
        File dexDir = new File(applicationInfo.dataDir, CODE_CACHE_SECONDARY_DIRECTORY);

        for (int secondaryNumber = 2; secondaryNumber <= totalDexNumber; secondaryNumber++) {
            String fileName = extractedFilePrefix + secondaryNumber + EXTRACTED_SUFFIX;
            File extractedFile = new File(dexDir, fileName);
            if (extractedFile.isFile()) {
                sourcePaths.add(extractedFile.getAbsolutePath());
            }
        }

        return sourcePaths;
    }

    private static SharedPreferences getMultiDexPreferences(Context context) {
        return context.getSharedPreferences(PREFS_FILE,
                Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB ? Context.MODE_PRIVATE : Context.MODE_MULTI_PROCESS);
    }

}