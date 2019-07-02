package demo.luojun.com.demo.network.persenter;

import com.network.OkHttpResponse;
import com.network.okhttp.OkHttpRequestUtils;

import java.util.Map;

import demo.luojun.com.demo.context.AppServerAPI;


/**
 * Created by luo.j on 2018/6/11.
 */
public class BaseOkHttpPersenter<V> extends BasePresenter {


    public  void requestPostSyncForm(String url,Map<String,String> parms,final int requestCode){
        OkHttpRequestUtils okHttpRequestUtils = new OkHttpRequestUtils();
        okHttpRequestUtils.postFromSync(AppServerAPI.YG_IP + url, parms, new OkHttpResponse() {
            @Override
            public void getSuccess(final String result) {
                getThisActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        reponse(result, requestCode);
                    }
                });
            }

            @Override
            public void getFail(String mes) {

            }
        });
    }










    protected void requestGetInitialSync(String url, final int requestCode){

        OkHttpRequestUtils okHttpRequestUtils = new OkHttpRequestUtils();
        okHttpRequestUtils.getSync(url, new OkHttpResponse() {
            @Override
            public void getSuccess(final String result) {
                getThisActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        reponse(result, requestCode);
                    }
                });
            }

            @Override
            public void getFail(String mes) {

            }
        });

    }
    protected void requestGetSync(String url, final int requestCode){
        OkHttpRequestUtils okHttpRequestUtils = new OkHttpRequestUtils();
        okHttpRequestUtils.getSync(AppServerAPI.YG_IP+url, new OkHttpResponse() {
            @Override
            public void getSuccess(final String result) {
                getThisActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        reponse(result, requestCode);
                    }
                });
            }

            @Override
            public void getFail(String mes) {

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


    @Override
    protected void sendRequest(String url, Map map, int sendCode) {
      //
        // ==+++++++++++++++++++++++++++++++++++++++++++++++++