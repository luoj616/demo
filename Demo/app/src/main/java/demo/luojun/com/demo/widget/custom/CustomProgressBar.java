package demo.luojun.com.demo.widget.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import demo.luojun.com.demo.R;

/**
 * Created by luo.j on 2019/4/10.
 * 自定义progressBar
 */

public class CustomProgressBar extends View {
    private int mFitstColor;//第一圈颜色
    private int mSecondColor;//第二圈颜色
    private int mCircleWidth;//圆的宽度
    private Paint mPaint;//
    private int mProgress;//当前进度
    private int mSpeed;//速度
    private boolean isNext;//是否开始下一个
    public CustomProgressBar(Context context){
        this(context,null);
    }
    public CustomProgressBar(Context context ,@Nullable AttributeSet attrs){
        this(context,attrs,0);
    }
    public CustomProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray =context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomProgressBar,defStyleAttr,0);
        int count=typedArray.getIndexCount();
        for (int i=0;i<count;i++){
            int attr = typedArray.getIndex(i);
            switch (attr){
                case R.styleable.CustomProgressBar_firstColor:
                    mFitstColor =typedArray.getColor(attr, Color.GREEN);
                    break;
                case R.styleable.CustomProgressBar_secondColor:
                    mSecondColor = typedArray.getColor(attr,Color.RED);
                    break;
                case R.styleable.CustomProgressBar_circleWidth:
                    mCircleWidth =typedArray.getDimensionPixelSize(attr,(int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_PX, 20, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.CustomProgressBar_speed:
                    mSpeed = typedArray.getInt(attr,20);
                    break;

            }
        }
        typedArray.recycle();
        mPaint = new Paint();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    mProgress=mProgress+1;
                    if(mProgress==360){
                        mProgress=0;
                        isNext= !isNext?true:false;
                    }
                    postInvalidate();
                    try {
                        Thread.sleep(mSpeed);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
       int centre = getWidth()/2;//圆心坐标
        int radius =centre-mCircleWidth/2;//半径
        mPaint.setStrokeWidth(mCircleWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        //new RectF(centre - radius, centre - radius, centre + radius, centre + radius); // 用于定义的圆弧的形状和大小的界限

        RectF oval = new RectF(centre - radius, centre - radius, centre + radius, centre + radius);
        if(!isNext){
           mPaint.setColor(mFitstColor);
            canvas.drawCircle(centre,centre,radius,mPaint);
            mPaint.setColor(mSecondColor);
            canvas.drawArc(oval, -90, mProgress, false, mPaint); // 根据进度画圆弧
        } else
        {
            mPaint.setColor(mSecondColor); // 设置圆环的颜色
            canvas.drawCircle(centre, centre, radius, mPaint); // 画出圆环
            mPaint.setColor(mFitstColor); // 设置圆环的颜色
            canvas.drawArc(oval, -90, mProgress, false, mPaint); // 根据进度画圆弧
        }



    }
}
