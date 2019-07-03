package com.network.rxretrofit;


import rx.Observable;

/**
 * Created by luo.j on 2019/7/3.
 */

public class ObjectLoader {

    /**
     *
     * @param observable
     * @param <T>
     * @return
     */
    protected  <T> Observable<T> observe(Observable<T> observable){
        return observable;
     /*           .subscribeOn(Schedulers.io())
             //   .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());*/
    }
}
