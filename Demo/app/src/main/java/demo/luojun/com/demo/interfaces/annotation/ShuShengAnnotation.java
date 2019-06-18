package demo.luojun.com.demo.interfaces.annotation;

import android.annotation.TargetApi;
import android.os.Build;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解
 * @Repeatable  使被修饰的注解可以重复的注解某一个程序元素

 * JDK 1.8
 * Created by luo.j on 2019/6/18.
 */



@TargetApi(Build.VERSION_CODES.N)
@Repeatable(ShuShengsAnnotation.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ShuShengAnnotation {
    String name() default "ben";
    int age();
}


