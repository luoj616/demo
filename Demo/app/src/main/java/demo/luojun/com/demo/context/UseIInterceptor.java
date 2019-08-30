package demo.luojun.com.demo.context;

import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;

/**
 * Created by luo.j on 2019/8/22.
 */

/**
 * ARouter 拦截器
 */
@Interceptor(priority = 11)
public class UseIInterceptor implements IInterceptor {
    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        Log.e(ARouterConstance.TAG, " priority = 11----useIInterceptor_当前线程---- "+Thread.currentThread().getName() );
        if(postcard.getPath().equals(ARouterConstance.ACTIVITY_URL_NETWORK)){
            Log.e(ARouterConstance.TAG, UseSecondIInterceptor.class.getName()+" 进行了拦截  ");
        }
        callback.onContinue(postcard);
    }

    @Override
    public void init(Context context) {
        Log.e(ARouterConstance.TAG, " priority = 11----useIInterceptor " );
    }
}
