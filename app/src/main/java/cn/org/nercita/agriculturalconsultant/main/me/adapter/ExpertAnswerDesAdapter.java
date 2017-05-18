package cn.org.nercita.agriculturalconsultant.main.me.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.org.nercita.agriculturalconsultant.Constants;
import cn.org.nercita.agriculturalconsultant.R;
import cn.org.nercita.agriculturalconsultant.main.bean.ExpertAnswerDesBen;
import cn.org.nercita.agriculturalconsultant.utils.ImageUtil;
import cn.org.nercita.agriculturalconsultant.view.CircleImageView;

/**
 * Created by 范博文 on 2017/4/11.
 */

public class ExpertAnswerDesAdapter extends BaseAdapter {
    private List<ExpertAnswerDesBen.AnswersBean> list = new ArrayList<>();
    private Context context;
    private final LayoutInflater inflater;

    public ExpertAnswerDesAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ExpertAnswerDesBen.AnswersBean answersBean = list.get(i);
        ViewHolder holder ;
        if (view == null) {
            view = inflater.inflate(R.layout.question_answer, null, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        ImageUtil.load(context, holder.replyImg, Constants.BaseUrL+answersBean.getExpertpic());
        holder.replyContent.setText(answersBean.getReponsecontent());
        holder.replyExpertname.setText(answersBean.getExpertname());
        return view;
    }

    static class ViewHolder {
        @Bind(R.id.reply_img)
        CircleImageView replyImg;
        @Bind(R.id.reply_expertname)
        TextView replyExpertname;
        @Bind(R.id.reply_content)
        TextView replyContent;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
    public void setData(List<ExpertAnswerDesBen.AnswersBean> list){
        if (list!=null){
            this.list = list;
            notifyDataSetChanged();
        }
    }
}
