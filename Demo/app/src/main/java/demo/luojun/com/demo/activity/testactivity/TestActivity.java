package demo.luojun.com.demo.activity.testactivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.luojun.com.demo.R;
import demo.luojun.com.demo.activity.networkactivity.OkhttpActivity;
import demo.luojun.com.demo.context.BaseActivity;

public class TestActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);

    }
    @OnClick(R.id.build_bt)
    public void buildBt(){
        basePresenter.jumpActivity(BuildActivity.class);
    }

    @OnClick(R.id.handler_bt)
    public void handlerBt(){
        basePresenter.jumpActivity(HandlerActivity.class);
    }









}
