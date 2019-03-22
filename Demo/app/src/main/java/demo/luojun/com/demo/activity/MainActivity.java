package demo.luojun.com.demo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.luojun.com.demo.R;
import demo.luojun.com.demo.activity.viewactivity.MotionEventActivity;
import demo.luojun.com.demo.activity.viewactivity.MyListviewActivity;
import demo.luojun.com.demo.activity.viewactivity.SimpleLayoutActivity;
import demo.luojun.com.demo.activity.viewactivity.TitleActivity;
import demo.luojun.com.demo.context.BaseActivity;
import demo.luojun.com.demo.activity.networkactivity.NetworkActivity;
import demo.luojun.com.demo.activity.viewactivity.WidgetActivity;

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

    @OnClick(R.id.event_bt)
    public void motionEventBt() {
        //startActivity(new Intent(MainActivity.this, NetworkActivity.class));
        basePresenter.jumpActivity(MotionEventActivity.class);
    }


    @OnClick(R.id.test_bt)
    public void test() {
        Toast.makeText(this,"test",Toast.LENGTH_LONG).show();
        jump();
        startActivity(new Intent(this, WidgetActivity.class));
    }
    @OnClick(R.id.simple_layout_bt)
    public void simplaLayout() {
        Toast.makeText(this,"test",Toast.LENGTH_LONG).show();
        jump();
        startActivity(new Intent(this, SimpleLayoutActivity.class));
    }
    @OnClick(R.id.title_view_bt)
    public void titleView(){
        basePresenter.jumpActivity(TitleActivity.class);
    }
    @OnClick(R.id.my_listview_bt)
    public  void myListView (){
        basePresenter.jumpActivity(MyListviewActivity.class);
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
