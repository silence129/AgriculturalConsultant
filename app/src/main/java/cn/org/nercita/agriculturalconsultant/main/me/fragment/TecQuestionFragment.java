package cn.org.nercita.agriculturalconsultant.main.me.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import cn.org.nercita.agriculturalconsultant.AppConfig;
import cn.org.nercita.agriculturalconsultant.Constants;
import cn.org.nercita.agriculturalconsultant.R;
import cn.org.nercita.agriculturalconsultant.base.BaseFragment;
import cn.org.nercita.agriculturalconsultant.main.bean.TechQuestionListBean;
import cn.org.nercita.agriculturalconsultant.main.communicate.activity.TechQuestionContentActivity;
import cn.org.nercita.agriculturalconsultant.main.communicate.adapter.TechQuestionListAdapter;
import cn.org.nercita.agriculturalconsultant.utils.JsonUtil;
import cn.org.nercita.agriculturalconsultant.utils.SPUtil;
import okhttp3.Call;

/**
 * Created by 范博文 on 2017/4/11.
 * 农技问答
 */

public class TecQuestionFragment extends BaseFragment {
    private static final String TAG = TecQuestionFragment.class.getSimpleName();
    @Bind(R.id.lv_list)
    PullToRefreshListView lvList;
    @Bind(R.id.empty_view)
    TextView emptyView;
    private TechQuestionListAdapter questionListAdapter;
    private List<TechQuestionListBean.ListBean> alllist = new ArrayList<>();
    private int pageNo = 1;
    private boolean isPullDown = false;
    private String id;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_questionlist;
    }

    @Override
    protected void initData() {
        super.initData();
        id = SPUtil.getString(getContext(), AppConfig.ID, "");
        //获取问专家列表
        getQuestion();
        setListener();
    }
    /**
    * author:范博文
    * date:2017/4/14 14:26
    * des:设置点击事件
    * param:null
    * return:null
    */
    private void setListener() {
        lvList.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageNo = 1;
                getQuestion();
                isPullDown = true;
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageNo ++;
                getQuestion();
                isPullDown = false;
            }
        });
        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), TechQuestionContentActivity.class);
                intent.putExtra("questionId", alllist.get(i-1).getId()+"");
                startActivity(intent);
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
        OkHttpUtils.get()
                .url(Constants.BaseUrL + Constants.TECH_QUESTION_LIST)
                .addParams("LinkAccountId", id)
                .addParams("everyPage", "15")
                .addParams("currentPage",pageNo+"")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG,e.getMessage()+"");
                        lvList.onRefreshComplete();
                        pageNo--;
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG,response);
                        lvList.onRefreshComplete();
                        lvList.setVisibility(View.VISIBLE);
                        TechQuestionListBean expertQuestionBean = JsonUtil.parseJsonToBean
                                (response, TechQuestionListBean.class);
                        if (expertQuestionBean==null){
                            pageNo--;
                            return;
                        }
                        List<TechQuestionListBean.ListBean> content = expertQuestionBean.getList();
                        if ((content==null||content.size()<1)&&pageNo==1){
                            pageNo--;
//                            ToastUtil.showShort(getContext(),"暂无数据");
                            lvList.setVisibility(View.GONE);
                            return;
                        }
                        if (isPullDown){
                            alllist.clear();
                        }
                        alllist.addAll(content);
                        if (questionListAdapter == null){
                            questionListAdapter = new TechQuestionListAdapter(getActivity(), alllist);
                            lvList.setAdapter(questionListAdapter);
                        }else {
                            questionListAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }


}
