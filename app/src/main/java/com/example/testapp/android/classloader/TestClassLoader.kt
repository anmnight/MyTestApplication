package com.example.testapp.android.classloader

import dalvik.system.DexClassLoader

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/9/10 10:18
 * anmnight@qq.com
 */
class TestClassLoader : DexClassLoader {

    constructor(dexPath: String?, optimizedDirectory: String?, librarySearchPath: String?, parent: ClassLoader?) : super(dexPath, optimizedDirectory, librarySearchPath, parent)


    override fun findClass(name: String?): Class<*> {


        return super.findClass(name)
    }

}