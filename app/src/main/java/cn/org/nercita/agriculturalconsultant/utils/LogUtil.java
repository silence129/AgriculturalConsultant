package cn.org.nercita.agriculturalconsultant.utils;

import android.util.Log;

public class LogUtil {
    private static boolean isDebug = true;
    private static String TAG = "Personal";

    public static void e(String msg) {
        if (isDebug) {
            Log.e(TAG, msg);
        }
    }

    public static void e(String msg, String tag) {
        if (isDebug) {
            Log.e(tag, msg);
        }
    }

    public static void e(Object object, String msg) {
        if (isDebug) {
            Log.e(object.getClass().getSimpleName(), msg);
        }
    }
}
