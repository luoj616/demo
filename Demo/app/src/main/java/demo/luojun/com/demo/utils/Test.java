package demo.luojun.com.demo.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.CLASS;

/**
 * Created by Administrator on 2019/6/15/015.
 */


@Target(METHOD)
@Retention(CLASS)
public @interface Test{

}
