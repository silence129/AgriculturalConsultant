package cn.org.nercita.agriculturalconsultant.main.service.activity;

import android.content.Intent;
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
import cn.org.nercita.agriculturalconsultant.common.WebViewActivity;
import cn.org.nercita.agriculturalconsultant.main.bean.MarkNewsListBean;
import cn.org.nercita.agriculturalconsultant.main.service.adapter.AgriculturalNewsAdapter;
import cn.org.nercita.agriculturalconsultant.utils.JsonUtil;
import cn.org.nercita.agriculturalconsultant.view.TitleBar;
import okhttp3.Call;



/**
 * Created by 梁兴胜 on 2017/4/14.
 * 农业资讯更多表列
 */

public class MarkNewsListActivity extends BaseActivity {
    private static final String TAG = "MarkNewsListActivity";

    //标题
    @Bind(R.id.title)
    TitleBar mTitle;
    //列表
    @Bind(R.id.m_listview)
    PullToRefreshListView mListview;
    //数据
    private List<MarkNewsListBean.ContentBean> mList = new ArrayList<>();
    //Adapter适配器
    private AgriculturalNewsAdapter mAdapter;
    //页数
    private int PageNo = 1;


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
     *
     * @author: liangxingsheng
     * @date: 2017/4/14 下午3:07
     */
    private void init() {

        mTitle.setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mTitle.setTitle("农业资讯");
        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MarkNewsListActivity.this, WebViewActivity.class);
                String url = Constants.BaseUrL + Constants.SERVICE_NEWS_DETAIL + "?id=" + mList.get(i-1).getId();
                intent.putExtra("url",url);
                startActivity(intent);
            }
        });
        mListview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                PageNo = 1;
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
     * @date: 2017/4/14 下午3:07
     */
    private void getData(final boolean isNew) {

        OkHttpUtils.post()
                .url(Constants.BaseUrL + Constants.MARK_NEWS_LIST)
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
                        MarkNewsListBean data = JsonUtil.parseJsonToBean(response, MarkNewsListBean.class);
                        if (data == null || data.getContent().size() == 0 || !data.getPage().isHasNextPage()) {
                            return;
                        }
                        if (isNew) {
                            mList.clear();
                        }
                        mList.addAll(data.getContent());
                        if (mAdapter == null) {
                            mAdapter = new AgriculturalNewsAdapter(MarkNewsListActivity.this, mList);
                            mListview.setAdapter(mAdapter);
                        }
                        mAdapter.notifyDataSetChanged();
                    }
                });
    }
}
