package cn.org.nercita.agriculturalconsultant.main.communicate.activity;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.Bind;
import cn.org.nercita.agriculturalconsultant.AppConfig;
import cn.org.nercita.agriculturalconsultant.Constants;
import cn.org.nercita.agriculturalconsultant.R;
import cn.org.nercita.agriculturalconsultant.base.BaseActivity;
import cn.org.nercita.agriculturalconsultant.main.bean.ResponseBean2;
import cn.org.nercita.agriculturalconsultant.utils.JsonUtil;
import cn.org.nercita.agriculturalconsultant.utils.SPUtil;
import cn.org.nercita.agriculturalconsultant.utils.ToastUtil;
import cn.org.nercita.agriculturalconsultant.view.TitleBar;
import okhttp3.Call;

/**
 * Created by 梁兴胜 on 2017/4/12.
 * 专家问题回答界面
 */

public class PublishExpertAnswerActivity extends BaseActivity {

    private static final String TAG = "PublishExpertAnswerActivity";
    //标题
    @Bind(R.id.title_market_price)
    TitleBar mTitle;
    //输入框
    @Bind(R.id.content_to_publish)
    EditText contentToPublish;
    //确定键
    private TextView commit;
    //提醒
    private SVProgressHUD mSvProgressHUD;
    //待发表内容
    private String content;
    //所答问题id
    private String questionId;
    //用户id
    private String id;

    @Override
    protected int getContentView() {
        return R.layout.activity_publish_answer;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        init();
    }

    /**
     * 初始化
     * @author: liangxingsheng
     * @date: 2017/4/12 上午9:40
     */
    private void init(){

        id = SPUtil.getString(this, AppConfig.ID, "1");
        questionId = getIntent().getStringExtra("questionId");
        mSvProgressHUD = new SVProgressHUD(this);
        initListener();
    }

    /**
     * 点击事件
     * @author: liangxingsheng
     * @date: 2017/4/12 上午9:40
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
                getPublishContent();
            }
        });
    }

    /**
     * 获取要发表的内容
     * @author: liangxingsheng
     * @date: 2017/4/12 上午9:40
     */
    private void getPublishContent() {

        content = contentToPublish.getText().toString().trim();
        if (TextUtils.isEmpty(content)){
            ToastUtil.showLong(PublishExpertAnswerActivity.this, "请填写内容");
        }else {
            publishContent();
        }
    }


    /**
     * 发表内容
     * @author: liangxingsheng
     * @date: 2017/4/14 下午9:10
     */
    private void publishContent() {

        commit.setEnabled(false);
        if (mSvProgressHUD != null)
            mSvProgressHUD.showWithStatus("正在提交...");
        OkHttpUtils.post()
                .url(Constants.ANSWERQUESTION)
                .addParams("accountId", id)
                .addParams("questionId", questionId)
                .addParams("content", content)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG,e.getMessage()+"");
                        commit.setEnabled(true);
                        ToastUtil.showLong(PublishExpertAnswerActivity.this, "回答失败");
                        if (mSvProgressHUD != null)
                            mSvProgressHUD.dismiss();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG,response);
                        ResponseBean2 result = JsonUtil.parseJsonToBean(response, ResponseBean2.class);
                        commit.setEnabled(true);
                        if (mSvProgressHUD != null)
                            mSvProgressHUD.dismiss();
                        if (result.getState().equals("success")){
                            setResult(ExpertQuestionContentActivity.PUBLISH_ANSWER_RESULT_CODE);
                            ToastUtil.showLong(PublishExpertAnswerActivity.this, "回答成功");
                            finish();
                        }else {
                            ToastUtil.showLong(PublishExpertAnswerActivity.this, result.getStr());
                        }
                    }
                });
    }
}
