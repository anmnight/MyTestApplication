package com.anmnight.commlibrary.http;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/8/14 16:35
 * anmnight@qq.com
 */
public class ServiceMethod<ReturnT> {

    public static ServiceMethod INSTANCE = ServiceMethodHolder.instance;

    static class ServiceMethodHolder {
        static ServiceMethod instance = new ServiceMethod();
    }

    /**
     * 真实执行方法
     *
     * @param url     地址
     * @param headers header
     * @param params  参数
     * @param type    请求类型
     * @return RestClient.Call<T>
     * @throws IOException exception
     */
    public OkHttpCall<ReturnT> invoke(String url, Map<String, String> headers, Map<String, Object> params, RestClient.RequestType type, final Method method) {

        Type returnType = method.getGenericReturnType();

        OkHttpCall<ReturnT> call;
        switch (type) {
            case GET:
                call = new OkHttpCall<>(HttpRequest.get(url, headers, params), returnType);
                break;
            case POST:
                call = new OkHttpCall<>(HttpRequest.post(url, headers, params), returnType);
                break;
            default:
                call = null;
                break;
        }

        return call;
    }


}
