package demo.luojun.com.demo.activity.android.viewactivity;

import android.os.Bundle;

import demo.luojun.com.demo.R;
import demo.luojun.com.demo.context.BaseActivity;

/**
 * 自定义view
 * 1:自定义view
 * 2:自定义progrogressBar
 */

public class CustomTitleViewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_title_view);
    }
}
