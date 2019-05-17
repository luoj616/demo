package demo.luojun.com.demo.activity.testactivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.luojun.com.demo.R;
import demo.luojun.com.demo.activity.networkactivity.OkhttpActivity;
import demo.luojun.com.demo.context.BaseActivity;

public class TestActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);

        handlerDemoByTwoWorkThread();
    }
    @OnClick(R.id.build_bt)
    public void buildBt(){
        basePresenter.jumpActivity(BuildActivity.class);
    }



    private Handler handler;
    private void handlerDemoByTwoWorkThread() {
        Thread hanMeiMeiThread = new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                handler = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        print( "hanMeiMei receiver message: " + ((String) msg.obj));
                        Toast.makeText(TestActivity.this, ((String) msg.obj), Toast.LENGTH_SHORT).show();
                    }
                };
                Looper.loop();
            }
        };
        Thread liLeiThread = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                print("-------------------sdfsafd-----------------------");
                Message message = handler.obtainMessage();
                message.obj = "Hi MeiMei";
                handler.sendMessage(message);
            }
        };
        hanMeiMeiThread.setName("韩梅梅 Thread");
        hanMeiMeiThread.start();
        liLeiThread.setName("李雷 Thread");
        liLeiThread.start();

        /*
        * 搞定，我们创建了两个Thread，liLeiThread和hanMeiMeiThread两个线程，很熟悉的名字啊！
        * 跟之前的代码没太大区别hanMeiMeiThread创建了Handler，liLeiThread通过Handler发送了消息。
        * 只不过此处我们只发送一个消息，所以没有使用what来进行标记
        * 运行看看，我们的李雷能拨通梅梅吗？
        * 啊哦，出错了
        * 05-13 17:08:17.709 20673-20739/? E/AndroidRuntime: FATAL EXCEPTION: 韩梅梅 Thread
                                                   Process: design.wang.com.designpatterns, PID: 20673
                                                   java.lang.RuntimeException: Can't create handler inside thread that has not called Looper.prepare()
                                                       at android.os.Handler.<init>(Handler.java:200)
                                                       at android.os.Handler.<init>(Handler.java:114)
        *Can't create handler inside thread that has not called Looper.prepare()
        * -----------》它说我们创建的handler没有调用Looper.prepare();
        * 好的，我们在实例化Handler之前调用下该方法，看一下。加上是不是没有报错了呢。
        * 等等，虽然没有报错，但是hanMeiMeiThread也没有接到消息啊，消息呢？别急。
        * 我们在Handler实例化之后加上Looper.loop();看一看，运行一下，是不是收到消息了呢。
        * 这是为什么呢？
        * 接下来我们就去看看Handler是怎么实现的发消息呢，弄清楚了原理，这里的原因也就明白了。
        */

    }





}
