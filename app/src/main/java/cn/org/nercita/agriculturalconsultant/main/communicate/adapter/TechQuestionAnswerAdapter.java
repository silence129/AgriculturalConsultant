package cn.org.nercita.agriculturalconsultant.main.communicate.adapter;

import android.content.Context;
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
import cn.org.nercita.agriculturalconsultant.main.bean.TechQuestionDetailBean;
import cn.org.nercita.agriculturalconsultant.view.MeasuredListView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 梁兴胜 on 2017/4/11.
 * 农技问答答案列表adapter
 */

public class TechQuestionAnswerAdapter extends BaseAdapter {


    //Context
    private Context mContext;
    //数据
    private List<TechQuestionDetailBean.TechReponselistBean> mList;
    private DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     * 构造方法
     *
     * @param mContext
     * @param mList
     * @author: liangxingsheng
     * @date: 2017/4/11 上午11:01
     */
    public TechQuestionAnswerAdapter(Context mContext, List<TechQuestionDetailBean.TechReponselistBean> mList) {
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
     * @modified_by liangxingsheng
     * @modified_date 2017/4/12 下午5:56
     */
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TechQuestionDetailBean.TechReponselistBean answer = mList.get(i);
        ViewHolder viewHolder = null;
        if (view == null) {
            view = View.inflate(mContext, R.layout.item_tech_answer, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.nameAnswer.setText(answer.getAccountName());
        viewHolder.contentQuestionTech.setText(answer.getContent());
        viewHolder.timeQuestionTech.setText(format.format(answer.getCreateTime()));
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
        @Bind(R.id.reply_this_answer)
        TextView replyThisAnswer;
        @Bind(R.id.address_answer)
        TextView addressAnswer;
        @Bind(R.id.content_question_tech)
        TextView contentQuestionTech;
        @Bind(R.id.time_question_tech)
        TextView timeQuestionTech;
        @Bind(R.id.image_zan)
        ImageView imageZan;
        @Bind(R.id.count_zan)
        TextView countZan;
        @Bind(R.id.count_pinglun)
        TextView countPinglun;
        @Bind(R.id.replyofanswer_tech)
        MeasuredListView replyofanswerTech;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}
