package demo.luojun.com.demo.activity;

import android.content.Intent;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;

import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.luojun.com.demo.R;
import demo.luojun.com.demo.activity.android.AndroidActivity;
import demo.luojun.com.demo.activity.java.JavaActivity;
import demo.luojun.com.demo.bean.Student;
import demo.luojun.com.demo.context.ARouterConstance;
import demo.luojun.com.demo.context.BaseActivity;

@Route( path =ARouterConstance.ACTIVITY_URL_MAIN,group = ARouterConstance.GROUP_MAIN)
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
        ARouter.getInstance().build(ARouterConstance.ACTIVITY_URL_NETWORK,ARouterConstance.GROUP_NETWORK)
                .withSerializable("student",new Student(true))
                .withString("name","luojun")
                .withInt("age",30)
                .navigation(this,/*startActivityForResult==*/123, new NavCallback() {
                    @Override
                    public void onArrival(Postcard postcard) {
                        err("目标抵达是调用--onFound"+"--goroup=="+postcard.getGroup()+"--path=="+postcard.getPath());
                    }

                    @Override
                    public void onFound(Postcard postcard) {
                        super.onFound(postcard);
                        err("目标被发现时调用---onFound"+"--goroup=="+postcard.getGroup()+"--path=="+postcard.getPath());
                    }


                    @Override
                    public void onLost(Postcard postcard) {
                        super.onLost(postcard);
                        err("路由丢失时调用---onFound");
                    }

                    @Override
                    public void onInterrupt(Postcard postcard) {
                        super.onInterrupt(postcard);
                        err("路由被拦截时调用---onFound");
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        err("返回跳转====="+requestCode+"---"+resultCode);
    }

    @OnClick(R.id.java_bt)
    public void buildBt(){
        basePresenter.jumpActivity(JavaActivity.class);
    }



    @OnClick(R.id.android_bt)
    public void fileioOnBt(){
        basePresenter.jumpActivity(AndroidActivity.class);
    }



    @OnClick(R.id.test_bt)
    public void testBt(){
       // basePresenter.jumpActivity(TestActivity.class);
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
