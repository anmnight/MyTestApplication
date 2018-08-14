package com.bankcomm.commlibrary;

import com.bankcomm.commlibrary.http.Call;
import com.bankcomm.commlibrary.http.HttpRequest;
import com.bankcomm.commlibrary.http.Response;
import com.bankcomm.commlibrary.http.RestClient;

import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {


    @Test
    public void okHttpTest() {

        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("http://ip.taobao.com/service/getIpInfo.php?ip=21.22.11.3")
                    .get()
                    .addHeader("testkey","testvalue")
                    .addHeader("Content-Type", "application/json")
                    .build();
            okhttp3.Response response = client.newCall(request).execute();

            System.out.println(response.body().string());

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void httpRequestTest() {

        try {
            Map<String, String> headers = new HashMap<>();
            headers.put("testkey", "testvaluel");
            headers.put("Content-Type", "application/json;charset=utf-8");
            okhttp3.Response response = HttpRequest.get("http://ip.taobao.com/service/getIpInfo.php?ip=21.22.11.3", headers, null).execute();

            System.out.println(response.body().string());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }


    @Test
    public void httpAnnotationTest() {

        try {

            RestClient client = new RestClient.Builder()
                    .baseUrl("http://ip.taobao.com/")
                    .build();
            TestApi api = client.create(TestApi.class);

            Call<IpResult> call = api.getData("21.22.11.3");
            Response<IpResult> response = call.execute();

            IpResult result = response.body();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}