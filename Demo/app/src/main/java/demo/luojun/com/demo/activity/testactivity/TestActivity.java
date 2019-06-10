package demo.luojun.com.demo.activity.testactivity;

import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.luojun.com.demo.R;
import demo.luojun.com.demo.activity.android.AndroidActivity;
import demo.luojun.com.demo.activity.android.HandlerActivity;
import demo.luojun.com.demo.activity.java.BuildActivity;
import demo.luojun.com.demo.activity.java.FileIoActivity;
import demo.luojun.com.demo.activity.java.JavaActivity;
import demo.luojun.com.demo.activity.java.classActivity;
import demo.luojun.com.demo.context.BaseActivity;

public class TestActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);

    }
    @OnClick(R.id.java_bt)
    public void buildBt(){
        basePresenter.jumpActivity(JavaActivity.class);
    }



    @OnClick(R.id.android_bt)
    public void fileioOnBt(){
        basePresenter.jumpActivity(AndroidActivity.class);
    }









}
