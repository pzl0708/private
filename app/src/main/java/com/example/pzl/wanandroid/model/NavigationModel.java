package com.example.pzl.wanandroid.model;

import com.example.pzl.wanandroid.primary.ICommonModel;
import com.example.pzl.wanandroid.primary.ICommonView;
import com.example.pzl.wanandroid.primary.MyServer;
import com.example.pzl.wanandroid.utils.HttpUtils;
import com.example.pzl.wanandroid.utils.ModelUtil;

public class NavigationModel implements ICommonModel {

    private MyServer mMyServer;

    @Override
    public void getData(ICommonView view, int intent, int... prams) {
        mMyServer = HttpUtils.getInstance().getServer();
        ModelUtil.getNetData(view,intent,mMyServer.getNavigation());
    }
}
