package demo.luojun.com.demo.activity.networkactivity;

import android.os.Bundle;
import android.widget.Button;

import com.network.MyClass;
import com.network.rxretrofit.YgServiceLoader;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.luojun.com.demo.R;
import demo.luojun.com.demo.context.BaseActivity;
import rx.functions.Action1;

/*import com.network.AppServerAPI;
import com.network.okhttp.InterceptorLog;
import com.network.retrofit.InterceptorHeader;*/

public class RxRetrofitActivity extends BaseActivity{
    @BindView(R.id.rxretrofit_version_bt)
    Button versionBt;
    @BindView(R.id.rxretrofit_detail_bt)
    Button detailBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_retrofit);
        ButterKnife.bind(this);
    }
    @OnClick(R.id.rxretrofit_detail_bt)
    public void detailOnClick(){

             YgServiceLoader ygServiceLoader = new YgServiceLoader();
                ygServiceLoader.getDetail("99890025")

                        .subscribe(new Action1<MyClass>() {
                            @Override
                            public void call(MyClass s) {
                                err("restul===" + s);
                                 versionBt.setText("sfsfd");
                            }
                        });




    }
    @OnClick(R.id.rxretrofit_version_bt)
    public void versionOnClick(){
     YgServiceLoader ygServiceLoader=new YgServiceLoader();
        ygServiceLoader.getVersion()

                .subscribe(new Action1<MyClass>() {
                    @Override
                    public void call(MyClass s) {
                        err("restul&&==="+s);
                      //  detailBt.setText("--sfsfd");
                    }
                });

    }


}
