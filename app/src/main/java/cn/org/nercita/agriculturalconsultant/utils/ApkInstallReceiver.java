package cn.org.nercita.agriculturalconsultant.utils;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;


/**
 * Created by Song on 2016/11/2.
 */
public class ApkInstallReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        if(intent.getAction().equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {
            long downloadApkId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            installApk(context, downloadApkId);
        }else if (intent.getAction().equals(DownloadManager.ACTION_NOTIFICATION_CLICKED)){
            Log.e("下载","xaizaizazaixi");
        }
    }

    /**
     * 安装apk
     */
    private void installApk(Context context,long downloadId) {

        long downId = SPUtil.getLong(context,DownloadManager.EXTRA_DOWNLOAD_ID, -1L);
        if(downloadId == downId) {
            DownloadManager downManager= (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
            Uri downloadUri = downManager.getUriForDownloadedFile(downloadId);
           SPUtil.putString(context,"downloadApk",downloadUri.getPath());
            if (downloadUri != null) {
                Log.e("UriUriUri",downloadUri.getPath());
                if (Build.VERSION.SDK_INT < 23){
                    Intent install= new Intent(Intent.ACTION_VIEW);
                    install.addCategory("android.intent.category.DEFAULT");
                    install.setDataAndType(downloadUri, "application/vnd.android.package-archive");
                    install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(install);
                }else{
                    File downloadFile = new File(context.getExternalFilesDir(null)+"/"+Environment.DIRECTORY_DOWNLOADS+"/Agricultural.apk");
                    if (downloadFile.exists())
                         openFile(downloadFile,context);
                    else
                        Toast.makeText(context, "文件不存在", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(context, "下载失败", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void openFile(File file, Context context) {
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

}
