package cn.org.nercita.agriculturalconsultant.main.home;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import cn.org.nercita.agriculturalconsultant.Constants;
import cn.org.nercita.agriculturalconsultant.R;
import cn.org.nercita.agriculturalconsultant.base.BaseFragment;
import cn.org.nercita.agriculturalconsultant.main.bean.HomeBean;
import cn.org.nercita.agriculturalconsultant.main.home.activity.ForecastActivity;
import cn.org.nercita.agriculturalconsultant.main.home.activity.LatestPriceActivity;
import cn.org.nercita.agriculturalconsultant.main.home.activity.LawsActivity;
import cn.org.nercita.agriculturalconsultant.main.home.bean.HeWeatherBean;
import cn.org.nercita.agriculturalconsultant.utils.DateUtil;
import cn.org.nercita.agriculturalconsultant.utils.JsonUtil;
import cn.org.nercita.agriculturalconsultant.utils.ToastUtil;
import cn.org.nercita.agriculturalconsultant.view.TitleBar;
import okhttp3.Call;


/**
 * Created by 梁兴胜 on 2017/4/5.
 * 首页
 */

public class HomeFragment extends BaseFragment {

    //标题
    @Bind(R.id.title_home)
    TitleBar mTitle;
    //下拉刷新
    @Bind(R.id.home_refresh)
    SwipeRefreshLayout refresh;
    //现在气温
    @Bind(R.id.temp_home_now)
    TextView tempHomeNow;
    //温度范围
    @Bind(R.id.tmp_home)
    TextView tmpHome;
    //风
    @Bind(R.id.wind_home)
    TextView windHome;
    //降雨量
    @Bind(R.id.rain_home)
    TextView rainHome;
    //地址
    @Bind(R.id.location_home)
    TextView locationHome;
    //天气日历
    @Bind(R.id.calendar_home)
    TextView calendarHome;
    //整个天气布局
    @Bind(R.id.weather_home)
    LinearLayout weatherHome;
    //天气预报数据
    HeWeatherBean.HeWeather5Bean.NowBean nowWeather;
    //新闻图片1
    @Bind(R.id.preview_image_news1)
    ImageView previewImageNews1;
    //新闻标题1
    @Bind(R.id.title_news1)
    TextView titleNews1;
    //新闻时间1
    @Bind(R.id.time_news1)
    TextView timeNews1;
    //新闻来源1
    @Bind(R.id.source_news1)
    TextView sourceNews1;
    //新闻布局1
    @Bind(R.id.rl_news_1)
    RelativeLayout rlNews1;
    //新闻图片2
    @Bind(R.id.preview_image_news2)
    ImageView previewImageNews2;
    //新闻标题2
    @Bind(R.id.title_news2)
    TextView titleNews2;
    //新闻时间2
    @Bind(R.id.time_news2)
    TextView timeNews2;
    //新闻来源2
    @Bind(R.id.source_news2)
    TextView sourceNews2;
    //新闻2
    @Bind(R.id.rl_news_2)
    RelativeLayout rlNews2;
    //价格1图片
    @Bind(R.id.image_price_newest1)
    ImageView imagePriceNewest1;
    //价格1名字
    @Bind(R.id.name_price1)
    TextView namePrice1;
    //价格1时间
    @Bind(R.id.time_price1)
    TextView timePrice1;
    //价格1市场
    @Bind(R.id.market_price1)
    TextView marketPrice1;
    //价格1价格
    @Bind(R.id.price_price1)
    TextView pricePrice1;
    //价格1布局
    @Bind(R.id.rl_price_1)
    RelativeLayout rlPrice1;
    //价格2图片
    @Bind(R.id.image_price_newest2)
    ImageView imagePriceNewest2;
    //价格2名称
    @Bind(R.id.name_price2)
    TextView namePrice2;
    //价格2时间
    @Bind(R.id.time_price2)
    TextView timePrice2;
    //价格2市场
    @Bind(R.id.market_price2)
    TextView marketPrice2;
    //价格2价格
    @Bind(R.id.price_price2)
    TextView pricePrice2;
    //价格2布局
    @Bind(R.id.rl_price_2)
    RelativeLayout rlPrice2;
    //新闻数据
    private List<HomeBean.LawsBean> newsList = new ArrayList<>();
    //价格数据
    private List<HomeBean.PriceBean> priceList = new ArrayList<>();
    private DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        init();

    }

    /**
     * @author: liangxingsheng
     * @date: 2017/4/10 上午10:12
     * @des: 下拉刷新
     */
    private void initRefresh() {

        refresh.setColorSchemeColors(getResources().getColor(R.color.title_green));
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
            }
        });
    }

    /**
     * @author: liangxingsheng
     * @date: 2017/4/10 上午10:12
     * @des: 初始化控件和数据
     */
    private void init() {
        initRefresh();
        getHeWeather();
        getData();
    }

    /**
     * 获取天气预报数据
     *
     * @author: liangxingsheng
     * @date: 2017/4/10 下午3:00
     */
    private void getHeWeather() {

        OkHttpUtils.get()
                .url(Constants.HE_WEATHER_NOW)
                .addParams("city", "beijing")
                .addParams("key", Constants.HE_WEATHER_KEY)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        ToastUtil.showShort(getActivity(), "获取天气失败");
                        refresh.setRefreshing(false);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        refresh.setRefreshing(false);
                        HeWeatherBean heWeather = JsonUtil.parseJsonToBean(response, HeWeatherBean.class);
                        if (heWeather == null || heWeather.getHeWeather5() == null || heWeather.getHeWeather5().size() == 0) {
                            ToastUtil.showShort(getActivity(), "获取天气失败");
                            return;
                        }
                        nowWeather = heWeather.getHeWeather5().get(0).getNow();
                        setWeatherData();
                    }
                });
    }

    /**
     * 天气预报数据处理
     *
     * @author: liangxingsheng
     * @date: 2017/4/10 下午3:00
     */
    private void setWeatherData() {

        //
        locationHome.setText("北京");
        calendarHome.setText(DateUtil.getChineseDate());
        tmpHome.setText(nowWeather.getTmp() + "℃");
        tempHomeNow.setText(nowWeather.getTmp() + "℃");
        windHome.setText(nowWeather.getWind().getDir() + nowWeather.getWind().getSc() + "级");
        rainHome.setText(" 降水量" + nowWeather.getPcpn());
    }


    /**
     * 获取首页数据
     *
     * @author: liangxingsheng
     * @date: 2017/4/14 下午12:56
     */
    private void getData() {

        OkHttpUtils.get()
                .url(Constants.BaseUrL + Constants.HOME)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
//                        ToastUtil.showShort(getActivity(), "获取失败");
                        if (refresh != null)
                            refresh.setRefreshing(false);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (refresh != null)
                            refresh.setRefreshing(false);
                        HomeBean homeBean = JsonUtil.parseJsonToBean(response, HomeBean.class);
                        newsList.addAll(homeBean.getLaws());
                        priceList.addAll(homeBean.getPrice());
                        setHomeData();
                    }
                });
    }

    /**
     * 天气预报数据处理
     *
     * @author: liangxingsheng
     * @date: 2017/4/14 下午2:00
     */
    private void setHomeData() {
        //
        if (newsList.size() > 1) {
            //            Glide.with(mContext).load(priceList.get(0).get()).placeholder(mContext.getResources()
//                    .getDrawable(R.drawable.pic_default_all))
//                    .transform(new GlideRoundTransform(mContext, 5))
//                    .into(previewImageNews1);
            titleNews1.setText(newsList.get(0).getTitle());
            timeNews1.setText(newsList.get(0).getTime());
            sourceNews1.setText("");
            //            Glide.with(mContext).load(priceList.get(0).get()).placeholder(mContext.getResources()
//                    .getDrawable(R.drawable.pic_default_all))
//                    .transform(new GlideRoundTransform(mContext, 5))
//                    .into(previewImageNews2);
            titleNews2.setText(newsList.get(1).getTitle());
            timeNews2.setText(newsList.get(1).getTime());
            sourceNews2.setText("");
        }
        if (priceList.size() > 1) {
            //            Glide.with(mContext).load(priceList.get(0).get()).placeholder(mContext.getResources()
//                    .getDrawable(R.drawable.pic_default_all))
//                    .transform(new GlideRoundTransform(mContext, 5))
//                    .into(imagePriceNewest1);
            namePrice1.setText(priceList.get(0).getProductName());
            timePrice1.setText(format.format(priceList.get(0).getTime()));
            marketPrice1.setText(priceList.get(0).getMarket());
            pricePrice1.setText(priceList.get(0).getPrice() + priceList.get(0).getPriceUnit());

//            Glide.with(mContext).load(priceList.get(0).get()).placeholder(mContext.getResources()
//                    .getDrawable(R.drawable.pic_default_all))
//                    .transform(new GlideRoundTransform(mContext, 5))
//                    .into(imagePriceNewest2);
            namePrice2.setText(priceList.get(1).getProductName());
            timePrice2.setText(format.format(priceList.get(1).getTime()));
            marketPrice2.setText(priceList.get(1).getMarket());
            pricePrice2.setText(priceList.get(1).getPrice() + priceList.get(1).getPriceUnit());
        }
    }

    /**
     * 添加跳转
     *
     * @author: liangxingsheng
     * @date: 2017/4/13 下午3:00
     */
    @OnClick({R.id.temp_home_now, R.id.weather_home, R.id.more_0, R.id.more_1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.temp_home_now:

                break;
            case R.id.weather_home:
                Intent intent = new Intent(getActivity(), ForecastActivity.class);
                startActivity(intent);
                break;
            case R.id.more_0:
                //最新资讯更多
                startActivity(new Intent(getActivity(), LawsActivity.class));
                break;
            case R.id.more_1:
                //最新价格更多
                startActivity(new Intent(getActivity(), LatestPriceActivity.class));
                break;
            case R.id.rl_news_1:
                //新闻1
                break;
            case R.id.rl_news_2:
                //新闻2
                break;
            case R.id.rl_price_1:
                //价格1
                break;
                //价格2
            case R.id.rl_price_2:
                break;
        }
    }

}
