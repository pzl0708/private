package com.example.pzl.wanandroid.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pzl.wanandroid.R;
import com.example.pzl.wanandroid.base.BaseMvpFragment;
import com.example.pzl.wanandroid.bean.KnowledgeHierachBean;
import com.example.pzl.wanandroid.model.KnowledgeHierarchyModel;
import com.example.pzl.wanandroid.primary.CommonPresenter;
import com.example.pzl.wanandroid.primary.ICommonView;
import com.example.pzl.wanandroid.primary.config.IntentConfig;
import com.example.pzl.wanandroid.ui.activity.KnowledgeDetailsActivity;
import com.example.pzl.wanandroid.ui.adapter.KnowledgeAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class KnowledgeHierarchyFragment extends BaseMvpFragment<CommonPresenter, KnowledgeHierarchyModel> implements ICommonView, KnowledgeAdapter.OnLongClick {

    @BindView(R.id.recycler)
    RecyclerView recycler;
    private List<KnowledgeHierachBean.DataBean> mList = new ArrayList<>();
    private KnowledgeAdapter mAdapter;

    @Override
    protected int initLayout() {
        return R.layout.fragment_knowledge_hierarchy;
    }

    @Override
    protected void initView() {
        mAdapter = new KnowledgeAdapter(getActivity(), mList);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler.setAdapter(mAdapter);
        mAdapter.setOnLongClick(this);
    }

    @Override
    protected void initData() {
        mPresenter.getData(IntentConfig.COMMON);
    }

    @Override
    protected CommonPresenter getPresenter() {
        return new CommonPresenter();
    }

    @Override
    protected KnowledgeHierarchyModel getModel() {
        return new KnowledgeHierarchyModel();
    }

    @Override
    public void onResponse(Object o, int intent) {
        KnowledgeHierachBean bean = (KnowledgeHierachBean) o;
        mList.addAll(bean.getData());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mList.clear();
    }

    @Override
    public void onLong(int position) {
        KnowledgeHierachBean.DataBean bean = mList.get(position);
        List<KnowledgeHierachBean.DataBean.ChildrenBean> children = bean.getChildren();
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<String> title = new ArrayList<>();
        for (KnowledgeHierachBean.DataBean.ChildrenBean child : children) {
            list.add(child.getId());
            title.add(child.getName());
        }
        Intent intent = new Intent(getActivity(), KnowledgeDetailsActivity.class);
        intent.putExtra("title",bean.getName());
        intent.putIntegerArrayListExtra("idList",list);
        intent.putStringArrayListExtra("titleList",title);
        startActivity(intent);
    }

    public void jumpToTheTop() {
        if (recycler != null) {
            recycler.smoothScrollToPosition(0);
        }
    }
}
