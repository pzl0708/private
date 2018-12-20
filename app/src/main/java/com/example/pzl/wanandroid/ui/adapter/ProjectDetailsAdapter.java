package com.example.pzl.wanandroid.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pzl.wanandroid.R;
import com.example.pzl.wanandroid.bean.ProjectDetailsBean;

import java.util.List;

public class ProjectDetailsAdapter extends RecyclerView.Adapter<ProjectDetailsAdapter.ViewHolder> {

    private Context context;
    private List<ProjectDetailsBean.DataBean.DatasBean> mList;

    public ProjectDetailsAdapter(Context context, List<ProjectDetailsBean.DataBean.DatasBean> list) {
        this.context = context;
        mList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_project_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        ProjectDetailsBean.DataBean.DatasBean bean = mList.get(position);
        Glide.with(context).load(bean.getEnvelopePic()).into(holder.envelopePic);
        holder.title.setText(bean.getTitle());
        holder.desc.setText(bean.getDesc());
        holder.niceDate.setText(bean.getNiceDate());
        holder.author.setText(bean.getAuthor());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLongClick.onLong(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView envelopePic;
        private TextView title, desc, niceDate, author;

        public ViewHolder(View itemView) {
            super(itemView);
            envelopePic = itemView.findViewById(R.id.item_project_list_iv);
            title = itemView.findViewById(R.id.item_project_list_title_tv);
            desc = itemView.findViewById(R.id.item_project_list_content_tv);
            niceDate = itemView.findViewById(R.id.item_project_list_time_tv);
            author = itemView.findViewById(R.id.item_project_list_author_tv);
        }
    }

    private OnLongClick onLongClick;

    public void setOnLongClick(OnLongClick onLongClick) {
        this.onLongClick = onLongClick;
    }

    public interface OnLongClick {
        void onLong(int position);
    }
}
