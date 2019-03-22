package demo.luojun.com.demo.widget.custom;

import android.content.Context;
import android.util.AttributeSet;

import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

import demo.luojun.com.demo.R;

/**
 * Created by luo.j on 2019/3/20.
 */

public class MyListView extends ListView implements View.OnTouchListener,GestureDetector.OnGestureListener {
    private View deleteButton;
    private ViewGroup itemLayout;
    private int seleteItem;
    private boolean isDeleteShown;
    private GestureDetector gestureDetector;
    public MyListView(Context context, AttributeSet attributeSet){
        super(context,attributeSet);
        gestureDetector = new GestureDetector(context,this);
        setOnTouchListener(this);
    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(isDeleteShown){
            hideDelete();
            return  false;
        }else {
            return gestureDetector.onTouchEvent(event);
        }


    }

    @Override
    public boolean onDown(MotionEvent e) {
        if (!isDeleteShown) {
            seleteItem = pointToPosition((int) e.getX(), (int) e.getY());
            seleteItem = pointToPosition((int) e.getX(), (int) e.getY());
        }
            return false;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if(!isDeleteShown&&Math.abs(velocityX)>Math.abs(velocityY)){
            deleteButton = LayoutInflater.from(getContext()).inflate(R.layout.button_view,null);
            deleteButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    hideDelete();
                    onDeleteListener.onDelete(seleteItem);
                }
            });
            itemLayout = (ViewGroup) getChildAt(seleteItem-getFirstVisiblePosition());
            RelativeLayout.LayoutParams relativeLayout = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT);
            relativeLayout.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            relativeLayout.addRule(RelativeLayout.CENTER_VERTICAL);
            itemLayout.addView(deleteButton,relativeLayout);
            isDeleteShown =true;
        }
        return false;
    }



// 隐藏删除按钮
   public void hideDelete() {
       itemLayout.removeView(deleteButton);
              deleteButton = null;
              isDeleteShown = false;
            }



    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }




    public interface OnDeleteListener{
        void onDelete(int index);
    }
    private OnDeleteListener onDeleteListener;

    public void setOnDeleteListener(OnDeleteListener onDeleteListener) {
        this.onDeleteListener = onDeleteListener;
    }
}
