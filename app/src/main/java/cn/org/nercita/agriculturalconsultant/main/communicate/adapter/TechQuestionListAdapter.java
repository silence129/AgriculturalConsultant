package cn.org.nercita.agriculturalconsultant.main.communicate.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.org.nercita.agriculturalconsultant.R;
import cn.org.nercita.agriculturalconsultant.common.GridImageAdapter;
import cn.org.nercita.agriculturalconsultant.main.bean.TechQuestionListBean;
import cn.org.nercita.agriculturalconsultant.view.MeasuredGridView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 梁兴胜 on 2017/4/11.
 * 农技问答列表adapter
 */

public class TechQuestionListAdapter extends BaseAdapter {


    //Context
    private Context mContext;
    //数据
    private List<TechQuestionListBean.ListBean> mList;
    private DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 构造方法
     *
     * @param mContext
     * @param mList
     * @author: liangxingsheng
     * @date: 2017/4/11 上午9:27
     */
    public TechQuestionListAdapter(Context mContext, List<TechQuestionListBean.ListBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    /**
     * 固定写法
     *
     * @return
     * @author: liangxingsheng
     */
    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    /**
     * 固定写法
     *
     * @return
     * @author: liangxingsheng
     */
    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    /**
     * 固定写法
     *
     * @return
     * @author: liangxingsheng
     */
    @Override
    public long getItemId(int i) {
        return i;
    }

    /**
     * 固定写法
     *
     * @return
     * @author: liangxingsheng
     */
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TechQuestionListBean.ListBean question = mList.get(i);
        ViewHolder viewHolder = null;
        if (view == null) {
            view = View.inflate(mContext, R.layout.item_techquestion_list, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.nameAsker.setText(question.getAccountName());
        viewHolder.contentQuestionTech.setText(question.getTitle());
        if (!TextUtils.isEmpty(question.getCreateTime() + "")) {
            viewHolder.timeQuestionTech.setText(format.format(question.getCreateTime()));
        }
        if (!TextUtils.isEmpty(question.getPics())) {
            viewHolder.gridViewTechQuestion.setVisibility(View.VISIBLE);
            String[] pics = question.getPics().split(",");
            List picsList = Arrays.asList(pics);
            viewHolder.gridViewTechQuestion.setAdapter(new GridImageAdapter(mContext, picsList));
        } else {
            viewHolder.gridViewTechQuestion.setVisibility(View.GONE);
        }
        return view;
    }

    /**
     * ViewHolder固定写法
     *
     * @author: liangxingsheng
     * @return
     */
    static class ViewHolder {
        @Bind(R.id.avatar_asker)
        CircleImageView avatarAsker;
        @Bind(R.id.name_asker)
        TextView nameAsker;
        @Bind(R.id.address_asker)
        TextView addressAsker;
        @Bind(R.id.content_question_tech)
        TextView contentQuestionTech;
        @Bind(R.id.gridView_tech_question)
        MeasuredGridView gridViewTechQuestion;
        @Bind(R.id.time_question_tech)
        TextView timeQuestionTech;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public void setData(List<TechQuestionListBean.ListBean> mList) {
        if (mList != null) {
            this.mList = mList;
            notifyDataSetChanged();
        }
    }
}
