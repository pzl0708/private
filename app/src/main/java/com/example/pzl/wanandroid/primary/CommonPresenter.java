package com.example.pzl.wanandroid.primary;

import com.example.pzl.wanandroid.base.BasePresenter;
import com.example.pzl.wanandroid.primary.config.IntentConfig;

public class CommonPresenter extends BasePresenter<ICommonModel,ICommonView> implements ICommonPresenter,ICommonView {

    @Override
    public void getData(int intent, int... prams) {
        if (getModel()==null) return;
        getModel().getData(this,intent,prams);
    }

    @Override
    public void onResponse(Object o, int intent) {
        if (getView() != null) {
            getView().onResponse(o, intent);
        }
    }

    @Override
    public void onCompleted() {
        if (getView() != null) {
            getView().onCompleted();
        }
    }

    @Override
    public void onError(Throwable e) {
        if (getView() != null) {
            getView().onError(e);
        }
    }
}
