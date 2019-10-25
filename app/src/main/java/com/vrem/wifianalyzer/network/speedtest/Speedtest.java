package com.vrem.wifianalyzer.network.speedtest;

import android.widget.ProgressBar;
import android.widget.Toast;

import com.vrem.wifianalyzer.MainContext;
import com.vrem.wifianalyzer.network.model.SpeedtestDatastructureDownload;
import com.vrem.wifianalyzer.network.model.SpeedtestDatastructureUpload;

import java.sql.Time;
import java.sql.Timestamp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Speedtest {

    public void download() {
        Long timestampOut = System.currentTimeMillis();
        SpeedtestService.getInstance().speedtestApi().download(timestampOut).enqueue(
                new Callback<SpeedtestDatastructureDownload>() {
                    @Override
                    public void onResponse(Call<SpeedtestDatastructureDownload> call, Response<SpeedtestDatastructureDownload> response) {

//                        ProgressBar progressBar = MainContext.INSTANCE.getMainActivity().findViewById(R.id.progressBar);
//                        progressBar.setVisibility(ProgressBar.INVISIBLE);
                        int size = response.body().getFileSize();
                        long latency = (System.currentTimeMillis() - timestampOut) / 2;
                        long speed = (size * 8) * 10 / latency;
                        System.out.println("Download Speed: " + speed+"bps");
//
                        if (response.isSuccessful()) {

                            Toast toast = Toast.makeText(MainContext.INSTANCE.getContext(),
                                    "Download Speed: " + speed+"bps", Toast.LENGTH_LONG);
                            toast.show();
                        } else {
                            Toast toast = Toast.makeText(MainContext.INSTANCE.getContext(),
                                    "Download Speed test failed with response: " + response.code(), Toast.LENGTH_LONG);
                            toast.show();
                        }
                    }

                    @Override
                    public void onFailure(Call<SpeedtestDatastructureDownload> call, Throwable t) {
                        System.out.println("ERROR on call" + call.request());
                        t.printStackTrace(System.out);
                        Toast toast = Toast.makeText(MainContext.INSTANCE.getContext(),
                                "Download Speed test failed", Toast.LENGTH_LONG);
                        toast.show();
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
        Long timestampOut = System.currentTimeMillis();
        int size = 1000000;
        Byte[] file = new Byte[size];
        SpeedtestDatastructureUpload speedtestDatastructure = new SpeedtestDatastructureUpload(file, timestampOut);
        SpeedtestService.getInstance().speedtestApi().upload(speedtestDatastructure).enqueue(
                new Callback<Long>() {
                    @Override
                    public void onResponse(Call<Long> call, Response<Long> response) {
//                        ProgressBar progressBar = MainContext.INSTANCE.getMainActivity().findViewById(R.id.progressBar);
//                        progressBar.setVisibility(ProgressBar.INVISIBLE);
                        Long latency = ((System.currentTimeMillis()) - timestampOut) / 2;
                        Long speed = (size * 8) * 10 / latency;
                        System.out.println("Upload Speed: " + speed+"bps");
//
                        if (response.isSuccessful()) {

                            Toast toast = Toast.makeText(MainContext.INSTANCE.getContext(),
                                    "Upload Speed: " + speed+"bps", Toast.LENGTH_LONG);
                            toast.show();
                        } else {
                            Toast toast = Toast.makeText(MainContext.INSTANCE.getContext(),
                                    "Upload Speed test failed", Toast.LENGTH_LONG);
                            toast.show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Long> call, Throwable t) {
                        System.out.println("ERROR on call" + call.request());
                        t.printStackTrace(System.out);



//                        ProgressBar progressBar = MainContext.INSTANCE.getMainActivity().findViewById(R.id.progressBar);
//                        progressBar.setVisibility(ProgressBar.INVISIBLE);

                        Toast toast = Toast.makeText(MainContext.INSTANCE.getContext(),
                                "Upload Speed test failed", Toast.LENGTH_LONG);
                        toast.show();
                    }
                });

    }
}
