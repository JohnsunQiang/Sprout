package com.lzq.sprout.base.presenter;

import com.lzq.sprout.base.view.IBaseMvpView;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BaseMvpPresenter<V extends IBaseMvpView> {
    private WeakReference<V> mViewRef;
    private WeakReference<LifecycleProvider> mLifecycleProviderRef;
    private CompositeDisposable mCompositeDisposable;

    public BaseMvpPresenter(V view) {
        onMvpAttachView(view);
    }

    public V getView() {
        return null != mViewRef ? mViewRef.get() : null;
    }

    public LifecycleProvider getLifecycleProvider() {
        return null != mLifecycleProviderRef ? mLifecycleProviderRef.get() : null;
    }

    public void setLifecycleProvider(LifecycleProvider lifecycleProvider) {
        mLifecycleProviderRef = new WeakReference<LifecycleProvider>(lifecycleProvider);
    }

    protected boolean isViewAttached() {
        return null != mViewRef && null != mViewRef.get() && mViewRef.get().isViewActive();
    }

    public void onMvpAttachView(V view) {
        mViewRef = new WeakReference<V>(view);
        mCompositeDisposable = new CompositeDisposable();
    }

    public void onMvpDetachView() {
        if (null != mViewRef) {
            mViewRef.clear();
            mViewRef = null;
        }
        if (null != mLifecycleProviderRef) {
            mLifecycleProviderRef.clear();
            mLifecycleProviderRef = null;
        }
        if (null != mCompositeDisposable) {
            mCompositeDisposable.clear();
            mCompositeDisposable = null;
        }
    }

    public void onDestroyPersenter() {
        onMvpDetachView();
    }

    public void addDisposable(Disposable disposable) {
        if (null != mCompositeDisposable && !mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.add(disposable);
        }
    }
}
