package com.example.testapp.android.classloader

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/9/10 10:18
 * anmnight@qq.com
 */
class TestClassLoader : ClassLoader() {

    override fun findClass(name: String?): Class<*> {
        return super.findClass(name)
    }

}