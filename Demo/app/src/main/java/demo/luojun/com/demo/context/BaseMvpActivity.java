package demo.luojun.com.demo.context;

import android.content.Context;
import android.os.Bundle;

import demo.luojun.com.demo.network.persenter.BaseNewPresenter;
import demo.luojun.com.demo.network.view.BaseView;

/**
 * mvp 模式。。。
 * @param <P>
 */

public abstract class BaseMvpActivity<P extends BaseNewPresenter> extends BaseNewActivity implements BaseView {
    public P persenter;

    @Override
    public void initBefore(Bundle savedInstanceState) {
        super.initBefore(savedInstanceState);
        persenter =  initPresener();
        persenter.addView(this);

    }
    public abstract  P initPresener();

    @Override
    public Context getContext() {
        return this;
    }
}
