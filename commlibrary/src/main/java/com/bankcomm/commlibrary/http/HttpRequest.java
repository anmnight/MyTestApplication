package com.bankcomm.commlibrary.http;


import java.util.Map;

import okhttp3.Call;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/8/13 14:11
 * anmnight@qq.com
 */
public class HttpRequest {

    //todo header 添加问题
    //todo 网络请求配置

    /**
     * timeOut connectionTimeOut is default set
     */
    private static OkHttpClient mHttpClient = new OkHttpClient();
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static String mBaseUrl = "";

    public static Call get(String url, Map<String, String> headers, Map<String, Object> params) {

        StringBuilder str = new StringBuilder(absolutePath(url));
        str.append("?");
        if (params != null) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                str.append(entry.getKey());
                str.append("=");
                str.append(entry.getValue());
                str.append("&");
            }
        }

        String path = str.substring(0, str.length() - 1);

        Request.Builder builder = new Request.Builder();
        builder.url(path);


        if (headers != null) {
            Headers headerBuild = Headers.of(headers);
            builder.headers(headerBuild);
        }

        builder.addHeader("Content-Type", "application/json;charset=utf-8");
        builder.get();
        Request request = builder.build();

        return mHttpClient.newCall(request);
    }


    public static Call post(String url, Map<String, String> header, Map<String, Object> params) {
        Headers headerBuild = Headers.of(header);
        RequestBody bodyBuild = RequestBody.create(JSON, params.toString());
        Request request = new Request.Builder()
                .headers(headerBuild)
                .post(bodyBuild)
                .url(absolutePath(url))
                .build();

        return mHttpClient.newCall(request);
    }

    public static void setmBaseUrl(String url) {
        HttpRequest.mBaseUrl = url;
    }

    private static String absolutePath(String relativePath) {
        return mBaseUrl + relativePath;
    }
}
