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

    /**
     * timeOut connectionTimeOut is default set
     */
    private static OkHttpClient mHttpClient = new OkHttpClient();
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static String mBaseUrl;

    public static Call get(String url, Map<String, String> headers, Map<String, String> params) {

        Headers headerBuild = Headers.of(headers);

        String path = absolutePath(url);

        StringBuilder str = new StringBuilder(path);
        str.append("?");
        for (Map.Entry<String, String> entry : params.entrySet()) {
            str.append(entry.getKey());
            str.append("=");
            str.append(entry.getValue());
            str.append("&");
        }
        str.substring(str.length() - 1, str.length());

        System.out.print(str);

        Request request = new Request.Builder()
                .headers(headerBuild)
                .url(str.toString())
                .get()
                .build();

        return mHttpClient.newCall(request);
    }


    public static Call post(String url, Map<String, String> header, Map<String, String> params) {
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
