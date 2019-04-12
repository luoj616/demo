package demo.luojun.com.demo.widget.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import demo.luojun.com.demo.R;

/**
 * Created by luo.j on 2019/4/12.
 */

/*
* 自定义圆背景TextView
* */
public class CustomCircleText extends View {
    private int mTextSize;//文字大小
    private int mTextColor;//文字颜色
    private int mBackgroudColor;//圆的背景颜色
    private int mRound;//圆的大小
    private String mText;
    private Rect rect;

    private Paint mPaint;
    public CustomCircleText(Context context) {
        this(context,null);
    }

    public CustomCircleText(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public CustomCircleText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray =context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomCircleText,defStyleAttr,0);
        mTextSize =typedArray.getDimensionPixelSize(R.styleable.CustomCircleText_mTextSize,(int)
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 14, getResources().getDisplayMetrics()));
        mTextColor=typedArray.getColor(R.styleable.CustomCircleText_mTextColor, Color.BLACK);
        mBackgroudColor = typedArray.getColor(R.styleable.CustomCircleText_mBackGroudColor,Color.YELLOW);
        mRound = typedArray.getDimensionPixelSize(R.styleable.CustomCircleText_mRound,(int)
                (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,5,getResources().getDisplayMetrics())));
        mText = typedArray.getString(R.styleable.CustomCircleText_mText);
        typedArray.recycle();
        mPaint = new Paint();
        rect = new Rect();


    }
    int height =40;
    int width =40;
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int widthMode =MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if(widthMode==MeasureSpec.EXACTLY){
            width =widthSize;
        }else {
            width =Math.min(width,widthSize);
        }
        if(heightMode == MeasureSpec.EXACTLY){
            height = heightSize;
        }else{
            height = Math.min(height,heightSize);
        }
        setMeasuredDimension(width,height);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setColor(mBackgroudColor);
        Log.e("info", getMeasuredWidth()/2+"----=====onDraw: "+getMeasuredHeight()/2 );
        canvas.drawCircle(getMeasuredWidth()/2,getMeasuredHeight()/2,getMeasuredWidth()/2,mPaint);

        mPaint.setColor(mTextColor);
        mPaint.setTextSize(mTextSize);
        mPaint.getTextBounds(mText,0,mText.length(),rect);
        DisplayMetrics dm = getContext().getResources().getDisplayMetrics();
        Log.e("info", dm.density+"=====onDraw: "+getWidth()+"---"+getMeasuredWidth() );
        Log.e("info", "onDraw: "+getWidth()+"---"+rect.width());
        Log.e("info", "onDraw: "+getWidth()+"---"+(getWidth()/2-rect.width()/2));
        Log.e("info", "onDraw: "+getHeight()+"-2--"+rect.height());
        Log.e("info", mText+"onDraw: "+getMeasuredHeight()+"---"+(getMeasuredHeight()/2+rect.height()/2));
        canvas.drawText(mText,
                (getWidth()/2-rect.width()/2),(getMeasuredHeight()/2+rect.height()/2),mPaint);
    }
}
