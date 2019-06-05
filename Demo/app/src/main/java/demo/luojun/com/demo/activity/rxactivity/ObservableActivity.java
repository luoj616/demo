package demo.luojun.com.demo.activity.rxactivity;

import android.graphics.Bitmap;
import android.os.Bundle;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.luojun.com.demo.R;
import demo.luojun.com.demo.bean.Student;
import demo.luojun.com.demo.context.BaseActivity;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * rxjava
 */
public class ObservableActivity extends BaseActivity{
    private    Observer<String> observer;//观察者
    private  Subscriber<String> subscriber;//观察者
   private  Observable<String> observable;//被观察者

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

    /**
     * action0 相当于Observer的onCompleted,不能单独使用,必须先使用
     */
    @OnClick(R.id.action0_bt)
    public void onCompletedOnClick(){
       actiono();
    }
    @OnClick(R.id.action1_bt)
    public void onNext0nErrorOnClick(){
        actrion1();
    }

    @OnClick(R.id.flatmap_bt)
    public void flatMapOnClick(){
        flatMapFunc();
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
        Action1<String> onNextAction = new Action1<String>() {
            // onNext()
            @Override
            public void call(String s) {
                err("action1===next=="+s);
            }
        };
        Action1<Throwable> onErrorAction = new Action1<Throwable>() {
            // onError()
            @Override
            public void call(Throwable throwable) {
                // Error handling
            }
        };
        Action0 onCompletedAction = new Action0() {
            // onCompleted()
            @Override
            public void call() {
                err("completed");
            }
        };
        observable.subscribe(onNextAction,onErrorAction,onCompletedAction);
    }
    public void actrion1(){
        Action1<String> action1 = new Action1<String>() {
            @Override
            public void call(String s) {
                err("action1=="+s);
            }
        };
        Action1<Throwable> throwableAction1=new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {

            }
        };
        observable.subscribe(action1);
        observable.subscribe(action1,throwableAction1);
    }

    public void mapFuck(){
       Observable.just("test")
               .map(new Func1<String, Bitmap>() {
                   @Override
                   public Bitmap call(String s) {
                       return null;
                   }
               }).subscribe(new Action1<Bitmap>() {
           @Override
           public void call(Bitmap bitmap) {

           }
       });
    }

    public void flatMapFunc(){
        Student student =new Student(true);
        Subscriber<Student.Course> subscriber = new Subscriber<Student.Course>() {
            @Override
            public void onCompleted() {
              err("completed----");
            }


            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Student.Course course) {
             err("course---"+course.getCourseName());
            }
        };
        Observable.just(student)
                .flatMap(new Func1<Student, Observable<Student.Course>>() {
                    @Override
                    public Observable<Student.Course> call(Student student) {
                        return Observable.from(student.getList());
                    }
                }).subscribe(subscriber);


    }

  public  class s implements InvocationHandler{

      @Override
      public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
          return null;
      }
  }
}
