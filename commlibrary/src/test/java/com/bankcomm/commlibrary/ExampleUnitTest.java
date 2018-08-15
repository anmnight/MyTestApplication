package com.bankcomm.commlibrary;

import com.bankcomm.commlibrary.http.HttpRequest;
import com.bankcomm.commlibrary.http.Response;
import com.bankcomm.commlibrary.http.RestClient;
import com.google.gson.Gson;

import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Type;
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
                    .url("http://douapi.zeusqwer.top/version/all")
                    .get()
                    .addHeader("testkey", "testvalue")
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
            okhttp3.Response response = HttpRequest.get("http://douapi.zeusqwer.top/version/all", headers, null).execute();

            System.out.println(response.body());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }


    @Test
    public void httpAnnotationTest() {

        try {
            RestClient client = new RestClient.Builder()
                    .baseUrl("http://douapi.zeusqwer.top")
                    .build();

            TestApi api = client.create(TestApi.class);

            Response<VersionResult> versions = api.getVersion().execute();

            System.out.println(versions.body().getMessage());


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }


    @Test
    public void gsonTest() {

        Foo foo = new Foo();
        foo.age = 20;
        foo.name = "asdf";

        String jsonStr = new Gson().toJson(foo);

        System.out.println("json str : " + jsonStr);

        Foo tFoo = parseT(jsonStr, Foo.class);

        System.out.println("name : " + tFoo.getName());

    }


    private <T> T parseT(String jsonStr, Type type) {

        return new Gson().fromJson(jsonStr, type);
    }

    class Foo {
        String name;
        int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}