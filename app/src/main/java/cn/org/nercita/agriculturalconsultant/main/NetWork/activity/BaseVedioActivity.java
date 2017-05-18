package cn.org.nercita.agriculturalconsultant.main.NetWork.activity;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import butterknife.Bind;
import butterknife.OnClick;
import cn.org.nercita.agriculturalconsultant.R;
import cn.org.nercita.agriculturalconsultant.base.BaseActivity;
import cn.org.nercita.agriculturalconsultant.main.NetWork.adapter.BaseVedioPagerAdapter;
import cn.org.nercita.agriculturalconsultant.main.NetWork.adapter.NBaseTempInfoAdapter;
import cn.org.nercita.agriculturalconsultant.main.NetWork.adapter.SelectVideoAdater;
import cn.org.nercita.agriculturalconsultant.main.bean.BaseInfo;
import cn.org.nercita.agriculturalconsultant.main.bean.BaseListInfo;
import cn.org.nercita.agriculturalconsultant.main.bean.BasePlotInfo;
import cn.org.nercita.agriculturalconsultant.utils.DensityUtils;
import cn.org.nercita.agriculturalconsultant.utils.JsonUtil;
import cn.org.nercita.agriculturalconsultant.view.LazyViewPager;
import cn.org.nercita.agriculturalconsultant.view.MeasuredGridView;
import cn.org.nercita.agriculturalconsultant.view.TitleBar;
import okhttp3.Call;
import pl.droidsonroids.gif.GifImageView;

/**
 * Created by fan on 2017/4/13.
 * 基地详情
 */

public class BaseVedioActivity extends BaseActivity implements DrawerLayout.DrawerListener {
    private static final String TAG = BaseVedioActivity.class.getSimpleName();
    @Bind(R.id.tb_title)
    TitleBar tbTitle;
    @Bind(R.id.empty_image)
    GifImageView emptyImage;
    @Bind(R.id.empty_text)
    TextView emptyText;
    @Bind(R.id.no_content)
    RelativeLayout noContent;
    @Bind(R.id.vp_vedio)
    LazyViewPager vpVedio;
    @Bind(R.id.nullvedio)
    TextView nullvedio;
    @Bind(R.id.rel_base_video)
    FrameLayout relBaseVideo;
    @Bind(R.id.text)
    TextView text;
    @Bind(R.id.left)
    ImageView left;
    @Bind(R.id.stationname)
    TextView stationname;
    @Bind(R.id.right)
    ImageView right;
    @Bind(R.id.gv)
    MeasuredGridView gv;
    @Bind(R.id.vediolayout)
    LinearLayout vediolayout;
    @Bind(R.id.text2)
    TextView text2;
    @Bind(R.id.baseintroduce)
    TextView baseintroduce;
    @Bind(R.id.linkman)
    TextView linkman;
    @Bind(R.id.linkmanphone)
    TextView linkmanphone;
    @Bind(R.id.location)
    TextView location;
    @Bind(R.id.drawer_list)
    ListView drawerList;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    private BaseListInfo.DataBean contentBean;
    private List<BasePlotInfo> plotInfo = new ArrayList<>();
    private List<BaseInfo.IpcameralistBean> device = new ArrayList<>();
    private NBaseTempInfoAdapter baseTempInfoAdapter;
    private int basenumber;
    private List<String> data = new ArrayList<>();//摄像头名称
    private BaseVedioPagerAdapter vedioadapter;
    private final static int BASE_CURRENT = -100;
    private View view;
    private ArrayList<String> data_select;
    private SelectVideoAdater adater;
    private int currentnumber = 0;

    @Override
    protected int getContentView() {
        return R.layout.acitvity_baseactivity;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
    }

    @Override
    protected void initData() {
        super.initData();
        //得到从上个界面获取的数据
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        int position = extras.getInt("position", 0);
        ArrayList<BaseListInfo.DataBean> data = extras.getParcelableArrayList("data");
        contentBean = data.get(position);
        //如果基地code为空，视频和基地数据不显示
        if ("0".equals(data.get(position).getType())) {
            relBaseVideo.setVisibility(View.GONE);
            vediolayout.setVisibility(View.GONE);
        } else {
            //显示数据并请求
            loading();
            getInfo(contentBean.getCode());
            tbTitle.setTitleRight("摄像头");
            tbTitle.setCommit(View.VISIBLE);
        }
        //设置基地数据
        setBaseInfo();
        drawerLayout.addDrawerListener(this);

    }

