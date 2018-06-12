package demo.luojun.com.demo.network;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.luojun.com.demo.R;

public class NetworkActivity extends AppCompatActivity {

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
