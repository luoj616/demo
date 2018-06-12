package demo.luojun.com.demo.utils;

import android.util.Log;

/**
 * Created by luo.j on 2018/6/12.
 */
public class LogUtils {
    public static void test2() {
        StackTraceElement[] s = Thread.currentThread().getStackTrace();
        for (StackTraceElement state : s) {
            String callerClazzName = state.getClassName();
            callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
            Log.e("info", "-**-" + callerClazzName + "-- *.*-- " + state.getMethodName() + "--  *:* -- " + state);
        }
    }



    private static final char TOP_LEFT_CORNER = '╔';
    private static final char BOTTOM_LEFT_CORNER = '╚';
    private static final char MIDDLE_CORNER = '╟';
    private static final char HORIZONTAL_DOUBLE_LINE = '║';
    private static final String DOUBLE_DIVIDER = "════════════════════════════════════════════";
    private static final String SINGLE_DIVIDER = "────────────────────────────────────────────";
    private static final String TOP_BORDER = TOP_LEFT_CORNER + DOUBLE_DIVIDER + DOUBLE_DIVIDER;
    private static final String BOTTOM_BORDER = BOTTOM_LEFT_CORNER + DOUBLE_DIVIDER + DOUBLE_DIVIDER;
    private static final String MIDDLE_BORDER = MIDDLE_CORNER + SINGLE_DIVIDER + SINGLE_DIVIDER;



  //打印json
//    public  static void printJson(StackTraceElement element,String json){
//
//      /*  if (!KyLog.configAllowLog){
//            return;
//        }*/
//
//        String[] values = generateValues(element);
//        String tag = values[0];
//        String fileName = values[1];
//
//        if (TextUtils.isEmpty(json)){
//            Log.d(tag,"JSON{json is null}");
//            return;
//        }
//        try {
//            if (json.startsWith("{")){
//                JSONObject jsonObject = new JSONObject(json);
//                json = jsonObject.toString(4);
//            }else if (json.startsWith("[")){
//                JSONArray array = new JSONArray(json);
//                json = array.toString(4);
//            }
//            String[] lines = json.split(LINE_SEPARATOR);
//            StringBuilder stringBuilder = new StringBuilder();
//            for (String line: lines){
//                stringBuilder.append("║ ").append(line).append(LINE_SEPARATOR);
//            }
//            Log.d(fileName,TOP_BORDER);
//            Log.d(fileName,HORIZONTAL_DOUBLE_LINE + " " + tag);
//            Log.d(fileName,MIDDLE_BORDER);
//            Log.d(fileName,stringBuilder.toString());
//            Log.d(fileName,BOTTOM_BORDER);
//        }catch (JSONException e){
//            Log.e(tag,e.getMessage() );
//        }
//
//    }


    public  static String[] generateValues(StackTraceElement element){
        String[] values = new String[2];

        StackTraceElement traceElement = element;
        StringBuilder sb = new StringBuilder();
        String className = traceElement.getClassName();
        String fileName = traceElement.getFileName();
        sb.append(className.substring(className.lastIndexOf(".") + 1)).append(".")
                .append(traceElement.getMethodName())
                .append(" (").append(fileName).append(":")
                .append(traceElement.getLineNumber())
                .append(") ");
        String tag = sb.toString();

        values[0] = tag;
        values[1] = fileName;
        return values;
    }
}
