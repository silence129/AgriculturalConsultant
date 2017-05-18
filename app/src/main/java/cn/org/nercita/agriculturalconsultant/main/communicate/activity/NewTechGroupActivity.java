package cn.org.nercita.agriculturalconsultant.main.communicate.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.utils.EaseUserUtils;
import com.hyphenate.easeui.widget.EaseExpandGridView;
import com.hyphenate.util.EMLog;
import com.hyphenate.util.NetUtils;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import cn.org.nercita.agriculturalconsultant.Constants;
import cn.org.nercita.agriculturalconsultant.R;
import cn.org.nercita.agriculturalconsultant.base.BaseActivity;
import cn.org.nercita.agriculturalconsultant.main.bean.ACGroupMemberEntity;
import cn.org.nercita.agriculturalconsultant.utils.SPUtil;
import cn.org.nercita.agriculturalconsultant.view.TitleBar;
import cn.org.nercita.agriculturalconsultant.view.address.AddressDialog;
import cn.org.nercita.agriculturalconsultant.view.address.AddressDialogEngine;
import me.iwf.photopicker.PhotoPicker;
import me.iwf.photopicker.PhotoPreview;


/**
 * Created by 范博文 on 2017/4/6.
 * 新建群组
 */
/**
 * @modified_by: GaoWenXu
 * @modified_date: 2017/4/10 16:35
 * @des:增加添加群成员功能
 */
public class NewTechGroupActivity extends BaseActivity implements AddressDialogEngine.OnSaveItemClickListener {
    final public static int REQUEST_CODE_ASK_CALL_IMAGE = 123;
    private static final int REQUEST_CODE_FOR_INDUSTRY = 1038;
    private static final int REQUEST_CODE_FOR_MEMBERS = 1039;
    public static final int RESULT_CODE_FOR_NEW_GROUP = 1031;
    private static final String TAG = NewTechGroupActivity.class.getSimpleName();
    public static final String NEW_GROUP_ADD_USER = "newgroupadduser";
    public static final String PUT_EXIST_USER = "put_exist_user";
    public static final String INDUTRY_TYPE = "industry";
    public static final String LOCATION_TYPE = "address";

    @Bind(R.id.tb_title)
    TitleBar tbTitle;
    @Bind(R.id.rb_industry_type)
    RadioButton rbIndustryType;
    @Bind(R.id.rb_loc_type)
    RadioButton rbLocType;
    @Bind(R.id.rg_group_type_select)
    RadioGroup NewTechGroupActivityrgGroupTypeSelect;
    @Bind(R.id.tv_industry)
    TextView tvIndustry;
    @Bind(R.id.tv_select_industry)
    ImageView tvSelectIndustry;
    @Bind(R.id.tv_group_loc)
    TextView tvGroupLoc;
    @Bind(R.id.img_creategroup_dingwei)
    ImageView imgCreategroupDingwei;
    @Bind(R.id.ll_group_loc_section)
    LinearLayout llGroupLocSection;
    @Bind(R.id.et_group_name)
    EditText etGroupName;
    @Bind(R.id.iv_group_avator)
    ImageView ivGroupAvator;
    @Bind(R.id.et_group_des)
    EditText etGroupDes;
    @Bind(R.id.exg_gridview)
    EaseExpandGridView exgGridview;
    @Bind(R.id.iv_group_member)
    ImageView ivGroupMember;
    private GridAdapter adapter;
    private AddressDialog addressDialog;
    String groupType = INDUTRY_TYPE;

    ArrayList<ACGroupMemberEntity.MembersBean> mMemoryCheckBeans = new ArrayList<>();
    private ArrayList<String> mCheckIds;
    private File personalPicFile;
    @Override
    protected int getContentView() {
        return R.layout.activity_new_group;
    }

