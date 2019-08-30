package demo.luojun.com.demo.activity.networkactivity;

import android.os.Bundle;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.luojun.com.demo.R;
import demo.luojun.com.demo.context.BaseMvpActivity;
import demo.luojun.com.demo.network.persenter.MvpPersenter;
import demo.luojun.com.demo.network.view.MvpView;

public class MVPActivity extends BaseMvpActivity<MvpPersenter> implements MvpView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        ButterKnife.bind(this);


    }
   @OnClick(R.id.mvp_detail)
   public void detail(){
       persenter.detail("99890025");
   }

   @BindView(R.id.mvp_sendmsg_et)
    EditText ed;
   @OnClick(R.id.mvp_sendmsg_bt)
   public void sendMsg(){
       persenter.sendMsg(ed.getText().toString());
   }

    @OnClick(R.id.mvp_login)
    public void login(){
        persenter.login();
    }
    @Override
    public void test() {
     err("llllllllllllllllllllllllllllllllllll");
    }

    @Override
    public MvpPersenter initPresener() {
        return new MvpPersenter();
    }
}
