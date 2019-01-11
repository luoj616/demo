package demo.luojun.com.demo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import demo.luojun.com.demo.R;

/**
 * Created by luo.j on 2019/1/10.
 */

public class HeadWidget extends RelativeLayout {
    public HeadWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.activity_okhttp,this);
    }
}
