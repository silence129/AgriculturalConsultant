package cn.org.nercita.agriculturalconsultant.main.communicate;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import cn.org.nercita.agriculturalconsultant.AppConfig;
import cn.org.nercita.agriculturalconsultant.AppContext;
import cn.org.nercita.agriculturalconsultant.Constants;
import cn.org.nercita.agriculturalconsultant.R;
import cn.org.nercita.agriculturalconsultant.base.BaseFragment;
import cn.org.nercita.agriculturalconsultant.main.bean.ACGroupInfoBean;
import cn.org.nercita.agriculturalconsultant.main.bean.TechQuestionListBean;
import cn.org.nercita.agriculturalconsultant.main.communicate.activity.ExpertQuestionAskActivity;
import cn.org.nercita.agriculturalconsultant.main.communicate.activity.ExpertQuestionListActivity;
import cn.org.nercita.agriculturalconsultant.main.communicate.activity.NewTechGroupActivity;
import cn.org.nercita.agriculturalconsultant.main.communicate.activity.TechQuestionAskActivity;
import cn.org.nercita.agriculturalconsultant.main.communicate.activity.TechQuestionContentActivity;
import cn.org.nercita.agriculturalconsultant.main.communicate.adapter.TechQuestionListAdapter;
import cn.org.nercita.agriculturalconsultant.utils.JsonUtil;
import cn.org.nercita.agriculturalconsultant.utils.SPUtil;
import cn.org.nercita.agriculturalconsultant.view.ACGroupItemView;
import okhttp3.Call;

import static android.app.Activity.RESULT_OK;
import static cn.org.nercita.agriculturalconsultant.R.id.tv_zdzj;

/**
 * Created by 范博文 on 2017/4/6.
 * 交流界面
 */
/**
 * @modified_by: GaoWenXu
 * @modified_date: 2017/4/10 11:23
 * @des:交流界面完善
 */

public class CommunicateBaseFragment extends BaseFragment {
    public static final int CREATE_GROUP = 1004;
    public static final int TECH_ASK = 1003;
    public static final int DELETE_GROUP = 1004;
    public static final String GROUP_INFO_DATA = "group_info_data";
    public static final int GROUP_MANAGE_REQUEST_CODE = 1005;
    public static final String GROUP_ID_INFO = "groupId";
    private static final int TODETAIL_MSG_GROUP = 1006;
    public static final String REFRESH_GROUP_TO_NEW_MSG = "refresh_group";
    public static final String MANAGE_GROUP_ACTION = "manage_group_info";
    @Bind(R.id.tv_wzj)
    TextView tvWzj;
    @Bind(tv_zdzj)
    TextView tvZdzj;
    @Bind(R.id.tv_AskTheExperts)
    RelativeLayout tvAskTheExperts;
    @Bind(R.id.tv_njwd)
    TextView tvNjwd;
    @Bind(R.id.tv_cttw)
    TextView tvCttw;
    @Bind(R.id.tv_Questions_Agricultural)
    RelativeLayout tvQuestionsAgricultural;
    @Bind(R.id.tec_)
    LinearLayout tec;
    @Bind(R.id.tv_new_group)
    TextView tvNewGroup;
    @Bind(R.id.tv_display_all)
    TextView tvDisplayAll;
    @Bind(R.id.tv_person_community)
    TextView tvPersonCommunity;
    @Bind(R.id.ll_expert_community)
    LinearLayout llExpertCommunity;
    @Bind(R.id.tv_tech_ask)
    TextView tvTechAsk;
    @Bind(R.id.ll_tech_ask)
    LinearLayout llTechAsk;
    @Bind(R.id.txt_dianjichuangjian)
    TextView txtDianjichuangjian;
    @Bind(R.id.lin_jainshezhong)
    LinearLayout linJainshezhong;
    @Bind(R.id.iv_process_validate)
    ImageView ivProcessValidate;
    @Bind(R.id.txt_red_bac)
    TextView txtRedBac;
    @Bind(R.id.ll_group_container)
    RelativeLayout llGroupContainer;
    @Bind(R.id.ll_group_mine)
    LinearLayout llGroupMine;
    @Bind(R.id.ll_other_group_container)
    LinearLayout llOtherGroupContainer;
    @Bind(R.id.ll_group_others)
    LinearLayout llGroupOthers;
    @Bind(R.id.ll_group_info)
    LinearLayout llGroupInfo;
    //区域筛选 梁兴胜
    @Bind(R.id.tv_word0_area)
    TextView tvWord0Area;
    //区域筛选 梁兴胜
    @Bind(R.id.word0_click_area)
    LinearLayout word0ClickArea;
    //类型筛选 梁兴胜
    @Bind(R.id.tv_word1_type)
    TextView tvWord1Type;
    //类型筛选 梁兴胜
    @Bind(R.id.word1_click_type)
    LinearLayout word1ClickType;
    //排序筛选 梁兴胜
    @Bind(R.id.tv_word2_order)
    TextView tvWord2Order;
    //排序筛选 梁兴胜
    @Bind(R.id.word2_click_order)
    LinearLayout word2ClickOrder;
    //问题列表
    @Bind(R.id.lv_tech_question)
    PullToRefreshListView lvTechQuestion;
    //刷新
//    @Bind(R.id.service_refresh)
//    SwipeRefreshLayout refresh;
    //问题列表adapter 梁兴胜
    TechQuestionListAdapter questionListAdapter;
    //问题列表数据
    private List<TechQuestionListBean.ListBean> questionList = new ArrayList<>();
    //accountId 梁兴胜
    private String accountId;
    //NICKNAME 梁兴胜
    private String nickName;
    //rols 梁兴胜
    private String rols;

