package cn.org.nercita.agriculturalconsultant.main.service.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import cn.org.nercita.agriculturalconsultant.R;
import cn.org.nercita.agriculturalconsultant.base.BaseActivity;
import cn.org.nercita.agriculturalconsultant.view.TitleBar;


/**
 * Created by 梁兴胜 on 2017/4/7.
 * 农资详情
 */

public class AgriculturalMaterialInfoActivity extends BaseActivity {
    //标题
    @Bind(R.id.title_nongzi_info)
    TitleBar mTitle;
    //农资图片
    @Bind(R.id.image_nongzi)
    ImageView imageNongzi;
    //农资名称
    @Bind(R.id.name_nongzi)
    TextView nameNongzi;
    //农资描述
    @Bind(R.id.desc_nongzi)
    TextView descNongzi;

    @Override
    protected int getContentView() {
        return R.layout.activity_agricultural_material_info;
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
    }

    /**
     * 获取数据
     * @author: liangxingsheng
     * @date: 2017/4/7 下午3:26
     */
    private void getData(){

    }
}
