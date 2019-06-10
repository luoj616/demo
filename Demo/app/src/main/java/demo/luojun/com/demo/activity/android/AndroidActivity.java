package demo.luojun.com.demo.activity.android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.luojun.com.demo.R;
import demo.luojun.com.demo.context.BaseActivity;

public class AndroidActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.handler_bt)
    public void handlerBt(){
        basePresenter.jumpActivity(HandlerActivity.class);
    }
}
