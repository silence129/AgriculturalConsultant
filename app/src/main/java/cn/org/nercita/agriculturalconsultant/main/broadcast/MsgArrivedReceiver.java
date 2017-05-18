package cn.org.nercita.agriculturalconsultant.main.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by 范博文 on 2016/12/2.
 * 监听消息到来的广播
 */

public class MsgArrivedReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (listener != null){
            listener.onMsgArrived(context,intent);
        }
    }

    public void setOnMsgArrivedListener(OnMsgArriveListener listener) {
        this.listener = listener;
    }

    private OnMsgArriveListener listener;

    public interface OnMsgArriveListener{
        void onMsgArrived(Context context, Intent intent);
    }
}
