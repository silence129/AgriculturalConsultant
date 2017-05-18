package cn.org.nercita.agriculturalconsultant.main.me.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.org.nercita.agriculturalconsultant.R;
import cn.org.nercita.agriculturalconsultant.common.GridImageAdapter;
import cn.org.nercita.agriculturalconsultant.main.bean.ExpertQuestionBean;
import cn.org.nercita.agriculturalconsultant.main.me.activity.DesExpertQuestionActivity;
import cn.org.nercita.agriculturalconsultant.view.MeasuredGridView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by fan on 2017/4/11.
 */

public class ExpertQuestionAdapter extends BaseAdapter {
    private static final String TAG = ExpertQuestionAdapter.class.getSimpleName();
    public static final String QUESTIONID = "question_id";
    private Context context;
    private List<ExpertQuestionBean.ContentBean> list = new ArrayList<>();
    private final LayoutInflater inflater;
    private DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public ExpertQuestionAdapter(Context context) {
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
        ViewHolder holder;
        final ExpertQuestionBean.ContentBean contentBean = list.get(i);
        if (view == null) {
            view = inflater.inflate(R.layout.item_techquestion_list, null, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.nameAsker.setText(contentBean.getAccountname());
        holder.contentQuestionTech.setText(contentBean.getTitle());
        if (!TextUtils.isEmpty(contentBean.getCreatedate())){
            long l = Long.parseLong(contentBean.getCreatedate());
            holder.timeQuestionTech.setText(format.format(l));
        }
        if (!TextUtils.isEmpty(contentBean.getImg())){
            String[] pics = contentBean.getImg().split(",");
            List picsList = Arrays.asList(pics);
            holder.gridViewTechQuestion.setVisibility(View.VISIBLE);
            holder.gridViewTechQuestion.setAdapter(new GridImageAdapter(context, picsList));
        }else {
            holder.gridViewTechQuestion.setVisibility(View.GONE);
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DesExpertQuestionActivity.class);
                Log.e(TAG, "questionid=" + contentBean.getId());
                intent.putExtra(QUESTIONID, String.valueOf(contentBean.getId()));
                context.startActivity(intent);
            }
        });
        return view;
    }

    public void getData(List<ExpertQuestionBean.ContentBean> list) {
        if (list != null) {
            this.list = list;
            notifyDataSetChanged();
        }
    }

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
}
