package cn.org.nercita.agriculturalconsultant.main.NetWork.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.org.nercita.agriculturalconsultant.R;
import cn.org.nercita.agriculturalconsultant.main.NetWork.activity.BaseVedioActivity;
import cn.org.nercita.agriculturalconsultant.main.bean.BaseListInfo;
import cn.org.nercita.agriculturalconsultant.utils.NetUtils;

/**
 * Created by fan on 2017/1/17.
 */

public class BaseListInfoAdapter extends BaseAdapter {
    private List<BaseListInfo.DataBean> list = new ArrayList<>();
    private Context context;

    public BaseListInfoAdapter(Context context) {
        this.context = context;

    }

    public void getBeanList(List<BaseListInfo.DataBean> list) {
        if (list != null) {
            this.list = list;
//            Log.e("TAG1", list.size() + "");
            notifyDataSetChanged();

        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        final BaseListInfo.DataBean dataBean = list.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_baselist, null, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (!TextUtils.isEmpty(list.get(position).getName())) {
            holder.basename.setText(list.get(position).getName());
        }
        holder.export.setVisibility(View.GONE);
     /*   if (!TextUtils.isEmpty(list.get(position).getExpertName())) {
            holder.export.setText("责任专家： " + list.get(position).getExpertName());
        }*/
        if (!TextUtils.isEmpty(dataBean.getAddress())) {
            holder.location.setText("地址： " + list.get(position).getAddress());
        }
        if (!TextUtils.isEmpty(dataBean.getImages())) {
//            ImageUtil.loadGridPic(context,holder.basepic,"http://123.127.160
// .69:10006/tech-service/resources/uploads"+list.get(position).getImageList().get(0));
            Glide.with(context).load(dataBean.getImages()).override(100, 100).placeholder(R
                    .drawable.pic_default_all).into(holder.basepic);
        }
        if ("1".equals(dataBean.getType())){
            holder.wulian.setVisibility(View.VISIBLE);
        }else {
            holder.wulian.setVisibility(View.INVISIBLE);
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("data", (ArrayList<BaseListInfo.DataBean>) list);
                bundle.putInt("position", position);
                intent.putExtras(bundle);
                intent.setClass(context, BaseVedioActivity.class);
                if ("1".equals(dataBean.getType())) {
                    if (NetUtils.isWifi(context)) {
                        context.startActivity(intent);
                    }else {
                        alertDialog(intent);
                    }
                }else {
                    context.startActivity(intent);

                }
            }
        });
        return convertView;

    }

    /**
     * 提示对话框
     */
    private void alertDialog(final Intent intent) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("注意");
        builder.setMessage("你当前不在wifi环境下，确定要继续吗？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
//                new VedioAsyncTask().execute(GreenGirdApplication.video_url);
                context.startActivity(intent);
            }
        });
        builder.setNegativeButton("前去设置", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                NetUtils.openSetting((Activity) context);
            }
        });
        builder.show();
    }

    static class ViewHolder {
        @Bind(R.id.basepic)
        ImageView basepic;
        @Bind(R.id.basename)
        TextView basename;
        @Bind(R.id.export)
        TextView export;
        @Bind(R.id.location)
        TextView location;
        @Bind((R.id.wulian))
        ImageView wulian;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
