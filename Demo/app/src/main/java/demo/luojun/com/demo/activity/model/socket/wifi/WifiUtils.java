package demo.luojun.com.demo.activity.model.socket.wifi;

import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.util.Objects;

import demo.luojun.com.demo.activity.model.service.net.WifiReceiver;

/**
 * Created by luo.j on 2019/11/25.
 */

public class WifiUtils {
    private Context context;
    public  WifiUtils(Context context){
        context = context;
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void enableWifi(Context context, WifiReceiver wifiReceiver) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        if (!Objects.requireNonNull(wifiManager).isWifiEnabled()) {
            //WiFi未打开，启用wifi
            Log.e("info", "enableWifi: ------*********************-----------");
            wifiManager.setWifiEnabled(true);
        } else {
            Log.e("info", "enableWifi: 已打开WiFi");
            //WiFi已打开，检查是否已连接了，如果已连接，先将连接断开。由于WiFi已经打开，可以直接进行WiFi扫描
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            if (wifiInfo != null) {
                wifiManager.disableNetwork(wifiInfo.getNetworkId());
                wifiManager.disconnect();
            }
            wifiManager.startScan();
        }
        //注册WiFi扫描结果、WiFi状态变化的广播接收
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        intentFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        context.registerReceiver(wifiReceiver, intentFilter);
    }

  public static String ssid="Honor8";
   public static String password="12345678";


}
