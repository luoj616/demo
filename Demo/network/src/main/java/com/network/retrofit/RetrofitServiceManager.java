package com.network.retrofit;

import com.network.AppServerAPI;
import com.network.okhttp.InterceptorLog;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Administrator on 2019/7/2/002.
 */

public class RetrofitServiceManager {
    private static final int DEFAULT_TIME_OUT = 5;//超时时间 5s
    private static final int DEFAULT_READ_TIME_OUT = 10;
    private Retrofit mRetrofit;
    private RetrofitServiceManager(){
        mRetrofit = new Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .baseUrl(AppServerAPI.YG_IP)
                .client(genericClient())
                .build();
    }

    public  Retrofit getRetrofitUtils(){
        return new Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .baseUrl(AppServerAPI.YG_IP)
                .client(genericClient())
                .build();
    }


    public OkHttpClient genericClient() {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(new InterceptorHeader())//公共参数
                .addInterceptor(new InterceptorLog())//日志拦截器
                .connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)//连接超时时间
                .writeTimeout(DEFAULT_READ_TIME_OUT,TimeUnit.SECONDS)//写操作 超时时间
                .readTimeout(DEFAULT_READ_TIME_OUT,TimeUnit.SECONDS)//读操作超时时间
                .build();
        return httpClient;
    }

    private static class SingletonHolder{
        private static final RetrofitServiceManager INSTANCE = new RetrofitServiceManager();
    }
    /**
     * 获取RetrofitServiceManager
     * @return
     */
    public static RetrofitServiceManager getInstance(){
        return SingletonHolder.INSTANCE;
    }
    /**
     * 获取对应的Service
     * @param service Service 的 class
     * @param <T>
     * @return
     */
    public <T> T create(Class<T> service){
        return mRetrofit.create(service);
    }

}
