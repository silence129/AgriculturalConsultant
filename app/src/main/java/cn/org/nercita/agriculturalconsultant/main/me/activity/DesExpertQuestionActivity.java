package cn.org.nercita.agriculturalconsultant.main.me.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import cn.org.nercita.agriculturalconsultant.AppConfig;
import cn.org.nercita.agriculturalconsultant.Constants;
import cn.org.nercita.agriculturalconsultant.R;
import cn.org.nercita.agriculturalconsultant.base.BaseActivity;
import cn.org.nercita.agriculturalconsultant.common.GridImageAdapter;
import cn.org.nercita.agriculturalconsultant.main.bean.ExpertAnswerDesBen;
import cn.org.nercita.agriculturalconsultant.main.me.adapter.ExpertAnswerDesAdapter;
import cn.org.nercita.agriculturalconsultant.utils.DialogHelper;
import cn.org.nercita.agriculturalconsultant.utils.JsonUtil;
import cn.org.nercita.agriculturalconsultant.utils.SPUtil;
import cn.org.nercita.agriculturalconsultant.utils.ToastUtil;
import cn.org.nercita.agriculturalconsultant.view.MeasuredGridView;
import cn.org.nercita.agriculturalconsultant.view.MeasuredListView;
import cn.org.nercita.agriculturalconsultant.view.TitleBar;
import okhttp3.Call;

import static cn.org.nercita.agriculturalconsultant.main.me.adapter.ExpertQuestionAdapter.QUESTIONID;

/**
 * Created by fan on 2017/4/11.
 * 专家提问详情
 */

public class DesExpertQuestionActivity extends BaseActivity {
    private static final String TAG = DesExpertQuestionActivity.class.getSimpleName();
    @Bind(R.id.question_name)
    TextView questionName;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.gridView_tech_question)
    MeasuredGridView gridimage;
    @Bind(R.id._question_content)
    TextView QuestionContent;
    @Bind(R.id.question_date)
    TextView questionDate;
    @Bind(R.id.noanswer)
    TextView noAnswer;
    @Bind(R.id.tabtitle)
    TitleBar tabtitle;
    @Bind(R.id.updateanswer)
    ImageView upDateAnswer;
    @Bind(R.id.expertanswerlv)
    MeasuredListView expertanswerlv;
    private ExpertAnswerDesAdapter answerDesAdapter;
    private AlertDialog.Builder inputDialog;
    private String questionId;
    private String id;
    private String rols;
    private DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private SVProgressHUD svProgressHUD ;

    @Override
    protected int getContentView() {
        return R.layout.activity_desexpertquestion;
    }

    @Override
    protected void initData() {
        super.initData();
        id = SPUtil.getString(DesExpertQuestionActivity.this, AppConfig.ID, "");
        rols = SPUtil.getString(DesExpertQuestionActivity.this, AppConfig.ROLS, "");
        answerDesAdapter = new ExpertAnswerDesAdapter(DesExpertQuestionActivity.this);
        svProgressHUD = new SVProgressHUD(DesExpertQuestionActivity.this);
        expertanswerlv.setAdapter(answerDesAdapter);
        Intent intent = getIntent();
        //得到questionid
        questionId = intent.getStringExtra(QUESTIONID);
        getQuestionAndAnswer(questionId);
        //回答问题设置点击事件出现回答对话框
        upDateAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (AppConfig.ROLEFARMER.equals(rols)) {
                    ToastUtil.showShort(DesExpertQuestionActivity.this, "您无回答权限");
                } else {
                    creatDialog();
                }
            }


        });
        tabtitle.setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


    }

    /**
     * author:范博文
     * date:2017/4/11 16:44
     * des:专家根据问题回答
     * param:null
     * return:null
     */
    private void updateAnswer(String answer) {
        if (TextUtils.isEmpty(answer)) {
            ToastUtil.showShort(DesExpertQuestionActivity.this, "回答不能为空");
            return;
        }
        OkHttpUtils.post().url(Constants.ANSWERQUESTION)
                .addParams("accountId", id)
                .addParams("questionId", questionId)
                .addParams("content", answer)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, e.getMessage() + "");
                        ToastUtil.showShort(DesExpertQuestionActivity.this, "网络连接错误");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG, response);
                        if (response.contains("success")) {
                            ToastUtil.showShort(DesExpertQuestionActivity.this, "回复成功");
                            getQuestionAndAnswer(questionId);
                        } else {
                            ToastUtil.showShort(DesExpertQuestionActivity.this, "回复失败，稍后再试");
                        }
                        svProgressHUD.dismiss();
                    }
                });
    }

    /**
     * author:范博文
     * date:2017/4/11 14:50
     * des:根据问题id获取回答详情
     *
     * @param questionId 问题id
     *                   return:null
     */
    private void getQuestionAndAnswer(String questionId) {
        OkHttpUtils.post()
                .url(Constants.QUESTIONDES)
                .addParams("questionId", questionId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, e.getMessage() + "");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG, response);
                        ExpertAnswerDesBen expertAnswerDesBen = JsonUtil.parseJsonToBean
                                (response, ExpertAnswerDesBen.class);
                        //设置问题
                        setQuestion(expertAnswerDesBen.getQuestion());
                        answerDesAdapter.setData(expertAnswerDesBen.getAnswers());
                        if (expertAnswerDesBen.getAnswers() == null || expertAnswerDesBen
                                .getAnswers().size() < 1) {
                            noAnswer.setVisibility(View.VISIBLE);
                            return;
                        }

                        noAnswer.setVisibility(View.GONE);

                    }
                });
    }

    /**
     * author:范博文
     * date:2017/4/11 15:15
     * des: 根据questionbean 设置问题
     *
     * @param question questionbean
     *                 return:null
     */
    private void setQuestion(ExpertAnswerDesBen.QuestionBean question) {
        questionName.setText(question.getAccountname());
        title.setText(question.getTitle());
        QuestionContent.setText(question.getContent());
        long l = Long.parseLong(question.getCreatedate());
        questionDate.setText(format.format(l));
        if (!TextUtils.isEmpty(question.getImg())) {
            String[] pics = question.getImg().split(",");
            List picsList = Arrays.asList(pics);
            gridimage.setAdapter(new GridImageAdapter(DesExpertQuestionActivity.this, picsList));
        } else {
            gridimage.setVisibility(View.GONE);
        }

    }

    /**
     * author:范博文
     * date:2017/4/12 9:08
     * des:创建对话框
     * param:null
     * return:null
     */
    private void creatDialog() {
        final AppCompatEditText editText = new AppCompatEditText(DesExpertQuestionActivity.this);
        inputDialog = DialogHelper.getInputDialog
                (DesExpertQuestionActivity.this, "输入回答问题", editText, "确定", "取消", false,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //上传回答
                                svProgressHUD.showWithStatus("上传中");
                                updateAnswer(editText.getText().toString());

                            }
                        }, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
        inputDialog.show();
    }
}
