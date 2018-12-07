package demo.luojun.com.demo.context;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.orhanobut.logger.Logger;

import demo.luojun.com.demo.persenter.BasePresenter;
import demo.luojun.com.demo.utils.ToastUtils;
import demo.luojun.com.demo.view.BaseView;

public class BaseActivity extends AppCompatActivity implements BaseView {
      protected BasePresenter basePresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        priintLog("所在页面--oncreate===——————"+getClass().getSimpleName());
        basePresenter = new BasePresenter();
        basePresenter.attachView(this);


    }
    /**
     *
     *
     * package com.yougou.tools;

     import android.util.Log;

     public class LogPrinter {

     // public static boolean isDebug = true; 开关在Myapplication 类，发布版本时，应改为false。

     private static final String DEBUG_TAG = "info";

     static int maxLogSize = 3000;

     public static void debugInfo(String msg) {
     if (MyApplication.isDebug) {    // 由于某些接口服务器返回的数据过大，需要分段打印log

     for (int i = 0; i <= msg.length() / maxLogSize; i++) {

     int start = i * maxLogSize;

     int end = (i + 1) * maxLogSize;

     end = end > msg.length() ? msg.length() : end;

     Log.i(DEBUG_TAG, msg.substring(start, end));

     }
     // Log.i(DEBUG_TAG, msg);
     }
     }

     public static void debugInfo(String tag,String msg) {
     if (MyApplication.isDebug) {    // 由于某些接口服务器返回的数据过大，需要分段打印log
     for (int i = 0; i <= msg.length() / maxLogSize; i++) {

     int start = i * maxLogSize;

     int end = (i + 1) * maxLogSize;

     end = end > msg.length() ? msg.length() : end;

     Log.i(tag, msg.substring(start, end));
     }
     // Log.i(DEBUG_TAG, msg);
     }
     }

     public static void debugInfo(String tag,String msg,Throwable t) {
     if (MyApplication.isDebug) {    // 由于某些接口服务器返回的数据过大，需要分段打印log

     for (int i = 0; i <= msg.length() / maxLogSize; i++) {

     int start = i * maxLogSize;

     int end = (i + 1) * maxLogSize;
     end = end > msg.length() ? msg.length() : end;

     Log.i(tag, msg.substring(start, end),t);
     }
     // Log.i(DEBUG_TAG, msg);
     }
     }
     public static void debugError(String msg) {
     if (MyApplication.isDebug) {
     Log.e(DEBUG_TAG, msg);
     }
     }

     public static void i(String msg) {
     if (MyApplication.isDebug) {
     System.out.println(msg);
     }
     }

     public static void i(String tag, String msg) {
     if (MyApplication.isDebug) {
     Log.i(tag, msg);
     }
     }

     /**
     * logcat 打印Json
     * @param tag
     * @param msg
     */
    public  static  void  json(String tag, String msg){
        if (MyApplication.isDebug) {
            JsonUtil.printJson(tag,msg,"");
        }
    }
    /**
     * logcat 打印Json
     * @param tag
     * @param msg
     * @param headString //
     */
    public  static  void  json(String tag, String msg,String headString){
        if (MyApplication.isDebug) {
            JsonUtil.printJson(tag,msg,headString);
        }
    }
}

     * 输入信息
     * @param info 日志信息
     */
    protected void print(String info) {
        //if (AppContext.isTest)
       Logger.e( info);

    }

    protected  void priintLog(String info){
        Log.e("info", info );
    }

    protected void toast(String info){
        ToastUtils.showToast(this, info);
    }
    @Override
    public Context getContext() {
        return this;
    }
}
