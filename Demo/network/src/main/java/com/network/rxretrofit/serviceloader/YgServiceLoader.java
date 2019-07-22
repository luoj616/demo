package com.network.rxretrofit.serviceloader;

import android.content.Context;
import android.util.Log;

import com.network.MyClass;
import com.network.ReturnHttpResponse;
import com.network.rxretrofit.HttpResultFunc;
import com.network.rxretrofit.ObjectLoader;
import com.network.rxretrofit.SubscriberOnNextListener;
import com.network.rxretrofit.service.YGRetrofitRxJavaService;

import rx.Observable;

/**
 * Created by luo.j on 2019/7/3.
 * service加载器
 */

public class YgServiceLoader extends ObjectLoader {
/*    @Override
    public YGRetrofitRxJavaService getService() {
        return RxRetrofitServiceManager.getInstance().create(YGRetrofitRxJavaService.class);
    }*/
       private YGRetrofitRxJavaService ygRetrofitRxJavaService;
    public YgServiceLoader(){
        ygRetrofitRxJavaService=(YGRetrofitRxJavaService)getService(YGRetrofitRxJavaService.class);
    }

    public Observable<MyClass> getVersion(){
        Log.e("info","yg----------------"+ygRetrofitRxJavaService);
        return observe(ygRetrofitRxJavaService.getVersion());

    }
    public Observable<MyClass> getDetail(String productId){
        return observe(ygRetrofitRxJavaService.getDetail(productId));
    }



    public void getDetailS(String productId,
                           SubscriberOnNextListener mSubscriberOnNextListener, Context context){
        Observable observable = ygRetrofitRxJavaService.getDetail(productId)
                .map(new HttpResultFunc<String>());

        toSubscribe(observable, mSubscriberOnNextListener,context);
    }




    public void getDetailS(String productId,
                           ReturnHttpResponse returnHttpResponse, Context context){
        Observable observable = ygRetrofitRxJavaService.getDetail(productId)
                .map(new HttpResultFunc<String>());

        toSubscribe(observable, returnHttpResponse,context);
    }








}

