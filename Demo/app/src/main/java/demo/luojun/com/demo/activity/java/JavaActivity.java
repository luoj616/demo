package demo.luojun.com.demo.activity.java;

import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.luojun.com.demo.R;
import demo.luojun.com.demo.activity.java.design.DesignActivity;
import demo.luojun.com.demo.activity.java.rxactivity.ObservableActivity;
import demo.luojun.com.demo.context.BaseActivity;

public class JavaActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java);
        ButterKnife.bind(this);
    }

   //设计模式
    @OnClick(R.id.design_bt)
    public void dexsignBt(){
        basePresenter.jumpActivity(DesignActivity.class);
    }

    @OnClick(R.id.rxjava_bt)
    public void rxjavaBt(){
        basePresenter.jumpActivity(ObservableActivity.class);
    }


    @OnClick(R.id.file_io_bt)
    public void fileioOnBt(){
        basePresenter.jumpActivity(FileIoActivity.class);
    }





    @OnClick(R.id.class_bt)
    public void classBt(){
        basePresenter.jumpActivity(classActivity.class);
    }


    @OnClick(R.id.annotation_bt)
    public void annotationBt(){
        basePresenter.jumpActivity(AnnotationActivity.class);
    }

    @OnClick(R.id.javapoet_bt)
    public void javapoetBt(){
        basePresenter.jumpActivity(JavapoetActivity.class);
    }


    @OnClick(R.id.generics_bt)
    public void genericsBt(){
        basePresenter.jumpActivity(GenericsActivity.class);
    }



}
