package com.vrem.wifianalyzer.network.model;

import java.sql.Time;
import java.sql.Timestamp;



public class SpeedtestDatastructureDownload {
    private String file;
    private Long timestamp;

    public SpeedtestDatastructureDownload(String file, Long timestamp) {
        this.file = file;
        this.timestamp = timestamp;
    }

    public int getFileSize() {
        return this.file.getBytes().length;
    }

    public Long getTimestamp() {
        return this.timestamp;
    }
}