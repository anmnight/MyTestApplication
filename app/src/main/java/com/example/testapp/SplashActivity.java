package com.example.testapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.anmnight.fastcoding.annotation.NeedPermission;
import com.anmnight.fastcoding.annotation.PermissionCanceled;
import com.anmnight.fastcoding.annotation.PermissionDenied;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 200);



    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


        if (requestCode == 200) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                Toast.makeText(this, "PERMISSION_GRANTED", Toast.LENGTH_SHORT).show();

            }


            if (grantResults[0] == PackageManager.PERMISSION_DENIED) {

                Toast.makeText(this, "PERMISSION_DENIED", Toast.LENGTH_SHORT).show();

            }

        }
    }

    @NeedPermission(permissions = {Manifest.permission.CAMERA}, requestCode = 200)
    public void need(AlertDialog dialog) {

        //alert dialog

        // sure request

        // else cancel

    }


    @PermissionCanceled
    public void cancel() {
        Toast.makeText(this, "PermissionCanceled", Toast.LENGTH_SHORT).show();
    }


    @PermissionDenied
    public void denied() {
        Toast.makeText(this, "PermissionDenied", Toast.LENGTH_SHORT).show();
    }


}
