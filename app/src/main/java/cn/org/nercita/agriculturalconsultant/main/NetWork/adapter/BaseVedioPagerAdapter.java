package cn.org.nercita.agriculturalconsultant.main.NetWork.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hikvision.netsdk.HCNetSDK;
import com.hikvision.netsdk.NET_DVR_DEVICEINFO_V30;
import com.hikvision.netsdk.PTZCommand;

import java.util.List;

import cn.org.nercita.agriculturalconsultant.R;
import cn.org.nercita.agriculturalconsultant.main.NetWork.activity.BaseVedioActivity;
import cn.org.nercita.agriculturalconsultant.main.bean.BaseInfo;
import cn.org.nercita.agriculturalconsultant.view.LazyViewPager;
import cn.org.nercita.agriculturalconsultant.view.PlaySurfaceViewCopy;


/**
 * Created by fan on 2016/12/19.
 */

public class BaseVedioPagerAdapter extends PagerAdapter {
    private static final String TAG = BaseVedioPagerAdapter.class.getSimpleName();
    private Context context;
    private LazyViewPager vpVedio;
    private TextView nullvedio;
    private List<BaseInfo.IpcameralistBean> list;
    private PlaySurfaceViewCopy play1;
    private BaseVedioActivity activity;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                if (!"0".equals(e)) {
                    vedioreal.setVisibility(View.INVISIBLE);
                    nullvedio.setVisibility(View.VISIBLE);
                    nullvedio.setText("设备不在线或联网失败");
                    nocontent.setVisibility(View.INVISIBLE);
                } else {
                    nullvedio.setVisibility(View.INVISIBLE);
                    vedioreal.setVisibility(View.VISIBLE);
                    nocontent.setVisibility(View.INVISIBLE);
                }
                play1.startPreview(ipcameralistBean.getAccountId(), ipcameralistBean
                        .getChannel());
            }
        }
    };
    private BaseInfo.IpcameralistBean ipcameralistBean;
    public boolean isskip;
    public RelativeLayout vedioreal;
    private final RelativeLayout nocontent;
    private String e;

    public BaseVedioPagerAdapter(Context context, List<BaseInfo.IpcameralistBean> list,
                                 LazyViewPager vpVedio, TextView nullvedio, RelativeLayout nocontent) {
        this.context = context;
        this.list = list;
        this.vpVedio = vpVedio;
        this.nullvedio = nullvedio;
        this.nocontent = nocontent;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        if (isskip) {
            stopVedio();
        }
        skipposition = position;
        ipcameralistBean = list.get(position);
        View view = View.inflate(context, R.layout.item_basevedio, null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                deviceLogins(ipcameralistBean);

                handler.sendEmptyMessage(0);


//                Log.e(TAG, ipcameralistBean.getVedioName());
            }
        }).start();
        play1 = (PlaySurfaceViewCopy) view.findViewById(R.id.sv_detail_vedio);
//        TextView tv_play1 = (TextView) view.findViewById(R.id.tv_play1);
        vedioreal = (RelativeLayout) view.findViewById(R.id.rel);
        ImageView up = (ImageView) view.findViewById(R.id.up);
        ImageView down = (ImageView) view.findViewById(R.id.down);
        ImageView left = (ImageView) view.findViewById(R.id.left);
        ImageView right = (ImageView) view.findViewById(R.id.right);
        ImageView zoomin = (ImageView) view.findViewById(R.id.zoomin);
        ImageView zoomout = (ImageView) view.findViewById(R.id.zoomout);
       /* ImageView suo = (ImageView) view.findViewById(R.id.scan);
        ImageView fang = (ImageView) view.findViewById(R.id.full);
        suo.setVisibility(View.GONE);
        fang.setVisibility(View.VISIBLE);*/
        up.setOnTouchListener(new MyTouchListener(PTZCommand.TILT_UP, ipcameralistBean));
        down.setOnTouchListener(new MyTouchListener(PTZCommand.TILT_DOWN, ipcameralistBean));
        left.setOnTouchListener(new MyTouchListener(PTZCommand.PAN_LEFT, ipcameralistBean));
        right.setOnTouchListener(new MyTouchListener(PTZCommand.PAN_RIGHT, ipcameralistBean));
        zoomin.setOnTouchListener(new MyTouchListener(PTZCommand.ZOOM_OUT, ipcameralistBean));
        zoomout.setOnTouchListener(new MyTouchListener(PTZCommand.ZOOM_IN, ipcameralistBean));
        Log.e(TAG, ipcameralistBean.getAccountId() + "---" + ipcameralistBean.getChannel());
