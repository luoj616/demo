package demo.luojun.com.demo.activity.model;

import demo.luojun.com.demo.interfaceimg.GuTianLe;
import demo.luojun.com.demo.interfaces.Star;

/**
 * 静态代理工厂类
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
}
