package com.anmnight.fastcoding.processor;

import com.google.auto.service.AutoService;

import javax.annotation.processing.Processor;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/12/24 17:27
 * anmnight@qq.com
 */
@AutoService(Processor.class)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes({"com.anmnight.fastcoding.annotation.RequestPermission"})
public class RequestPermissionProcessor {

    //todo aop permission
}