    /**
     * author:范博文
     * date:2017/4/13 16:55
     * des:设置基地数据
     * param:null
     * return:null
     */
    private void setBaseInfo() {
        if (!TextUtils.isEmpty(contentBean.getLinkname())) {
            linkman.setText(contentBean.getLinkname());
        }
        if (!TextUtils.isEmpty(contentBean.getAddress())) {
            location.setText(contentBean.getAddress());
        }
        if (!TextUtils.isEmpty(contentBean.getBz())) {
            baseintroduce.setText(contentBean.getBz());
        }
        if (!TextUtils.isEmpty(contentBean.getTel())) {
            linkmanphone.setText(contentBean.getTel());
        }
        tbTitle.setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        tbTitle.setCommitListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout != null) {
                    if (adater != null) {
                        adater.setDataPositon(vpVedio.getCurrentItem());
                        adater.notifyDataSetChanged();
                    }
                    drawerLayout.openDrawer(Gravity.RIGHT);

                }
            }
        });

    }

    /**
     * author:范博文
     * date:2017/4/13 15:49
     * des:根据基地code获取基地数据
     *
     * @param iotCode 基地code
     *                return:
     */
    private void getInfo(String iotCode) {
        OkHttpUtils.get()
                .url("http://123.127.160.21/display/easyapi/mobile/getGreenhouseByStationcode")
                .addParams("code", iotCode)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, e.getMessage() + "");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG, response);
                        if (response == null || response.equals("")) {
                            stationname.setText("暂无数据");
                            gv.setVisibility(View.GONE);
                        }
                        paseInfo(response);
                    }
                });
    }

    /**
     * author:范博文
     * date:2017/4/13 15:58
     * des:解析获得的数据
     *
     * @param response 基地数据
     *                 return:null
     */
    private void paseInfo(String response) {
        Type type = new TypeToken<List<BaseInfo>>() {
        }.getType();
        //获取基地信息
        List<BaseInfo> objects = (List<BaseInfo>) JsonUtil.parseJsonToList
                (response, type);
        //判断基地有多少检测点
        if (objects != null && objects.size() > 0) {
            for (int i = 0; i < objects.size(); i++) {
                //获得检测点名称
                String name = objects.get(i).getName();
                List<BaseInfo.StationFactoresBean> stationFactores = objects.get
                        (i).getStationFactores();

                if (stationFactores != null && stationFactores.size() > 0) {
                    //将检测点名称及数据添加到集合中
                    BasePlotInfo info = new BasePlotInfo(name, stationFactores);
                    plotInfo.add(info);
                }
                //获取温室摄像头数据
                List<BaseInfo.IpcameralistBean> ipcameralist = objects.get(i)
                        .getIpcameralist();
                //一个温室可能有多个摄像头，遍历摄像头列表
                if (ipcameralist != null && ipcameralist.size() > 0) {
                    for (int j = 0; j < ipcameralist.size(); j++) {
                        ipcameralist.get(j).setName(name + "-" + ipcameralist.get
                                (j).getName());
                        if (ipcameralist.get(j) != null) {
                            //得到摄像头列表
                            device.add(ipcameralist.get(j));
                        }
                    }
                }
            }
            if (plotInfo != null && plotInfo.size() > 0) {
                //基地数据的adapter
                baseTempInfoAdapter = new NBaseTempInfoAdapter(plotInfo.get(0)
                        .getList(), BaseVedioActivity.this);
                stationname.setText(plotInfo.get(0).getName());
                //基地检测点数据
                basenumber = plotInfo.size();
                gv.setAdapter(baseTempInfoAdapter);
                gv.setVisibility(View.VISIBLE);
            } else {
                stationname.setText("暂无数据");
            }
        } else {
            stationname.setText("暂无数据");
        }
        //如果摄像头里面有一条数据为空的话，那么将这条数据移除
        ListIterator<BaseInfo.IpcameralistBean> cameraListBeanListIterator = device.listIterator();
        while (cameraListBeanListIterator.hasNext()) {
            BaseInfo.IpcameralistBean next = cameraListBeanListIterator.next();
            if (TextUtils.isEmpty(next.getHttpPort() + "") || TextUtils.isEmpty(next.getSdkPort()
                    + "") || TextUtils.isEmpty(next.getUserName())
                    || TextUtils.isEmpty(next.getPassword())) {
                cameraListBeanListIterator.remove();

            }
        }
        //设置摄像头数据
        if (device != null && device.size() != 0) {
            vpVedio.setVisibility(View.VISIBLE);

            Log.e(TAG, device.size() + "seadapter");
            nullvedio.setVisibility(View.INVISIBLE);
            vedioadapter = new BaseVedioPagerAdapter(BaseVedioActivity.this,
                    device, vpVedio, nullvedio, noContent);
            vpVedio.setAdapter(vedioadapter);
            vedioadapter.vedioreal.setVisibility(View.VISIBLE);
            noContent.setVisibility(View.GONE);
        } else {
            vpVedio.setVisibility(View.INVISIBLE);
            nullvedio.setVisibility(View.VISIBLE);
            noContent.setVisibility(View.GONE);
            nullvedio.setText("暂无视频数据");
        }
        data.clear();
        for (int i = 0; i < device.size(); i++) {
            String vedioName = device.get(i).getName();
            data.add(vedioName);
        }
        initSelect(device, BASE_CURRENT);

    }

    @OnClick({R.id.left, R.id.right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.left:
                if (basenumber <= 1) return;
                currentnumber--;
                if (currentnumber < 0) {
                    currentnumber = basenumber - 1;
                }
                if (plotInfo == null || plotInfo.size() == 0) {
                    stationname.setText("暂无数据");
                } else {
                    stationname.setText(plotInfo.get(currentnumber).getName());
                    baseTempInfoAdapter.setData(plotInfo.get(currentnumber).getList());

                }

                break;
            case R.id.right:
                if (basenumber <= 1) return;
                currentnumber++;
                if (currentnumber == basenumber) {
                    currentnumber = 0;
                }
                if (plotInfo == null || plotInfo.size() == 0) return;
                stationname.setText(plotInfo.get(currentnumber).getName());
                baseTempInfoAdapter.setData(plotInfo.get(currentnumber).getList());
                break;
        }
    }

    /**
     * author:范博文
     * date:2017/4/13 15:46
     * des:loading 画面
     * param:null
     * return:null
     */
    public void loading() {
        try {
            noContent = (RelativeLayout) findViewById(R.id.no_content);
            //图片
            ImageView emptyImage = (ImageView) findViewById(R.id.empty_image);
            //文字提示
            emptyText = (TextView) findViewById(R.id.empty_text);
            emptyImage.setBackgroundResource(R.drawable.loading);
//            emptyText.setText("正在加载");
            noContent.setVisibility(View.VISIBLE);
        } catch (NullPointerException e) {
            Toast.makeText(BaseVedioActivity.this, "请在布局中include layout: empty_background @author" +
                            " liuyang",
                    Toast.LENGTH_LONG).show();
            Log.e("@author liuyang：加载中的布局", "请在布局中include layout: empty_background @author " +
                    "liuyang");
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (vedioadapter != null) {
            vedioadapter.stopVedio();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (vedioadapter != null) {
            vedioadapter.startVedio();
        }
    }

    /**
     * 设置选线adapter
     *
     * @param
     */
    public void initSelect(List<BaseInfo.IpcameralistBean> device_select, int current) {
        if (view == null) {
            view = LayoutInflater.from(this).inflate(R.layout.drawer_list_head, null);
            AbsListView.LayoutParams params = new AbsListView.LayoutParams(AbsListView
                    .LayoutParams.MATCH_PARENT, DensityUtils.dp2px(this, 80));
//            drawer_head_basename = (TextView) view.findViewById(R.id.drawer_head_basename);
//            drawer_head_baselocation = (TextView) view.findViewById(R.id
// .drawer_head_baselocation);
            view.setLayoutParams(params);
            drawerList.addHeaderView(view);
        }
        if (device_select == null && device_select.size() <= 0) {
            return;
        }
        data_select = new ArrayList<String>();
        for (int i = 0; i < device_select.size(); i++) {
            String vedioName = device_select.get(i).getName();
            data_select.add(vedioName);
        }
        if (data_select.size() > 0) {
            adater = new SelectVideoAdater(data_select, this, vpVedio.getCurrentItem());
            String adress = "";
            drawerList.setAdapter(adater);
        }
        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //TODO 点击事件后处理逻辑
                vedioadapter.isskip = true;
                vpVedio.setCurrentItem(position - 1);
                if (drawerLayout != null) {
                    drawerLayout.closeDrawers();
                }
            }
        });

    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {

    }

    @Override
    public void onDrawerOpened(View drawerView) {
        openDrawer(false);
    }

    @Override
    public void onDrawerClosed(View drawerView) {

    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }

    /**
     * 点击抽屉和滑动抽屉操作
     */
    public void openDrawer(boolean isClickOpen) {
        if (drawerLayout != null) {
            if (adater != null) {
                if (vpVedio != null)
                    adater.setDataPositon(vpVedio.getCurrentItem());
                if (data_select != null && data_select.size() > 0) {
                    adater.notifyDataSetChanged();
                }

            }
            if (isClickOpen) {
                drawerLayout.openDrawer(Gravity.RIGHT);
            }

        }
    }
}
