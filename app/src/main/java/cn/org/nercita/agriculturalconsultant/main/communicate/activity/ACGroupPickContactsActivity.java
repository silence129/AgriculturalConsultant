package cn.org.nercita.agriculturalconsultant.main.communicate.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.easeui.widget.EaseSidebar;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import cn.org.nercita.agriculturalconsultant.R;
import cn.org.nercita.agriculturalconsultant.main.bean.ACAllFriendsEntity;
import cn.org.nercita.agriculturalconsultant.main.bean.ACGroupMemberEntity;
import cn.org.nercita.agriculturalconsultant.main.communicate.adapter.ACEaseContactAdapter;
import cn.org.nercita.agriculturalconsultant.utils.JsonUtil;
import okhttp3.Call;

/**
 * 描述：成员选择界面
 * @author GaoWenXu
 * @date 2017/4/10 15:05
 * @version v1.0.0
 */
public class ACGroupPickContactsActivity extends ACBaseActivity {
    private static final String TAG = ACGroupPickContactsActivity.class.getSimpleName();
    private static final String CHECKED_MEMBERS = ACGroupPickContactsActivity.class.getSimpleName();
    public static final String CHECK_USER_IDS = "checked_user_imids";
    public static final String CHECK_USER_BEANS = "check_user_beans";
    public static final int NEW_GROUP_FOR_MEMBERS = 1008;
    /**
     * if this is a new group
     */
    protected boolean isCreatingNewGroup;
    private PickContactAdapter contactAdapter;
    /**
     * members already in the group
     */
    private List<ACGroupMemberEntity.MembersBean> existMembers = new ArrayList<>();
    private List<ACGroupMemberEntity.MembersBean> alluserList = new ArrayList<>();
    private String groupId;
    private ArrayList groupMembers;
    private ArrayList<ACGroupMemberEntity.MembersBean> checkedMembers;
    private String lastPageAction;
    @Bind(R.id.bt_title_right)
    Button mRightSubmitClick;
    private ArrayList<ACGroupMemberEntity.MembersBean> mNewGroupExist;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_acgroup_pick_contacts;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        getLastPageData();
        if (groupId == null) {// create new group
            isCreatingNewGroup = true;        // 是否是创建新的群组
            if (mNewGroupExist != null) {
                Log.e(TAG, "mNewGroupExist.size() " + mNewGroupExist.size());
                existMembers.addAll(mNewGroupExist);
            }
        } else {
            if (groupMembers != null) {
                existMembers.addAll(groupMembers);
            }
        }
        if (TextUtils.equals(lastPageAction, ACGroupDetailMsgActivity.ADD_USER_TO_GROUP)) {
            mRightSubmitClick.setText("提交");
        } else if (TextUtils.equals(lastPageAction, NewTechGroupActivity.NEW_GROUP_ADD_USER)) {
            mRightSubmitClick.setText("选择");
        }
        loadAllGoodFriends();
    }

    private void getLastPageData() {
        Intent intent = getIntent();
        groupId = intent.getStringExtra("groupId");
        groupMembers = (ArrayList) intent.getSerializableExtra("groupMembers");
        mNewGroupExist = (ArrayList<ACGroupMemberEntity.MembersBean>) intent.getSerializableExtra(NewTechGroupActivity.PUT_EXIST_USER);
        lastPageAction = intent.getAction();
    }

    private void loadAllGoodFriends() {
//        ATNercitaApi.loadAllFriendsData("30001", "", "1", "a100", new StringCallback() {
//            @Override
//            public void onError(Call call, Exception e, int id) {
//                Log.e(TAG, "load AllFriends members error : " + e.getMessage());
//            }
//
//            @Override
//            public void onResponse(String response, int id) {
//                Log.e(TAG, "load AllFriends  members response : " + response);
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
        ACAllFriendsEntity allFriendsEntity = JsonUtil.parseJsonToBean(response, ACAllFriendsEntity.class);
        if (allFriendsEntity != null) {
            List<ACGroupMemberEntity.MembersBean> list = allFriendsEntity.getResult();
            Log.e(TAG, "list == null ? " + (list == null));
            getContactList(list);    // 将数据传递给Ui
        }
    }


    private void getContactList(List<ACGroupMemberEntity.MembersBean> members) {

        for (ACGroupMemberEntity.MembersBean member : members) {
            alluserList.add(member);
            Log.e(TAG, "member == null " + (member == null));
        }
        // sort the list
        Collections.sort(alluserList, new Comparator<ACGroupMemberEntity.MembersBean>() {    // 传入比较器中进行排序

            @Override
            public int compare(ACGroupMemberEntity.MembersBean lhs, ACGroupMemberEntity.MembersBean rhs) {
                if (lhs.getInitialLetter().equals(rhs.getInitialLetter())) {
                    if (lhs.getNickName() == null){
                        lhs.setNickName(" ");
                    }
                    if (rhs.getNickName() == null){
                        rhs.setNickName(" ");
                    }
                    return lhs.getNickName().compareTo(rhs.getNickName());
                } else {
                    if ("#".equals(lhs.getInitialLetter())) {
                        return 1;
                    } else if ("#".equals(rhs.getInitialLetter())) {
                        return -1;
                    }
                    return lhs.getInitialLetter().compareTo(rhs.getInitialLetter());    // 比较首字母进行排序
                }

            }
        });

        ListView listView = (ListView) findViewById(R.id.list);
        contactAdapter = new PickContactAdapter(this, R.layout.em_row_contact_with_checkbox, alluserList);//adapter中传入的 是 alluserList
        listView.setAdapter(contactAdapter);
        ((EaseSidebar) findViewById(R.id.sidebar)).setListView(listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkbox);
                checkBox.toggle();

            }
        });
    }

    /**
     * save selected members
     *
     * @param v
     */
    @OnClick({R.id.bt_title_right})
    public void onClick(View v) {
        int itemId = v.getId();
        switch (itemId) {
            case R.id.bt_title_right:
                if (TextUtils.equals(lastPageAction, ACGroupDetailMsgActivity.ADD_USER_TO_GROUP)) {
                    submitAddGroup();
                } else if (TextUtils.equals(lastPageAction, NewTechGroupActivity.NEW_GROUP_ADD_USER)) {
                    returnDataforNewGroup();
                }
                break;
        }

    }

    /**
     * 为新建群组 返回数据
     */
    private void returnDataforNewGroup() {
        Intent intent = new Intent();
        ArrayList<String> toBeAddMembers = getToBeAddMembers();
        intent.putExtra(CHECK_USER_IDS, toBeAddMembers);
        intent.putExtra(CHECK_USER_BEANS, checkedMembers);
        setResult(NEW_GROUP_FOR_MEMBERS, intent);
        finish();
    }

    /**
     * 将选择的用户添加到网络
     */
    private void submitAddGroup() {
        List<String> var = getToBeAddMembers();
//        ATNercitaApi.addGroupUser(groupId, var, new StringCallback() {
//            @Override
//            public void onError(Call call, Exception e, int id) {
//                Log.e(TAG, "Add user Exception : " + e.getMessage());
//                Toast.makeText(getApplicationContext(), " " + e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onResponse(String response, int id) {
//                try {
//                    JSONObject obj = new JSONObject(response);
//                    if (obj != null) {
//                        int status = (int) obj.get("status");
//                        if (status == 200) { // 添加成功
//                            Intent intent = new Intent();
////                            intent.putExtra(CHECKED_MEMBERS,checkedMembers);
//                            setResult(RESULT_OK, intent);
//                            finish();
//                        } else {
//                            String message = (String) obj.get("message");
//                            Toast.makeText(getApplicationContext(), "" + message, Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    Toast.makeText(getApplicationContext(), "添加失败", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
    }


    /**
     * get selected members
     *
     * @return
     */
    private ArrayList<String> getToBeAddMembers() {
        ArrayList<String> members = new ArrayList<>();
//        createGroupMembers = new ArrayList<>();
        checkedMembers = new ArrayList<>();
//        notCreateNew = new ArrayList<>();
        int length = contactAdapter.isCheckedArray.length;
        for (int i = 0; i < length; i++) {
            String username = contactAdapter.getItem(i).getImuserName();
//            if (!isCreatingNewGroup && contactAdapter.isCheckedArray[i] && !existMembers.contains(contactAdapter.getItem(i))) {
//                members.add(username);
//
//            }else {
//                checkedMembers.add(contactAdapter.getItem(i));
//                createGroupMembers.add(username);
//            }
            if (contactAdapter.isCheckedArray[i]) {
                if (isCreatingNewGroup) {
                    members.add(username);
                    checkedMembers.add(contactAdapter.getItem(i));
                    continue;
                }
                if (!existMembers.contains(contactAdapter.getItem(i))) {
                    members.add(username);
                    checkedMembers.add(contactAdapter.getItem(i));
                }
            }
        }

        return members;
    }

    /**
     * adapter
     */
    private class PickContactAdapter extends ACEaseContactAdapter {

        private boolean[] isCheckedArray;

        public PickContactAdapter(Context context, int resource, List<ACGroupMemberEntity.MembersBean> users) {
            super(context, resource, users);
            isCheckedArray = new boolean[users.size()];
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = super.getView(position, convertView, parent);
            final int itemPosition = position;
            ACGroupMemberEntity.MembersBean item = getItem(position);


            final CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkbox);    // checkBox  头像  name
            ImageView avatarView = (ImageView) view.findViewById(R.id.avatar);
            TextView nameView = (TextView) view.findViewById(R.id.name);

            if (checkBox != null) {
                if (existMembers != null && existMembers.contains(item)) {
                    checkBox.setButtonDrawable(R.drawable.em_checkbox_bg_gray_selector);
                } else {
                    checkBox.setButtonDrawable(R.drawable.em_checkbox_bg_selector);
                }

                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//						 check the exist members
                        if (existMembers.contains(getItem(itemPosition))) {
                            isChecked = true;
                            checkBox.setChecked(true);
                        }
                        isCheckedArray[position] = isChecked;

                    }
                });
                // keep exist members checked
                if (existMembers.contains(getItem(position))) {
                    checkBox.setChecked(true);
                    isCheckedArray[position] = true;
                } else {
                    checkBox.setChecked(isCheckedArray[position]);
                }
            }

            return view;
        }
    }

    public void back(View view) {
        finish();
    }
}
