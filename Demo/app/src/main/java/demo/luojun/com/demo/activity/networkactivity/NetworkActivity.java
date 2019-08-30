package demo.luojun.com.demo.activity.networkactivity;

import android.content.Intent;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;

import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.luojun.com.demo.R;
import demo.luojun.com.demo.bean.Student;
import demo.luojun.com.demo.context.ARouterConstance;
import demo.luojun.com.demo.context.BaseActivity;
@Route(path = ARouterConstance.ACTIVITY_URL_NETWORK,group = ARouterConstance.GROUP_NETWORK)
public class NetworkActivity extends BaseActivity {
    @Autowired()
    String name;
    @Autowired()
    int age;
    //@Autowired(name="student")
    Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
        ButterKnife.bind(this);
        student = (Student) getIntent().getSerializableExtra("student");
        err("name=="+name+ "   age=="+age+"   student=="+student.toString());

        setResult(124);

    }
    @OnClick(R.id.okhttp_bt)
    public void networkBt(){
        startActivity(new Intent(this, OkhttpActivity.class));
    }


    @OnClick(R.id.retrofit_bt)
    public void retrofitOnClick(){

        startActivity(new Intent(this, retrofitActivity.class));
    }
    @OnClick(R.id.rxretrofit_bt)
    public void rxretrofitOnClick(){

        startActivity(new Intent(this, RxRetrofitActivity.class));
    }
    @OnClick(R.id.okhttputils_bt)
    public void okHttpUtilsOnClick(){

            startActivity(new Intent(this, OkHttpUtilsActivity.class));
    }

    @OnClick(R.id.socket_bt)
    public void socketOnClick(){

        startActivity(new Intent(this, SocketActivity.class));
    }

    @OnClick(R.id.mvp_bt)
    public void mvpClick(){

        startActivity(new Intent(this, MVPActivity.class));
    }
}
