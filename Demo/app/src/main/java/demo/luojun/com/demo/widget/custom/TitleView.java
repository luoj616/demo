package demo.luojun.com.demo.widget.custom;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import demo.luojun.com.demo.R;

/**
 * Created by luo.j on 2019/3/20.
 */

public class TitleView extends FrameLayout {
    private Button leftButton;

    private TextView titleText;

    public TitleView(Context context, AttributeSet attributeSet){
        super(context,attributeSet);
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view  =layoutInflater.inflate(R.layout.title,this);
        leftButton = (Button) findViewById(R.id.button_left);
        titleText = (TextView) findViewById(R.id.title_text);
        leftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity)getContext()).finish();
            }
        });

    }
    public void setLeftButtonText(String text){
        if(TextUtils.isEmpty(text))
            return;
        leftButton.setText(text);
    }
    public void setTitleText(String text){
        titleText.setText(text);
    }
    public void setLeftButtonOnClick(OnClickListener onClick){
        if(onClick==null)
            return;
        leftButton.setOnClickListener(onClick);
    }

}
