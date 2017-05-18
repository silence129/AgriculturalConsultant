package cn.org.nercita.agriculturalconsultant.main.communicate.activity;

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
import cn.org.nercita.agriculturalconsultant.AppConfig;
import cn.org.nercita.agriculturalconsultant.Constants;
import cn.org.nercita.agriculturalconsultant.R;
import cn.org.nercita.agriculturalconsultant.base.BaseActivity;
import cn.org.nercita.agriculturalconsultant.main.bean.ExpertQuestionListBean;
import cn.org.nercita.agriculturalconsultant.main.communicate.adapter.ExpertQuestionListAdapter;
import cn.org.nercita.agriculturalconsultant.utils.JsonUtil;
import cn.org.nercita.agriculturalconsultant.utils.SPUtil;
import cn.org.nercita.agriculturalconsultant.view.TitleBar;
import okhttp3.Call;

/**
 * Created by 梁兴胜 on 2017/4/12.
 * 专家问答问题列表 专家角色
 */

public class ExpertQuestionListActivity extends BaseActivity {

    public static final String TAG = "ExpertQuestionListAc";
    //标题
    @Bind(R.id.title_)
    TitleBar mTitle;
    //问题列表
    @Bind(R.id.lv_expert_question)
    PullToRefreshListView lvExpertQuestion;
    //问题列表adapter 梁兴胜
    private ExpertQuestionListAdapter questionListAdapter;
    //问题列表数据
    private List<ExpertQuestionListBean.ContentBean> questionList = new ArrayList();
    //页数
    private int PageNo = 1;
    //accountId
    private String accountId;
    //用户id
    private String id;

    @Override
    protected int getContentView() {
        return R.layout.activity_expert_question_list;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        init();
    }

    /**
     * 初始化
     * @author: liangxingsheng
     * @date: 2017/4/12 上午9:41
     */
    private void init(){
        id = SPUtil.getString(this, AppConfig.ID, "1");
        accountId = SPUtil.getInt(this, AppConfig.ACCOUNT_ID, 1)+"";
        getQuestionData(true);
        initListener();
    }

    /**
     * 点击事件
     * @author: liangxingsheng
     * @date: 2017/4/12 上午10:35
     */
    private void initListener(){

        mTitle.setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mTitle.setCommitListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ExpertQuestionListActivity.this, ExpertQuestionAskActivity.class);
                startActivity(intent);
            }
        });
        lvExpertQuestion.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(ExpertQuestionListActivity.this, ExpertQuestionContentActivity.class);
                intent.putExtra("questionId",questionList.get(i-1).getId()+"");
                startActivity(intent);
            }
        });
        lvExpertQuestion.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                PageNo = 1;
                getQuestionData(true);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                PageNo ++;
                getQuestionData(false);
            }
        });
    }

    /**
     * 专家问答数据
     * @author: liangxingsheng
     * @date: 2017/4/12 上午10:35
     */
    private void getQuestionData(final boolean isNew){

        OkHttpUtils.post()
                .url(Constants.BaseUrL + Constants.EXPERT_QUESTION_LIST)
                .addParams("accountId", accountId)
                .addParams("everyPage", "15")
                .addParams("currentPage", PageNo+"")
                .build()
                .execute(new StringCallback() {

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        PageNo --;
                        if (lvExpertQuestion != null)
                            lvExpertQuestion.onRefreshComplete();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG, response);
                        if (lvExpertQuestion != null)
                            lvExpertQuestion.onRefreshComplete();
                        ExpertQuestionListBean data = JsonUtil.parseJsonToBean(response, ExpertQuestionListBean.class);
                        if (data == null || data.getContent().size() == 0){
                            return;
                        }
                        if (isNew){
                            questionList.clear();
                        }
                        questionList.addAll(data.getContent());
                        if (questionListAdapter == null){
                            questionListAdapter = new ExpertQuestionListAdapter(ExpertQuestionListActivity.this, questionList);
                            lvExpertQuestion.setAdapter(questionListAdapter);
                        }
                        questionListAdapter.notifyDataSetChanged();
                    }
                });
    }
}
