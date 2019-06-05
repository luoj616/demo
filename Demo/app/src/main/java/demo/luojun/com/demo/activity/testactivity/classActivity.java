package demo.luojun.com.demo.activity.testactivity;

import android.os.Bundle;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.luojun.com.demo.R;
import demo.luojun.com.demo.bean.Student;
import demo.luojun.com.demo.context.BaseActivity;

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

    public void getObjectClass(){

        try {
            Class cls = Student.class;
            Class nameCls = Class.forName("demo.luojun.com.demo.bean.Student");
            Student student = new Student();
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
