package com.network;

/**
 * Created by luo.j on 2019/7/2.
 */

public interface OkHttpResponse {
    void getSuccess(String result);
    void getFail(String mes);
}
