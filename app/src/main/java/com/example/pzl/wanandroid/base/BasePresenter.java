package com.example.pzl.wanandroid.base;

import java.lang.ref.WeakReference;

public class BasePresenter<M, V> {

    private WeakReference<M> mWeakReferenceM;
    private WeakReference<V> mWeakReferenceV;

    public void attach(M model, V view) {
        mWeakReferenceM = new WeakReference<M>(model);
        mWeakReferenceV = new WeakReference<V>(view);
    }

    public void death() {
        if (mWeakReferenceM != null) {
            mWeakReferenceM.clear();
            mWeakReferenceM = null;
        }
        if (mWeakReferenceV != null) {
            mWeakReferenceV.clear();
            mWeakReferenceV = null;
        }
    }

    public M getModel() {
        return mWeakReferenceM != null ? mWeakReferenceM.get() : null;
    }

    public V getView() {
        return mWeakReferenceV != null ? mWeakReferenceV.get() : null;
    }
}
