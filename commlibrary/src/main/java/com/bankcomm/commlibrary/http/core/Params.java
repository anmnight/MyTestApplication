package com.bankcomm.commlibrary.http.core;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/8/13 13:53
 * anmnight@qq.com
 */

/**
 * void login(@Params("key") String value)
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface Params {
    String value();
}
