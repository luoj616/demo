package demo.luojun.com.demo.activity.networkactivity;

import android.os.Bundle;

import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.luojun.com.demo.R;
import demo.luojun.com.demo.context.BaseActivity;
import demo.luojun.com.demo.interfaces.retrofit.YGApiService;
import demo.luojun.com.demo.utils.RSAEncryptionUtil;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
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
    同步
     */
    @OnClick(R.id.retrofit_sync_bt)
    public void syncOnclick(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Retrofit retrofit = new Retrofit.Builder()
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .baseUrl("http://mobile.yougou.com")
                        .client(genericClient())
                        .build();
                YGApiService ygApiService = retrofit.create(YGApiService.class);

                try {
                    Response<String> str=ygApiService.getVersion().execute();
                    err("同步---"+str.body().toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();



    }

    /**
     * 异步
     */
    @OnClick(R.id.retrofit_async_bt)
    public void asyncOnClick(){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .baseUrl("http://mobile.yougou.com")
                .client(genericClient())
                .build();
        YGApiService ygApiService = retrofit.create(YGApiService.class);


        ygApiService.getVersion().enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String baseData = response.body().toString();
                err("异步+=="+baseData+"---");
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });


    }




    public  OkHttpClient genericClient() {

             OkHttpClient httpClient = new OkHttpClient.Builder()

                      .addInterceptor(new Interceptor() {

                     @Override

                     public okhttp3.Response intercept(Chain chain) throws IOException {

                                    Request request = chain.request()

                                                .newBuilder()
                                            .headers(initHead())


                                 /*              .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")

                                               .addHeader("Accept-Encoding", "gzip, deflate")

                                                 .addHeader("Connection", "keep-alive")



                                              .addHeader("Cookie", "add cookies here")*/

                                                .build();

                                         return chain.proceed(request);

                                    }



                    })

                     .build();



           return httpClient;

           }


    public Headers initHead(){
        String tokenStr = "860962035785914_dc:09:4c:d6:f9:63";
        String ran = System.currentTimeMillis() + "";
        String token = RSAEncryptionUtil.sign(tokenStr + "_" + ran);
        //   print(tokenStr + "  -- " + ran + "  " + token);
        //  print("test==useragin==" + getUserAgent());
        Headers.Builder builder =new Headers.Builder()
                .add("User-Agent", getUserAgent())
                .add("Udid", "dc:09:4c:d6:f9:63")//Udid=dc:09:4c:d6:f9:63
                .add("Ver", "1.0")//Ver=1.0
                .add("Os", "android")//Os=android
                .add("screenlevel", "1")//screenlevel=1
                .add("Sourceid", "YgYougouwebA59")//Sourceid
                .add("nodeCode", "")//Udid=dc:09:4c:d6:f9:63
                .add("modelType", "")//Ver=1.0
                .add("screenh", "1184")//Udid=dc:09:4c:d6:f9:63
                .add("Osversion", "5.1")//Udid=dc:09:4c:d6:f9:63
                .add("mac", "dc:09:4c:d6:f9:63")//Ver=1.0
                .add("token", token)//Udid=dc:09:4c:d6:f9:63
                .add("viewPath", "")//Ver=1.0
                .add("productLine", "vm8")//Udid=dc:09:4c:d6:f9:63
                .add("random", ran)//Ver=1.0
                .add("topicId", "")//Udid=dc:09:4c:d6:f9:63
                .add("Appkey", "9980998")//Ver=1.0
                .add("Appversion", "4.2.4")//Udid=dc:09:4c:d6:f9:63
                .add("commodityCode", "")//Ver=1.0
                .add("screenw", "720")//Udid=dc:09:4c:d6:f9:63
                .add("imei", "860962035785914")//Ver=1.0
                .add("topicName", "")//Udid=dc:09:4c:d6:f9:63
                .add("Unique", "ed00c4efa746e354f67f7eaa1ef0f20b");
        return builder.build();
    }

    private static String getUserAgent() {
        String userAgent = "优购商城 4.2.4 (HUAWEI TAG-AL00;android 5.1;zh)";
        StringBuffer sb = new StringBuffer();
        for (int i = 0, length = userAgent.length(); i < length; i++) {
            char c = userAgent.charAt(i);
            if (c <= '\u001f' || c >= '\u007f') {
                sb.append(String.format("\\u%04x", (int) c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

}
