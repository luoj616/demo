package demo.luojun.com.demo.network.model;

import android.content.Context;

import com.network.ReturnHttpResponse;
import com.network.rxretrofit.SubscriberOnNextListener;
import com.network.rxretrofit.loaderfactory.YGSimpleFactory;

/**
 * Created by luo.j on 2019/7/31.
 */

public class MvpModel {
    public void getDetail(Context context, String id,ReturnHttpResponse returnHttpResponse) {
        YGSimpleFactory.getYgServiceLoader().getDetailS(id, returnHttpResponse, context);
    }



    public void getLogin(String verifycode,String username,SubscriberOnNextListener subscriberOnNextListener,
    Context context) {
        YGSimpleFactory.getYgServiceLoader().getLogin(verifycode,username,subscriberOnNextListener,context );
    }
    public void getLogin(String verifycode,String username, ReturnHttpResponse returnHttpResponse,
                         Context context) {
        YGSimpleFactory.getYgServiceLoader().getLogin(verifycode,username,returnHttpResponse,context );
    }


    public void sendMsg(String username, ReturnHttpResponse returnHttpResponse,
                         Context context) {
        YGSimpleFactory.getYgServiceLoader().sendMsgStr(username,returnHttpResponse,context );
    }
}
