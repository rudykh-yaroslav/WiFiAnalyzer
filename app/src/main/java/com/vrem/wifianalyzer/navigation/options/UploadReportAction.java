package com.vrem.wifianalyzer.navigation.options;

import com.vrem.wifianalyzer.MainContext;
import com.vrem.wifianalyzer.report.Report;
import com.vrem.wifianalyzer.wifi.scanner.ScannerService;

class UploadReportAction implements Action {
    @Override
    public void execute() {
        ScannerService scannerService = MainContext.INSTANCE.getScannerService();
        Report report = new Report(scannerService.getWiFiData());
        report.send();
    }
}
