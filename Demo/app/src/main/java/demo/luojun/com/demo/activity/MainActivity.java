package demo.luojun.com.demo.activity;

import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.luojun.com.demo.R;
import demo.luojun.com.demo.activity.fragmentactivity.FragmentActivity;
import demo.luojun.com.demo.activity.networkactivity.NetworkActivity;
import demo.luojun.com.demo.activity.rxactivity.ObservableActivity;
import demo.luojun.com.demo.activity.testactivity.TestActivity;
import demo.luojun.com.demo.activity.viewactivity.CustomViewActivity;
import demo.luojun.com.demo.context.BaseActivity;

public class MainActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.network_bt)
    public void networkBt() {
        //startActivity(new Intent(MainActivity.this, NetworkActivity.class));
        basePresenter.jumpActivity(NetworkActivity.class);
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



    @OnClick(R.id.test_bt)
    public void testBt(){
        basePresenter.jumpActivity(TestActivity.class);
    }
    @OnClick(R.id.rxjava_bt)
    public void rxjavaBt(){
        basePresenter.jumpActivity(ObservableActivity.class);
    }


    public void jump() {
//        Intent i = new Intent(Intent.ACTION_VIEW);
//        String guanzhu_URL = "http://weixin.qq.com/r/o3W_sRvEMSVOhwrSnyCH"; //这是你公共帐号的二维码的实际内容。可以用扫描软件扫一下就得到了。这是我的公共帐号地址。
//
//        i.setData(Uri.parse(guanzhu_URL)); //设置要传递的内容。
//
//        i.setPackage("com.tencent.mm"); //直接制定要发送到的程序的包名。也可以不制定。就会弹出程序选择器让你手动选木程序。
//
//     //   i.putExtra(Intent.EXTRASUBJECT,"Share"); i.setFlags(Intent.FLAGACTIVITYNEWTASK);
//
//        startActivity(i); //当然要在Activity界面 调用了。
}

}
