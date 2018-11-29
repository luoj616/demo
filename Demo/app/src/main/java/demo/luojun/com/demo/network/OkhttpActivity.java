package demo.luojun.com.demo.network;

import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.luojun.com.demo.R;
import demo.luojun.com.demo.context.BaseActivity;
import demo.luojun.com.demo.persenter.OkhttpPersenter;
import demo.luojun.com.demo.view.OkhttpView;
import okhttp3.OkHttpClient;

public class OkhttpActivity extends BaseActivity implements OkhttpView {
    private OkhttpPersenter okhttpPersenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
        ButterKnife.bind(this);
        okhttpPersenter =new OkhttpPersenter();
        okhttpPersenter.attachView(this);
        print("test");
     //   OkHttpClient okHttpClient = new OkHttpClient();
    }
    @OnClick(R.id.get_okhttp_bt)
    public void onclickGetOkhttp(){
        print("test");
      okhttpPersenter.okhttpGet();

    }

    @Override
    public void view(String info) {
        print(info);


    }
}
