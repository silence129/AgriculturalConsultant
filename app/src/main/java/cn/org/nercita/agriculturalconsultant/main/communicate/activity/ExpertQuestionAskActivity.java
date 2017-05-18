package cn.org.nercita.agriculturalconsultant.main.communicate.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.Bind;
import butterknife.OnClick;
import cn.org.nercita.agriculturalconsultant.AppConfig;
import cn.org.nercita.agriculturalconsultant.Constants;
import cn.org.nercita.agriculturalconsultant.R;
import cn.org.nercita.agriculturalconsultant.base.BaseActivity;
import cn.org.nercita.agriculturalconsultant.common.ATCitiesAdapter;
import cn.org.nercita.agriculturalconsultant.common.ATCountiesAdapter;
import cn.org.nercita.agriculturalconsultant.main.bean.ExpertLibraryBean;
import cn.org.nercita.agriculturalconsultant.main.bean.QuestionTypeBean;
import cn.org.nercita.agriculturalconsultant.main.bean.TypeListBean;
import cn.org.nercita.agriculturalconsultant.main.me.adapter.PhotoAdapter;
import cn.org.nercita.agriculturalconsultant.utils.BitmapUtils;
import cn.org.nercita.agriculturalconsultant.utils.ImageUtil;
import cn.org.nercita.agriculturalconsultant.utils.JsonUtil;
import cn.org.nercita.agriculturalconsultant.utils.LogUtil;
import cn.org.nercita.agriculturalconsultant.utils.PermissionUtils;
import cn.org.nercita.agriculturalconsultant.utils.RecyclerItemClickListener;
import cn.org.nercita.agriculturalconsultant.utils.SPUtil;
import cn.org.nercita.agriculturalconsultant.utils.ToastUtil;
import cn.org.nercita.agriculturalconsultant.view.TitleBar;
import de.hdodenhof.circleimageview.CircleImageView;
import me.iwf.photopicker.PhotoPicker;
import me.iwf.photopicker.PhotoPreview;
import okhttp3.Call;

import static cn.org.nercita.agriculturalconsultant.main.communicate.activity
        .ExpertListLibraryActivity.CHOOSE;

/**
 * Created by 梁兴胜 on 2017/4/12.
 * 专家问题提问界面
 */

public class ExpertQuestionAskActivity extends BaseActivity {
    private static final String TAG = "ExpertQuestionAskActivity";
    private static final int CHOOSEEXPERT = 115;
    @Bind(R.id.expert_selected_name)
    TextView selected_name;
    //标题
    @Bind(R.id.title)
    TitleBar mTitle;
    @Bind(R.id.expert1)
    //推荐专家1
            CircleImageView expert1;
    @Bind(R.id.expert2)
    //推荐专家2
            CircleImageView expert2;
    //推荐专家3
    @Bind(R.id.expert3)
    CircleImageView expert3;
    //更多专家
    @Bind(R.id.more_expert)
    TextView moreExpert;
    //已选专家
    @Bind(R.id.expert_selected)
    CircleImageView expertSelected;
    //问题类型
    @Bind(R.id.tv_question_type)
    TextView questionTypeSelected;
    //问题类型
    @Bind(R.id.iv_question_type)
    ImageView ivQuestionType;
    //问题内容
    @Bind(R.id.edt_help_Content)
    EditText edtHelpContent;
    //图片选择
    @Bind(R.id.tv_expert_camera)
    TextView tvExpertCamera;
    //图片预览
    @Bind(R.id.photo_picker_ExpertHelp)
    RecyclerView photoPreview;
    //已选图片
    private ArrayList<String> photosSelected = new ArrayList<>();
    //图片预览
    private PhotoAdapter photoAdapter;
    //确定键
    @Bind(R.id.tv_expert_help_Commit)
    TextView tvExpertHelpCommit;
    @Bind(R.id.update_title)

