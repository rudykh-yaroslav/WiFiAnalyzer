package com.vrem.wifianalyzer.navigation.items;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;

import com.vrem.wifianalyzer.MainActivity;
import com.vrem.wifianalyzer.MainContext;
import com.vrem.wifianalyzer.navigation.NavigationMenu;
import com.vrem.wifianalyzer.report.Report;

import org.apache.commons.lang3.StringUtils;

class UploadReportItem implements NavigationItem {
    @Override
    public void activate(@NonNull MainActivity mainActivity, @NonNull MenuItem menuItem, @NonNull NavigationMenu navigationMenu) {
        String authKey = MainContext.INSTANCE.getAuthTokenProvider().getAuthKey();
        if (StringUtils.isBlank(authKey)) {
            MainContext.INSTANCE.getLoginDialogProvider().callLoginDialog();
        } else {
            Report report = new Report(MainContext.INSTANCE.getScannerService().getWiFiData());
            report.send();
        }
    }

    @Override
    public boolean isRegistered() {
        return false;
    }

    @Override
    public int getVisibility() {
        return View.GONE;
    }
}
