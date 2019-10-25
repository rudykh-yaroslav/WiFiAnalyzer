package com.vrem.wifianalyzer.network.speedtest;

import android.widget.ProgressBar;
import android.widget.Toast;

import com.vrem.wifianalyzer.MainContext;
import com.vrem.wifianalyzer.R;
import com.vrem.wifianalyzer.network.model.SpeedtestDatastructure;

import java.sql.Time;
import java.sql.Timestamp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Speedtest {

    public void download() {
        Timestamp timestampOut = new Timestamp(System.currentTimeMillis());
        SpeedtestService.getInstance().speedtestApi().download(timestampOut).enqueue(
                new Callback<SpeedtestDatastructure>() {
                    @Override
                    public void onResponse(Call<SpeedtestDatastructure> call, Response<SpeedtestDatastructure> response) {

//                        ProgressBar progressBar = MainContext.INSTANCE.getMainActivity().findViewById(R.id.progressBar);
//                        progressBar.setVisibility(ProgressBar.INVISIBLE);
                        int size = response.body().getFileSize();
                        long latency = ((new Timestamp(System.currentTimeMillis())).getTime() - timestampOut.getTime()) / 2;
                        long speed = (size * 8) * 10 / latency;
                        System.out.println("Download Speed: " + speed+"bps");
//
                        if (response.isSuccessful()) {

//                            Toast toast = Toast.makeText(MainContext.INSTANCE.getContext(),
//                                    R.string.report_upload_success, Toast.LENGTH_LONG);
//                            toast.show();
                        } else {
//                            Toast toast = Toast.makeText(MainContext.INSTANCE.getContext(),
//                                    R.string.report_upload_failure, Toast.LENGTH_LONG);
//                            toast.show();
                        }
                    }

                    @Override
                    public void onFailure(Call<SpeedtestDatastructure> call, Throwable t) {
                        System.out.println("ERROR on call" + call.request());
//                        ProgressBar progressBar = MainContext.INSTANCE.getMainActivity().findViewById(R.id.progressBar);
//                        progressBar.setVisibility(ProgressBar.INVISIBLE);
//
//                        Toast toast = Toast.makeText(MainContext.INSTANCE.getContext(),
//                                R.string.report_upload_failure, Toast.LENGTH_LONG);
//                        toast.show();
                    }
                });

    }


    public void upload() {
        Timestamp timestampOut = new Timestamp(System.currentTimeMillis());
        int size = 1000000;
        Byte[] file = new Byte[size];
        SpeedtestDatastructure speedtestDatastructure = new SpeedtestDatastructure(file, timestampOut);
        SpeedtestService.getInstance().speedtestApi().upload(speedtestDatastructure).enqueue(
                new Callback<Timestamp>() {
                    @Override
                    public void onResponse(Call<Timestamp> call, Response<Timestamp> response) {
//                        ProgressBar progressBar = MainContext.INSTANCE.getMainActivity().findViewById(R.id.progressBar);
//                        progressBar.setVisibility(ProgressBar.INVISIBLE);
                        Long latency = ((new Timestamp(System.currentTimeMillis())).getTime() - timestampOut.getTime()) / 2;
                        Long speed = (size * 8) * 10 / latency;
                        System.out.println("Upload Speed: " + speed+"bps");
//
                        if (response.isSuccessful()) {

//                            Toast toast = Toast.makeText(MainContext.INSTANCE.getContext(),
//                                    R.string.report_upload_success, Toast.LENGTH_LONG);
//                            toast.show();
                        } else {
//                            Toast toast = Toast.makeText(MainContext.INSTANCE.getContext(),
//                                    R.string.report_upload_failure, Toast.LENGTH_LONG);
//                            toast.show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Timestamp> call, Throwable t) {
                        System.out.println("ERROR on call" + call.request());
//                        ProgressBar progressBar = MainContext.INSTANCE.getMainActivity().findViewById(R.id.progressBar);
//                        progressBar.setVisibility(ProgressBar.INVISIBLE);
//
//                        Toast toast = Toast.makeText(MainContext.INSTANCE.getContext(),
//                                R.string.report_upload_failure, Toast.LENGTH_LONG);
//                        toast.show();
                    }
                });

    }
}
