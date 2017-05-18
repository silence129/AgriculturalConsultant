package cn.org.nercita.agriculturalconsultant.main.home.adapter;

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
import cn.org.nercita.agriculturalconsultant.R;
import cn.org.nercita.agriculturalconsultant.main.bean.NewPriceBean;
import cn.org.nercita.agriculturalconsultant.utils.GlideRoundTransform;

/**
 * Created by 梁兴胜 on 2017/4/10.
 * 首页最新价格adapter
 */

public class LatestPriceAdapter extends BaseAdapter {


    //Context
    private Context mContext;
    //数据
    private List<NewPriceBean.ContentBean> mList;
    private DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     * 构造方法
     *
     * @param mContext
     * @param mList
     * @author: liangxingsheng
     * @date: 2017/4/6 下午17:58
     */
    public LatestPriceAdapter(Context mContext, List<NewPriceBean.ContentBean> mList) {
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
     * 添加数据
     * @return
     * @author: liangxingsheng
     * @modified_by liangxingsheng
     * @modified_date 2017/4/13 下午4:42
     */
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        NewPriceBean.ContentBean price = mList.get(i);
        ViewHolder viewHolder = null;
        if (view == null) {
            view = View.inflate(mContext, R.layout.item_price_newest, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Glide.with(mContext).load(price.getPicname()).placeholder(mContext.getResources().getDrawable(R.drawable.pic_default_all)).transform(new GlideRoundTransform(mContext, 5)).into(viewHolder.imagePriceNewest);
        viewHolder.namePrice.setText(price.getProductName());
        viewHolder.timePrice.setText(format.format(price.getGetTime()));
        viewHolder.marketPrice.setText(price.getMarketName());
        viewHolder.pricePrice.setText(price.getPrice()+price.getPriceUnit());
        return view;
    }

    /**
     * ViewHolder固定写法
     * @author: liangxingsheng
     * @return
     */
    static class ViewHolder {
        @Bind(R.id.image_price_newest)
        ImageView imagePriceNewest;
        @Bind(R.id.name_price)
        TextView namePrice;
        @Bind(R.id.time_price)
        TextView timePrice;
        @Bind(R.id.market_price)
        TextView marketPrice;
        @Bind(R.id.price_price)
        TextView pricePrice;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
