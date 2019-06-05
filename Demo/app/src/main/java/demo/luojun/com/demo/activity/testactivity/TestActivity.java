package demo.luojun.com.demo.activity.testactivity;

import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.luojun.com.demo.R;
import demo.luojun.com.demo.context.BaseActivity;

public class TestActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);

    }
    @OnClick(R.id.build_bt)
    public void buildBt(){
        basePresenter.jumpActivity(BuildActivity.class);
    }

    @OnClick(R.id.handler_bt)
    public void handlerBt(){
        basePresenter.jumpActivity(HandlerActivity.class);
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
