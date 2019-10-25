package com.vrem.wifianalyzer.auth;

import android.app.Dialog;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.vrem.wifianalyzer.MainContext;
import com.vrem.wifianalyzer.R;
import com.vrem.wifianalyzer.network.WiFiAdminNetworkService;
import com.vrem.wifianalyzer.report.ReportSender;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
            WiFiAdminNetworkService
                    .getInstance()
                    .wiFiAdminApi()
                    .login(new LoginData(login.getText().toString(), password.getText().toString()))
                    .enqueue(
                            new Callback<UserData>() {

                                @Override
                                public void onResponse(Call<UserData> call, Response<UserData> response) {
                                    ProgressBar progressBar = MainContext.INSTANCE.getMainActivity().findViewById(R.id.progressBar);
                                    progressBar.setVisibility(ProgressBar.INVISIBLE);

                                    if (response.isSuccessful()) {
                                        MainContext.INSTANCE.getAuthTokenProvider().setUserData(response.body());
                                        ReportSender reportSender = new ReportSender();
                                        reportSender.send();
                                    } else {
                                        Toast toast = Toast.makeText(MainContext.INSTANCE.getContext(),
                                                R.string.login_failure, Toast.LENGTH_LONG);
                                        toast.show();
                                    }

                                    loginDialog.hide();
                                }

                                @Override
                                public void onFailure(Call<UserData> call, Throwable t) {
                                    ProgressBar progressBar = MainContext.INSTANCE.getMainActivity().findViewById(R.id.progressBar);
                                    progressBar.setVisibility(ProgressBar.INVISIBLE);

                                    Toast toast = Toast.makeText(MainContext.INSTANCE.getContext(),
                                            R.string.login_failure, Toast.LENGTH_LONG);
                                    toast.show();

                                    loginDialog.hide();
                                }
                            });
        });


    }
}
