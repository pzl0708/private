package com.example.pzl.wanandroid.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseMvpActivity<P extends BasePresenter,M> extends BaseActivity {

    public P mPresenter;
    private M mModel;
    private Unbinder mBind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayout());
        mBind = ButterKnife.bind(this);
        initView();
        mPresenter = getPresenter();
        mModel = getModel();
        if (mPresenter != null)mPresenter.attach(mModel,this);
        initData();
    }

    protected abstract int initLayout();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract P getPresenter();

    protected abstract M getModel();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBind.unbind();
        if (mPresenter != null)mPresenter.death();
    }
}
