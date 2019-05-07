package com.anmnight.commlibrary.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.anmnight.commlibrary.R;

class LoadingTextDialog extends Dialog {


    public LoadingTextDialog(@NonNull Context context) {
        super(context);
        init(context);
    }

    private ProgressBar mProgress;
    private Button mButton;
    private TextView mTextView;

    private void init(Context context) {

        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.dialog_loading_text_layout, null);

        mProgress = view.findViewById(R.id.progress_bar);
        mButton = view.findViewById(R.id.button);
        mTextView = view.findViewById(R.id.text);
        mTextView.setGravity(Gravity.CENTER);

        setCancelable(false);
        setContentView(view);

        int width = context.getApplicationContext().getResources().getDisplayMetrics().widthPixels;

        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = width;
        lp.height = width;
        getWindow().setAttributes(lp);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mButton.setOnClickListener((view) -> dismiss());
    }

    public void showLoading() {

        mTextView.setVisibility(View.GONE);
        mProgress.setVisibility(View.VISIBLE);
        mButton.setVisibility(View.GONE);

        if (!isShowing()) {
            show();
        }
    }

    public void showText(String message) {

        mTextView.setVisibility(View.VISIBLE);
        mProgress.setVisibility(View.GONE);
        mButton.setVisibility(View.VISIBLE);

        mTextView.setText(message);

        if (!isShowing()) {
            show();
        }
    }

}
