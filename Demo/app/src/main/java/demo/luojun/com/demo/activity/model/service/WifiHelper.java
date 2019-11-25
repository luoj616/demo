package demo.luojun.com.demo.activity.model.service;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WifiHelper {
  public static final String DEFAULT_PROBE_AP_NAME = "AscannerDAP";
  
  public static final String DEFAULT_PROBE_AP_PWD = "12345678";
  
  private static final String PROBE_AP_DEFAULT_NAME = "AscannerDAP";
  
  private static final String PROBE_AP_NAME_PREFIX = "MYJC";
  
  public static final int WIFI_AP_STATE_DISABLED = 11;
  
  public static final int WIFI_AP_STATE_DISABLING = 10;
  
  public static final int WIFI_AP_STATE_ENABLED = 13;
  
  public static final int WIFI_AP_STATE_ENABLING = 12;
  
  public static final int WIFI_AP_STATE_FAILED = 14;
  
  private Context context;
  
  private LocationManager locationManager;
  
  private WifiManager wifiManager;
  
  @SuppressLint({"WifiManagerPotentialLeak"})
  public WifiHelper(Context paramContext) {
    this.context = paramContext.getApplicationContext();
    this.wifiManager = (WifiManager)paramContext.getSystemService(Context.WIFI_SERVICE);
    this.locationManager = (LocationManager)paramContext.getSystemService(Context.LOCATION_SERVICE);
  }
  
  private WifiConfiguration getWifiConfiguration(String paramString1, String paramString2) {
    WifiConfiguration wifiConfiguration = new WifiConfiguration();
    wifiConfiguration.SSID = stringContentToWifiConfigurationString(paramString1);
    wifiConfiguration.preSharedKey = stringContentToWifiConfigurationString(paramString2);
    return wifiConfiguration;
  }
  
  private boolean isProbeSSID(String paramString) { return (paramString.contains("MYJC") || paramString.contains("AscannerDAP")); }
  
  private String stringContentToWifiConfigurationString(String paramString) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("\"");
    stringBuilder.append(paramString);
    stringBuilder.append("\"");
    return stringBuilder.toString();
  }
  
  public void closeWifi() {
    if (this.wifiManager.isWifiEnabled())
      this.wifiManager.setWifiEnabled(false); 
  }
  
  public boolean closeWifiAP() {
    try {
      Method method = this.wifiManager.getClass().getMethod("setWifiApEnabled", new Class[] { WifiConfiguration.class, boolean.class });
      WifiConfiguration wifiConfiguration = new WifiConfiguration();
      return ((Boolean)method.invoke(this.wifiManager, new Object[] { wifiConfiguration, Boolean.valueOf(false) })).booleanValue();
    } catch (NoSuchMethodException noSuchMethodException) {
      noSuchMethodException.printStackTrace();
      return false;
    } catch (InvocationTargetException invocationTargetException) {
      invocationTargetException.printStackTrace();
      return false;
    } catch (IllegalAccessException illegalAccessException) {
      illegalAccessException.printStackTrace();
      return false;
    } 
  }
  
  public boolean disableProbeNetwork() {
    for (WifiConfiguration wifiConfiguration : this.wifiManager.getConfiguredNetworks()) {
      int i = wifiConfiguration.networkId;
      String str = wifiConfiguration.SSID;
      StringBuilder stringBuilder2 = new StringBuilder();
      stringBuilder2.append("config:");
      stringBuilder2.append(str);
      LogUtils.LOGI("disableProbeNetwork", stringBuilder2.toString());
      if (isProbeSSID(str)) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("disable:");
        stringBuilder.append(wifiConfiguration.SSID);
        LogUtils.LOGI("disableProbeNetwork", stringBuilder.toString());
        this.wifiManager.disableNetwork(i);
        continue;
      } 
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("enable:");
      stringBuilder1.append(wifiConfiguration.SSID);
      LogUtils.LOGI("disableProbeNetwork", stringBuilder1.toString());
      this.wifiManager.enableNetwork(i, true);
    } 
    return true;
  }
  
  public boolean enableNetwork(@NonNull String paramString1, @NonNull String paramString2) {
    String str = stringContentToWifiConfigurationString(paramString1);
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("wifihelper enableNetwork apName:");
    stringBuilder.append(paramString1);
    stringBuilder.append("  pwd:");
    stringBuilder.append(paramString2);
    LogUtils.LOGI("ssid", stringBuilder.toString());
    Iterator iterator = this.wifiManager.getConfiguredNetworks().iterator();
    boolean bool = true;
    int i = -1;
    while (iterator.hasNext()) {
      WifiConfiguration wifiConfiguration = (WifiConfiguration)iterator.next();
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("apName:");
      stringBuilder1.append(wifiConfiguration.SSID);
      stringBuilder1.append("  id:");
      stringBuilder1.append(wifiConfiguration.networkId);
      LogUtils.LOGI("ssid", stringBuilder1.toString());
      if (str.contains(wifiConfiguration.SSID) && !TextUtils.isEmpty(wifiConfiguration.SSID)) {
        LogUtils.LOGI("ssid", "found SSID equal.");
        i = wifiConfiguration.networkId;
        bool = false;
        continue;
      } 
      this.wifiManager.disableNetwork(wifiConfiguration.networkId);
    } 
    if (bool) {
      WifiConfiguration wifiConfiguration = getWifiConfiguration(paramString1, paramString2);
      i = this.wifiManager.addNetwork(wifiConfiguration);
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("addNetwork:");
      stringBuilder1.append(wifiConfiguration.SSID);
      stringBuilder1.append("  id:");
      stringBuilder1.append(wifiConfiguration.networkId);
      LogUtils.LOGI("ssid", stringBuilder1.toString());
    } 
    if (this.wifiManager.enableNetwork(i, true)) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("enableNetwork id:");
      stringBuilder1.append(i);
      LogUtils.LOGI("ssid", stringBuilder1.toString());
      return false;
    } 
    return this.wifiManager.reconnect();
  }
  
  public String getActiveApName() {
    String str2 = this.wifiManager.getConnectionInfo().getSSID();
    if (TextUtils.isEmpty(str2))
      return ""; 
    String str1 = str2;
    if (str2.startsWith("\"")) {
      str1 = str2;
      if (str2.endsWith("\""))
        str1 = str2.substring(1, str2.length() - 1); 
    } 
    return str1;
  }
  
  public NetworkInfo getActiveNetworkInfo() {
    return ((ConnectivityManager)this.context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo(); }
  
  public WifiInfo getConnectionInfo() { return this.wifiManager.getConnectionInfo(); }
  
  public List<ScanResult> getScanResults() {
    List list = this.wifiManager.getScanResults();
    return (list != null) ? list : new ArrayList();
  }
  
  public int getWifiApState() {
    try {
      return ((Integer)this.wifiManager.getClass().getMethod("getWifiApState", new Class[0]).invoke(this.wifiManager, new Object[0])).intValue();
    } catch (InvocationTargetException invocationTargetException) {
      invocationTargetException.printStackTrace();
      return 14;
    } catch (NoSuchMethodException noSuchMethodException) {
      noSuchMethodException.printStackTrace();
      return 14;
    } catch (IllegalAccessException illegalAccessException) {
      illegalAccessException.printStackTrace();
      return 14;
    } 
  }
  
  public int getWifiState() { return this.wifiManager.getWifiState(); }
  
  public boolean isLocationServiceEnabled() { return
          (this.locationManager.isProviderEnabled("network") || this.locationManager.isProviderEnabled("gps")); }
  
  public boolean isNetworkConnected() {
    NetworkInfo networkInfo = ((ConnectivityManager)this.context.getSystemService(Context.CONNECTIVITY_SERVICE))
            .getActiveNetworkInfo();
    return (networkInfo != null && networkInfo.isConnected());
  }
  
  public boolean isWifiApEnabled() { return (getWifiApState() == 13); }
  
  public boolean isWifiEnabled() { return this.wifiManager.isWifiEnabled(); }
  
  public void openWifi() {
    if (this.wifiManager.isWifiEnabled())
      this.wifiManager.setWifiEnabled(false); 
    this.wifiManager.setWifiEnabled(true);
  }
  
  public boolean openWifiAP(String paramString1, String paramString2) {
    if (isWifiEnabled())
      closeWifi(); 
    if (isWifiApEnabled())
      closeWifiAP(); 
    WifiConfiguration wifiConfiguration = new WifiConfiguration();
    wifiConfiguration.SSID = paramString1;
    wifiConfiguration.preSharedKey = paramString2;
    wifiConfiguration.allowedAuthAlgorithms.set(0);
    wifiConfiguration.allowedKeyManagement.set(1);
    try {
      return ((Boolean)this.wifiManager.getClass().getMethod("setWifiApEnabled", new Class[] { WifiConfiguration.class, boolean.class }).invoke(this.wifiManager, new Object[] { wifiConfiguration, Boolean.valueOf(true) })).booleanValue();
    } catch (NoSuchMethodException param) {
      param.printStackTrace();
      return false;
    } catch (InvocationTargetException param) {
      param.printStackTrace();
      return false;
    } catch (IllegalAccessException param) {
      param.printStackTrace();
      return false;
    } 
  }
  
  public boolean startScan() { return this.wifiManager.startScan(); }
}


/* Location:              D:\androidWord\apkedit\dex2jar-2.0\classes-dex2jar.jar!\com\lee\arrayscan2\service\net\WifiHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */