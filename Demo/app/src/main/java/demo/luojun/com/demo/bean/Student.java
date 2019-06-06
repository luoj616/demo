package demo.luojun.com.demo.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by luo.j on 2019/5/14.
 */

public class Student extends BaseBean {
    private int age;
    private String Name;
    private List<Course> list;
    private Map<String,String> map;

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public List<Course> getList() {
        return list;
    }

    public void setList(List<Course> list) {
        this.list = list;
    }

    public void test(){}
    public Student(){};

    public Student(boolean isInit){
        if(isInit){
            this.Name="--init Name-=";
            this.age=20;
            this.list= new ArrayList<>();
            for(int i=0;i<5;i++){
                Course course = new Course();
                course.setCourseName("course=="+i);
                this.list.add(course);
            }
        }
    };
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

    public static class Course{
        private String courseName;

        public String getCourseName() {
            return courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }
    }


    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", Name='" + Name + '\'' +
                ", list=" + list +
                ", map=" + map +
                '}';
    }
}
