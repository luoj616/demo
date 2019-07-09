package com.lj.test.singleton;

/**
 * Created by Administrator on 2019/7/9/009.
 * d单例模式
 * 懒汉式
 * 饿汉式：单例创建时机不可控，即类加载时 自动创建 单例
 懒汉式：单例创建时机可控，即有需要时，才 手动创建 单例
 */

public class Singleton2 {
    private static Singleton2 singleton2;
    private Singleton2(){}

    //缺点 基础实现的懒汉式是线程不安全的  1
    public static Singleton2 getInstance(){
        if(singleton2==null){
            singleton2=new Singleton2();
        }
        return singleton2;
    }
    //改进线程不安全     2
    public synchronized static Singleton2 getInstance2(){
        if(singleton2==null){
            singleton2=new Singleton2();
        }
        return singleton2;
    }
    //效果跟2 一样。只是不同的写法      3
    //每次访问都要进行线程同步（即 调用synchronized锁)，造成过多的同步开销（加锁 = 耗时、耗能）
    public static Singleton2 getInstance3(){
        synchronized (Singleton2.class){
            if(singleton2==null){
                singleton2=new Singleton2();
            }
        }
        return singleton2;
    }

     //改进 2和3   双重校验锁（懒汉式的改进）  4
    //原理
   // 在同步锁的基础上，添加1层 if判断：若单例已创建，则不需再执行加锁操作就可获取实例，从而提高性能
    public static  Singleton2 newInstance(){
        if(singleton2==null) {
            synchronized (Singleton2.class) {
                if (singleton2 == null) {
                    singleton2 = new Singleton2();
                }
            }
        }
        return singleton2;
    }


}


/*
* class Singleton {
    // 1. 类加载时，先不自动创建单例
   //  即，将单例的引用先赋值为 Null
    private static  Singleton ourInstance  = null；

    // 2. 构造函数 设置为 私有权限
    // 原因：禁止他人创建实例
    private Singleton() {
    }

    // 3. 需要时才手动调用 newInstance（） 创建 单例
    public static  Singleton newInstance() {
    // 先判断单例是否为空，以避免重复创建
    if( ourInstance == null){
        ourInstance = new Singleton();
        }
        return ourInstance;
    }
}*/
