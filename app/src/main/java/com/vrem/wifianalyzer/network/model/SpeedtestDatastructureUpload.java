package com.vrem.wifianalyzer.network.model;

public class SpeedtestDatastructureUpload {
    private Byte[] file;
    private Long timestamp;

    public SpeedtestDatastructureUpload(Byte[] file, Long timestamp) {
        this.file = file;
        this.timestamp = timestamp;
    }

    public int getFileSize() {
        return this.file.length;
    }

    public Long getTimestamp() {
        return this.timestamp;
    }
}