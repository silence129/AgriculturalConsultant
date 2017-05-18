package cn.org.nercita.agriculturalconsultant.main.home.activity;

import android.graphics.Color;
import android.os.Build;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import cn.org.nercita.agriculturalconsultant.Constants;
import cn.org.nercita.agriculturalconsultant.R;
import cn.org.nercita.agriculturalconsultant.base.BaseActivity;
import cn.org.nercita.agriculturalconsultant.main.home.bean.HeWeatherBean;
import cn.org.nercita.agriculturalconsultant.utils.JsonUtil;
import cn.org.nercita.agriculturalconsultant.utils.SPUtil;
import cn.org.nercita.agriculturalconsultant.utils.ToastUtil;
import cn.org.nercita.agriculturalconsultant.view.address.AddressDialog;
import cn.org.nercita.agriculturalconsultant.view.address.AddressDialogEngine;
import okhttp3.Call;

import static cn.org.nercita.agriculturalconsultant.R.id.tv_address;

/**
 * Created by 梁兴胜 on 2017/4/10.
 * 天气预报界面
 */

public class ForecastActivity extends BaseActivity implements AddressDialogEngine.OnSaveItemClickListener{

    //返回
    @Bind(R.id.iv_back)
    ImageView ivBack;
    //定位
    @Bind(tv_address)
    TextView tvAddress;
    //刷新
    @Bind(R.id.home_refresh)
    SwipeRefreshLayout refresh;
    //天气背景
    @Bind(R.id.bing_pic_img)
    ImageView bingPicImg;
    //天气大图
    @Bind(R.id.image_weather)
    ImageView imageWeather;
    //温度大
    @Bind(R.id.temp_weather)
    TextView tempWeather;
    //风力大
    @Bind(R.id.wind_weather)
    TextView windWeather;
    //湿度大
    @Bind(R.id.hum_weather)
    TextView humWeather;
    //日期1
    @Bind(R.id.day1)
    TextView day1;
    //天气图片1
    @Bind(R.id.image_day1)
    ImageView imageDay1;
    //高温1
    @Bind(R.id.tem_max_day1)
    TextView temMaxDay1;
    //低温1
    @Bind(R.id.tem_min_day1)
    TextView temMinDay1;
    //天气状况1
    @Bind(R.id.cond_day1)
    TextView condDay1;
    //日期2
    @Bind(R.id.day2)
    TextView day2;
    //天气图片2
    @Bind(R.id.image_day2)
    ImageView imageDay2;
    //高温2
    @Bind(R.id.tem_max_day2)
    TextView temMaxDay2;
    //低温2
    @Bind(R.id.tem_min_day2)
    TextView temMinDay2;
    //天气状况2
    @Bind(R.id.cond_day2)
    TextView condDay2;
    //日期3
    @Bind(R.id.day3)
    TextView day3;
    //天气图片3
    @Bind(R.id.image_day3)
    ImageView imageDay3;
    //高温3
    @Bind(R.id.tem_max_day3)
    TextView temMaxDay3;
    //低温3
    @Bind(R.id.tem_min_day3)
    TextView temMinDay3;
    //天气状况3
    @Bind(R.id.cond_day3)
    TextView condDay3;
    //    //当前天气数据
//    HeWeatherBean.HeWeather5Bean.BasicBean nowWeather;
    //天气预报数据
    List<HeWeatherBean.HeWeather5Bean.DailyForecastBean> forecastList;
    //地址选择
    private AddressDialog addressDialog;
    private String province;
    private String city;
    private String county;
    private String town;
    private String citycode;
    private String weatherAddress = "beijing";

    @Override
    protected int getContentView() {
        return R.layout.activity_forecast;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        initRefresh();
//        loadBGPic();
        getHeWeather();
        addressDialog = new AddressDialog(ForecastActivity.this);
//        addressDialog.setOnSaveItemClickListener(this);

        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }


