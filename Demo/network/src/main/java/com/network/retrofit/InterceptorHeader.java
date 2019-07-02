package com.network.retrofit;

import com.network.okhttp.HeaderUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2019/7/2/002.
 */

public class InterceptorHeader implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request requestLog = chain.request()
                .newBuilder()
                .headers(HeaderUtils.getHead())
                                 /*              .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                                               .addHeader("Accept-Encoding", "gzip, deflate")
                                                 .addHeader("Connection", "keep-alive")
                                              .addHeader("Cookie", "add cookies here")*/

                .build();
        okhttp3.Response response = chain.proceed(requestLog);
        return response;
    }
}
