package demo.luojun.com.demo.persenter;

import demo.luojun.com.demo.context.AppServerAPI;
import demo.luojun.com.demo.view.OkhttpView;

/**
 * Created by luo.j on 2018/6/14.
 */
public class OkhttpPersenter extends BaseOkHttpPersenter<OkhttpView> {
    public void okhttpGet() {
        requestGetSync(AppServerAPI.YG_VERSION,1);
    }

    @Override
    protected void reponse(String result, int requestCode) {
        super.reponse(result, requestCode);
       // toast(result);
        print(requestCode+"=="+result);
    }
}

