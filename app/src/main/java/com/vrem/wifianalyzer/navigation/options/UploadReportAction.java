package com.vrem.wifianalyzer.navigation.options;

import com.vrem.wifianalyzer.MainContext;
import com.vrem.wifianalyzer.report.Report;
import com.vrem.wifianalyzer.wifi.scanner.ScannerService;

import org.apache.commons.lang3.StringUtils;

class UploadReportAction implements Action {
    @Override
    public void execute() {
        String authKey = MainContext.INSTANCE.getAuthTokenProvider().getAuthKey();
        if (StringUtils.isBlank(authKey)) {
            MainContext.INSTANCE.getLoginDialogProvider().callLoginDialog();
        } else {
            ScannerService scannerService = MainContext.INSTANCE.getScannerService();
            Report report = new Report(scannerService.getWiFiData());
            report.send();
        }
    }
}
