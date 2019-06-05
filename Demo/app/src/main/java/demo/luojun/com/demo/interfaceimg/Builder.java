package demo.luojun.com.demo.interfaceimg;

import demo.luojun.com.demo.bean.Student;
import demo.luojun.com.demo.interfaces.Build;

/**
 * Created by luo.j on 2019/5/14.
 */

public class Builder implements Build {
    private int  age;
    private String name;
    private Student student;

    @Override
    public Build setAge(int age) {
        this.age = age;
        return this;

    }

    @Override
    public Build setName(String name) {
        this.name = name;
        return this;

    }

    public Student build(){
        student= new Student(false);
        student.setAge(age);
        student.setName(name);
        return student;
    }
}
