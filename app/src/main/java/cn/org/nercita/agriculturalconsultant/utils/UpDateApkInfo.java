package cn.org.nercita.agriculturalconsultant.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import cn.org.nercita.agriculturalconsultant.main.bean.ApkInfo;
import okhttp3.Call;

/**
 * Created by fan on 2017/4/13.
 * 版本升级
 */

public class UpDateApkInfo {
    /* 查询apk版本的url */
    private static final String searchAPK_URL = "http://123.127.160.21/display/easyapi/mobile/searchAPK?versionType=7";
    private static final String TAG = UpDateApkInfo.class.getSimpleName();
    private ApkInfo apkInfo;
    private Context context;
    private static UpDateApkInfo upDateApkInfo;
    public static UpDateApkInfo getInstence(){
        if (upDateApkInfo==null){
            upDateApkInfo = new UpDateApkInfo();
        }
        return upDateApkInfo;
    }
    public void updateAPk(Context context){
        this.context = context;
        checkVersionUpdate(showVersion(context));
    }
    /**
     * 获取版本号
     *
     * @param context
     * @return
     */

    private int showVersion(Context context) {
        if (context == null) {
            return 9999;
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context
                    .getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 9999;
    }
    /**
     * 检查版本更新
     *
     * @param localVersionCode version
     */
    private void checkVersionUpdate(final int localVersionCode) {

        OkHttpUtils.get().url(searchAPK_URL).build().execute(new StringCallback() {


            @Override
            public void onError(Call call, Exception e, int id) {
//                Toast.makeText(BaseVedioActivity.this, "请检查网络连接", Toast.LENGTH_SHORT).show();
                Log.e(TAG, e.getMessage() + "");
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e(TAG, response);
                apkInfo = JsonUtil.parseJsonToBean(response, ApkInfo.class);
                Log.e(TAG, apkInfo.getVersion() + "---" + localVersionCode );
                if (apkInfo != null) {
                    if (Integer.parseInt(apkInfo.getVersion()) > localVersionCode) {
                        //如果版本大于当前apk版本 提示更新
                        showAlertUpdate(apkInfo.getVersionApk());

                    }
                } else {
//                    Toast.makeText(BaseVedioActivity.this, "请检查网络连接", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    /**
     * 提示版本更新
     * 修改升级apk 方法
     *范博文
     * 2017.4.5
     * @param url apk地址
     */
    private void showAlertUpdate(final String url) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("发现新版本更新");
        builder.setPositiveButton("立即更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (ContextCompat.checkSelfPermission(context, Manifest.permission
                        .WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    //Android 6.0申请权限
                    String[] sdcard = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest
                            .permission.WRITE_EXTERNAL_STORAGE};
                    ActivityCompat.requestPermissions((Activity) context, sdcard, 2);
                } else {
                    Log.e(TAG, "权限申请ok");
                    if (NetUtils.isMobileNetConntected((Activity) context)) {
                        Log.e(TAG, "联的是移动");
                        AlertDialog.Builder buildertwo = new AlertDialog.Builder(context);
                        buildertwo.setTitle("检测到您实用的是移动网络，是否继续？");
                        buildertwo.setPositiveButton("继续更新", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (DownLoadUtils.getInstance(context)
                                        .canDownload()) {
                                    DownloadApk.downloadApk(context, url,
                                            "农事咨询更新", "Consultant");
                                } else {
                                    DownLoadUtils.getInstance(context)
                                            .skipToDownloadManager();
                                }
                            }
                        });
                        buildertwo.setNegativeButton("下次再说", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        buildertwo.create().show();
                    } else {
                        if (DownLoadUtils.getInstance(context).canDownload()) {
                            DownloadApk.downloadApk(context, url, "农事资讯",
                                    "Consultant");
                        } else {
                            DownLoadUtils.getInstance(context)
                                    .skipToDownloadManager();
                        }
                    }
                }
            }
        });
        builder.setNegativeButton("下次再说", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //toNextActivity(false);
            }
        });
        builder.create().show();

    }

}
