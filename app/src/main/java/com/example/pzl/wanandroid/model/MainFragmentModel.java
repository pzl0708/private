package com.example.pzl.wanandroid.model;

import com.example.pzl.wanandroid.primary.ICommonModel;
import com.example.pzl.wanandroid.primary.ICommonView;
import com.example.pzl.wanandroid.primary.MyServer;
import com.example.pzl.wanandroid.primary.config.IntentConfig;
import com.example.pzl.wanandroid.utils.HttpUtils;
import com.example.pzl.wanandroid.utils.ModelUtil;

public class MainFragmentModel implements ICommonModel {

    private MyServer mMyServer;
    private int page = 0;

    @Override
    public void getData(ICommonView view, int intent, int... prams) {
        mMyServer = HttpUtils.getInstance().getServer();
        if (intent == IntentConfig.COMMON) getBannerImage(view,intent,prams);
        if (intent == IntentConfig.NO_LOAD || intent == IntentConfig.REFRESH) getArticleList(view,intent,prams);
        if (intent == IntentConfig.LOAD_MORE) getArticleMore(view,intent,prams);
    }

    private void getArticleMore(ICommonView view, int intent, int[] prams) {
        page++;
        ModelUtil.getNetData(view,intent,mMyServer.getArticleList(page));
    }

    private void getArticleList(ICommonView view, int intent, int[] prams) {
        page = 0;
        ModelUtil.getNetData(view,intent,mMyServer.getArticleList(page));
    }

    private void getBannerImage(ICommonView view, int intent, int[] prams) {
        ModelUtil.getNetData(view,intent,mMyServer.getBannerImage());
    }
}
