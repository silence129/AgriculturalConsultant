package cn.org.nercita.agriculturalconsultant.main.me.fragment;

import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.GetBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import cn.org.nercita.agriculturalconsultant.AppConfig;
import cn.org.nercita.agriculturalconsultant.Constants;
import cn.org.nercita.agriculturalconsultant.R;
import cn.org.nercita.agriculturalconsultant.base.BaseFragment;
import cn.org.nercita.agriculturalconsultant.main.bean.ExpertQuestionBean;
import cn.org.nercita.agriculturalconsultant.main.me.adapter.ExpertQuestionAdapter;
import cn.org.nercita.agriculturalconsultant.utils.JsonUtil;
import cn.org.nercita.agriculturalconsultant.utils.SPUtil;
import okhttp3.Call;

/**
 * Created by 范博文 on 2017/4/11.
 * 专家问答
 */

public class ExpertQuestionFragment extends BaseFragment {
    private static final String TAG = ExpertQuestionFragment.class.getSimpleName();
    @Bind(R.id.lv_list)
    PullToRefreshListView lvList;
    @Bind(R.id.empty_view)
    TextView emptyView;
    private ExpertQuestionAdapter adapter;
    private String role;
    private String url = "";
    private String id;
    private int pageNo = 1;
    private List<ExpertQuestionBean.ContentBean> allList = new ArrayList<>();
    private List<ExpertQuestionBean.ContentBean> fresh = new ArrayList<>();
    private boolean pullDown = false;
    private String accountid;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_questionlist;
    }

    @Override
    protected void initData() {
        super.initData();
        role = SPUtil.getString(getContext(), AppConfig.ROLS, "");
        id = SPUtil.getString(getContext(), AppConfig.ID, "");
        accountid = SPUtil.getInt(getContext(), AppConfig.ACCOUNT_ID, 1) + "";
        adapter = new ExpertQuestionAdapter(getContext());
        lvList.setAdapter(adapter);
        if (AppConfig.ROLEFARMER.equals(role)) {
            url = Constants.FARMERQUESLIST;
        } else {
            url = Constants.EXPERTQUESTIONLIST;
        }
        //获取问专家列表
        getQuestion();
        //设置监听事件
        setListener();
    }

    /**
     * author:范博文
     * date:2017/4/12 16:54
     * des:监听事件设置
     * param:null
     * return:null
     */
    private void setListener() {
        lvList.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageNo = 1;
                getQuestion();
                pullDown = true;

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageNo++;
                getQuestion();
                pullDown = false;
            }
        });
    }

    /**
     * author:范博文
     * date:2017/4/11 11:22
     * des:获取专家问题列表
     * param:null
     * return:null
     */
    private void getQuestion() {
        fresh.clear();
        GetBuilder getBuilder = OkHttpUtils.get();
        getBuilder.url(url);
        //专家账号需要传accountid 范博文 2017.4.17
        if (AppConfig.ROLEFARMER.equals(role)){
            getBuilder.addParams("accountId",id);
        }else {
            getBuilder.addParams("accountId",accountid);
        }
        getBuilder
                .addParams("currentPage", pageNo + "")
                .addParams("everyPage", 10 + "")
                .build()
                .execute(new StringCallback() {

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, e.getMessage() + "");
                        lvList.onRefreshComplete();
                        pageNo--;
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        lvList.onRefreshComplete();
                        Log.e(TAG, response);
                        lvList.setVisibility(View.VISIBLE);
                        ExpertQuestionBean expertQuestionBean = JsonUtil.parseJsonToBean
                                (response, ExpertQuestionBean.class);
                        if (expertQuestionBean == null) return;
                        List<ExpertQuestionBean.ContentBean> content = expertQuestionBean
                                .getContent();
                        if ((content == null || content.size() < 1) && pageNo == 1) {
//                            ToastUtil.showShort(getContext(), "暂无数据");
                            lvList.setVisibility(View.GONE);
                            return;
                        }
                        if (pullDown) {
                            allList.clear();
                        }
                        fresh = content;
                        allList.addAll(fresh);
                        adapter.getData(allList);
                    }
                });
    }
}
