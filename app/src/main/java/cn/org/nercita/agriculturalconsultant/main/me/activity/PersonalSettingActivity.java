package cn.org.nercita.agriculturalconsultant.main.me.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.OnClick;
import cn.org.nercita.agriculturalconsultant.AppConfig;
import cn.org.nercita.agriculturalconsultant.R;
import cn.org.nercita.agriculturalconsultant.base.BaseActivity;
import cn.org.nercita.agriculturalconsultant.main.LoginActivity;
import cn.org.nercita.agriculturalconsultant.utils.SPUtil;
import cn.org.nercita.agriculturalconsultant.utils.ToastUtil;
import cn.org.nercita.agriculturalconsultant.view.TitleBar;

/**
 * Created by 范博文 on 2017/4/12.
 * 个人中心设置
 */

public class PersonalSettingActivity extends BaseActivity {
    @Bind(R.id.title)
    TitleBar title;
    @Bind(R.id.btn_logout)
    Button btnLogout;

    @Override
    protected int getContentView() {
        return R.layout.activity_personal_setting;
    }

    @Override
    protected void initData() {
        super.initData();
        title.setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @OnClick({R.id.btn_logout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_logout:
                //退出登录
                logOut();
                break;
        }
    }

    /**
     * author:范博文
     * date:2017/4/12 15:23
     * des:退出登录
     * param:null
     * return:null
     */
    private void logOut() {
        startActivity(new Intent(PersonalSettingActivity.this, LoginActivity.class));
        SPUtil.putString(PersonalSettingActivity.this, AppConfig.ID,"");
        SPUtil.putString(PersonalSettingActivity.this, AppConfig.NICKNAME,"");
        SPUtil.putString(PersonalSettingActivity.this, AppConfig.ROLS,"");
        SPUtil.putInt(PersonalSettingActivity.this,AppConfig.ACCOUNT_ID,0);
        ToastUtil.showShort(PersonalSettingActivity.this,"退出成功");
        finish();

    }
}
