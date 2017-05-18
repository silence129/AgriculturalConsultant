package cn.org.nercita.agriculturalconsultant.main.service.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import cn.org.nercita.agriculturalconsultant.Constants;
import cn.org.nercita.agriculturalconsultant.R;
import cn.org.nercita.agriculturalconsultant.base.BaseActivity;
import cn.org.nercita.agriculturalconsultant.common.WebViewActivity;
import cn.org.nercita.agriculturalconsultant.main.bean.TechRulesListBean;
import cn.org.nercita.agriculturalconsultant.main.service.adapter.FarmVideoAdapter;
import cn.org.nercita.agriculturalconsultant.utils.JsonUtil;
import cn.org.nercita.agriculturalconsultant.view.TitleBar;
import okhttp3.Call;

/**
 * Created by 梁兴胜 on 2017/4/7.
 * 技术规章首页
 */

public class TechRulesActivity extends BaseActivity {
    private static final String TAG = "TechRulesActivity";
    //标题栏
    @Bind(R.id.title_tech_rules)
    TitleBar mTitle;
    //搜索框
    @Bind(R.id.et_search_tech_rules)
    EditText etSearchTechRules;
    //搜索区域
    @Bind(R.id.ll_search_tech_rules)
    LinearLayout llSearchTechRules;
    //筛选词tv0
    @Bind(R.id.tv_word0_tech_rules)
    TextView tvWord0TechRules;
    //筛选词点击0
    @Bind(R.id.word0_click_tech_rules)
    LinearLayout word0ClickTechRules;
    //筛选词tv1
    @Bind(R.id.tv_word1_tech_rules)
    TextView tvWord1TechRules;
    //筛选词点击1
    @Bind(R.id.word1_click_tech_rules)
    LinearLayout word1ClickTechRules;
    //筛选词tv2
    @Bind(R.id.tv_word2_tech_rules)
    TextView tvWord2TechRules;
    //筛选词点击2
    @Bind(R.id.word2_click_tech_rules)
    LinearLayout word2ClickTechRules;
    //视频列表
    @Bind(R.id.gv_tech_rules)
    PullToRefreshGridView mGridView;
    //搜索词
    private String searchKeyWord = "";
    //数据
    private List<TechRulesListBean.ResourceBean.ContentBean> mList = new ArrayList<>();
    //Adapter适配器
    private FarmVideoAdapter mAdapter;
    //页数
    private int PageNo = 1;
    //筛选菜单
    private PopupWindow popupWindow;
    //筛选菜单
    private ListView popListView;
    //用来区分是点的哪类筛选词
    private int searchType = 0;
    //类筛选词
    private List<String> searchWordList0 = new ArrayList<>();
    //类筛选词
    private List<String> searchWordList1 = new ArrayList<>();
    //类筛选词
    private List<String> searchWordList2 = new ArrayList<>();
    //类筛选词
    private String searchWord0 = "";
    //类筛选词
    private String searchWord1 = "";
    //类筛选词
    private String searchWord2 = "";


    @Override
    protected int getContentView() {
        return R.layout.activity_tect_rules;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        init();
    }

    @Override
    protected void initData() {
        super.initData();
        getData(true);
    }

