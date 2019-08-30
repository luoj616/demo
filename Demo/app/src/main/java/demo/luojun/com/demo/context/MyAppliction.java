package demo.luojun.com.demo.context;

import android.app.Activity;
import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

import java.util.Stack;


/**
 * Created by luo.j on 2018/5/15.
 */
public class MyAppliction extends Application {
    private static MyAppliction mInstance; //application 单例对象
    public static boolean isDebug = true;
    private static Stack<Activity> activityStack;//栈对象

    @Override
    public void onCreate() {
        super.onCreate();
        //aRouter初始化
        if(isDebug){
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);

        mInstance = this;

        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(true)  //（可选）是否显示线程信息。 默认值为true
                .methodCount(2)         // （可选）要显示的方法行数。 默认2
                .methodOffset(7)        // （可选）隐藏内部方法调用到偏移量。 默认5
              // .logStrategy(customLog) //（可选）更改要打印的日志策略。 默认LogCat
                .tag("info")   //（可选）每个日志的全局标记。 默认PRETTY_LOGGER
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));

       // Logger.addLogAdapter(new AndroidLogAdapter());
//        ZXingLibrary.initDisplayOpinion(this);
//
//        PreferenceUtil.init(this);
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
        ARouter.getInstance().destroy();
    }

    public synchronized static MyAppliction getInstance() {
        return mInstance;
    }

    //添加Activity
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }


    //删除activity
    public void removeActivity(Activity activity) {
        if (activityStack == null)
            return;
        activityStack.remove(activity);
    }


    //清除所有的Activity
    public void finishAllActivity() {
        if (activityStack == null)
            return;
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }
}
