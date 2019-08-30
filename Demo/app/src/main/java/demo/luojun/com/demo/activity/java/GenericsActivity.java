package demo.luojun.com.demo.activity.java;

import android.os.Bundle;
import android.util.Log;

import com.network.BaseBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.luojun.com.demo.R;
import demo.luojun.com.demo.activity.model.GenericsClass;
import demo.luojun.com.demo.bean.Student;
import demo.luojun.com.demo.context.BaseActivity;
import demo.luojun.com.demo.interfaceimg.GenericsImg;
import demo.luojun.com.demo.interfaceimg.GenericsImgs;
import demo.luojun.com.demo.interfaces.GenericsInt;

/**
 * 泛型
 */


public class GenericsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generics);
        ButterKnife.bind(this);


        List<Integer> gInteger = new ArrayList<>();
        gInteger.add(1);
        List<Number> gNumber = new ArrayList<Number>();
         gNumber.add(100);
        showKeyValue1(gInteger);
        showKeyValue1(gNumber);

//Error:Execution failed for task ':app:incrementalDebugJavaCompilationSafeguard'.
// java.io.IOException: Could not delete path 'D:\outproject\githup\demo\Demo\app\build\intermediates\classes\debug\demo\luojun\com\demo\fragment'.

    }
    /*\
    泛型类
     */
    @OnClick(R.id.generics_class_bt)
    public  void genericsClassOnClick(){
        GenericsClass<String> genericsClass= new GenericsClass<String>("string generics class");
        err(genericsClass.getT());

        GenericsClass genericsClass1 = new GenericsClass(false);
        err(genericsClass1.getT()+"--------------");

    }

    /**
     * 泛型接口
     */
    @OnClick(R.id.generics_interface_bt)
    public  void genericsIntOnClick(){
        GenericsInt<String> stringGenericsInt = new GenericsImgs();
        String str =stringGenericsInt.next();
        err(str);
        GenericsInt<Student> studentGenericsInt =new GenericsImg<Student>();
        err("未传入泛型实=="+studentGenericsInt.next());
    }

    /**
     * 通配符？
     */
    @OnClick(R.id.generics_tpf_bt)
    public void genericsOnClick(){
        GenericsInt<String> stringGenericsInt = new GenericsImgs();
        gen(stringGenericsInt);
        GenericsInt<Student> studentGenericsInt =new GenericsImg<Student>();
        gen(studentGenericsInt);
    }

    /**
     * 泛型方法
     */
    @OnClick(R.id.generics_method_bt)
    public void genericsMethod(){
        GenericsClass<String> genericsClass= new GenericsClass<String>("string generics class");
        genericsClass.genericsMethod(new BaseBean());
        Student student= new Student(true);
        err("泛型方法。带参数返回==="+genericsClass.genericsMethodReturn(student).toString());
        GenericsInt<String> stringGenericsInt = new GenericsImgs();

        GenericsInt<Student> studentGenericsInt =new GenericsImg<Student>();
        err("----------------泛型方法-------------------");
         genericsClass.genericsMethod(stringGenericsInt);
        genericsClass.genericsMethod(studentGenericsInt);
    }

   public void gen(GenericsInt<?> genericsInt){
       err("通配符==="+genericsInt.next());
   }
    public void showKeyValue1(List<?> obj){
        Log.d("泛型测试","key value is " + obj.get(0));
    }



}
