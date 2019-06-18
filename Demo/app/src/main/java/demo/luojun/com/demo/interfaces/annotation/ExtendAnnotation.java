package demo.luojun.com.demo.interfaces.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解
 * 元注解 @Inherited 其让被修饰的注解拥有被继承的能力
 * Created by luo.j on 2019/6/18.
 */

@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public  @interface ExtendAnnotation {

}
