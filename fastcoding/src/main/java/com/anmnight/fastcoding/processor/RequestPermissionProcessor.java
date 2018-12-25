package com.anmnight.fastcoding.processor;

import com.anmnight.fastcoding.CodeUnit;
import com.anmnight.fastcoding.annotation.NeedPermission;
import com.google.auto.service.AutoService;

import java.io.IOException;
import java.io.Writer;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Name;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.JavaFileObject;

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/12/24 17:27
 * anmnight@qq.com
 */
@AutoService(Processor.class)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes({
        "com.anmnight.fastcoding.annotation.NeedPermission"})
public class RequestPermissionProcessor extends AbstractProcessor {


    private Elements mElementUtils;
    private Filer mFiler;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {

        mElementUtils = processingEnvironment.getElementUtils();
        mFiler = processingEnvironment.getFiler();

    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {


        for (Element element : roundEnvironment.getElementsAnnotatedWith(NeedPermission.class)) {


            // 获取注解所在类的 Element
            Element enclosingElement = element.getEnclosingElement();
            // 获取注解所在类的 Name
            Name enclosingElementName = enclosingElement.getSimpleName();

            // 生成类的名字
            String clsName = enclosingElementName.toString() + "_Proxy";

            // 获取注解传入数据
            String fieldName = element.getSimpleName().toString();



            int requestCode = element.getAnnotation(NeedPermission.class).requestCode();
            String[] permissions = element.getAnnotation(NeedPermission.class).permissions();

            PackageElement packageName = mElementUtils.getPackageOf(element);

            try {
                // 创建文件
                JavaFileObject javaFileObject = mFiler.createSourceFile(packageName + "." + clsName);
                Writer writer = javaFileObject.openWriter();
                // 把内容写入到文件中
                writer.write(CodeUnit.generateNeed(enclosingElementName, clsName, fieldName, packageName, requestCode, permissions));
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }


        return true;
    }
}
