package cn.org.nercita.agriculturalconsultant;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.SystemClock;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import cn.org.nercita.agriculturalconsultant.base.BaseActivity;
import cn.org.nercita.agriculturalconsultant.main.broadcast.MsgArrivedReceiver;
import cn.org.nercita.agriculturalconsultant.nav.NavFragment;
import cn.org.nercita.agriculturalconsultant.nav.NavigationButton;
import cn.org.nercita.agriculturalconsultant.utils.ApkInstallReceiver;
import cn.org.nercita.agriculturalconsultant.utils.DownloadApk;
import cn.org.nercita.agriculturalconsultant.utils.UpDateApkInfo;

/**
 * 主Activity
 *
 * @author: liangxingsheng
 * @date: 2017/4/6 下午2:29
 */
public class MainActivity extends BaseActivity implements
        NavFragment.OnNavigationReselectListener {

    private NavFragment mNavBar;
    private long mBackPressedTime;
    private MsgArrivedReceiver msgReceiver;
    private static ApkInstallReceiver apkInstallReceiver;

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    /**
     * 初始化底部导航
     *
     * @author: liangxingsheng
     * @date: 2017/4/6 下午2:29
     */
    @Override
    protected void initWidget() {
        super.initWidget();
        FragmentManager manager = getSupportFragmentManager();
        mNavBar = ((NavFragment) manager.findFragmentById(R.id.fag_nav));
        mNavBar.setup(this, manager, R.id.main_container, this);
        //检查版本更新 2017.4.13 范博文
        UpDateApkInfo.getInstence().updateAPk(MainActivity.this);
        DownloadApk.registerBroadcast(this);
        //2.删除已存在的Apk
        DownloadApk.removeFile(this);
    }

    @Override
    public void onReselect(NavigationButton navigationButton) {

    }

    /**
     * 双击退出APP
     *
     * @author: liangxingsheng
     * @date: 2017/4/6 下午2:29
     */
    @Override
    public void onBackPressed() {

        long curTime = SystemClock.uptimeMillis();
        if ((curTime - mBackPressedTime) < (3 * 1000)) {
            finish();
        } else {
            mBackPressedTime = curTime;
            Toast.makeText(this, R.string.tip_double_click_exit, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 设置消息到达的监听事件  范博文  2017.4.6
     ****/
    public void setMsgArrivedListener(OnMsgArrivedListener listener) {
        this.listener = listener;
    }

    private OnMsgArrivedListener listener;

    public interface OnMsgArrivedListener {
        void onMsgArrived();
    }
    /***********************************************/
    /**
     * author:范博文
     * date:2017/4/6 15:44
     * des: 注册获取消息的广播接收者
     * param:null
     * return:null
     */
    private void registerMsgListener() {
        msgReceiver = new MsgArrivedReceiver();
        IntentFilter filter = new IntentFilter(AppContext.ON_MSG_RECEIVE);
        registerReceiver(msgReceiver, filter);
        msgReceiver.setOnMsgArrivedListener(new MsgArrivedReceiver.OnMsgArriveListener() {
            @Override
            public void onMsgArrived(Context context, Intent intent) {
                if (listener != null) {
                    listener.onMsgArrived();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销广播接收者
        if (msgReceiver != null) {
            unregisterReceiver(msgReceiver);

        }
        DownloadApk.unregisterBroadcast(MainActivity.this);
    }
}
