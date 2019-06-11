package demo.luojun.com.demo.activity.model;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import demo.luojun.com.demo.utils.LogPrint;

/**
 * 动态代理类
 * Created by luo.j on 2019/6/11.
 */

public class DynamicsProxy implements InvocationHandler {
    private Object object;
    public DynamicsProxy(Object object){
        this.object=object;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        LogPrint.debugError("动态代理执行的方法=="+method.getName());
         Object result =  method.invoke(object,args);
        return result;
    }
}
