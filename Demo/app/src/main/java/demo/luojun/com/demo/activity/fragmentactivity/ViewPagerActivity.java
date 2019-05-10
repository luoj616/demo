package demo.luojun.com.demo.activity.fragmentactivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.luojun.com.demo.R;
import demo.luojun.com.demo.activity.adapter.PagerFragmentAdapter;
import demo.luojun.com.demo.context.BaseActivity;
import demo.luojun.com.demo.fragment.testfragment.CMyOrderListPagerFragment;
import demo.luojun.com.demo.widget.TabPageIndicator;

/**
 *viewpager+fragemnt
 */

public class ViewPagerActivity extends BaseActivity {
    @BindView(R.id.view_pager)
    ViewPager mPager;
   @BindView(R.id.view_pager_Indicator)
    TabPageIndicator mIndicator;
    @BindView(R.id.tv_item_one)
    TextView oneItemTv;
    @BindView(R.id.tv_item_two)
    TextView twoItemTv;
    @BindView(R.id.tv_item_three)
    TextView threeItemTv;
    public final String tabName[] = { "全部", "待付款", "已付款", "待付款", "已付sss款" };

    private List<Fragment> mFragments ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        mFragments = new ArrayList<Fragment>();
        for(int i=0;i<tabName.length;i++){
            CMyOrderListPagerFragment orderListFragments = CMyOrderListPagerFragment.newInstance("1",tabName[i]);
            mFragments.add(orderListFragments);
        }
        PagerFragmentAdapter mAdapter = new PagerFragmentAdapter(getSupportFragmentManager(),mFragments,tabName);
        mPager.setAdapter(mAdapter);
        mIndicator.setViewPager(mPager);
        mIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

        });
        mIndicator.notifyDataSetChanged();

        int index = getIntent().getIntExtra("index", 0);
        mIndicator.setCurrentItem(index);
    }

    @OnClick(R.id.tv_item_one)
    public void oneItemOnClick(){

    }
    @OnClick(R.id.tv_item_two)
    public void twoItemOnClick(){

    }
    @OnClick(R.id.tv_item_three)
    public void threeItemOnClick(){

    }
}
