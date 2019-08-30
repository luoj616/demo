package com.network.rxretrofit;

import android.util.Log;

import com.network.BaseBean;

import rx.functions.Func1;



/**
 * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
 *http://gank.io/post/56e80c2c677659311bed9841
 * @param <T> Subscriber真正需要的数据类型，也就是Data部分的数据类型
 */
public class HttpResultFunc<T> implements Func1<BaseBean<T>, T> {
/*    private ReturnHttpResponse returnHttpResponse;
    public HttpResultFunc(){}
    public HttpResultFunc(ReturnHttpResponse returnHttpResponse){
        this.returnHttpResponse = returnHttpResponse;
    }*/
    @Override
    public T call(BaseBean<T> httpResult) {

        Log.e("info","过滤结果=="+httpResult.getCode()+"------"+httpResult.toString());
/*        if(httpResult.getCode() != 0&&returnHttpResponse!=null){
            returnHttpResponse.getFail("----",httpResult.getCode());
            throw new ApiException(ApiException.ERROR);
        }else*/
        if (httpResult.getCode() != 0) {
            throw new ApiException(httpResult.getCode());
        }
        Log.e("info",httpResult.getData()+"---**---"+httpResult.toString());
        return httpResult.getData();
    }
}


