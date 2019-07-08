package com.network.rxretrofit;


import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by luo.j on 2019/7/3.
 */

public class ObjectLoader<T> implements CreateServiceLoader<T> {



    /*
     * @param observable
     * @param <T>
     * @return
    */
    protected  <T> Observable<T> observe(Observable<T> observable){
        return observable
         .subscribeOn(Schedulers.io())
            //  .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public T getService(Class<T> t) {
        return RxRetrofitServiceManager.getInstance().create(t);
    }
}
