package demo.luojun.com.demo.widget.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import demo.luojun.com.demo.utils.DensityUtil;
import demo.luojun.com.demo.utils.LogPrint;

/**
 * Created by luo.j on 2019/1/10.
 */

public class CounterView extends View implements View.OnClickListener
 {

        // 定义画笔
        private Paint mPaint;
        // 用于获取文字的宽和高
        private Rect mBounds ;
        // 计数值，每点击一次本控件，其值增加1
        private int mCount;
      private Context context;

        public CounterView(Context context, AttributeSet attrs) {
            super(context, attrs);

            // 初始化画笔、Rect
            mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mBounds = new Rect();
            this.context  = context;
            // 本控件的点击事件
            setOnClickListener(this);
        }
//       public void  CounterViews(Context context,AttributeSet attributeSet ,String s){
//           mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
//           mBounds = new Rect();
//       }
//       public void t(Canvas canvas){
//           mPaint.setColor(Color.BLUE);
//           canvas.drawRect(0,0,getWidth(),getHeight(),mPaint);
//           mPaint.setColor(Color.RED);
//           mPaint.setTextSize(50);
//           String text =mCount+"";
//           mPaint.getTextBounds(text,0,text.length(),mBounds);
//           float width =mBounds.width();
//           float height = mBounds.height();
//           canvas.drawText(text,getWidth()/2-width/2,getHeight()/2-height/2,mPaint);
//       }
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            LogPrint.debugError("------onDraw--");
            mPaint.setColor(Color.BLUE);
            // 绘制一个填充色为蓝色的矩形
            canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);

            mPaint.setColor(Color.YELLOW);
            mPaint.setTextSize(50);
            String text = String.valueOf(mCount);
            // 获取文字的宽和高
            mPaint.getTextBounds(text, 0, text.length(), mBounds);
            float textWidth = mBounds.width();
            float textHeight = mBounds.height();
//        LogPrint.debugError(DensityUtil.dip2px(context,100)+"---"+context.getResources().getDisplayMetrics().density);
//        LogPrint.debugError( mCount+"=="+textWidth+"---"+textHeight);
//        LogPrint.debugError( mCount+"=="+getHeight()+"---"+getWidth());
//        LogPrint.debugError( mCount+"=="+(getWidth()/2-textWidth/2)+"-------ss-----------"+(getHeight() / 2 + textHeight / 2));
//            // 绘制字符串
            canvas.drawText(text, getWidth() / 2 - textWidth / 2, getHeight() / 2
                    + textHeight / 2, mPaint);
        }

     @Override
     protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
         super.onMeasure(widthMeasureSpec, heightMeasureSpec);
         setMeasuredDimension(300,200);
         LogPrint.debugError("------onMeasure--");
     }

     @Override
        public void onClick(View v) {
            mCount++;

            // 重绘
            invalidate();
        }

     @Override
     protected void onLayout(boolean changed, int l, int t, int r, int b) {
         super.onLayout(changed,l,t,r,b);
         LogPrint.debugError("------onLayoute--");
     }
 }
/*        LogPrint.debugError(DensityUtil.dip2px(context,100)+"---"+context.getResources().getDisplayMetrics().density);
        LogPrint.debugError(count+"=="+width+"---"+height);
        LogPrint.debugError(count+"=="+getHeight()+"---"+getWidth());
        LogPrint.debugError(count+"=="+(getWidth()/2-width/2)+"-------ss-----------"+(getHeight()/2-height/2));*/