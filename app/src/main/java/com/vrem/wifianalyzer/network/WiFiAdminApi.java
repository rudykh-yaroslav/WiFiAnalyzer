package com.vrem.wifianalyzer.network;

import com.vrem.wifianalyzer.auth.LoginData;
import com.vrem.wifianalyzer.auth.UserData;
import com.vrem.wifianalyzer.wifi.model.Report;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface WiFiAdminApi {
    @POST("/api/v1/report")
    Call<Void> sendReport(@Body Report report);

    @POST("/api/v1/login")
    Call<UserData> login(@Body LoginData loginData);
}
