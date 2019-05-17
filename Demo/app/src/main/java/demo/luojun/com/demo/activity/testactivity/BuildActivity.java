package demo.luojun.com.demo.activity.testactivity;

import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.luojun.com.demo.R;
import demo.luojun.com.demo.activity.model.Director;
import demo.luojun.com.demo.bean.Student;
import demo.luojun.com.demo.context.BaseActivity;
import demo.luojun.com.demo.interfaceimg.Builder;

/**
 * 建造者设计模式案例build
 */

public class BuildActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build);
        ButterKnife.bind(this);
    }
    @OnClick(R.id.director_bt)
    public void directorOnClick(){
        print(Director.create().toString());
    }
    @OnClick(R.id.builder_bt)
    public void builderOnClick(){
        print(new Builder().setAge(22).setName("hha").build().toString());
        print(new Builder().setAge(26).setName("meiyou.shenm").build().toString());
    }

    @OnClick(R.id.builder_studen_bt)
    public void ctorOnClick(){
        Student student=new Student();
        Student.Builder builder = new Student.Builder();
   print( new Student.Builder().setAge(100).setName("weilai").build().toString());
    }
}
