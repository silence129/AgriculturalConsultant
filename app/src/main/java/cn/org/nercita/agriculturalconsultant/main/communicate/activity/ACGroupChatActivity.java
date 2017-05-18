package cn.org.nercita.agriculturalconsultant.main.communicate.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import cn.org.nercita.agriculturalconsultant.R;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.ui.EaseChatFragment;


import cn.org.nercita.agriculturalconsultant.main.communicate.fragment.AcGroupChatFragMent;

/**
 * 描述：群组聊天Activity
 * @author GaoWenXu
 * @date 2017/4/10 13:48
 * @version v1.0.0
 */
public class ACGroupChatActivity extends AppCompatActivity {
    private static final String TAG = ACGroupChatActivity.class.getSimpleName();
    public static final String GROUP_CHAT_ROOM_PAGE = "fromchatpage_to_detail";
    private EaseChatFragment chatFragment;
    public static final String GROUP_ID_KEY = "groupId";
    String groupId;
    private String groupName;
    private String forwardMsgId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acgroup_chat);
        groupId = getIntent().getStringExtra(GROUP_ID_KEY);
        groupName = getIntent().getStringExtra("groupName");
        forwardMsgId=getIntent().getStringExtra("forward_msg_id");
        Log.e(TAG,"groupId :  " +groupId);

//        ATGroupDataDetail mDataDetail = (ATGroupDataDetail) getIntent().getSerializableExtra(ATGroupInfoFragment.GROUP_INFO_DATA);
        //new出EaseChatFragment或其子类的实例
         chatFragment = new AcGroupChatFragMent();
        // chatFragment.setChatFragmentListener(this);
        Bundle args = new Bundle();
        args.putInt(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_GROUP);
        args.putString(EaseConstant.EXTRA_USER_ID, groupId);
        args.putString("toName",groupName);
        args.putString("forward_msg_id", forwardMsgId);
//        args.putString("");
        chatFragment.setArguments(args);
        getSupportFragmentManager().beginTransaction().add(R.id.container, chatFragment).commit();

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode==AcGroupChatFragMent.GROUPMANAGER){
//            if (resultCode==ATGroupDetailMsgActivity.RESULT_CODE_DELETE){
//                Toast.makeText(this,"退出成功",Toast.LENGTH_SHORT).show();
//                setResult(ATGroupInfoFragment.DELETE_GROUP);
//                finish();
//            }
//        }


    }
}
