package com.example.testapp.andserver.net;

import android.util.Log;

import com.yanzhenjie.andserver.annotation.Interceptor;
import com.yanzhenjie.andserver.framework.HandlerInterceptor;
import com.yanzhenjie.andserver.framework.handler.RequestHandler;
import com.yanzhenjie.andserver.http.HttpRequest;
import com.yanzhenjie.andserver.http.HttpResponse;

import java.util.Objects;

import androidx.annotation.NonNull;

@Interceptor
public class CrossInterceptor implements HandlerInterceptor {

    private String TAG = "CrossInterceptor";

    @Override
    public boolean onIntercept(@NonNull HttpRequest request, @NonNull HttpResponse response, @NonNull RequestHandler handler) {

        String origin = request.getHeader("Origin");

        Log.i(TAG, "origin : " + origin);

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,Authorization");
        response.setHeader("Access-Control-Allow-Credentials", "true");

        return false;
    }
}
