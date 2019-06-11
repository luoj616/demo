package demo.luojun.com.demo.activity.java;

import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.luojun.com.demo.R;
import demo.luojun.com.demo.context.BaseActivity;

public class JavaActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.build_bt)
    public void buildBt(){
        basePresenter.jumpActivity(BuildActivity.class);
    }

    @OnClick(R.id.proxy_bt)
    public void handlerBt(){
        basePresenter.jumpActivity(ProxyActivity.class);
    }

    @OnClick(R.id.file_io_bt)
    public void fileioOnBt(){
        basePresenter.jumpActivity(FileIoActivity.class);
    }





    @OnClick(R.id.class_bt)
    public void fieldBt(){
        basePresenter.jumpActivity(classActivity.class);
    }
}
