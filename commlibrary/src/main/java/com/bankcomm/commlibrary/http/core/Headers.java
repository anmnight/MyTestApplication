package com.bankcomm.commlibrary.http.core;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/8/13 13:52
 * anmnight@qq.com
 */

/**
 * @Headers({"key1:value1","key2:value2"})
 * void mothod();
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Headers {
    String[] value();
}
