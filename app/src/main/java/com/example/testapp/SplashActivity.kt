package com.example.testapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import com.anmnight.fastcoding.annotation.NeedPermission
import com.anmnight.fastcoding.annotation.PermissionCanceled
import com.anmnight.fastcoding.annotation.PermissionDenied

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)




        startActivity(Intent(this, MainActivity::class.java))



        finish()


//        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 200)


    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)


        if (requestCode == 200) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                Toast.makeText(this, "PERMISSION_GRANTED", Toast.LENGTH_SHORT).show()

            }


            if (grantResults[0] == PackageManager.PERMISSION_DENIED) {

                Toast.makeText(this, "PERMISSION_DENIED", Toast.LENGTH_SHORT).show()

            }

        }
    }

    @NeedPermission(permissions = arrayOf(Manifest.permission.CAMERA), requestCode = 200)
    fun need(dialog: AlertDialog) {

        //alert dialog

        // sure request

        // else cancel

    }


    @PermissionCanceled
    fun cancel() {
        Toast.makeText(this, "PermissionCanceled", Toast.LENGTH_SHORT).show()
    }


    @PermissionDenied
    fun denied() {
        Toast.makeText(this, "PermissionDenied", Toast.LENGTH_SHORT).show()
    }


}
