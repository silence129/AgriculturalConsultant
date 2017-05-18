package cn.org.nercita.agriculturalconsultant.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

import com.hikvision.netsdk.HCNetSDK;
import com.hikvision.netsdk.NET_DVR_PREVIEWINFO;
import com.hikvision.netsdk.RealPlayCallBack;

import org.MediaPlayer.PlayM4.Player;


@SuppressLint("NewApi")
public class PlaySurfaceViewCopy extends SurfaceView implements Callback {
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (m_iPreviewHandle < 0) {
                Log.e(TAG, "NET_DVR_RealPlay is failed!Err:"
                        + HCNetSDK.getInstance().NET_DVR_GetLastError());
            }
        }
    };
    private final String TAG = "PlaySurfaceView";
    public int m_iPreviewHandle = -1;
    private int m_iPort = -1;

    private boolean m_bSurfaceCreated = false;

    public PlaySurfaceViewCopy(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        getHolder().addCallback(this);
        Log.e(TAG, "1");
    }

    public PlaySurfaceViewCopy(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);
        Log.e(TAG, "2");
    }

    public PlaySurfaceViewCopy(Context context) {
        super(context);
        getHolder().addCallback(this);
        Log.e(TAG, "3");
    }

    @Override
    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
        System.out.println("surfaceChanged");
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        m_bSurfaceCreated = true;
        getHolder().setFormat(PixelFormat.TRANSLUCENT);
        if (-1 == m_iPort) {
            return;
        }
        Surface surface = holder.getSurface();
        if (true == surface.isValid()) {
            if (false == Player.getInstance()
                    .setVideoWindow(m_iPort, 0, holder)) {
                Log.e(TAG, "Player setVideoWindow failed!");
            }
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        m_bSurfaceCreated = false;
        if (-1 == m_iPort) {
            return;
        }
        if (true == holder.getSurface().isValid()) {
            if (false == Player.getInstance().setVideoWindow(m_iPort, 0, null)) {
                Log.e(TAG, "Player setVideoWindow failed!");
            }
        }
    }

    public void startPreview(final int iUserID, int iChan) {
        final RealPlayCallBack fRealDataCallBack = getRealPlayerCbf();
        if (fRealDataCallBack == null) {
            Log.e(TAG, "fRealDataCallBack object is failed!");
            return;
        }
        Log.i(TAG, "preview channel:" + iChan);

        final NET_DVR_PREVIEWINFO previewInfo = new NET_DVR_PREVIEWINFO();
        previewInfo.lChannel = iChan;
        previewInfo.dwStreamType = 1; //substream
        previewInfo.bBlocked = 1;
        previewInfo.dwLinkMode = 0;
        Log.e(TAG,previewInfo.dwLinkMode+"linkmode");
        // HCNetSDK start preview
        new Thread(new Runnable() {
            @Override
            public void run() {
                m_iPreviewHandle = HCNetSDK.getInstance().NET_DVR_RealPlay_V40(iUserID,
                        previewInfo, fRealDataCallBack);
                handler.sendEmptyMessage(0);

            }
        }).start();

    }

    public void stopPreview() {
        boolean b = HCNetSDK.getInstance().NET_DVR_StopRealPlay(m_iPreviewHandle);
        if (b){
            int i = HCNetSDK.getInstance().NET_DVR_GetLastError();
            Log.e(TAG,"stop veido error code = "+i);
        }

        stopPlayer();
    }

    private void stopPlayer() {
        Player.getInstance().stopSound();
        // player stop play

        if (!Player.getInstance().stop(m_iPort)) {
            int lastError = Player.getInstance().getLastError(m_iPort);
            Log.e(TAG,"stop m_iport"+m_iPort);
            return;
        }

        if (!Player.getInstance().closeStream(m_iPort)) {
            Log.e(TAG, "closeStream is failed!");
            return;
        }
        if (!Player.getInstance().freePort(m_iPort)) {
            Log.e(TAG, "freePort is failed!" + m_iPort);
            return;
        }

        m_iPort = -1;
    }

    private RealPlayCallBack getRealPlayerCbf() {
        RealPlayCallBack cbf = new RealPlayCallBack() {
            public void fRealDataCallBack(int iRealHandle, int iDataType,
                                          byte[] pDataBuffer, int iDataSize) {
//                Log.e("Base","iRealHandle"+iRealHandle+"--"+"iDataType"+iDataType);
                processRealData(1, iDataType, pDataBuffer, iDataSize,
                        Player.STREAM_REALTIME);
            }
        };
        return cbf;
    }

    private void processRealData(int iPlayViewNo, int iDataType,
                                 byte[] pDataBuffer, int iDataSize, int iStreamMode) {
        //   Log.i(TAG, "iPlayViewNo:" + iPlayViewNo + ",iDataType:" + iDataType + ",iDataSize:" + iDataSize);
        if (HCNetSDK.NET_DVR_SYSHEAD == iDataType) {
            if (m_iPort >= 0) {
                return;
            }
            m_iPort = Player.getInstance().getPort();
            Log.e(TAG,"m_iPort start  "+m_iPort);
            if (m_iPort == -1) {
                Log.e(TAG, "getPort is failed with: "
                        + Player.getInstance().getLastError(m_iPort));

                return;
            }
            Log.i(TAG, "getPort succ with: " + m_iPort);
            if (iDataSize > 0) {
                if (!Player.getInstance().setStreamOpenMode(m_iPort,
                        iStreamMode)) //set stream mode
                {
                    Log.e(TAG, "setStreamOpenMode failed");
                    return;
                }
                if (!Player.getInstance().openStream(m_iPort, pDataBuffer,
                        iDataSize, 2 * 1024 * 1024)) //open stream
                {
                    Log.e(TAG, "openStream failed");
                    return;
                }
                while (!m_bSurfaceCreated) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.i(TAG, "wait a100 for surface, handle:" + iPlayViewNo);
                }

                if (!Player.getInstance().play(m_iPort, getHolder())) {
                    Log.e(TAG, "play failed,error:"
                            + Player.getInstance().getLastError(m_iPort));
                    return;
                }
                if (!Player.getInstance().playSound(m_iPort)) {
                    Log.e(TAG, "playSound failed with error code:"
                            + Player.getInstance().getLastError(m_iPort));
                    return;
                }
            }
        } else {
            if (!Player.getInstance()
                    .inputData(m_iPort, pDataBuffer, iDataSize)) {
               /* Log.e(TAG, "inputData failed with: "
                        + Player.getInstance().getLastError(m_iPort));*/
            }
        }
    }
}
