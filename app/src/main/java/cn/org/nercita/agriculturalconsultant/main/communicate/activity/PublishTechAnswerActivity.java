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
import cn.org.nercita.agriculturalconsultant.utils.SPUtil;
import cn.org.nercita.agriculturalconsultant.utils.ToastUtil;
import cn.org.nercita.agriculturalconsultant.view.TitleBar;
import okhttp3.Call;

/**
 * Created by 梁兴胜 on 2017/4/12.
 * 发表农技答案
 */

public class PublishTechAnswerActivity extends BaseActivity {

    private static final String TAG = "PublishTechAnswerActivity";
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
    //accountId   //accountid 应改为id  范博文 2017.4.17
    private String id;
    //NICKNAME
    private String nickName;

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

        questionId = getIntent().getStringExtra("questionId");
        mSvProgressHUD = new SVProgressHUD(this);
        initListener();
        //accountid 应改为id  范博文 2017.4.17
        id = SPUtil.getString(this, AppConfig.ID, "");
        nickName = SPUtil.getString(this, AppConfig.NICKNAME, "1");
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
            ToastUtil.showLong(PublishTechAnswerActivity.this, "请填写内容");
        }else if (TextUtils.isEmpty(questionId)){
            ToastUtil.showLong(PublishTechAnswerActivity.this, "请返回重试");
        }else {
            publishContent();
        }
    }

    /**
     * 发表内容
     * @author: liangxingsheng
     * @date: 2017/4/12 上午9:40
     */
    private void publishContent() {

        commit.setEnabled(false);
        if (mSvProgressHUD != null)
            mSvProgressHUD.showWithStatus("正在提交...");
        OkHttpUtils.post()
                .url(Constants.BaseUrL + Constants.TECH_QUESTION_ANSWER)
                .addParams("accountId", id)
                .addParams("accountName", nickName)
                .addParams("content", content)
                .addParams("questionId", questionId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        commit.setEnabled(true);
                        Log.e(TAG,e.getMessage()+"");
                        ToastUtil.showLong(PublishTechAnswerActivity.this, "回答失败");
                        if (mSvProgressHUD != null)
                            mSvProgressHUD.dismiss();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG,response);
                        commit.setEnabled(true);
                        if (mSvProgressHUD != null)
                            mSvProgressHUD.dismiss();
                        if (response.contains("200")){
                            setResult(TechQuestionContentActivity.PUBLISH_ANSWER_RESULT_CODE);
                            ToastUtil.showLong(PublishTechAnswerActivity.this, "回答成功");
                            finish();
                        }else {
                            ToastUtil.showLong(PublishTechAnswerActivity.this, "回答失败，请稍后重试1");
                        }

                    }
                });

    }
}
