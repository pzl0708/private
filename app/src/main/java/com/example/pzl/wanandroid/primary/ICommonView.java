package com.example.pzl.wanandroid.primary;

import com.example.pzl.wanandroid.primary.config.IntentConfig;

public interface ICommonView<V> {
    void onResponse(V v, int intent);
    void onCompleted();
    void onError(Throwable e);
}