    /**
     * 初始化控件
     * @author: liangxingsheng
     * @date: 2017/4/7 上午9:39
     * @modified_by liangxingsheng
     * @modified_date 2017/4/13 下午5:10
     * @modified 数据请求和跳转
     */
    private void init() {

        mTitle.setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
//        mTitle.setSearch(View.VISIBLE);
        mTitle.setSearchLinstener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (llSearchTechRules.getVisibility() == View.GONE){
                    llSearchTechRules.setVisibility(View.VISIBLE);
                    searchKeyWord = etSearchTechRules.getText().toString().trim();
                }else {
                    llSearchTechRules.setVisibility(View.GONE);
                    searchKeyWord = "";
                }

            }
        });
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(TechRulesActivity.this, WebViewActivity.class);
                String url = Constants.BaseUrL + Constants.SERVICE_TECH_RULES_H5 + "?id=" + mList.get(i).getId();
                intent.putExtra("url",url);
                startActivity(intent);
            }
        });
        mGridView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
                getData(true);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
                PageNo ++;
                getData(false);
            }
        });
    }


    /**
     * 数据获取
     * @author: liangxingsheng
     * @date: 2017/4/7 上午9:39
     * @modified_by liangxingsheng
     * @modified_date 2017/4/13 下午5:10
     * @modified 数据请求和跳转
     */
    private void getData(final boolean isNew) {

        OkHttpUtils.post()
                .url(Constants.BaseUrL + Constants.SERVICE_TECH_RULES)
                .addParams("currentPage", PageNo+"")
                .build()
                .execute(new StringCallback() {

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        PageNo --;
                        if (mGridView != null)
                            mGridView.onRefreshComplete();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG, response);
                        if (mGridView != null)
                            mGridView.onRefreshComplete();
                        TechRulesListBean data = JsonUtil.parseJsonToBean(response, TechRulesListBean.class);
                        if (data == null || data.getResource().getContent().size() == 0){
                            return;
                        }
                        if (isNew){
                            mList.clear();
                        }
                        mList.addAll(data.getResource().getContent());
                        if (mAdapter == null){
                            mAdapter = new FarmVideoAdapter(TechRulesActivity.this, mList);
                            mGridView.setAdapter(mAdapter);
                        }
                        mAdapter.notifyDataSetChanged();
                    }
                });
    }

    /**
     * 初始化筛选菜单
     *
     * @author: liangxingsheng
     * @date: 2017/4/7 上午9:39
     */
    private void initPop() {

        popupWindow = new PopupWindow(-1, -2);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        popupWindow.setFocusable(true);
        View popView = LayoutInflater.from(this).inflate(R.layout.listview_popup, null);
        popListView = (ListView) popView.findViewById(R.id.lv_popup);
        popupWindow.setContentView(popView);
        popListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (searchType) {
                    case 0:
//                        unitWord = ((TextView) view).getText().toString();
//                        categoryWord = "";
//                        jobWord = "";
//                        searchYuansuo.setText(unitWord);
//                        searchZhuanye.setText("专业");
//                        searchZhicheng.setText("职称");
                        break;
                    case 1:
//                        unitWord = "";
//                        categoryWord = ((TextView) view).getText().toString();
//                        jobWord = "";
//                        searchYuansuo.setText("院所");
//                        searchZhuanye.setText(categoryWord);
//                        searchZhicheng.setText("职称");
                        break;
                    case 2:
//                        unitWord = "";
//                        categoryWord = "";
//                        jobWord = ((TextView) view).getText().toString();
//                        searchYuansuo.setText("院所");
//                        searchZhuanye.setText("专业");
//                        searchZhicheng.setText(jobWord);
                        break;
                }
                PageNo = 1;
                getData(true);
                popupWindow.dismiss();
            }
        });
    }

    /**
     * 点击事件
     * @param view
     * @author: liangxingsheng
     * @date: 2017/4/7 上午9:39
     */
    @OnClick({R.id.word0_click_tech_rules, R.id.word1_click_tech_rules, R.id.word2_click_tech_rules})
    public void onViewClicked(View view) {
        if (popupWindow == null) {
            initPop();
        }
        switch (view.getId()) {
            case R.id.word0_click_tech_rules:
                searchType = 0;
                searchWordList0 = new ArrayList<String>() {
                };
                searchWordList0.add("萝卜");
                searchWordList0.add("白菜");
                searchWordList0.add("玉米");
                popListView.setAdapter(new ArrayAdapter<String>(this, R.layout.item_popup, searchWordList0));
                break;
            case R.id.word1_click_tech_rules:
                searchType = 1;
                searchWordList1 = new ArrayList<String>() {
                };
                searchWordList1.add("萝卜");
                searchWordList1.add("白菜");
                searchWordList1.add("玉米");
                popListView.setAdapter(new ArrayAdapter<String>(this, R.layout.item_popup, searchWordList1));
                break;
            case R.id.word2_click_tech_rules:
                searchType = 2;
                searchWordList2 = new ArrayList<String>() {
                };
                searchWordList2.add("萝卜");
                searchWordList2.add("白菜");
                searchWordList2.add("玉米");
                popListView.setAdapter(new ArrayAdapter<String>(this, R.layout.item_popup, searchWordList2));
                break;
        }

        if (popupWindow.isShowing()) {
            popupWindow.dismiss();
        } else {
            popupWindow.showAsDropDown(word0ClickTechRules);
        }
    }
}
