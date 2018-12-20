package com.example.pzl.wanandroid.model;

import com.example.pzl.wanandroid.primary.ICommonModel;
import com.example.pzl.wanandroid.primary.ICommonView;
import com.example.pzl.wanandroid.primary.MyServer;
import com.example.pzl.wanandroid.primary.config.IntentConfig;
import com.example.pzl.wanandroid.utils.HttpUtils;
import com.example.pzl.wanandroid.utils.ModelUtil;

public class KnowledgeHierarchyModel implements ICommonModel{

    private MyServer mMyServer;

    @Override
    public void getData(ICommonView view, int intent, int... prams) {
        mMyServer = HttpUtils.getInstance().getServer();
        if (intent == IntentConfig.COMMON) getKnowledgeList(view,intent,prams);
        if (intent == IntentConfig.NO_LOAD || intent == IntentConfig.REFRESH || intent == IntentConfig.LOAD_MORE) getKnowledgeDetails(view,intent,prams);
    }

    private void getKnowledgeDetails(ICommonView view, int intent, int[] prams) {
        ModelUtil.getNetData(view,intent,mMyServer.getKnowledgeDetails(prams[0],prams[1]));
    }

    private void getKnowledgeList(ICommonView view, int intent, int[] prams) {
        ModelUtil.getNetData(view,intent,mMyServer.getKnowledgeList());
    }
}
