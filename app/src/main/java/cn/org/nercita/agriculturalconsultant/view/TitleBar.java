package cn.org.nercita.agriculturalconsultant.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.org.nercita.agriculturalconsultant.R;


/**
 *
 */
public class TitleBar extends RelativeLayout {

    private boolean mIsShowBack, mIsShowTitle, mIsShowCommit;
    private ImageView mBack;
    private TextView mTitle, mCommit;
    private LinearLayout mInPutSection;
    private ImageView search;
    private RelativeLayout title;
    private ImageView mMore;
    private ImageView mTitleAdd;

    public TitleBar(Context context) {
        this(context, null);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = initView(context);
        setAttr(context, attrs, defStyleAttr);
        show();
        this.addView(view);
    }


    @NonNull
    private View initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.mian_title, this, false);
        mInPutSection = (LinearLayout) view.findViewById(R.id.ll_search_section);
        mBack = (ImageView) view.findViewById(R.id.iv_title_back);
        mTitle = (TextView) view.findViewById(R.id.tv_title_name);
        mCommit = (TextView) view.findViewById(R.id.tv_title_commit);
        search = (ImageView) view.findViewById(R.id.iv_title_search);
        title = (RelativeLayout) view.findViewById(R.id.title_title);
        mMore = (ImageView) view.findViewById(R.id.content_more);
        mTitleAdd = (ImageView) view.findViewById(R.id.iv_title_add);
        return view;
    }

    private void setAttr(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.TitleBar, defStyleAttr, 0);
        int indexCount = array.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = array.getIndex(i);
            switch (index) {
                case R.styleable.TitleBar_isShowBack:
                    mIsShowBack = array.getBoolean(index, false);
                    break;
                case R.styleable.TitleBar_isShowTitle:
                    mIsShowTitle = array.getBoolean(index, false);
                    break;
                case R.styleable.TitleBar_isShowCommit:
                    mIsShowCommit = array.getBoolean(index, false);
                    break;
                case R.styleable.TitleBar_TitleText:
                    mTitle.setText(array.getString(index));
                    break;
                case R.styleable.TitleBar_titleRight:
                    mCommit.setText(array.getString(index));
                    break;
            }
        }
    }

    private void show() {
        if (mIsShowBack) {
            mBack.setVisibility(VISIBLE);
        } else {
            mBack.setVisibility(INVISIBLE);
        }
        if (mIsShowTitle) {
            mTitle.setVisibility(VISIBLE);
        } else {
            mTitle.setVisibility(INVISIBLE);
        }
        if (mIsShowCommit) {
            mCommit.setVisibility(VISIBLE);
        } else {
            mCommit.setVisibility(INVISIBLE);
        }
    }

    public void setBackListener(OnClickListener listener) {
        mBack.setOnClickListener(listener);
    }
    public void setMoreLinstener(OnClickListener linstener){
        mMore.setOnClickListener(linstener);
    }
    public void setCommitListener(OnClickListener listener) {
        mCommit.setOnClickListener(listener);
    }
    public void setSearchLinstener(OnClickListener listener){
        search.setOnClickListener(listener);
    }

    public void setTitle(String text) {
        if (!TextUtils.isEmpty(text)) {
            mTitle.setText(text);
        }
    }

    public void setTitleRight(String text) {
        if (!TextUtils.isEmpty(text)) {
            mCommit.setText(text);
        }
    }
    //显示返回标志
    public void setIvBack(int state){
       mBack.setVisibility(state);
    }
    //显示搜索图片
    public void setSearch(int state){
       search.setVisibility(state);
    }
    public void setMore(int state){mMore.setVisibility(state);}
    public void setTitleBar(int state){
        title.setVisibility(state);
    }
    public void setCommit(int state){mCommit.setVisibility(state);}
    public TextView getCommit() {
        return mCommit;
    }

    /**
     * 设置Input 输入区域的显示状态
     * @param state
     */
    public void setInputSectionState(int state){
        mInPutSection.setVisibility(state);
    }

    /**
     *  设置titleBar 上的 添加按钮是否显示
     * @param isShow
     */
    public void setAddButtonState(boolean isShow){
        mTitleAdd.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    /**
     *  设置点击的监听
     * @param listener
     */
    public void setTitleAddClickListener(OnClickListener listener){
        if (listener != null){
            mTitleAdd.setOnClickListener(listener);
        }
    }
}
