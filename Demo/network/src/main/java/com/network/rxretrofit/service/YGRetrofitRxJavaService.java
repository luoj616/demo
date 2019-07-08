package com.network.rxretrofit.service;

import com.network.AppServerAPI;
import com.network.MyClass;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by luo.j on 2019/7/3.
 */

public interface YGRetrofitRxJavaService {

  @GET(AppServerAPI.YG_VERSION)
  Observable<MyClass> getVersion();

    @POST(AppServerAPI.YG_commodityDetail)
    @FormUrlEncoded
    Observable<MyClass> getDetail(@Field("productid") String productid);

}
