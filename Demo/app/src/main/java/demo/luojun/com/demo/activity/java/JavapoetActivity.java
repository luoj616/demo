package demo.luojun.com.demo.activity.java;

import android.os.Bundle;

import com.lj.test.MainTest;

import demo.luojun.com.demo.R;
import demo.luojun.com.demo.context.BaseActivity;

/**
 * 代码生成jar报 javapoet
 */
public class JavapoetActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_javapoet);
       // ButterKnife.bind(this);

       // helloword();
    }

    public void helloword(){
        MainTest test=new MainTest();
     //   test.helloword();




    }
}
