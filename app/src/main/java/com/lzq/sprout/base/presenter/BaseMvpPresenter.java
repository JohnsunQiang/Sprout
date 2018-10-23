package com.lzq.sprout.base.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.lzq.sprout.base.view.IBaseMvpView;

import java.lang.ref.WeakReference;

public class BaseMvpPresenter<V extends IBaseMvpView> {
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

    public void onMvpAttachView(V view) {
        setView(view);
    }

    public void onMvpDetachView() {
        if (null != mViewRef) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

    public void onCreatePersenter(@NonNull Bundle savedInstanceState) {
    }


    public void onDestroyPersenter() {
    }

    public void onMvpSaveInstanceState(Bundle savedInstanceState) {
    }
}