    @Override
    protected void initData() {
        super.initData();
        tbTitle.setTitle("新建群组");
        tbTitle.setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        tbTitle.getCommit().setVisibility(View.VISIBLE);
        tbTitle.setCommitListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAndCreateGroup();
            }
        });
        addressDialog = new AddressDialog(NewTechGroupActivity.this);
        addressDialog.setOnSaveItemClickListener(this);
        NewTechGroupActivityrgGroupTypeSelect.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
                switch (checkedRadioButtonId) {
                    case R.id.rb_industry_type:
                        groupType = INDUTRY_TYPE;
                        llGroupLocSection.setVisibility(View.GONE);
                        break;
                    case R.id.rb_loc_type:
                        groupType = LOCATION_TYPE;
                        llGroupLocSection.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });
        adapter = new GridAdapter(this, R.layout.em_grid, mMemoryCheckBeans);
        exgGridview.setAdapter(adapter);
    }

    @OnClick({R.id.iv_group_avator, R.id.iv_group_member})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_group_avator:
                //检查权限
                checkPromission();
                break;
            //拉群成员
            case R.id.iv_group_member:
              Intent addUserIntent = new Intent(NewTechGroupActivity.this,
                        ACGroupPickContactsActivity.class);
                addUserIntent.setAction(NEW_GROUP_ADD_USER);
                addUserIntent.putExtra(PUT_EXIST_USER, mMemoryCheckBeans);
                startActivityForResult(addUserIntent, REQUEST_CODE_FOR_MEMBERS);
                break;
            case R.id.tv_select_industry:
             //群类型选择

                break;
            case R.id.tv_group_loc:
                addressDialog.showAddressDialog(NewTechGroupActivity.this);
                break;
            case R.id.img_creategroup_dingwei:
                    tvGroupLoc.performClick();
                break;
        }
    }
    /**
     * 校验用户输入信息 以及 创建新群
     */
    private void checkAndCreateGroup() {
        final String groupName = etGroupName.getText().toString().trim();
        final String mGroupDes = etGroupDes.getText().toString().trim();
        if (TextUtils.isEmpty(groupName)) {
            Toast.makeText(getApplicationContext(), "群组名字不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(mGroupDes)) {
            Toast.makeText(getApplicationContext(), "群描述不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        String mIndustry = this.tvIndustry.getText().toString().trim();
        if (TextUtils.equals(mIndustry, "未选") && TextUtils.equals(groupType, INDUTRY_TYPE)) {
            Toast.makeText(getApplicationContext(), "所属行业不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        String groupLocation = tvGroupLoc.getText().toString().trim();
        if (TextUtils.equals(groupType, LOCATION_TYPE) && TextUtils.equals(groupLocation, getResources().getString(R.string.tv_select_location))) {
            Toast.makeText(getApplicationContext(), "所在地域不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        if (personalPicFile == null) {
            Toast.makeText(getApplicationContext(), "群头像不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String imUser = SPUtil.getString(getApplicationContext(), Constants.IMUSERNAME, "");
        if (TextUtils.isEmpty(imUser)) {
            Toast.makeText(getApplicationContext(), "用户信息缺失，请从新登陆", Toast.LENGTH_SHORT).show();
            return;
        }


//        ATNercitaApi.createNewGroup(groupName, mGroupDes, mIndustry, imUser, personalPicFile, mCheckIds, mProvince, mCity, mCounty, groupType, new StringCallback() {
//            @Override
//            public void onError(Call call, Exception e, int id) {
//
//            }
//
//            @Override
//            public void onResponse(String response, int id) {
//                Log.e(TAG, "response json : " + response);
//                try {
//                    JSONObject jsonObject = new JSONObject(response);
//                    int status = (int) jsonObject.get("status");
//                    if (status == 200) {
//                        Toast.makeText(getApplicationContext(), "创建成功", Toast.LENGTH_SHORT).show();
//                        setResult(RESULT_CODE_FOR_NEW_GROUP);
//                        finish();
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    Log.e(TAG, "error msg : " + e.getMessage());
//                    Toast.makeText(getApplicationContext(), "创建失败", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
    }

    /**
     * author:范博文
     * date:2017/4/6 17:17
     * des: 动态申请权限
     * param:null
     * return:null
     */
    private void checkPromission() {
        if (Build.VERSION.SDK_INT >= 23) {
            int checkSDCardPermission = ContextCompat.checkSelfPermission(NewTechGroupActivity
                    .this, Manifest.permission.READ_EXTERNAL_STORAGE);
            int checkCameraPermission = ContextCompat.checkSelfPermission(NewTechGroupActivity
                    .this, Manifest.permission.CAMERA);
            if (checkSDCardPermission != PackageManager.PERMISSION_GRANTED ||
                    checkCameraPermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(NewTechGroupActivity.this, new
                        String[]{Manifest.permission.CAMERA, Manifest.permission
                        .READ_EXTERNAL_STORAGE}, REQUEST_CODE_ASK_CALL_IMAGE);
                return;
            } else {
                //上面已经写好的获取照片方法
                chooseImage();
            }
        } else {
            //上面已经写好的获取照片方法
            chooseImage();
        }
    }

    /**
     * author:范博文
     * date:2017/4/6 17:18
     * des: 申请权限回调
     * param: null
     * return: null
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_CALL_IMAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager
                        .PERMISSION_GRANTED) {
                    chooseImage();
                } else {
                    Toast.makeText(NewTechGroupActivity.this, "获取权限失败", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }
    }

    /**
     * author:范博文
     * date:2017/4/7 9:40
     * des: 从图库选择图片
     * param:null
     * return:null
     */
    public void chooseImage() {
        PhotoPicker.builder()
                .setPhotoCount(1)
                .start(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            //从选择群头像返回的图片地址
            case RESULT_OK:
                if (requestCode == PhotoPicker.REQUEST_CODE || requestCode == PhotoPreview
                        .REQUEST_CODE) {
                    List<String> photos = null;
                    if (data != null) {
                        photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                    }

                    if (photos != null) {
//                        Log.e(TAG, "photoPath : " + photos.get(0));
                        personalPicFile = new File(photos.get(0));
                        Picasso.with(getApplicationContext()).load(Uri.fromFile(personalPicFile))
                                .into
                                (ivGroupAvator);
                    }
                }
                break;
            case ACGroupPickContactsActivity.NEW_GROUP_FOR_MEMBERS:
                if (requestCode == REQUEST_CODE_FOR_MEMBERS){
                    mCheckIds = (ArrayList<String>) data.getSerializableExtra(ACGroupPickContactsActivity.CHECK_USER_IDS);
                    ArrayList<ACGroupMemberEntity.MembersBean> mCheckedBean = (ArrayList<ACGroupMemberEntity.MembersBean>) data.getSerializableExtra(ACGroupPickContactsActivity.CHECK_USER_BEANS);
                    if (mCheckedBean.size() > 0) {
                        mMemoryCheckBeans.clear();
                        mMemoryCheckBeans.addAll(mCheckedBean);
                        ivGroupMember.setVisibility(View.GONE);
                        exgGridview.setVisibility(View.VISIBLE);
                        adapter.notifyDataSetChanged();
                    }



                }


               break;
        }
    }
    String mProvince;
    String mCity;
    String mCounty;
    @Override
    public void onSaveItemClick(String province, String city, String county, String town) {
        mProvince = province;
        mCity = city;
        mCounty = county;
        StringBuilder builder = new StringBuilder();
        builder.append(province).append(city).append(county).append(town);
        tvGroupLoc.setText(builder.toString());
        addressDialog.dismiss();
    }

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
                Log.e("", "imUserName  - " + imUserName + " currentUser - " + EMClient.getInstance().getCurrentUser());
                if (!imUserName.equals(EMClient.getInstance().getCurrentUser())) {
                    // if current user is not group admin, hide add/remove btn
                    convertView.setVisibility(View.INVISIBLE);
                } else { // 显示删除按钮
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
                            EMLog.d("", st10);
                            isInDeleteMode = true;
                            notifyDataSetChanged();
                        }
                    });
                }
            } else if (position == getCount() - 2) { // 添加群组成员按钮
                holder.textView.setText("");
                holder.imageView.setImageResource(R.drawable.em_smiley_add_btn);
//				button.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.smiley_add_btn, 0, 0);
                // 如果不是创建者或者没有相应权限
//                if (!group.isAllowInvites() && !group.getOwner().equals(EMClient.getInstance().getCurrentUser())) {
//                    // if current user is not group admin, hide add/remove btn
//                    convertView.setVisibility(View.INVISIBLE);
//                } else {
                // 正处于删除模式下,隐藏添加按钮
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
                        Intent addUserIntent = new Intent(NewTechGroupActivity.this, ACGroupPickContactsActivity.class);
                        addUserIntent.setAction(NEW_GROUP_ADD_USER);
                        addUserIntent.putExtra(PUT_EXIST_USER, mMemoryCheckBeans);
                        startActivityForResult(addUserIntent, REQUEST_CODE_FOR_MEMBERS);
                    }
                });
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
//                            if (EMClient.getInstance().getCurrentUser().equals(username)) {
//                                new EaseAlertDialog(GroupDetailsActivity.this, st12).show();
//                                return;
//                            }
                            if (!NetUtils.hasNetwork(getApplicationContext())) {
                                Toast.makeText(getApplicationContext(), getString(R.string.network_unavailable), Toast.LENGTH_SHORT).show();
                                return;
                            }
                            deleteMembersFromGroup(item);
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
                     * @param
                     */
                    protected void deleteMembersFromGroup(ACGroupMemberEntity.MembersBean item) {
                        mCheckIds.remove(item.getImuserName());
                        mMemoryCheckBeans.remove(item);
                        memebers.remove(item);
                        notifyDataSetChanged();
                        isInDeleteMode = false;
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
