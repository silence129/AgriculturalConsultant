package cn.org.nercita.agriculturalconsultant.main.me.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.org.nercita.agriculturalconsultant.Constants;
import cn.org.nercita.agriculturalconsultant.R;



/**
 * Created by fan on 2017/3/7.
 * 工作日志图片Adapter
 */

public class FarmManageImageAdapter extends BaseAdapter {

    private Context context;
    private String[] images = new String[]{};
    public static final String TAG = "WorkDiaryImageAdapter";

    public FarmManageImageAdapter(Context context, String[] images) {
        this.context = context;
        this.images = images;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Object getItem(int position) {
        return images[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_single_image, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Glide.with(context).load(Constants.BaseUrL +"resources/uploads" +images[position]).into(viewHolder.image);
//        Glide.with(context).load("http://123.127.160.69:10006/tech-service/resources/uploads/workdiary/1488851400502.jpg").into(viewHolder.image);
//        setLifeImage(viewHolder, position, images);
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.iv_single)
        ImageView image;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

/*    *//**
     * 放大图片
     *//*
    private void setLifeImage(final ViewHolder vh, final int position, final String[] imgs) {

        vh.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> urls = new ArrayList();
                String[] result = {};
                for (int i = 0; i < imgs.length; i++) {
                    String url = MyConstants.IMAGE_PATH  + imgs[i];
                    urls.add(url);
                }
                ImageGalleryActivity.show(context.getApplicationContext(), urls.toArray(result), position);

            }
        });
    }*/
}
