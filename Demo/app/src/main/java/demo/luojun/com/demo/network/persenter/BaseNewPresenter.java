package demo.luojun.com.demo.network.persenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.orhanobut.logger.Logger;

import demo.luojun.com.demo.network.view.BaseView;
import demo.luojun.com.demo.utils.ToastUtils;

/**
 *  * 控制器基类 mvp retrofit rxjava
 * Created by luo.j on 2019/7/31.
 */

//<里面传入的参数必须是BaseView的子类或者本身>
//这个类的作用就是获取到当前的View
public class BaseNewPresenter<V extends BaseView> {

    private  V view;

    //加载View,建立连接
    public void addView(V v) {
        this.view = v;
    }

    //断开连接
    public void detattch() {
        if (view != null) {
            view = null;
        }
    }



    /**
     * 是否与View建立连接
     * 每次调用业务请求的时候都要出先调用方法检查是否与View建立连接
     */
    public boolean isViewAttached() {
        return view != null;
    }

    /**
     * 获取连接的view
     */
    public V getView() {
        return view;
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

}