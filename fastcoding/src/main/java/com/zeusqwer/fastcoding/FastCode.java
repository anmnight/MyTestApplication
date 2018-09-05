package com.zeusqwer.fastcoding;

/**
 * © 1908 anxiao Co.,Ltd All Rights Reserved.
 * 作者：anxiao on 2018/2/26 17:20
 * 邮箱：anxiao@bankcomm.com
 */

public abstract class FastCode {

    static void register(Object target) {

        Class targetClass = target.getClass();





    }

    interface Binder<T> {
        void bind(T activity);
    }
}
