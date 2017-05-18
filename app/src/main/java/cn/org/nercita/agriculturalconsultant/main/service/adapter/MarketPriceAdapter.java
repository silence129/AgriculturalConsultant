package cn.org.nercita.agriculturalconsultant.main.service.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.org.nercita.agriculturalconsultant.R;

/**
 * Created by 梁兴胜 on 2017/4/6.
 * 市场价格的Adapter
 */

public class MarketPriceAdapter extends BaseAdapter {

    //Context
    private Context mContext;
    //数据
    private List mList;

    /**
     * 构造方法
     * @author: liangxingsheng
     * @date: 2017/4/6 下午2:23
     * @param mContext
     * @param mList
     */
    public MarketPriceAdapter(Context mContext, List mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    /**
     * 固定写法
     * @author: liangxingsheng
     * @return
     */
    @Override
    public int getCount() {
        return mList==null ? 0 : mList.size();
    }
    /**
     * 固定写法
     * @author: liangxingsheng
     * @return
     */
    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }
    /**
     * 固定写法
     * @author: liangxingsheng
     * @return
     */
    @Override
    public long getItemId(int i) {
        return i;
    }
    /**
     * 固定写法
     * @author: liangxingsheng
     * @return
     */
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            view = View.inflate(mContext, R.layout.item_market_price, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        if (i%2 == 0){
            view.setBackgroundColor(mContext.getResources().getColor(R.color.gray_item));
        }else {
            view.setBackgroundColor(Color.WHITE);
        }
        return view;
    }
    /**
     * ViewHolder固定写法
     * @author: liangxingsheng
     * @return
     */
    static class ViewHolder {
        @Bind(R.id.product_name)
        TextView productName;
        @Bind(R.id.price_date)
        TextView priceDate;
        @Bind(R.id.market_name)
        TextView marketName;
        @Bind(R.id.product_price)
        TextView productPrice;
        @Bind(R.id.price_trend)
        ImageView priceTrend;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
