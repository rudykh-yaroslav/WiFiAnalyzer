package com.vrem.wifianalyzer.network;

import android.os.Handler;
import android.os.Looper;

import com.vrem.wifianalyzer.MainContext;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WiFiAdminNetworkService {
    private static final String AUTH_TOKEN_HEADER = "AUTHORIZATION";
    private static final String AUTH_TOKEN_PREFIX = "Bearer ";
    private static final String BASE_URL = "http://172.30.12.123:8080"; //TODO: move to settings
    private static WiFiAdminNetworkService instance = new WiFiAdminNetworkService();
    //    private static final String BASE_URL = "https://wifi-admin.herokuapp.com:8080"; //TODO: move to settings
    private Retrofit retrofit;
    private Handler loginDialogHandler;

    private WiFiAdminNetworkService() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        loginDialogHandler = new Handler(Looper.getMainLooper());

        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(chain -> {
                    Request original = chain.request();
                    Request request = original.newBuilder()
                            .header(AUTH_TOKEN_HEADER, AUTH_TOKEN_PREFIX + MainContext.INSTANCE.getAuthTokenProvider().getAuthKey())
                            .method(original.method(), original.body())
                            .build();

                    Response response = chain.proceed(request);
                    if (response.code() == 401 || response.code() == 403) {
                        MainContext.INSTANCE.getAuthTokenProvider().resetUserData();
                        loginDialogHandler.post(() ->
                                MainContext.INSTANCE.getLoginDialogProvider().callLoginDialog());
                        return response;
                    }
                    return response;
                });

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build();
    }

    public static WiFiAdminNetworkService getInstance() {
        return instance;
    }

    public WiFiAdminApi wiFiAdminApi() {
        return retrofit.create(WiFiAdminApi.class);
    }
}