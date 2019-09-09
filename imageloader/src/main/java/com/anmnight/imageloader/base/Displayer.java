package com.anmnight.imageloader.base;

import android.graphics.Bitmap;
import java.io.IOException;

public interface Displayer {

    //预处理bitmap
    public Bitmap pretreatmentBitmap(byte[] stream) throws IOException;

    //显示bitmap
    public void display(Bitmap bitmap);
}
