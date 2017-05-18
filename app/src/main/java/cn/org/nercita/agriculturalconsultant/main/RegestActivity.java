package cn.org.nercita.agriculturalconsultant.main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import cn.org.nercita.agriculturalconsultant.Constants;
import cn.org.nercita.agriculturalconsultant.R;
import cn.org.nercita.agriculturalconsultant.base.BaseActivity;
import cn.org.nercita.agriculturalconsultant.main.bean.RegestTypeBean;
import cn.org.nercita.agriculturalconsultant.utils.PhoneFormatCheckUtils;
import cn.org.nercita.agriculturalconsultant.utils.ToastUtil;
import okhttp3.Call;

/**
 * Created by 范博文 on 2017/4/10.
 * 注册界面
 */

public class RegestActivity extends BaseActivity {


    private static final String TAG = RegestActivity.class.getSimpleName();
    @Bind(R.id.regest_username)
    EditText regestUsername;
    @Bind(R.id.regest_nickname)
    EditText regestNickname;
    @Bind(R.id.regest_phone)
    EditText regestPhone;
    @Bind(R.id.regest_pssword)
    EditText regestPssword;
    @Bind(R.id.regest_config_password)
    EditText regestConfigPassword;
    @Bind(R.id.regest_type)
    TextView regestType;
    @Bind(R.id.regest_bt)
    Button regestBt;
    private String username;
    private String nickname;
    private String phonenumber;
    private String password;
    private SVProgressHUD progressHUD;
    private List<RegestTypeBean.TypeIdBean> typeId;
    private StringBuffer buffername;
    private StringBuffer bufferid;
    private String[] stringname = {"种植业","畜牧业","水产","其他"};
    private String[] stringid= {"1","2","3","4"};
    private String regestid;

    @Override
    protected int getContentView() {
        return R.layout.activity_regest;
    }

    @Override
    protected void initData() {
        super.initData();
        progressHUD = new SVProgressHUD(RegestActivity.this);
        //得到注册类型
//        getType();

    }

    /**
     * author:范博文
     * date:2017/4/14 14:38
     * des:得到注册类型
     * param:null
     * return:null
     */
    private void getType() {
        OkHttpUtils.get()
                .url("http://192.168.16.56:8080/bthscreen/visitor/typeId")
                .build()
                .buildCall(new StringCallback() {



                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, e.getMessage() + "");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG, response);
                      /*  RegestTypeBean regestTypeBean = JsonUtil.parseJsonToBean(response,
                                RegestTypeBean.class);
                        if (regestTypeBean == null) return;
                        typeId = regestTypeBean.getTypeId();
                        for (int i = 0; i < typeId.size(); i++) {
                            buffername = new StringBuffer();
                            bufferid = new StringBuffer();
                            bufferid.append(typeId.get(i).getTitle()+",");
                            buffername.append(typeId.get(i).getId()+",");
                        }
                        stringid = bufferid.toString().split(",");
                        stringname = buffername.toString().split(",");*/
                    }
                });
    }


    @OnClick({R.id.regest_bt, R.id.regest_type})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.regest_bt:
                //注册前检查
                checkregest();
                break;
            case R.id.regest_type:
                //选择类型对话框
                creatTypeDialog();
                break;
        }


    }

    /**
     * author:范博文
     * date:2017/4/14 14:50
     * des:选择类型对话框
     * param:null
     * return:null
     */
    private void creatTypeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(RegestActivity.this);
        builder.setTitle("请选择类型");
        builder.setItems(stringname, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
//                ToastUtil.show(RegestActivity.this,stringid[i]);
                regestType.setText(stringname[i]);
                regestid = stringid[i];

            }
        });
        builder.create().show();
    }

    /**
     * author:范博文
     * date:2017/4/12 10:26
     * des:提取信息注册
     * param:null
     * return:null
     */
    private void regest() {
        progressHUD.showWithStatus("请稍后");
        OkHttpUtils.post()
                .url(Constants.REGEST)
                .addParams("userName", username)
                .addParams("password", password)
                .addParams("phone", phonenumber)
                .addParams("bthTypeId", regestid)
                .addParams("nickName", nickname)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, e.getMessage() + "");
                        progressHUD.dismiss();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        progressHUD.dismiss();
                        Log.e(TAG, response);
                        if (response.contains("200")){
                            ToastUtil.showShort(RegestActivity.this, "注册成功");
                            finish();
                        }
                        if (response.contains("304")){
                            ToastUtil.showShort(RegestActivity.this, "手机号已被注册！");
                        }
                        if (response.contains("303")){
                            ToastUtil.showShort(RegestActivity.this, "用户已被注册！");
                        }

                    }
                });

    }

    /**
     * author:范博文
     * date:2017/4/12 10:25
     * des:检查注册信息是否完整
     * param:null
     * return:null
     */
    private void checkregest() {
        username = regestUsername.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            ToastUtil.showShort(RegestActivity.this, "用户名不能为空");
            return;
        }
        nickname = regestNickname.getText().toString().trim();
        if (TextUtils.isEmpty(nickname)) {
            ToastUtil.showShort(RegestActivity.this, "姓名不能为空");
            return;
        }
        phonenumber = regestPhone.getText().toString().trim();
        if (TextUtils.isEmpty(phonenumber)) {
            ToastUtil.showShort(RegestActivity.this, "手机号不能为空");
            return;

        } else {
            boolean phoneLegal = PhoneFormatCheckUtils.isPhoneLegal(phonenumber);
            if (!phoneLegal) {
                ToastUtil.showShort(RegestActivity.this, "请输入正确的手机号码");
                return;
            }
        }
        password = regestPssword.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            ToastUtil.showShort(RegestActivity.this, "密码不能为空");
            return;
        }
        String configPassword = regestConfigPassword.getText().toString().trim();
        if (TextUtils.isEmpty(configPassword)) {
            ToastUtil.showShort(RegestActivity.this, "确认密码不能为空");
            return;
        }
        if (!password.equals(configPassword)) {
            ToastUtil.showShort(RegestActivity.this, "两次密码不一致！重新确认后输入");
            return;
        }
        if (TextUtils.isEmpty(regestType.getText().toString().trim())){
            ToastUtil.showShort(RegestActivity.this, "请选择类型！");
            return;
        }
        //注册
        regest();

    }
}
