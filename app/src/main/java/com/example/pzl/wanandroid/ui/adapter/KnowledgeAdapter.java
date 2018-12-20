package com.example.pzl.wanandroid.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pzl.wanandroid.R;
import com.example.pzl.wanandroid.bean.KnowledgeHierachBean;
import com.example.pzl.wanandroid.utils.CommonUtils;

import java.util.List;

public class KnowledgeAdapter extends RecyclerView.Adapter<KnowledgeAdapter.ViewHolder> {

    private Context context;
    private List<KnowledgeHierachBean.DataBean> mList;

    public KnowledgeAdapter(Context context, List<KnowledgeHierachBean.DataBean> list) {
        this.context = context;
        mList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_knowledge_hierarchy,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        KnowledgeHierachBean.DataBean bean = mList.get(position);
        List<KnowledgeHierachBean.DataBean.ChildrenBean> beans = bean.getChildren();
        holder.title.setText(bean.getName());
        holder.title.setTextColor(CommonUtils.randomColor());
        StringBuffer sb = new StringBuffer();
        for (KnowledgeHierachBean.DataBean.ChildrenBean childrenBean : beans) {
            sb.append(childrenBean.getName()+"   ");
        }
        holder.name.setText(sb.toString());

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
        private TextView title,name;
        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_knowledge_hierarchy_title);
            name = itemView.findViewById(R.id.item_knowledge_hierarchy_content);
        }
    }

    private OnLongClick onLongClick;

        public void setOnLongClick(OnLongClick onLongClick) {
            this.onLongClick = onLongClick;
        }

        public interface OnLongClick{
            void onLong(int position);
        }
}
