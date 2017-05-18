package cn.org.nercita.agriculturalconsultant.main.communicate.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import cn.org.nercita.agriculturalconsultant.Constants;
import cn.org.nercita.agriculturalconsultant.R;
import cn.org.nercita.agriculturalconsultant.base.BaseActivity;
import cn.org.nercita.agriculturalconsultant.main.bean.QuestionTypeBean;
import cn.org.nercita.agriculturalconsultant.main.communicate.adapter.SelectQuestionTypeAdapter;
import cn.org.nercita.agriculturalconsultant.main.communicate.adapter.TechQuestionListAdapter;
import cn.org.nercita.agriculturalconsultant.utils.JsonUtil;
import okhttp3.Call;


/**
 * Created by 范博文 on 2017/4/7.
 * 农技问答
 */

public class TecQuestionActivity extends BaseActivity implements SelectQuestionTypeAdapter
        .SelectTypeListener {
    private static final String TAG = TecQuestionActivity.class.getSimpleName();
    @Bind(R.id.allOfCountry)
    LinearLayout allOfCountry;
    @Bind(R.id.allOfSort)
    LinearLayout allOfSort;
    @Bind(R.id.sortOfTime)
    LinearLayout sortOfTime;
    @Bind(R.id.tec_ruquest_img2)
    ImageView typeImage;//分类箭头
    @Bind(R.id.tec_ruquest_img3)
    ImageView tiemImage;//时间箭头
    @Bind(R.id.fenlei)
    TextView fenLei;//按分类排序
    @Bind(R.id.paixu)
    TextView paixu;//按时间排序
    //问题列表 梁兴胜
    @Bind(R.id.lv_tech_question)
    PullToRefreshListView lvTechQuestion;
    //问题列表adapter 梁兴胜
    TechQuestionListAdapter questionListAdapter;
    //问题列表数据
    private List questionList;
    private boolean imageIsUp = false;//箭头是否向上
    private PopupWindow mPopupWindow;
    private ListView lfetlv;
    private ListView rightlv;

    private SelectQuestionTypeAdapter quesTypeAdapter;
    private List<QuestionTypeBean.ResultBean> parentType;
    private List<QuestionTypeBean.ResultBean> timeType = new ArrayList<>();
    private List<QuestionTypeBean.ResultBean> childType = new ArrayList<>();
    private SelectQuestionTypeAdapter childAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_tecquestion;
    }

    @Override
    protected void initData() {
        super.initData();
        init();
        //获取选择的popupwindow
        creatpop();
        //获取分类的父类型
        getType(0);
        setListener();
    }

    /**
     * 点击和刷新事件
     * @author: liangxingsheng
     * @date: 2017/4/11 上午9:41
     */
    private void setListener() {
        quesTypeAdapter.setSelectTypeListener(this);
        childAdapter.setSelectTypeListener(this);
        lvTechQuestion.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(TecQuestionActivity.this, TechQuestionContentActivity.class);
                startActivity(intent);
            }
        });
        lvTechQuestion.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });
    }

    /**
     * 初始化
     * @author: liangxingsheng
     * @date: 2017/4/11 上午9:41
     */
    private void init(){

        if (questionListAdapter == null){
            questionListAdapter = new TechQuestionListAdapter(this, questionList);
            lvTechQuestion.setAdapter(questionListAdapter);
        }
        questionListAdapter.notifyDataSetChanged();
    }

    /**
     * author:范博文
     * date:2017/4/7 15:29
     * des:adapter 点击监听事件，当adapter点击时在activity 做申请数据操作
     *
     * @param size     adapter 中 item数量
     * @param position 点击位置
     * @param isParent 是否是父adapter
     *                 return:null
     */
    @Override
    public void itemSelect(int size, int position, boolean isParent) {
        Log.e(TAG, "点击了吗");
        //如果是父adapter 走父adapter点击事件，否则走子adapter点击事件
        if (isParent) {
            //如果等于2即按时间排序，不做申请二级类型操作,如果不是就申请二级类型
            if (size == 2) {
                if (timeType.get(position).getName().contains("回复")) {
                    paixu.setText("按回复时间");
                } else {
                    paixu.setText("按发布时间");
                }
                mPopupWindow.dismiss();
            } else {
                //如果点击位置与一级类型相同，那么为其他，直接申请数据
                if (position == size - 1) {
                    mPopupWindow.dismiss();
                    fenLei.setText(parentType.get(position).getName());
                } else {
                    getType(parentType.get(position).getId());
                }
            }

        } else {
            //二级筛选
            childType.get(position).getId();
            fenLei.setText(childType.get(position).getName());
            mPopupWindow.dismiss();
        }

    }

    @OnClick({R.id.allOfCountry, R.id.allOfSort, R.id.sortOfTime})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.allOfCountry:
                break;
            //按分类筛选
            case R.id.allOfSort:
                //给箭头设置事件
                change(typeImage);
                setData(view);
                break;
            //按时间排序
            case R.id.sortOfTime:
                change(tiemImage);
                setData(view);
                break;
        }
    }

    /**
     * author:范博文
     * date:2017/4/7 13:34
     * des: 控制箭头向上或向下
     *
     * @param imageView 哪个类的image
     *                  return: null
     */
    private void change(final ImageView imageView) {
        if (imageIsUp) {
            imageView.setBackgroundDrawable(getResources().getDrawable(R.drawable.xia));
            imageIsUp = false;
        } else {
            imageView.setBackgroundDrawable(getResources().getDrawable(R.drawable.lvshagn));
            imageIsUp = true;
        }
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                imageView.setBackgroundDrawable(getResources().getDrawable(R.drawable.xia));

                childType.clear();
                imageIsUp = false;
            }
        });
    }

    /**
     * author:范博文
     * date:2017/4/7 13:37
     * des: 选择的popupwindow
     * return:null
     */
    private void creatpop() {

        View view = LayoutInflater.from(TecQuestionActivity.this).inflate(R.layout
                .popup_tecquestion, null, false);
        mPopupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT, true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(false);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        //左listview
        lfetlv = (ListView) view.findViewById(R.id.popquestion_head);
        //右listview
        rightlv = (ListView) view.findViewById(R.id.popquestion_second);
        quesTypeAdapter = new SelectQuestionTypeAdapter
                (TecQuestionActivity.this, true);
        //子类type的adapter
        childAdapter = new SelectQuestionTypeAdapter(TecQuestionActivity.this, false);
        lfetlv.setAdapter(quesTypeAdapter);
        rightlv.setAdapter(childAdapter);
        //创建按时间排序list
        QuestionTypeBean.ResultBean typeBean = new QuestionTypeBean.ResultBean();
        typeBean.setName("按回复时间排序");
        timeType.add(typeBean);
        QuestionTypeBean.ResultBean typeBean1 = new QuestionTypeBean.ResultBean();
        typeBean1.setName("按发布时间排序");
        timeType.add(typeBean1);

    }

    /**
     * author:范博文
     * date:2017/4/7 14:39
     * des: 根据点击的item 设置type数据
     *
     * @param view 点击的条目
     *             return:null
     */
    private void setData(View view) {
        switch (view.getId()) {
            case R.id.allOfSort:
                quesTypeAdapter.setData(parentType);
                rightlv.setVisibility(View.VISIBLE);
                mPopupWindow.showAsDropDown(view);
                break;
            case R.id.sortOfTime:
                rightlv.setVisibility(View.GONE);
                quesTypeAdapter.setData(timeType);
                mPopupWindow.showAsDropDown(view);
                break;
        }
    }

    /**
     * author:范博文
     * date:2017/4/7 15:34
     * des:根据一级点击条目请求二级条目
     *
     * @param parendid 点击的一级条目
     *                 return:null
     */
    public void getType(final int parendid) {

        OkHttpUtils.get()
                .url(Constants.QUESTION_TYPE)
                .addParams("parentId", parendid + "")
                .build()
                .execute(new StringCallback() {

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "getSecondType error" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG, response);
                        QuestionTypeBean questionTypeBean = JsonUtil.parseJsonToBean(response,
                                QuestionTypeBean.class);
                        if (parendid == 0) {
                            parentType = questionTypeBean.getResult();
                        } else {
                            childType = questionTypeBean.getResult();
                            childAdapter.setData(childType);
                        }
                    }
                });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            imageIsUp = false;
            mPopupWindow.dismiss();
            return true;
        }
        return super.dispatchTouchEvent(ev);
    }

}
