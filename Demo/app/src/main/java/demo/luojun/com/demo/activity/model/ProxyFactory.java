package demo.luojun.com.demo.activity.model;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

import java.lang.reflect.Proxy;

import demo.luojun.com.demo.interfaceimg.GuTianLe;
import demo.luojun.com.demo.interfaces.Star;

/**
 * 静态工厂类
 * Created by Administrator on 2019/6/10/010.
 */

public class ProxyFactory {
    /**
     * 获取静态代理
     * @return
     */
    public static Star getProxy(){
        return new Agent(new GuTianLe());
    }

    /**
     * 获取动态代理
     * @param object
     * @return
     */
    public static Object getDynamicsProxy(Object object){
        DynamicsProxy dynamicsProxy =new DynamicsProxy(object);
        return Proxy.newProxyInstance(object.getClass().getClassLoader(),
                object.getClass().getInterfaces(),dynamicsProxy);

    }

    /**
     * 获取非接口动态代理
     * @param object
     * @return
     */
    public static Object getCglibDynaProxy(Object target){
        Enhancer enhancer=new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(new CglibProxy());
        Object targetProxy= enhancer.create();
        return targetProxy;

    }
}
