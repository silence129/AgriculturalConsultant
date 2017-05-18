package cn.org.nercita.agriculturalconsultant.main.NetWork.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cn.org.nercita.agriculturalconsultant.R;
import cn.org.nercita.agriculturalconsultant.main.bean.BaseInfo;


/**
 * Created by Administrator on 2016/12/9.
 */

public class NBaseTempInfoAdapter extends BaseAdapter {
    private List<BaseInfo.StationFactoresBean> list = new ArrayList<>();
    private Context context;
//    private final ListIterator<BaseInfo.StationFactoresBean> stationFactoresBeanListIterator;

    public NBaseTempInfoAdapter(List<BaseInfo.StationFactoresBean> list, Context context) {
        this.list = list;
        this.context = context;
        Iterator<BaseInfo.StationFactoresBean> iterator = list.iterator();
        while (iterator.hasNext()){
            BaseInfo.StationFactoresBean next = iterator.next();
            if (next.getFactorId().equals("SWITCH")||next.getFactorId().equals("LIE_DURATION")||next.getFactorId().equals("STEP_COUNT")
                    ||next.getFactorId().equals("STREAM")||TextUtils.isEmpty(next.getLastValue())){
                iterator.remove();
            }
        }
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

        BaseInfo.StationFactoresBean info = list.get(i);

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.param_grid_adapter, null, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        String type = info.getFactorId();

            if (TextUtils.equals(type, "ATC")) {
                //空气温度
                holder.image.setImageResource(R.drawable.ce_kong);
                holder.content.setText(info.getLastValue()+"℃");
            } else if (TextUtils.equals(type, "AHC")) {
                //空气湿度
                holder.image.setImageResource(R.drawable.ce_shi);
                holder.content.setText(info.getLastValue()+"%");
            } else if (TextUtils.equals(type, "STC")) {
                //土壤温度
                holder.image.setImageResource(R.drawable.ce_tu);
                holder.content.setText(info.getLastValue()+"℃");
            }
            else if (TextUtils.equals(type, "H2S")) {
                //硫化氢
                holder.image.setImageResource(R.drawable.hydrogen_sulfide);
                holder.content.setText(info.getLastValue()+"%");
            } else if (TextUtils.equals(type, "NOISE")) {
                //噪音
                holder.image.setImageResource(R.drawable.noise);
                holder.content.setText(info.getLastValue()+"dB");
            } else if (TextUtils.equals(type, "NH3")) {
                //氨气
                holder.image.setImageResource(R.drawable.nh3);
                holder.content.setText(info.getLastValue()+"%");
            }else if (TextUtils.equals(type, "SWC")) {
                //土壤湿度
                holder.image.setImageResource(R.drawable.ce_shui);
                holder.content.setText(info.getLastValue()+"%");
            } else if (TextUtils.equals(type, "LIGHT")) {
                //光照强度
                holder.image.setImageResource(R.drawable.ce_guang);
                holder.content.setText(info.getLastValue()+"Lux");
            } else if (TextUtils.equals(type, "CO2")) {
                //二氧化碳
                holder.image.setImageResource(R.drawable.param3);
                holder.content.setText(info.getLastValue());
            } else if (TextUtils.equals(type,"DEW")){
                //露点
                holder.image.setImageResource(R.drawable.ce_lu);
                holder.content.setText(info.getLastValue()+"℃");
            }else if (TextUtils.equals(type, "ALI")) {
                //辐射
                holder.image.setImageResource(R.drawable.radiation);
                holder.content.setText(info.getLastValue()+"rad");
            }else if (TextUtils.equals(type, "APT")) {
                //日降雨
                holder.image.setImageResource(R.drawable.daily_rain);
                holder.content.setText(info.getLastValue()+"mm");
            }else if (TextUtils.equals(type, "AWD")) {
                //风向
                holder.image.setImageResource(R.drawable.wind_direction);
                holder.content.setText(info.getLastValue());
            } else if (TextUtils.equals(type, "AWS")) {
                //风速
                holder.image.setImageResource(R.drawable.wind_speed);
                holder.content.setText(info.getLastValue());
            } else if (TextUtils.equals(type, "AAP")) {
                //压力
                holder.image.setImageResource(R.drawable.pressure);
                holder.content.setText(info.getLastValue()+"Pa");
            }else {
                holder.image.setImageResource(R.drawable.cgq_icon);
                holder.image.setScaleType(ImageView.ScaleType.FIT_XY);
                holder.content.setText(info.getLastValue());
            }

            holder.title.setText(info.getDisplayName());


        return view;
    }
    public class ViewHolder{
        ImageView image;
        TextView title;
        TextView content;
        public ViewHolder(View v){
           image = (ImageView) v.findViewById(R.id.iv_params_icon);
            title = (TextView) v.findViewById(R.id.tv_params_data);
            content = (TextView) v.findViewById(R.id.tv_params_description);
        }
    }
    public void setData(List<BaseInfo.StationFactoresBean> list){
        if (list!=null){
            Iterator<BaseInfo.StationFactoresBean> iterator = list.iterator();
            while (iterator.hasNext()){
                BaseInfo.StationFactoresBean next = iterator.next();
                if (next.getFactorId().equals("SWITCH")||next.getFactorId().equals("LIE_DURATION")||next.getFactorId().equals("STEP_COUNT")
                        ||next.getFactorId().equals("STREAM")||TextUtils.isEmpty(next.getLastValue())){
                    iterator.remove();
                }
            }
            this.list = list;
            notifyDataSetChanged();
        }
    }

}


