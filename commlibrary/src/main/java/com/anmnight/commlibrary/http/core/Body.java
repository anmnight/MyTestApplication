package com.anmnight.commlibrary.http.core;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/8/17 17:30
 * anmnight@qq.com
 */

/**
 * void test(@Body Object obj)
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface Body {
}
