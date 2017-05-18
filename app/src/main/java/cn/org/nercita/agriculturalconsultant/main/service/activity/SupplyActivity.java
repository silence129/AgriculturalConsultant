package cn.org.nercita.agriculturalconsultant.main.service.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import cn.org.nercita.agriculturalconsultant.Constants;
import cn.org.nercita.agriculturalconsultant.R;
import cn.org.nercita.agriculturalconsultant.base.BaseActivity;
import cn.org.nercita.agriculturalconsultant.common.WebViewActivity;
import cn.org.nercita.agriculturalconsultant.main.bean.SupplyListBean;
import cn.org.nercita.agriculturalconsultant.main.service.adapter.SupplyListAdapter;
import cn.org.nercita.agriculturalconsultant.utils.JsonUtil;
import cn.org.nercita.agriculturalconsultant.view.TitleBar;
import okhttp3.Call;

/**
 * Created by 梁兴胜 on 2017/4/13.
 * 最新供应更多页面
 */

public class SupplyActivity extends BaseActivity {
    private static final String TAG = "SupplyActivity";
    //标题
    @Bind(R.id.title)
    TitleBar mTitle;
    //搜索框
    @Bind(R.id.et_search_tech_rules)
    EditText etSearch;
    //搜索布局
    @Bind(R.id.ll_search_tech_rules)
    LinearLayout llSearch;
    //列表
    @Bind(R.id.gv_supply)
    PullToRefreshGridView mGridView;
    //搜索词
    private String searchKeyWord = "";
    //数据
    private List<SupplyListBean.ContentBean> mList = new ArrayList<>();
    //Adapter适配器
    private SupplyListAdapter mAdapter;
    //页数
    private int PageNo = 1;
    //用来区分是点的哪类筛选词
    private int searchType = 0;

    @Override
    protected int getContentView() {
        return R.layout.activity_supply;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        init();
    }

    @Override
    protected void initData() {
        super.initData();
        getData(true);
    }

    /**
     * 初始化控件
     *
     * @author: liangxingsheng
     * @date: 2017/4/13 下午3:07
     */
    private void init() {

        mTitle.setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
//        mTitle.setSearch(View.VISIBLE);
        mTitle.setSearchLinstener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (llSearch.getVisibility() == View.GONE){
                    llSearch.setVisibility(View.VISIBLE);
                    searchKeyWord = etSearch.getText().toString().trim();
                }else {
                    llSearch.setVisibility(View.GONE);
                    searchKeyWord = "";
                }

            }
        });
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(SupplyActivity.this, WebViewActivity.class);
//                String url = Constants.BaseUrL23 + Constants.SERVICE_NEWS_DETAIL + "?id=" + mList.get(i).getId();
//                intent.putExtra("url",url);
                intent.putExtra("url",mList.get(i).getUrl());
                startActivity(intent);
            }
        });
        mGridView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
                getData(true);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
                PageNo ++;
                getData(false);
            }
        });
    }


    /**
     * 数据获取
     * @author: liangxingsheng
     * @date: 2017/4/13 下午3:07
     */
    private void getData(final boolean isNew) {

        OkHttpUtils.post()
                .url(Constants.BaseUrL + Constants.SERVICE_SUPPLY_MORE)
                .addParams("currentPage", PageNo+"")
                .build()
                .execute(new StringCallback() {

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        PageNo --;
                        Log.e(TAG, e.toString());
                        if (mGridView != null)
                            mGridView.onRefreshComplete();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG, response);
                        if (mGridView != null)
                            mGridView.onRefreshComplete();
                        SupplyListBean data = JsonUtil.parseJsonToBean(response, SupplyListBean.class);
                        if (data == null || data.getContent().size() == 0){
                            return;
                        }
                        if (isNew){
                            mList.clear();
                        }
                        mList.addAll(data.getContent());
                        if (mAdapter == null){
                            mAdapter = new SupplyListAdapter(SupplyActivity.this, mList);
                            mGridView.setAdapter(mAdapter);
                        }
                        mAdapter.notifyDataSetChanged();
                    }
                });
    }


}
