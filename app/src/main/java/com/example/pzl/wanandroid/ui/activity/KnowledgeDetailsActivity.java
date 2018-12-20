package com.example.pzl.wanandroid.ui.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import com.example.pzl.wanandroid.R;
import com.example.pzl.wanandroid.base.BaseMvpActivity;
import com.example.pzl.wanandroid.model.KnowledgeHierarchyModel;
import com.example.pzl.wanandroid.primary.CommonPresenter;
import com.example.pzl.wanandroid.primary.ICommonView;
import com.example.pzl.wanandroid.ui.adapter.KnowledgeFragmentAdapter;
import com.example.pzl.wanandroid.ui.fragment.childfragment.KnowledgeChildFragment;
import com.example.pzl.wanandroid.utils.StatusBarUtil;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class KnowledgeDetailsActivity extends BaseMvpActivity<CommonPresenter, KnowledgeHierarchyModel> implements ICommonView {

    @BindView(R.id.common_toolbar_title_tv)
    TextView mTitleTv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.knowledge_hierarchy_detail_tab_layout)
    SlidingTabLayout mSlidingTabLayout;
    @BindView(R.id.knowledge_hierarchy_detail_viewpager)
    ViewPager mViewPager;
    @BindView(R.id.knowledge_floating_action_btn)
    FloatingActionButton knowledgeFloatingActionBtn;

    @Override
    protected int initLayout() {
        return R.layout.activity_knowledge_details;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        ArrayList<Integer> idList = intent.getIntegerArrayListExtra("idList");
        ArrayList<String> titleList = intent.getStringArrayListExtra("titleList");
        String title1 = intent.getStringExtra("title");
        mTitleTv.setText(title1);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayShowTitleEnabled(false);
        StatusBarUtil.setStatusColor(getWindow(), ContextCompat.getColor(this, R.color.main_status_bar_blue), 1f);

        List<Fragment> list = new ArrayList<>();
        List<String> title = new ArrayList<>();

        for (int i = 0; i < idList.size(); i++) {
            title.add(titleList.get(i));
            list.add(KnowledgeChildFragment.newInstance(null,idList.get(i)));
        }
        KnowledgeFragmentAdapter adapter = new KnowledgeFragmentAdapter(getSupportFragmentManager(), list, title);
        mViewPager.setAdapter(adapter);
        mSlidingTabLayout.setViewPager(mViewPager);
    }

    @Override
    protected void initData() {

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

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }
}
