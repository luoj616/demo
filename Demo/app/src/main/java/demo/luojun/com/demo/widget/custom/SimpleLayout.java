package demo.luojun.com.demo.widget.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by luo.j on 2019/3/20.
 */

public class SimpleLayout extends ViewGroup {
    public SimpleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
       // setMeasuredDimension(200,300);
        if(getChildCount()>0){
            View view =getChildAt(0);
            measureChild(view,widthMeasureSpec,heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
          if(getChildCount()>0){
              View childView =getChildAt(0);
              childView.layout(0,0,childView.getMeasuredWidth(),childView.getMeasuredHeight());
          }
    }
}
