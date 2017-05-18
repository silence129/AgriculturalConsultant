package cn.org.nercita.agriculturalconsultant.main.communicate.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import cn.org.nercita.agriculturalconsultant.Constants;
import cn.org.nercita.agriculturalconsultant.R;
import cn.org.nercita.agriculturalconsultant.base.BaseActivity;
import cn.org.nercita.agriculturalconsultant.common.GridImageAdapter;
import cn.org.nercita.agriculturalconsultant.main.bean.ExpertQuestionDetailBean;
import cn.org.nercita.agriculturalconsultant.main.communicate.adapter.ExpertQuestionAnswerAdapter;
import cn.org.nercita.agriculturalconsultant.utils.JsonUtil;
import cn.org.nercita.agriculturalconsultant.view.MeasuredGridView;
import cn.org.nercita.agriculturalconsultant.view.MeasuredListView;
import cn.org.nercita.agriculturalconsultant.view.TitleBar;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;

/**
 * Created by 梁兴胜 on 2017/4/12.
 * 专家问答问题详情
 */

public class ExpertQuestionContentActivity extends BaseActivity {
    //请求码
    public static final int PUBLISH_ANSWER_QEQUEST_CODE = 1003;
    public static final int PUBLISH_ANSWER_RESULT_CODE = 1004;
    private static final String TAG = "ExpertQuestionContentAc";
    //标题
    @Bind(R.id.title_question)
    TitleBar mTitle;
    //提问人头像
    @Bind(R.id.avatar_asker)
    CircleImageView avatarAsker;
    //提问人
    @Bind(R.id.name_asker)
    TextView nameAsker;
    //提问地址
    @Bind(R.id.address_asker)
    TextView addressAsker;
    //问题内容
    @Bind(R.id.content_question)
    TextView contentQuestion;
    //问题图片
    @Bind(R.id.gridView_question)
    MeasuredGridView gridViewQuestion;
    //提问时间
    @Bind(R.id.time_question)
    TextView timeQuestion;
    //回答
    @Bind(R.id.image_answer_order)
    ImageView imageAnswer;
    //答案列表
    @Bind(R.id.answer_list_expert)
    MeasuredListView answerListExpert;
    //回答adapter
    private ExpertQuestionAnswerAdapter answerAdapter;
    //答案数据
    private List<ExpertQuestionDetailBean.AnswersBean> answerList = new ArrayList<>();
    //当前问题id
    private String questionId;
    private DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    protected int getContentView() {
        return R.layout.activity_content_expert_question;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        init();
    }

    /**
     * 初始化
     * @author: liangxingsheng
     * @date: 2017/4/11 上午10:35
     */
    private void init(){

        questionId = getIntent().getStringExtra("questionId");
        initListener();
        getExpertQuestionDetail(true);
    }

    /**
     * 点击事件
     * @author: liangxingsheng
     * @date: 2017/4/11 上午10:35
     */
    private void initListener(){

        mTitle.setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    /**
     * 专家问答详情数据
     * @author: liangxingsheng
     * @date: 2017/4/12 下午3:23
     * @param isNew 是否为刷新
     */
    private void getExpertQuestionDetail(final boolean isNew){

        OkHttpUtils.get()
                .url(Constants.BaseUrL + Constants.EXPERT_QUESTION_DETAIL)
                .addParams("questionId", questionId)
//                .addParams("everyPage", "15")
//                .addParams("currentPage", "")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG, response);
                        setDetailData(response, isNew);
                    }
                });
    }

    /**
     * 处理问题详情数据
     * @author: liangxingsheng
     * @date: 2017/4/12 下午3:35
     */
    private void setDetailData(String response, boolean isNew){

        ExpertQuestionDetailBean data = JsonUtil.parseJsonToBean(response, ExpertQuestionDetailBean.class);
        if (data == null){
            return;
        }
        //问题
        nameAsker.setText(data.getQuestion().getAccountname());
        contentQuestion.setText(data.getQuestion().getContent());
        if (!TextUtils.isEmpty(data.getQuestion().getCreatedate())){
            long l = Long.parseLong(data.getQuestion().getCreatedate());
            timeQuestion.setText(format.format(l));
        }

        if (!TextUtils.isEmpty(data.getQuestion().getImg())){
            String[] pics = data.getQuestion().getImg().split(",");
            List picsList = Arrays.asList(pics);
            gridViewQuestion.setAdapter(new GridImageAdapter(this, picsList));
        }
        //答案
        if (isNew){
            answerList.clear();
        }
        answerList.addAll(data.getAnswers());
        if (answerAdapter == null){
            answerAdapter = new ExpertQuestionAnswerAdapter(ExpertQuestionContentActivity.this, answerList);
            answerListExpert.setAdapter(answerAdapter);
        }
        answerAdapter.notifyDataSetChanged();
    }

    /**
     * 接收发布答案结果
     * @author: liangxingsheng
     * @date: 2017/4/12 上午10:35
     * @param requestCode 请求码
     * @param resultCode 结果码
     * @param data 数据
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PUBLISH_ANSWER_QEQUEST_CODE){
            switch (resultCode){
                case PUBLISH_ANSWER_RESULT_CODE:
                    //重新获取数据刷新
                    getExpertQuestionDetail(true);
                    break;
            }
        }
    }

    @OnClick(R.id.image_answer_order)
    public void onViewClicked() {
        // TODO: 2017/4/12  判断账户类型 回答还是追加 或者 是否显示回答按钮
        Intent intent = new Intent(ExpertQuestionContentActivity.this, PublishExpertAnswerActivity.class);
        intent.putExtra("questionId", questionId);
        startActivityForResult(intent, PUBLISH_ANSWER_QEQUEST_CODE);
    }
}
