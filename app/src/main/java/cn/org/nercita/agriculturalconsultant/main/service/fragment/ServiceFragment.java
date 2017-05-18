package cn.org.nercita.agriculturalconsultant.main.service.fragment;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
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
import cn.org.nercita.agriculturalconsultant.common.WebViewActivity;
import cn.org.nercita.agriculturalconsultant.main.home.activity.LatestPriceActivity;
import cn.org.nercita.agriculturalconsultant.main.service.activity.AgriculturalMaterialsActivity;
import cn.org.nercita.agriculturalconsultant.main.service.activity.MarkNewsListActivity;
import cn.org.nercita.agriculturalconsultant.main.service.activity.SupplyActivity;
import cn.org.nercita.agriculturalconsultant.main.service.activity.TechRulesActivity;
import cn.org.nercita.agriculturalconsultant.main.service.bean.ServiceHomeBean;
import cn.org.nercita.agriculturalconsultant.utils.GlideRoundTransform;
import cn.org.nercita.agriculturalconsultant.utils.JsonUtil;
import cn.org.nercita.agriculturalconsultant.view.GlideImageLoader;
import cn.org.nercita.agriculturalconsultant.view.TitleBar;
import okhttp3.Call;


/**
 * Created by 梁兴胜 on 2017/4/6.
 * 服务首页
 */

public class ServiceFragment extends BaseFragment {

