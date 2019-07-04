package com.network.retrofit.service;

import com.network.AppServerAPI;
import com.network.BaseBean;

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
   Call<BaseBean> getVersion();

 @POST(AppServerAPI.YG_commodityDetail)
 @FormUrlEncoded
   Call<BaseBean> getDetail(@Field("productid") String productid);
}
