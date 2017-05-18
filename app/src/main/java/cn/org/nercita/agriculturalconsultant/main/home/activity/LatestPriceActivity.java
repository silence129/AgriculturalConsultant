package cn.org.nercita.agriculturalconsultant.main.home.activity;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

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
import cn.org.nercita.agriculturalconsultant.main.bean.NewPriceBean;
import cn.org.nercita.agriculturalconsultant.main.home.adapter.LatestPriceAdapter;
import cn.org.nercita.agriculturalconsultant.utils.JsonUtil;
import cn.org.nercita.agriculturalconsultant.view.TitleBar;
import okhttp3.Call;

/**
 * Created by 梁兴胜 on 2017/4/13.
 * 最新价格列表
 */

public class LatestPriceActivity extends BaseActivity {
    private static final String TAG = "LawsActivity";
    //标题
    @Bind(R.id.title)
    TitleBar mTitle;
    //列表
    @Bind(R.id.m_listview)
    PullToRefreshListView mListview;
    //搜索框
    @Bind(R.id.et_search)
    EditText etSearch;
    //所搜
    @Bind(R.id.search)
    TextView search;
    //搜索布局
    @Bind(R.id.ll_search)
    LinearLayout llSearch;
    //数据
    private List<NewPriceBean.ContentBean> mList = new ArrayList<>();
    //Adapter适配器
    private LatestPriceAdapter mAdapter;
    //页数
    private int PageNo = 1;
    //用来区分是点的哪类筛选词
    private int searchType = 0;
    //搜索词
    private String searchKeyWord = "";
    //排序
    private String sort = "";

    @Override
    protected int getContentView() {
        return R.layout.activity_list;
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
     * @author: liangxingsheng
     * @date: 2017/4/13 下午3:07
     * @modified_by liangxingsheng
     * @modified_date 2017/4/14 下午3:31
     * 添加搜索
     */
    private void init() {

        mTitle.setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mTitle.setTitle("最新价格");
        mTitle.setSearch(View.VISIBLE);
        mTitle.setSearchLinstener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (llSearch.getVisibility() == View.VISIBLE) {
                    llSearch.setVisibility(View.GONE);
                    searchKeyWord = "";
                } else {
                    llSearch.setVisibility(View.VISIBLE);
                    searchKeyWord = etSearch.getText().toString().trim();
                }
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PageNo = 1;
                searchKeyWord = etSearch.getText().toString().trim();
                getData(true);
            }
        });
        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

//                Intent intent = new Intent(LawsActivity.this, WebViewActivity.class);
//                String url = Constants.BaseUrL + Constants.SERVICE_NEWS_DETAIL + "?id=" + mList.get(i-1).getId();
//                intent.putExtra("url",url);
//                startActivity(intent);
            }
        });
        mListview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                PageNo = 1;
                sort = "";
                getData(true);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                PageNo++;
                getData(false);
            }
        });
    }


    /**
     * 数据获取
     *
     * @author: liangxingsheng
     * @date: 2017/4/13 下午3:07
     */
    private void getData(final boolean isNew) {

        OkHttpUtils.post()
                .url(Constants.BaseUrL + Constants.HOME_PRICE_LIST)
                .addParams("productName", searchKeyWord)
                .addParams("sort", sort)
                .addParams("currentPage", PageNo + "")
                .build()
                .execute(new StringCallback() {

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        PageNo--;
                        Log.e(TAG, e.toString());
                        if (mListview != null)
                            mListview.onRefreshComplete();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG, response);
                        if (mListview != null)
                            mListview.onRefreshComplete();
                        NewPriceBean data = JsonUtil.parseJsonToBean(response, NewPriceBean.class);
                        if (data == null || data.getContent().size() == 0 || !data.getPage().isHasNextPage()) {
                            return;
                        }
                        if (isNew) {
                            mList.clear();
                        }
                        mList.addAll(data.getContent());
                        if (mAdapter == null) {
                            mAdapter = new LatestPriceAdapter(LatestPriceActivity.this, mList);
                            mListview.setAdapter(mAdapter);
                        }
                        mAdapter.notifyDataSetChanged();
                    }
                });
    }
}
