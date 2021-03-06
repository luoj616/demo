package com.network.rxretrofit.service;

import com.network.AppServerAPI;
import com.network.BaseBean;
import com.network.MyClass;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by luo.j on 2019/7/3.
 */

public interface YGRetrofitRxJavaService {

  @GET(AppServerAPI.YG_VERSION)
  Observable<MyClass> getVersion();

    @POST(AppServerAPI.YG_commodityDetail)
    @FormUrlEncoded
    Observable<BaseBean<String>> getDetail(@Field("productid") String productid);

  @GET(AppServerAPI.YG_LOGIN)
  Observable<BaseBean<String>> getLogin(@Query("verifycode") String verifycode, @Query("username") String username);



  @GET(AppServerAPI.YG_SEND_MSM)
  Observable<BaseBean<String>> sendMsg(@Query("username") String username);


  @GET(AppServerAPI.YG_SEND_MSM)
  Observable<String> sendMsgStr(@Query("username") String username);
}
