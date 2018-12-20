package com.example.pzl.wanandroid.ui.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import com.example.pzl.wanandroid.R;
import com.example.pzl.wanandroid.base.BaseMvpFragment;
import com.example.pzl.wanandroid.bean.ProjectTabBean;
import com.example.pzl.wanandroid.model.ProjectModel;
import com.example.pzl.wanandroid.primary.CommonPresenter;
import com.example.pzl.wanandroid.primary.ICommonView;
import com.example.pzl.wanandroid.primary.config.IntentConfig;
import com.example.pzl.wanandroid.ui.adapter.KnowledgeFragmentAdapter;
import com.example.pzl.wanandroid.ui.fragment.childfragment.ProjectChildFragment;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ProjectFragment extends BaseMvpFragment<CommonPresenter, ProjectModel> implements ICommonView {

    @BindView(R.id.project_tab_layout)
    SlidingTabLayout projectTabLayout;
    @BindView(R.id.project_divider)
    View projectDivider;
    @BindView(R.id.project_viewpager)
    ViewPager projectViewpager;
    @BindView(R.id.normal_view)
    LinearLayout normalView;

    private List<Fragment> mList = new ArrayList<>();
    private List<String> mTitle = new ArrayList<>();
    private KnowledgeFragmentAdapter mAdapter;
    private ProjectChildFragment mFragment;

    @Override
    protected int initLayout() {
        return R.layout.fragment_project;
    }

    @Override
    protected void initView() {
        mAdapter = new KnowledgeFragmentAdapter(getChildFragmentManager(),mList,mTitle);
        projectViewpager.setAdapter(mAdapter);
        projectTabLayout.setViewPager(projectViewpager);
        projectViewpager.setCurrentItem(0);
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
    protected ProjectModel getModel() {
        return new ProjectModel();
}

    @Override
    public void onResponse(Object o, int intent) {
        ProjectTabBean bean = (ProjectTabBean) o;
        List<ProjectTabBean.DataBean> data = bean.getData();
        for (ProjectTabBean.DataBean datum : data) {
            mTitle.add(datum.getName());
            mFragment = ProjectChildFragment.newInstance(datum.getId(), null);
            mList.add(mFragment);
        }
        mAdapter.setList(mList,mTitle);
        projectTabLayout.notifyDataSetChanged();
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
        mTitle.clear();
    }

    public void jumpToTheTop() {
        if (mFragment != null) {
            mFragment.jumpToTheTop();
        }
    }
}
