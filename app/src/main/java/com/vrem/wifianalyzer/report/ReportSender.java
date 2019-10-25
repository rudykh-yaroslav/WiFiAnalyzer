package com.vrem.wifianalyzer.report;

import android.location.Location;
import android.os.Build;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.vrem.wifianalyzer.MainContext;
import com.vrem.wifianalyzer.R;
import com.vrem.wifianalyzer.network.DadataNetworkService;
import com.vrem.wifianalyzer.network.WiFiAdminNetworkService;
import com.vrem.wifianalyzer.network.model.DadataRq;
import com.vrem.wifianalyzer.network.model.DadataRs;
import com.vrem.wifianalyzer.network.model.Suggestion;
import com.vrem.wifianalyzer.wifi.model.DeviceDetails;
import com.vrem.wifianalyzer.wifi.model.Report;
import com.vrem.wifianalyzer.wifi.model.WiFiData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReportSender {

    public ReportSender() {
    }

    public void send() {
        ProgressBar progressBar = MainContext.INSTANCE.getMainActivity().findViewById(R.id.progressBar);
        progressBar.setVisibility(ProgressBar.VISIBLE);

        WiFiData wiFiData = MainContext.INSTANCE.getScannerService().getWiFiData();
        Location location = MainContext.INSTANCE.getScannerService().getLocation();
        double lat = location != null ? location.getLatitude() : 0;
        double lon = location != null ? location.getLongitude() : 0;
        String login = MainContext.INSTANCE.getAuthTokenProvider().getLogin();
        DeviceDetails deviceDetails = new DeviceDetails(Build.MANUFACTURER, Build.MODEL, Build.VERSION.RELEASE);
        Report report = new Report(wiFiData, login, deviceDetails, lat, lon);

        if (lat != 0 && lon != 0) {
            DadataNetworkService
                    .getInstance()
                    .dadataApi()
                    .reverseGeocode(new DadataRq(lat, lon))
                    .enqueue(
                            new Callback<DadataRs>() {

                                @Override
                                public void onResponse(Call<DadataRs> call, Response<DadataRs> response) {
                                    if (response.isSuccessful()) {
                                        List<Suggestion> suggestions = response.body().getSuggestions();
                                        if (suggestions != null && suggestions.size() > 0) {
                                            report.setAddress(suggestions.get(0).getValue());
                                        }
                                    }
                                    doSend(report);
                                }

                                @Override
                                public void onFailure(Call<DadataRs> call, Throwable t) {
                                    doSend(report);
                                }
                            }
                    );
        }
    }

    private void doSend(Report report) {

        WiFiAdminNetworkService
                .getInstance()
                .wiFiAdminApi()
                .sendReport(report)
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
