package com.network.rxretrofit.loaderfactory;

import com.network.rxretrofit.serviceloader.YgServiceLoader;

/**
 * Created by luo.j on 2019/7/8.
 * 简单工厂
 * serviceloader 加载器
 */

public class YGSimpleFactory {

    public static YgServiceLoader getYgServiceLoader(){
        return  new YgServiceLoader();
    }
}
