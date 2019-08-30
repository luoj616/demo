package demo.luojun.com.demo.network.persenter;

import android.util.Log;

import com.network.ReturnHttpResponse;

import demo.luojun.com.demo.network.model.MvpModel;
import demo.luojun.com.demo.network.view.MvpView;

/**
 * Created by luo.j on 2019/7/31.
 */

public class MvpPersenter extends BaseNewPresenter<MvpView> {
    private MvpModel mvpModel;
    public MvpPersenter(){
        mvpModel = new MvpModel();

    }

    public void sendMsg(String username){
        mvpModel.sendMsg(username, new ReturnHttpResponse<String>() {
            @Override
            public void success(String o) {
                printLog("=-==success________________"+o.toString());
            }

            @Override
            public void getFail(String mes, int Code) {
        printLog("=-==err0r_________________");
            }
        },getThisContext());
    }
    int a =4000;
    boolean is=true;
    public void login(){
  /*      mvpModel.getLogin("1", "13925279482", new SubscriberOnNextListener<BaseBean<String>>() {
            @Override
            public void onNext(BaseBean<String> o) {
              print("00-----------"+o.toString());
            }
        },getThisContext());*/

        if(a<4200&&is) {
            String str = a + "";
            if (str.length() != 4) {
                if (str.length() == 1) {
                    str = "000" + str;
                } else if (str.length() == 2) {
                    str = "00" + str;
                } else if (str.length() == 3) {
                    str = "0" + str;
                }
            }
             printLog(str+"----------------------");
            mvpModel.getLogin(str, "13925279482", new ReturnHttpResponse<String>() {
                @Override
                public void success(String o) {
                    printLog("00-----------" + o.toString());
                    toast("----------success----------");
                    is=false;
                }

                @Override
                public void getFail(String mes, int code) {

                    a = a +1;
                  login();
              //      login();
//                    login();
//                    login();
//                    login();
//                    login();
                    printLog( "---------***-----------" + a);
                }
            }, getThisContext());
        }
    }
    public void detail(String id){
        mvpModel.getDetail(getThisContext(),id,new ReturnHttpResponse<String>() {
            @Override
            public void success(String o) {
                Log.e("info","&&&&+++"+getView().toString());

            }

            @Override
            public void getFail(final String mes, final int Code) {
                Log.e("info",getView().toString());
               getView().test();
                toast("-----------888------------");
              /*  RxRetrofitActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        detailBt.setText("MES" + mes + "--" + Code);
                    }
                });*/

            }
        });

    }
}
