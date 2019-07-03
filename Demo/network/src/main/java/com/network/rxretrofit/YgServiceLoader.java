package com.network.rxretrofit;

import com.network.rxretrofit.service.RetrofitRxJavaService;

import rx.Observable;

/**
 * Created by luo.j on 2019/7/3.
 */

public class YgServiceLoader extends ObjectLoader {
    private RetrofitRxJavaService ygApiService;
    public YgServiceLoader(){
        ygApiService = RxRetrofitServiceManager.getInstance().create(RetrofitRxJavaService.class);
    }
    public Observable<String> getVersion(){
        return observe(ygApiService.getVersion());

    }
    public Observable<String> getDetail(String productId){
        return observe(ygApiService.getDetail(productId));
    }

}
