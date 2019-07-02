package com.network.okhttp;

import com.google.gson.Gson;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by luo.j on 2019/7/2.
 */

public class OkHttpUtils {

    public OkHttpClient getOkHttpClient(){
        OkHttpClient okHttpClient =new OkHttpClient();
        return okHttpClient;
    }
    public OkHttpClient getOkHttpClientInterceptor(){
      OkHttpClient okHttpClient=  new OkHttpClient.Builder()
                // .cache(cache)
                .addInterceptor(new InterceptorLog())
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .build();
        return okHttpClient;
    }



    public Request getRequestYG(String url) {
        Request request = new Request.Builder()
                .url(url)
                .headers(HeaderUtils.getHead())
                .build();
        return request;

    }



    /**
     * formbody数据格式post请求
     * @param url
     * @param parms
     * @return
     */
    public Request getRequestYGPostForm(String url,Map<String,String> parms){
        Request request= new Request.Builder()
                .url(url)
                .method("POST",getFormBody(parms))
                //  .post(getFormBody(parms))
                .headers(HeaderUtils.getHead())
                .build();

        return request;

    }
    /**
     * json数据格式post请求
     * @param url
     * @param parms
     * @return
     */
    public Request getRequestYGPostJson(String url,Map<String,String> parms){
        Request request= new Request.Builder()
                .url(url)
                .post(getRequestBody(parms))
                .headers(HeaderUtils.getHead())
                .build();

        return request;

    }

    /**
     * formBody数据请求
     * @param parms
     * @return
     */
    public FormBody getFormBody(Map<String,String> parms){
        FormBody.Builder formBody =new FormBody.Builder();
        for (String key : parms.keySet()) {
            System.out.println("key= "+ key + " and value= " + parms.get(key));
            formBody.add(key,parms.get(key));
        }
        return formBody.build();
    }

    /**
     * json数据请求
     * @param parms
     * @return
     */
    public RequestBody getRequestBody(Map<String,String> parms){

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");//数据类型为json格式，
        RequestBody requestBody = RequestBody.create(JSON, new Gson().toJson(parms).toString());
        return requestBody;
    }

}
