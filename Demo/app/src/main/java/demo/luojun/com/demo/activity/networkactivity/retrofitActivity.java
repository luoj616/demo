package demo.luojun.com.demo.activity.networkactivity;

import android.os.Bundle;

import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.luojun.com.demo.R;
import demo.luojun.com.demo.context.AppServerAPI;
import demo.luojun.com.demo.context.BaseActivity;
import demo.luojun.com.demo.interfaces.retrofit.YGApiService;
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
        new Thread(new Runnable() {
            @Override
            public void run() {
                Retrofit retrofit = new Retrofit.Builder()
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .baseUrl(AppServerAPI.YG_IP)
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
     * get  异步
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
     @OnClick(R.id.retrofit_post_bt)
     public void commendDetailOnClick(){
         Retrofit retrofit = new Retrofit.Builder()
                 .addConverterFactory(ScalarsConverterFactory.create())
                 .baseUrl("http://mobile.yougou.com")
                 .client(genericClient())
                 .build();
         YGApiService ygApiService=retrofit.create(YGApiService.class);
         ygApiService.getDetail("99890025").enqueue(new Callback<String>() {
             @Override
             public void onResponse(Call<String> call, Response<String> response) {
                 String baseData = response.body().toString();
                 err("post 异步+=="+baseData+"---");
             }

             @Override
             public void onFailure(Call<String> call, Throwable t) {

             }
         });
     }


    public  OkHttpClient genericClient() {

             OkHttpClient httpClient = new OkHttpClient.Builder()
                    .addInterceptor(interceptorHeader)
                     .addInterceptor(interceptorLog)
                     /* .addInterceptor(new Interceptor() {

                     @Override

                     public okhttp3.Response intercept(Chain chain) throws IOException {

                                    Request requestLog = chain.request()

                                                .newBuilder()
                                            .headers(initHead())


                                 *//*              .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")

                                               .addHeader("Accept-Encoding", "gzip, deflate")

                                                 .addHeader("Connection", "keep-alive")



                                              .addHeader("Cookie", "add cookies here")*//*

                                                .build();
                         //这个chain里面包含了request和response，所以你要什么都可以从这里拿

                         long t1 = System.nanoTime();//请求发起的时间

                         String method = requestLog.method();
                         if ("POST".equals(method)) {
                             StringBuilder sb = new StringBuilder();
                             if (requestLog.body() instanceof FormBody) {
                                 FormBody body = (FormBody) requestLog.body();
                                 for (int i = 0; i < body.size(); i++) {
                                     sb.append(body.encodedName(i) + "=" + body.encodedValue(i) + ",");
                                 }
                                 sb.delete(sb.length() - 1, sb.length());
                                 err(String.format("发送请求 %s on %s %n%s %nRequestParams:{%s}",
                                         requestLog.url(), chain.connection(), requestLog.headers(), sb.toString()));
                             }
                         } else {
                             err(String.format("发送请求 %s on %s%n%s",
                                     requestLog.url(), chain.connection(), requestLog.headers()));
                         }
                         okhttp3.Response response = chain.proceed(requestLog);
                         long t2 = System.nanoTime();//收到响应的时间
                         //这里不能直接使用response.body().string()的方式输出日志
                         //因为response.body().string()之后，response中的流会被关闭，程序会报错，我们需要创建出一
                         //个新的response给应用层处理
                         ResponseBody responseBody = response.peekBody(1024 * 1024);
                         err(
                                 String.format("接收响应: [%s] %n返回json:【%s】 %.1fms %n%s",
                                         response.request().url(),
                                         responseBody.string(),
                                         (t2 - t1) / 1e6d,
                                         response.headers()
                                 ));

                                         return response;

                                    }



                    })*/

                     .build();



           return httpClient;

           }
    private  Interceptor interceptorLog = new Interceptor(){

         @Override
         public okhttp3.Response intercept(Chain chain) throws IOException {
             //这个chain里面包含了request和response，所以你要什么都可以从这里拿
             Request request = chain.request();
             long t1 = System.nanoTime();//请求发起的时间

             String method = request.method();
             if ("POST".equals(method)) {
                 StringBuilder sb = new StringBuilder();
                 if (request.body() instanceof FormBody) {
                     FormBody body = (FormBody) request.body();
                     for (int i = 0; i < body.size(); i++) {
                         sb.append(body.encodedName(i) + "=" + body.encodedValue(i) + ",");
                     }
                     sb.delete(sb.length() - 1, sb.length());
                     err(String.format("发送请求 %s on %s %n%s %nRequestParams:{%s}",
                             request.url(), chain.connection(), request.headers(), sb.toString()));
                 }
             } else {
                 err(String.format("发送请求 %s on %s%n%s",
                         request.url(), chain.connection(), request.headers()));
             }
             okhttp3.Response response = chain.proceed(request);
             long t2 = System.nanoTime();//收到响应的时间
             //这里不能直接使用response.body().string()的方式输出日志
             //因为response.body().string()之后，response中的流会被关闭，程序会报错，我们需要创建出一
             //个新的response给应用层处理
             ResponseBody responseBody = response.peekBody(1024 * 1024);
             err(
                     String.format("接收响应: [%s] %n返回json:【%s】 %.1fms %n%s",
                             response.request().url(),
                             responseBody.string(),
                             (t2 - t1) / 1e6d,
                             response.headers()
                     ));
             return response;
         }
     };
    Interceptor interceptorHeader = new Interceptor(){

        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Request requestLog = chain.request()
                    .newBuilder()
                    .headers(initHead())
                                 /*              .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                                               .addHeader("Accept-Encoding", "gzip, deflate")
                                                 .addHeader("Connection", "keep-alive")
                                              .addHeader("Cookie", "add cookies here")*/

                    .build();
            okhttp3.Response response = chain.proceed(requestLog);
            return response;
        }
    };
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
