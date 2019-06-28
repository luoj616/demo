package demo.luojun.com.demo.activity.networkactivity;

import android.os.Bundle;

import java.io.IOException;

import demo.luojun.com.demo.R;
import demo.luojun.com.demo.context.BaseActivity;
import demo.luojun.com.demo.interfaces.retrofit.YGApiService;
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
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .baseUrl("http://mobile.yougou.com")
                .build();
        YGApiService ygApiService = retrofit.create(YGApiService.class);

        try {
            Response<String> str=ygApiService.getVersion().execute();
            err("jjj---"+str.body().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        ygApiService.getVersion().enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String baseData = response.body().toString();
                err(baseData+"---");
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
//       Call<String> call =ygApiService.getVersion();
//        try {
//            String student=call.execute().body();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        err("call=="+call.toString());


    }
}
