package com.anmnight.fastcoding.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/12/25 09:38
 * anmnight@qq.com
 */

@Retention(RetentionPolicy.CLASS)
@Target({ElementType.METHOD})
public @interface PermissionCanceled {
}
