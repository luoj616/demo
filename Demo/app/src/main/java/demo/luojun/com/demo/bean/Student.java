package demo.luojun.com.demo.bean;

import java.io.Serializable;

/**
 * Created by luo.j on 2019/5/14.
 */

public class Student implements Serializable {
    private int age;
    private String Name;
    public void test(){}
    public Student(){};
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return Name;
    }


    public void setName(String name) {
        Name = name;
    }

    private Student(Builder builder){
       this.age= builder.age;
        this.Name = builder.name;
    }



    public static class Builder{
         private int age;
         private String name;
         public Builder(){}
         public Builder setName(String name) {
             this.name = name;
             return this;
         }
         public Builder setAge(int age){
             this.age =age;
             return this;
         }
         public Student build(){
             return new Student(this);
         }
     }
    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", Name='" + Name + '\'' +
                '}';
    }
}
