package demo.luojun.com.demo.activity.java;

import android.os.Bundle;

import com.network.MyClass;
import com.network.rxretrofit.loaderfactory.YGSimpleFactory;

import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.luojun.com.demo.R;
import demo.luojun.com.demo.context.BaseActivity;
import rx.functions.Action1;

/**
 * 工厂模式
 */
public class FactoryActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_factory);
        ButterKnife.bind(this);
    }

    /**
     * 工厂模式
     */
    @OnClick(R.id.simple_factory_act_bt)
    public void simpleFactoryOnClick(){
        YGSimpleFactory.getYgServiceLoader().getVersion()
                .subscribe(new Action1<MyClass>() {
                    @Override
                    public void call(MyClass myClass) {
                            err("结果==="+myClass.toString());
                    }
                });
    }
}
