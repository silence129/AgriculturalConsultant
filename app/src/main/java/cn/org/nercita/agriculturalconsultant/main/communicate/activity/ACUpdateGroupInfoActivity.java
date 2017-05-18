package cn.org.nercita.agriculturalconsultant.main.communicate.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.org.nercita.agriculturalconsultant.R;
/**
 * 描述：更新群组信息
 * @author GaoWenXu
 * @date 2017/4/10 15:09
 * @version v1.0.0
 */
public class ACUpdateGroupInfoActivity extends AppCompatActivity {
    public static final int RESULT_CODE_UPDATE_GROUP_INFO = 1054;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acupdate_group_info);
    }
}
