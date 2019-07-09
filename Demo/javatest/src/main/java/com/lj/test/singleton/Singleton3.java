package com.lj.test.singleton;

/**
 * Created by Administrator on 2019/7/10/010.
 * 单例模式  静态内部类
 * 原理
 根据 静态内部类 的特性，同时解决了按需加载、线程安全的问题，同时实现简洁
 */

public class Singleton3 {
    private Singleton3(){}
    private static class SingletonHolder{
        private static  Singleton3  singleton3 = new Singleton3();
    }

    public static Singleton3 newInstance(){
        return SingletonHolder.singleton3;
    }
}


/*
* class Singleton {

    // 1. 创建静态内部类
    private static class Singleton2 {
       // 在静态内部类里创建单例
      private static  Singleton ourInstance  = new Singleton()；
    }

    // 私有构造函数
    private Singleton() {
    }

    // 延迟加载、按需创建
    public static  Singleton newInstance() {
        return Singleton2.ourInstance;
    }

}

// 调用过程说明：
      // 1. 外部调用类的newInstance()
      // 2. 自动调用Singleton2.ourInstance
       // 2.1 此时单例类Singleton2得到初始化
       // 2.2 而该类在装载 & 被初始化时，会初始化它的静态域，从而创建单例；
       // 2.3 由于是静态域，因此只会JVM只会加载1遍，Java虚拟机保证了线程安全性
      // 3. 最终只创建1个单例*/
