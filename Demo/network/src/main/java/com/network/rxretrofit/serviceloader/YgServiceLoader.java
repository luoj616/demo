package com.network.rxretrofit.serviceloader;

import android.content.Context;
import android.util.Log;

import com.network.MyClass;
import com.network.ReturnHttpResponse;
import com.network.rxretrofit.HttpResultFunc;
import com.network.rxretrofit.HttpResultFuncStr;
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

    private YGRetrofitRxJavaService ygRetrofitRxJavaServiceStr;
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

   public void getLogin(String verifycode,String username,SubscriberOnNextListener mSubscriberOnNextListener, Context context){
       Observable observable =  ygRetrofitRxJavaService.getLogin(verifycode,username)
               .map(new HttpResultFunc<String>());

       toSubscribe(observable, mSubscriberOnNextListener,context);
   }



    public void getLogin(String verifycode,String username,   ReturnHttpResponse returnHttpResponse, Context context){
        Observable observable =  ygRetrofitRxJavaService.getLogin(verifycode,username)
                .map(new HttpResultFunc<String>());

        toSubscribe(observable,returnHttpResponse,context);
    }

    public void sendMsg(String username,   ReturnHttpResponse returnHttpResponse, Context context){
        Observable observable =  ygRetrofitRxJavaService.sendMsg(username)
                .map(new HttpResultFunc<String>());

        toSubscribe(observable,returnHttpResponse,context);
    }


    public void sendMsgStr(String username,   ReturnHttpResponse returnHttpResponse, Context context){
        Observable observable =  ygRetrofitRxJavaService.sendMsgStr(username)
                .map(new HttpResultFuncStr());

        toSubscribe(observable,returnHttpResponse,context);
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

