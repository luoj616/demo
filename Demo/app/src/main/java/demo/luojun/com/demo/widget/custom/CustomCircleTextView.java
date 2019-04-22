package demo.luojun.com.demo.widget.custom;

import android.content.Context;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;


/**
 * Created by luo.j on 2019/4/15.
 * 继承textVie.修改背景为圆形
 *
 */

public class CustomCircleTextView extends android.support.v7.widget.AppCompatTextView{
    private Rect rect;
    private Paint paint;
   public   CustomCircleTextView(Context context){
       super(context);

   }
    public CustomCircleTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint=new Paint();
    paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);//抗锯齿
        paint.setDither(true);//防抖动


    }
    private int height =48;
    private int width = 48;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode =  MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if(widthMode==MeasureSpec.EXACTLY||heightMode==MeasureSpec.EXACTLY){
            width= widthSize;
            height = heightSize;
            width = Math.min(width,height);

        }
        setMeasuredDimension(width,width);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(getWidth()/2,getHeight()/2,Math.max(getWidth(), getHeight())/2,paint);
        super.onDraw(canvas);
    }
}
