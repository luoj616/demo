package demo.luojun.com.demo.activity.java.rxactivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.luojun.com.demo.R;
import demo.luojun.com.demo.bean.Student;
import demo.luojun.com.demo.context.BaseActivity;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

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
    //观察者observer
    @OnClick(R.id.observer_bt)
    public void observerOnClick(){
        observerAndObservable();
    }

    //观察者 subscriber
    @OnClick(R.id.subscriber_bt)
    public void subscriberOnClick(){
       subscriberAndObervale();
    }


    //observable .just
    @OnClick(R.id.just_observable_bt)
    public void justObservableOnClick(){
        justObservable();
    }

    //observable.from
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

    //Action1
    @OnClick(R.id.action1_bt)
    public void onNext0nErrorOnClick(){
        actrion1();
    }



    /**
     * 观察者oberver
     */
    public void observerAndObservable(){
        observable.subscribe(observer);
    }

    /**
     * 观察者  subscriber
     */
    public void subscriberAndObervale(){
        observable.subscribe(subscriber);

        Observable.create(new Observable.OnSubscribe<String>(){
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("oohhgl");
                subscriber.onNext("ooooo");
                subscriber.onError( new Throwable());

            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                err("执行完成------");

            }

            @Override
            public void onError(Throwable e) {
                err("onError------"+e.toString());
            }

            @Override
            public void onNext(String s) {

            }
        });
    }

    /**
     * obervable just
     */
    public void justObservable(){
        Observable observable= Observable.just("just","ahh","werwr");
        // 将会依次调用：
        observable.subscribe(observer);


    }

    /**
     * obervable from
     */
    public void formObservable(){
        String[] strings={"from","s","luojun","huangxia"};
        Observable observable = Observable.from(strings);

        observable.subscribe(observer);
    }

    /**
     * action1
     */
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
        // 自动创建 Subscriber ，并使用 onNextAction、 onErrorAction 和 onCompletedAction
        // 来定义 onNext()、 onError() 和 onCompleted()
        observable.subscribe(onNextAction,onErrorAction,onCompletedAction);
    }


    /**
     * Acton1
     */
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
        // 自动创建 Subscriber ，并使用 onNextAction 来定义 onNext()
        observable.subscribe(action1);
        // 自动创建 Subscriber ，并使用 onNextAction 和 onErrorAction 来定义 onNext() 和 onError()
        observable.subscribe(action1,throwableAction1);
    }

    /**
     * 县城调度
     */
    @OnClick(R.id.scheduler_bt)
    public void schecdulerOnClick(){
        final int imageRes;
        final ImageView imageView = null;
        Observable.create(new Observable.OnSubscribe<Drawable>(){
            @Override
            public void call(Subscriber<? super Drawable> subscriber) {
                Drawable drawable =null;
                subscriber.onNext(drawable);
                subscriber.onCompleted();

            }
        }).filter(new Func1<Drawable, Boolean>() {
            @Override
            public Boolean call(Drawable drawable) {
                return drawable!=null&&imageView!=null;
            }
        })
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Drawable>() {
                    @Override
                    public void onCompleted() {
                        err("线程调度完成---");
                    }

                    @Override
                    public void onError(Throwable e) {
                        err("线程调度完成---error");
                    }

                    @Override
                    public void onNext(Drawable drawable) {
                        err("线程调度 next--");
                        imageView.setImageDrawable(drawable);
                    }
                });
    }

    /**
     * 转换map
     */

    @OnClick(R.id.map_bt)
    public void mapOnClick(){
        List<Student> list= new ArrayList<Student>();
      list.add(new Student(true));
        list.add(new Student(true,"luo"));
        list.add(new Student(true,"jun"));
        Observable.from(list)
                .map(new Func1<Student, List<Student.Course>>() {

                    @Override
                    public List<Student.Course> call(Student student) {
                        err(student.toString());
                        return student.getList();
                    }
                })
                .subscribe(new Action1<List<Student.Course>>() {
            @Override
            public void call(List<Student.Course> courses) {
                  err("-------------cour size---_"+courses.size());
                Observable.from(courses).subscribe(new Action1<Student.Course>() {
                    @Override
                    public void call(Student.Course course) {
                        err("cour name---_"+course.getCourseName());
                    }
                });
            }
        });
    }

    /**
     * 转换 flatMap
     */
    @OnClick(R.id.flatmap_bt)
    public void flatMapFuncOnClick(){
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


}
