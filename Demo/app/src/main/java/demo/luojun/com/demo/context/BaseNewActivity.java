package demo.luojun.com.demo.context;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.orhanobut.logger.Logger;

import demo.luojun.com.demo.utils.LogPrint;
import demo.luojun.com.demo.utils.ToastUtils;

public class BaseNewActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        priintLog("所在页面newbase--oncreate===——————"+getClass().getSimpleName());

          initBefore(savedInstanceState);

    }
    public void initBefore(Bundle savedInstanceState) {

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

}
