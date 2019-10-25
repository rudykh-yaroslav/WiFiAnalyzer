package com.vrem.wifianalyzer.navigation.options;

import com.vrem.wifianalyzer.MainContext;
import com.vrem.wifianalyzer.report.ReportSender;

import org.apache.commons.lang3.StringUtils;

class UploadReportAction implements Action {
    @Override
    public void execute() {
        String authKey = MainContext.INSTANCE.getAuthTokenProvider().getAuthKey();
        if (StringUtils.isBlank(authKey)) {
            MainContext.INSTANCE.getLoginDialogProvider().callLoginDialog();
        } else {
            ReportSender reportSender = new ReportSender();
            reportSender.send();
        }
    }
}
