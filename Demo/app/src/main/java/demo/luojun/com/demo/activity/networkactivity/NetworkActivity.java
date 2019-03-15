package demo.luojun.com.demo.activity.networkactivity;

import android.content.Intent;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.luojun.com.demo.R;
import demo.luojun.com.demo.context.BaseActivity;

public class NetworkActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
        ButterKnife.bind(this);
    }
    @OnClick(R.id.okhttp_bt)
    public void networkBt(){
        startActivity(new Intent(this, OkhttpActivity.class));
    }
}
