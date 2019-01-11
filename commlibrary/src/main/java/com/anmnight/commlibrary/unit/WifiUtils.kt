package com.anmnight.commlibrary.unit

import android.net.wifi.WifiConfiguration
import android.net.wifi.WifiManager
import android.util.Log

import java.lang.reflect.InvocationTargetException

object WifiUtils {

    private const val TAG = "WifiUtils"
    private const val APName = "SELF_APPLY"
    private const val APPassword = "12345666"

    fun createHotspot(wifiManager: WifiManager): Boolean {
        var request: Boolean
        //开启热点
        if (wifiManager.isWifiEnabled) {
            //如果wifi处于打开状态，则关闭wifi,
            wifiManager.isWifiEnabled = false
        }
        val config = WifiConfiguration()
        config.SSID = APName
        config.preSharedKey = APPassword
        config.hiddenSSID = false//是否隐藏网络
        config.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN)//开放系统认证
        config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP)
        config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK)
        config.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP)
        config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP)
        config.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP)
        config.status = WifiConfiguration.Status.ENABLED
        //通过反射调用设置热点
        try {
            val method = wifiManager.javaClass.getMethod("setWifiApEnabled", WifiConfiguration::class.java, java.lang.Boolean.TYPE)
            val enable = method.invoke(wifiManager, config, true) as Boolean
            if (enable) {
                request = true
            } else {
                request = false
                Log.e(TAG, "创建失败")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e(TAG, "创建失败")
            //TODO:开启失败 反馈日志
            request = false
        }

        return request
    }


    /**
     * 关闭热点,并开启wifi
     */
    @Throws(NoSuchMethodException::class, InvocationTargetException::class, IllegalAccessException::class)
    fun closeWifiHotspot(wifiManager: WifiManager) {
        val method = wifiManager.javaClass.getMethod("getWifiApConfiguration")
        method.isAccessible = true
        val config = method.invoke(wifiManager) as WifiConfiguration
        val method2 = wifiManager.javaClass.getMethod("setWifiApEnabled", WifiConfiguration::class.java, Boolean::class.javaPrimitiveType)
        method2.invoke(wifiManager, config, false)
        //开启wifi
        wifiManager.isWifiEnabled = true
    }
}