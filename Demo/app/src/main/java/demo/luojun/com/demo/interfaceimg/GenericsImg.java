package demo.luojun.com.demo.interfaceimg;

import demo.luojun.com.demo.interfaces.GenericsInt;

/**
 * Created by luo.j on 2019/6/26.
 * 当实现泛型接口的类，未传入泛型实参时：
 */

public class GenericsImg<T> implements GenericsInt<T> {
    @Override
    public T next() {
        return null;
    }

    @Override
    public void error(T t) {

    }
}