    /**
     * @author: liangxingsheng
     * @date: 2017/4/10 下午3:55
     * @des: 下拉刷新
     */
    private void initRefresh() {

        refresh.setColorSchemeColors(getResources().getColor(R.color.title_green));
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getHeWeather();
            }
        });
    }


    /**
     * @author: liangxingsheng
     * @date: 2017/4/10 下午4:25
     * @des: 天气背景
     */
    private void loadBGPic() {

        String requestBingPic = "http://guolin.tech/api/bing_pic";
        OkHttpUtils.get()
                .url(requestBingPic)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (TextUtils.isEmpty(response)) return;
                        Glide.with(ForecastActivity.this).load(response).into(bingPicImg);
                    }
                });
    }


    /**
     * 获取天气预报数据
     *
     * @author: liangxingsheng
     * @date: 2017/4/10 下午3:00
     */
    private void getHeWeather() {

        OkHttpUtils.get()
                .url(Constants.HE_WEATHER)
                .addParams("city", weatherAddress)
                .addParams("key", Constants.HE_WEATHER_KEY)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        ToastUtil.showShort(ForecastActivity.this, "获取天气失败");
                        refresh.setRefreshing(false);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        refresh.setRefreshing(false);
                        HeWeatherBean heWeather = JsonUtil.parseJsonToBean(response, HeWeatherBean.class);
                        if (heWeather == null || heWeather.getHeWeather5() == null || heWeather.getHeWeather5().size() == 0) {
                            ToastUtil.showShort(ForecastActivity.this, "获取天气失败");
                            return;
                        }
                        forecastList = heWeather.getHeWeather5().get(0).getDaily_forecast();
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

        if (forecastList == null || forecastList.size() <3) return;
        //大天气
        HeWeatherBean.HeWeather5Bean.DailyForecastBean day1Bean = forecastList.get(0);
        HeWeatherBean.HeWeather5Bean.DailyForecastBean day2Bean = forecastList.get(1);
        HeWeatherBean.HeWeather5Bean.DailyForecastBean day3Bean = forecastList.get(2);
//        tempWeather.setText(day1Bean.get);
        //天气1
//        day1.setText("");
        temMaxDay1.setText(day1Bean.getTmp().getMax()+"℃");
        temMinDay1.setText(day1Bean.getTmp().getMin()+"℃");
        condDay1.setText(day1Bean.getCond().getTxt_d());
        //天气2
//        day2.setText(day2Bean.getDate());
        temMaxDay2.setText(day2Bean.getTmp().getMax()+"℃");
        temMinDay2.setText(day2Bean.getTmp().getMin()+"℃");
        condDay2.setText(day2Bean.getCond().getTxt_d());
        //天气3
//        day3.setText("");
        temMaxDay3.setText(day3Bean.getTmp().getMax()+"℃");
        temMinDay3.setText(day3Bean.getTmp().getMin()+"℃");
        condDay3.setText(day3Bean.getCond().getTxt_d());
        /** 梁兴胜 根据天气切换天气图片 2017/4/14 下午5:00 **/
        imageDay1.setImageResource(getResources().getIdentifier("a"+day1Bean.getCond().getCode_d(), "drawable", getApplicationInfo().packageName));
        imageDay2.setImageResource(getResources().getIdentifier("a"+day2Bean.getCond().getCode_d(), "drawable", getApplicationInfo().packageName));
        imageDay3.setImageResource(getResources().getIdentifier("a"+day3Bean.getCond().getCode_d(), "drawable", getApplicationInfo().packageName));
    }

    /**
     * 点击事件
     *
     * @param view
     * @author: liangxingsheng
     * @date: 2017/4/10 下午2:53
     */
    @OnClick({R.id.iv_back, tv_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case tv_address:
                //选择地址
                addressDialog.showAddressDialog(getApplicationContext());
                break;
        }
    }


    @Override
    public void onSaveItemClick(String province, String city, String county, String town) {
        this.province = province;
        this.city = city;
        this.county = county;
        this.town = town;
        weatherAddress = county;
        if (TextUtils.isEmpty(county)) {
            weatherAddress = city;
        }
        SPUtil.putString(this, Constants.WEATHER_ADDRESS, weatherAddress);
        tvAddress.setText(weatherAddress);
        addressDialog.dismiss();
        getHeWeather();
    }
}
