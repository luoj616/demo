package demo.luojun.com.demo.activity.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by luo.j on 2019/5/8.
 */

public class ViewPagerAdapter extends PagerAdapter {
    private List<ImageView> list;
    public ViewPagerAdapter(List<ImageView> list){
        this.list = list;
    }
    /**
     * 获取当前要显示对象的数量
     */
    @Override
    public int getCount() {
        return list.size();
    }
    /**
     * 判断是否用对象生成界面
     */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    /**
     * 从ViewGroup中移除当前对象（图片）
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(list.get(position));
    }

    /**
     * 当前要显示的对象（图片）
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(list.get(position));
        return list.get(position);
    }
}
