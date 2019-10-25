package com.vrem.wifianalyzer.wifi.model;

public class Report {
    private final WiFiData wiFiData;
    private final String login;
    private final DeviceDetails deviceDetails;
    private final double lat;
    private final double lon;
    private String address;

    public Report(WiFiData wiFiData, String login, DeviceDetails deviceDetails, double lat, double lon) {
        this.wiFiData = wiFiData;
        this.login = login;
        this.deviceDetails = deviceDetails;
        this.lat = lat;
        this.lon = lon;
    }

    public WiFiData getWiFiData() {
        return wiFiData;
    }

    public String getLogin() {
        return login;
    }

    public DeviceDetails getDeviceDetails() {
        return deviceDetails;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
