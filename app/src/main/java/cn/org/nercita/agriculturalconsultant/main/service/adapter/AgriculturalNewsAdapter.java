package cn.org.nercita.agriculturalconsultant.main.service.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.org.nercita.agriculturalconsultant.Constants;
import cn.org.nercita.agriculturalconsultant.R;
import cn.org.nercita.agriculturalconsultant.main.bean.MarkNewsListBean;
import cn.org.nercita.agriculturalconsultant.utils.GlideRoundTransform;

/**
 * Created by 梁兴胜 on 2017/4/6.
 * 服务首页农业资讯Adapter
 */

public class AgriculturalNewsAdapter extends BaseAdapter {


    //Context
    private Context mContext;
    //数据
    private List<MarkNewsListBean.ContentBean> mList;
    private DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     * 构造方法
     *
     * @param mContext
     * @param mList
     * @author: liangxingsheng
     * @date: 2017/4/6 下午17:58
     */
    public AgriculturalNewsAdapter(Context mContext, List<MarkNewsListBean.ContentBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    /**
     * 固定写法
     *
     * @return
     * @author: liangxingsheng
     */
    @Override
    public int getCount() {
        return mList==null ? 0 : mList.size();
    }

    /**
     * 固定写法
     *
     * @return
     * @author: liangxingsheng
     */
    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    /**
     * 固定写法
     *
     * @return
     * @author: liangxingsheng
     */
    @Override
    public long getItemId(int i) {
        return i;
    }

    /**
     * 固定写法
     *
     * @return
     * @author: liangxingsheng
     * @modified_by liangxingsheng
     * @modified_date 2017/4/13 上午11:02
     * 添加数据
     */
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MarkNewsListBean.ContentBean news = mList.get(i);
        ViewHolder viewHolder = null;
        if (view == null) {
            view = View.inflate(mContext, R.layout.item_farm_news, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Glide.with(mContext).load(Constants.PIC_PATH1 + news.getImgSrc()).placeholder(mContext.getResources().getDrawable(R.drawable.pic_default_all)).transform(new GlideRoundTransform(mContext, 5)).into(viewHolder.previewImageNews);
        viewHolder.titleNews.setText(news.getTitle());
        viewHolder.timeNews.setText(format.format(news.getCreateDate()));
        viewHolder.sourceNews.setText("");
        return view;
    }
    /**
     * ViewHolder固定写法
     * @author: liangxingsheng
     * @return
     */
    static class ViewHolder {
        @Bind(R.id.preview_image_news)
        ImageView previewImageNews;
        @Bind(R.id.title_news)
        TextView titleNews;
        @Bind(R.id.time_news)
        TextView timeNews;
        @Bind(R.id.source_news)
        TextView sourceNews;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
