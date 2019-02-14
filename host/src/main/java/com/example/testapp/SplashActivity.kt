package com.example.testapp

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.testapp.widget.PermissionDialog
import java.util.*

class SplashActivity : Activity(), PermissionDialog.OkListener {

    private val tag = "SplashActivity"
    private val permissionCode = 200


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


//        startActivity(Intent(this, MainActivity::class.java))

//        finish()


        val permissions = checkPermissions()

        ActivityCompat.requestPermissions(this, permissions, permissionCode)


//        ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_SMS)

//        val mPermissionDialog = PermissionDialog(this, this)
//
//        mPermissionDialog.show()


    }


    private fun checkPermissions(): Array<String> {

        val permissions = arrayOf(
                Manifest.permission.READ_SMS,
                Manifest.permission.INSTALL_SHORTCUT,
                Manifest.permission.MODIFY_AUDIO_SETTINGS)

        val deniedPermission = arrayListOf<String>()

        for (value in permissions) {

            val isGranted = ContextCompat.checkSelfPermission(this, value)

            if (isGranted == PackageManager.PERMISSION_DENIED) {
                deniedPermission.add(value)
            }

        }

        val array = arrayOfNulls<String>(deniedPermission.size)

        return deniedPermission.toArray(array)
    }


    override fun onOk() {


    }


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == permissionCode) {

            for ((index, value) in grantResults.withIndex()) {
                val isTip = ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[index])
                if (value != PackageManager.PERMISSION_GRANTED) {
                    if (isTip) {
                        requestPermissions(permissions, permissionCode)
                    } else {
                        Log.w(tag, "请去设置开启权限")
                    }
                }
            }
        }
    }


}
