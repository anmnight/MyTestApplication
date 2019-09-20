package com.example.testapp.watcher;

import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AbsListView;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Watcher {

    private String tag = "Watcher";
    private Method sHookMethod;
    private Field sHookField;
    private int mPrivateTagKey;
    private IProxyClickListener mListener;


    private Watcher(IProxyClickListener listener, int viewTagKey) {
        this.mListener = listener;
        this.mPrivateTagKey = viewTagKey;
    }

    private Watcher(IProxyClickListener listener) {
        this.mListener = listener;
        this.mPrivateTagKey = 999;
    }


    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private IProxyClickListener listener;

        public Builder setListener(IProxyClickListener ls) {
            this.listener = ls;
            return this;
        }

        public Watcher create() {
            return new Watcher(listener);
        }

        public Watcher create(int viewTagKey) {
            return new Watcher(listener, viewTagKey);
        }

    }

    public void watch(Window window) {

        init();
        View rootView = window.getDecorView().getRootView();
        rootView.getViewTreeObserver()
                .addOnGlobalLayoutListener(() -> hookViews(rootView, 0));
    }


    private void init() {
        if (sHookMethod == null) {
            try {
                Class viewClass = Class.forName("android.view.View");
                sHookMethod = viewClass.getDeclaredMethod("getListenerInfo");
                sHookMethod.setAccessible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (sHookField == null) {
            try {
                Class listenerInfoClass = Class.forName("android.view.View$ListenerInfo");
                sHookField = listenerInfoClass.getDeclaredField("mOnClickListener");
                sHookField.setAccessible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private void hookViews(View view, int recycledContainerDeep) {
        if (view.getVisibility() == View.VISIBLE) {
            boolean forceHook = recycledContainerDeep == 1;
            if (view instanceof ViewGroup) {
                boolean existAncestorRecycle = recycledContainerDeep > 0;
                ViewGroup p = (ViewGroup) view;
                if (!(p instanceof AbsListView) || existAncestorRecycle) {
                    hookClickListener(view, recycledContainerDeep, forceHook);
                    if (existAncestorRecycle) {
                        recycledContainerDeep++;
                    }
                } else {
                    recycledContainerDeep = 1;
                }
                int childCount = p.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View child = p.getChildAt(i);
                    hookViews(child, recycledContainerDeep);
                }
            } else {
                hookClickListener(view, recycledContainerDeep, forceHook);
            }
        }
    }


    private void hookClickListener(View view, int recycledContainerDeep, boolean forceHook) {
        boolean needHook = forceHook;
        if (!needHook) {
            needHook = view.isClickable();
            if (needHook && recycledContainerDeep == 0) {
                needHook = view.getTag(mPrivateTagKey) == null;
            }
        }
        if (needHook) {
            try {
                Object getListenerInfo = sHookMethod.invoke(view);
                View.OnClickListener baseClickListener = getListenerInfo == null ? null : (View.OnClickListener) sHookField.get(getListenerInfo);
                if ((baseClickListener != null && !(baseClickListener instanceof IProxyClickListener.WrapClickListener))) {
                    sHookField.set(getListenerInfo, new IProxyClickListener.WrapClickListener(baseClickListener, mListener));
                    view.setTag(mPrivateTagKey, recycledContainerDeep);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
