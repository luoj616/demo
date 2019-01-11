package demo.luojun.com.demo.utils;


import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;


public class DensityUtil {
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 后台数据转dp再转px
     * return px
     */
    public static int server2px(Context context, float serverH) {
        final float serverDp = serverH / 2.0f;
        return dip2px(context, serverDp);
    }

    /**
     * 后台数据转dp再转px
     * return px
     */
    public static int server2px(Context context, float serverW, float serverH) {
        final float h = serverH * 720 / serverW;
        return server2px(context, h);
    }

    /**
     * 屏幕宽度
     *
     * @param mContext
     * @return
     */
    public static int getScreenWidth(Context mContext) {
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;         // 屏幕宽度（像素）
        return width;
    }


    /**
     * px转换sp
     */
    public static int px2sp(Context mContext,int pxValue) {
        final float fontScale = mContext.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * sp转换px
     */
    public static int sp2px(Context mContext,int spValue) {
        final float fontScale = mContext.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);

    }
}




/**
 * 获得屏幕的宽度和高度有很多种方法

 //1、通过WindowManager获取
 DisplayMetrics dm = new DisplayMetrics();
 heigth = dm.heightPixels;
 width = dm.widthPixels;

 //2、通过Resources获取
 DisplayMetrics dm = getResources().getDisplayMetrics();
 heigth = dm.heightPixels;
 width = dm.widthPixels;

 //3、获取屏幕的默认分辨率
 Display display = getWindowManager().getDefaultDisplay();
 heigth = display.getWidth();
 width = display.getHeight();

 //4、通过类直接取
 getWindowManager().getDefaultDisplay().getMetrics(dm);


 第一种和第三种都是使用getWindowManager()得到的,但这个是建立在类Activity上的，如果自己的类没有继承这两个,则取不到数据,故个人认为通过Resources获取最好。
 ---------------------

 */