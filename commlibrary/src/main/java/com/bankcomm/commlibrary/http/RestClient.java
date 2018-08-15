package com.bankcomm.commlibrary.http;

import com.bankcomm.commlibrary.http.core.Get;
import com.bankcomm.commlibrary.http.core.Headers;
import com.bankcomm.commlibrary.http.core.Params;
import com.bankcomm.commlibrary.http.core.Post;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
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
    private Type mReturnType;
    private Map<String, String> mHeaders = new HashMap<>();
    private Map<String, String> mBody = new HashMap<>();


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
        for (Annotation[] parameterAnnotation : paramsAnnotations) {
            int len = parameterAnnotation.length;
            for (int i = 0; i < len; i++) {
                Annotation annotation = parameterAnnotation[i];
                if (annotation instanceof Params) {
                    Params params = (Params) annotation;
                    mBody.put(params.value(), (String) args[i]);
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
