package demo.luojun.com.demo.activity.java;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;


import java.io.IOException;
import java.lang.reflect.Modifier;

import butterknife.ButterKnife;
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
        ButterKnife.bind(this);
    }

    public void helloword(){
        MethodSpec methodSpec=MethodSpec.methodBuilder("main")


                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(TypeName.VOID)
                .addParameter(String[].class,"args")
                .addStatement("$T,out.println($S)",System.class,"hello,javapoet")
                .build();
        TypeSpec typeSpec =TypeSpec.classBuilder("HelloWord")
                .addModifiers(Modifier.PUBLIC)
                .addMethod(methodSpec)
                .build();
        JavaFile javaFile =JavaFile.builder("demo.luojun.com.demo.activity.java",typeSpec)
                .build();
        try {
            javaFile.writeTo(System.out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        MethodSpec main = MethodSpec.methodBuilder("main")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(void.class)
                .addParameter(String[].class, "args")
                .addStatement("$T.out.println($S)", System.class, "Hello, JavaPoet!")
                .build();

    }
}
