package cn.org.nercita.agriculturalconsultant.main.me;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.OnClick;
import cn.org.nercita.agriculturalconsultant.AppConfig;
import cn.org.nercita.agriculturalconsultant.Constants;
import cn.org.nercita.agriculturalconsultant.R;
import cn.org.nercita.agriculturalconsultant.base.BaseFragment;
import cn.org.nercita.agriculturalconsultant.main.me.activity.FarmManageActivity;
import cn.org.nercita.agriculturalconsultant.main.me.activity.MyQuestionActivity;
import cn.org.nercita.agriculturalconsultant.main.me.activity.PersonalSettingActivity;
import cn.org.nercita.agriculturalconsultant.utils.ImageUtil;
import cn.org.nercita.agriculturalconsultant.utils.SPUtil;
import cn.org.nercita.agriculturalconsultant.view.CircleImageView;

/**
 * Created by 范博文 on 2017/4/10.
 * 我的
 */

public class MeFragment extends BaseFragment {


    @Bind(R.id.personal_icon)
    CircleImageView personalIcon;
    @Bind(R.id.personal_name)
    TextView personalName;
    @Bind(R.id.myquestion)
    TextView myquestion;
    @Bind(R.id.message)
    TextView message;
    @Bind(R.id.mysave)
    TextView mysave;
    @Bind(R.id.farmmanage)
    TextView farmmanage;
    @Bind(R.id.checkupdate)
    TextView checkupdate;
    @Bind(R.id.feedback)
    TextView feedback;
    @Bind(R.id.setting)
    TextView setting;
    @Bind(R.id.aboutus)
    TextView aboutus;
    private AlertDialog.Builder inputDialog;
    private String nickname;
    private String account_icon;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initData() {
        super.initData();
        nickname = SPUtil.getString(getContext(), AppConfig.NICKNAME, "");
        account_icon = SPUtil.getString(getContext(), AppConfig.ACCOUNT_ICON, "");
        ImageUtil.load(getContext(),personalIcon, Constants.PIC_PATH1+"uploads/"+account_icon);
        personalName.setText(nickname);
    }

    @OnClick({R.id.myquestion, R.id.message, R.id.mysave, R.id.farmmanage, R.id.feedback,R.id.setting})
    public void onClick(View view) {
        switch (view.getId()) {
            //我的问答
            case R.id.myquestion:
                startActivity(new Intent(getActivity(), MyQuestionActivity.class));
                break;
            case R.id.message:
                break;
            case R.id.mysave:
                break;
            //农事管理
            case R.id.farmmanage:
                startActivity(new Intent(getActivity(), FarmManageActivity.class));
                break;
            case R.id.feedback:
                break;
            //设置
            case R.id.setting:
                startActivity(new Intent(getActivity(),PersonalSettingActivity.class));
                break;
        }
    }
}
