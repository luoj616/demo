package demo.luojun.com.demo.context;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.orhanobut.logger.Logger;

import demo.luojun.com.demo.utils.ToastUtils;
import demo.luojun.com.demo.view.BaseView;

public class BaseActivity extends AppCompatActivity implements BaseView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    /**
     * 输入信息
     * @param info 日志信息
     */
    protected void print(String info) {
        //if (AppContext.isTest)
       Logger.e( info);

    }

    protected void toast(String info){
        ToastUtils.showToast(this, info);
    }
    @Override
    public Context getContext() {
        return this;
    }
}
