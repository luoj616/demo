package com.network;

/**
 * Created by Administrator on 2019/7/2/002.
 */

public interface RetrofitResponse {
    void getSuccess(String result);
    void getFail(String mes);
}
