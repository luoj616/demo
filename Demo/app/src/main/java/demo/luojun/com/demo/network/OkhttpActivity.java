package demo.luojun.com.demo.network;

import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.luojun.com.demo.R;
import demo.luojun.com.demo.context.BaseActivity;
import demo.luojun.com.demo.persenter.BaseOkHttpPersenter;
import demo.luojun.com.demo.view.OkhttpView;
import okhttp3.OkHttpClient;

public class OkhttpActivity extends BaseActivity implements OkhttpView {
    private BaseOkHttpPersenter baseOkHttpPersenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
        ButterKnife.bind(this);
        baseOkHttpPersenter =new BaseOkHttpPersenter();
        baseOkHttpPersenter.attachView(this);
        print("test");
        OkHttpClient okHttpClient = new OkHttpClient();
    }
    @OnClick(R.id.get_okhttp_bt)
    public void onclickGetOkhttp(){
        print("test");
        baseOkHttpPersenter.okhttpGet();

    }

    @Override
    public void view(String info) {
        print(info);


    }
}
