package demo.luojun.com.demo.context;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.orhanobut.logger.Logger;

import java.util.Map;

import demo.luojun.com.demo.network.persenter.BasePresenter;
import demo.luojun.com.demo.utils.LogPrint;
import demo.luojun.com.demo.utils.ToastUtils;
import demo.luojun.com.demo.network.view.BaseView;

public class BaseActivity extends AppCompatActivity implements BaseView {
      protected BasePresenter basePresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        priintLog("所在页面--oncreate===——————"+getClass().getSimpleName());
        basePresenter = new BasePresenter() {
            @Override
            protected void sendRequest(String url, Map map, int sendCode) {

            }
        };
        basePresenter.attachView(this);


    }

     /**
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
    protected void err(String info){
        LogPrint.debugError(info);
    }

    protected void toast(String info){
        ToastUtils.showToast(this, info);
    }
    @Override
    public Context getContext() {
        return this;
    }
}
