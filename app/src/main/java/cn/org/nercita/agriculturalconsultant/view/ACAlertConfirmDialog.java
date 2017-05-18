package cn.org.nercita.agriculturalconsultant.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import cn.org.nercita.agriculturalconsultant.R;


/**
 * 描述：Dialog提示确认退出
 * @author GaoWenXu
 * @date 2017/4/10 14:30
 * @version v1.0.0
 */
public class ACAlertConfirmDialog extends Dialog implements View.OnClickListener {

    private View mDialogView;
    private TextView mDialogDismiss;
    private TextView mConfirm;
    private TextView mTvMain;
    private TextView mTvSub;

    public ACAlertConfirmDialog(Context context) {
        super(context, R.style.AlertHintDialogStyle);
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes(); // 获取当前的LayoutParams
        params.gravity = Gravity.CENTER ;
        window.setAttributes(params); // 重新设置 LayoutParams
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDialogView = View.inflate(getContext(), R.layout.dialog_confirm_quit, null);
        setContentView(mDialogView);
        initView();
    }

    /**
     * @author mike_cui
     * @date 2016/7/4 17:00
     * @param
     * @description 初始化控件
     */
    private void initView() {
        mDialogDismiss = (TextView) mDialogView.findViewById(R.id.tv_dialog_dismiss);
        mTvMain = (TextView) mDialogView.findViewById(R.id.tv_main);
        mTvSub = (TextView) mDialogView.findViewById(R.id.tv_sub);
        mConfirm = (TextView) mDialogView.findViewById(R.id.tv_confirm);
        mDialogDismiss.setOnClickListener(this);
        mConfirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_dialog_dismiss:
                if (onDialogButtonClickListener != null){
                    onDialogButtonClickListener.onDismissClick();
                }
                break;
            case R.id.tv_confirm:
                if (onDialogButtonClickListener != null){
                    onDialogButtonClickListener.onConfirmClick();
                }
                break;
        }
    }

    /**
     * @author mike_cui
     * @date 2016/7/5 18:41
     * @param
     * @description  隐藏子标题
     */
    public void hideSubTitle(){
        mTvSub.setVisibility(View.GONE);
    }

    /**
     * @author mike_cui
     * @date 2016/7/5 18:42
     * @param
     * @description  设置主标题要显示的内容
     */
    public void setmTvMain(String title) {
        if (title != null){
            mTvMain.setText(title);
        }
    }

    /**
     *  设置监听
     * @param onDialogButtonClickListener
     */
    public void setOnDialogButtonClickListener(OnDialogButtonClickListener onDialogButtonClickListener) {
        this.onDialogButtonClickListener = onDialogButtonClickListener;
    }

    private OnDialogButtonClickListener onDialogButtonClickListener;

    public interface OnDialogButtonClickListener{
        void onDismissClick();
        void onConfirmClick();
    }
}
