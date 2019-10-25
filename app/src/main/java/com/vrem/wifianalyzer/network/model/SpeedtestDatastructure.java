package com.vrem.wifianalyzer.network.model;

import java.sql.Time;
import java.sql.Timestamp;

public class SpeedtestDatastructure {
    private Byte[] file;
    private Timestamp timestamp;

    public SpeedtestDatastructure(Byte[] file, Timestamp timestamp) {
        this.file = file;
        this.timestamp = timestamp;
    }

    public int getFileSize() {
        return this.file.length;
    }

    public Timestamp getTimestamp() {
        return this.timestamp;
    }
}
