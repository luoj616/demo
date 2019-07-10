package demo.luojun.com.demo.activity.java.design;

import android.os.Bundle;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.luojun.com.demo.R;
import demo.luojun.com.demo.activity.model.ProxyFactory;
import demo.luojun.com.demo.context.BaseActivity;
import demo.luojun.com.demo.interfaceimg.GuTianLe;
import demo.luojun.com.demo.interfaceimg.LiuDeHua;
import demo.luojun.com.demo.interfaces.Star;

/**
 * Java 代理模式
 * 代理设计模式
 * https://blog.csdn.net/ShuSheng0007/article/details/80864854
 */
public class ProxyActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proxy);
        ButterKnife.bind(this);

        dynamicsProxy();

    }
    /*
    * 静态代理
    * */
    @OnClick(R.id.static_proxy_bt)
    public void staticProxyOnClick(){
        ProxyFactory.getProxy().paly("神雕侠侣——静态代理");
    }

    /**
     * 动态代理
     */
    @OnClick(R.id.dynamics_proxy_bt)
    public void dynamicsProxyOnClick(){
        Star star = (Star) ProxyFactory.getDynamicsProxy(new GuTianLe());
        star.paly("神雕侠侣-动态代理");
    }

    /**
     * 动态代理
     */
    @OnClick(R.id.cglib_proxy_bt)
    public void cblibProxyOnClick(){
//        LiuDeHua cProxy = (LiuDeHua) ProxyFactory.getCglibDynaProxy(new LiuDeHua());
//        cProxy.song("------冰雨------非接口动态代理");
//        cProxy.play("---新少林市---非接口动态代理");

        LiuDeHua target = new LiuDeHua();
       LiuDeHua cProxy=(LiuDeHua) new ProxyFactorys(target).getProxyInstance();

        //执行代理对象的方法
       cProxy.song("------冰雨------非接口动态代理");
       cProxy.play("---新少林市---非接口动态代理");
    }


    public void dynamicsProxy(){
        final Object guTianLe = new GuTianLe();
       Star star =(Star) Proxy.newProxyInstance(guTianLe.getClass().getClassLoader(), guTianLe.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                       Object results = method.invoke(guTianLe,args);
                        return results;
                    }
                });
        star.paly("--圆月弯刀--");

    }


    public class ProxyFactorys implements MethodInterceptor {
        //维护目标对象
        private Object target;

        public ProxyFactorys(Object target) {
            this.target = target;
        }

        //给目标对象创建一个代理对象
        public Object getProxyInstance(){
            //1.工具类
            Enhancer en = new Enhancer();
            //2.设置父类
            en.setSuperclass(target.getClass());
            //3.设置回调函数
            en.setCallback(this);
            //4.创建子类(代理对象)
            return en.create();

        }

        @Override
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
            System.out.println("开始事务...");

            //执行目标对象的方法
            Object returnValue = method.invoke(target, args);

            System.out.println("提交事务...");

            return returnValue;
        }
    }
}
