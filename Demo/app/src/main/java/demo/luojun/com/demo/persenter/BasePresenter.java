package demo.luojun.com.demo.persenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import demo.luojun.com.demo.MainActivity;
import demo.luojun.com.demo.network.NetworkActivity;
import demo.luojun.com.demo.utils.ToastUtils;
import demo.luojun.com.demo.view.BaseView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * 控制器基类
 * Created by luo.j on 2018/3/27.
 */
public class BasePresenter<V extends BaseView> {
    /**
     * 绑定的view
     */
    private V mvpView;

    public BasePresenter() {
    }


    /**
     * 绑定view，一般在初始化中调用该方法
     */

    public void attachView(V mvpView) {
        this.mvpView = mvpView;
    }

    /**
     * 断开view，一般在onDestroy中调用
     */

    public void detachView() {
        this.mvpView = null;
    }

    /**
     * 是否与View建立连接
     * 每次调用业务请求的时候都要出先调用方法检查是否与View建立连接
     */
    public boolean isViewAttached() {
        return mvpView != null;
    }

    /**
     * 获取连接的view
     */
    public V getView() {
        return mvpView;
    }

    /**
     * 获取当前页面context
     * @return
     */
    protected Context getThisContext() {
        return getView().getContext();
    }

    /**
     * 获取当前Activity
     * @return
     */
    protected Activity getThisActivity() {
        return (Activity) getView().getContext();
    }

    /**
     * 输入信息
     *
     * @param info 日志信息
     */
    protected void print(String info) {
        //   if(AppContext.isTest)
        Logger.e( info);

    }

    protected  void printLog(String info){
        Log.e("info", "printLog: ======"+info );
    }

    /**
     * 跳转页面
     * @param clss
     */
    public   void jumpActivity(Class clss){
       getThisContext(). startActivity( new Intent(getThisContext(), clss));
    }

    /**
     * 截断输出日志
     *
     * @param msg
     */
    protected void printLogAll(String msg) {
        int segmentSize = 3 * 1024;
        long length = msg.length();
        if (length <= segmentSize) {// 长度小于等于限制直接打印
            Log.e("info", msg);
        } else {
            while (msg.length() > segmentSize) {// 循环分段打印日志
                String logContent = msg.substring(0, segmentSize);
                msg = msg.replace(logContent, "");
                Log.e("info", logContent);
            }
            Log.e("info", msg);// 打印剩余日志
        }
    }
    protected void toast(String info){
        ToastUtils.showToast(getThisActivity(), info);
    }


    public OkHttpClient getOkhttpClient() {
        return new OkHttpClient();
    }

    public OkHttpClient getOkhttpClientBulider() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(3 * 10000, TimeUnit.MILLISECONDS);//设置超时时间
        return builder.build();
    }

    /**
     * okhttp 同步get请求
     *
     * @param requestCode

     */
    protected void requestGetSync( final int requestCode,Request request) {
        //test
        final Call call = getOkhttpClient().newCall(request);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = call.execute();
                    final String result = response.body().string();
                    getThisActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            reponse(result, requestCode);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * okhttp 异步get请求
     *
     * @param requestCode
     */
    protected void requestGetAsyn( final int requestCode, Request request) {
        final Call call = getOkhttpClient().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {}

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                getThisActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            reponse(response.body().string(), requestCode);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    /**
     * 返回数据
     * @param result
     * @param requestCode
     */
    protected void reponse(String result, int requestCode) {
    }


}