//        Log.e("Adapter",ipcameralistBean.getName());
//        tv_play1.setText(device.getName());
//        startVedio();
        /*fang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putInt("accountId", ipcameralistBean.getAccountId());
                bundle.putInt("channel", ipcameralistBean.getChannel());
                intent.putExtras(bundle);
                intent.setClass(context, DetailVedio.class);
                context.startActivity(intent);
            }
        });*/
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        Log.e(TAG, "destroy");
//        LogUtil.e(MyConstants.TAG, "destroyItem position:"+position);
        if (!isskip) {
            stopVedio();
        }
        isskip = false;
//        if (!b) {
//            Log.e(MyConstants.TAG, "NET_DVR_Logout is failed!Err:"
//                    + HCNetSDK.getInstance().NET_DVR_GetLastError());
//        }
//        restartVedio(skipposition, isskip, list);

    }

    public int skipposition;


    public void restartVedio(int position, boolean isSikp, List<BaseInfo.IpcameralistBean> list) {

        if (isSikp) {
//            LoginVedio.deviceLogins(list.get(position), context);
            play1.startPreview(list.get(position).getAccountId(), list.get(position).getChannel());
            isskip = false;
        }

    }

    /**
     * 注销登录视频
     */
    public void logOutVedio(List<BaseInfo.IpcameralistBean> list) {
        for (int i = 0; i < list.size(); i++) {
            HCNetSDK.getInstance().NET_DVR_Logout_V30(list.get(i).getAccountId());
        }
    }

    /**
     * 停止该界面的所有视频
     */
    public void stopVedio() {
        play1.stopPreview();
//        boolean b = HCNetSDK.getInstance().NET_DVR_Logout_V30(ipcameralistBean.getAccountId());

    }

    /**
     * 播放该界面的所有视频
     */
    public void startVedio() {
        play1.startPreview(ipcameralistBean.getAccountId(), ipcameralistBean.getChannel());


    }

    private class MyTouchListener implements View.OnTouchListener {

        private int mPtzCommand;
        private BaseInfo.IpcameralistBean mDevice;

        public MyTouchListener(int command, BaseInfo.IpcameralistBean mDevice) {
            this.mPtzCommand = command;
            this.mDevice = mDevice;
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    if (!HCNetSDK.getInstance().NET_DVR_PTZControl_Other(
                            mDevice.getAccountId(),
                            mDevice.getChannel(), mPtzCommand,
                            0)) {
                        Log.e(TAG,
                                "start PAN_LEFT failed with error code: "
                                        + HCNetSDK.getInstance()
                                        .NET_DVR_GetLastError());
                    } else {
                        Log.i(TAG, "start PAN_LEFT succ");
                    }
                    break;
                case MotionEvent.ACTION_MOVE:

                    break;
                case MotionEvent.ACTION_UP:
                    if (!HCNetSDK.getInstance().NET_DVR_PTZControl_Other(
                            mDevice.getAccountId(),
                            mDevice.getChannel(), mPtzCommand,
                            1)) {
                        Log.e(TAG,
                                "stop PAN_LEFT failed with error code: "
                                        + HCNetSDK.getInstance()
                                        .NET_DVR_GetLastError());
                    } else {
                        Log.i(TAG, "stop PAN_LEFT succ");
                    }
                    break;
            }
            return true;
        }
    }
    /**
     * 单个设备登录
     *
     * @param device
     * @return
     */
    public void deviceLogins(final BaseInfo.IpcameralistBean device) {
        NET_DVR_DEVICEINFO_V30 m_oNetDvrDeviceInfoV30 = new NET_DVR_DEVICEINFO_V30();
        int userId = HCNetSDK.getInstance().NET_DVR_Login_V30(
                device.getAddress(), device.getSdkPort(), device.getUserName(),
                device.getPassword(), m_oNetDvrDeviceInfoV30);
        device.setAccountId(userId);
        if (userId < 0) {
            e = HCNetSDK.getInstance().NET_DVR_GetLastError()+"";
            Log.e(TAG, "NET_DVR_Login is failed!Err:"
                    + e);

         /*   activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(activity.getApplicationContext(), "摄像头登录失败!", Toast
                            .LENGTH_SHORT).show();
                }
            });*/
            return;
        }
        if (m_oNetDvrDeviceInfoV30.byChanNum > 0) {
            device.setChannel(m_oNetDvrDeviceInfoV30.byStartChan);
        }
        e = HCNetSDK.getInstance().NET_DVR_GetLastError()+"";
        Log.i(TAG, "NET_DVR_Login is Successful!");
    }
}
