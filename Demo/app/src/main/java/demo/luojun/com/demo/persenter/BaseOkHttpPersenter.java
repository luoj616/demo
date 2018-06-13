package demo.luojun.com.demo.persenter;

import java.io.IOException;
import java.security.PublicKey;

import demo.luojun.com.demo.context.AppServerAPI;
import demo.luojun.com.demo.utils.RSAEncryptionUtil;
import demo.luojun.com.demo.view.OkhttpView;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by luo.j on 2018/6/11.
 */
public class BaseOkHttpPersenter extends BasePresenter<OkhttpView> {
    public void okhttpGet() {
 /*       OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        OkHttpClient okHttpClients = builder.build();*/


        ygChannel();

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

    /*
          gou I/info: httpHeaders[ Udid=dc:09:4c:d6:f9:63]
06-13 17:56:26.412 5066-5066/com.yougou I/info: httpHeaders[ Ver=1.0]
06-13 17:56:26.412 5066-5066/com.yougou I/info: httpHeaders[ Os=android]
06-13 17:56:26.412 5066-5066/com.yougou I/info: httpHeaders[ screenlevel=1]
06-13 17:56:26.412 5066-5066/com.yougou I/info: httpHeaders[ Sourceid=YgYougouwebA59]
06-13 17:56:26.412 5066-5066/com.yougou I/info: httpHeaders[ nodeCode=]
06-13 17:56:26.412 5066-5066/com.yougou I/info: httpHeaders[ modelType=]
06-13 17:56:26.412 5066-5066/com.yougou I/info: httpHeaders[ screenh=1184]
06-13 17:56:26.412 5066-5066/com.yougou I/info: httpHeaders[ Osversion=5.1]
06-13 17:56:26.412 5066-5066/com.yougou I/info: httpHeaders[ mac=dc:09:4c:d6:f9:63]
06-13 17:56:26.412 5066-5066/com.yougou I/info: httpHeaders[ token=MKep0qbU0qGEau2PpEh+KtE3mjWmN5iT2OE/h7A2EwiVOAT9GcM2ueMUtOeQcSiEDztfk9JERWUiZNsQSuHvzNNO+BYzemCPtJuYVfvRgBDN1Gb9qWCBN046bDEIECTSGFlfZgt2WHz5HgUgD/7CK+3dYblFgjsBGNYBboqnQzs=]
06-13 17:56:26.412 5066-5066/com.yougou I/info: httpHeaders[ viewPath=]
06-13 17:56:26.413 5066-5066/com.yougou I/info: httpHeaders[ productLine=vm8]
06-13 17:56:26.413 5066-5066/com.yougou I/info: httpHeaders[ random=1528883786397]
06-13 17:56:26.413 5066-5066/com.yougou I/info: httpHeaders[ topicId=]
06-13 17:56:26.413 5066-5066/com.yougou I/info: httpHeaders[ Appkey=9980998]
06-13 17:56:26.413 5066-5066/com.yougou I/info: httpHeaders[ Appversion=4.2.4]
06-13 17:56:26.413 5066-5066/com.yougou I/info: httpHeaders[ commodityCode=]
06-13 17:56:26.413 5066-5066/com.yougou I/info: httpHeaders[ screenw=720]
06-13 17:56:26.413 5066-5066/com.yougou I/info: httpHeaders[ imei=860962035785914]
06-13 17:56:26.413 5066-5066/com.yougou I/info: httpHeaders[ topicName=]
06-13 17:56:26.413 5066-5066/com.yougou I/info: httpHeaders[ Unique=ed00c4efa746e354f67f7eaa1ef0f20b]*/
    public void ygChannel(){
        String 	tokenStr="860962035785914_dc:09:4c:d6:f9:63";
        String ran=System.currentTimeMillis()+"";
        String token=RSAEncryptionUtil.sign(tokenStr+"_"+ran);
        print(tokenStr+" "+ran+"  "+token);
        print("test==useragin=="+getUserAgent());
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(AppServerAPI.YG_VERSION)
               .header("User-Agent", getUserAgent())
                .addHeader("Udid","dc:09:4c:d6:f9:63")//Udid=dc:09:4c:d6:f9:63
                .addHeader("Ver","1.0")//Ver=1.0
                .addHeader("Os","android")//Os=android
                .addHeader("screenlevel","1")//screenlevel=1
                .addHeader("Sourceid","YgYougouwebA59")//Sourceid
                .addHeader("nodeCode","")//Udid=dc:09:4c:d6:f9:63
                .addHeader("modelType","")//Ver=1.0
                .addHeader("screenh","1184")//Udid=dc:09:4c:d6:f9:63
                .addHeader("Osversion","5.1")//Udid=dc:09:4c:d6:f9:63
                .addHeader("mac","dc:09:4c:d6:f9:63")//Ver=1.0
                .addHeader("token",token)//Udid=dc:09:4c:d6:f9:63
                .addHeader("viewPath","")//Ver=1.0
                .addHeader("productLine","vm8")//Udid=dc:09:4c:d6:f9:63
                .addHeader("random",ran)//Ver=1.0
                .addHeader("topicId","")//Udid=dc:09:4c:d6:f9:63
                .addHeader("Appkey","9980998")//Ver=1.0
                .addHeader("Appversion","4.2.4")//Udid=dc:09:4c:d6:f9:63
                .addHeader("commodityCode","")//Ver=1.0
                .addHeader("screenw","720")//Udid=dc:09:4c:d6:f9:63
                .addHeader("imei","860962035785914")//Ver=1.0
                .addHeader("topicName","")//Udid=dc:09:4c:d6:f9:63
                .addHeader("Unique","ed00c4efa746e354f67f7eaa1ef0f20b")//Ver=1.0
                .build();
        final Call call = okHttpClient.newCall(request);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = call.execute();
                    print(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public static String sign(String content) {
        byte[] temp= content.getBytes();
        PublicKey publicKey= RSAEncryptionUtil.loadPublicKeyWithPEM(RSAEncryptionUtil.RSA_PUBLIC);
        return RSAEncryptionUtil.encrypt2Base64String(temp, publicKey);

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
