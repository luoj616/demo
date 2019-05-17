package demo.luojun.com.demo.activity.networkactivity;

import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.luojun.com.demo.R;
import demo.luojun.com.demo.context.BaseActivity;
import demo.luojun.com.demo.network.persenter.OkhttpPersenter;
import demo.luojun.com.demo.network.view.OkhttpView;

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

//        Message message= new Message();
//        message.what=1;
//        handler.sendMessage(message);


     //   OkHttpClient okHttpClient = new OkHttpClient();

    }

    @OnClick(R.id.get_okhttp_bt)
    public void onclickGetOkhttp(){

        okhttpPersenter.okhttpGet();

    }
    @OnClick(R.id.get_yg_bt)
    public void onclickYougouOkhttp(){

        okhttpPersenter.okhttpYG();

    }
    @OnClick(R.id.get_yg_catebt)
    public void onclickYougouCateOkhttp(){

        okhttpPersenter.okhttpYGCate();

    }
    @OnClick(R.id.get_yg_version_bt)
    public void onclickYougouVersionhttp(){

        okhttpPersenter.okhttpYGVersion();

    }
    @OnClick(R.id.get_yg_detail_bt)
    public void onclickYougouDetailOkhttp(){
        print("test");
        okhttpPersenter.okhttpYGDetail();

    }

    @Override
    public void view(String info) {
        print(info);


    }
}
