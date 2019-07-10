package demo.luojun.com.demo.activity.java.design;

import android.os.Bundle;

import com.lj.test.singleton.Carrier;
import com.lj.test.singleton.Singleton1;
import com.lj.test.singleton.Singleton2;
import com.lj.test.singleton.Singleton3;
import com.lj.test.singleton.StoreHouse;

import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.luojun.com.demo.R;
import demo.luojun.com.demo.context.BaseActivity;


/*
单例模式
 */
public class SingletonActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singleton);
        ButterKnife.bind(this);
    }
    @OnClick(R.id.single_bt)
    public void singletonOnClick(){
        StoreHouse storeHouse1= StoreHouse.getInstance();
        StoreHouse storeHouse2 = StoreHouse.getInstance();
        Carrier carrier1= new Carrier(storeHouse1);
        Carrier carrier2= new Carrier(storeHouse2);
        carrier1.moveIn(30);
        err("仓库商品余量："+carrier1.storeHouse.getQuantity());
        carrier2.MoveOut(50);
        err("仓库商品余量："+carrier2.storeHouse.getQuantity());
    }
    //饿汉式
    @OnClick(R.id.single_eh_bt)
    public void singletonOnClick1(){
        Singleton1 singleton1 = Singleton1.getInstance();

    }
    //懒汉式
    @OnClick(R.id.single_lanh_bt)
    public void singletonOnClick2(){
        //线程不安去的
        Singleton2 singleton2= Singleton2.getInstance();
        //线程安全的  消耗资源
        Singleton2 singleton21=Singleton2.getInstance2();
        Singleton2  singleton22 = Singleton2.getInstance3();
        //进一步改进型  烦硕
        Singleton2 singleton23 = singleton2.newInstance();

    }
    //内部静态类
    @OnClick(R.id.single_static_class_bt)
    public void singletonOnClick3(){
        Singleton3 singleton3 = Singleton3.newInstance();
    }
}
