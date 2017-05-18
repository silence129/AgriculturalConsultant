package cn.org.nercita.agriculturalconsultant.main.service.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import cn.org.nercita.agriculturalconsultant.R;
import cn.org.nercita.agriculturalconsultant.base.BaseActivity;
import cn.org.nercita.agriculturalconsultant.main.service.adapter.MarketPriceAdapter;
import cn.org.nercita.agriculturalconsultant.view.TitleBar;

/**
 * Created by 梁兴胜 on 2017/4/6.
 * 市场价格界面
 */

public class MarketPriceActivity extends BaseActivity {

    //标题栏
    @Bind(R.id.title_market_price)
    TitleBar mTitle;
    //列表ListView
    @Bind(R.id.lv_market_price)
    PullToRefreshListView mListview;
    //筛选词tv0
    @Bind(R.id.tv_word0_price)
    TextView tvWord0Price;
    //筛选词点击0
    @Bind(R.id.word0_click_price)
    LinearLayout word0ClickPrice;
    //筛选词tv1
    @Bind(R.id.tv_word1_price)
    TextView tvWord1Price;
    //筛选词点击1
    @Bind(R.id.word1_click_price)
    LinearLayout word1ClickPrice;
    //筛选词tv2
    @Bind(R.id.tv_word2_price)
    TextView tvWord2Price;
    //筛选词点击2
    @Bind(R.id.word2_click_price)
    LinearLayout word2ClickPrice;
    //搜索框
    @Bind(R.id.et_search_price)
    EditText etSearchPrice;
    //搜索布局
    @Bind(R.id.ll_search_price)
    LinearLayout llSearchPrice;
    //搜索词
    private String searchKeyWord = "";
    //数据
    private List mList;
    //Adapter适配器
    private MarketPriceAdapter mAdapter;
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
        return R.layout.activity_market_price;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        init();
    }

    @Override
    protected void initData() {
        super.initData();
        getData();
    }

    /**
     * 初始化控件
     *
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
                if (llSearchPrice.getVisibility() == View.GONE){
                    llSearchPrice.setVisibility(View.VISIBLE);
                    searchKeyWord = etSearchPrice.getText().toString().trim();
                }else {
                    llSearchPrice.setVisibility(View.GONE);
                    searchKeyWord = "";
                }

            }
        });
        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
        mListview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });
    }

    /**
     * 数据获取
     *
     * @author: liangxingsheng
     * @date: 2017/4/6 下午2:26
     */
    private void getData() {

        if (mAdapter == null) {
            mAdapter = new MarketPriceAdapter(this, mList);
            mListview.setAdapter(mAdapter);
        }
        mAdapter.notifyDataSetChanged();
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
                getData();
                popupWindow.dismiss();
            }
        });
    }

    /**
     * 点击事件
     *
     * @param view
     * @author: liangxingsheng
     * @date: 2017/4/7 上午9:39
     */
    @OnClick({R.id.word0_click_price, R.id.word1_click_price, R.id.word2_click_price})
    public void onViewClicked(View view) {

        if (popupWindow == null) {
            initPop();
        }
        switch (view.getId()) {
            case R.id.word0_click_price:
                searchType = 0;
                searchWordList0 = new ArrayList<String>() {
                };
                searchWordList0.add("萝卜");
                searchWordList0.add("白菜");
                searchWordList0.add("玉米");
                popListView.setAdapter(new ArrayAdapter<String>(this, R.layout.item_popup, searchWordList0));
                break;
            case R.id.word1_click_price:
                searchType = 1;
                searchWordList1 = new ArrayList<String>() {
                };
                searchWordList1.add("萝卜");
                searchWordList1.add("白菜");
                searchWordList1.add("玉米");
                popListView.setAdapter(new ArrayAdapter<String>(this, R.layout.item_popup, searchWordList1));
                break;
            case R.id.word2_click_price:
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
            popupWindow.showAsDropDown(word0ClickPrice);
        }
    }

}
