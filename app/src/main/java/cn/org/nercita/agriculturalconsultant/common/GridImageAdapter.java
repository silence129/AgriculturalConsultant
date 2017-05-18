package cn.org.nercita.agriculturalconsultant.common;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.org.nercita.agriculturalconsultant.Constants;
import cn.org.nercita.agriculturalconsultant.R;
import cn.org.nercita.agriculturalconsultant.utils.ImageGallery.ImageGalleryActivity;


/**
 * Created by 梁兴胜 on 2017/4/11.
 * 单张图片GridImageAdapter
 */

public class GridImageAdapter extends BaseAdapter {

    private Context context;
    private List<String> images;
    public static final String TAG = "GridImageAdapter";
    private final String[] imagesl;

    public GridImageAdapter(Context context, List<String> images) {
        this.context = context;
        this.images = images;
        imagesl = (String[]) images.toArray();
    }

    @Override
    public int getCount() {
        return images == null ? 0 : images.size();
    }

    @Override
    public Object getItem(int position) {
        return images.get(position);
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
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Glide.with(context).load(Constants.BaseUrL + images.get(position)).into(viewHolder.ivSingle);
        setLifeImage(viewHolder,position,imagesl);
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.iv_single)
        ImageView ivSingle;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
    /**
     * 放大图片
     */
    private void setLifeImage(final ViewHolder vh, final int position, final String[] imgs) {

        vh.ivSingle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> urls = new ArrayList();
                String[] result = {};
                for (int i = 0; i < imgs.length; i++) {
                    String url = Constants.BaseUrL+imgs[i];
                    urls.add(url);
                }
                ImageGalleryActivity.show(context.getApplicationContext(), urls.toArray(result), position);

            }
        });
    }
}
