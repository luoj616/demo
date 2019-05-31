package demo.luojun.com.demo.activity.rxactivity;

import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.luojun.com.demo.R;
import demo.luojun.com.demo.context.BaseActivity;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;

public class ObservableActivity extends BaseActivity{
    private    Observer<String> observer;
    private  Subscriber<String> subscriber;
   private  Observable<String> observable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observable);
        ButterKnife.bind(this);
     observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                err("observer===over-----");

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String o) {
                err("-----observer=="+o);

            }
        };

        subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                err("_----game over-----");

            }

            @Override
            public void onError(Throwable e) {
                err("_----erer--e oversda-----"+e.toString());
            }

            @Override
            public void onNext(String s) {
                err(s+"=====hha");

            }
        };

observable =Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("SS");
                subscriber.onNext("hahh");
                subscriber.onCompleted();
            }
        });

    }
    @OnClick(R.id.observer_bt)
    public void observerOnClick(){
        observerAndObservable();
    }
    @OnClick(R.id.subscriber_bt)
    public void subscriberOnClick(){
       subscriberAndObervale();
    }
    @OnClick(R.id.just_observable_bt)
    public void justObservableOnClick(){
        justObservable();
    }
    @OnClick(R.id.from_observable_bt)
    public void fromObservableOnClick(){
      formObservable();
    }


    public void observerAndObservable(){
        observable.subscribe(observer);
    }
    public void subscriberAndObervale(){
        observable.subscribe(subscriber);
    }
    public void justObservable(){
        Observable observable= Observable.just("just","ahh","werwr");
        observable.subscribe(observer);
    }
    public void formObservable(){
        String[] strings={"from","s","luojun","huangxia"};
        Observable observable = Observable.from(strings);

        observable.subscribe(observer);
    }

    public void actiono(){
        Action0 action0 = new Action0() {
            @Override
            public void call() {

            }
        };
    }
    public void actrion1(){
        Action1<String> action1 = new Action1<String>() {
            @Override
            public void call(String s) {
                err("action1=="+s);
            }
        }
    }
}
