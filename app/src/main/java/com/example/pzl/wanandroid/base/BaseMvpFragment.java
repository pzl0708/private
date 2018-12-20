package com.example.pzl.wanandroid.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseMvpFragment<P extends BasePresenter,M> extends BaseFragment {

    public P mPresenter;
    private M mModel;
    private Unbinder mBind;
    private View mInflate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mInflate = LayoutInflater.from(getContext()).inflate(initLayout(), container, false);
        mBind = ButterKnife.bind(this,mInflate);
        initView();
        mPresenter = getPresenter();
        mModel = getModel();
        if (mPresenter != null)mPresenter.attach(mModel,this);
        initData();
        return mInflate;
    }

    protected abstract int initLayout();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract P getPresenter();

    protected abstract M getModel();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBind.unbind();
        if (mPresenter != null)mPresenter.death();
    }
}
