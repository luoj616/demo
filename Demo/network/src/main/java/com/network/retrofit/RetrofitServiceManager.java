package com.network.retrofit;

import com.network.AppServerAPI;
import com.network.okhttp.InterceptorLog;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Administrator on 2019/7/2/002.
 */

public class RetrofitUtils {

    public  Retrofit getRetrofitUtils(){
        return new Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .baseUrl(AppServerAPI.YG_IP)
                .client(genericClient())
                .build();
    }


    public OkHttpClient genericClient() {

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(new InterceptorHeader())
                .addInterceptor(new InterceptorLog())


                .build();



        return httpClient;

    }

}
