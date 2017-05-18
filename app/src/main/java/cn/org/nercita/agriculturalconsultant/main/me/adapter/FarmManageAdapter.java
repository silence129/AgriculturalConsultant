package cn.org.nercita.agriculturalconsultant.main.me.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.org.nercita.agriculturalconsultant.R;
import cn.org.nercita.agriculturalconsultant.main.bean.FarmManageBean;
import cn.org.nercita.agriculturalconsultant.utils.DateUtil;
import cn.org.nercita.agriculturalconsultant.view.MeasuredGridView;

/**
 * Created by fan on 2017/4/13.
 */

public class FarmManageAdapter extends BaseAdapter {
    private Context context;
    private List<FarmManageBean.ContentBean> list = new ArrayList<>();
    private final LayoutInflater inflater;

    public FarmManageAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        FarmManageBean.ContentBean contentBean = list.get(i);
        ViewHolder holder ;
        if (view == null) {
            view = inflater.inflate(R.layout.item_farmmagane, null, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        holder.updatetime.setText(DateUtil.poorTime(String.valueOf(contentBean.getCreateTime()))+"前");
        holder.tvItemActivityMyLifeContent.setText(contentBean.getContent());
        if (!TextUtils.isEmpty(contentBean.getPic())){
            holder.gvImage.setVisibility(View.VISIBLE);
            String[] imagelist = contentBean.getPic().split(",");
            Arrays.asList(imagelist);
            holder.gvImage.setAdapter(new FarmManageImageAdapter(context, imagelist));
        }else {
            holder.gvImage.setVisibility(View.GONE);
        }



        return view;
    }

    static class ViewHolder {
        @Bind(R.id.tv_item_activity_my_life_content)
        TextView tvItemActivityMyLifeContent;
        @Bind(R.id.gv_image)
        MeasuredGridView gvImage;
        @Bind(R.id.updatetime)
        TextView updatetime;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
    public void setdata(List<FarmManageBean.ContentBean> list){
        if (list!=null){
            this.list = list;
            notifyDataSetChanged();
        }
    }
}
