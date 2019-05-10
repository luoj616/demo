package demo.luojun.com.demo.activity.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by luo.j on 2019/5/10.
 */

public class PagerFragmentAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> mFragments;
    private String[] tabNames;

    public PagerFragmentAdapter(FragmentManager fm,List<Fragment> mFragments,String[] tabNames) {
        super(fm);
        this.mFragments =mFragments;
        this.tabNames =tabNames;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabNames[position];
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }
}
