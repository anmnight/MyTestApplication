package com.anmnight.remoteprocess.unit

import android.app.ActivityManager
import android.content.Context
import android.os.BatteryManager

import java.io.BufferedReader
import java.io.FileInputStream
import java.io.FileReader
import java.io.IOException
import java.io.InputStreamReader

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/12/27 14:27
 * anmnight@qq.com
 */
object SystemStatusUnit {


    private var mActivityManager: ActivityManager? = null
    private var mBatteryManager: BatteryManager? = null

    val curProcessCpuRate: Float
        get() {
            val totalCpuTime1 = totalCpuTime.toFloat()
            val processCpuTime1 = appCpuTime.toFloat()
            try {
                Thread.sleep(360)
            } catch (e: Exception) {
            }

            val totalCpuTime2 = totalCpuTime.toFloat()
            val processCpuTime2 = appCpuTime.toFloat()
            return 100 * (processCpuTime2 - processCpuTime1) / (totalCpuTime2 - totalCpuTime1)
        }

    val totalCpuRate: Float
        get() {
            val totalCpuTime1 = totalCpuTime.toFloat()
            val totalUsedCpuTime1 = totalCpuTime1 - sStatus.idletime
            try {
                Thread.sleep(360)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

            val totalCpuTime2 = totalCpuTime.toFloat()
            val totalUsedCpuTime2 = totalCpuTime2 - sStatus.idletime
            return 100 * (totalUsedCpuTime2 - totalUsedCpuTime1) / (totalCpuTime2 - totalCpuTime1)
        }

    private val totalCpuTime: Long
        get() {
            var cpuInfos: Array<String>? = null
            try {
                val reader = BufferedReader(InputStreamReader(
                        FileInputStream("/proc/stat")), 1000)
                val load = reader.readLine()
                reader.close()
                cpuInfos = load.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            } catch (ex: IOException) {
                ex.printStackTrace()
            }

            sStatus.usertime = java.lang.Long.parseLong(cpuInfos!![2])
            sStatus.nicetime = java.lang.Long.parseLong(cpuInfos[3])
            sStatus.systemtime = java.lang.Long.parseLong(cpuInfos[4])
            sStatus.idletime = java.lang.Long.parseLong(cpuInfos[5])
            sStatus.iowaittime = java.lang.Long.parseLong(cpuInfos[6])
            sStatus.irqtime = java.lang.Long.parseLong(cpuInfos[7])
            sStatus.softirqtime = java.lang.Long.parseLong(cpuInfos[8])
            return sStatus.totalTime
        }

    private val appCpuTime: Long
        get() {
            var cpuInfos: Array<String>? = null
            try {
                val pid = android.os.Process.myPid()
                val reader = BufferedReader(InputStreamReader(
                        FileInputStream("/proc/$pid/stat")), 1000)
                val load = reader.readLine()
                reader.close()
                cpuInfos = load.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            } catch (ex: IOException) {
                ex.printStackTrace()
            }

            return (java.lang.Long.parseLong(cpuInfos!![13])
                    + java.lang.Long.parseLong(cpuInfos[14]) + java.lang.Long.parseLong(cpuInfos[15])
                    + java.lang.Long.parseLong(cpuInfos[16]))
        }


    private var sStatus = Status()

    @Synchronized
    private fun getActivityManager(context: Context): ActivityManager {
        if (mActivityManager == null) {
            mActivityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        }
        return mActivityManager as ActivityManager
    }

    @Synchronized
    private fun getBatteryManager(context: Context): BatteryManager {
        if (mBatteryManager == null) {
            mBatteryManager = context.getSystemService(Context.BATTERY_SERVICE) as BatteryManager
        }
        return mBatteryManager as BatteryManager
    }

    fun currentButtery(context: Context): Long {

        return getBatteryManager(context).getIntProperty(BatteryManager.BATTERY_PROPERTY_CURRENT_NOW).toLong()
    }

    fun getAvailableMemory(context: Context): Long {

        val mi = ActivityManager.MemoryInfo()
        getActivityManager(context).getMemoryInfo(mi)
        return mi.availMem
    }

    internal class Status {
        var usertime: Long = 0
        var nicetime: Long = 0
        var systemtime: Long = 0
        var idletime: Long = 0
        var iowaittime: Long = 0
        var irqtime: Long = 0
        var softirqtime: Long = 0

        val totalTime: Long
            get() = (usertime + nicetime + systemtime + idletime + iowaittime
                    + irqtime + softirqtime)
    }
}
