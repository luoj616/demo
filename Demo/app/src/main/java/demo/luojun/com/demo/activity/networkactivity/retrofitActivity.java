package demo.luojun.com.demo.activity.networkactivity;

import android.os.Bundle;

import com.network.RetrofitResponse;
import com.network.retrofit.RetrofitRequest;

import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.luojun.com.demo.R;
import demo.luojun.com.demo.context.BaseActivity;


public class retrofitActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        ButterKnife.bind(this);
//        Retrofit retrofit = new Retrofit.Builder().build();
//        YGApiService ygApiService =retrofit.create(YGApiService.class);
//        observe(ygApiService.getVersion()).map(new Func1<String , String>() {
//            @Override
//            public String call(String s) {
//                //可以处理对应的逻辑后在返回
//                return s;
//            }
//        });

    }
    /*protected  <T> Observable<T> observe(Observable<T> observable){
        return observable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
 /*
    *//*
     get 同步
     */
    @OnClick(R.id.retrofit_sync_bt)
    public void syncOnclick(){
        RetrofitRequest retrofitRequest= new RetrofitRequest();
        retrofitRequest.getVersion(new RetrofitResponse() {
            @Override
            public void getSuccess(String result) {

            }

            @Override
            public void getFail(String mes) {

            }
        });



    }

    /**
     * get  异步
     */
    @OnClick(R.id.retrofit_async_bt)
    public void asyncOnClick(){



    }
     @OnClick(R.id.retrofit_post_bt)
     public void commendDetailOnClick(){
         RetrofitRequest retrofitRequest= new RetrofitRequest();
         retrofitRequest.getDetail("99890025", new RetrofitResponse() {
             @Override
             public void getSuccess(String result) {
                 err("post 异步+=="+result+"---");
             }

             @Override
             public void getFail(String mes) {

             }
         });

     }



}
