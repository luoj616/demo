package com.network.okhttp;

import com.network.OkHttpResponse;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by luo.j on 2019/7/2.
 */

public class OkHttpRequestUtils {
    public void getSync(final String url, final OkHttpResponse networkResponse){
        new Thread(new Runnable() {
            @Override
            public void run() {
              OkHttpUtils okHttpUtils = new OkHttpUtils();
                try {
                    System.out.println("---------------------module===");
                    Response response= okHttpUtils.getOkHttpClientInterceptor().newCall(okHttpUtils.getRequestYG(url)).execute();
                       String result =response.body().string();
                    System.out.println("---------------------module===_________________________________");
                            System.out.println("module==="+result);
                    networkResponse.getSuccess(result);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void getASync(final String url, final OkHttpResponse networkResponse){

                OkHttpUtils okHttpUtils = new OkHttpUtils();

                    System.out.println("---------------------module===");
                     okHttpUtils.getOkHttpClientInterceptor().newCall(okHttpUtils.getRequestYG(url))
                             .enqueue(new Callback() {
                                 @Override
                                 public void onFailure(Call call, IOException e) {

                                 }

                                 @Override
                                 public void onResponse(Call call, Response response) throws IOException {
                                     String result =response.body().string();
                                     System.out.println("module==="+result);
                                     networkResponse.getSuccess(result);
                                 }
                             });






    }
    public void postFromSync(final String url,final Map<String ,String> map,final OkHttpResponse networkResponse){
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpUtils okHttpUtils = new OkHttpUtils();
                try {

                    Response response= okHttpUtils.getOkHttpClientInterceptor()
                            .newCall(okHttpUtils.getRequestYGPostForm(url,map)).execute();
                    String result =response.body().string();

                    System.out.println("module==="+result);
                    networkResponse.getSuccess(result);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    public void postJsonASync(final String url,final Map<String ,String> map,final OkHttpResponse networkResponse){

                OkHttpUtils okHttpUtils = new OkHttpUtils();


                   okHttpUtils.getOkHttpClientInterceptor()
                            .newCall(okHttpUtils.getRequestYGPostJson(url,map))
                            .enqueue(new Callback() {
                                @Override
                                public void onFailure(Call call, IOException e) {

                                }

                                @Override
                                public void onResponse(Call call, Response response) throws IOException {
                                    String result =response.body().string();

                                    System.out.println("module==="+result);
                                    networkResponse.getSuccess(result);
                                }
                            });


            }


}
