package demo.luojun.com.demo.persenter;

import demo.luojun.com.demo.context.AppServerAPI;
import demo.luojun.com.demo.view.OkhttpView;

/**
 * Created by luo.j on 2018/6/14.
 */
public class OkhttpPersenter extends BaseOkHttpPersenter<OkhttpView> {
    public void okhttpGet() {
        requestGetSync(AppServerAPI.OKHTTP_GET,1);
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
        requestGetSync(AppServerAPI.YG_commodityDetail,5);
    }

    @Override
    protected void reponse(String result, int requestCode) {
        super.reponse(result, requestCode);
       // toast(result);
        print(requestCode+"=="+result);
    }
}

