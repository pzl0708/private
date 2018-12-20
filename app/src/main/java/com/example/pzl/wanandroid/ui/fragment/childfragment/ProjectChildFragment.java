package com.example.pzl.wanandroid.ui.fragment.childfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pzl.wanandroid.R;
import com.example.pzl.wanandroid.base.BaseMvpFragment;
import com.example.pzl.wanandroid.bean.ProjectDetailsBean;
import com.example.pzl.wanandroid.model.ProjectModel;
import com.example.pzl.wanandroid.primary.CommonPresenter;
import com.example.pzl.wanandroid.primary.ICommonView;
import com.example.pzl.wanandroid.primary.config.IntentConfig;
import com.example.pzl.wanandroid.ui.activity.DetailsActivity;
import com.example.pzl.wanandroid.ui.adapter.ProjectDetailsAdapter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ProjectChildFragment extends BaseMvpFragment<CommonPresenter,ProjectModel> implements ICommonView, ProjectDetailsAdapter.OnLongClick {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.recycler)
    XRecyclerView recycler;

    private List<ProjectDetailsBean.DataBean.DatasBean> mList = new ArrayList<>();
    private ProjectDetailsAdapter mAdapter ;
    private int page = 0;

    private int mParam1;
    private String mParam2;

    public static ProjectChildFragment newInstance(int param1, String param2) {
        ProjectChildFragment fragment = new ProjectChildFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_project_child;
    }

    @Override
    protected void initView() {
        initRecycleView(recycler);
        mAdapter = new ProjectDetailsAdapter(getActivity(),mList);
        recycler.setAdapter(mAdapter);
        mAdapter.setOnLongClick(this);
    }

    @Override
    protected void initData() {
        mPresenter.getData(IntentConfig.NO_LOAD,page,mParam1);
    }

    @Override
    protected CommonPresenter getPresenter() {
        return new CommonPresenter();
    }

    @Override
    protected ProjectModel getModel() {
        return new ProjectModel();
    }

    @Override
    public void onResponse(Object o, int intent) {
        if (intent == IntentConfig.NO_LOAD) {
            mList.clear();
            ProjectDetailsBean bean = (ProjectDetailsBean) o;
            mList.addAll(bean.getData().getDatas());
            mAdapter.notifyDataSetChanged();
            recycler.refreshComplete();
        }
        if (intent == IntentConfig.LOAD_MORE){
            ProjectDetailsBean bean = (ProjectDetailsBean) o;
            mList.addAll(bean.getData().getDatas());
            mAdapter.notifyDataSetChanged();
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
        page=0;
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(1000);
                    mPresenter.getData(IntentConfig.NO_LOAD,page,mParam1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    public void loadMore() {
        super.loadMore();
        page++;
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(1000);
                    mPresenter.getData(IntentConfig.LOAD_MORE,page,mParam1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    public void onLong(int position) {
        ProjectDetailsBean.DataBean.DatasBean bean = mList.get(position);
        Intent intent = new Intent(getActivity(), DetailsActivity.class);
        intent.putExtra("title",bean.getTitle());
        intent.putExtra("url",bean.getLink());
        startActivity(intent);
    }

    public void jumpToTheTop() {
        if (recycler != null) {
            recycler.smoothScrollToPosition(0);
        }
    }
}
