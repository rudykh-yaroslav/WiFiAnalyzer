package com.vrem.wifianalyzer.network.model;

public class DadataRq {
    private final double lat;
    private final double lon;
    private final int count;

    public DadataRq(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
        this.count = 1;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }
}
