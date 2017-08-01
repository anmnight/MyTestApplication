package http;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by anxiao on 2017/8/1.
 * 基本网络请求
 */

public class RestClient {
    //    private static String baseURl = "https://mhms.homeinns.com/";
    private static String baseURl = "http://app.cainiaoshaocai.com/";

    private static Retrofit retrofit = new Retrofit.Builder().baseUrl(baseURl)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static RestApi SERVICES() {
        return retrofit.create(RestApi.class);
    }
}
