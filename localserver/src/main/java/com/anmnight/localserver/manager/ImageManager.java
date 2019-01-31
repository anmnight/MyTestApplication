package com.anmnight.localserver.manager;


import com.anmnight.commlibrary.unit.PathManager;

import java.io.File;

public class ImageManager {

    private PathManager mPathManager;
    private static ImageManager mImageManager;

    public ImageManager(PathManager manager) {

        this.mPathManager = manager;

    }


    public static ImageManager getInstance(PathManager manager) {
        if (mImageManager == null) {
            synchronized (ImageManager.class) {
                if (mImageManager == null) {
                    mImageManager = new ImageManager(manager);
                }
            }
        }
        return mImageManager;
    }


    public File[] images() {
        return new File(mPathManager.getImageDir()).listFiles();
    }

}
