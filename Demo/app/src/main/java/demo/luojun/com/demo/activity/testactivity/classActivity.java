package demo.luojun.com.demo.activity.testactivity;

import android.os.Bundle;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.luojun.com.demo.R;
import demo.luojun.com.demo.bean.Student;
import demo.luojun.com.demo.context.BaseActivity;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * java  反射
 */

public class classActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);
        ButterKnife.bind(this);
    }
    @OnClick(R.id.filed_bt)
    public void filedOnClick(){
        Student student = new Student(true);
        Class cls = Student.class;
        try {
            student.a=1000;
            Field field= cls.getField("a");
            Field f= cls.getDeclaredField("list");
            Field name =cls.getDeclaredField("Name");
            Field age =cls.getDeclaredField("age");
            name.setAccessible(true);
            age.setAccessible(true);
            err(field.getName()+"-------------"+field.getType().getName()+"---"+field.getGenericType());
            err(f.getName()+"-------------"+f.getType()+"---"+f.getGenericType());
            err("----------------------------------"+field.getInt(student));

                err("value=="+name.get(student)+"--age--"+age.getInt(student));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

    }

    /**
     * 方法属性
     */
    @OnClick(R.id.mothed_type_bt)
    public void mothedTypeOnClick(){
        Class cls = Student.class;
        err("----------************************-----------------------------------");
        try {
            Method methods[] = cls.getDeclaredMethods();



            for ( Method m : methods ) {
                err("_____****_______________--method name:"+m.getName()+"--"+m.getReturnType());



                Class[] pTypes = m.getParameterTypes();

                err(m.getExceptionTypes()+"method para types:"+pTypes.length);
                for ( Class type : pTypes ) {
                    err(" "+ type.getName());
                }


                Type[] gTypes = m.getGenericParameterTypes();
                err("---------method para generic types:"+gTypes.length);
                for ( Type type : gTypes ) {
                    err(" "+ type);
                }
                err("---------------------------------------------");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 方法调用
     */
    @OnClick(R.id.invoke_bt)
    public void invokeOnClick(){
        Student student = new Student(true);
        Class cls = Student.class;
        try {
            Method methodSetAge =cls.getMethod("setAge",int.class);
            methodSetAge.invoke(student,10000000);
            err("setAge=="+student.getAge());

            Method methodGetName=cls.getMethod("getName");
            String name = (String) methodGetName.invoke(student);
            err("getName----------"+name);

            Method methodGetList =cls.getMethod("getList");
            List<Student.Course> list = (List<Student.Course>) methodGetList.invoke(student);
            Observable.from(list).subscribe(new Action1<Student.Course>() {
                @Override
                public void call(Student.Course course) {
                     err("getLIST =="+course.getCourseName());
                }
            });

            Method methodSetList =cls.getMethod("setList",List.class);
            List<Student.Course> listTest =new ArrayList<>();
            for(int i=0;i<3;i++){
                Student.Course c=new Student.Course();
                c.setCourseName("HHA---"+i);
            }
            methodSetList.invoke(student,listTest);
            Observable.from(student.getList()).subscribe(new Action1<Student.Course>() {
                @Override
                public void call(Student.Course course) {
                    err("setLIST =="+course.getCourseName());
                }
            });



            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    @OnClick(R.id.constructor_bt)
    public void Constructor(){
        Class cls = Student.class;
        Constructor[] constructors = cls.getConstructors();
        Constructor[] constructors1 = cls.getDeclaredConstructors();
        Observable.from(constructors).subscribe(new Action1<Constructor>() {
            @Override
            public void call(Constructor constructor) {
               err("不包括默认构造器=="+constructor.toString());
            }
        });
        Observable.just(cls)
                .flatMap(new Func1<Class, Observable<Constructor>>() {
                    @Override
                    public Observable<Constructor> call(Class aClass) {
                        return Observable.from(aClass.getDeclaredConstructors());
                    }
                }).subscribe(new Action1<Constructor>() {
            @Override
            public void call(Constructor constructor) {
                err("包括默认构造器=="+constructor.toString());
            }
        });


        try {
            Constructor constr=cls.getConstructor(boolean.class);

                Student s= (Student) constr.newInstance(true);
             err("带参数=="+s.getName()+"---"+s.getAge());
            Constructor constr2=cls.getDeclaredConstructor();
            Student s2= (Student) constr2.newInstance();
            err("***带参数=="+s2.getName()+"---"+s2.getAge());
            Constructor constr1 =cls.getConstructor();

            Student s1 = (Student) constr1.newInstance();
            err("no带参数=="+s1.getName()+"---"+s1.getAge());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }

    public void getObjectClass(){

        try {
            Class cls = Student.class;
            Class nameCls = Class.forName("demo.luojun.com.demo.bean.Student");
            Student student = new Student(false);
            Class objectCls = student.getClass();

              Student student1= (Student) cls.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
            catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

    }
}
