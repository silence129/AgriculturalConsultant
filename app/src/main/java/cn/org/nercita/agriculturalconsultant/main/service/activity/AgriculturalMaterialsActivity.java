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
import cn.org.nercita.agriculturalconsultant.common.WebViewActivity;
import cn.org.nercita.agriculturalconsultant.main.bean.NongziListBean;
import cn.org.nercita.agriculturalconsultant.main.service.adapter.AgriculturalMaterialsAdapter;
import cn.org.nercita.agriculturalconsultant.utils.JsonUtil;
import cn.org.nercita.agriculturalconsultant.view.TitleBar;
import okhttp3.Call;

/**
 * Created by 梁兴胜 on 2017/4/7.
 * 农资查询首页
 */

public class AgriculturalMaterialsActivity extends BaseActivity {
    private static final String TAG = "AgriculturalMaterialsAc";
    //标题
    @Bind(R.id.title_nongzi)
    TitleBar mTitle;
    //搜索框
    @Bind(R.id.et_search_nongzi)
    EditText etSearchNongzi;
    //搜索键
    @Bind(R.id.search)
    TextView search;
    //搜索区域
    @Bind(R.id.ll_search_nongzi)
    LinearLayout llSearchNongzi;
    //筛选词tv0
    @Bind(R.id.tv_word0_nongzi)
    TextView tvWord0Nongzi;
    //筛选词点击0
    @Bind(R.id.word0_click_nongzi)
    LinearLayout word0ClickNongzi;
    //筛选词tv1
    @Bind(R.id.tv_word1_nongzi)
    TextView tvWord1Nongzi;
    //筛选词点击1
    @Bind(R.id.word1_click_nongzi)
    LinearLayout word1ClickNongzi;
    //筛选词tv2
    @Bind(R.id.tv_word2_nongzi)
    TextView tvWord2Nongzi;
    //筛选词点击2
    @Bind(R.id.word2_click_nongzi)
    LinearLayout word2ClickNongzi;
    //农资列表
    @Bind(R.id.lv_nongzi)
    PullToRefreshListView mListview;
    //搜索词
    private String searchKeyWord = "";
    //数据
    private List<NongziListBean.ContentBean> mList = new ArrayList<>();
    //Adapter适配器
    private AgriculturalMaterialsAdapter mAdapter;
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
        return R.layout.activity_agricultural_materials;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        init();
    }

    /**
     * 初始化控件
     * @author: liangxingsheng
     * @date: 2017/4/6 下午2:26
     */
    @Override
    protected void initData() {
        super.initData();
        getData(true);
    }

    /**
     * 初始化控件
     * @author: liangxingsheng
     * @date: 2017/4/6 下午2:26
     */
    private void init() {

        mTitle.setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mTitle.setSearch(View.VISIBLE);
        mTitle.setSearchLinstener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (llSearchNongzi.getVisibility() == View.VISIBLE){
                    llSearchNongzi.setVisibility(View.GONE);
                    searchKeyWord = "";
                }else {
                    llSearchNongzi.setVisibility(View.VISIBLE);
                    searchKeyWord = etSearchNongzi.getText().toString().trim();
                }
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PageNo = 1;
                searchKeyWord = etSearchNongzi.getText().toString().trim();
                getData(true);
            }
        });
        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(AgriculturalMaterialsActivity.this, WebViewActivity.class);
                intent.putExtra("url", mList.get(i-1).getUrl());
                startActivity(intent);
            }
        });

        mListview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                PageNo = 1;
                getData(true);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                PageNo ++;
                getData(false);
            }
        });
    }

    /**
     * 数据获取及筛选
     *
     * @author: liangxingsheng
     * @date: 2017/4/14 上午10:08
     */
    private void getData(final boolean isNew) {

        OkHttpUtils.post()
                .url(Constants.BaseUrL + Constants.NONGZI_LIST)
                .addParams("currentPage", PageNo + "")
                //查询条件，可以没有
                .addParams("title", searchKeyWord)
                .build()
                .execute(new StringCallback() {

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        PageNo--;
                        Log.e(TAG, e.toString());
                        if (mListview != null)
                            mListview.onRefreshComplete();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG, response);
                        if (mListview != null)
                            mListview.onRefreshComplete();
                        NongziListBean data = JsonUtil.parseJsonToBean(response, NongziListBean.class);
                        if (data == null || data.getContent().size() == 0) {
                            return;
                        }
                        if (isNew) {
                            mList.clear();
                        }
                        mList.addAll(data.getContent());
                        if (mAdapter == null) {
                            mAdapter = new AgriculturalMaterialsAdapter(AgriculturalMaterialsActivity.this, mList);
                            mListview.setAdapter(mAdapter);
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
     * @author: liangxingsheng
     * @date: 2017/4/7 下午1:55
     * @param view
     */
    @OnClick({R.id.word0_click_nongzi, R.id.word1_click_nongzi, R.id.word2_click_nongzi})
    public void onViewClicked(View view) {

        if (popupWindow == null) {
            initPop();
        }
        switch (view.getId()) {
            case R.id.word0_click_nongzi:
                searchType = 0;
                searchWordList0 = new ArrayList<String>() {
                };
                searchWordList0.add("萝卜");
                searchWordList0.add("白菜");
                searchWordList0.add("玉米");
                popListView.setAdapter(new ArrayAdapter<String>(this, R.layout.item_popup, searchWordList0));
                break;
            case R.id.word1_click_nongzi:
                searchType = 1;
                searchWordList1 = new ArrayList<String>() {
                };
                searchWordList1.add("萝卜");
                searchWordList1.add("白菜");
                searchWordList1.add("玉米");
                popListView.setAdapter(new ArrayAdapter<String>(this, R.layout.item_popup, searchWordList1));
                break;
            case R.id.word2_click_nongzi:
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
            popupWindow.showAsDropDown(word0ClickNongzi);
        }
    }

}
