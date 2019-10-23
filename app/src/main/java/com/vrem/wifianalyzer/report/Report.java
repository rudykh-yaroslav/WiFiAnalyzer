package com.vrem.wifianalyzer.report;

import com.google.gson.Gson;
import com.vrem.wifianalyzer.wifi.model.WiFiData;

public class Report {
    private final WiFiData wiFiData;

    public Report(WiFiData wiFiData) {
        this.wiFiData = wiFiData;
    }

    public void send() {
        String reportJson = new Gson().toJson(wiFiData);
        System.out.println("!!! Resport: " + reportJson);
    }
}