    private static final String TAG = CommunicateBaseFragment.class.getSimpleName();
    private List<ACGroupInfoBean.ResultBean> result;
    int ownerCount = 0;
    int otherCount = 0;
    //页数
    private int PageNo = 1;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_communicate_nochat;
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        init();
    }



    /**
     * 初始化
     * @author: liangxingsheng
     * @date: 2017/4/11 下午2:14
     */
    private void init(){

        setListener();
        getTechQuestionData(true);
        accountId = SPUtil.getInt(getActivity(), AppConfig.ACCOUNT_ID, 1)+"";
        nickName = SPUtil.getString(getActivity(), AppConfig.NICKNAME, "1");
        rols = SPUtil.getString(getActivity(), AppConfig.ROLS, "");
        if (questionListAdapter == null){
            questionListAdapter = new TechQuestionListAdapter(getActivity(), questionList);
            lvTechQuestion.setAdapter(questionListAdapter);
        }
        questionListAdapter.notifyDataSetChanged();
        //根据角色修改界面 范博文  2017.4.18
        if (AppConfig.ROLEFARMER.equals(rols)) {
            //农民
            tvWzj.setText("问专家");
            tvZdzj.setText("指定专家作答");
        } else {
            tvWzj.setText("答农民");
            tvZdzj.setText("解答农民问题");
        }
    }

    /**
     * 点击和刷新事件
     * @author: liangxingsheng
     * @date: 2017/4/11 上午9:41
     * @modified_by liangxingsheng
     * @modified_date 2017/4/14 下午9:32
     * 下拉刷新和上拉加载
     */
    private void setListener() {
        //查看问题详情
        lvTechQuestion.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), TechQuestionContentActivity.class);
                intent.putExtra("questionId", questionList.get(i-1).getId()+"");
                startActivity(intent);
            }
        });
        lvTechQuestion.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                PageNo = 1;
                getTechQuestionData(true);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                PageNo ++;
                getTechQuestionData(false);
            }
        });
    }

    /**
     * 农技问答数据
     * @author: liangxingsheng
     * @date: 2017/4/11 下午5:46
     */
    private void getTechQuestionData(final boolean isNew){

        OkHttpUtils.get()
                .url(Constants.BaseUrL + Constants.TECH_QUESTION_LIST)
                //如果查登录人提的问题及解答过的问题，传此值为登录id，默认查询所有问题
//                .addParams("LinkAccountId", "")
                .addParams("everyPage", "5")
                .addParams("currentPage", PageNo+"")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        PageNo --;
                        if (lvTechQuestion != null)
                            lvTechQuestion.onRefreshComplete();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG, response);
                        if (lvTechQuestion != null)
                            lvTechQuestion.onRefreshComplete();
                        TechQuestionListBean data = JsonUtil.parseJsonToBean(response, TechQuestionListBean.class);
                        if (data == null || data.getList().size() == 0){
                            return;
                        }
                        if (isNew){
                            questionList.clear();
                            questionList.addAll(data.getList());
                        }else {
                            questionList.addAll(data.getList());
                        }
                        questionListAdapter.setData(questionList);
                    }
                });
    }

    /**
     * 提问后刷新结果
     * @author: liangxingsheng
     * @date: 2017/4/11 下午3:07
     * @param requestCode 请求码
     * @param resultCode 结果码
     * @param data 数据
     * @modified_by 梁兴胜
     * @modified_date 2017/4/12 下午5:57
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case TECH_ASK:
                    getTechQuestionData(true);
                    break;
            }
        }
    }


    /**
     * author:范博文
     * date:2017/4/6 14:45
     * des: 点击事件
     * param: 无
     * return: null
     * @modified_by 梁兴胜
     * @modified_date 2017/4/12 下午5:57
     * 两种问答提问
     */
    @OnClick({R.id.tv_AskTheExperts, R.id.tv_Questions_Agricultural, R.id.tv_new_group, R.id.word0_click_area, R.id.word1_click_type, R.id.word2_click_order})
    public void onClick(View view) {
        switch (view.getId()) {
            //问专家 或 回答问题  范博文 2017.4.21

            case R.id.tv_AskTheExperts:
                if (AppConfig.ROLEFARMER.equals(rols)) {
                 //农民 角色
                    startActivity(new Intent(getActivity(), ExpertQuestionAskActivity.class));
                } else {
                    //专家角色
                    startActivity(new Intent(getActivity(), ExpertQuestionListActivity.class));
                }
                break;
            //农技问答
            case R.id.tv_Questions_Agricultural:
//                startActivity(new Intent(getActivity(), TecQuestionActivity.class));
                startActivityForResult(new Intent(getActivity(), TechQuestionAskActivity.class), TECH_ASK);
                break;
            //新建群组
            case R.id.tv_new_group:
                Intent intent = new Intent(getActivity(), NewTechGroupActivity.class);
                startActivityForResult(intent, CREATE_GROUP);
                break;
            case R.id.word0_click_area:
                break;
            case R.id.word1_click_type:
                break;
            case R.id.word2_click_order:
                break;
        }
    }
    /**
    * @author:GaoWenXu
    * @date:2017/4/10 12:27
    * @param:
    * @return:
    * @exception
    * @des:获取群组信息
    */
    @Override
    protected void initData() {
        super.initData();
    }
    /**
    * @author:GaoWenXu
    * @date:2017/4/10 12:48
    * @param:response:网络获取的群组信息字符串
    * @return:
    * @exception
    * @des:Json数据转化成实体类
    */
    private void processJson(String response) {
        if (response == null) {
            return;
        }
        Gson gson = new Gson();
        ACGroupInfoBean groupInfoBean = gson.fromJson(response, ACGroupInfoBean.class);
        if (groupInfoBean == null) {
            return;
        }
        //result = groupInfoBean.getResult();
        if (isVisible()) {
            showGroupData(result);
        }
    }
    private void showGroupData(List<ACGroupInfoBean.ResultBean> result) {
        if (result != null && result.size() > 0 ) {
            Log.e(TAG, "mineGroup == null ? " );
            llGroupMine.removeAllViews();
            llGroupOthers.removeAllViews();
            llGroupInfo.setVisibility(View.VISIBLE);
            tvDisplayAll.setVisibility(View.VISIBLE);
//        mEmptyLayout.setVisibility(View.GONE);
        } else {
            llGroupInfo.setVisibility(View.GONE);
//        mEmptyLayout.setVisibility(View.VISIBLE);
            return;
        }
        for (int i = 0; i < result.size(); i++) {
            ACGroupInfoBean.ResultBean resultBean = result.get(i);
            //遍历信息
            updateUiByData(resultBean);
        }
        llGroupContainer.setVisibility(ownerCount > 0 ? View.VISIBLE : View.GONE);
        llOtherGroupContainer.setVisibility(otherCount > 0 ? View.VISIBLE : View.GONE);
    }
    private void updateUiByData(final ACGroupInfoBean.ResultBean bean) {
        final ACGroupItemView itemView = new ACGroupItemView(AppContext.getInstance());
        EMConversation conversation = EMClient.getInstance().chatManager().getConversation(bean.getChatgroupid());
        if (conversation != null && conversation.getUnreadMsgCount() > 0) {
            //得到未读消息
            int unreadMsgCount = conversation.getUnreadMsgCount();
            itemView.setUnreadCountState(unreadMsgCount > 0);
            itemView.setUnreadCount(String.valueOf(unreadMsgCount));
        }
        itemView.setGroupIcon(bean.getPic());
        itemView.setGroupName(bean.getGroupname());
        itemView.setGroupThemeOrDes(bean.getIndustry(),bean.getType());

        itemView.setMemberSize(String.valueOf(bean.getPeoplenum()));
        itemView.setGroupDes(String.valueOf(bean.getDescription()));

        int loacl_AccountId = SPUtil.getInt(getActivity(), Constants.ACCOUNT_ID, -1);
        //看是否是管理员显示管理标志
        if (loacl_AccountId == bean.getOwneraccountid() && llGroupMine.getChildCount() < result.size()) {
            ownerCount++;
            itemView.setOwnerStateClickListener(new ACGroupItemView.OnOwnerStateClickListener() {
                @Override
                public void onOwnerStateClick() {
                    bean.setUnReadCountSize(0);
                    itemView.setUnreadCountState(false);
//                    Intent intent = new Intent(getActivity(), ATGroupChatActivity.class);
//                    intent.putExtra(ATGroupChatActivity.GROUP_ID_KEY, bean.getChatgroupid());
//                    intent.putExtra("groupName", bean.getGroupname());
//                    startActivityForResult(intent, GROUP_MANAGE_REQUEST_CODE);
                }

                @Override
                public void onAddPersonItemClick() {
//                        Intent intent = new Intent(getActivity(),FriendContactListActivity.class);
//                        intent.putExtra("usersmap",users);
//                        startActivity(intent);
                }

                @Override
                public void onManageClick() {
//                    Intent intent = new Intent(getActivity(), ATGroupDetailMsgActivity.class);
//                    intent.putExtra(GROUP_ID_INFO, bean.getChatgroupid());
//                    intent.setAction(MANAGE_GROUP_ACTION);
//                    startActivityForResult(intent, TODETAIL_MSG_GROUP);
                }
            });
            llGroupMine.addView(itemView);
            itemView.setManageButtonVisiable(true);
//            itemView.setGroupItemClickListener(new ATGroupItemView.OnGroupItemViewClickListener() {
//                @Override
//                public void onMangeButtonClick() {
//                    Intent intent = new Intent(getActivity(), ATGroupDetailMsgActivity.class);
//                    intent.putExtra(GROUP_ID_INFO, bean.getChatgroupid());
//                    intent.setAction(MANAGE_GROUP_ACTION);
//                    startActivityForResult(intent, TODETAIL_MSG_GROUP);
//                }
//            });
            itemView.setPadding(0, 0, 0, 20);
        } else if (loacl_AccountId != bean.getOwneraccountid() && llGroupOthers.getChildCount() < result.size()) {
            otherCount++;
            itemView.setOnEnterRoomClickListener(new ACGroupItemView.OnEnterRoomClickListener() {
                @Override
                public void enterChatRoomClick() {
                    bean.setUnReadCountSize(0);
                    itemView.setUnreadCountState(false);
//                    Intent intent = new Intent(getActivity(), ATGroupChatActivity.class);
//                    intent.putExtra(ATGroupChatActivity.GROUP_ID_KEY, bean.getChatgroupid());
//                    intent.putExtra(GROUP_INFO_DATA, bean);
//                    intent.putExtra("groupName", bean.getGroupname());
//                    startActivityForResult(intent,GROUP_MANAGE_REQUEST_CODE);
                }
            });
            llGroupOthers.addView(itemView);
            itemView.setManageButtonVisiable(false);
            itemView.setPadding(0, 0, 0, 20);
        }
    }
}
