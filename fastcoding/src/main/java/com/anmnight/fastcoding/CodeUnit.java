package com.anmnight.fastcoding;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;
import javax.lang.model.element.Name;
import javax.lang.model.element.PackageElement;

import static javax.lang.model.element.Modifier.*;


/**
 * https://github.com/anmnight
 * author：anxiao on 2018/12/25 10:53
 * anmnight@qq.com
 */
public class CodeUnit {

    public static String generateNeed(Name enclosingElementName, String clsName, String fieldName, PackageElement packageName, int requestCode, String[] permissions) {


        ClassName activity = ClassName.get(packageName.toString(), enclosingElementName.toString());

        String str = "";
        for (String temp : permissions) {
            str += "\"" + temp + "\"";
            str += ",";
        }

        FieldSpec fields = FieldSpec.builder(activity, "mTarget")
                .addModifiers(PRIVATE)
                .build();

        MethodSpec constructor = MethodSpec.constructorBuilder()
                .addModifiers(PUBLIC)
                .addParameter(activity, "target")
                .addStatement("this.mTarget = target")
                .build();

        MethodSpec requestPermission = MethodSpec.methodBuilder("request")
                .addModifiers(PUBLIC)
                .addStatement("mTarget.requestPermissions(new String[]{" + str + "},$L)", requestCode)
                .build();

        TypeSpec typeSpec = TypeSpec.classBuilder(clsName)
                .addMethod(constructor)
                .addMethod(requestPermission)
                .addField(fields)
                .build();


        return JavaFile.builder(packageName.toString(), typeSpec).build().toString();
    }


}
