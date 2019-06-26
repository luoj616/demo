package demo.luojun.com.demo.activity.model;

/**
 * Created by luo.j on 2019/6/26.
 */

import demo.luojun.com.demo.interfaces.GenericsInt;
import demo.luojun.com.demo.utils.LogPrint;

/**
 * 泛型类
 * class 类名称 <泛型标识：可以随便写任意标识号，标识指定的泛型的类型>{
 * private 泛型标识 （成员变量类型） var;
 *       .....
        *
        *      }
        *      }
 */
public class GenericsClass<T> {
    private T t;
    public GenericsClass(T t){
        this.t = t;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    /**
     * 泛型方法
     * @param t
     * @param <T>
     */
    public <T> void genericsMethod(T t){

        LogPrint.debugError("泛型方法=="+t.toString());
    }

    /**
     * 泛型方法
     * @param t
     * @param <T>
     * @return
     */
    public <T> T genericsMethodReturn(T t){

        return t;
    }

    /**
     * 泛型方法
     * @param t
     * @param <T>
     */
    public <T> void genericsMethod(GenericsInt<T> t){
         t.next();
    }
}
