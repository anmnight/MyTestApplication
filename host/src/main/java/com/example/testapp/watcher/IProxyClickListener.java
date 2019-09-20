package com.example.testapp.watcher;

import android.view.View;

public interface IProxyClickListener {

    boolean onProxyClick(WrapClickListener wrap, View view);

    class WrapClickListener implements View.OnClickListener {

        IProxyClickListener mProxyListener;
        View.OnClickListener mBaseListener;

        WrapClickListener(View.OnClickListener l, IProxyClickListener proxyListener) {
            mBaseListener = l;
            mProxyListener = proxyListener;
        }

        @Override
        public void onClick(View v) {
            boolean handled = mProxyListener != null && mProxyListener.onProxyClick(WrapClickListener.this, v);
            if (!handled && mBaseListener != null) {
                mBaseListener.onClick(v);
            }
        }
    }
}
