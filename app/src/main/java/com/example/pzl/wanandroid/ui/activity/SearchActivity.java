package com.example.pzl.wanandroid.ui.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.pzl.wanandroid.R;
import com.example.pzl.wanandroid.base.BaseMvpActivity;
import com.example.pzl.wanandroid.bean.SearchBean;
import com.example.pzl.wanandroid.bean.UsageBean;
import com.example.pzl.wanandroid.model.HomeModel;
import com.example.pzl.wanandroid.primary.CommonPresenter;
import com.example.pzl.wanandroid.primary.ICommonView;
import com.example.pzl.wanandroid.primary.config.IntentConfig;
import com.example.pzl.wanandroid.utils.CommonUtils;
import com.example.pzl.wanandroid.utils.StatusBarUtil;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends BaseMvpActivity<CommonPresenter, HomeModel> implements ICommonView {

    @BindView(R.id.search_back_ib)
    ImageButton searchBackIb;
    @BindView(R.id.search_tv)
    TextView searchTv;
    @BindView(R.id.search_edit)
    EditText searchEdit;
    @BindView(R.id.search_tint_tv)
    TextView searchTintTv;
    @BindView(R.id.search_toolbar)
    Toolbar searchToolbar;
    @BindView(R.id.top_search_flow_layout)
    TagFlowLayout topSearchFlowLayout;
    @BindView(R.id.search_history_clear_all_tv)
    TextView searchHistoryClearAllTv;
    @BindView(R.id.search_history_null_tint_tv)
    TextView searchHistoryNullTintTv;
    @BindView(R.id.search_history_rv)
    RecyclerView searchHistoryRv;
    @BindView(R.id.search_scroll_view)
    NestedScrollView searchScrollView;
    @BindView(R.id.search_floating_action_btn)
    FloatingActionButton searchFloatingActionBtn;

    private List<SearchBean.DataBean> mList;
    private TagAdapter<SearchBean.DataBean> mAdapter;

    @Override
    protected int initLayout() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {
        searchToolbar.setBackgroundColor(ContextCompat.getColor(this,R.color.white));
        setSupportActionBar(searchToolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayShowTitleEnabled(false);
        StatusBarUtil.setStatusColor(getWindow(), ContextCompat.getColor(this, R.color.main_status_bar_blue), 1f);

        mList = new ArrayList<>();
        mAdapter = new TagAdapter<SearchBean.DataBean>(mList){

            @Override
            public View getView(FlowLayout parent, int position, SearchBean.DataBean dataBean) {
                TextView tv = (TextView) LayoutInflater.from(SearchActivity.this).inflate(R.layout.flow_layout_tv,
                        parent, false);
                String name = dataBean.getName();
                tv.setText(name);
                setItemBackground(tv);
                topSearchFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                    @Override
                    public boolean onTagClick(View view, int position, FlowLayout parent) {
                        return true;
                    }
                });
                return tv;
            }
        };

        topSearchFlowLayout.setAdapter(mAdapter);
    }

    private void setItemBackground(TextView tv) {
        tv.setBackgroundColor(CommonUtils.randomTagColor());
        tv.setTextColor(ContextCompat.getColor(this, R.color.white));
    }

    @Override
    protected void initData() {
        mPresenter.getData(IntentConfig.NO_LOAD);
    }

    @Override
    protected CommonPresenter getPresenter() {
        return new CommonPresenter();
    }

    @Override
    protected HomeModel getModel() {
        return new HomeModel();
    }

    @Override
    public void onResponse(Object o, int intent) {
        SearchBean bean = (SearchBean) o;
        mList.addAll(bean.getData());
        mAdapter.notifyDataChanged();
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
