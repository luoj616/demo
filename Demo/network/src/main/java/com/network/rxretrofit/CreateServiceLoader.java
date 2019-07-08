package com.network.rxretrofit;

/**
 * Created by luo.j on 2019/7/8.
 */

public interface CreateServiceLoader<T> {
    T getService(Class<T> t);

}
