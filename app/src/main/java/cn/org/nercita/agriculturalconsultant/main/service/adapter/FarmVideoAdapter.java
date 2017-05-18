package cn.org.nercita.agriculturalconsultant.main.service.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.org.nercita.agriculturalconsultant.R;
import cn.org.nercita.agriculturalconsultant.main.bean.TechRulesListBean;
import cn.org.nercita.agriculturalconsultant.utils.GlideRoundTransform;

/**
 * Created by 梁兴胜 on 2017/4/7.
 * 农业视频通用adapter
 */

public class FarmVideoAdapter extends BaseAdapter {

    //Context
    private Context mContext;
    //数据
    private List<TechRulesListBean.ResourceBean.ContentBean> mList;

    /**
     * 构造方法
     *
     * @param mContext
     * @param mList
     * @author: liangxingsheng
     * @date: 2017/4/7 上午10:59
     */
    public FarmVideoAdapter(Context mContext, List<TechRulesListBean.ResourceBean.ContentBean> mList) {
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
     */
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TechRulesListBean.ResourceBean.ContentBean video = mList.get(i);
        ViewHolder viewHolder = null;
        if (view == null) {
            view = View.inflate(mContext, R.layout.item_video, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Glide.with(mContext.getApplicationContext()).load(video.getPic()).placeholder(mContext.getResources().getDrawable(R.drawable.pic_default_all)).transform(new GlideRoundTransform(mContext, 5)).into(viewHolder.previewImageVideo);
        viewHolder.titleVideoName.setText(video.getName());

        return view;
    }

    static class ViewHolder {
        @Bind(R.id.preview_image_video)
        ImageView previewImageVideo;
        @Bind(R.id.title_video_name)
        TextView titleVideoName;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
