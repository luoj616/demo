package com.network.rxretrofit.serviceloader;

import android.util.Log;

import com.network.BaseBean;
import com.network.MyClass;
import com.network.rxretrofit.ApiException;
import com.network.rxretrofit.ObjectLoader;
import com.network.rxretrofit.service.YGRetrofitRxJavaService;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

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



    public void getDetailS(Subscriber<String> subscriber, String productId){

//        movieService.getTopMovie(start, count)
//                .map(new HttpResultFunc<List<Subject>>())
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);

        Observable observable = ygRetrofitRxJavaService.getDetail(productId)
                .map(new HttpResultFunc<String>());

        toSubscribe(observable, subscriber);
    }


    private <T> void toSubscribe(Observable<T> o, Subscriber<T> s){
        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }
    /**
     * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
     *http://gank.io/post/56e80c2c677659311bed9841
     * @param <T> Subscriber真正需要的数据类型，也就是Data部分的数据类型
     */
    private class HttpResultFunc<T> implements Func1<BaseBean<T>, T> {

        @Override
        public T call(BaseBean<T> httpResult) {
            Log.e("info",httpResult.toString());
            if (httpResult.getCode() != 0) {
                throw new ApiException(httpResult.getCode());
            }
            return httpResult.getData();
        }
    }
}

