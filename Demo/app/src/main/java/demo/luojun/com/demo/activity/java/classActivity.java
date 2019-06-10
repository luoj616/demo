package demo.luojun.com.demo.activity.java;

import android.os.Bundle;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

public class classActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);
        ButterKnife.bind(this);
    }

    /**
     * Class 属性 field
     */
    @OnClick(R.id.filed_bt)
    public void filedOnClick() {
        Student student = new Student(true);
        Class cls = Student.class;
        try {
            err( "-----------属性名和类型------------"  );
            student.a=1000;
            Field field = cls.getField("a");
            err(  "------访问属性非私有属性 可以是父类的：--------");
             err("名+类型+泛型类型："+field.getName() +" "+ field.getType().getName() + "---" + field.getGenericType());
            Field f = cls.getDeclaredField("list");
            err("访问private属性 不能是父类的");
            err("名+类型+泛型类型："+f.getName() +" "  + f.getType() + "---" + f.getGenericType());



            err( "-----------属性设置和得到值-------------"  );
            Field ageField = cls.getDeclaredField("age");
            ageField.setAccessible(true);
            err( "原来的值 age==：" +ageField.getInt(student) );
           ageField.setInt(student,1000000);
          int age=ageField.getInt(student);
            err( "设置后的值 age==：" +age );
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

    }

    /**
     * Class 方法method属性
     */
    @OnClick(R.id.mothed_type_bt)
    public void mothedTypeOnClick() {
        Class cls = Student.class;
        /**-----------------------------**/
            /*获取方法的属性，。*/
        err("--");
        err("--------------获取方法属性--------------------");
        try {
            Method methods[] = cls.getDeclaredMethods();

            for (Method m : methods) {
                err("获取方法名+方法的修饰符+方法返回类型:"
                        + m.getName() +"--"+  Modifier.toString(m.getModifiers())+" "+ m.getReturnType());

                Class[] pTypes = m.getParameterTypes();
                err( "----获取方法的传参:" + pTypes.length);
                for (Class type : pTypes) {
                    err(" " + type.getName());
                }

                Type[] gTypes = m.getGenericParameterTypes();
                err("----获取方法的传参,包括泛型:" + gTypes.length);
                for (Type type : gTypes) {
                    err(" " + type);
                }
                err("--");
                err("------------*****end ****-----------------");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * class方法method调用invoke
     */
    @OnClick(R.id.invoke_bt)
    public void invokeOnClick() {
        Student student = new Student(true);
        Class cls = Student.class;
        try {
            /**-----------------------------**/
            /*反射调用方法，。*/
            err("--");
            err("--------------反射调用方法--------------------");
            Method methodSetAge = cls.getMethod("setAge", int.class);
            methodSetAge.invoke(student, 10000000);
            err("setAge==" + student.getAge());



            /**-----------------------------**/
            /*反射调用方法，不需要传参。*/
            err("--");
            err("--------------反射调用方法，不需要传参。--------------------");
            Method methodGetName = cls.getMethod("getName");
            String name = (String) methodGetName.invoke(student);
            err("getName----------" + name);


            /**-----------------------------**/
            /*反射调用方法，获取返回值。*/
            err("--");
            err("--------------反射调用方法，获取返回值。--------------------");
            Method methodGetList = cls.getMethod("getList");
            List<Student.Course> list = (List<Student.Course>) methodGetList.invoke(student);
            Observable.from(list).subscribe(new Action1<Student.Course>() {
                @Override
                public void call(Student.Course course) {
                    err("getLIST ==" + course.getCourseName());
                }
            });


            /**-----------------------------**/
            /*反射调用方法，传入list类型。*/
            err("--");
            err("--------------反射调用方法，传入list类型--------------------");
            List<Student.Course> listTest = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                Student.Course c = new Student.Course();
                c.setCourseName("HHA---" + i);
                listTest.add(c);
            }

            Method methodSetList = cls.getMethod("setList", List.class);
            methodSetList.invoke(student, listTest);
            Observable.from(student.getList()).subscribe(new Action1<Student.Course>() {
                @Override
                public void call(Student.Course course) {
                    err("setLIST ==" + course.getCourseName());
                }
            });


            /**-----------------------------**/
            /*反射调用方法，传入map类型。*/
            err("--");
            err("--------------反射调用方法，传入map类型--------------------");
            Map<String,String> mapTest= new HashMap<>();
            mapTest.put("1","map1");
            mapTest.put("2","map2");
            Method methodMap= cls.getMethod("setMap", Map.class);
            methodMap.invoke(student,mapTest);
            err("mapTest"+student.getMap().toString());

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Class 构造器  constructor
     */
    @OnClick(R.id.constructor_bt)
    public void Constructor() {
        Class cls = Student.class;

        err("  -----获取所有构造器------");
        Constructor[] constructors = cls.getConstructors();

        Observable.from(constructors).subscribe(new Action1<Constructor>() {
            @Override
            public void call(Constructor constructor) {
                err("不包括默认构造器==" + constructor.toString());
            }
        });
        err("  -----获取所有构造器，包括默认构造器------");
        Observable.just(cls)
                .flatMap(new Func1<Class, Observable<Constructor>>() {
                    @Override
                    public Observable<Constructor> call(Class aClass) {
                        return Observable.from(aClass.getDeclaredConstructors());
                    }
                }).subscribe(new Action1<Constructor>() {
            @Override
            public void call(Constructor constructor) {
                err("包括默认构造器==" + constructor.toString());
            }
        });


        try {
            err("------------------------------------");
            err("  -----通过构造器。创建对象-----");
            Constructor constr = cls.getConstructor(boolean.class);
            Student s = (Student) constr.newInstance(true);
            err("带参数的构造器。创建的对象==" + s.toString());
            err("--");

            Constructor constr2 = cls.getDeclaredConstructor();
            Student s2 = (Student) constr2.newInstance();
            err("无参构造器，可以通过默认构造--getDeclaredConstructor");
            err( s2.toString());
            err("--");


            Constructor constr1 = cls.getConstructor();
            Student s1 = (Student) constr1.newInstance();
            err("无参构造器，不可以通过默认构造器穿件，必须类中写有无参构造器--getConstructor()");
            err( s1.toString());
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

    /**
     * Class 获取
     */
    public void getObjectClass() {

        try {
            Class cls1 = Student.class;

            Class nameCls2 = Class.forName("demo.luojun.com.demo.bean.Student");

            Student student = new Student(false);
            Class objectCls3 = student.getClass();

            Student student1 = (Student) cls1.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
