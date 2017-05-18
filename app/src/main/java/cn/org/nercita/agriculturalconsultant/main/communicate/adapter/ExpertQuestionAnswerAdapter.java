package cn.org.nercita.agriculturalconsultant.main.communicate.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.org.nercita.agriculturalconsultant.R;
import cn.org.nercita.agriculturalconsultant.main.bean.ExpertQuestionDetailBean;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 梁兴胜 on 2017/4/12.
 * 专家问题答案adapter
 */

public class ExpertQuestionAnswerAdapter extends BaseAdapter {

    //Context
    private Context mContext;
    //数据
    private List<ExpertQuestionDetailBean.AnswersBean> mList;
    private DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 构造方法
     * @param mContext
     * @param mList
     * @author: liangxingsheng
     * @date: 2017/4/12 上午11:21
     */
    public ExpertQuestionAnswerAdapter(Context mContext, List<ExpertQuestionDetailBean.AnswersBean> mList) {
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
        return mList==null ? 0 : mList.size();
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
        ExpertQuestionDetailBean.AnswersBean answers = mList.get(i);
        ViewHolder viewHolder = null;
        if (view == null) {
            view = View.inflate(mContext, R.layout.item_expert_answer, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.nameAnswer.setText(answers.getExpertname());
        viewHolder.addressAnswer.setText(answers.getStationname());
        viewHolder.contentQuestion.setText(answers.getReponsecontent());
        if (TextUtils.isEmpty(answers.getReponsedate())){
            long l = Long.parseLong(answers.getReponsedate());
            viewHolder.timeQuestion.setText(format.format(l));
        }

        return view;
    }

    /**
     * ViewHolder固定写法
     * @author: liangxingsheng
     * @return
     */
    static class ViewHolder {
        @Bind(R.id.avatar_answer)
        CircleImageView avatarAnswer;
        @Bind(R.id.name_answer)
        TextView nameAnswer;
        @Bind(R.id.address_answer)
        TextView addressAnswer;
        @Bind(R.id.content_question)
        TextView contentQuestion;
        @Bind(R.id.time_question)
        TextView timeQuestion;
        @Bind(R.id.image_zan)
        ImageView imageZan;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
