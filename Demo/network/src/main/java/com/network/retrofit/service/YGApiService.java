package com.network.retrofit.service;

import com.network.AppServerAPI;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by luo.j on 2019/6/28.
 */

public interface YGApiService {
   @GET(AppServerAPI.YG_VERSION)
   Call<String> getVersion();

 @POST(AppServerAPI.YG_commodityDetail)
 @FormUrlEncoded
   Call<String> getDetail(@Field("productid") String productid);
}
