package cn.org.nercita.agriculturalconsultant;


import android.app.Application;
import android.content.Intent;
import android.util.Log;

import com.hikvision.netsdk.HCNetSDK;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.controller.EaseUI;

import java.util.List;

import cn.org.nercita.agriculturalconsultant.utils.DatabaseUtil;

/**
 * 全局应用程序类
 * 用于保存和调用全局应用配置及访问网络数据
 */
public class AppContext extends Application {
    public static final int PAGE_SIZE = 20;// 默认分页大小
    private static AppContext instance;
    //收到消息的广播fitter
    public static final String ON_MSG_RECEIVE = "receive_msg";

    @Override
    public void onCreate() {
        super.onCreate();
        DatabaseUtil.packDataBase(this);
        instance = this;
        //初始化环信
        initMob();
        initHKSdk();
    }

    /**
     * author:范博文
     * date:2017/4/6 15:56
     * des:初始化环信
     * param:null
     * return:null
     */
    private void initMob() {
        EMOptions options = new EMOptions();
        // 默认添加好友时，是不需要验证的，改成需要验证
//        options.setAcceptInvitationAlways(false);
        options.setAutoLogin(true);
        //初始化
        if (EaseUI.getInstance().init(getApplicationContext(), options)) {
            //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
//        EMClient.getInstance().setDebugMode(true);

            //给环信做收到消息的监听
            EMMessageListener messageListener = new EMMessageListener() {
                private String acition;
                private String groupid;
                private String userName;

                @Override
                public void onMessageReceived(List<EMMessage> messages) {
                    Intent intent = new Intent();
                    Log.e("AppContext", messages.size() + "message");
                    for (int i = 0; i < messages.size(); i++) {
                        userName = messages.get(i).getUserName();
                        String to = messages.get(i).getTo();
                        groupid = messages.get(i).getTo();
                        intent.putExtra("groupid", groupid);
                        acition = ON_MSG_RECEIVE;
                        Log.e("AppContext", userName + "to=" + to);

                    }
                    intent.putExtra("username", userName);
                    intent.setAction(acition);
                    //发送广播
                    sendBroadcast(intent);
                }
                //透传消息
                @Override
                public void onCmdMessageReceived(List<EMMessage> list) {
                    Intent intent = new Intent();
//                    intent.setAction(ON_MSG_APPLY);
                    sendBroadcast(intent);

                }

                @Override
                public void onMessageReadAckReceived(List<EMMessage> list) {
                }

                @Override
                public void onMessageDeliveryAckReceived(List<EMMessage> list) {
                }

                @Override
                public void onMessageChanged(EMMessage emMessage, Object o) {
                }
            };
            EMClient.getInstance().chatManager().addMessageListener(messageListener);
        }
    }
    /**
    * author:范博文
    * date:2017/4/13 16:34
    * des:初始化海康sdk
    * param:
    * return:
    */
    private boolean initHKSdk() {
        //init net sdk
        if (!HCNetSDK.getInstance().NET_DVR_Init()) {
            return false;
        }
        return true;
    }
    /**
     * 获得当前app运行的AppContext
     *
     * @return AppContext
     */
    public static AppContext getInstance() {
        return instance;
    }


}