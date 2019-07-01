package demo.luojun.com.demo.network.persenter;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import demo.luojun.com.demo.context.AppServerAPI;
import demo.luojun.com.demo.network.view.OkhttpView;

/**
 * Created by luo.j on 2018/6/14.
 */
public class OkhttpPersenter extends BaseOkHttpPersenter<OkhttpView> {
    public void okhttpGet() {
        requestGetInitialSync(AppServerAPI.OKHTTP_GET,1);
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Runnable() {
            @Override
            public void run() {

            }
        });
    }

    public void okhttpYG() {
        requestGetSync(AppServerAPI.YG_CHANELS,2);
    }


    public void okhttpYGVersion(){
        requestGetSync(AppServerAPI.YG_VERSION,3);
    }

    public void okhttpYGCate(){
        requestGetSync(AppServerAPI.YG_categories4all,4);
    }

    public void okhttpYGDetail(){
        boolean ispost=true;
        if(!ispost){//
            String url =AppServerAPI.YG_commodityDetail+"?productid=99890025";
            requestGetSync(url,5);
        }else {
            Map<String,String> map =  new HashMap<String,String>();
            map.put("productid","99890025");
            requestPostSyncForm(AppServerAPI.YG_commodityDetail,map,5);
        }

    }

    @Override
    protected void reponse(String result, int requestCode) {
        super.reponse(result, requestCode);
       // toast(result);
        print(requestCode+"=="+result);
    }
}

