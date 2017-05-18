package cn.org.nercita.agriculturalconsultant.main.communicate.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.org.nercita.agriculturalconsultant.R;
import cn.org.nercita.agriculturalconsultant.main.bean.QuestionTypeBean;

/**
 * Created by 范博文 on 2017/4/7.
 * 选择问题类型adapter
 */

public class SelectQuestionTypeAdapter extends BaseAdapter {
    private List<QuestionTypeBean.ResultBean> list = new ArrayList<>();
    private Context context;
    private TextView oldView;
    private boolean isParent;

    public SelectQuestionTypeAdapter(Context context,boolean isParent) {
        this.context = context;
        this.isParent = isParent;
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_pop_area_text, null,
                    false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tvItemPopAreaText.setText(list.get(i).getName());
        holder.tvItemPopAreaText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener!=null){
                    listener.itemSelect(list.size(),i,isParent);
                }

                if (oldView == null) {
                    oldView = holder.tvItemPopAreaText;
                    oldView.setTextColor(context.getResources().getColor(R.color.title_green));
                } else {
                    oldView.setTextColor(Color.GRAY);
                    oldView = holder.tvItemPopAreaText;
                    holder.tvItemPopAreaText.setTextColor(context.getResources().getColor(R.color
                            .title_green));
                }
            }

        });

        return view;
    }

    static class ViewHolder {
        @Bind(R.id.tv_item_pop_area_text)
        TextView tvItemPopAreaText;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public void setData(List<QuestionTypeBean.ResultBean> list) {
        if (oldView != null) {
            oldView.setTextColor(Color.GRAY);
        }
        if (list != null) {
            this.list = list;
            notifyDataSetChanged();
        }
    }
    public interface SelectTypeListener{
        void itemSelect(int size,int position,boolean isParent);
    }
    public void setSelectTypeListener(SelectTypeListener listener){
        this.listener = listener;
    }
    public SelectTypeListener listener;

}
