package cn.org.nercita.agriculturalconsultant.main.service.adapter;

import android.content.Context;
import android.text.TextUtils;
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
import cn.org.nercita.agriculturalconsultant.main.bean.SupplyListBean;
import cn.org.nercita.agriculturalconsultant.utils.GlideRoundTransform;

/**
 * Created by 梁兴胜 on 2017/4/13.
 * 最新供应adapter
 */

public class SupplyListAdapter extends BaseAdapter {

    //Context
    private Context mContext;
    //数据
    private List<SupplyListBean.ContentBean> mList;

    /**
     * 构造方法
     *
     * @param mContext
     * @param mList
     * @author: liangxingsheng
     * @date: 2017/4/13 下午3:03
     */
    public SupplyListAdapter(Context mContext, List<SupplyListBean.ContentBean> mList) {
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
        return mList == null ? 0 : mList.size();
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
        SupplyListBean.ContentBean video = mList.get(i);
        ViewHolder viewHolder = null;
        if (view == null) {
            view = View.inflate(mContext, R.layout.item_supply, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Glide.with(mContext.getApplicationContext()).load(video.getIcon()).placeholder(mContext.getResources().getDrawable(R.drawable.pic_default_all)).transform(new GlideRoundTransform(mContext, 5)).into(viewHolder.previewImageSupply);
        viewHolder.titleSupplyName.setText(video.getTitle());
        if (!TextUtils.isEmpty(video.getValue())){
        viewHolder.numSupply.setText(video.getValue()+"吨");
        }
        viewHolder.supplyAddress.setText(video.getAddress());

        return view;
    }


    static class ViewHolder {
        @Bind(R.id.preview_image_supply)
        ImageView previewImageSupply;
        @Bind(R.id.title_supply_name)
        TextView titleSupplyName;
        @Bind(R.id.num_supply)
        TextView numSupply;
        @Bind(R.id.supply_address)
        TextView supplyAddress;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
