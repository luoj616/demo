package demo.luojun.com.demo.network.persenter;

import org.json.JSONObject;

import java.io.IOException;
import java.security.PublicKey;
import java.util.Map;

import demo.luojun.com.demo.utils.RSAEncryptionUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * Created by luo.j on 2018/6/11.
 */
public class BaseOkHttpPersenter<V> extends BasePresenter {

    public Request getRequestYG(String url) {
        Request request = new Request.Builder()
                .url(url)
     /*           .addHeader("User-Agent", getUserAgent())
                .addHeader("Udid", "dc:09:4c:d6:f9:63")//Udid=dc:09:4c:d6:f9:63
                .addHeader("Ver", "1.0")//Ver=1.0
                .addHeader("Os", "android")//Os=android
                .addHeader("screenlevel", "1")//screenlevel=1
                .addHeader("Sourceid", "YgYougouwebA59")//Sourceid
                .addHeader("nodeCode", "")//Udid=dc:09:4c:d6:f9:63
                .addHeader("modelType", "")//Ver=1.0
                .addHeader("screenh", "1184")//Udid=dc:09:4c:d6:f9:63
                .addHeader("Osversion", "5.1")//Udid=dc:09:4c:d6:f9:63
                .addHeader("mac", "dc:09:4c:d6:f9:63")//Ver=1.0
                .addHeader("token", token)//Udid=dc:09:4c:d6:f9:63
                .addHeader("viewPath", "")//Ver=1.0
                .addHeader("productLine", "vm8")//Udid=dc:09:4c:d6:f9:63
                .addHeader("random", ran)//Ver=1.0
                .addHeader("topicId", "")//Udid=dc:09:4c:d6:f9:63
                .addHeader("Appkey", "9980998")//Ver=1.0
                .addHeader("Appversion", "4.2.4")//Udid=dc:09:4c:d6:f9:63
                .addHeader("commodityCode", "")//Ver=1.0
                .addHeader("screenw", "720")//Udid=dc:09:4c:d6:f9:63
                .addHeader("imei", "860962035785914")//Ver=1.0
                .addHeader("topicName", "")//Udid=dc:09:4c:d6:f9:63
                .addHeader("Unique", "ed00c4efa746e354f67f7eaa1ef0f20b")//Ver=1.0*/
                .headers(initHead())
                .build();
        return request;

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

    public static String sign(String content) {
        byte[] temp = content.getBytes();
        PublicKey publicKey = RSAEncryptionUtil.loadPublicKeyWithPEM(RSAEncryptionUtil.RSA_PUBLIC);
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




    public OkHttpClient getOkhttpClient() {
        return new OkHttpClient();
    }

/*    public OkHttpClient getOkhttpClientBulider() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(3 * 10000, TimeUnit.MILLISECONDS);//设置超时时间
        return builder.build();
    }*/

    /**
     * formbody数据格式post请求
     * @param url
     * @param parms
     * @return
     */
    public Request getRequestYGPostForm(String url,Map<String,String> parms){
    Request request= new Request.Builder()
            .url(url)
            .method("POST",getFormBody(parms))
          //  .post(getFormBody(parms))
            .headers(initHead())
            .build();

    return request;

}
    /**
     * json数据格式post请求
     * @param url
     * @param parms
     * @return
     */
    public Request getRequestYGPostJson(String url,Map<String,String> parms){
        Request request= new Request.Builder()
                .url(url)
                .post(getRequestBody(parms))
                .headers(initHead())
                .build();

        return request;

    }

    /**
     * formBody数据请求
     * @param parms
     * @return
     */
    public FormBody getFormBody(Map<String,String> parms){
    FormBody.Builder formBody =new FormBody.Builder();
    for (String key : parms.keySet()) {
   print("key= "+ key + " and value= " + parms.get(key));
        formBody.add(key,parms.get(key));
         }
    return formBody.build();
}

    /**
     * json数据请求
     * @param parms
     * @return
     */
    public RequestBody getRequestBody(Map<String,String> parms){
    print("RequestBody===="+new JSONObject(parms).toString());
    MediaType JSON = MediaType.parse("application/json; charset=utf-8");//数据类型为json格式，
    RequestBody requestBody = RequestBody.create(JSON, new JSONObject(parms).toString());
    return requestBody;
}


public  void requestPostSyncJson(String url,Map<String,String> parms,final int requestCode){
    requestPostSync(requestCode,getRequestYGPostJson(url,parms));
}
    public  void requestPostSyncForm(String url,Map<String,String> parms,final int requestCode){
        requestPostSync(requestCode,getRequestYGPostForm(url,parms));
    }
    /**
     * okhttp  同步post请求
     * @param requestCode
     * @param request
     */

 public void    requestPostSync(final int requestCode, final Request request){
          new Thread(new Runnable() {
              @Override
              public void run() {
                  try {
                      Response response =getOkhttpClient().newCall(request).execute();
                      final String result = response.body().string();
                      getThisActivity().runOnUiThread(new Runnable() {
                          @Override
                          public void run() {
                              reponse(result, requestCode);
                          }
                      });
                  } catch (IOException e) {
                      e.printStackTrace();
                  }
              }
          }).start();
 }

 public void requestPosyAysn(final int requestCode, final Request request){
     getOkhttpClient().newCall(request).enqueue(new Callback() {
         @Override
         public void onFailure(Call call, IOException e) {

         }

         @Override
         public void onResponse(Call call, final Response response) throws IOException {
             getThisActivity().runOnUiThread(new Runnable() {
                 @Override
                 public void run() {
                     try {
                         reponse(response.body().string(), requestCode);
                     } catch (IOException e) {
                         e.printStackTrace();
                     }
                 }
             });
         }
     });
 }








    protected void requestGetSync(String url, final int requestCode){
        printLog(url);
        printLog("-@@@@@@--"+getRequestYG(url).url().port()+"----------------"+getOkhttpClient().proxy());
        printLog(getRequestYG(url).url().host()+"----------------"+getRequestYG(url).url().username());
        requestGetSync(requestCode,getRequestYG(url));
    }
    @Override
    protected void sendRequest(String url, Map map, int sendCode) {
        requestGetSync(sendCode,getRequestYG(url));
    }

    /**
     * okhttp 同步get请求
     *
     * @param requestCode

     */
    protected void requestGetSync( final int requestCode,Request request) {
        //test
       final OkHttpClient httpClient = new OkHttpClient();
        final Call call = httpClient.newCall(request);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = call.execute();
                    printLog("----____________________3______________------------");
                    printLog(httpClient.proxy()+"---------1-------");
                    printLog(httpClient.dns().lookup("mobile.yougou.com").get(0).getHostAddress()+"----------2------");
                    printLog(httpClient.dns().lookup("mobile.yougou.com").get(0).getHostName()+"----------2------");
                    printLog(new String(httpClient.dns().lookup("mobile.yougou.com").get(0).getAddress())+"----------2------");
                    printLog(httpClient.dns().lookup("mobile.yougou.com").get(0).toString()+"----------2------");
                    final String result = response.body().string();
                    getThisActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            reponse(result, requestCode);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * okhttp 异步get请求
     *
     * @param requestCode
     */
    protected void requestGetAsyn( final int requestCode, Request request) {
        final Call call = getOkhttpClient().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {}

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                getThisActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            reponse(response.body().string(), requestCode);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    /**
     * 返回数据
     * @param result
     * @param requestCode
     */
    protected void reponse(String result, int requestCode) {
    }

}
