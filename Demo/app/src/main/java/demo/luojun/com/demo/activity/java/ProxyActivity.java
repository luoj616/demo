package demo.luojun.com.demo.activity.java;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.luojun.com.demo.R;
import demo.luojun.com.demo.activity.model.ProxyFactory;
import demo.luojun.com.demo.context.BaseActivity;

/**
 * Java 代理模式
 * 代理设计模式
 * https://blog.csdn.net/ShuSheng0007/article/details/80864854
 */
public class ProxyActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proxy);
        ButterKnife.bind(this);
    }
    /*
    * 静态代理
    * */
    @OnClick(R.id.static_proxy_bt)
    public void staticProxyOnClick(){
        ProxyFactory.getProxy().paly("神雕侠侣");
    }
}
