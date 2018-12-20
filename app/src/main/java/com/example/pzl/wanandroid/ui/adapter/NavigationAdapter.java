package com.example.pzl.wanandroid.ui.adapter;

import android.app.ActivityOptions;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pzl.wanandroid.R;
import com.example.pzl.wanandroid.bean.NavigationBean;
import com.example.pzl.wanandroid.utils.CommonUtils;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

public class NavigationAdapter extends RecyclerView.Adapter<NavigationAdapter.ViewHolder > {

    private Context context;
    private List<NavigationBean.DataBean> mList;

    public NavigationAdapter(Context context, List<NavigationBean.DataBean> list) {
        this.context = context;
        mList = list;
    }

    @NonNull
    @Override
    public NavigationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_navigation,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final NavigationAdapter.ViewHolder holder, int position) {
        NavigationBean.DataBean bean = mList.get(position);
        holder.title.setText(bean.getName());
        List<NavigationBean.DataBean.ArticlesBean> articles = bean.getArticles();
        holder.mLayout.setAdapter(new TagAdapter<NavigationBean.DataBean.ArticlesBean>(articles) {
            @Override
            public View getView(FlowLayout parent, int position, NavigationBean.DataBean.ArticlesBean articlesBean) {
                TextView tv = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.flow_layout_tv,
                        holder.mLayout, false);
                if (articlesBean == null) {
                    return null;
                }
                String name = articlesBean.getTitle();
                tv.setPadding(CommonUtils.dp2px(10), CommonUtils.dp2px(10),
                        CommonUtils.dp2px(10), CommonUtils.dp2px(10));
                tv.setText(name);
                tv.setTextColor(CommonUtils.randomColor());
                holder.mLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                    @Override
                    public boolean onTagClick(View view, int position, FlowLayout parent) {
                        return true;
                    }
                });
                return tv;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TagFlowLayout mLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_navigation_tv);
            mLayout = itemView.findViewById(R.id.item_navigation_flow_layout);
        }
    }
}
