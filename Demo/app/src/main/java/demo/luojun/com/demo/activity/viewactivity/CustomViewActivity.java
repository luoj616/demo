package demo.luojun.com.demo.activity.viewactivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.luojun.com.demo.R;
import demo.luojun.com.demo.context.BaseActivity;

import static android.R.attr.button;

/**
 * 自定义view 列表activity
 */
public class CustomViewActivity extends BaseActivity {
    @BindView(R.id.event_bt)
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
        ButterKnife.bind(this);

//        GradientDrawable gradientDrawable = (GradientDrawable) button.getBackground();
//        gradientDrawable.setColor(Color.parseColor("#6ABD20"));
//        gradientDrawable.setStroke(1,Color.parseColor("#6ABD20"));
    }
    @OnClick(R.id.event_bt)
    public void motionEventBt() {
        //startActivity(new Intent(MainActivity.this, NetworkActivity.class));
        basePresenter.jumpActivity(MotionEventActivity.class);
    }


    @OnClick(R.id.test_bt)
    public void test() {
        Toast.makeText(this,"test",Toast.LENGTH_LONG).show();

        startActivity(new Intent(this, WidgetActivity.class));
    }
    @OnClick(R.id.simple_layout_bt)
    public void simplaLayout() {
        Toast.makeText(this,"test",Toast.LENGTH_LONG).show();

        startActivity(new Intent(this, SimpleLayoutActivity.class));
    }
    @OnClick(R.id.title_view_bt)
    public void titleView(){
        basePresenter.jumpActivity(TitleActivity.class);
    }

    /**
     * 自定义view
     */
    @OnClick(R.id.custom_title_view_bt)
    public void customTileView(){
        basePresenter.jumpActivity(CustomTitleViewActivity.class);
    }

    @OnClick(R.id.my_listview_bt)
    public  void myListView (){
        basePresenter.jumpActivity(MyListviewActivity.class);
    }

    /*
      圆背景Textview
     */
    @OnClick(R.id.circle_textview_bt)
    public  void circleTextView (){
        basePresenter.jumpActivity(CircleTextViewActivity.class);
    }
}
