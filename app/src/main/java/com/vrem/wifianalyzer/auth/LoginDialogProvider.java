package com.vrem.wifianalyzer.auth;

import android.app.Dialog;
import android.widget.Button;
import android.widget.EditText;

import com.vrem.wifianalyzer.MainContext;
import com.vrem.wifianalyzer.R;
import com.vrem.wifianalyzer.report.Report;
import com.vrem.wifianalyzer.wifi.scanner.ScannerService;

public class LoginDialogProvider {

    public void callLoginDialog() {
        Dialog loginDialog = new Dialog(MainContext.INSTANCE.getMainActivity());
        loginDialog.setContentView(R.layout.login_form);
        loginDialog.setCancelable(false);
        Button loginBtn = loginDialog.findViewById(R.id.login_button);

        EditText login = loginDialog.findViewById(R.id.value_username);
        EditText password = loginDialog.findViewById(R.id.value_password);
        loginDialog.show();

        loginBtn.setOnClickListener(v -> {
            ScannerService scannerService = MainContext.INSTANCE.getScannerService();
            Report report = new Report(scannerService.getWiFiData());
            report.send();

            loginDialog.hide();
        });


    }
}
