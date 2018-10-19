package com.lzq.sprout.base;

import android.os.Bundle;

import java.lang.ref.WeakReference;

public class BaseMvpPresenter<V extends IBaseMvpView> implements IBaseMvpPresenter<V> {
    private WeakReference<V> mViewRef;

    public V getView() {
        return null != mViewRef ? mViewRef.get() : null;
    }

    public void setView(V view) {
        mViewRef = new WeakReference<V>(view);
    }

    protected boolean isViewAttached() {
        return null != mViewRef && null != mViewRef.get();
    }

    @Override
    public void onMvpAttachView(V view, Bundle savedInstanceState) {
        setView(view);
    }

    @Override
    public void onMvpStart() {

    }

    @Override
    public void onMvpResume() {

    }

    @Override
    public void onMvpPause() {

    }

    @Override
    public void onMvpStop() {

    }

    @Override
    public void onMvpSaveInstanceState(Bundle savedInstanceState) {

    }

    @Override
    public void onMvpDetachView(boolean retainInstance) {
        if (null != mViewRef) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

    @Override
    public void onMvpDestroy() {

    }
}
