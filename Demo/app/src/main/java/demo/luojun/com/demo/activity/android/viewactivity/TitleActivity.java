package demo.luojun.com.demo.activity.android.viewactivity;

import android.os.Bundle;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import demo.luojun.com.demo.R;
import demo.luojun.com.demo.context.BaseActivity;
import demo.luojun.com.demo.widget.custom.TitleView;

public class TitleActivity extends BaseActivity {
    @BindView(R.id.title_view)
    TitleView titleView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);
        ButterKnife.bind(this);
        titleView.setLeftButtonText("hha");
        titleView.setLeftButtonOnClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                titleView.setTitleText("TEST");
            }
        });
    }
}
