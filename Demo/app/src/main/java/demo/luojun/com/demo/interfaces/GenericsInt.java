package demo.luojun.com.demo.interfaces;

/**
 * 泛型接口
 * Created by luo.j on 2019/6/26.
 */

public interface GenericsInt<T> {

       T  next();

       void error(T t);
}
