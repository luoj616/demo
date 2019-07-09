package com.lj.test.singleton;

/**
 * Created by Administrator on 2019/7/9/009.
 * 单例模式。
 *饿汉式
 * 依赖 JVM类加载机制，保证单例只会被创建1次，即 线程安全
 *
 * 初始化创建
 */

public class Singleton1 {
    private static Singleton1 singleton1= new Singleton1();
    private Singleton1(){}
    public static Singleton1 getInstance(){
        return singleton1;
    }



}



/*class Singleton {

    // 1. 加载该类时，单例就会自动被创建
    private static  Singleton ourInstance  = new  Singleton();

    // 2. 构造函数 设置为 私有权限
    // 原因：禁止他人创建实例
    private Singleton() {
    }

    // 3. 通过调用静态方法获得创建的单例
    public static  Singleton newInstance() {
        return ourInstance;
    }
}
* */