package com.bankcomm.commlibrary;

import com.bankcomm.commlibrary.http.Call;
import com.bankcomm.commlibrary.http.Response;
import com.bankcomm.commlibrary.http.RestClient;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void httpAnnotationTest() {


        try {


//            Map<String, String> headers = new HashMap<>();
//            headers.put("testKey", "testValuel");
//
//            okhttp3.Response response = HttpRequest.get("http://ip.taobao.com/service/getIpInfo.php?ip=21.22.11.3", headers, null).execute();
//
//            System.out.println(response.body().string());

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