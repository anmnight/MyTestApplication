package http;

import javax.inject.Inject;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {

    @Inject
    RestClient(){

    }

    private String baseURl = "https://hms.homeinns.com/";

    private Retrofit retrofit = new Retrofit.Builder().baseUrl(baseURl)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public <T> T request(Class<T> api) {
        return retrofit.create(api);
    }
}
