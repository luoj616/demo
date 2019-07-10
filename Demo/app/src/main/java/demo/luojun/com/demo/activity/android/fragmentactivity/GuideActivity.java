package demo.luojun.com.demo.activity.android.fragmentactivity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import demo.luojun.com.demo.R;
import demo.luojun.com.demo.activity.adapter.ViewPagerAdapter;
import demo.luojun.com.demo.context.BaseActivity;



/**
 *
 * desc 引导页
 */

public class GuideActivity extends BaseActivity {
    @BindView(R.id.guide_ViewPager)
    ViewPager viewPager;
    @BindView(R.id.dot_Layout)
    LinearLayout layout;
    private List<ImageView> imageViews;
    private ImageView[] dotViews;
    public static int[] imags= new int[]{R.mipmap.ic_guide_1,R.mipmap.ic_guide_2,R.mipmap.ic_guide_3,R.mipmap.ic_guide_4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for(int i=0;i<dotViews.length;i++){
                    if(position==i){
                        dotViews[i].setSelected(true);
                    }else{
                        dotViews[i].setSelected(false);
                    }
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        initImages();
        initDots();
        ViewPagerAdapter viewPageAdapter = new ViewPagerAdapter(imageViews);
        viewPager.setAdapter(viewPageAdapter);
    }

    private void initDots() {
        LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(20,20);
        mParams.setMargins(12,0,12,0);
        dotViews= new ImageView[imags.length];
       for(int i=0;i<dotViews.length;i++){
           ImageView iv = new ImageView(this);
           iv.setLayoutParams(mParams);
           iv.setImageResource(R.drawable.selector_dot);
           if(i==0){
               iv.setSelected(true);
           }else {
               iv.setSelected(false);
           }
           dotViews[i]=iv;
           layout.addView(iv);
       }
    }

    private void initImages() {
        ViewPager.LayoutParams mParams =new ViewPager.LayoutParams();
        imageViews = new ArrayList<ImageView>();
        for(int img:imags){
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(mParams);
            imageView.setImageResource(img);
            imageView.setAdjustViewBounds(true);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageViews.add(imageView);

        }
    }
}
