package com.network.rxretrofit.service;

import com.network.AppServerAPI;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by luo.j on 2019/7/3.
 */

public interface RetrofitRxJavaService {

    @GET(AppServerAPI.YG_VERSION)
    Observable<String> getVersion();

    @POST(AppServerAPI.YG_commodityDetail)
    @FormUrlEncoded
    Observable<String> getDetail(@Field("productid") String productid);

}
