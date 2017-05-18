package cn.org.nercita.agriculturalconsultant.view;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;



import butterknife.ButterKnife;

import butterknife.OnClick;
import cn.org.nercita.agriculturalconsultant.R;


/**
 * Created by admin on 2016/9/29.
 */
/**
 * 描述：群列表我创建的点击popwindow
 * @author GaoWenXu
 * @date 2017/4/10 13:24
 * @version v1.0.0
 */

public class ACGroupMangePopuView extends PopupWindow {

    LinearLayout mMangeGroup;

    public ACGroupMangePopuView(Context context, int width, int height) {
        super(width, height);
        View manageView = View.inflate(context,R.layout.view_manage_setting, null);
        mMangeGroup= (LinearLayout) manageView.findViewById(R.id.ll_manage);
        setWidth(width);
        setHeight(height);
        setContentView(manageView);
        setBackgroundDrawable(new BitmapDrawable());
        setOutsideTouchable(true);
    }

    public void setShowAtAsDrown(View anchor, int width , int height){
        showAsDropDown(anchor,width,height);
    }
    @OnClick({R.id.ll_manage})
    public void onClick(View v){
        int itemId = v.getId();
        switch (itemId) {
            case R.id.ll_manage:
                if (listener != null){
                    listener.onManageItemClick();
                }
                break;

        }
    }

    private OnPopGroupSettingClickListener listener;

    public void setGroupItemClickListener(OnPopGroupSettingClickListener listener) {
        this.listener = listener;
    }

    public interface OnPopGroupSettingClickListener{
        void onManageItemClick();
        void onAddPersonItemClick();
        void onShareItemClick();

    }
}
