package demo.luojun.com.demo.persenter;

import java.io.IOException;

import demo.luojun.com.demo.context.AppServerAPI;
import demo.luojun.com.demo.view.OkhttpView;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by luo.j on 2018/6/11.
 */
public class BaseOkHttpPersenter extends BasePresenter<OkhttpView> {
    public void okhttpGet(){


        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().get().url(AppServerAPI.OKHTTP_GET).build();
      final   Call call = okHttpClient.newCall(request);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                    Response response =   call.execute();
                   print(response.body().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();


//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                print("----error---");
//            }
//
//            @Override
//            public void onResponse(Call call,  Response response) throws IOException {
//                print("------------success-----------------------------");
//              final   String str =  response.body().string();
//
//              print(str);
//                getThisActivity().runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        getView().view(str);
//                        print(str);
//                   //         toast(str);
//
//                    }
//                });
//
//
//            }
//        });
    }


}
