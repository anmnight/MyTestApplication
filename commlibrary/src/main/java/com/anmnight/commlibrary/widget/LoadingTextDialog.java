package com.anmnight.commlibrary.widget;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;

public class LoadingTextDialog extends Dialog {
    public LoadingTextDialog(@NonNull Context context) {
        super(context);
        setCancelable(false);
    }

}