    //问题标题
            TextView updateTitle;
    @Bind(R.id.name1)
    TextView expertName1;
    @Bind(R.id.name2)
    TextView expertName2;
    @Bind(R.id.name3)
    TextView expertName3;
    //提醒
    private SVProgressHUD mSvProgressHUD;
    //选择问题类型
    private PopupWindow mPopupWindow;
    //问题类型1级
    private ListView popList1;
    //问题类型2级
    private ListView popList2;
    //问题类型1级
    private List<String> typeList1 = new ArrayList<>();
    //问题类型2级
    private List<String> typeList2 = new ArrayList<>();
    //问题类型1级Adapter
    private ATCitiesAdapter type1Aadapter;
    //问题类型2级Adapter
    private ATCountiesAdapter type2Aadapter;
    //问题类型
    private String typeId = "";
    //问题内容
    private String questionContent = "";
    //问题类型Response
    private QuestionTypeBean typeResponse;
    //已选专家id
    private String expertId = "";
    private String id;
    private String picurl = Constants.PIC_PATH1 + "uploads/";
    private ExpertLibraryBean.ItemBean itemBean;
    private ExpertLibraryBean.ItemBean itemBean1;
    private ExpertLibraryBean.ItemBean itemBean2;
    private TypeListBean typeListBean;
    private int poplist1position;

    @Override
    protected int getContentView() {
        return R.layout.activity_expert_ask;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        init();
    }

    /**
     * 初始化
     *
     * @author: liangxingsheng
     * @date: 2017/4/12 下午3:08
     */
    private void init() {
        //得到id
        id = SPUtil.getString(ExpertQuestionAskActivity.this, AppConfig.ID, "");
        mSvProgressHUD = new SVProgressHUD(this);
        initListener();
        //获取专家
        getExpert();
        getQuestionType();
    }

