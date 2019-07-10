package demo.luojun.com.demo.activity.android.fragmentactivity;

import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.luojun.com.demo.R;
import demo.luojun.com.demo.context.BaseActivity;

public class FragmentActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        ButterKnife.bind(this);
    }
    @OnClick(R.id.fragment_guide_bt)
    public void guideJumpBt(){
        basePresenter.jumpActivity(GuideActivity.class);
    }

    @OnClick(R.id.viewpager_bt)
    public void viewpagerJumpBt(){
        basePresenter.jumpActivity(ViewPagerActivity.class);
    }
}
