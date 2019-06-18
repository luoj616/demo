package demo.luojun.com.demo.activity.model;

import demo.luojun.com.demo.interfaces.annotation.Constraints;
import demo.luojun.com.demo.interfaces.annotation.DBTable;
import demo.luojun.com.demo.interfaces.annotation.SQLInteger;
import demo.luojun.com.demo.interfaces.annotation.SQLString;

/**
 * Created by luo.j on 2019/6/18.
 * 数据库表Member对应实例类bean
 */
@DBTable(name = "MEMBER")
public class Member {

    //主键
    @SQLString(name = "ID",value = 50 ,constraint = @Constraints(primaryKey = true))
   private  String id;
    //名字
    @SQLString(name = "NAME" ,value = 30)
    private String name;

    //年龄age
    @SQLInteger(name = "AGE")
    private int age;

    //描述
    @SQLString(name = "DESCRIPTION",value = 150,constraint = @Constraints(allowNull = true))
    private String description;
}

