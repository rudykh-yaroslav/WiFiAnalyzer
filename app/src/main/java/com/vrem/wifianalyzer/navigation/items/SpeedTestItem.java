package com.vrem.wifianalyzer.navigation.items;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;

import com.vrem.wifianalyzer.MainActivity;
import com.vrem.wifianalyzer.MainContext;
import com.vrem.wifianalyzer.navigation.NavigationMenu;
import com.vrem.wifianalyzer.network.speedtest.Speedtest;
import com.vrem.wifianalyzer.report.ReportSender;

import org.apache.commons.lang3.StringUtils;

class SpeedTestItem implements NavigationItem {
    @Override
    public void activate(@NonNull MainActivity mainActivity, @NonNull MenuItem menuItem, @NonNull NavigationMenu navigationMenu) {
        String authKey = MainContext.INSTANCE.getAuthTokenProvider().getAuthKey();
        if (StringUtils.isBlank(authKey)) {
            MainContext.INSTANCE.getLoginDialogProvider().callLoginDialog();
        } else {
            Speedtest speedtest = new Speedtest();
            speedtest.download();
            speedtest.upload();
            //ToDo: STUB
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
