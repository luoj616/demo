package demo.luojun.com.demo.activity.networkactivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.luojun.com.demo.R;
import demo.luojun.com.demo.activity.model.service.net.WifiReceiver;
import demo.luojun.com.demo.activity.model.socket.ScanDeviceTool;
import demo.luojun.com.demo.activity.model.socket.wifi.WifiAdmin;
import demo.luojun.com.demo.activity.model.socket.wifi.WifiUtils;
import demo.luojun.com.demo.context.BaseActivity;
import socketblockdemo.Const;
import socketblockdemo.SocketThreadManager;

public class SocketActivity extends BaseActivity {
    public static Context s_context;
    @BindView( R.id.res_txt)
    TextView textView;
    @BindView(R.id.socket_txt)
    EditText editText;

    Handler handler = null;
    private BroadcastReceiver bcReceiver;
    String ssid="Honor8";
    String password="12345678";


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket);
        ButterKnife.bind(this);
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
        });
        //

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

    @OnClick(R.id.scan_btn)
    public void scanBtOnClick(){
        err("--------------烧苗的结果--------------------");
        //  err(getServerIP()+"------------------------");
        WifiAdmin wa = new WifiAdmin(this);
        wa.openWifi();
        wa.addNetwork(wa.CreateWifiInfo(ssid, password, 3));
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @OnClick(R.id.scan_btn2)
    public void scanBtOnClick2(){
        err("--------------烧苗的结果-------2-------------");
        //  err(getServerIP()+"------------------------");
        WifiUtils wifiUtils = new WifiUtils(this);
        wifiUtils.enableWifi(this,new WifiReceiver(this));
    }

    @OnClick(R.id.send_btn)
    public void send(){
        String str = editText.getText().toString();
        if(!TextUtils.isEmpty(str)){
            SocketThreadManager.sharedInstance().sendMsg(str.getBytes(), handler);
        }
    }


    List<String> pList;
    InetAddress HOST;
    Socket socket;
    int PORT=8888;
    boolean isConnected;
    BufferedReader in;
    PrintWriter out;
    /**
     * 扫描局域网IP并连接
     * @return 返回值表示是否连接成功
     */
    private boolean getServerIP() {
        new Thread(){
            @Override
            public void run() {
                ScanDeviceTool scanDeviceTool = new ScanDeviceTool();
                pList = scanDeviceTool.scan();//调用工具类方法开始扫描
                err("plist-----"+pList);
                if(pList != null && pList.size() >0) {
                    for (final String ip : pList) {
                        new Thread() {
                            @Override
                            public void run() {
                                try {
                                    HOST = InetAddress.getByName(ip);
                                    err("-------->" + ip);
                                } catch (UnknownHostException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    socket = new Socket(HOST, PORT);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                if (socket != null) {//如果无法建立连接，socket将为空
                                    try {
                                        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    try {
                                        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    if(socket.isConnected()){
                                        err("-------->conncted: "+ip);
                                        isConnected = true;
                                    }
                                }
                            }
                        }.start();
                    }
                }
            }
        }.start();
        return isConnected;
    }

    @Override
    protected void onDestroy(){
        if(bcReceiver != null){
            unregisterReceiver(bcReceiver);
        }
        super.onDestroy();
    }

    public void regBroadcast() {
        bcReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
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

}