package com.vrem.wifianalyzer.wifi.model;

public class DeviceDetails {
    private final String manufacturer;
    private final String model;
    private final String androidVersion;

    public DeviceDetails(String manufacturer, String model, String androidVersion) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.androidVersion = androidVersion;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    public String getAndroidVersion() {
        return androidVersion;
    }
}
