package com.zeusqwer.fastcoding.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * © 1908 anxiao Co.,Ltd All Rights Reserved.
 * 作者：anxiao on 2018/2/9 10:53
 * 邮箱：anxiao@bankcomm.com
 */

@Retention(RetentionPolicy.CLASS)
@Target({ElementType.METHOD})
public @interface FastUiThread {
}


