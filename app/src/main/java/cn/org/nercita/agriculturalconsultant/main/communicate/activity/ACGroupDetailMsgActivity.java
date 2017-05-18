package cn.org.nercita.agriculturalconsultant.main.communicate.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.utils.EaseUserUtils;
import com.hyphenate.easeui.widget.EaseAlertDialog;
import com.hyphenate.easeui.widget.EaseExpandGridView;
import com.hyphenate.util.EMLog;
import com.hyphenate.util.NetUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.org.nercita.agriculturalconsultant.Constants;
import cn.org.nercita.agriculturalconsultant.R;
import cn.org.nercita.agriculturalconsultant.main.bean.ACGroupDataDetail;
import cn.org.nercita.agriculturalconsultant.main.bean.ACGroupMemberEntity;
import cn.org.nercita.agriculturalconsultant.main.communicate.CommunicateBaseFragment;
import cn.org.nercita.agriculturalconsultant.utils.JsonUtil;
import cn.org.nercita.agriculturalconsultant.utils.SPUtil;
import cn.org.nercita.agriculturalconsultant.view.ACAlertConfirmDialog;
import cn.org.nercita.agriculturalconsultant.view.TitleBar;

/**
 * 描述：群管理界面
 * @author GaoWenXu
 * @date 2017/4/10 14:14
 * @version v1.0.0
 */
public class ACGroupDetailMsgActivity extends AppCompatActivity implements View.OnClickListener {


    private static final int UPATE_INFO_REQUEST_CODE = 2001;
    private static final int REQUEST_CODE_ADD_USER = 0;
    private static final String TAG = ACGroupDetailMsgActivity.class.getSimpleName();
    private static final String CACHE_KEY_GROUP_DETAIL = "group_detail_";
    public static final String GROUP_ID_TO_MEMBER = "groupid";
    public static final int RESULT_CODE_DELETE = 2002;
    public static final String ADD_USER_TO_GROUP = "adduserforgroup";
    public static final String UPDATE_GROUP_NAME = "update_group_name";
    public static final String UPDATE_GROUP_TYPE = "update_group_type";
    public static final String UPDATE_GROUP_DES = "update_group_des";
    @Bind(R.id.tb_title)
    TitleBar mTitle;
    @Bind(R.id.tv_dis_group_name)
    TextView mGroupName;
    @Bind(R.id.tv_dis_des)
    TextView mGroupDes;
    @Bind(R.id.tv_group_members)
    TextView mTvGroupMemberSize;
    @Bind(R.id.tv_group_type)
    TextView mTvGroupType;
    @Bind(R.id.exg_gridview)
    EaseExpandGridView gridView;
    @Bind(R.id.tv_delete_group)
    TextView mTvDeleteGroup;
    @Bind(R.id.rl_group_name)
    RelativeLayout mUpdateGroupName;
    @Bind(R.id.rl_group_type)
    RelativeLayout mUpdateGroupType;
    @Bind(R.id.rl_group_des)
    RelativeLayout mUpdateGroupDes;



    private String mGroupID;
    private ACGroupDataDetail groupDataDetail;
    private GridAdapter adapter;
    private ACAlertConfirmDialog confirmDialog;
    private String startPageAction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acgroup_detail_msg);
        ButterKnife.bind(this);
    }



    private void init(Bundle savedInstanceState) {

        getDataFromIntent();
        mTitle.setTitle("群管理");
        mTitle.setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        // 设置OnTouchListener
        gridView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (adapter.isInDeleteMode) {
                            adapter.isInDeleteMode = false;
                            adapter.notifyDataSetChanged();
                            return true;
                        }
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
        setListener();
    }

    /**
     * 根据action 不同设置监听
     */
    private void setListener() {
        if (TextUtils.equals(CommunicateBaseFragment.MANAGE_GROUP_ACTION,startPageAction)){
            mUpdateGroupName.setOnClickListener(this);
            mUpdateGroupType.setOnClickListener(this);
            mUpdateGroupDes.setOnClickListener(this);
        }
    }


    protected void initData() {

        requestGroupMemberData();
    }
