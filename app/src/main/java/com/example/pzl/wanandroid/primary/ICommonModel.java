package com.example.pzl.wanandroid.primary;

import com.example.pzl.wanandroid.primary.ICommonView;
import com.example.pzl.wanandroid.primary.config.IntentConfig;

public interface ICommonModel {
    void getData(ICommonView view, int intent, int... prams);
}
