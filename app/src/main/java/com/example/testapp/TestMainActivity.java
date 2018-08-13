package com.example.testapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.bankcomm.commlibrary.http.Call;
import com.bankcomm.commlibrary.http.Response;
import com.bankcomm.commlibrary.http.RestClient;
import com.example.testapp.test.IpResult;
import com.example.testapp.test.TestApi;

import java.io.IOException;

public class TestMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_main);


        RestClient client = new RestClient.Builder()
                .baseUrl("http://ip.taobao.com/")
                .build();


        TestApi api = client.create(TestApi.class);
        final Call<IpResult> call = api.getData("21.22.11.3");

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response<IpResult> response = call.execute();
                    IpResult result = response.body();
                    Log.d("Main", result.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }
}
