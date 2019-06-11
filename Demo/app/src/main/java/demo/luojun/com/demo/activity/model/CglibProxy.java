package demo.luojun.com.demo.activity.model;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;

import demo.luojun.com.demo.utils.LogPrint;

/**
 * 非接口代理
 * 定义拦截器，在调用目标方法时，
 * CGLib会回调MethodInterceptor接口方法拦截，来实现你自己的代理逻辑，类似于JDK中的InvocationHandler接口。
 * Created by luo.j on 2019/6/11.
 */

public class CglibProxy implements MethodInterceptor{
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        LogPrint.debugError("-非接口动态代理 参数==-"+ Arrays.toString(objects));
       Object result = methodProxy.invokeSuper(o,objects);
        return result;
    }
}
