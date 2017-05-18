package cn.org.nercita.agriculturalconsultant.main.communicate.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
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
import android.widget.RelativeLayout;
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
import cn.org.nercita.agriculturalconsultant.main.bean.QuestionTypeBean;
import cn.org.nercita.agriculturalconsultant.main.bean.TypeListBean;
import cn.org.nercita.agriculturalconsultant.main.me.adapter.PhotoAdapter;
import cn.org.nercita.agriculturalconsultant.utils.BitmapUtils;
import cn.org.nercita.agriculturalconsultant.utils.JsonUtil;
import cn.org.nercita.agriculturalconsultant.utils.LogUtil;
import cn.org.nercita.agriculturalconsultant.utils.PermissionUtils;
import cn.org.nercita.agriculturalconsultant.utils.RecyclerItemClickListener;
import cn.org.nercita.agriculturalconsultant.utils.SPUtil;
import cn.org.nercita.agriculturalconsultant.utils.ToastUtil;
import cn.org.nercita.agriculturalconsultant.view.TitleBar;
import me.iwf.photopicker.PhotoPicker;
import me.iwf.photopicker.PhotoPreview;
import okhttp3.Call;

/**
 * Created by 梁兴胜 on 2017/4/11.
 * 农技提问界面
 */

public class TechQuestionAskActivity extends BaseActivity {

    private static final String TAG = "TechQuestionAskActivity";
    //标题
    @Bind(R.id.title_ask_tech)
    TitleBar mTitle;
    //提问内容
    @Bind(R.id.content_question_tech_ask)
    EditText contentAsk;
    //已选问题类型
    @Bind(R.id.question_type_selected)
    TextView questionTypeSelected;
    //问题类型布局
    @Bind(R.id.question_type)
    RelativeLayout questionType;
    //选择图片
    @Bind(R.id.select_photo)
    ImageView selectPhoto;
    //预览图片
    @Bind(R.id.photo_preview)
    RecyclerView photoPreview;
    //已选图片
    private ArrayList<String> photosSelected = new ArrayList<>();
    //图片预览
    private PhotoAdapter photoAdapter;
    //选择问题类型
    private PopupWindow mPopupWindow;
    //问题类型1级
    private ListView popList1;
    //问题类型2级
    private ListView popList2;
    //问题类型1级
    private List<String> typeList1 = new ArrayList<String>();
    ;
    //问题类型2级
    private List<String> typeList2 = new ArrayList<String>();
    ;
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
    //TypeListBean
    private TypeListBean typeListBean;
    //确定键
    private TextView commit;
    //提醒
    private SVProgressHUD mSvProgressHUD;
    //id  范博文 2017.4.17 修改  应传id
    private String id;
    //NICKNAME
    private String nickName;
    private int poplistposition;

    @Override
    protected int getContentView() {
        return R.layout.activity_tech_ask;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        init();
        photosSelected.clear();
        //获取问题类型 范博文 2017.4.17
        getQuestionType();
    }

    /**
     * 初始化
     *
     * @author: liangxingsheng
     * @date: 2017/4/11 下午3:08
     */
    private void init() {

        mSvProgressHUD = new SVProgressHUD(this);
        initListener();
        //id  范博文 2017.4.17 修改  应传id
        id = SPUtil.getString(this, AppConfig.ID, "");
        nickName = SPUtil.getString(this, AppConfig.NICKNAME, "1");
    }

