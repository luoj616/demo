package demo.luojun.com.demo.interfaces;

import demo.luojun.com.demo.bean.Student;

/**
 * 建造者模式build
 * Created by luo.j on 2019/5/14.
 */

public interface Build {
    Build setAge(int age);
    Build setName(String name);


    Student build();
}
