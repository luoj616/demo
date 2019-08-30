package com.network.rxretrofit;

import rx.functions.Func1;

/**
 * Created by luo.j on 2019/8/16.
 */

public class HttpResultFuncStr<T> implements Func1<T, T> {



    @Override
    public T call(T t) {
//        Log.e("info","过滤结果=="+httpResult.getCode()+"------"+httpResult.toString());
///*        if(httpResult.getCode() != 0&&returnHttpResponse!=null){
//            returnHttpResponse.getFail("----",httpResult.getCode());
//            throw new ApiException(ApiException.ERROR);
//        }else*/
//        if (httpResult.getCode() != 0) {
//            throw new ApiException(httpResult.getCode());
//        }
        return t;
    }
}

