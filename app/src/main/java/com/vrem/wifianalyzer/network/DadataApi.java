package com.vrem.wifianalyzer.network;

import com.vrem.wifianalyzer.network.model.DadataRq;
import com.vrem.wifianalyzer.network.model.DadataRs;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface DadataApi {
    @POST("api/4_1/rs/geolocate/address")
    Call<DadataRs> reverseGeocode(@Body DadataRq dadataRq);
}
