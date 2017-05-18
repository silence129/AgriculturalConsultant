package cn.org.nercita.agriculturalconsultant.main.me.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import cn.org.nercita.agriculturalconsultant.Constants;
import cn.org.nercita.agriculturalconsultant.R;
import cn.org.nercita.agriculturalconsultant.base.BaseActivity;
import cn.org.nercita.agriculturalconsultant.main.bean.FarmManageBean;
import cn.org.nercita.agriculturalconsultant.main.me.adapter.FarmManageAdapter;
import cn.org.nercita.agriculturalconsultant.utils.JsonUtil;
import cn.org.nercita.agriculturalconsultant.utils.ToastUtil;
import cn.org.nercita.agriculturalconsultant.view.TitleBar;
import okhttp3.Call;

/**
 * Created by 范博文 on 2017/4/10.
 * 农事管理
 */

public class FarmManageActivity extends BaseActivity {


    private static final String TAG = FarmManageActivity.class.getSimpleName();
    @Bind(R.id.title)
    TitleBar title;
    @Bind(R.id.lv_list)
    PullToRefreshListView lvlist;
    @Bind(R.id.update)
    FloatingActionButton update;
    private FarmManageAdapter adapter;
    private int pageNo = 1;
    private boolean isPullDown = false;
    private List<FarmManageBean.ContentBean> allList = new ArrayList<>();



    @Override
    protected int getContentView() {
        return R.layout.fragment_farmmanage;
    }

    @Override
    protected void initData() {
        super.initData();
        //得到农事管理列表
        getInfo();
        adapter = new FarmManageAdapter(FarmManageActivity.this);
        lvlist.setAdapter(adapter);
        setListener();
        title.setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    /**
     * author:范博文
     * date:2017/4/13 14:29
     * des:设置点击事件
     * param:null
     * return:null
     */
    private void setListener() {
        lvlist.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                isPullDown = true;
                pageNo = 1;
                getInfo();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                isPullDown = false;
                pageNo++;
                getInfo();
            }
        });
    }

    /**
     * author:范博文
     * date:2017/4/13 10:29
     * des:得到农事管理列表
     * param:
     * return:
     */
    public void getInfo() {
        OkHttpUtils.get()
                .url(Constants.FARMERMANAGELIST)
                .addParams("everyPage",10+"")
                .addParams("currentPage",pageNo+"")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, e.getMessage() + "");
                        pageNo--;
                        lvlist.onRefreshComplete();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG, response);
                        lvlist.onRefreshComplete();
                        FarmManageBean farmManageBean = JsonUtil.parseJsonToBean(response,
                                FarmManageBean.class);
                        if (farmManageBean.getContent() == null || farmManageBean.getContent()
                                .size() < 1) {
                            pageNo--;
                            ToastUtil.showShort(FarmManageActivity.this, "暂无数据");
                            return;
                        }
                        List<FarmManageBean.ContentBean> content = farmManageBean.getContent();
                        if (isPullDown){
                            allList.clear();
                        }
                        allList.addAll(content);
                        adapter.setdata(allList);

                    }
                });

    }

    @OnClick({R.id.update})
    public void onClick(View view) {
        switch (view.getId()) {
            //上传记录
            case R.id.update:
                startActivityForResult(new Intent(FarmManageActivity.this, UpDateRecoredActivity.class),1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1){
            if (resultCode==0){
                getInfo();
            }
        }
    }
}
