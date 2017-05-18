package cn.org.nercita.agriculturalconsultant.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class SPUtil {
    private static SharedPreferences mSp;

    private static SharedPreferences getSharedPreferences(Context context) {
        if (mSp == null) {
            mSp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        return mSp;
    }

    public static void putBoolean(Context context, String key, boolean value) {
        // SharedPreferences sp = mContext.getSharedPreferences("config",
        // Context.MODE_PRIVATE);
        getSharedPreferences(context).edit().putBoolean(key, value).commit();
    }

    public static boolean getBoolean(Context context, String key, boolean defValue) {
        // SharedPreferences sp = mContext.getSharedPreferences("config",
        // Context.MODE_PRIVATE);
        return getSharedPreferences(context).getBoolean(key, defValue);
    }

    public static void putString(Context context, String key, String value) {
        getSharedPreferences(context).edit().putString(key, value).commit();
    }

    public static String getString(Context context, String key, String defValue) {
        return getSharedPreferences(context).getString(key, defValue);
    }

    public static void putInt(Context context, String key, int value) {
        getSharedPreferences(context).edit().putInt(key, value).commit();
    }

    public static int getInt(Context context, String key, int defValue) {
        return getSharedPreferences(context).getInt(key, defValue);
    }

    /**
     * 移除一个字符串值
     *
     * @param context
     * @param key
     */
    public static void removeString(Context context, String key) {
        getSharedPreferences(context).edit().remove(key).commit();
    }
    public static long getLong(Context context,String key,long defValue) {
        return   getSharedPreferences(context).getLong(key,defValue);
    }
    public static void setLong(Context context, String key, Long value) {
        getSharedPreferences(context).edit().putLong(key, value).commit();
    }
    /**
     * 把流里的内容解析成字符串
     *
     * @param is
     * @return
     * @throws IOException
     */
    public static String streamToString(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        while ((len = is.read(buffer)) != -1) {
            baos.write(buffer, 0, len);
        }
        String string = baos.toString();
        baos.close();
        is.close();
        return string;
    }
}