    /**
     * author:范博文
     * date:2017/4/14 18:45
     * des:获取前三个专家
     * param:null
     * return:null
     */
    private void getExpert() {
        OkHttpUtils.get()
                .url(Constants.GETEXPERTLIST)
                .addParams("pageSize", "3")
                .addParams("currentPage", 1 + "")
                .build()
                .execute(new StringCallback() {

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, e.getMessage() + "");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG, response);
                        ExpertLibraryBean expertLibraryBean = JsonUtil.parseJsonToBean(response,
                                ExpertLibraryBean.class);
                        if (expertLibraryBean == null) {
                            return;
                        }
                        List<ExpertLibraryBean.ItemBean> item = expertLibraryBean.getItem();
                        if (item == null || item.size() < 1) {
                            ToastUtil.show(ExpertQuestionAskActivity.this, "没有更多数据了");
                            return;
                        }
                        itemBean = item.get(0);
                        ImageUtil.loadAccountPic(ExpertQuestionAskActivity.this, expert1, picurl
                                + itemBean.getPhoto());
                        expertName1.setText(itemBean.getName());
                        itemBean1 = item.get(1);
                        ImageUtil.loadAccountPic(ExpertQuestionAskActivity.this, expert2, picurl
                                + itemBean1.getPhoto());
                        expertName2.setText(itemBean1.getName());
                        itemBean2 = item.get(2);
                        ImageUtil.loadAccountPic(ExpertQuestionAskActivity.this, expert3, picurl
                                + itemBean2.getPhoto());
                        expertName3.setText(itemBean2.getName());


                    }
                });
    }

    /**
     * 点击事件
     *
     * @author: liangxingsheng
     * @date: 2017/4/12 下午3:08
     */
    private void initListener() {

        mTitle.setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        photoPreview.addOnItemTouchListener(new RecyclerItemClickListener(this, new
                RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        PhotoPreview.builder()
                                .setPhotos(photosSelected)
                                .setCurrentItem(position)
                                .start(ExpertQuestionAskActivity.this);
                    }
                }));
        photoPreview.setLayoutManager(new StaggeredGridLayoutManager(4, OrientationHelper
                .VERTICAL));
    }

    /**
     * 点击事件
     *
     * @author: liangxingsheng
     * @date: 2017/4/12 下午3:07
     */
    @OnClick({R.id.expert1, R.id.expert2, R.id.expert3, R.id.more_expert, R.id.tv_question_type,
            R.id.iv_question_type, R.id.tv_expert_camera, R.id.tv_expert_help_Commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.expert1:
                selected_name.setText(itemBean.getName());
                ImageUtil.loadAccountPic(ExpertQuestionAskActivity.this, expertSelected, picurl +
                        itemBean.getPhoto());
                expertId = itemBean.getId() + "";
                break;
            case R.id.expert2:
                selected_name.setText(itemBean1.getName());
                ImageUtil.loadAccountPic(ExpertQuestionAskActivity.this, expertSelected, picurl +
                        itemBean1.getPhoto());
                expertId = itemBean1.getId() + "";
                break;
            case R.id.expert3:
                selected_name.setText(itemBean2.getName());
                ImageUtil.loadAccountPic(ExpertQuestionAskActivity.this, expertSelected, picurl +
                        itemBean2.getPhoto());
                expertId = itemBean2.getId() + "";
                break;
            case R.id.more_expert:
                startActivityForResult(new Intent(ExpertQuestionAskActivity.this,
                        ExpertListLibraryActivity.class), CHOOSEEXPERT);
                break;
            case R.id.tv_question_type:
            case R.id.iv_question_type:
                mPopupWindow.showAsDropDown(view);
                break;
            case R.id.tv_expert_camera:
                PermissionUtils.requestPermission(this, PermissionUtils.CODE_CAMERA,
                        mPermissionGrant);
                break;
            case R.id.tv_expert_help_Commit:
                getQuestionContent();
                break;
        }
    }

    /**
     * 接收图片选择结果
     *
     * @param requestCode 请求码
     * @param resultCode  结果码
     * @param data        数据
     * @author: liangxingsheng
     * @date: 2017/4/12 下午3:07
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PhotoPicker.REQUEST_CODE:
                case PhotoPreview.REQUEST_CODE:
                    if (data != null) {
                        photosSelected = data.getStringArrayListExtra(PhotoPicker
                                .KEY_SELECTED_PHOTOS);
                        Log.d("图片", photosSelected.size() + "");
                        photoAdapter = new PhotoAdapter(ExpertQuestionAskActivity.this,
                                photosSelected);
                        photoPreview.setAdapter(photoAdapter);
                        photoAdapter.notifyDataSetChanged();
                    }
                    break;

            }
        } else if (requestCode == CHOOSEEXPERT) {
            if (resultCode == CHOOSE) {
                if (data != null) {
                    Bundle extras = data.getExtras();
                    String expertName = extras.getString("name", "");
                    String expertIcon = extras.getString("icon", "");
                    String expertid = extras.getString("expertid", "");
                    selected_name.setText(expertName);
                    ImageUtil.loadAccountPic(ExpertQuestionAskActivity.this, expertSelected,
                            picurl + expertIcon);
                    expertId = expertid;

                }
            }
        }
    }


    /**
     * 申请相册权限
     *
     * @author: liangxingsheng
     * @date: 2017/4/12 下午3:07
     */
    private PermissionUtils.PermissionGrant mPermissionGrant = new PermissionUtils
            .PermissionGrant() {
        @Override
        public void onPermissionGranted(int requestCode) {
            switch (requestCode) {
                case PermissionUtils.CODE_READ_EXTERNAL_STORAGE:
                case PermissionUtils.CODE_CAMERA:
                    PhotoPicker.builder()
                            .setPhotoCount(4)
                            .setShowCamera(true)
                            .setShowGif(true)
                            .setPreviewEnabled(false)
                            .start(ExpertQuestionAskActivity.this, PhotoPicker.REQUEST_CODE);
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * 创建问题类型popupwindow
     *
     * @author: liangxingsheng
     * @date: 2017/4/12 下午3:07
     */
    private void initPopWindow() {
        //种植 2畜牧兽医 3水产 4农机化 5其他
        View popupView = getLayoutInflater().inflate(R.layout.popupwindow_question_type, null);
        mPopupWindow = new PopupWindow(popupView, LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT, true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        popList1 = (ListView) popupView.findViewById(R.id.popuid_question_type);
        popList2 = (ListView) popupView.findViewById(R.id.popuid_question_type_);
        type1Aadapter = new ATCitiesAdapter(this);
        popList1.setAdapter(type1Aadapter);
        type1Aadapter.setDataListAndNotify(typeList1);
        popList1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                poplist1position = position;
                TextView tv = (TextView) view;
                if (!tv.isSelected()) {
                    Map<String, Boolean> map = type1Aadapter.map;
                    Set<String> keys = map.keySet();
                    Iterator<String> iterator = keys.iterator();
                    while (iterator.hasNext()) {
                        String key = iterator.next();
                        if (tv.getText().toString().equals(key)) {
                            map.put(key, true);
                        } else {
                            map.put(key, false);
                        }
                    }
                }
                type1Aadapter.notifyDataSetChanged();
                if (position == 3) {
                    typeId = (position + 1) + "";
                    questionTypeSelected.setText("其他");
                    mPopupWindow.dismiss();
                    return;
                }
                typeList2.clear();
                for (int i = 0; i < typeListBean.getList().get(position).getChildList().size(); i++) {
                    typeList2.add(typeListBean.getList().get(position).getChildList().get(i).getTitle());
                }
                type2Aadapter = new ATCountiesAdapter(ExpertQuestionAskActivity.this);
                popList2.setAdapter(type2Aadapter);
                type2Aadapter.setDataListAndNotify(typeList2);
            }


        });


        popList2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                questionTypeSelected.setText(typeList2.get(position));
                mPopupWindow.dismiss();
                typeId = typeListBean.getList().get(poplist1position).getChildList().get(position).getSort()  + "";
                LogUtil.e(TAG, "typeId==" + typeId);
            }
        });
    }


    /**
     * 获取问题内容
     *
     * @author: liangxingsheng
     * @date: 2017/4/12 下午4:38
     */
    private void getQuestionContent() {

        questionContent = edtHelpContent.getText().toString().trim();
        if (questionContent.length() <= 9) {
            ToastUtil.showShort(getApplicationContext(), "问题描述不能少于10个字");
            return;
        } else if (TextUtils.isEmpty(typeId)) {
            ToastUtil.showShort(getApplicationContext(), "请选择问题类");
            return;
        } else if (TextUtils.isEmpty(expertId)) {
            ToastUtil.showShort(getApplicationContext(), "请选择专家");
            return;
        }
        if (TextUtils.isEmpty(updateTitle.getText().toString())) {
            ToastUtil.showShort(getApplicationContext(), "请输入标题");
            return;
        }
        uploadQuestion();
    }

    /**
     * 提交问题
     *
     * @author: liangxingsheng
     * @date: 2017/4/12 下午4:38
     */
    private void uploadQuestion() {
        Log.e(TAG,"aimid"+expertId+"accountid"+id);
        tvExpertHelpCommit.setEnabled(false);
        if (mSvProgressHUD != null)
            mSvProgressHUD.showWithStatus("正在提交...");
        PostFormBuilder post = OkHttpUtils.post();
        post.url(Constants.ASKEXPERT);
        if (photosSelected != null && photosSelected.size() > 0) {
            for (String selectedPhoto : photosSelected) {
                Uri uri = Uri.fromFile(new File(selectedPhoto));
                File file = BitmapUtils.scal(uri);
                post.addFile("fileup", selectedPhoto, file);//相关图片
            }
        }
        post.addParams("aimid", expertId);
        post.addParams("accountid", id);
        post.addParams("content", questionContent);
        post.addParams("title", updateTitle.getText().toString());
        post.addParams("typeid",typeId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        tvExpertHelpCommit.setEnabled(true);
                        Log.e(TAG, e.getMessage() + "");
                        ToastUtil.showLong(ExpertQuestionAskActivity.this, "上传失败");
                        if (mSvProgressHUD != null)
                            mSvProgressHUD.dismiss();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        tvExpertHelpCommit.setEnabled(true);
                        Log.e(TAG, response);
                        if (response.contains("200")) {
                            ToastUtil.showLong(ExpertQuestionAskActivity.this, "上传成功");
                            edtHelpContent.setText("");
                            updateTitle.setText("");
                            photosSelected.clear();
                            if (photoAdapter != null) {
                                photoAdapter.notifyDataSetChanged();
                            }


                        }else {
                            ToastUtil.showLong(ExpertQuestionAskActivity.this, "提问失败，请稍后重试");

                        }
                        if (mSvProgressHUD != null)
                            mSvProgressHUD.dismiss();
                    }
                });
    }
    /**
     * 获取问题类型
     * @author: liangxingsheng
     * @date: 2017/4/11 下午4:08
     */
    private void getQuestionType(){

        OkHttpUtils.get()
                .url(Constants.BaseUrL+Constants.TYPE_LIST)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {

                        typeListBean = JsonUtil.parseJsonToBean(response, TypeListBean.class);
                        for (int i = 0; i < typeListBean.getList().size() ; i++) {
                            typeList1.add(typeListBean.getList().get(i).getTitle());
                        }
                        initPopWindow();
                    }
                });
    }
}
