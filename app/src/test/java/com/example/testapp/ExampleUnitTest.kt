package com.example.testapp

import org.junit.Test

import java.util.ArrayList
import java.util.Random

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class ExampleUnitTest {


    fun gsonTest(){
        val str = "chName=%E5%AE%89%E9%9C%84&phoneNum=18502938991&enName=AN%20XIAO&idNun=123123&startDate=20140111&endDate=20250111"


    }


    @Test
    @Throws(Exception::class)
    fun bubble_sort() {

        val list = ArrayList<Int>()

        var w = 0
        while (w < 20) {
            list.add(Random().nextInt(100))
            w++
        }

        val arr = list.toTypedArray()

        var old = ""
        for (i in arr) {
            old += i.toString() + ","
        }
        old = old.substring(0, old.length - 1)
        //small->big
        for (j in arr.indices) {
            for (k in 0 until arr.size - j - 1) {
                if (arr[k] > arr[k + 1]) {
                    val t = arr[k]
                    arr[k] = arr[k + 1]
                    arr[k + 1] = t
                }
            }
        }

        var now = ""
        for (i in arr) {
            now += i.toString() + ","
        }
        now = now.substring(0, now.length - 1)


    }

    @Test
    fun quick_sort() {
        val list = ArrayList<Int>()

        var w = 0
        while (w < 20) {
            list.add(Random().nextInt(100))
            w++
        }

        val arr = list.toTypedArray()

        var old = ""
        for (i in arr) {
            old += i.toString() + ","
        }
        old = old.substring(0, old.length - 1)


        //small->big
        // TODO: 2017/9/1 快速排序
        for (j in arr.indices) {
            for (k in 0 until arr.size - j - 1) {
                if (arr[k] > arr[k + 1]) {
                    val t = arr[k]
                    arr[k] = arr[k + 1]
                    arr[k + 1] = t
                }
            }
        }


        var now = ""
        for (i in arr) {
            now += i.toString() + ","
        }
        now = now.substring(0, now.length - 1)

    }


    @Test
    fun binarySearchTest() {

        val array = intArrayOf(1, 3, 5, 23, 71, 93, 131)

        val x = 71

        val index = binarySearch(array, x, 0, 6)


        System.out.println("find index is $index")

    }

    private fun binarySearch(array: IntArray, x: Int): Int {

        var low = 0
        var height = array.size - 1

        while (low <= height) {
            val middle = (height + low) / 2

            when {
                x == array[middle] -> return middle
                x > array[middle] -> low = middle + 1
                else -> height = middle - 1
            }
        }
        return -1
    }

    private fun binarySearch(array: IntArray, x: Int, beginIndex: Int, endIndex: Int): Int {

        val middle = (beginIndex + endIndex) / 2

        if (x < array[beginIndex] || x > array[endIndex] || beginIndex > endIndex) {
            return -1
        }
        return when {
            array[middle] == x -> middle

            x > array[middle] -> binarySearch(array, x, middle + 1, endIndex)

            else -> binarySearch(array, x, beginIndex, middle - 1)

        }
    }

    /**
     * 效率底下的斐波那契数列, 耗时的操作
     * @param num
     * @return
     */
    fun fibc(num: Int): Int {
        if (num == 0) {
            return 0
        }
        return if (num == 1) {
            1
        } else fibc(num - 1) + fibc(num - 2)
    }

}