package demo.luojun.com.demo.activity.networkactivity;

import android.os.Bundle;

import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.luojun.com.demo.R;
import demo.luojun.com.demo.context.AppServerAPI;
import demo.luojun.com.demo.context.BaseActivity;

import com.network.RetrofitResponse;
import com.network.retrofit.RetrofitRequest;
import com.network.retrofit.service.YGApiService;
import demo.luojun.com.demo.utils.RSAEncryptionUtil;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class retrofitActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        ButterKnife.bind(this);


    }

    /*
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
