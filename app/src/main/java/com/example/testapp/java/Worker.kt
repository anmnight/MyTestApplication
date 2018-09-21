package com.example.testapp.java

import android.util.Log
import java.util.*

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/9/20 17:24
 * anmnight@qq.com
 */
class Worker : Observer {
    /**
     * This method is called whenever the observed object is changed. An
     * application calls an <tt>Observable</tt> object's
     * `notifyObservers` method to have all the object's
     * observers notified of the change.
     *
     * @param   o     the observable object.
     * @param   arg   an argument passed to the `notifyObservers`
     * method.
     */
    override fun update(o: Observable?, arg: Any?) {

        Log.i("Worker", "update")
    }
}