package cn.org.nercita.agriculturalconsultant.view.address;

import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import cn.org.nercita.agriculturalconsultant.R;


/**
 * Created by cuiyonghong on 16/11/8.
 */

public class AddressDialog extends Dialog {

    private static final String TAG = AddressDialog.class.getSimpleName();
    private AddressDialogEngine dialogEngine = null;

    public AddressDialog(Context context) {
        this(context, R.style.bottom_dialog);
    }

    public AddressDialog(Context context, int themeResId) {
        super(context, themeResId);
        init(context);
    }


    /**
     * 初始化
     */
    private void init(Context context) {
        dialogEngine = new AddressDialogEngine(context);
        View contentView = dialogEngine.getContentView();
        setContentView(contentView);

        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = dp2px(context,300);
        Log.e(TAG, "applyDimens " + (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, new DisplayMetrics()));
        window.setAttributes(params);
        window.setGravity(Gravity.BOTTOM);
    }

    public void showAddressDialog(Context context) {
        show();
    }

    public void setOnSaveItemClickListener(AddressDialogEngine.OnSaveItemClickListener listener){
        dialogEngine.setSaveItemClick(listener);
    }

    public static int dp2px(Context context, float dp) {
        return (int) Math.ceil(context.getResources().getDisplayMetrics().density * dp);
    }
}
