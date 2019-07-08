package com.network.rxretrofit.serviceloader;

import android.util.Log;

import com.network.MyClass;
import com.network.rxretrofit.ObjectLoader;
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
}

