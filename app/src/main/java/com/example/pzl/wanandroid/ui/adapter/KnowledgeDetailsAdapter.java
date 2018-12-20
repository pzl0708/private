package com.example.pzl.wanandroid.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pzl.wanandroid.R;
import com.example.pzl.wanandroid.bean.KnowledgeDetailsBean;

import java.util.List;

public class KnowledgeDetailsAdapter extends RecyclerView.Adapter<KnowledgeDetailsAdapter.ViewHolder>{

    private Context context;
    private List<KnowledgeDetailsBean.DataBean.DatasBean> mList;

    public KnowledgeDetailsAdapter(Context context, List<KnowledgeDetailsBean.DataBean.DatasBean> list) {
        this.context = context;
        mList = list;
    }

    @NonNull
    @Override
    public KnowledgeDetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_search_pager,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KnowledgeDetailsAdapter.ViewHolder holder, final int position) {
        KnowledgeDetailsBean.DataBean.DatasBean bean = mList.get(position);
        holder.author.setText(bean.getAuthor());
        holder.chapterName.setText(bean.getSuperChapterName()+"/"+bean.getChapterName());
        holder.title.setText(bean.getTitle());
        holder.niceData.setText(bean.getNiceDate());

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
        private TextView author,chapterName,title,niceData;
        public ViewHolder(View itemView) {
            super(itemView);
            author = itemView.findViewById(R.id.item_search_pager_author);
            chapterName = itemView.findViewById(R.id.item_search_pager_chapterName);
            title = itemView.findViewById(R.id.item_search_pager_title);
            niceData = itemView.findViewById(R.id.item_search_pager_niceDate);
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
