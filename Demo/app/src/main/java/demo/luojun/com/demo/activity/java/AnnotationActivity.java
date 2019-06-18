package demo.luojun.com.demo.activity.java;

import android.os.Bundle;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.OnClick;
import demo.luojun.com.demo.R;
import demo.luojun.com.demo.activity.model.SonAnnotation;
import demo.luojun.com.demo.context.BaseActivity;
import demo.luojun.com.demo.interfaces.annotation.Constraints;
import demo.luojun.com.demo.interfaces.annotation.DBTable;
import demo.luojun.com.demo.interfaces.annotation.ExtendAnnotation;
import demo.luojun.com.demo.interfaces.annotation.SQLInteger;
import demo.luojun.com.demo.interfaces.annotation.SQLString;

/**
 * java注解  Annotation
 */
public class AnnotationActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annotation);
    }
    @OnClick(R.id.get_annotation_bt)
    public void getAnnotationOnClick(){
        Class<?> clazz = SonAnnotation.class;

        //根据指定注解类型获取该注解
        ExtendAnnotation documentA=clazz.getAnnotation(ExtendAnnotation.class);
        print("根据指定注解类型获取该注解==="+documentA);

        //获取该元素上的所有注解，包含从父类继承
        Annotation[] annotations =clazz.getAnnotations();
        print("获取该元素上的所有注解，包含从父类继承"+ Arrays.toString(annotations));

        //获取该元素上的所有注解，但不包含继承！
        Annotation[] annotations1 = clazz.getDeclaredAnnotations();
        print("获取该元素上的所有注解，但不包含继承！"+ Arrays.toString(annotations));


        //判断注解是否在该元素上
        boolean is =clazz.isAnnotationPresent(ExtendAnnotation.class);
        print("判断注解是否在该元素上"+ Arrays.toString(annotations));

    }
    @OnClick(R.id.createtable_annotation_bt)
    public void createTableAnnotationOnClick(){
        String className = "demo.luojun.com.demo.activity.model.Member";
        try {
            print("Table Creation SQL for " +
                     className + " is :\n" + createTableSql(className));
        } catch (ClassNotFoundException e) {
            print("Table Creation SQL for :  ERROR ");
            e.printStackTrace();
        }
    }


    public String createTableSql(String className) throws ClassNotFoundException {
        Class<?> cl = Class.forName(className);
        DBTable dbTable= cl.getAnnotation(DBTable.class);
        if(dbTable==null){
            print("No DBTable annotations in class " + className);
            return null;
        }
        String tableName = dbTable.name();
        // If the name is empty, use the Class name:

        if(tableName.length() < 1)
            tableName = cl.getName().toUpperCase();

        List<String> columnDefs = new ArrayList<String>();
        //通过Class类API获取到所有成员字段
        for(Field field : cl.getDeclaredFields()) {
            String columnName = null;
            //获取字段上的注解
            Annotation[] anns = field.getDeclaredAnnotations();
            if(anns.length < 1)
                continue; // Not a db table column

            //判断注解类型
            if(anns[0] instanceof SQLInteger) {
                SQLInteger sInt = (SQLInteger) anns[0];
                //获取字段对应列名称，如果没有就是使用字段名称替代
                if(sInt.name().length() < 1)
                    columnName = field.getName().toUpperCase();
                else
                    columnName = sInt.name();
                //构建语句
                columnDefs.add(columnName + " INT" +
                        getConstraints(sInt.constraint()));
            }
            //判断String类型
            if(anns[0] instanceof SQLString) {
                SQLString sString = (SQLString) anns[0];
                // Use field name if name not specified.
                if(sString.name().length() < 1)
                    columnName = field.getName().toUpperCase();
                else
                    columnName = sString.name();
                columnDefs.add(columnName + " VARCHAR(" +
                        sString.value() + ")" +
                        getConstraints(sString.constraint()));
            }


        }
        //数据库表构建语句
        StringBuilder createCommand = new StringBuilder(
                "CREATE TABLE " + tableName + "(");
        for(String columnDef : columnDefs)
            createCommand.append("\n    " + columnDef + ",");

        // Remove trailing comma
        String tableCreate = createCommand.substring(
                0, createCommand.length() - 1) + ");";
        return tableCreate;

    }
    /**
     * 判断该字段是否有其他约束
     * @param con
     * @return
     */
    private String getConstraints(Constraints con){
        String constraints="";
        if(con.allowNull()){
            constraints = constraints+ "NOT NULL";
        }
        if(con.primaryKey())
            constraints += " PRIMARY KEY";
        if(con.unique())
            constraints += " UNIQUE";
        return constraints;
    }
}
