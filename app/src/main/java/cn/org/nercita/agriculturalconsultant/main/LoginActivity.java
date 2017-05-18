package cn.org.nercita.agriculturalconsultant.main;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.Bind;
import butterknife.OnClick;
import cn.org.nercita.agriculturalconsultant.AppConfig;
import cn.org.nercita.agriculturalconsultant.Constants;
import cn.org.nercita.agriculturalconsultant.MainActivity;
import cn.org.nercita.agriculturalconsultant.R;
import cn.org.nercita.agriculturalconsultant.base.BaseActivity;
import cn.org.nercita.agriculturalconsultant.main.bean.LogInfoBean;
import cn.org.nercita.agriculturalconsultant.utils.JsonUtil;
import cn.org.nercita.agriculturalconsultant.utils.SPUtil;
import cn.org.nercita.agriculturalconsultant.utils.ToastUtil;
import okhttp3.Call;

/**
 * Created by 范博文 on 2017/4/10.
 * 登录界面
 */

public class LoginActivity extends BaseActivity {
    private static final String TAG = LoginActivity.class.getSimpleName();
    @Bind(R.id.login_username)
    EditText loginUsername;
    @Bind(R.id.login_password)
    EditText loginPassword;
    @Bind(R.id.login_login)
    Button loginLogin;
    @Bind(R.id.login_forgotpassword)
    TextView loginForgotpassword;
    @Bind(R.id.login_regest)
    TextView loginRegest;
    private SVProgressHUD progressHUD;

    @Override
    protected int getContentView() {
        return R.layout.activity_login;
    }

    @OnClick({R.id.login_login, R.id.login_regest})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_login:
                //登录检查
                logincheck();
                break;
            case R.id.login_regest:
                startActivity(new Intent(LoginActivity.this, RegestActivity.class));
                break;
        }
    }

    @Override
    protected void initData() {
        super.initData();
        progressHUD = new SVProgressHUD(LoginActivity.this);
    }

    /**
     * author:范博文
     * date:2017/4/12 11:20
     * des:登录检查
     * param:null
     * return:null
     */
    private void logincheck() {
        String username = loginUsername.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            ToastUtil.showShort(LoginActivity.this, "用户名不能为空！");
            return;
        }
        String password = loginPassword.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            ToastUtil.showShort(LoginActivity.this, "密码不能为空！");
            return;
        }
        progressHUD.showWithStatus("请稍后");
        login(username, password);
    }

    /**
     * author:范博文
     * date:2017/4/12 11:25
     * des:登录操作
     *
     * @param username 用户名
     * @param password 密码
     *                 return:
     */
    private void login(String username, String password) {
        OkHttpUtils.post()
                .url(Constants.LOGIN)
                .addParams("userName", username)
                .addParams("password", password)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, e.getMessage() + "");
                        progressHUD.dismiss();
                        ToastUtil.showShort(LoginActivity.this, "网络连接错误");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG, response);
                        progressHUD.dismiss();
                        LogInfoBean logInfoBean = JsonUtil.parseJsonToBean(response, LogInfoBean
                                .class);
                        int state = logInfoBean.getState();
                        if (202 == state) {
                            ToastUtil.showShort(LoginActivity.this, "密码错误");
                            return;
                        }
                        if (203 == state) {
                            ToastUtil.showShort(LoginActivity.this, "账号被禁用");
                            return;
                        }
                        if (200 == state) {
                            //将信息存入sp
                            SPUtil.putString(LoginActivity.this, AppConfig.USERNAME, logInfoBean
                                    .getLoginUser().getUserName());//账号
                            SPUtil.putString(LoginActivity.this, AppConfig.PASSWORD, logInfoBean
                                    .getLoginUser().getPassword());//密码
                            SPUtil.putString(LoginActivity.this, AppConfig.NICKNAME, logInfoBean
                                    .getLoginUser().getNickName());//用户名);
                            SPUtil.putString(LoginActivity.this, AppConfig.ROLS, logInfoBean
                                    .getLoginUser().getRoles());//角色
                            SPUtil.putString(LoginActivity.this, AppConfig.ID, String.valueOf
                                    (logInfoBean.getLoginUser().getId()));//用户id
                            SPUtil.putInt(LoginActivity.this, AppConfig.ACCOUNT_ID, logInfoBean.getLoginUser().getAccountId());//用户ACCOUNT_ID
                            if (logInfoBean.getLoginUser().getPhoto()!=null){
                                SPUtil.putString(LoginActivity.this,AppConfig.ACCOUNT_ICON,logInfoBean.getLoginUser().getPhoto());
                            }
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        }


                    }
                });
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.slide_bottom_in, R.anim.slide_bottom_out);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
//        overridePendingTransition(R.anim.slide_bottom_in, R.anim.slide_bottom_out);
    }

}
