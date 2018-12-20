package com.example.pzl.wanandroid.ui.fragment.childfragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pzl.wanandroid.R;
import com.example.pzl.wanandroid.base.BaseMvpFragment;
import com.example.pzl.wanandroid.bean.KnowledgeDetailsBean;
import com.example.pzl.wanandroid.model.KnowledgeHierarchyModel;
import com.example.pzl.wanandroid.primary.CommonPresenter;
import com.example.pzl.wanandroid.primary.ICommonView;
import com.example.pzl.wanandroid.primary.config.IntentConfig;
import com.example.pzl.wanandroid.ui.activity.DetailsActivity;
import com.example.pzl.wanandroid.ui.adapter.KnowledgeDetailsAdapter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class KnowledgeChildFragment extends BaseMvpFragment<CommonPresenter, KnowledgeHierarchyModel> implements ICommonView, KnowledgeDetailsAdapter.OnLongClick {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.recycler)
    XRecyclerView recycler;
    private List<KnowledgeDetailsBean.DataBean.DatasBean> mList = new ArrayList<>();
    private KnowledgeDetailsAdapter mAdapter;
    private int page = 0;

    private String mParam1;
    private int mParam2;

    public static KnowledgeChildFragment newInstance(String param1, int param2) {
        KnowledgeChildFragment fragment = new KnowledgeChildFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putInt(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getInt(ARG_PARAM2);
        }
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_knowledge_child;
    }

    @Override
    protected void initView() {
        initRecycleView(recycler);
        mAdapter = new KnowledgeDetailsAdapter(getActivity(), mList);
        recycler.setAdapter(mAdapter);
        mAdapter.setOnLongClick(this);
    }

    @Override
    protected void initData() {
        mPresenter.getData(IntentConfig.NO_LOAD, page, mParam2);
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
        if (intent == IntentConfig.REFRESH) {
            mList.clear();
        }
        KnowledgeDetailsBean bean = (KnowledgeDetailsBean) o;
        mList.addAll(bean.getData().getDatas());
        mAdapter.notifyDataSetChanged();
        recycler.refreshComplete();
        if (intent == IntentConfig.LOAD_MORE) {
            recycler.loadMoreComplete();
        }
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void refresh() {
        super.refresh();
        page = 0;
        mPresenter.getData(IntentConfig.REFRESH, page, mParam2);
    }

    @Override
    public void loadMore() {
        super.loadMore();
        page++;
        mPresenter.getData(IntentConfig.LOAD_MORE, page, mParam2);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mList.clear();
    }

    @Override
    public void onLong(int position) {
        KnowledgeDetailsBean.DataBean.DatasBean bean = mList.get(position);
        Intent intent = new Intent(getActivity(), DetailsActivity.class);
        intent.putExtra("title",bean.getTitle());
        intent.putExtra("url",bean.getLink());
        startActivity(intent);
    }
}
