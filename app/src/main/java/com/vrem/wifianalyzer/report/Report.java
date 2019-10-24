package com.vrem.wifianalyzer.report;

import android.widget.ProgressBar;
import android.widget.Toast;

import com.vrem.wifianalyzer.MainContext;
import com.vrem.wifianalyzer.R;
import com.vrem.wifianalyzer.network.DadataNetworkService;
import com.vrem.wifianalyzer.network.WiFiAdminNetworkService;
import com.vrem.wifianalyzer.network.model.DadataRq;
import com.vrem.wifianalyzer.network.model.DadataRs;
import com.vrem.wifianalyzer.network.model.Suggestion;
import com.vrem.wifianalyzer.wifi.model.WiFiData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Report {
    private final WiFiData wiFiData;

    public Report(WiFiData wiFiData) {
        this.wiFiData = wiFiData;
    }

    public void send() {
        ProgressBar progressBar = MainContext.INSTANCE.getMainActivity().findViewById(R.id.progressBar);
        progressBar.setVisibility(ProgressBar.VISIBLE);

        wiFiData.setLogin(MainContext.INSTANCE.getAuthTokenProvider().getLogin());

        if (wiFiData.getLat() != 0 && wiFiData.getLon() != 0) {
            DadataNetworkService
                    .getInstance()
                    .dadataApi()
                    .reverseGeocode(new DadataRq(wiFiData.getLat(), wiFiData.getLon()))
                    .enqueue(
                            new Callback<DadataRs>() {

                                @Override
                                public void onResponse(Call<DadataRs> call, Response<DadataRs> response) {
                                    if (response.isSuccessful()) {
                                        List<Suggestion> suggestions = response.body().getSuggestions();
                                        if (suggestions != null && suggestions.size() > 0) {
                                            wiFiData.setAddress(suggestions.get(0).getValue());
                                        }
                                    }
                                    doSend();
                                }

                                @Override
                                public void onFailure(Call<DadataRs> call, Throwable t) {
                                    doSend();
                                }
                            }
                    );
        }
    }

    private void doSend() {

        WiFiAdminNetworkService
                .getInstance()
                .wiFiAdminApi()
                .sendReport(wiFiData)
                .enqueue(
                        new Callback<Void>() {

                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                ProgressBar progressBar = MainContext.INSTANCE.getMainActivity().findViewById(R.id.progressBar);
                                progressBar.setVisibility(ProgressBar.INVISIBLE);

                                if (response.isSuccessful()) {
                                    Toast toast = Toast.makeText(MainContext.INSTANCE.getContext(),
                                            R.string.report_upload_success, Toast.LENGTH_LONG);
                                    toast.show();
                                } else {
                                    Toast toast = Toast.makeText(MainContext.INSTANCE.getContext(),
                                            R.string.report_upload_failure, Toast.LENGTH_LONG);
                                    toast.show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                ProgressBar progressBar = MainContext.INSTANCE.getMainActivity().findViewById(R.id.progressBar);
                                progressBar.setVisibility(ProgressBar.INVISIBLE);

                                Toast toast = Toast.makeText(MainContext.INSTANCE.getContext(),
                                        R.string.report_upload_failure, Toast.LENGTH_LONG);
                                toast.show();
                            }
                        });
    }
}
