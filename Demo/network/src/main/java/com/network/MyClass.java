package com.network;

public class MyClass extends BaseBean {
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "MyClass{" +
                "age=" + age +
                '}'+"BaseBean{" +

                ", response='" + response + '\'' +
                '}';
    }
}
