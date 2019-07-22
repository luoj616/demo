package com.network;

/**
 * Created by luo.j on 2019/7/22.
 */

public interface ReturnHttpResponse<T> {
   void  success(T t);
    void getFail(String mes,int Code);
}
