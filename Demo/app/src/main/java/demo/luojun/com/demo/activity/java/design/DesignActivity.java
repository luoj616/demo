package demo.luojun.com.demo.activity.java.design;

import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.luojun.com.demo.R;
import demo.luojun.com.demo.context.BaseActivity;

/**
 * 设计模式
 */

public class DesignActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design);
        ButterKnife.bind(this);
    }
    //单例模式
    @OnClick(R.id.singleton_bt)
    public void SingletonBt(){
        basePresenter.jumpActivity(SingletonActivity.class);
    }
   //建造者模式
    @OnClick(R.id.build_bt)
    public void buildBt(){
        basePresenter.jumpActivity(BuildActivity.class);
    }
   //代理模式
    @OnClick(R.id.proxy_bt)
    public void handlerBt(){
        basePresenter.jumpActivity(ProxyActivity.class);
    }
    //工厂模式
    @OnClick(R.id.simple_factory_bt)
    public void simpleFactoryBt(){
        basePresenter.jumpActivity(FactoryActivity.class);
    }
}
