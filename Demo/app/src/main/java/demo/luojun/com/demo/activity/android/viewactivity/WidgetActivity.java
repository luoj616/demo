package demo.luojun.com.demo.activity.android.viewactivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import demo.luojun.com.demo.R;
import demo.luojun.com.demo.context.BaseActivity;
import demo.luojun.com.demo.utils.LogPrint;
import demo.luojun.com.demo.widget.custom.CounterView;

public class WidgetActivity extends BaseActivity {
   private CounterView counterView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widget);
        counterView = (CounterView) findViewById(R.id.counter_view);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.main_layout);
        LogPrint.debugError(counterView.getMeasuredHeight()+"-------*****---------------"+counterView.getMeasuredWidth());

        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View view = layoutInflater.inflate(R.layout.button_view,null,false);
        LayoutInflater layoutInfater1 = LayoutInflater.from(this);
        View view1 =layoutInfater1.inflate(R.layout.activity_main,null,false);
        LayoutInflater layoutInflater1 = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            relativeLayout.addView(view);
        err("------------------------"+relativeLayout.getParent());
        LayoutInflater flater1 = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        err("------------------------");
        err("-----dispatchTouchEvent-");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        err("----onTouchEvent----");
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                LogPrint.debugError("按下------------");
                break;
            case MotionEvent.ACTION_MOVE:
                LogPrint.debugError("移动——————————————————————————");
                break;
            case MotionEvent.ACTION_UP:
                LogPrint.debugError("刚开---------");
                break;
        }
        return false;
    }

    @Override
    protected void onStart() {
        super.onStart();

      //  LogPrint.debugError(counterView.getMeasuredHeight()+"-------*****---ONSTATT------------"+counterView.getMeasuredWidth());

    }

    @Override
    protected void onResume() {
        super.onResume();
      //  LogPrint.debugError(counterView.getMeasuredHeight()+"-------*****---ONRESUME------------"+counterView.getMeasuredWidth());
    }
}
