package cn.org.nercita.agriculturalconsultant.main.communicate.adapter;

import android.content.Context;
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
import cn.org.nercita.agriculturalconsultant.main.bean.ExpertLibraryBean;
import cn.org.nercita.agriculturalconsultant.utils.ImageUtil;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by fan on 2017/4/14.
 */

public class ExpertListAdapter extends BaseAdapter {
    private List<ExpertLibraryBean.ItemBean> list = new ArrayList<>();
    private Context context;
    private final LayoutInflater inflater;

    public ExpertListAdapter(Context context) {
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
        ViewHolder holder;
        ExpertLibraryBean.ItemBean expertLibraryBean = list.get(i);
        if (view == null) {
            view = inflater.inflate(R.layout.item_expertlibrary, null, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        holder.name.setText(expertLibraryBean.getName());
        ImageUtil.loadAccountPic(context,holder.touxiang,"http://gc.nercita.org.cn/bth/resources/uploads/"+expertLibraryBean.getPhoto());

        return view;
    }

    static class ViewHolder {
        @Bind(R.id.touxiang)
        CircleImageView touxiang;
        @Bind(R.id.name)
        TextView name;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
    public void setData(List<ExpertLibraryBean.ItemBean> list){
        if (list!=null){
            this.list = list;
            notifyDataSetChanged();
        }
    }
}
