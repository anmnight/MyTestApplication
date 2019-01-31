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



            Element enclosingElement = element.getEnclosingElement();
            Name enclosingElementName = enclosingElement.getSimpleName();


            String clsName = enclosingElementName.toString() + "_Proxy";


            String fieldName = element.getSimpleName().toString();



            int requestCode = element.getAnnotation(NeedPermission.class).requestCode();
            String[] permissions = element.getAnnotation(NeedPermission.class).permissions();

            PackageElement packageName = mElementUtils.getPackageOf(element);

            try {

                JavaFileObject javaFileObject = mFiler.createSourceFile(packageName + "." + clsName);
                Writer writer = javaFileObject.openWriter();

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
