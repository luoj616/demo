package demo.luojun.com.demo.activity.model.service.net;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.util.List;
import java.util.Objects;

import demo.luojun.com.demo.activity.model.socket.wifi.WifiUtils;

import static demo.luojun.com.demo.activity.model.socket.wifi.WifiUtils.ssid;

/**
 * Created by luo.j on 2019/11/25.
 */

public class WifiReceiver  extends BroadcastReceiver {
    private Context context;
    public WifiReceiver(Context context){
      this.context = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (Objects.requireNonNull(action).equals(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)) {
            Log.e("info", "enableWifi:______0d0f-- ");
            //扫描结果广播，查找扫描列表是否存在指定SSID的WiFi，如果存在则进行连接
            WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            List<ScanResult> scanResultList = Objects.requireNonNull(wifiManager).getScanResults();
            Log.e("info", scanResultList.toString());
            for (ScanResult scanResult : scanResultList) {
                Log.e("info", scanResult.SSID + "---");
                Log.e("info", scanResult.SSID.equals(WifiUtils.ssid) + "----------------------------");
                if (scanResult.SSID.equals(WifiUtils.ssid)) {
                    Log.e("info", ssid + "=___________jinlail _________________" + WifiUtils.password);
                    connectWifi(context,ssid, WifiUtils.password, 2, this);
                }
            }
        } else if (action.equals(WifiManager.WIFI_STATE_CHANGED_ACTION)) {
            //WiFi状态变化广播：如果WiFi已经完成开启，即可进行WiFi扫描
            Log.e("info", "enableWifi:_________________ ");
            int wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, 0);
            switch (wifiState) {
                case WifiManager.WIFI_STATE_ENABLED:
                    WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                    Objects.requireNonNull(wifiManager).startScan();
                    break;
                case WifiManager.WIFI_STATE_DISABLED:
                    break;
                default:
                    break;
            }
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public  boolean connectWifi(Context context, String ssid, String password, int i, BroadcastReceiver wifiReceiver) {
        Log.e("Info","-------------------*****************-----------------------------");

        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        List<WifiConfiguration> wifiConfigurations = Objects.requireNonNull(wifiManager).getConfiguredNetworks();
        for(WifiConfiguration wifiConfiguration : wifiConfigurations) {
            if(wifiConfiguration.SSID.equals("\"" + ssid + "\"")) {
                boolean isWifiEnable = wifiManager.enableNetwork(wifiConfiguration.networkId, true);
                Log.e("Info",isWifiEnable+"-----------22---------------"+wifiConfiguration.toString());
                if(isWifiEnable) {
                    context.unregisterReceiver(wifiReceiver);
                    return true;
                } else {
                    context.unregisterReceiver(wifiReceiver);
                    return false;
                }
            }
        }
        int netId = wifiManager.addNetwork(createWifiConfiguration(ssid, password, i));
        boolean isWifiEnable = wifiManager.enableNetwork(netId, true);
        if(isWifiEnable) {
            context.unregisterReceiver(wifiReceiver);
            return true;
        }
        context.unregisterReceiver(wifiReceiver);
        return false;
    }
    private  WifiConfiguration createWifiConfiguration(String ssid, String password, int i) {
        WifiConfiguration wifiConfiguration = new WifiConfiguration();
        wifiConfiguration.allowedAuthAlgorithms.clear();
        wifiConfiguration.allowedGroupCiphers.clear();
        wifiConfiguration.allowedKeyManagement.clear();
        wifiConfiguration.allowedPairwiseCiphers.clear();
        wifiConfiguration.allowedProtocols.clear();
        wifiConfiguration.SSID = "\"" + ssid + "\"";
        wifiConfiguration.status = WifiConfiguration.Status.ENABLED;
        if(i== 0) {
            wifiConfiguration.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
            wifiConfiguration.wepTxKeyIndex = 0;
        } else if(i== 1 ) {
            wifiConfiguration.hiddenSSID = true;
            wifiConfiguration.wepKeys[0]= "\"" + password + "\"";
            wifiConfiguration.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.SHARED);
            wifiConfiguration.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
            wifiConfiguration.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
            wifiConfiguration.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
            wifiConfiguration.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP104);
            wifiConfiguration.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
            wifiConfiguration.wepTxKeyIndex = 0;
        } else if (i== 2) {
            wifiConfiguration.preSharedKey = "\"" + password + "\"";
            wifiConfiguration.hiddenSSID = false;
            wifiConfiguration.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
            wifiConfiguration.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
            wifiConfiguration.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
            wifiConfiguration.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
            wifiConfiguration.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
            wifiConfiguration.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
            wifiConfiguration.status = WifiConfiguration.Status.ENABLED;
        }
        return wifiConfiguration;
    }


}

