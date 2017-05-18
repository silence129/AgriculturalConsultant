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
import cn.org.nercita.agriculturalconsultant.main.bean.TechQuestionDetailBean;
import cn.org.nercita.agriculturalconsultant.main.communicate.adapter.TechQuestionAnswerAdapter;
import cn.org.nercita.agriculturalconsultant.utils.JsonUtil;
import cn.org.nercita.agriculturalconsultant.view.MeasuredGridView;
import cn.org.nercita.agriculturalconsultant.view.MeasuredListView;
import cn.org.nercita.agriculturalconsultant.view.TitleBar;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;

/**
 * Created by 梁兴胜 on 2017/4/11.
 * 农技问答详情
 */

public class TechQuestionContentActivity extends BaseActivity {
    //请求码
    public static final int PUBLISH_ANSWER_QEQUEST_CODE = 1001;
    public static final int PUBLISH_ANSWER_RESULT_CODE = 1002;
    private static final String TAG = "TechQuestionContentAc";
    //标题
    @Bind(R.id.title_question)
    TitleBar mTitle;
    //提问人头像
    @Bind(R.id.avatar_asker)
    CircleImageView avatarAsker;
    //提问人
    @Bind(R.id.name_asker)
    TextView nameAsker;
    //提问人地址
    @Bind(R.id.address_asker)
    TextView addressAsker;
    //问题内容
    @Bind(R.id.content_question_tech)
    TextView contentQuestionTech;
    //问题图片
    @Bind(R.id.gridView_tech_question)
    MeasuredGridView gridViewTechQuestion;
    ////提问时间
    @Bind(R.id.time_question_tech)
    TextView timeQuestionTech;
    //回答数量
    @Bind(R.id.answer_num)
    ImageView answerNum;
    //回答数量
    @Bind(R.id.count_answer)
    TextView countAnswer;
    //排序图标
    @Bind(R.id.image_answer_order)
    ImageView imageAnswerOrder;
    //回答
    private TextView commit;
    //排序
    @Bind(R.id.tv_image_answer_order)
    TextView tvImageAnswerOrder;
    //答案列表
    @Bind(R.id.answer_list_tech)
    MeasuredListView answerListTech;
    //回答adapter
    private TechQuestionAnswerAdapter answerAdapter;
    //答案数据
    private List<TechQuestionDetailBean.TechReponselistBean> answerList = new ArrayList();
    //是否降序
    private boolean isDesc = false;
    //当前问题id
    private String questionId;
    //时间转换
    private DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override

    protected int getContentView() {
        return R.layout.activity_techquestion_content;
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
        getTechQuestionDetail(true);
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
        commit = mTitle.getCommit();
        mTitle.setCommitListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(questionId)) return;
                Intent intent = new Intent(TechQuestionContentActivity.this, PublishTechAnswerActivity.class);
                intent.putExtra("questionId", questionId);
                startActivityForResult(intent, PUBLISH_ANSWER_QEQUEST_CODE);
            }
        });
    }

    /**
     * 农技问答详情数据
     * @author: liangxingsheng
     * @date: 2017/4/12 下午3:23
     * @param isNew 是否为刷新
     */
    private void getTechQuestionDetail(final boolean isNew){

        OkHttpUtils.get()
                .url(Constants.BaseUrL + Constants.TECH_QUESTION_DETAIL)
                .addParams("id", questionId)
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

        TechQuestionDetailBean data = JsonUtil.parseJsonToBean(response, TechQuestionDetailBean.class);
        if (data == null){
            return;
        }
        //问题
        nameAsker.setText(data.getQuestion().getAccountName());
        contentQuestionTech.setText(data.getQuestion().getTitle());
        timeQuestionTech.setText(format.format(data.getQuestion().getCreateTime()));
        countAnswer.setText(data.getTechReponselist().size()+"条回答");
        if (!TextUtils.isEmpty(data.getQuestion().getPics())){
            String[] pics = data.getQuestion().getPics().split(",");
            List picsList = Arrays.asList(pics);
            gridViewTechQuestion.setAdapter(new GridImageAdapter(this, picsList));
        }
        //答案
        if (isNew){
            answerList.clear();
        }
        answerList.addAll(data.getTechReponselist());
        if (answerAdapter == null){
            answerAdapter = new TechQuestionAnswerAdapter(TechQuestionContentActivity.this, answerList);
            answerListTech.setAdapter(answerAdapter);
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
                    getTechQuestionDetail(true);
                    break;
            }
        }
    }

    @OnClick({R.id.image_answer_order, R.id.tv_image_answer_order})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_answer_order:
            case R.id.tv_image_answer_order:
                //默认false,为升序
                isDesc = !isDesc;
                if (isDesc){
                    //降序
                    imageAnswerOrder.setImageResource(R.drawable.order1);
                }
                else {
                    //升序
                    imageAnswerOrder.setImageResource(R.drawable.order);
                }

                break;
        }
    }
}
