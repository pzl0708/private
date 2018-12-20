package com.example.pzl.wanandroid.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.pzl.wanandroid.R;
import com.example.pzl.wanandroid.base.BaseMvpActivity;
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

public class UsageActivity extends BaseMvpActivity<CommonPresenter,HomeModel> implements ICommonView {

    @BindView(R.id.common_toolbar_title_tv)
    TextView commonToolbarTitleTv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.useful_sites_flow_layout)
    TagFlowLayout usefulSitesFlowLayout;
    @BindView(R.id.usage_scroll_view)
    NestedScrollView usageScrollView;

    private List<UsageBean.DataBean> mList;
    private TagAdapter<UsageBean.DataBean> mAdapter;

    @Override
    protected int initLayout() {
        return R.layout.activity_usage;
    }

    @Override
    protected void initView() {
        toolbar.setBackgroundColor(ContextCompat.getColor(this,R.color.white));
        toolbar.setNavigationIcon(ContextCompat.getDrawable(this,R.drawable.ic_arrow_back_grey_24dp));
        commonToolbarTitleTv.setText(R.string.useful_sites);
        commonToolbarTitleTv.setTextColor(ContextCompat.getColor(this,R.color.black));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayShowTitleEnabled(false);
        StatusBarUtil.setStatusColor(getWindow(), ContextCompat.getColor(this, R.color.main_status_bar_blue), 1f);

        mList = new ArrayList<>();
        mAdapter = new TagAdapter<UsageBean.DataBean>(mList){

            @Override
            public View getView(FlowLayout parent, int position, UsageBean.DataBean dataBean) {
                TextView tv = (TextView) LayoutInflater.from(UsageActivity.this).inflate(R.layout.flow_layout_tv,
                        parent, false);
                String name = dataBean.getName();
                tv.setText(name);
                setItemBackground(tv);
                usefulSitesFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                    @Override
                    public boolean onTagClick(View view, int position, FlowLayout parent) {
                        return true;
                    }
                });
                return tv;
            }
        };

        usefulSitesFlowLayout.setAdapter(mAdapter);
    }

    private void setItemBackground(TextView tv) {
        tv.setBackgroundColor(CommonUtils.randomTagColor());
        tv.setTextColor(ContextCompat.getColor(this, R.color.white));
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
    protected HomeModel getModel() {
        return new HomeModel();
    }

    @Override
    public void onResponse(Object o, int intent) {
        UsageBean bean = (UsageBean) o;
        mList.addAll(bean.getData());
        mAdapter.notifyDataChanged();
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }
}
