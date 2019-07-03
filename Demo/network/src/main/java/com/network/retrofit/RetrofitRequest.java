package com.network.retrofit;

import com.network.RetrofitResponse;
import com.network.retrofit.service.YGApiService;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2019/7/2/002.
 */

public class RetrofitRequest {
    public void getVersion(final RetrofitResponse retrofitResponse){
        new Thread(new Runnable() {
            @Override
            public void run() {

                YGApiService ygApiService =  RetrofitServiceManager.getInstance().create(YGApiService.class);

                try {
                    Response<String> str=ygApiService.getVersion().execute();
                    String result = str.body().toString();
                    System.out.print("同步---"+result);
                    retrofitResponse.getSuccess(result);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void getDetail(String detail,final RetrofitResponse retrofitResponse){

        YGApiService ygApiService = RetrofitServiceManager.getInstance().create(YGApiService.class);
        ygApiService.getDetail(detail).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String baseData = response.body().toString();
                System.out.print("post 异步+=="+baseData+"---");
                retrofitResponse.getSuccess(baseData);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
}
