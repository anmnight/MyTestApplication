package com.anmnight.imageloader.cacher;

import android.content.Context;

import java.io.File;

public class PathHelper {

    public static File externalCacheDir(Context context) {
        return context.getExternalCacheDir();
    }
}
