package demo.luojun.com.demo.activity.networkactivity;

import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.luojun.com.demo.R;
import demo.luojun.com.demo.activity.model.Director;
import demo.luojun.com.demo.context.BaseActivity;

public class OkHttpUtilsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http_utils);
        ButterKnife.bind(this);
    }

}
