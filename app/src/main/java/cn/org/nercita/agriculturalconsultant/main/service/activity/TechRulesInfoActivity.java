package cn.org.nercita.agriculturalconsultant.main.service.activity;

import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.Bind;
import cn.org.nercita.agriculturalconsultant.R;
import cn.org.nercita.agriculturalconsultant.base.BaseActivity;
import cn.org.nercita.agriculturalconsultant.view.TitleBar;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by 梁兴胜 on 2017/4/7.
 * 技术规程详情
 */

public class TechRulesInfoActivity extends BaseActivity {
    //标题
    @Bind(R.id.title_tech_info)
    TitleBar mTitle;
    //播放器
    @Bind(R.id.videoplayer)
    JCVideoPlayerStandard videoplayer;
    //技术名称
    @Bind(R.id.tv_name_tech)
    TextView tvNameTech;
    //讲师
    @Bind(R.id.lecturer_tech)
    TextView lecturerTech;
    //技术描述
    @Bind(R.id.desc_tech)
    TextView descTech;

    @Override
    protected int getContentView() {
        return R.layout.activity_tech_rules_info;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        init();
    }

    /**
     * 初始化控件
     *
     * @author: liangxingsheng
     * @date: 2017/4/7 下午3:26
     */
    @Override
    protected void initData() {
        super.initData();
        getData();
    }

    /**
     * 初始化控件
     *
     * @author: liangxingsheng
     * @date: 2017/4/7 下午3:26
     */
    private void init() {

        mTitle.setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        videoplayer.setUp("", JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "");
        Glide.with(this).load("").into(videoplayer.thumbImageView);
    }

    /**
     * 获取数据
     *
     * @author: liangxingsheng
     * @date: 2017/4/7 下午3:26
     */
    private void getData() {

    }

    /**
     * 处理播放视频时,点击返回键和当前activity不在前台的情况
     * @author: liangxingsheng
     * @date: 2017/4/7 下午3:26
     */
    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }
    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

}
