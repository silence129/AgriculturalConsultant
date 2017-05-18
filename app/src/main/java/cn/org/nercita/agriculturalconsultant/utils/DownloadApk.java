package cn.org.nercita.agriculturalconsultant.utils;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;


/**
 * Apk下载
 * Created by Song on 2016/11/2.
 */
public class DownloadApk {

    private static ApkInstallReceiver apkInstallReceiver;

    /**
     * 下载APK文件
     * @param context
     * @param url
     * @param title
     * @param appName
     */
    public static void downloadApk(Context context, String url, String title,final String appName) {
        Log.e("APK路径",context.getExternalFilesDir(null)+"/"+Environment.DIRECTORY_DOWNLOADS+"/Consultant.apk");
        Log.e("url",url+"");
        //获取存储的下载ID
        long downloadId = SPUtil.getLong(context,DownloadManager.EXTRA_DOWNLOAD_ID,-1L);
        if(downloadId != -1) {
            //存在downloadId
            DownLoadUtils downLoadUtils = DownLoadUtils.getInstance(context);
            //获取当前状态
            int status = downLoadUtils.getDownloadStatus(downloadId);
            if(DownloadManager.STATUS_SUCCESSFUL == status) {
                //状态为下载成功
                //获取下载路径URI
                Uri downloadUri = downLoadUtils.getDownloadUri(downloadId);
                Log.e("下载好的Uri",downloadUri.getPath());
                if (Build.VERSION.SDK_INT < 23){
                    if(null != downloadUri) {
                        //存在下载的APK，如果两个APK相同，启动更新界面。否之则删除，重新下载。
                        if(compare(getApkInfo(context,downloadUri.getPath()),context)) {
                            startInstall(context, downloadUri);
                            return;
                        } else {
                            //删除下载任务以及文件
                            downLoadUtils.getDownloadManager().remove(downloadId);
                        }
                    }
                    start(context, url, title,appName);
                }else{
                    if(null != downloadUri) {
                        //存在下载的APK，如果两个APK相同，启动更新界面。否之则删除，重新下载。
                        if(compare(getApkInfo(context,context.getExternalFilesDir(null)+"/"+Environment.DIRECTORY_DOWNLOADS+"/Consultant.apk"),context)) {
                            startInstall(context, downloadUri);
                            return;
                        } else {
                            //删除下载任务以及文件
                            downLoadUtils.getDownloadManager().remove(downloadId);
                        }
                    }
                    start(context, url, title,appName);
                }

            } else if(DownloadManager.STATUS_FAILED == status) {
                //下载失败,重新下载
                start(context, url, title,appName);
            }else {
                Log.d(context.getPackageName(), "apk is already downloading");
            }
        } else {
            //不存在downloadId，没有下载过APK
            start(context, url, title,appName);
        }
    }

    /**
     * 开始下载
     * @param context
     * @param url
     * @param title
     * @param appName
     */
    private static void start(Context context, String url, String title,String appName) {

        if(hasSDKCard()) {
            long id = DownLoadUtils.getInstance(context).download(url,
                    title, "下载完成后点击打开", appName);
            SPUtil.setLong(context,DownloadManager.EXTRA_DOWNLOAD_ID,id);
        } else {
            Toast.makeText(context,"手机未安装SD卡，下载失败",Toast.LENGTH_LONG).show();
        }
    }

    public static void registerBroadcast(Context context) {
        apkInstallReceiver = new ApkInstallReceiver();
        context.registerReceiver(apkInstallReceiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }

    public static void unregisterBroadcast(Context context) {
        if(null != apkInstallReceiver) {
            context.unregisterReceiver(apkInstallReceiver);
        }
    }

    /**
     * 跳转到安装界面
     * @param context
     * @param uri
     */
    private static void startInstall(Context context, Uri uri) {

        if (Build.VERSION.SDK_INT < 23){
            Intent install= new Intent(Intent.ACTION_VIEW);
            install.addCategory("android.intent.category.DEFAULT");
            install.setDataAndType(uri, "application/vnd.android.package-archive");
            install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(install);
        }else{
            File downloadFile = new File(context.getExternalFilesDir(null)+"/"+Environment.DIRECTORY_DOWNLOADS+"/Consultant.apk");
            if (downloadFile.exists())
            openFile(downloadFile,context);
        }
    }
    public static void openFile(File file, Context context) {
        Intent intent = new Intent();
        intent.addFlags(268435456);
        intent.setAction("android.intent.action.VIEW");
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        try {
            context.startActivity(intent);
        } catch (Exception var5) {
            var5.printStackTrace();
            Toast.makeText(context, "没有找到打开此类文件的程序", Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * 获取APK程序信息
     * @param context
     * @param path
     * @return
     */
    private static PackageInfo getApkInfo(Context context, String path) {

        PackageManager pm = context.getPackageManager();
        PackageInfo pi = pm.getPackageArchiveInfo(path, PackageManager.GET_ACTIVITIES);
        if(null != pi) {
            return pi;
        }
        return null;
    }


    /**
     * 比较两个APK的信息
     * @param apkInfo
     * @param context
     * @return
     */
    private static boolean compare(PackageInfo apkInfo,Context context) {

        if(null == apkInfo) {
            return false;
        }
        String localPackageName = context.getPackageName();
        if(localPackageName.equals(apkInfo.packageName)) {
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(localPackageName, 0);
                //比较当前APK和下载的APK版本号
                if (apkInfo.versionCode > packageInfo.versionCode) {
                    //如果下载的APK版本号大于当前安装的APK版本号，返回true
                    return true;
                }
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 是否存在SD卡
     */
    private static boolean hasSDKCard() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 删除已下载的文件
     */
    public static void removeFile(Context context) {
        String filePath = SPUtil.getString(context,"downloadApk",null);
                    Log.e("---filePath",filePath+ "");
        if(null != filePath) {
            File downloadFile=null;
            if (Build.VERSION.SDK_INT < 23){
             downloadFile = new File(filePath);
            }else {
                downloadFile = new File(context.getExternalFilesDir(null)+"/"+Environment.DIRECTORY_DOWNLOADS+"/Consultant.apk");
            }
                    Log.e("---downloadFile",downloadFile+ "");

            if(null != downloadFile && downloadFile.exists()) {
                    Log.e("---文件存在", "");
                //删除之前先判断用户是否已经安装了，安装了才删除。
                if(!compare(getApkInfo(context,filePath),context)) {
                    downloadFile.delete();
                    Log.e("----shanchu", "已删除");
                }
            }
        }
    }


}
