package com.example.pzl.wanandroid.base;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class BaseObserver implements Observer {

    private Disposable d;

    @Override
    public void onSubscribe(Disposable d) {
        this.d = d;
    }

    @Override
    public void onNext(Object value) {

    }

    @Override
    public void onError(Throwable e) {
        dispose();
    }

    @Override
    public void onComplete() {
        dispose();
    }

    private void dispose() {
        if (d != null && d.isDisposed()) {
            d.dispose();
        }
    }
}
