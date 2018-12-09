package demo.luojun.com.demo.utils;



     import android.util.Log;

     import demo.luojun.com.demo.context.MyAppliction;

public class  LogPrint {

     // public static boolean isDebug = true; 开关在Myapplication 类，发布版本时，应改为false。

     private static final String DEBUG_TAG = "info";

     static int maxLogSize = 3000;

     public static void debugInfo(String msg) {
     if (MyAppliction.isDebug) {    // 由于某些接口服务器返回的数据过大，需要分段打印log

     for (int i = 0; i <= msg.length() / maxLogSize; i++) {

     int start = i * maxLogSize;

     int end = (i + 1) * maxLogSize;

     end = end > msg.length() ? msg.length() : end;

     Log.i(DEBUG_TAG, msg.substring(start, end));

     }
     // Log.i(DEBUG_TAG, msg);
     }
     }

     public static void debugInfo(String tag,String msg) {
         if (MyAppliction.isDebug){    // 由于某些接口服务器返回的数据过大，需要分段打印log
     for (int i = 0; i <= msg.length() / maxLogSize; i++) {

     int start = i * maxLogSize;

     int end = (i + 1) * maxLogSize;

     end = end > msg.length() ? msg.length() : end;

     Log.i(tag, msg.substring(start, end));
     }
     // Log.i(DEBUG_TAG, msg);
     }
     }

     public static void debugInfo(String tag,String msg,Throwable t) {
         if (MyAppliction.isDebug) {    // 由于某些接口服务器返回的数据过大，需要分段打印log

     for (int i = 0; i <= msg.length() / maxLogSize; i++) {

     int start = i * maxLogSize;

     int end = (i + 1) * maxLogSize;
     end = end > msg.length() ? msg.length() : end;

     Log.i(tag, msg.substring(start, end),t);
     }
     // Log.i(DEBUG_TAG, msg);
     }
     }
     public static void debugError(String msg) {
         if (MyAppliction.isDebug){
     Log.e(DEBUG_TAG, msg);
     }
     }

     public static void i(String msg) {
         if (MyAppliction.isDebug) {
     System.out.println(msg);
     }
     }

     public static void i(String tag, String msg) {
         if (MyAppliction.isDebug) {
     Log.i(tag, msg);
     }
     }

     /**
     * logcat 打印Json
     * @param tag
     * @param msg
     */
//    public  static  void  json(String tag, String msg){
//        if (MyAppliction.isDebug) {
//            JsonUtil.printJson(tag,msg,"");
//        }
//    }
    /**
     * logcat 打印Json
     * @param tag
     * @param msg
     * @param headString //
     */
//    public  static  void  json(String tag, String msg,String headString){
//        if (MyApplication.isDebug) {
//            JsonUtil.printJson(tag,msg,headString);
//        }
//    }
}

