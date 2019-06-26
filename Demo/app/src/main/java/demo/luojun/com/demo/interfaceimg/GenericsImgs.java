package demo.luojun.com.demo.interfaceimg;

import demo.luojun.com.demo.interfaces.GenericsInt;

/**
 * Created by luo.j on 2019/6/26.
 * 实现泛型接口
 * 当实现泛型接口的类，传入泛型实参时：
 */

public class GenericsImgs implements GenericsInt<String> {

    @Override
    public String next() {
        return "当实现泛型接口的类，传入泛型实参时";
    }

    @Override
    public void error(String s) {

    }
}
