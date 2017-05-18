package cn.org.nercita.agriculturalconsultant.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.org.nercita.agriculturalconsultant.R;


/**
 * 描述：群组列表ItemView，用于展示群组信息
 * @author GaoWenXu
 * @date 2017/4/10 13:25
 * @version v1.0.0
 */
public class ACGroupItemView extends FrameLayout implements ACGroupMangePopuView.OnPopGroupSettingClickListener {

    private static final String TAG = ACGroupItemView.class.getSimpleName();
    @Bind(R.id.iv_group_icon)
    ImageView mGroupIcon;
    @Bind(R.id.iv_manage)
    LinearLayout mImageMange;
    @Bind(R.id.tv_group_name)
    TextView mTvGroupName;
    @Bind(R.id.tv_group_theme)
    TextView mTvGroupTheme;
    @Bind(R.id.tv_group_des)
    TextView mGroupDes;
    @Bind(R.id.tv_member_num)
    TextView mMemberSize;
    @Bind(R.id.tv_unread_count)
    TextView mUnreadCount;

    private ACGroupMangePopuView popuView;
    private int width;
    private int popuHeight;

    public ACGroupItemView(Context context) {
        this(context, null);
    }

    public ACGroupItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ACGroupItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 初始化控件
     */
    private void init() {
        View itemView = View.inflate(getContext(), R.layout.vie_group_item, null);
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        width = wm.getDefaultDisplay().getWidth();
        addView(itemView);
        ButterKnife.bind(this, itemView);
        popuHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,30,getContext().getResources().getDisplayMetrics());
        popuView = new ACGroupMangePopuView(getContext(), (int)(width * 0.25), popuHeight);
        popuView.setGroupItemClickListener(this);
    }
    /**
    * @author:GaoWenXu
    * @date:2017/4/10 13:29
    * @param:url：网络获取的群头像url
    * @return:
    * @exception
    * @des:设置群头像
    */
    public void setGroupIcon(String url) {
        Glide.with(getContext()).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.pic_default_all).transform(new GlideRoundTransform(getContext(), 10)).into(mGroupIcon);
    }



    @OnClick({R.id.iv_manage,R.id.ll_to_chat_room, R.id.ll_ownerclick})
    public void onClick(View v) {
        int clickId = v.getId();
        switch (clickId) {
            case R.id.iv_manage:
//                if (!popuView.isShowing()){
//                    Log.e(TAG,"size : "+(-popuView.getWidth()));
//                    popuView.showAsDropDown(mImageMange,-(int)(width * 0.5),(-mImageMange.getMeasuredHeight()/2)+(-popuHeight/2));
//                }else {
//                    popuView.dismiss();
//                }
                if (ownerStateClickListener != null){
                    ownerStateClickListener.onManageClick();
                }
                break;
            case R.id.ll_to_chat_room:
            case R.id.ll_ownerclick:
                if (ownerStateClickListener != null){
                    ownerStateClickListener.onOwnerStateClick();
                }
                if (onEnterRoomClickListener != null){
                    onEnterRoomClickListener.enterChatRoomClick();
                }
                break;
        }
    }
/**
* @author:GaoWenXu
* @date:2017/4/10 13:30
* @param:isShow：控制是否显示bool型
* @return:
* @exception
* @des:设置管理按钮是否显示
*/
    public void setManageButtonVisiable(boolean isShow) {
        mImageMange.setVisibility(isShow ? VISIBLE : INVISIBLE);
    }
/**
* @author:GaoWenXu
* @date:2017/4/10 13:33
* @param:
* @return:
* @exception
* @des:设置群名称
*/
    public void setGroupName(String groupName){
        if (groupName != null){
            mTvGroupName.setText(groupName);
        }
    }
/**
* @author:GaoWenXu
* @date:2017/4/10 13:34
* @param:
* @return:
* @exception
* @des:设置群类型
*/
    public void setGroupThemeOrDes(String groupTheme,String type){
        if (!TextUtils.isEmpty(groupTheme)){
            mTvGroupTheme.setText(groupTheme);
        }else if (TextUtils.equals(type,"address")){
            mTvGroupTheme.setText("地域群");
        }
    }
/**
* @author:GaoWenXu
* @date:2017/4/10 13:34
* @param:
* @return:
* @exception
* @des:群详情
*/
    public void setGroupDes(String groupDes){
        if (groupDes != null){
            mGroupDes.setText(groupDes);
        }
    }
/**
* @author:GaoWenXu
* @date:2017/4/10 13:34
* @param:
* @return:
* @exception
* @des:摄者群成员数量
*/
    public void setMemberSize(String memberSize){
        if (memberSize != null){
            mMemberSize.setText(memberSize);
        }
    }

    public void setGroupItemClickListener(OnGroupItemViewClickListener listener) {
        this.listener = listener;
    }
/**
* @author:GaoWenXu
* @date:2017/4/10 13:36
* @param:
* @return:
* @exception
* @des:设置未读消息数量
*/
    public void setUnreadCount(String unreadCount){
        if (!TextUtils.isEmpty(unreadCount)){
            mUnreadCount.setText(unreadCount);
        }
    }

    public void setUnreadCountState(boolean isShowUnread){
        mUnreadCount.setVisibility(isShowUnread ? VISIBLE : INVISIBLE);
    }

    private OnGroupItemViewClickListener listener;

    @Override
    public void onManageItemClick() {
        if (listener != null){
            popuView.dismiss();
            listener.onMangeButtonClick();
        }
    }

    @Override
    public void onAddPersonItemClick() {
        if (ownerStateClickListener != null){
            ownerStateClickListener.onAddPersonItemClick();
        }

    }

    @Override
    public void onShareItemClick() {

    }

    public interface OnGroupItemViewClickListener {
        void onMangeButtonClick();
    }

    private OnEnterRoomClickListener onEnterRoomClickListener;

    /**
     *  点击跳转到 聊天室
     * @param onEnterRoomClickListener
     */
    public void setOnEnterRoomClickListener(OnEnterRoomClickListener onEnterRoomClickListener) {
        this.onEnterRoomClickListener = onEnterRoomClickListener;
    }

    public interface OnEnterRoomClickListener {
        void enterChatRoomClick();

    }


    public void setOwnerStateClickListener(OnOwnerStateClickListener ownerStateClickListener) {
        this.ownerStateClickListener = ownerStateClickListener;
    }

    private OnOwnerStateClickListener ownerStateClickListener;

    public interface OnOwnerStateClickListener {
        void onOwnerStateClick();
        void onAddPersonItemClick();
        void onManageClick();
    }
}
