package demo.luojun.com.demo.activity.android;

import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.luojun.com.demo.R;
import demo.luojun.com.demo.activity.android.fragmentactivity.FragmentActivity;
import demo.luojun.com.demo.activity.android.viewactivity.CustomViewActivity;
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


    @OnClick(R.id.custom_view_bt)
    public void customViewBt() {
        //startActivity(new Intent(MainActivity.this, NetworkActivity.class));
        basePresenter.jumpActivity(CustomViewActivity.class);
    }
    @OnClick(R.id.fragment_bt)
    public void fragmentBt(){
        basePresenter.jumpActivity(FragmentActivity.class);
    }
}
