package com.anmnight.commlibrary.http;

import com.anmnight.commlibrary.http.core.Body;
import com.anmnight.commlibrary.http.core.Get;
import com.anmnight.commlibrary.http.core.Headers;
import com.anmnight.commlibrary.http.core.Params;
import com.anmnight.commlibrary.http.core.Post;
import com.google.gson.Gson;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;


/**
 * https://github.com/anmnight
 * author：anxiao on 2018/8/13 15:19
 * anmnight@qq.com
 */
public class RestClient {

    private RequestType mRequestType;
    private static String mBaseUrl;
    private String mPath;
    private Map<String, String> mHeaders = new HashMap<>();
    private Map<String, Object> mBody = new HashMap<>();

    public static final class Builder {

        public Builder() {

        }


        public Builder baseUrl(String baseUrl) {
            if (baseUrl == null) {
                throw new NullPointerException("baseUrl mast not be null");
            }
            RestClient.mBaseUrl = baseUrl;
            HttpRequest.setmBaseUrl(baseUrl);
            return this;
        }

        public RestClient build() {
            return new RestClient();
        }


    }


    enum RequestType {
        GET, POST
    }

    /**
     * 注册代理
     *
     * @param clazz 被代理类
     * @param <T>   返回类型
     * @return 代理类
     */
    @SuppressWarnings("unchecked")
    public <T> T create(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(
                clazz.getClassLoader(),
                new Class[]{clazz},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        performParser(proxy, method, args);
                        return loadServiceMethod().invoke(mPath, mHeaders, mBody, mRequestType, method);
                    }
                });
    }


    /**
     * 注解解析
     * perform ApiService
     *
     * @param proxy  proxy
     * @param method method
     * @param args   args
     * @throws ClassNotFoundException exception
     */
    @SuppressWarnings("unchecked")
    private void performParser(Object proxy, Method method, Object[] args) throws NoSuchMethodException {

        if (mBaseUrl == null) {
            throw new NullPointerException("baseUrl must not be null");
        }

        Annotation[] annotations = method.getAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation instanceof Post) {
                mRequestType = RequestType.POST;
                mPath = ((Post) annotation).value();
            }

            if (annotation instanceof Get) {
                mRequestType = RequestType.GET;
                mPath = ((Get) annotation).value();
            }

            if (annotation instanceof Headers) {
                String[] values = ((Headers) annotation).value();
                for (String header : values) {
                    int colon = header.indexOf(':');
                    if (colon == -1 || colon == 0 || colon == header.length() - 1) {
                        throw new NoSuchMethodException("@Headers value must be in the form \"Name: Value\". Found: \"%s\"");
                    }
                    String[] temp = header.split(":");
                    mHeaders.put(temp[0], temp[1]);
                }
            }

        }

        Annotation[][] paramsAnnotations = method.getParameterAnnotations();
        for (int j = 0; j < paramsAnnotations.length; j++) {

            Annotation[] parameterAnnotation = paramsAnnotations[j];

            for (Annotation annotation : parameterAnnotation) {
                if (annotation instanceof Params) {
                    Params params = (Params) annotation;
                    mBody.put(params.value(), args[j]);
                }

                if (annotation instanceof Body) {
                    Object obj = args[j];

                    Gson gson = new Gson();
                    Map map = new HashMap<String, Object>();
                    String str = gson.toJson(obj);

                    map = gson.fromJson(str, map.getClass());

                    mBody.putAll(map);
                }
            }
        }

    }


    /**
     * 代理真实业务
     */
    private ServiceMethod<?> loadServiceMethod() {
        return ServiceMethod.INSTANCE;
    }


}
