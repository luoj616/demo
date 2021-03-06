package com.network.retrofit;

import com.network.BaseBean;
import com.network.ReturnHttpResponse;
import com.network.retrofit.service.YGApiService;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2019/7/2/002.
 */

public class RetrofitRequest {
    public void getVersion(final ReturnHttpResponse returnHttpResponse){
        new Thread(new Runnable() {
            @Override
            public void run() {

                YGApiService ygApiService =  RetrofitServiceManager.getInstance().create(YGApiService.class);

                try {
                    Response<BaseBean> str=ygApiService.getVersion().execute();
                    String result = str.body().toString();

                    System.out.print("同步---"+result);
                    returnHttpResponse.success(str.body());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void getDetail(String detail,final ReturnHttpResponse returnHttpResponse){

        YGApiService ygApiService = RetrofitServiceManager.getInstance().create(YGApiService.class);
        ygApiService.getDetail(detail).enqueue(new Callback<BaseBean>() {
            @Override
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {

                returnHttpResponse.success(response.body());
            }

            @Override
            public void onFailure(Call<BaseBean> call, Throwable t) {

            }
        });
    }
}