    private static final String TAG = "ServiceFragment";
    //标题
    @Bind(R.id.title_service)
    TitleBar mTitle;
    //轮播图
    @Bind(R.id.banner_service)
    Banner banner;
    //技术规程
    @Bind(R.id.tech_rules)
    TextView techRules;
    //农业技术
    @Bind(R.id.agricultural_tech)
    TextView agriculturalTech;
    //市场行情
    @Bind(R.id.shichanghangqing)
    TextView shichanghangqing;
    //农资查询
    @Bind(R.id.nongzichaxun)
    TextView nongzichaxun;
    //视频一预览图
    @Bind(R.id.preview_image_video)
    ImageView previewImageVideo;
    //视频一名字
    @Bind(R.id.title_video_name)
    TextView titleVideoName;
    //供应数量1
    @Bind(R.id.num_supply)
    TextView numSupply;
    //供应地址1
    @Bind(R.id.supply_address)
    TextView supplyAddress;
    //视频一播放
    @Bind(R.id.play_video)
    LinearLayout playVideo;
    //视频二预览图
    @Bind(R.id.preview_image_video1)
    ImageView previewImageVideo1;
    //视频二名字
    @Bind(R.id.title_video_name1)
    TextView titleVideoName1;
    //供应数量2
    @Bind(R.id.num_supply1)
    TextView numSupply1;
    //供应地址2
    @Bind(R.id.supply_address1)
    TextView supplyAddress1;
    //视频二播放
    @Bind(R.id.play_video1)
    LinearLayout playVideo1;
    //下拉刷新
    @Bind(R.id.service_refresh)
    SwipeRefreshLayout refresh;
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
    //轮播图图片
    private List<String> images = new ArrayList<>();
    //轮播图标题
    private List<String> titles = new ArrayList<>();
    //轮播图数据
    private List<ServiceHomeBean.FeatureBean> bannerList = new ArrayList<>();
    //最新供应数据
    private List<ServiceHomeBean.DisplayBalanceBean> supplyList = new ArrayList<>();
    //农业资讯数据
    private List<ServiceHomeBean.MarkNewBean> newsList = new ArrayList<>();
    private DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_service;
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        initRefresh();
    }

    @Override
    protected void initData() {
        super.initData();
        getServiceData();
    }

    /**
     * @author: liangxingsheng
     * @date: 2017/4/7 下午3:12
     * @des: 下拉刷新
     */
    private void initRefresh() {

        refresh.setColorSchemeColors(getResources().getColor(R.color.title_green));
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getServiceData();
            }
        });
    }

    /**
     * @author: liangxingsheng
     * @date: 2017/4/6 下午4:12
     * 设置轮播图
     */
    private void initBanner() {

        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        //设置点击事件
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                ServiceHomeBean.FeatureBean bean = bannerList.get(position);
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
//                String url = Constants.BaseUrL + Constants.SERVICE_NEWS_DETAIL + bean.getId();
//                intent.putExtra("url", url);
                String url = bean.getBuyUrl();
                intent.putExtra("url", url);
                startActivity(intent);
            }
        });
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    /**
     * 点击事件
     *
     * @param view
     * @author: liangxingsheng
     * @date: 2017/4/6 下午4:12
     */
    @OnClick({R.id.tech_rules, R.id.agricultural_tech, R.id.shichanghangqing, R.id.nongzichaxun,
            R.id.play_video, R.id.play_video1, R.id.more_0, R.id.more_1, R.id.rl_news_1, R.id.rl_news_2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tech_rules:
                //技术章程
                startActivity(new Intent(getActivity(), TechRulesActivity.class));
                break;
            case R.id.agricultural_tech:
                //
                break;
            case R.id.shichanghangqing:
                //市场行情
                startActivity(new Intent(getActivity(), LatestPriceActivity.class));
                break;
            case R.id.nongzichaxun:
                //农资查询
                startActivity(new Intent(getActivity(), AgriculturalMaterialsActivity.class));
                break;
            case R.id.rl_news_1:
                //新闻1
                if (newsList.size()>0){
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                String url = Constants.BaseUrL + Constants.SERVICE_NEWS_DETAIL + "?id=" + newsList.get(0).getId();
                intent.putExtra("url",url);
                startActivity(intent);
                }
                break;
            case R.id.rl_news_2:
                //新闻2
                if (newsList.size()>1) {
                    Intent intent1 = new Intent(getActivity(), WebViewActivity.class);
                    String url1 = Constants.BaseUrL + Constants.SERVICE_NEWS_DETAIL + "?id=" + newsList.get(1).getId();
                    intent1.putExtra("url", url1);
                    startActivity(intent1);
                }
                break;
            case R.id.play_video:
                //最新供应1
                if (supplyList.size() > 0) {
                    ServiceHomeBean.DisplayBalanceBean bean = supplyList.get(0);
                    Intent intent = new Intent(getActivity(), WebViewActivity.class);
//                    String url = Constants.BaseUrL23 + Constants.SERVICE_NEWS_DETAIL + bean.getId();
//                    intent.putExtra("url", url);
                    intent.putExtra("url", bean.getUrl());
                    startActivity(intent);
                }
                break;
            case R.id.play_video1:
                //最新供应2
                if (supplyList.size() > 1) {
                    ServiceHomeBean.DisplayBalanceBean bean1 = supplyList.get(1);
                    Intent intent1 = new Intent(getActivity(), WebViewActivity.class);
//                    String url = Constants.BaseUrL23 + Constants.SERVICE_NEWS_DETAIL + bean1.getId();
//                    intent1.putExtra("url", url);
                    intent1.putExtra("url", bean1.getUrl());
                    startActivity(intent1);
                }
                break;
            case R.id.more_0:
                //最新供应更多
                Intent intent = new Intent(getActivity(), SupplyActivity.class);
                startActivity(intent);
                break;
            case R.id.more_1:
                //农业资讯更多
                Intent intent1 = new Intent(getActivity(), MarkNewsListActivity.class);
                startActivity(intent1);
                break;
        }
    }

    /**
     * 服务首页数据
     *
     * @date: 2017/4/13 上午11:03
     * @modified_by liangxingsheng
     */
    private void getServiceData() {

        OkHttpUtils.post()
                .url(Constants.BaseUrL + Constants.SERVICE_HOME)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        refresh.setRefreshing(false);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG, response);
                        refresh.setRefreshing(false);
                        ServiceHomeBean service = JsonUtil.parseJsonToBean(response, ServiceHomeBean.class);
                        bannerList = service.getFeature();
                        supplyList = service.getDisplayBalance();
                        newsList = service.getMarkNew();
                        setHomeData();
                    }
                });
    }

    /**
     * 设置数据
     *
     * @date: 2017/4/14 下午16:03
     * @modified_by liangxingsheng
     * 布局和数据修改
     */
    private void setHomeData() {
        images.clear();
        titles.clear();
        //轮播图
        for (ServiceHomeBean.FeatureBean bean : bannerList) {
            images.add(Constants.PIC_PATH1 + bean.getPic());
            titles.add(bean.getTitle());
        }
        initBanner();
        //供应
        if (supplyList.size() > 0) {
            Glide.with(getActivity().getApplicationContext()).load(supplyList.get(0).getIcon())
                    .placeholder(getResources().getDrawable(R.drawable.pic_default_all))
                    .transform(new GlideRoundTransform(mContext, 5))
                    .into(previewImageVideo);
            titleVideoName.setText(supplyList.get(0).getTitle());
            numSupply.setText(supplyList.get(0).getValue());
            supplyAddress.setText(supplyList.get(0).getAddress());
        }
        if (supplyList.size() > 1) {
            Glide.with(getActivity().getApplicationContext()).load(supplyList.get(1).getIcon())
                    .placeholder(getResources().getDrawable(R.drawable.pic_default_all))
                    .transform(new GlideRoundTransform(mContext, 5))
                    .into(previewImageVideo1);
            titleVideoName1.setText(supplyList.get(1).getTitle());
            numSupply1.setText(supplyList.get(1).getValue());
            supplyAddress1.setText(supplyList.get(1).getAddress());
        }
        //农业资讯
        if (newsList.size() > 1) {
            Glide.with(mContext).load(Constants.PIC_PATH1 + newsList.get(0).getImgSrc()).placeholder(mContext.getResources()
                    .getDrawable(R.drawable.pic_default_all))
                    .transform(new GlideRoundTransform(mContext, 5))
                    .into(previewImageNews1);
            titleNews1.setText(newsList.get(0).getTitle());
            timeNews1.setText(format.format(newsList.get(0).getCreateDate()));
            sourceNews1.setText("");
            //
            Glide.with(mContext).load(Constants.PIC_PATH1 + newsList.get(1).getImgSrc()).placeholder(mContext.getResources()
                    .getDrawable(R.drawable.pic_default_all))
                    .transform(new GlideRoundTransform(mContext, 5))
                    .into(previewImageNews2);
            titleNews2.setText(newsList.get(1).getTitle());
            timeNews2.setText(format.format(newsList.get(1).getCreateDate()));
            sourceNews2.setText("");
        }
    }

}
