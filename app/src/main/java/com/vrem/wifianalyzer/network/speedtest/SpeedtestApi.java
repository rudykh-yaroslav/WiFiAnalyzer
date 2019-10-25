package com.vrem.wifianalyzer.network.speedtest;

import com.vrem.wifianalyzer.network.model.SpeedtestDatastructure;

import java.sql.Timestamp;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface SpeedtestApi {
    @POST("/speedtest/upload")
    Call<Timestamp> upload(@Body SpeedtestDatastructure speedtestDatastructure);

    @POST("/speedtest/download")
    Call<SpeedtestDatastructure> download(@Query("timestamp") Timestamp timestamp);
}
