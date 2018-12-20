package com.example.pzl.wanandroid.model;

import com.example.pzl.wanandroid.primary.ICommonModel;
import com.example.pzl.wanandroid.primary.ICommonView;
import com.example.pzl.wanandroid.primary.MyServer;
import com.example.pzl.wanandroid.primary.config.IntentConfig;
import com.example.pzl.wanandroid.utils.HttpUtils;
import com.example.pzl.wanandroid.utils.ModelUtil;

public class ProjectModel implements ICommonModel{

    private MyServer mMyServer;

    @Override
    public void getData(ICommonView view, int intent, int... prams) {
        mMyServer = HttpUtils.getInstance().getServer();
        if (intent == IntentConfig.COMMON) getProjectTitle(view,intent,prams);
        if (intent == IntentConfig.NO_LOAD || intent == IntentConfig.LOAD_MORE) getProjectDetails(view,intent,prams);
    }

    private void getProjectDetails(ICommonView view, int intent, int[] prams) {
        ModelUtil.getNetData(view,intent,mMyServer.getProjectDetails(prams[0],prams[1]));
    }

    private void getProjectTitle(ICommonView view, int intent, int[] prams) {
        ModelUtil.getNetData(view,intent,mMyServer.getProjectTitle());
    }
}
