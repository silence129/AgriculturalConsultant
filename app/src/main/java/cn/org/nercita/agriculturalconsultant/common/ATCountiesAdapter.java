package cn.org.nercita.agriculturalconsultant.common;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.org.nercita.agriculturalconsultant.R;


/**
 * Created by husk on 2016/10/22.
 */

public class ATCountiesAdapter extends BaseAdapter {
    private Context context;
    private List<String> dataList;
    public Map<String, Boolean> map = new HashMap<>();

    public ATCountiesAdapter(Context context) {
        this.context = context;

    }

    public void setDataListAndNotify(List<String> dataList) {
        this.dataList = dataList;
        map.clear();
        for (String key : dataList) {
            map.put(key, false);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return dataList == null ? 0 : dataList.size();
    }

    @Override
    public String getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = (TextView) View.inflate(context, R.layout.item_pop_area_text, null);
        textView.setText(getItem(position));
        Boolean isSel = map.get(dataList.get(position));
        if (isSel != null) {
            textView.setSelected(isSel);
        }

        if (textView.isSelected()) {
            textView.setTextColor(context.getResources().getColor(R.color.title_green));
        } else {

            textView.setTextColor(Color.GRAY);
        }
        return textView;
    }
}
