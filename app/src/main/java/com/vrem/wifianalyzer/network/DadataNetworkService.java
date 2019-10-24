package com.vrem.wifianalyzer.network;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DadataNetworkService {
    private static final String AUTH_TOKEN_HEADER = "Authorization";
    private static final String AUTH_TOKEN = "Token ab9ed7d11fea794781d23d488b8a32d20c281403";
    private static final String BASE_URL = "https://suggestions.dadata.ru/suggestions/";
    private static DadataNetworkService instance = new DadataNetworkService();
    private Retrofit retrofit;

    private DadataNetworkService() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(chain -> {
                    Request original = chain.request();
                    Request request = original.newBuilder()
                            .header(AUTH_TOKEN_HEADER, AUTH_TOKEN)
                            .header("Content-Type", "application/json")
                            .header("Accept", "application/json")
                            .method(original.method(), original.body())
                            .build();
                    return chain.proceed(request);
                });

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build();
    }

    public static DadataNetworkService getInstance() {
        return instance;
    }

    public DadataApi dadataApi() {
        return retrofit.create(DadataApi.class);
    }
}
