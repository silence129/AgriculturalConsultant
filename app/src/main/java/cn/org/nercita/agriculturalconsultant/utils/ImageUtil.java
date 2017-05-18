package cn.org.nercita.agriculturalconsultant.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.squareup.picasso.Picasso;

import java.io.InputStream;

import cn.org.nercita.agriculturalconsultant.R;

public class ImageUtil {

    private ImageUtil() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static Bitmap readBitMap(Context context, int resId) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        //获取资源图片
        InputStream is = context.getResources().openRawResource(resId);
        return BitmapFactory.decodeStream(is, null, opt);
    }

    public static void load(Context context, ImageView imageView, String url) {
        if (!TextUtils.isEmpty(url))
        Picasso.with(context).load(url).placeholder(R.drawable.pic_default_all).into(imageView);
    }
    public static void loadGridPic(Context context, ImageView imageView, String url) {
        if (!TextUtils.isEmpty(url))
            Picasso.with(context).load(url).resize(100,100).placeholder(R.drawable.pic_default_all).into(imageView);
    }
    public static void loadAccountPic(Context context, ImageView imageView, String url) {
        if (!TextUtils.isEmpty(url))
            Picasso.with(context).load(url).resize(60,60).placeholder(R.drawable.touxiang_default).into(imageView);
    }
    public static void  loadCornerImg(Context context, ImageView imageView, String url, int radius) {
        if (radius == -1) radius = 7;
        if (!TextUtils.isEmpty(url))
            Glide.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.pic_default_all).transform(new GlideRoundTransform(context, radius)).into(imageView);

    }
}
