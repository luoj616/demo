package demo.luojun.com.demo.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by  on 2017/12/8.
 */
public class ToastUtils {

    private static Toast toast;

    public static void showToast(Context context, String message) {
        if (toast == null) {
            toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        } else {
            toast.setText(message);
        }
        toast.show();
    }


}
