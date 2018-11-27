package com.lzq.sprout.base.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.lzq.sprout.base.view.IBaseMvpView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.lang.ref.WeakReference;

public class BaseMvpPresenter<V extends IBaseMvpView> {
    private WeakReference<V> mViewRef;
    private WeakReference<LifecycleProvider> mLifecycleProviderRef;

    public V getView() {
        return null != mViewRef ? mViewRef.get() : null;
    }

    public void setView(V view) {
        mViewRef = new WeakReference<V>(view);
    }

    public LifecycleProvider<ActivityEvent> getLifecycleProvider() {
        return null != mLifecycleProviderRef ? mLifecycleProviderRef.get() : null;
    }

    public void setLifecycleProvider(LifecycleProvider lifecycleProvider) {
        mLifecycleProviderRef = new WeakReference<LifecycleProvider>(lifecycleProvider);
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
        onMvpDetachView();
    }

    public void onMvpSaveInstanceState(Bundle savedInstanceState) {
    }
}
