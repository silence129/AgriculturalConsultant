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
import cn.org.nercita.agriculturalconsultant.main.bean.NongziListBean;
import cn.org.nercita.agriculturalconsultant.utils.GlideRoundTransform;

/**
 * Created by 梁兴胜 on 2017/4/7.
 */

public class AgriculturalMaterialsAdapter extends BaseAdapter {
    //Context
    private Context mContext;
    //数据
    private List<NongziListBean.ContentBean> mList;

    /**
     * 构造方法
     * @param mContext
     * @param mList
     * @author: liangxingsheng
     * @date: 2017/4/7 下午2:08
     */
    public AgriculturalMaterialsAdapter(Context mContext, List<NongziListBean.ContentBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    /**
     * 固定写法
     * @return
     * @author: liangxingsheng
     */
    @Override
    public int getCount() {
        return mList==null ? 0 : mList.size();
    }

    /**
     * 固定写法
     * @return
     * @author: liangxingsheng
     */
    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    /**
     * 固定写法
     * @return
     * @author: liangxingsheng
     */
    @Override
    public long getItemId(int i) {
        return i;
    }

    /**
     * 固定写法
     * @return
     * @author: liangxingsheng
     */
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        NongziListBean.ContentBean nongzi = mList.get(i);
        ViewHolder viewHolder = null;
        if (view == null) {
            view = View.inflate(mContext, R.layout.item_nongzi, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Glide.with(mContext).load(nongzi.getIcon()).placeholder(mContext.getResources().getDrawable(R.drawable.pic_default_all)).transform(new GlideRoundTransform(mContext, 5)).into(viewHolder.imageNongzi);
        viewHolder.nameNongzi.setText(nongzi.getTitle());
        viewHolder.descNongzi.setText(nongzi.getContent());

        return view;
    }

    static class ViewHolder {
        @Bind(R.id.image_nongzi)
        ImageView imageNongzi;
        @Bind(R.id.name_nongzi)
        TextView nameNongzi;
        @Bind(R.id.desc_nongzi)
        TextView descNongzi;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
