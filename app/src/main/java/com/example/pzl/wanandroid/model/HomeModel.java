package com.example.pzl.wanandroid.model;

import com.example.pzl.wanandroid.primary.ICommonModel;
import com.example.pzl.wanandroid.primary.ICommonView;
import com.example.pzl.wanandroid.primary.MyServer;
import com.example.pzl.wanandroid.primary.config.IntentConfig;
import com.example.pzl.wanandroid.utils.HttpUtils;
import com.example.pzl.wanandroid.utils.ModelUtil;

public class HomeModel implements ICommonModel {

    private MyServer mMyServer;

    @Override
    public void getData(ICommonView view, int intent, int... prams) {
        mMyServer = HttpUtils.getInstance().getServer();
        if (intent == IntentConfig.COMMON) getUsageList(view,intent,prams);
        if (intent == IntentConfig.NO_LOAD) getSearchList(view,intent,prams);
    }

    private void getSearchList(ICommonView view, int intent, int[] prams) {
        ModelUtil.getNetData(view,intent,mMyServer.getSearchList());
    }

    private void getUsageList(ICommonView view, int intent, int[] prams) {
        ModelUtil.getNetData(view,intent,mMyServer.getUsageList());
    }
}
