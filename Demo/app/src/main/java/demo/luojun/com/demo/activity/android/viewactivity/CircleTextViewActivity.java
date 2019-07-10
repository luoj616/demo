package demo.luojun.com.demo.activity.android.viewactivity;

import android.os.Bundle;

import demo.luojun.com.demo.R;
import demo.luojun.com.demo.context.BaseActivity;

/**
 * 圆背景textView
 * https://blog.csdn.net/qq_16131393/article/details/52441000
 * 1:第一种自定义圆
 * 2:继承textView
 * 3:shape
 */
public class CircleTextViewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_text_view);
    }
}
