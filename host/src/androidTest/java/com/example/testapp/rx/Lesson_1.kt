package com.example.testapp.rx

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.test.runner.AndroidJUnit4
import com.example.testapp.images

import org.junit.Test
import org.junit.runner.RunWith
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription

import io.reactivex.Flowable
import io.reactivex.FlowableSubscriber
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function
import io.reactivex.internal.util.QueueDrainHelper.request
import io.reactivex.schedulers.Schedulers
import java.net.URI
import java.net.URL

@RunWith(AndroidJUnit4::class)
class Lesson_1 {

    @Test
    fun test() {
        Observable.just("aaa", "bbb", "ccc")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<String> {
                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(s: String) {
                        println(s)
                    }

                    override fun onError(e: Throwable) {

                    }

                    override fun onComplete() {

                    }
                })
    }


    @Test
    fun test2() {





    }


    private fun downloadBitmap(path: String): Bitmap {
        val connection = URL(path).openConnection()
        connection.connectTimeout=10000
        return BitmapFactory.decodeStream(connection.getInputStream())
    }
}
