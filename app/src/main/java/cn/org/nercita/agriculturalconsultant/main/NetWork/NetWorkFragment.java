package cn.org.nercita.agriculturalconsultant.main.NetWork;

import android.util.Log;
import android.view.View;
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
import cn.org.nercita.agriculturalconsultant.R;
import cn.org.nercita.agriculturalconsultant.base.BaseFragment;
import cn.org.nercita.agriculturalconsultant.main.NetWork.adapter.BaseListInfoAdapter;
import cn.org.nercita.agriculturalconsultant.main.bean.BaseListInfo;
import cn.org.nercita.agriculturalconsultant.utils.JsonUtil;
import cn.org.nercita.agriculturalconsultant.utils.ToastUtil;
import cn.org.nercita.agriculturalconsultant.view.TitleBar;
import okhttp3.Call;


/**
 * Created by 范博文 on 2017/4/17.
 * 物联网
 */

public class NetWorkFragment extends BaseFragment {
    private static final String TAG = NetWorkFragment.class.getSimpleName();
    @Bind(R.id.title)
    TitleBar title;
    @Bind(R.id.search)
    EditText search;
    @Bind(R.id.search_bt)
    TextView searchBt;
    @Bind(R.id.ll_search_section_manage)
    LinearLayout llSearchSectionManage;
    @Bind(R.id.square)
    TextView square;
    @Bind(R.id.leavl)
    TextView leavl;
    @Bind(R.id.type)
    TextView type;
    @Bind(R.id.baseinfo_lv)
    PullToRefreshListView lv;
    private BaseListInfoAdapter adapter;
    private int pageNo = 1;
    private boolean isPullDown = false;
    private List<BaseListInfo.DataBean> alllist = new ArrayList<>();
    private boolean searchbar = false;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_network;
    }

    @Override
    protected void initData() {
        super.initData();
        title.setSearch(View.VISIBLE);
        //设置点击事件
        setListener();
        //获取基地数据
        getData();
        adapter = new BaseListInfoAdapter(getContext());
        lv.setAdapter(adapter);
    }

    /**
     * author:范博文
     * date:2017/4/10 17:37
     * des: 控件的点击事件
     * param:null
     * return:null
     */
    private void setListener() {
        //点击title搜索按钮，显示搜索框
        title.setSearchLinstener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (searchbar) {
                    llSearchSectionManage.setVisibility(View.GONE);
                    searchbar = false;
                } else {
                    llSearchSectionManage.setVisibility(View.VISIBLE);
                    searchbar = true;
                }
            }
        });
        //上拉刷新及下拉加载
        lv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                isPullDown = true;
                pageNo = 1;
                getData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageNo++;
                isPullDown = false;
                lv.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        lv.onRefreshComplete();
                    }
                }, 1000);
//                getData();
            }
        });
    }

    /**
     * author:范博文
     * date:2017/4/17 16:27
     * des:获取基地信息
     * param:    null
     * return:null
     */
    private void getData() {
        //修改基地接口  2017.4.21 范博文
        OkHttpUtils.get()
                .url("http://123.127.160.21/display/easyapi/mobile/getGcStationList")
                .addParams("page", pageNo + "")
                .addParams("pageSize", 10 + "")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        lv.onRefreshComplete();
                        Log.e(TAG, e.getMessage() + "");
                        pageNo--;
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        lv.onRefreshComplete();
                        Log.e(TAG, response);
                        String substring = response.substring(1, response.length() - 1);
                        BaseListInfo baseListInfo = JsonUtil.parseJsonToBean(substring,
                                BaseListInfo.class);


                        if (baseListInfo.getData() == null || baseListInfo.getData().size() < 1) {
                            ToastUtil.showShort(getContext(), "没有更多数据了");
                            pageNo--;
                            return;
                        }
                        if (isPullDown) {
                            alllist.clear();
                        }
                        alllist.addAll(baseListInfo.getData());
                        adapter.getBeanList(alllist);


                    }
                });
    }


}
