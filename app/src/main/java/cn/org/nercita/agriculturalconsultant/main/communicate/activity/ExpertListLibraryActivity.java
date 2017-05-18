package cn.org.nercita.agriculturalconsultant.main.communicate.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import cn.org.nercita.agriculturalconsultant.Constants;
import cn.org.nercita.agriculturalconsultant.R;
import cn.org.nercita.agriculturalconsultant.base.BaseActivity;
import cn.org.nercita.agriculturalconsultant.main.bean.ExpertLibraryBean;
import cn.org.nercita.agriculturalconsultant.main.communicate.adapter.ExpertListAdapter;
import cn.org.nercita.agriculturalconsultant.utils.JsonUtil;
import cn.org.nercita.agriculturalconsultant.utils.ToastUtil;
import cn.org.nercita.agriculturalconsultant.view.TitleBar;
import okhttp3.Call;

/**
 * Created by 范博文 on 2017/4/14.
 * 专家库
 */

public class ExpertListLibraryActivity extends BaseActivity {
    private static final String TAG = ExpertListLibraryActivity.class.getSimpleName();
    public static final int CHOOSE = 1237;
    @Bind(R.id.title)
    TitleBar title;
    @Bind(R.id.lv_list)
    PullToRefreshListView lvList;
    private ExpertListAdapter adapter;
    private boolean pullDown = false;
    private  List<ExpertLibraryBean.ItemBean> allList = new ArrayList<>();
    private int pageNo = 1;

    @Override
    public void finishActivity(int requestCode) {
        super.finishActivity(requestCode);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_expertlistlibrary;
    }

    @Override
    protected void initData() {
        super.initData();
        adapter = new ExpertListAdapter(ExpertListLibraryActivity.this);
        lvList.setAdapter(adapter);
        //设置监听事件
        setListener();
        //获得专家列表
        getExpertList();
    }

    /**
     * author:范博文
     * date:2017/4/14 17:12
     * des:获得专家列表
     * param:null
     * return:null
     */
    private void getExpertList() {
        OkHttpUtils.get()
                .url(Constants.GETEXPERTLIST)
                .addParams("pageSize","10")
                .addParams("currentPage",pageNo+"")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG,e.getMessage()+"");
                        pageNo--;
                        lvList.onRefreshComplete();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG,response);
                        lvList.onRefreshComplete();
                        ExpertLibraryBean expertLibraryBean = JsonUtil.parseJsonToBean(response,
                                ExpertLibraryBean.class);
                        if (expertLibraryBean==null){
                            pageNo--;
                            return;
                        }
                        List<ExpertLibraryBean.ItemBean> item = expertLibraryBean.getItem();
                        if (item==null||item.size()<1){
                            pageNo--;
                            ToastUtil.show(ExpertListLibraryActivity.this,"没有更多数据了");
                            return;
                        }
                        if (pullDown){
                            allList.clear();
                        }
                        allList.addAll(item);
                        adapter.setData(allList);


                    }
                });

    }

    private void setListener() {
        title.setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("name",allList.get(i-1).getName());
                bundle.putString("icon",allList.get(i-1).getPhoto());
                bundle.putString("expertid",String.valueOf(allList.get(i-1).getId()));
                intent.putExtras(bundle);
                setResult(CHOOSE,intent);
                finish();

            }
        });
        lvList.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                pullDown = true;
                pageNo = 1;
                getExpertList();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                pullDown = false;
                pageNo++;
                getExpertList();
            }
        });
    }
}
