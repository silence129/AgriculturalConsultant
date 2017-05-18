package cn.org.nercita.agriculturalconsultant.main.NetWork.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.org.nercita.agriculturalconsultant.R;

/**
 * 作者：abao on 2016/12/24 12:07
 * 邮箱：huangxinbao513@163.com
 */

public class SelectVideoAdater extends BaseAdapter {
    private List<String> data=new ArrayList<String>();
    private Context context;
    private LayoutInflater inflater;
    private int current;

    public SelectVideoAdater(List<String> data, Context context, int current) {
        this.data = data;
        this.context = context;
        this.inflater=LayoutInflater.from(context);
        this.current=current;
    }
  public int setDataPositon(int position){
      return this.current=position;
  }
    public List<String> setData(List<String> data){
        return this.data=data;
    }
    @Override
    public int getCount() {
        return data==null?0:data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            viewHolder=new ViewHolder();
            convertView=inflater.inflate(R.layout.drawer_list_item,parent,false);
            viewHolder.tv= (TextView) convertView.findViewById(R.id.item_tv);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.tv.setText(data.get(position));
        Drawable drawable_normal= context.getResources().getDrawable(R.drawable.sxt_x_icon);
        drawable_normal.setBounds(0, 0, drawable_normal.getMinimumWidth(), drawable_normal.getMinimumHeight());
        viewHolder.tv.setCompoundDrawables(drawable_normal,null,null,null);
        viewHolder.tv.setTextColor(context.getResources().getColor(R.color.drawer));
        if(current!=-2&&current==position) {
        Drawable drawable= context.getResources().getDrawable(R.drawable.sxt_tc_icon);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        viewHolder.tv.setCompoundDrawables(drawable,null,null,null);
            viewHolder.tv.setTextColor(context.getResources().getColor(R.color.global_color));
        }
        Log.e("getView 方法",""+current);
        return convertView;
    }
    class ViewHolder{
        TextView tv;
    }
}
