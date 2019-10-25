package com.vrem.wifianalyzer.network.speedtest;

import com.vrem.wifianalyzer.network.model.SpeedtestDatastructureDownload;
import com.vrem.wifianalyzer.network.model.SpeedtestDatastructureUpload;

import java.sql.Timestamp;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface SpeedtestApi {
    @POST("/speedtest/upload")
    Call<Long> upload(@Body SpeedtestDatastructureUpload speedtestDatastructure);

    @POST("/speedtest/download")
    Call<SpeedtestDatastructureDownload> download(@Query("timestamp") Long timestamp);
}