/**
* @author:GaoWenXu
* @date:2017/4/10 14:36
* @param:
* @return:
* @exception
* @des:获取群里成员
*/
    private void requestGroupMemberData() {
//        ATNercitaApi.loadAllGroupMembers(mGroupID, new StringCallback() {
//            @Override
//            public void onError(Call call, Exception e, int id) {
//                Log.e(TAG, "error msg load Group : " + e.getMessage());
//            }
//
//            @Override
//            public void onResponse(String response, int id) {
//                processJson(response);
//            }
//        });
    }

    /**
     * 处理获取的群组成员信息
     *
     * @param response
     */
    private void processJson(String response) {
        if (response == null) {
            return;
        }
        ACGroupMemberEntity memberEntity = JsonUtil.parseJsonToBean(response, ACGroupMemberEntity.class);
        List<ACGroupMemberEntity.MembersBean> members = memberEntity.getMembers();
        if (members == null){
            return;
        }
        mTvGroupMemberSize.setText("(" + members.size() + "人)");
        adapter = new GridAdapter(this, R.layout.em_grid, members);
        gridView.setAdapter(adapter);
    }


    protected void sendRequestData() {

//        ATNercitaApi.loadGroupDetailMsg(mGroupID, new StringCallback() {
//            @Override
//            public void onError(Call call, Exception e, int id) {
//
//            }
//
//            @Override
//            public void onResponse(String response, int id) {
//                executeDataBySubClazz(response);
//            }
//        });
    }


    protected void executeDataBySubClazz(String response) {

        if (response == null) {
            return;
        }
        Log.e(TAG, "group detail json str :" + response);
        groupDataDetail = JsonUtil.parseJsonToBean(response, ACGroupDataDetail.class);
        if (groupDataDetail == null) {
            return;
        }
        updateUiByData(groupDataDetail);
    }


    protected String getCacheKeyPrefix() {
        return CACHE_KEY_GROUP_DETAIL;
    }

    /**
     * 更新Ui 页面
     *
     * @param detail
     */
    private void updateUiByData(ACGroupDataDetail detail) {
        mGroupName.setText(detail.getGroupname());
        mTvGroupType.setText(detail.getIndustry());
        mGroupDes.setText(detail.getDescription());
    }

    /**
     * 获取从上一个页面传递过来groupId
     */
    private void getDataFromIntent() {
        Intent intent = getIntent();
        mGroupID = intent.getStringExtra(CommunicateBaseFragment.GROUP_ID_INFO);
        startPageAction = intent.getAction();
        Log.e(TAG,"action : "+startPageAction);
    }

    @OnClick({R.id.tv_delete_group})
    public void onClick(View v) {
        int itemId = v.getId();
        switch (itemId) {
            case R.id.rl_group_name:
                Intent updateGroupName = new Intent(ACGroupDetailMsgActivity.this, ACUpdateGroupInfoActivity.class);
                updateGroupName.putExtra(CommunicateBaseFragment.GROUP_INFO_DATA, groupDataDetail);
                updateGroupName.setAction(UPDATE_GROUP_NAME);
                startActivityForResult(updateGroupName, UPATE_INFO_REQUEST_CODE);
                break;
            case R.id.rl_group_type:
                Intent updateType = new Intent(ACGroupDetailMsgActivity.this, ACUpdateGroupInfoActivity.class);
                updateType.putExtra(CommunicateBaseFragment.GROUP_INFO_DATA, groupDataDetail);
                updateType.setAction(UPDATE_GROUP_TYPE);
                startActivityForResult(updateType, UPATE_INFO_REQUEST_CODE);
                break;
            case R.id.rl_group_des:
                Intent updateDes = new Intent(ACGroupDetailMsgActivity.this, ACUpdateGroupInfoActivity.class);
                updateDes.putExtra(CommunicateBaseFragment.GROUP_INFO_DATA, groupDataDetail);
                updateDes.setAction(UPDATE_GROUP_DES);
                startActivityForResult(updateDes, UPATE_INFO_REQUEST_CODE);
                break;
//            case R.id.tv_all_friends:
//                Intent allFriendIntent = new Intent(ATGroupDetailMsgActivity.this,AllFriendsActivity.class);
//                allFriendIntent.putExtra(GROUP_ID_TO_MEMBER,mGroupID);
//                startActivity(allFriendIntent);
//                break;
            case R.id.tv_delete_group:
                deleteCurrentGroup();
                break;
        }
    }

    private void deleteCurrentGroup() {
        confirmDialog = new ACAlertConfirmDialog(ACGroupDetailMsgActivity.this);
        confirmDialog.show();
        confirmDialog.setmTvMain("删除群组");
        confirmDialog.hideSubTitle();
        confirmDialog.setOnDialogButtonClickListener(new ACAlertConfirmDialog.OnDialogButtonClickListener() {
            @Override
            public void onDismissClick() {
                confirmDialog.dismiss();
            }

            @Override
            public void onConfirmClick() {
                doDeleteFromNet();
            }
        });
    }

    /**
     * 发送网络请求删除当前群组
     */
    private void doDeleteFromNet() {
//        ATNercitaApi.deleteCurrentGroup(mGroupID, new StringCallback() {
//
//            @Override
//            public void onError(Call call, Exception e, int id) {
//                if (confirmDialog.isShowing()) {
//                    confirmDialog.dismiss();
//                }
//                Toast.makeText(getApplicationContext(), "网络请求失败", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onResponse(String response, int id) {
//                Log.e(TAG, "deleteGroup Json : " + response);
//                processDeleteGroup(response);
//                if (confirmDialog.isShowing()) {
//                    confirmDialog.dismiss();
//                }
//            }
//        });
    }

    /**
     * 处理删除群组的json
     *
     * @param response
     */
    private void processDeleteGroup(String response) {
        if (response == null) {
            return;
        }
        try {
            JSONObject obj = new JSONObject(response);
            if (obj != null) {
                int status = (int) obj.get("status");
                if (status == 200) { // 删除成功
//                    if (TextUtils.equals(ATGroupInfoFragment.MANAGE_GROUP_ACTION,startPageAction)){
//
//                    }
                    setResult(RESULT_CODE_DELETE);
                    finish();
                } else {
                    String message = (String) obj.get("message");
                    Toast.makeText(getApplicationContext(), "" + message, Toast.LENGTH_SHORT).show();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private ProgressDialog progressDialog;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ACUpdateGroupInfoActivity.RESULT_CODE_UPDATE_GROUP_INFO) {
           // requestData(true);
        }

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_ADD_USER:// 添加群成员
//                    addMembersToGroup(newmembers);
                    requestGroupMemberData();
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 群组成员gridadapter
     *
     * @author admin_new
     */
    private class GridAdapter extends ArrayAdapter<ACGroupMemberEntity.MembersBean> {

        private ArrayList<ACGroupMemberEntity.MembersBean> memebers;
        private int res;
        public boolean isInDeleteMode;
        public GridAdapter(Context context, int textViewResourceId, List<ACGroupMemberEntity.MembersBean> members) {
            super(context, textViewResourceId, members);
            res = textViewResourceId;
            isInDeleteMode = false;
            this.memebers = (ArrayList<ACGroupMemberEntity.MembersBean>) members;
        }

        @Override
        public View getView(final int position, View convertView, final ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = LayoutInflater.from(getContext()).inflate(res, null);
                holder.imageView = (ImageView) convertView.findViewById(R.id.iv_avatar);
                holder.textView = (TextView) convertView.findViewById(R.id.tv_name);
                holder.badgeDeleteView = (ImageView) convertView.findViewById(R.id.badge_delete);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            final LinearLayout button = (LinearLayout) convertView.findViewById(R.id.button_avatar);
            // 最后一个item，减人按钮
            if (position == getCount() - 1) {
                holder.textView.setText("");
                // 设置成删除按钮
                holder.imageView.setImageResource(R.drawable.em_smiley_minus_btn);
//				button.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.smiley_minus_btn, 0, 0);
//                 如果不是创建者或者没有相应权限，不提供加减人按钮
                String imUserName = SPUtil.getString(getApplicationContext(), Constants.IMUSERNAME, "");
                Log.e(TAG, "imUserName  - " + imUserName + " currentUser - " + EMClient.getInstance().getCurrentUser());
                if (TextUtils.equals(ACGroupChatActivity.GROUP_CHAT_ROOM_PAGE,startPageAction)) {
                    // if current user is not group admin, hide add/remove btn
                    convertView.setVisibility(View.INVISIBLE);
                } else if (TextUtils.equals(CommunicateBaseFragment.MANAGE_GROUP_ACTION,startPageAction)){ // 显示删除按钮
                    if (isInDeleteMode) {
                        // 正处于删除模式下，隐藏删除按钮
                        convertView.setVisibility(View.INVISIBLE);
                    } else {
                        // 正常模式
                        convertView.setVisibility(View.VISIBLE);
                        convertView.findViewById(R.id.badge_delete).setVisibility(View.INVISIBLE);
                    }
                    final String st10 = getResources().getString(R.string.The_delete_button_is_clicked);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            EMLog.d(TAG, st10);
                            isInDeleteMode = true;
                            notifyDataSetChanged();
                        }
                    });
                }else {
                    convertView.setVisibility(View.INVISIBLE);
                }
            } else if (position == getCount() - 2) { // 添加群组成员按钮
                holder.textView.setText("");
                holder.imageView.setImageResource(R.drawable.em_smiley_add_btn);
//				button.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.smiley_add_btn, 0, 0);
//                 如果不是创建者或者没有相应权限
                if (TextUtils.equals(ACGroupChatActivity.GROUP_CHAT_ROOM_PAGE,startPageAction)) {
                    // if current user is not group admin, hide add/remove btn
                    convertView.setVisibility(View.INVISIBLE);
                } else if (TextUtils.equals(CommunicateBaseFragment.MANAGE_GROUP_ACTION,startPageAction)) {
//                 正处于删除模式下,隐藏添加按钮
                    if (isInDeleteMode) {
                        convertView.setVisibility(View.INVISIBLE);
                    } else {
                        convertView.setVisibility(View.VISIBLE);
                        convertView.findViewById(R.id.badge_delete).setVisibility(View.INVISIBLE);
                    }
                    final String st11 = getResources().getString(R.string.Add_a_button_was_clicked);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(ACGroupDetailMsgActivity.this, ACGroupPickContactsActivity.class);
                            intent.putExtra("groupMembers", memebers);
                            intent.putExtra("groupId", mGroupID);
                            intent.setAction(ADD_USER_TO_GROUP);
                            // 进入选人页面
                            startActivityForResult(intent, REQUEST_CODE_ADD_USER);
                        }
                    });
                }else {
                    convertView.setVisibility(View.INVISIBLE);
                }
            } else { // 普通item，显示群组成员
                final ACGroupMemberEntity.MembersBean item = getItem(position);
                convertView.setVisibility(View.VISIBLE);
                button.setVisibility(View.VISIBLE);
//				Drawable avatar = getResources().getDrawable(R.drawable.default_avatar);
//				avatar.setBounds(0, 0, referenceWidth, referenceHeight);
//				button.setCompoundDrawables(null, avatar, null, null);
                EaseUserUtils.setUserNick(item.getNickName(), holder.textView);
                EaseUserUtils.setUserAvatar(getContext(), item.getImuserName(), holder.imageView);
                if (isInDeleteMode) {
                    // 如果是删除模式下，显示减人图标
                    convertView.findViewById(R.id.badge_delete).setVisibility(View.VISIBLE);
                } else {
                    convertView.findViewById(R.id.badge_delete).setVisibility(View.INVISIBLE);
                }
                final String st12 = getResources().getString(R.string.not_delete_myself);
                final String st13 = getResources().getString(R.string.Are_removed);
                final String st14 = getResources().getString(R.string.Delete_failed);
                final String st15 = getResources().getString(R.string.confirm_the_members);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (isInDeleteMode) {
                            // 如果是删除自己，return
                            if (EMClient.getInstance().getCurrentUser().equals(item.getImuserName())) {
                                new EaseAlertDialog(ACGroupDetailMsgActivity.this, st12).show();
                                return;
                            }
                            if (!NetUtils.hasNetwork(getApplicationContext())) {
                                Toast.makeText(getApplicationContext(), getString(R.string.network_unavailable), Toast.LENGTH_SHORT).show();
                                return;
                            }
                            deleteMembersFromGroup(item.getImuserName());
                        } else {
                            // 正常情况下点击user，可以进入用户详情或者聊天页面等等
                            // startActivity(new
                            // Intent(GroupDetailsActivity.this,
                            // ChatActivity.class).putExtra("userId",
                            // user.getUsername()));

                        }
                    }

                    /**
                     * 删除群成员
                     *
                     * @param username
                     */
                    protected void deleteMembersFromGroup(final String username) {
                        final ProgressDialog deleteDialog = new ProgressDialog(ACGroupDetailMsgActivity.this);
                        deleteDialog.setMessage(st13);
                        deleteDialog.setCanceledOnTouchOutside(false);
                        deleteDialog.show();

//                        ATNercitaApi.deleteGroupMembers(username,mGroupID, new StringCallback() {
//                            @Override
//                            public void onError(Call call, Exception e, int id) {
//                                deleteDialog.dismiss();
//                                isInDeleteMode = false;
//                                Toast.makeText(getApplicationContext(),"删除失败", Toast.LENGTH_SHORT).show();
//                            }
//
//                            @Override
//                            public void onResponse(String response, int id) {
//                                if (response == null) {
//                                    return;
//                                }
//                                isInDeleteMode = false;
//                                try {
//                                    JSONObject obj = new JSONObject(response);
//                                    int status = (int) obj.get("status");
//                                    if (status == 200) { // 删除成功
//                                        requestGroupMemberData();
//                                        Toast.makeText(getContext(), "删除成功", Toast.LENGTH_SHORT).show();
//                                    } else {
//                                        String message = (String) obj.get("message");
//                                        Toast.makeText(getApplicationContext(), "" + message, Toast.LENGTH_SHORT).show();
//                                    }
//                                    deleteDialog.dismiss();
//                                } catch (JSONException e) {
//                                    deleteDialog.dismiss();
//                                    e.printStackTrace();
//                                }
//                            }
//                        });
                        new Thread(new Runnable() {

                            @Override
                            public void run() {
//                                try {
//                                    // 删除被选中的成员
//                                    EMClient.getInstance().groupManager().removeUserFromGroup(groupId, username);
//                                    isInDeleteMode = false;
//                                    runOnUiThread(new Runnable() {
//
//                                        @Override
//                                        public void run() {
//                                            deleteDialog.dismiss();
//                                            refreshMembers();
//                                            ((TextView) findViewById(R.id.group_name)).setText(group.getGroupName() + "("
//                                                    + group.getAffiliationsCount() + st);
//                                        }
//                                    });
//                                } catch (final Exception e) {
//                                    deleteDialog.dismiss();
//                                    runOnUiThread(new Runnable() {
//                                        public void run() {
//                                            Toast.makeText(getApplicationContext(), st14 + e.getMessage(), Toast.LENGTH_LONG).show();
//                                        }
//                                    });
//                                }

                            }
                        }).start();
                    }
                });

                button.setOnLongClickListener(new View.OnLongClickListener() {

                    @Override
                    public boolean onLongClick(View v) {
//                        if(EMClient.getInstance().getCurrentUser().equals(username))
//                            return true;
//                        if (group.getOwner().equals(EMClient.getInstance().getCurrentUser())) {
//                            new EaseAlertDialog(GroupDetailsActivity.this, null, st15, null, new EaseAlertDialog.AlertDialogUser() {
//
//                                @Override
//                                public void onResult(boolean confirmed, Bundle bundle) {
//                                    if(confirmed){
//                                        addUserToBlackList(username);
//                                    }
//                                }
//                            }, true).show();

//                        }
                        return false;
                    }
                });
            }
            return convertView;
        }

        @Override
        public int getCount() {
            return super.getCount() + 2;
        }
    }

    private static class ViewHolder {
        ImageView imageView;
        TextView textView;
        ImageView badgeDeleteView;
    }
}