    /**
     * 点击事件
     *
     * @author: liangxingsheng
     * @date: 2017/4/11 下午3:08
     */
    private void initListener() {

        mTitle.setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        commit = mTitle.getCommit();
        mTitle.setCommitListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getQuestionContent();
            }
        });
        photoPreview.addOnItemTouchListener(new RecyclerItemClickListener(this, new
                RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                PhotoPreview.builder()
                        .setPhotos(photosSelected)
                        .setCurrentItem(position)
                        .start(TechQuestionAskActivity.this);
            }
        }));
        photoPreview.setLayoutManager(new StaggeredGridLayoutManager(4, OrientationHelper
                .VERTICAL));
    }

    /**
     * 点击事件
     *
     * @author: liangxingsheng
     * @date: 2017/4/11 下午3:07
     */
    @OnClick({R.id.question_type, R.id.select_photo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.question_type:
                //选择问题类型
//                initPopWindow(view);
                mPopupWindow.showAsDropDown(view);
                break;
            case R.id.select_photo:
                //选择图片
                PermissionUtils.requestPermission(this, PermissionUtils.CODE_CAMERA,
                        mPermissionGrant);
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
     * @date: 2017/4/11 下午3:07
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
                        photoAdapter = new PhotoAdapter(TechQuestionAskActivity.this,
                                photosSelected);
                        photoPreview.setAdapter(photoAdapter);
                        photoAdapter.notifyDataSetChanged();
                    }
                    break;
            }
        }
    }


    /**
     * 申请相册权限
     *
     * @author: liangxingsheng
     * @date: 2017/4/11 下午3:07
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
                            .start(TechQuestionAskActivity.this, PhotoPicker.REQUEST_CODE);
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * 创建问题类型popupwindow
     *
     * @author: 范博文
     * @date: 2017/4/17 下午3:07
     */
    private void initPopWindow() {
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
                poplistposition = position;
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
                for (int i = 0; i < typeListBean.getList().get(position).getChildList().size();
                     i++) {
                    typeList2.add(typeListBean.getList().get(position).getChildList().get(i)
                            .getTitle());
                }
                type2Aadapter = new ATCountiesAdapter(TechQuestionAskActivity.this);
                popList2.setAdapter(type2Aadapter);
                type2Aadapter.setDataListAndNotify(typeList2);


            }
        });
        popList2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                questionTypeSelected.setText(typeList2.get(position));
                mPopupWindow.dismiss();
                typeId = typeListBean.getList().get(poplistposition).getChildList().get(position)
                        .getSort() + "";
                LogUtil.e(TAG, "typeId==" + typeId);
            }
        });
    }

    /**
     * 获取问题类型
     *
     * @author: 范博文
     * @date: 2017/4/17 下午4:08
     */
    private void getQuestionType() {

        OkHttpUtils.get()
                .url(Constants.BaseUrL + Constants.TYPE_LIST)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {

                        typeListBean = JsonUtil.parseJsonToBean(response, TypeListBean.class);
                        for (int i = 0; i < typeListBean.getList().size(); i++) {
                            typeList1.add(typeListBean.getList().get(i).getTitle());
                        }
                        initPopWindow();
                    }
                });
    }

    /**
     * 获取问题类型2级
     *
     * @author: liangxingsheng
     * @date: 2017/4/11 下午4:08
     */
    private void getQuestionType2(String parentId) {

        OkHttpUtils.get()
                .url(Constants.QUESTION_TYPE)
                .addParams("parentId", parentId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {

                        typeList2.clear();
                        typeResponse = JsonUtil.parseJsonToBean(response, QuestionTypeBean.class);
                        for (int i = 0; i < typeResponse.getResult().size(); i++) {
                            typeList2.add(typeResponse.getResult().get(i).getName());
                        }
                        type2Aadapter = new ATCountiesAdapter(TechQuestionAskActivity.this);
                        popList2.setAdapter(type2Aadapter);
                        type2Aadapter.setDataListAndNotify(typeList2);
                    }
                });
    }

    /**
     * 获取问题内容
     *
     * @author: liangxingsheng
     * @date: 2017/4/11 下午4:38
     */
    private void getQuestionContent() {

        questionContent = contentAsk.getText().toString().trim();
        if (questionContent.length() <= 9) {
            ToastUtil.showShort(getApplicationContext(), "问题描述不能少于10个字");
        } else if (TextUtils.isEmpty(typeId)) {
            ToastUtil.showShort(getApplicationContext(), "请选择问题类");
        } else {
            uploadQuestion();
        }
    }

    /**
     * 提交问题
     *
     * @author: liangxingsheng
     * @date: 2017/4/11 下午4:38
     */
    private void uploadQuestion() {

        commit.setEnabled(false);
        if (mSvProgressHUD != null)
            mSvProgressHUD.showWithStatus("正在提交...");
        PostFormBuilder formBuilder = OkHttpUtils.post()
                .url(Constants.BaseUrL + Constants.TECH_QUESTION_ASK)
                .addParams("accountId", id)
                .addParams("accountName", nickName)
                .addParams("title", questionContent)
                .addParams("typeid", typeId);
        if (photosSelected != null && photosSelected.size() > 0) {
            for (String selectedPhoto : photosSelected) {
                Uri uri = Uri.fromFile(new File(selectedPhoto));
                File file = BitmapUtils.scal(uri);
                formBuilder.addFile("file", selectedPhoto, file);//相关图片
            }
        }
        formBuilder.build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        commit.setEnabled(true);
                        Log.e(TAG, e.getMessage() + "");
                        ToastUtil.showLong(TechQuestionAskActivity.this, "提问失败");
                        if (mSvProgressHUD != null)
                            mSvProgressHUD.dismiss();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG, response);
                        commit.setEnabled(true);
                        if (response.contains("200")) {
                            ToastUtil.showLong(TechQuestionAskActivity.this, "提问成功");
                            setResult(RESULT_OK);
                            finish();
                        }else {
                            ToastUtil.showLong(TechQuestionAskActivity.this, "提问失败，请稍后重试");
                        }
                        if (mSvProgressHUD != null)
                            mSvProgressHUD.dismiss();

                    }
                });
    }
}
