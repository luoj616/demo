package demo.luojun.com.demo.activity.viewactivity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import demo.luojun.com.demo.R;
import demo.luojun.com.demo.context.BaseActivity;
import demo.luojun.com.demo.utils.LogPrint;

public class MotionEventActivity extends BaseActivity {
    @BindView(R.id.touch_event_bt)
    Button touchEventBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motion_event);
        ButterKnife.bind(this);
        touchEventBt.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
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
        });
        touchEventBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogPrint.debugError("onclik----------------------");
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        err("--------------------------------dfsadf------");
        return true;
    }


    class MyOnGestureListener extends GestureDetector {
        public MyOnGestureListener(OnGestureListener listener, Handler handler) {
            super(listener, handler);
        }
    }
}
