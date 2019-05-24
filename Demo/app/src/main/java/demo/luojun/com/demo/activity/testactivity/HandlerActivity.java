package demo.luojun.com.demo.activity.testactivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Button;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.luojun.com.demo.R;
import demo.luojun.com.demo.context.BaseActivity;

public class HandlerActivity extends BaseActivity {
    @BindView(R.id.timerBt)
    Button timerBt;

    private Handler handlerMain=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }

        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        ButterKnife.bind(this);

        handlerDemoByTwoWorkThread();
        new Thread(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
        });

      //  ExecutorService service =new ThreadPoolExecutor(3,10,60, TimeUnit.MINUTES,null);
    }

    Timer timer;
    /**
     * Timer 定时器
     */
    @OnClick(R.id.timerBt)
    public void timerOnClick(){
      timer =new Timer();
        timer.schedule(new MyTimerTask(),0,1000);
        CountDownTimer countDownTimer = new CountDownTimer(90000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {

            }
        };

    }
    private int index;
    public class MyTimerTask extends TimerTask{

        @Override
        public void run() {
          HandlerActivity.this. runOnUiThread(new Runnable() {
               @Override
               public void run() {

                   timerBt.setText(index+"");
               }
           });

            index++;
            err(index+"-----------inex------------");
            if(index>=60&&timer!=null){
               err("____________jinlail ____________________");
                timer.cancel();
                timer=null;
            }
        }
    }

    /**
     * asyncTask
     */
    @OnClick(R.id.asyncTaskBt)
    public void asyncTaskOnClick(){
        new MyAsyncTask().execute("http://mobile.yougou.com/v_1.8/version");
    }
    /**
     * 线程池测试
     */
    @OnClick(R.id.threadpoolExecuteBt)
    public void threadPoolExecuteOnClick(){
        ExecutorService service = Executors.newFixedThreadPool(5);//五个线程
        ExecutorService executorService =new ThreadPoolExecutor(3,5,1, TimeUnit.SECONDS,new LinkedBlockingDeque<Runnable>(25));
        for( int i=0;i<30;i++){
            final int index=i;
            Runnable runnable=new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                        print("Thread==un: "+index);
                        print("当前线程：=="+Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };

            executorService.execute(runnable);
        }


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
                        Toast.makeText(HandlerActivity.this, ((String) msg.obj), Toast.LENGTH_SHORT).show();
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
    public class MyAsyncTask extends AsyncTask<String,Integer,byte[]>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            err("-----开始--------");
        }

        @Override
        protected byte[] doInBackground(String... params) {
            InputStream inputStream=null;
            HttpURLConnection httpURLConnection =null;
            try {
                URL url =new URL(params[0]);
                if(url!=null){
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setConnectTimeout(10000);
                    httpURLConnection.setReadTimeout(5000);
                    if(httpURLConnection.getResponseCode()==200){
                        InputStream is =httpURLConnection.getInputStream();
                        long total =httpURLConnection.getContentLength();
                        int count=0;
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        byte[] buffer = new byte[1024];
                        int len =0;
                        while ((len =is.read(buffer))!=-1){
                            baos.write(buffer,0,len);
                            err(count+"--@@@@--"+len+"-----------------------"+(int)((count/(float)total)*100));
                            count= count+len;


                            publishProgress((int)((count/(float)total)*100));
                            //为了演示进度,休眠500毫秒
                            Thread.sleep(500);
                        }
                        is.close();
                        err("return=="+baos.toString()+"------------------");
                        byte[] datas = baos.toByteArray();
                        err("string=="+new String(datas)+"------------------");
                        baos.close();

                        return datas;

                    }
                }

            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {

            err(values.toString()+"--------------pro--------"+values[0]);
        }

        @Override
        protected void onPostExecute(byte[] bytes) {
     err("======"+bytes.toString());
        }
    }

}
