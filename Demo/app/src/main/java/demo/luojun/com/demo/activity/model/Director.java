package demo.luojun.com.demo.activity.model;

import demo.luojun.com.demo.bean.Student;
import demo.luojun.com.demo.interfaceimg.Builder;

/**
 * Created by luo.j on 2019/5/14.
 */

public class Director {
    public static Student create(){
        return new Builder().setName("我是罗军").setAge(30).build();
    }
}
