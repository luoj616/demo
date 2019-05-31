package demo.luojun.com.demo.activity.networkactivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import demo.luojun.com.demo.R;
import demo.luojun.com.demo.context.BaseActivity;
import socketblockdemo.Const;
import socketblockdemo.SocketThreadManager;

public class SocketActivity extends BaseActivity implements View.OnClickListener{
    private BroadcastReceiver bcReceiver;

    public static Context s_context;

    TextView textView;

    EditText editText;

    Button btn;

    Handler handler = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    err("-2323@#-@@--");
                    Socket socket = new Socket();

                    SocketAddress socketAddr = new InetSocketAddress("mobile.yougou.com", 80);
                    socket.connect(socketAddr);
//                  InetSocketAddress address =new InetSocketAddress("D");
//                    Socket socket =new Socket(AppServerAPI.YG_VERSION,0);
                    err("-2323@#-)*******************************************((--");
                    InputStream inputStream =socket.getInputStream();
                    byte[] buffer = new byte[1024];
                    int len = 0;
                    while((len = inputStream.read(buffer))!=-1){
                        String text = new String(buffer, 0, len);
                  err(text+"----------------------------fsdfsdf");
                    }


                    int count = socket.getInputStream().read(buffer);
                    err(len+"-----2323@#-)((--"+count);
                    String receiveData = new String(buffer);
                    err("-2323@#---"+receiveData);
                } catch (Exception e) {
                    err("-----------err---------------"+e.toString());
                    e.printStackTrace();
                }
            }
        }).start();

//        textView = (TextView)this.findViewById(R.id.res_txt);
//        editText= (EditText)this.findViewById(R.id.socket_txt);
//        btn= (Button)this.findViewById(R.id.send_btn);
//        btn.setOnClickListener(this);
//
//        textView.setText("服务器返回消息显示在此");
//
//        NetManager.instance().init(this);
//
//
//        s_context = this;
//new Thread(new Runnable() {
//    @Override
//    public void run() {
//        SocketThreadManager.sharedInstance();
//    }
//}).start();
//
//
//        regBroadcast();
//
//
//        handler = new Handler( getMainLooper() )
//        {
//            @Override
//            public void handleMessage(Message msg)
//            {
//                switch(msg.what){
//
//                    case 0:
//                        showMsg("发送socket失败");
//                        break;
//
//                    case 1:
//
//                        showMsg("发送socket成功");
//                        break;
//
//                }
//            }
//        };
    }



    @Override

    protected void onDestroy(){

        if(bcReceiver != null){
            unregisterReceiver(bcReceiver);
        }

        super.onDestroy();
    }

    public void regBroadcast()
    {
        bcReceiver = new BroadcastReceiver()
        {
            @Override
            public void onReceive(Context context, Intent intent)
            {
                final String value = intent.getStringExtra("response");

                runOnUiThread(new Runnable()
                {

                    @Override
                    public void run()
                    {
                        textView.setText(value);
                    }
                });

            }
        };

        IntentFilter intentToReceiveFilter = new IntentFilter();
        intentToReceiveFilter.addAction(Const.BC);
        registerReceiver(bcReceiver, intentToReceiveFilter);
    }


    public void showMsg(String str){

        Toast.makeText(SocketActivity.this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v)
    {
        String str = editText.getText().toString();
        if(!TextUtils.isEmpty(str)){

            SocketThreadManager.sharedInstance().sendMsg(str.getBytes(), handler);
        }
    }
}
