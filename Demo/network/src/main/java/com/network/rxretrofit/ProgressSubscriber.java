package com.network.rxretrofit;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.network.ReturnHttpResponse;
import com.network.rxretrofit.progress.ProgressCancelListener;
import com.network.rxretrofit.progress.ProgressDialogHandler;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import rx.Subscriber;

/**
 * 用于在Http请求开始时，自动显示一个ProgressDialog
 * 在Http请求结束是，关闭ProgressDialog
 * 调用者自己对请求数据进行处理
 * Created by liukun on 16/3/10.
 */
public class ProgressSubscriber<T> extends Subscriber<T> implements ProgressCancelListener {

  //  private SubscriberOnNextListener mSubscriberOnNextListener;
    private ProgressDialogHandler mProgressDialogHandler;
    private ReturnHttpResponse returnHttpResponse;//结果处理
    private boolean isFail =false;//是否自己处理fail

    private Context context;

    public ProgressSubscriber(ReturnHttpResponse returnHttpResponse, Context context) {
       // this.mSubscriberOnNextListener = mSubscriberOnNextListener;
        this.returnHttpResponse = returnHttpResponse ;
        isFail = true;
        this.context = context;
        mProgressDialogHandler = new ProgressDialogHandler(context, this, true);
    }
    public ProgressSubscriber(final SubscriberOnNextListener mSubscriberOnNextListener, Context context) {
        this.returnHttpResponse = new ReturnHttpResponse<T>(){

            @Override
            public void success(T t) {
                mSubscriberOnNextListener.onNext(t);
            }

            @Override
            public void getFail(String mes, int Code) {

            }
        };
      //  this.mSubscriberOnNextListener = mSubscriberOnNextListener;
        this.context = context;
        mProgressDialogHandler = new ProgressDialogHandler(context, this, true);
    }

    private void showProgressDialog(){
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
        }
    }

    private void dismissProgressDialog(){
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
            mProgressDialogHandler = null;
        }
    }

    /**
     * 订阅开始时调用
     * 显示ProgressDialog
     */
    @Override
    public void onStart() {
        showProgressDialog();
    }

    /**
     * 完成，隐藏ProgressDialog
     */
    @Override
    public void onCompleted() {
        dismissProgressDialog();
        Toast.makeText(context, "Get Top Movie Completed", Toast.LENGTH_SHORT).show();
    }

    /**
     * 对错误进行统一处理
     * 隐藏ProgressDialog
     * @param e
     */
    @Override
    public void onError(Throwable e) {
        Log.e("info","-------------------error---------------------"+e.toString());
        dismissProgressDialog();
        if(isFail) {
            returnHttpResponse.getFail(e.getMessage(),0);

            return;
        }
        if (e instanceof SocketTimeoutException) {
            Toast.makeText(context, "Subscriber onErro:" +"网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
        } else if (e instanceof ConnectException) {
            Toast.makeText(context, "Subscriber onErro:" +"网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Subscriber onErro:" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }

    /**
     * 将onNext方法中的返回结果交给Activity或Fragment自己处理
     *
     * @param t 创建Subscriber时的泛型类型
     */
    @Override
    public void onNext(T t) {
        if(returnHttpResponse!=null)
            returnHttpResponse.success(t);
      /*  if (mSubscriberOnNextListener != null) {
            mSubscriberOnNextListener.onNext(t);
        }*/
    }

    /**
     * 取消ProgressDialog的时候，取消对observable的订阅，同时也取消了http请求
     */
    @Override
    public void onCancelProgress() {
        if (!this.isUnsubscribed()) {
            this.unsubscribe();
        }
    }
}