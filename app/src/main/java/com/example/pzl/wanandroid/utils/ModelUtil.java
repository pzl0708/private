package com.example.pzl.wanandroid.utils;

import com.example.pzl.wanandroid.base.BaseObserver;
import com.example.pzl.wanandroid.primary.ICommonView;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ModelUtil {
    public static <T> void getNetData(final ICommonView commonView, final int intent, Observable<T> observable){
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver(){
                    @Override
                    public void onNext(Object value) {
                        super.onNext(value);
                        commonView.onResponse(value,intent);
                    }

                    @Override
                    public void onComplete() {
                        super.onComplete();
                        commonView.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        commonView.onError(e);
                    }
                });
    }
}
